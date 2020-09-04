package com.cutting.manager.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "location")
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String location;

    public LocationEntity(String location) {
        this.location = location;
    }

    public LocationEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

}
