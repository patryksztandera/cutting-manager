package com.cutting.manager.models.services;

import com.cutting.manager.models.entities.LocationEntity;
import com.cutting.manager.models.repositories.LocationRepository;
import com.cutting.manager.models.responses.LocationFxModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public ObservableList<LocationFxModel> getAll() {
        ObservableList<LocationFxModel> locationModelObservableList = FXCollections.observableArrayList();

        locationRepository.findAll().forEach(e -> locationModelObservableList.add(new LocationFxModel(e)));
        return locationModelObservableList;
    }

    public void add(LocationFxModel model) {
        locationRepository.save(mapFxModel(model));
    }

    private LocationEntity mapFxModel(final LocationFxModel model) {
        return new LocationEntity(model.getLocation());
    }
}
