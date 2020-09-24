package com.cutting.manager.models.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "type")
public class TypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 30, unique = true)
    private String type;

    @Column
    private String info;

    @OneToMany(mappedBy = "typeEntity", cascade = CascadeType.ALL)
    private List<MetalSheetEntity> metalSheet = new ArrayList<>();

    public TypeEntity(String type, String info) {
        this.type = type;
        this.info = info;
    }

    public TypeEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getInfo() {
        return info;
    }

    public void setType(String type) {
        this.type = type;
    }
}
