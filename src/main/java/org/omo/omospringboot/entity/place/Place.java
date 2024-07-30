package org.omo.omospringboot.entity.place;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "location_type")
public abstract class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private String overview;

    private String contactNumber; // 문의 및 안내
}