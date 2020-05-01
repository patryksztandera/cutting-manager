package database.manager.controllers;

import javafx.fxml.FXML;

public class TopMenuToggleController {

    @FXML
    private MainController mainController;

    @FXML
    public void openSheet() {
        mainController.setCenterBorderPane("/fxml/Sheet.fxml");
    }

    @FXML
    public void openType() {
        mainController.setCenterBorderPane("/fxml/Type.fxml");
    }

    @FXML
    public void openLocation() {
        mainController.setCenterBorderPane("/fxml/Location.fxml");
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

}
