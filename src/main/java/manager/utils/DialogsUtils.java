package manager.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;
import java.util.ResourceBundle;

public class DialogsUtils {

    private static ResourceBundle resourceBundle = FxmlUtils.getResourceBundle();

    public static Optional<ButtonType> exitConfirmation(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(resourceBundle.getString("exit.title"));
        alert.setHeaderText(null);
        alert.setContentText(resourceBundle.getString("exit.text"));
        return alert.showAndWait();
    }

    public static void aboutApplication(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(resourceBundle.getString("about.title"));
        alert.setHeaderText(null);
        alert.setContentText(resourceBundle.getString("about.text"));
        alert.showAndWait();
    }
}
