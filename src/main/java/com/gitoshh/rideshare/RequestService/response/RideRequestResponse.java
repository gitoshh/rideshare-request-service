package com.gitoshh.rideshare.RequestService.response;

import com.gitoshh.rideshare.RequestService.types.RideRequestStatus;
import lombok.Builder;

@Builder
public record RideRequestResponse(
        Long id,
        Long userId,
        Long driverId,
        double startLatitude,
        double startLongitude,
        double endLatitude,
        double endLongitude,
        RideRequestStatus status,
        double driverLatitude,
        double driverLongitude,
        double cost
) {
}
