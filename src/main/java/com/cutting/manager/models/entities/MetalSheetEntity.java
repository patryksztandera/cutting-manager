package com.cutting.manager.models.entities;

import javax.persistence.*;
import java.time.ZonedDateTime;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type")
    private TypeEntity typeEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_location")
    private LocationEntity locationEntity;

    public MetalSheetEntity( Double length, Double width, Double thickness, TypeEntity typeEntity, LocationEntity locationEntity) {
        this.timestamp = ZonedDateTime.now();
        this.length = length;
        this.width = width;
        this.thickness = thickness;
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

    public TypeEntity getTypeEntity() {
        return typeEntity;
    }

    public LocationEntity getLocationEntity() {
        return locationEntity;
    }
}
