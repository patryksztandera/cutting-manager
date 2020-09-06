package com.cutting.manager.models.responses;

import com.cutting.manager.models.entities.TypeEntity;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TypeFxModel {
    private final StringProperty type = new SimpleStringProperty();
    private final StringProperty info = new SimpleStringProperty();

    public TypeFxModel(String type, String info) {
        this.type.set(type);
        this.info.set(info);
    }

    public TypeFxModel() {
    }

    public TypeFxModel(TypeEntity typeEntity) {
        this.type.set(typeEntity.getType());
        this.info.set(typeEntity.getInfo());
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public String getInfo() {
        return info.get();
    }

    public StringProperty infoProperty() {
        return info;
    }
}
