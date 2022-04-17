import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ElevatorSimApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ElevatorSimApplication.class.getResource("/main/main-view.fxml"));
        primaryStage.setScene(new Scene(fxmlLoader.load(), 400, 300));
        primaryStage.show();
    }
}
