package com.gitoshh.rideshare.RequestService.entity;

import com.gitoshh.rideshare.RequestService.types.RideRequestStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ride_matches")
public class RideMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long rideRequestId;

    private double cost;

    private Long driverId;

    @Enumerated(EnumType.STRING)
    private RideMatchStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RideMatch rideMatch = (RideMatch) o;
        return getId() != null && Objects.equals(getId(), rideMatch.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public RideMatch accept() {
        this.status = RideMatchStatus.ACCEPTED;
        return this;
    }

    public RideMatch reject() {
        this.status = RideMatchStatus.REJECTED;
        return this;
    }
}
