package manager.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import manager.utils.DialogsUtils;
import manager.utils.FxmlUtils;

import java.io.IOException;
import java.util.Optional;
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

    public void setCenterBorderPane(String fxmlPath) {
        Parent parent = FxmlUtils.fxmlLoad(fxmlPath);
        borderPaneMain.setCenter(parent);
    }

    public void closeApplication() {
        Optional<ButtonType> buttonType = DialogsUtils.exitConfirmation();
        if(buttonType.get() == ButtonType.OK) {
            Platform.exit();
            System.exit(0);
        }
    }

    public void aboutApplication() {
        DialogsUtils.aboutApplication();
    }
}
