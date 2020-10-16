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
                model.getQuantity(),
                model.getOwner(),
                typeRepository.getByType(model.getType()),
                locationRepository.getByLocation(model.getLocation()));
    }

    public void deleteById(Long id) {
        metalSheetRepository.deleteById(id);
    }

    public void updateLength(Long id, String length) {
        MetalSheetEntity entity = metalSheetRepository.getById(id);
        entity.setLength(Double.parseDouble(length));
        metalSheetRepository.save(entity);
    }

    public void updateWidth(Long id, String width) {
        MetalSheetEntity entity = metalSheetRepository.getById(id);
        entity.setWidth(Double.parseDouble(width));
        metalSheetRepository.save(entity);
    }

    public void updateThickness(Long id, String thickness) {
        MetalSheetEntity entity = metalSheetRepository.getById(id);
        entity.setThickness(Double.parseDouble(thickness));
        metalSheetRepository.save(entity);
    }

    public void updateQuantity(Long id, String quantity) {
        MetalSheetEntity entity = metalSheetRepository.getById(id);
        entity.setQuantity(Integer.parseInt(quantity));
        metalSheetRepository.save(entity);
    }
}
