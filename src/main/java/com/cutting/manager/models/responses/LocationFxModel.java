package com.cutting.manager.models.responses;

import com.cutting.manager.models.entities.LocationEntity;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LocationFxModel {
    private final StringProperty location = new SimpleStringProperty();

    public LocationFxModel(String location) {
        this.location.set(location);
    }

    public LocationFxModel() {
    }

    public LocationFxModel(LocationEntity locationEntity) {
        this.location.set(locationEntity.getLocation());
    }

    public String getLocation() {
        return location.get();
    }

    public StringProperty locationProperty() {
        return location;
    }
}
