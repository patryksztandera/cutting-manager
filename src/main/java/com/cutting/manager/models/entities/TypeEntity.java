package com.cutting.manager.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "type")
public class TypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String type;

    @Column
    private String info;

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
}
