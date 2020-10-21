package com.cutting.manager.models.responses;

import com.cutting.manager.models.entities.ClientEntity;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ClientFxModel {
    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty surname = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();

    public ClientFxModel(String name, String surname, String email, String password) {
        this.name.setValue(name);
        this.surname.setValue(surname);
        this.email.setValue(email);
        this.password.setValue(password);
    }

    public ClientFxModel() {
    }

    public ClientFxModel(ClientEntity entity) {
        this.id.setValue(entity.getId());
        this.name.setValue(entity.getName());
        this.surname.setValue(entity.getSurname());
        this.email.setValue(entity.getEmail());
        this.password.setValue(entity.getPassword());
    }

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getSurname() {
        return surname.get();
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }
}
