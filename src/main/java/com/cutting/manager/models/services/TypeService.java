package com.cutting.manager.models.services;

import com.cutting.manager.models.entities.TypeEntity;
import com.cutting.manager.models.repositories.TypeRepository;
import com.cutting.manager.models.responses.TypeFxModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;

@Service
public class TypeService {
    private final TypeRepository typeRepository;

    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public ObservableList<TypeFxModel> getAll() {
        ObservableList<TypeFxModel> typeModelObservableList = FXCollections.observableArrayList();

        typeRepository.findAll().forEach(e -> typeModelObservableList.add(new TypeFxModel(e)));
        return typeModelObservableList;
    }

    public void add(TypeFxModel model) {
        typeRepository.save(mapFxModel(model));
    }

    private TypeEntity mapFxModel(final TypeFxModel model) {
        return new TypeEntity(model.getType(), model.getInfo());
    }
}
