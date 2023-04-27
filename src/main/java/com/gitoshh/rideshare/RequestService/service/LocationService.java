package com.gitoshh.rideshare.RequestService.service;

import org.springframework.stereotype.Component;

@Component
public class LocationService {
    /**
     * Calculate distance between two points in latitude and longitude.
     * Uses Haversine method as its base.
     *
     * @param lat1 Latitude of the first point
     * @param lat2 Latitude of the second point
     * @param lon1 Longitude of the first point
     * @param lon2 Longitude of the second point
     */
    public double calculateDistance(double lat1, double lat2, double lon1,
                                    double lon2) {
        // distance between latitudes and longitudes
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        // convert to radians
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // apply formulae
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(lat1) *
                        Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    }
}
