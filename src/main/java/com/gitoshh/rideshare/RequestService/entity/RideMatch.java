package com.gitoshh.rideshare.RequestService.entity;

import jakarta.persistence.*;

@Entity
public class RideMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long rideRequestId;

    private int cost;

    private Long driverId;

    @Enumerated(EnumType.STRING)
    private RideMatchStatus status;

}
