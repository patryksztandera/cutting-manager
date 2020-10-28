package com.cutting.manager.controllers;

import com.cutting.manager.models.services.ClientService;
import com.cutting.manager.singleton.ClientSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
public class LoginController {
    private final ClientService clientService;
    private final ApplicationContext applicationContext;
    @Value("classpath:/fxml/manager.fxml")
    private Resource managerResource;
    @Value("classpath:/fxml/register.fxml")
    private Resource registerResource;
    @FXML
    public TextField emailTextField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Button loginButton;

    public LoginController(ClientService clientService, ApplicationContext applicationContext) {
        this.clientService = clientService;
        this.applicationContext = applicationContext;
    }

    public void initialize() {
    }

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        ClientSingleton clientSingleton = ClientSingleton.getINSTANCE();
        clientSingleton.setClientFxModel(clientService.getByEmail(this.emailTextField.getText()));
        if (BCrypt.checkpw(this.passwordField.getText(), clientService.getByEmail(this.emailTextField.getText()).getPassword())) {
            loadStage(actionEvent, managerResource);
        }
    }

    private void loadStage(ActionEvent actionEvent, Resource resource) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(resource.getURL());
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        Scene managerScene = new Scene(fxmlLoader.load());

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(managerScene);
        window.show();
    }
}
