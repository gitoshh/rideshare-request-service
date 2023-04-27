package com.gitoshh.rideshare.RequestService.service;

import com.gitoshh.rideshare.RequestService.entity.RideRequest;
import com.gitoshh.rideshare.RequestService.exception.NotFoundException;
import com.gitoshh.rideshare.RequestService.repo.RideRequestRepository;
import com.gitoshh.rideshare.RequestService.request.LocatorRequest;
import com.gitoshh.rideshare.RequestService.request.RideRequestCreateRequest;
import com.gitoshh.rideshare.RequestService.response.LocatorResponse;
import com.gitoshh.rideshare.RequestService.response.RideRequestResponse;
import com.gitoshh.rideshare.RequestService.types.RideRequestStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public record RideRequestService(RideRequestRepository rideRequestRepository, WebClient.Builder webClient, CostingService costingService) {
    @Value("${location-service.url}")
    private static String locationServiceUrl;

    /**
     * Create a new ride request.
     *
     * @param rideRequestCreateRequest The request to create a new ride request.
     * @return The ride request response.
     */
    public RideRequestResponse createRideRequest(RideRequestCreateRequest rideRequestCreateRequest) {
        RideRequest rideRequest = RideRequest.builder()
                .userId(rideRequestCreateRequest.userId())
                .startLatitude(rideRequestCreateRequest.startLatitude())
                .startLongitude(rideRequestCreateRequest.startLongitude())
                .endLatitude(rideRequestCreateRequest.endLatitude())
                .endLongitude(rideRequestCreateRequest.endLongitude())
                .status(RideRequestStatus.PENDING)
                .build();

        rideRequestRepository.save(rideRequest);

        LocatorResponse locatedDriver = fetchClosestRide(rideRequestCreateRequest.startLatitude(), rideRequestCreateRequest.startLongitude());
        double cost = costingService.calculateCost(rideRequestCreateRequest.startLatitude(), rideRequestCreateRequest.startLongitude(), rideRequestCreateRequest.endLatitude(), rideRequestCreateRequest.endLongitude());


        return RideRequestResponse.builder().
                id(rideRequest.getId())
                .userId(rideRequest.getUserId())
                .startLatitude(rideRequest.getStartLatitude())
                .startLongitude(rideRequest.getStartLongitude())
                .endLatitude(rideRequest.getEndLatitude())
                .endLongitude(rideRequest.getEndLongitude())
                .status(rideRequest.getStatus())
                .driverId(locatedDriver.userId())
                .driverLatitude(locatedDriver.latitude())
                .driverLongitude(locatedDriver.longitude())
                .cost(cost)
                .build();
    }

    /**
     * Fetch a ride request by id.
     *
     * @param id The id of the ride request to fetch.
     * @return The fetched ride request.
     */
    public RideRequest findRideRequestById(Long id) {
        return rideRequestRepository.findById(id).orElseThrow(
                () ->
                        new NotFoundException("Ride request not found"));
    }

    /**
     * Accept a ride request.
     *
     * @param id The id of the ride request to accept.
     * @return The accepted ride request.
     */
    public RideRequest acceptRideRequest(Long id) {
        RideRequest rideRequest = findRideRequestById(id);
        rideRequest.setStatus(RideRequestStatus.ACCEPTED);
        return rideRequestRepository.save(rideRequest);
    }

    /**
     * Start a ride request.
     *
     * @param id The id of the ride request to accept.
     * @return The accepted ride request.
     */
    public RideRequest startRideRequest(Long id) {
        RideRequest rideRequest = findRideRequestById(id);
        rideRequest.setStatus(RideRequestStatus.STARTED);
        return rideRequestRepository.save(rideRequest);
    }

    /**
     * Reject a ride request.
     *
     * @param id The id of the ride request to reject.
     * @return The rejected ride request.
     */
    public RideRequest rejectRideRequest(Long id) {
        RideRequest rideRequest = findRideRequestById(id);
        rideRequest.setStatus(RideRequestStatus.REJECTED);
        return null;
    }

    /**
     * Accept a ride request.
     *
     * @param id The id of the ride request to accept.
     * @return The accepted ride request.
     */
    public RideRequest cancelRideRequest(Long id) {
        RideRequest rideRequest = findRideRequestById(id);
        rideRequest.setStatus(RideRequestStatus.CANCELLED);
        return rideRequestRepository.save(rideRequest);
    }

    /**
     * Complete a ride request.
     *
     * @param id The id of the ride request to complete.
     * @return The completed ride request.
     */
    public RideRequest completeRideRequest(Long id) {
        RideRequest rideRequest = findRideRequestById(id);
        rideRequest.setStatus(RideRequestStatus.COMPLETED);
        rideRequest.setEndTime(LocalDateTime.now());
        return rideRequestRepository.save(rideRequest);
    }

    /**
     * Fetch closest ride.
     *
     * @param latitude  The latitude of the user.
     * @param longitude The longitude of the user.
     */
    public LocatorResponse fetchClosestRide(double latitude, double longitude) {
        LocatorRequest locatorRequest = new LocatorRequest(latitude, longitude);

        return webClient.build()
                .post()
                .uri(locationServiceUrl + "/locations/closest")
                .body(locatorRequest, LocatorRequest.class)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(LocatorResponse.class)
                .timeout(Duration.ofMillis(5000))
                .block();
    }
}
