package com.cutting.manager.models.services;

import com.cutting.manager.models.entities.LocationEntity;
import com.cutting.manager.models.repositories.LocationRepository;
import com.cutting.manager.models.responses.LocationFxModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public ObservableList<String> getLocations() {
        ObservableList<String> locationObservableList = FXCollections.observableArrayList();

        locationRepository.findAll().forEach(e -> locationObservableList.add(e.getLocation()));
        return locationObservableList;
    }

    public void add(LocationFxModel model) {
        locationRepository.save(mapFxModel(model));
    }

    private LocationEntity mapFxModel(final LocationFxModel model) {
        return new LocationEntity(model.getLocation());
    }

    @Transactional
    public void delete(String location) {
        locationRepository.deleteByLocation(location);
    }

    public void updateLocation(String oldLocation, String newLocation) {
        LocationEntity entity = locationRepository.getByLocation(oldLocation);
        entity.setLocation(newLocation);
        locationRepository.save(entity);
    }
}
