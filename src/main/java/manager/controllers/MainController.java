package manager.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.util.ResourceBundle;

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

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        ResourceBundle resourceBundle = ResourceBundle.getBundle("bundles.language");
        loader.setResources(resourceBundle);
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPaneMain.setCenter(parent);
    }

}
