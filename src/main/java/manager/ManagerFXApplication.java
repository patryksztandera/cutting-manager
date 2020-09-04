package manager;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import manager.utils.FxmlUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;

@SpringBootApplication
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

    public static void main(String[] args) {

        launch(args);
        SpringApplication.run(Main.class, args);
    }
}

