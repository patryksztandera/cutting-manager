package com.cutting.manager.models.services;

import com.cutting.manager.models.entities.MetalSheetEntity;
import com.cutting.manager.models.repositories.LocationRepository;
import com.cutting.manager.models.repositories.MetalSheetRepository;
import com.cutting.manager.models.repositories.TypeRepository;
import com.cutting.manager.models.responses.MetalSheetFxModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class MetalSheetService {
    private final MetalSheetRepository metalSheetRepository;
    private final TypeRepository typeRepository;
    private final LocationRepository locationRepository;

    public MetalSheetService(MetalSheetRepository metalSheetRepository, TypeRepository typeRepository, LocationRepository locationRepository) {
        this.metalSheetRepository = metalSheetRepository;
        this.typeRepository = typeRepository;
        this.locationRepository = locationRepository;
    }

    public ObservableList<MetalSheetFxModel> getAll() {
        ObservableList<MetalSheetFxModel> metalSheetModelObservableList = FXCollections.observableArrayList();

        metalSheetRepository.findAll().forEach(e -> metalSheetModelObservableList.add(new MetalSheetFxModel(e)));
        return metalSheetModelObservableList;
    }

    public void add(MetalSheetFxModel model) {
        metalSheetRepository.save(mapFxModel(model));
    }

    private MetalSheetEntity mapFxModel(MetalSheetFxModel model) {
        return new MetalSheetEntity(
                model.getLength(),
                model.getWidth(),
                model.getThickness(),
                typeRepository.getByType(model.getType()),
                locationRepository.getByLocation(model.getLocation()));
    }

    public void deleteById(Long id) {
        metalSheetRepository.deleteById(id);
        //metalSheetRepository.deleteByTimestamp(zonedDateTime);
    }
}
