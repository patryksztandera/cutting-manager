package com.cutting.manager.models.entities;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "jobs")
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private ZonedDateTime postTime;

    @Column
    private ZonedDateTime endTime;

    @Column
    private String name;

    @Column
    private String fileType;

    @Lob
    @Column
    private byte[] imgByte;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_metal_sheet")
    private MetalSheetEntity metalSheetEntity;

    public JobEntity(String name, String fileType, byte[] imgByte, MetalSheetEntity metalSheetEntity) {
        this.postTime = ZonedDateTime.now();
        this.name = name;
        this.fileType = fileType;
        this.imgByte = imgByte;
        this.metalSheetEntity = metalSheetEntity;
    }

    public JobEntity() {
    }

    public Long getId() {
        return id;
    }

    public ZonedDateTime getPostTime() {
        return postTime;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public String getName() {
        return name;
    }

    public String getFileType() {
        return fileType;
    }

    public byte[] getImgByte() {
        return imgByte;
    }

    public MetalSheetEntity getMetalSheetEntity() {
        return metalSheetEntity;
    }

    public void setEndTime(ZonedDateTime endTime) {
        this.endTime = endTime;
    }
}
