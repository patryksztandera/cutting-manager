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
/*
        String check= "go";
        String exit ="exit";
        String example = "go";

        while (check.equals(example)){

            String delete = "d";
            String insert = "i";
            String selectAll = "s";

            Scanner scanner = new Scanner(System.in);
            System.out.println("to EXIT tap \"exit\", to Continue tap \"go\"");
            check = scanner.nextLine();
            if (!check.equals(exit)) {

                System.out.println("d - delete, i - insert new sheet, s - select:");
                String operation = scanner.nextLine();

                if (operation.equals(insert)) {

                    Location location = new Location();
                    location.selectAll();

                    Type type = new Type();
                    type.selectAll();

                    double length = scanner.nextDouble();
                    double width = scanner.nextDouble();
                    double thickness = scanner.nextDouble();
                    int idLocation = scanner.nextInt();
                    int idType = scanner.nextInt();

                    Sheet sheet = new Sheet(length, width, thickness, idLocation, idType);
                    sheet.insert();

                }
                else if (operation.equals(delete)) {

                    Sheet sheet = new Sheet();
                    sheet.selectAll();
                    double id = scanner.nextDouble();
                    sheet.delete(id);
                }
                else if (operation.equals(selectAll)){
                    Sheet sheet = new Sheet();
                    sheet.selectAll();
                }
            }
        }
*/
    }
}

