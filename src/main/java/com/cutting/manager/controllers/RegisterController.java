package com.cutting.manager.controllers;

import com.cutting.manager.models.responses.ClientFxModel;
import com.cutting.manager.models.services.ClientService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RegisterController {
    private final ClientService clientService;
    private final ApplicationContext applicationContext;
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
    public PasswordField repeatField;
    @FXML
    public CheckBox adminCheckBox;

    public RegisterController(ClientService clientService, ApplicationContext applicationContext) {
        this.clientService = clientService;
        this.applicationContext = applicationContext;
    }

    public void initialize(){
        repeatFieldBindings();
        buttonBindings();
    }

    private void repeatFieldBindings() {
        this.repeatField.disableProperty().bind(this.passwordField.textProperty().isEmpty()
                .or(this.passwordField.textProperty().length().lessThan(4)));
    }

    private void buttonBindings() {
        this.signButton.disableProperty().bind(this.nameTextField.textProperty().isEmpty()
                .or(this.surnameTextField.textProperty().isEmpty())
                .or(this.emailTextFiled.textProperty().isEmpty())
                .or(this.passwordField.textProperty().isEmpty())
                .or(this.repeatField.textProperty().isNotEqualTo((this.passwordField.textProperty()))));
    }

    public void signUp(ActionEvent actionEvent) throws IOException {
        clientService.add(new ClientFxModel(
                this.nameTextField.getText(),
                this.surnameTextField.getText(),
                this.emailTextFiled.getText(),
                BCrypt.hashpw(this.passwordField.getText(), BCrypt.gensalt()),
                this.adminCheckBox.isSelected()));

        FXMLLoader fxmlLoader = new FXMLLoader(managerResource.getURL());
        fxmlLoader.setControllerFactory(applicationContext::getBean);

        Scene managerScene = new Scene(fxmlLoader.load());

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(managerScene);
        window.show();
    }
}
