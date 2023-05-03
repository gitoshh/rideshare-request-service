package com.gitoshh.rideshare.RequestService.entity;

import com.gitoshh.rideshare.RequestService.types.RideRequestStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class RideRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    private Long driverId;

    private int cost;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Column(nullable = false)
    private double startLatitude;

    @Column(nullable = false)
    private double startLongitude;

    @Column(nullable = false)
    private double endLatitude;

    @Column(nullable = false)
    private double endLongitude;

    @Column(nullable = false)
    private RideRequestStatus status;

    @CreationTimestamp
    public LocalDateTime createdAt;

    @UpdateTimestamp
    public LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RideRequest that = (RideRequest) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public RideRequest accept() {
        this.status = RideRequestStatus.ACCEPTED;
        return this;
    }

    public RideRequest reject() {
        this.status = RideRequestStatus.REJECTED;
        return this;
    }
}
