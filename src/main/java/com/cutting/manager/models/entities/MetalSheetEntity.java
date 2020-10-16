package com.cutting.manager.models.entities;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "metal_sheet")
public class MetalSheetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private ZonedDateTime timestamp;

    @Column
    private Double length;

    @Column
    private Double width;

    @Column
    private Double thickness;

    @Column
    private Integer quantity;

    @Column
    private String owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type")
    private TypeEntity typeEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_location")
    private LocationEntity locationEntity;

    @OneToMany(mappedBy = "metalSheetEntity", cascade = CascadeType.ALL)
    private List<JobEntity> job = new ArrayList<>();

    public MetalSheetEntity( Double length, Double width, Double thickness, Integer quantity, String owner, TypeEntity typeEntity, LocationEntity locationEntity) {
        this.timestamp = ZonedDateTime.now();
        this.length = length;
        this.width = width;
        this.thickness = thickness;
        this.quantity = quantity;
        this.owner = owner;
        this.typeEntity = typeEntity;
        this.locationEntity = locationEntity;
    }

    public MetalSheetEntity() {
    }

    public Long getId() {
        return id;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public Double getLength() {
        return length;
    }

    public Double getWidth() {
        return width;
    }

    public Double getThickness() {
        return thickness;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getOwner() {
        return owner;
    }

    public TypeEntity getTypeEntity() {
        return typeEntity;
    }

    public LocationEntity getLocationEntity() {
        return locationEntity;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public void setThickness(Double thickness) {
        this.thickness = thickness;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
