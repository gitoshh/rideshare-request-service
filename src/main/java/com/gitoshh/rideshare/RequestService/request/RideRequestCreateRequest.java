package com.gitoshh.rideshare.RequestService.request;

import java.time.LocalDateTime;

public record RideRequestCreateRequest(
        Long userId,
        Long driverId,
        double startLatitude,
        double startLongitude,
        double endLatitude,
        double endLongitude
) {
}
