package database.manager.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

public class MainController {

    @FXML
    private BorderPane borderPaneMain;

    @FXML
    private TopMenuToggleController topMenuController;

    @FXML
    private void initialize(){

        topMenuController.setMainController(this);
    }

    public void setCenterBorderPane(String fxmlPath){

        FXMLLoader loader = new FXMLLoader();
        Parent parent = null;
        try {
            parent = loader.load(getClass().getResource(fxmlPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPaneMain.setCenter(parent);
    }

}
