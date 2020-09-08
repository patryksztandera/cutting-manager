package com.cutting.manager.models.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "location")
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 250, unique = true)
    private String location;

    @OneToMany(mappedBy = "locationEntity", cascade = CascadeType.ALL)
    private List<MetalSheetEntity> metalSheet = new ArrayList<>();

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
