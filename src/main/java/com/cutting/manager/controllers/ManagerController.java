package com.cutting.manager.controllers;

import com.cutting.manager.models.responses.ClientFxModel;
import com.cutting.manager.singleton.ClientSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ManagerController {
    @Value("classpath:/fxml/metalSheet.fxml")
    private Resource metalSheetResource;
    @Value("classpath:/fxml/location.fxml")
    private Resource locationResource;
    @Value("classpath:/fxml/type.fxml")
    private Resource typeResource;
    @Value("classpath:/fxml/jobMenu.fxml")
    private Resource jobMenuResource;
    @Value("classpath:/fxml/changePassword.fxml")
    private Resource userResource;
    @Value("classpath:/fxml/register.fxml")
    private Resource adminResource;
    @Value("classpath:/fxml/loginForm.fxml")
    private Resource loginResource;
    private final ApplicationContext applicationContext;
    @FXML
    public BorderPane borderPane;
    @FXML
    public Button userButton;
    @FXML
    public Label displayLabel;
    @FXML
    public Label temperatureLabel;
    ClientFxModel clientFxModel;

    public ManagerController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @FXML
    public void initialize() throws IOException {
        ClientSingleton clientSingleton = ClientSingleton.getINSTANCE();
        this.clientFxModel = clientSingleton.getClientFxModel();

        this.displayLabel.setText("Hello, " + this.clientFxModel.getName() + " " + this.clientFxModel.getSurname());

        Document document = Jsoup
                .connect("https://www.accuweather.com/pl/pl/warsaw/274663/weather-forecast/274663").get();
        this.temperatureLabel.setText("Warsaw, " + document.getElementsByClass("temp").get(0).text());
    }

    @FXML
    public void loadMetalSheet() throws IOException {
        borderPane.setCenter(loadStage(metalSheetResource));
    }

    @FXML
    public void loadLocation() throws IOException {
        borderPane.setCenter(loadStage(locationResource));
    }

    @FXML
    public void loadType() throws IOException {
        borderPane.setCenter(loadStage(typeResource));
    }

    @FXML
    public void loadJob() throws IOException {
        borderPane.setCenter(loadStage(jobMenuResource));
    }

    @FXML
    public void loadUser() throws IOException {
        if (this.clientFxModel.isAdmin()) {
            borderPane.setCenter(loadStage(adminResource));
        } else {
            borderPane.setCenter(loadStage(userResource));
        }
    }

    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        Scene loginScene = new Scene(loadStage(loginResource));

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(loginScene);

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((primaryScreenBounds.getWidth() - window.getWidth()) / 2);
        window.setY((primaryScreenBounds.getHeight() - window.getHeight()) / 2);

        window.show();

    }

    private Parent loadStage(Resource resource) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(resource.getURL());
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        return fxmlLoader.load();
    }
}
