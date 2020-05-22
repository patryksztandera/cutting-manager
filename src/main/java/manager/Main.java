package manager;

import javafx.scene.layout.Pane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import manager.utils.FxmlUtils;

import java.sql.*;
import java.util.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        Locale.setDefault(new Locale("en"));
        Pane pane = FxmlUtils.fxmlLoad("/fxml/Main.fxml");
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle(FxmlUtils.getResourceBundle().getString("application.name"));
        primaryStage.show();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        launch(args);
    }
}

