package com.cutting.manager.models.services;

import com.cutting.manager.models.entities.ClientEntity;
import com.cutting.manager.models.repositories.ClientRepository;
import com.cutting.manager.models.responses.ClientFxModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientFxModel getByEmail(String email) {
        return new ClientFxModel(clientRepository.getByEmail(email));
    }

    public ObservableList<ClientFxModel> getAll() {
        ObservableList<ClientFxModel> clientFxModelObservableList = FXCollections.observableArrayList();

        clientRepository.findAll().forEach(e -> clientFxModelObservableList.add(new ClientFxModel(e)));
        return clientFxModelObservableList;
    }

    public void add(ClientFxModel model) {
        clientRepository.save(mapFxModel(model));
    }

    private ClientEntity mapFxModel(ClientFxModel model) {
        return new ClientEntity(
                model.getName(),
                model.getSurname(),
                model.getEmail(),
                model.getPassword(),
                model.isAdmin());
    }
}
