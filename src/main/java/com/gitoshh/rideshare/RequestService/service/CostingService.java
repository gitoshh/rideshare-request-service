package com.gitoshh.rideshare.RequestService.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class CostingService {
    private final LocationService locationService;

    @Value("${app.chargePerKm}")
    private double chargePerKm;

    @Value("${app.serviceCharge}")
    private double serviceCharge;

    /**
     * Calculate cost of a ride given distance in kilometers
     *
     * @param distance in kilometers
     * @return cost in shilling
     */
    public double calculateCost(double distance) {
        double cost = distance * chargePerKm + serviceCharge;
        return Math.floor(cost);
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
