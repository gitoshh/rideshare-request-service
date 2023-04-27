package com.gitoshh.rideshare.RequestService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CostingService {
    private final LocationService locationService;

    @Value("${app.charge-per-km}")
    private static double chargePerKm;

    @Value("${app.service-charge}")
    private static double serviceCharge;

    /**
     * Calculate cost of a ride given distance in kilometers
     *
     * @param distance in kilometers
     * @return cost in shilling
     */
    public double calculateCost(double distance) {
        return distance * chargePerKm + serviceCharge;
    }

    /**
     * Calculate cost of a ride given two points
     *
     * @param latitude1  latitude of first point
     * @param longitude1 longitude of first point
     * @param latitude2  latitude of second point
     * @param longitude2 longitude of second point
     * @return cost in shilling
     */
    public double calculateCost(double latitude1, double longitude1, double latitude2, double longitude2) {
        return calculateCost(locationService.calculateDistance(latitude1, longitude1, latitude2, longitude2));
    }
}
