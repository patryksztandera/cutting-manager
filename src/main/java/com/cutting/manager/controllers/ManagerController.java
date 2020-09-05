package com.cutting.manager.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
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
    private final ApplicationContext applicationContext;
    @FXML
    public BorderPane borderPane;
    @FXML
    public Button metalSheetButton;

    public ManagerController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @FXML
    public void initialize() {
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

    private Parent loadStage(Resource resource) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(resource.getURL());
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        return fxmlLoader.load();
    }
}
