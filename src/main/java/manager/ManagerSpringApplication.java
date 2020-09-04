package manager;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainSpringApplication {
    public static void main(String[] args) {
        Application.launch(ManagerFXApplication.class, args);
    }
}
