package com.cutting.manager.controllers;

import com.cutting.manager.models.responses.ClientFxModel;
import com.cutting.manager.models.services.ClientService;
import com.cutting.manager.singleton.ClientSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class ChangePasswordController {
    private final ClientService clientService;
    @FXML
    public PasswordField oldPasswordField;
    @FXML
    public PasswordField newPasswordField;
    @FXML
    public PasswordField repeatPasswordField;
    @FXML
    public Button changePasswordButton;
    ClientFxModel clientFxModel;

    public ChangePasswordController(ClientService clientService) {
        this.clientService = clientService;
    }

    public void initialize() {
        ClientSingleton clientSingleton = ClientSingleton.getINSTANCE();
        this.clientFxModel = clientSingleton.getClientFxModel();

        repeatPasswordFieldBindings();
        changePasswordButtonBindings();
    }

    private void repeatPasswordFieldBindings() {
        this.repeatPasswordField.disableProperty().bind(this.newPasswordField.textProperty().isEmpty()
                .or(this.newPasswordField.textProperty().length().lessThan(4)));
    }

    private void changePasswordButtonBindings() {
        this.changePasswordButton.disableProperty().bind(this.oldPasswordField.textProperty().isEmpty()
                .or(this.oldPasswordField.textProperty().isEmpty())
                .or(this.repeatPasswordField.textProperty().isNotEqualTo(this.newPasswordField.textProperty())));
    }

    public void changePasswordOnAction(ActionEvent actionEvent) {
        if (BCrypt.checkpw(this.oldPasswordField.getText(), this.clientFxModel.getPassword())) {
            clientService.changePassword(this.clientFxModel.getEmail(),
                    BCrypt.hashpw(this.newPasswordField.getText(), BCrypt.gensalt()));
        }

        this.oldPasswordField.clear();
        this.newPasswordField.clear();
        this.repeatPasswordField.clear();
    }
}
