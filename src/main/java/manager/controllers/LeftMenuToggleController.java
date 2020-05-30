package manager.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;

import java.io.IOException;

public class LeftMenuToggleController {

    @FXML
    private MainController mainController;

    @FXML
    private ToggleButton sheetToggleButton;

    @FXML
    private ToggleButton typeToggleButton;

    @FXML
    private ToggleButton locationToggleButton;

    @FXML
    public void openSheet() throws IOException{
        if (sheetToggleButton.isSelected()) {
            mainController.setCenterBorderPane("/fxml/Sheet.fxml");
        }
        else{
            mainController.setCenterBorderPane("/fxml/DefaultScreen.fxml");
        }
    }

    @FXML
    public void openType() throws IOException{

        if (typeToggleButton.isSelected()) {
            mainController.setCenterBorderPane("/fxml/Type.fxml");
        }
        else{
            mainController.setCenterBorderPane("/fxml/DefaultScreen.fxml");
        }
    }

    @FXML
    public void openLocation()throws IOException {

        if (locationToggleButton.isSelected()) {
            mainController.setCenterBorderPane("/fxml/Location.fxml");
        }
        else{
            mainController.setCenterBorderPane("/fxml/DefaultScreen.fxml");
        }
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
