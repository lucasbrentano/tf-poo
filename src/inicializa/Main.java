package inicializa;

import aplicacao.ACMEAirDrones;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import viewcontroller.MenuController;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ACMEAirDrones app = new ACMEAirDrones();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/telas/Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        MenuController controller = fxmlLoader.getController();
        controller.setACMEAirDrones(app);
        stage.setTitle("ACMEAirDrones");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}