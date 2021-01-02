package com.cutting.manager.controllers;

import com.cutting.manager.models.responses.ClientFxModel;
import com.cutting.manager.models.services.ClientService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RegisterController {
    private final ClientService clientService;
    @Value("classpath:/fxml/manager.fxml")
    private Resource managerResource;
    @FXML
    public TextField nameTextField;
    @FXML
    public TextField surnameTextField;
    @FXML
    public TextField emailTextFiled;
    @FXML
    public Button signButton;
    @FXML
    public PasswordField passwordField;
    @FXML
    public PasswordField repeatPasswordField;
    @FXML
    public CheckBox adminCheckBox;

    public RegisterController(ClientService clientService) {
        this.clientService = clientService;
    }

    public void initialize(){
        repeatPasswordFieldBindings();
        buttonBindings();
    }

    private void repeatPasswordFieldBindings() {
        this.repeatPasswordField.disableProperty().bind(this.passwordField.textProperty().isEmpty()
                .or(this.passwordField.textProperty().length().lessThan(4)));
    }

    private void buttonBindings() {
        this.signButton.disableProperty().bind(this.nameTextField.textProperty().isEmpty()
                .or(this.surnameTextField.textProperty().isEmpty())
                .or(this.emailTextFiled.textProperty().isEmpty())
                .or(this.passwordField.textProperty().isEmpty())
                .or(this.repeatPasswordField.textProperty().isNotEqualTo((this.passwordField.textProperty()))));
    }

    public void signUp(ActionEvent actionEvent) throws IOException {
        clientService.add(new ClientFxModel(
                this.nameTextField.getText(),
                this.surnameTextField.getText(),
                this.emailTextFiled.getText(),
                BCrypt.hashpw(this.passwordField.getText(), BCrypt.gensalt()),
                this.adminCheckBox.isSelected()));

        this.nameTextField.clear();
        this.surnameTextField.clear();
        this.emailTextFiled.clear();
        this.adminCheckBox.setSelected(false);
        this.passwordField.clear();
        this.repeatPasswordField.clear();
    }
}
