package com.cutting.manager.models.services;

import com.cutting.manager.models.entities.TypeEntity;
import com.cutting.manager.models.repositories.TypeRepository;
import com.cutting.manager.models.responses.TypeFxModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TypeService {
    private final TypeRepository typeRepository;

    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public TypeFxModel getByType(String type) {
        return new TypeFxModel(typeRepository.getByType(type));
    }

    public ObservableList<TypeFxModel> getAll() {
        ObservableList<TypeFxModel> typeModelObservableList = FXCollections.observableArrayList();

        typeRepository.findAll().forEach(e -> typeModelObservableList.add(new TypeFxModel(e)));
        return typeModelObservableList;
    }

    public ObservableList<String> getTypes() {
        ObservableList<String> typeObservableList = FXCollections.observableArrayList();

        typeRepository.findAll().forEach(e -> typeObservableList.add(e.getType()));
        return typeObservableList;
    }

    public void add(TypeFxModel model) {
        typeRepository.save(mapFxModel(model));
    }

    private TypeEntity mapFxModel(final TypeFxModel model) {
        return new TypeEntity(model.getType(), model.getInfo());
    }

    @Transactional
    public void delete(String type) {
        typeRepository.deleteByType(type);
    }

    public void updateType(String oldType, String newType) {
        TypeEntity entity = typeRepository.getByType(oldType);
        entity.setType(newType);
        typeRepository.save(entity);
    }

    public void updateInfo(String type, String newInfo) {
        TypeEntity entity = typeRepository.getByType(type);
        entity.setInfo(newInfo);
        typeRepository.save(entity);
    }
}
