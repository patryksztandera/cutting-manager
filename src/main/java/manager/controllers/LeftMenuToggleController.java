package manager.controllers;

import javafx.fxml.FXML;

import java.io.IOException;

public class LeftMenuToggleController {

    @FXML
    private MainController mainController;

    @FXML
    public void openSheet() throws IOException{
        mainController.setCenterBorderPane("/fxml/Sheet.fxml");
    }

    @FXML
    public void openType() throws IOException{
        mainController.setCenterBorderPane("/fxml/Type.fxml");
    }

    @FXML
    public void openLocation()throws IOException {
        mainController.setCenterBorderPane("/fxml/Location.fxml");
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

}
