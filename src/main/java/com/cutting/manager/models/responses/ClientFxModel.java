package com.cutting.manager.models.responses;

import com.cutting.manager.models.entities.ClientEntity;
import javafx.beans.property.*;

public class ClientFxModel {
    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty surname = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();
    private final BooleanProperty admin = new SimpleBooleanProperty();

    public ClientFxModel(String name, String surname, String email, String password, Boolean admin) {
        this.name.setValue(name);
        this.surname.setValue(surname);
        this.email.setValue(email);
        this.password.setValue(password);
        this.admin.setValue(admin);
    }

    public ClientFxModel() {
    }

    public ClientFxModel(ClientEntity entity) {
        this.id.setValue(entity.getId());
        this.name.setValue(entity.getName());
        this.surname.setValue(entity.getSurname());
        this.email.setValue(entity.getEmail());
        this.password.setValue(entity.getPassword());
        this.admin.setValue(entity.getAdmin());
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

    public boolean isAdmin() {
        return admin.get();
    }

    public BooleanProperty adminProperty() {
        return admin;
    }
}
