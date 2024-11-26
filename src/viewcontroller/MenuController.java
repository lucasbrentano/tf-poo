package viewcontroller;

import aplicacao.ACMEAirDrones;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    private ACMEAirDrones app;

    public MenuController() {
        this.app = new ACMEAirDrones();
    }

    public void handleCadastrarTransporte() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/telas/Transporte.fxml"));
            Parent root = loader.load();

            TransporteController transporteController = loader.getController();

            transporteController.setACMEAirDrones(app);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Cadastro de Transporte");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao Carregar Tela de Cadastro");
            alert.setHeaderText(null);
            alert.setContentText("Ocorreu um erro ao tentar abrir a tela de cadastro de transporte: " + e.getMessage());
            alert.showAndWait();
        }
    }

    public void handleCadastrarDrone() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/telas/Drone.fxml"));
            Parent root = loader.load();

            DroneController droneController = loader.getController();

            droneController.setACMEAirDrones(app);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Cadastro de Drones");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao Carregar Tela de Cadastro");
            alert.setHeaderText(null);
            alert.setContentText("Ocorreu um erro ao tentar abrir a tela de cadastro de transporte: " + e.getMessage());
            alert.showAndWait();
        }
    }

    public void handleProcessarTransporte() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/telas/ProcessarTransporte.fxml"));
            Parent root = loader.load();

            ProcessarTransporteController processarTransporteController = loader.getController();

            processarTransporteController.setACMEAirDrones(app);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Processar Transporte");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao Carregar Tela de Cadastro");
            alert.setHeaderText(null);
            alert.setContentText("Ocorreu um erro ao tentar abrir a tela de cadastro de transporte: " + e.getMessage());
            alert.showAndWait();
        }
    }

    public void handleRelatorioGeral() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/telas/RelatorioGeral.fxml"));
            Parent root = loader.load();

            RelatorioGeralController relatorioGeralController = loader.getController();

            relatorioGeralController.setACMEAirDrones(app);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Relatório Geral");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao Carregar Tela de Cadastro");
            alert.setHeaderText(null);
            alert.setContentText("Ocorreu um erro ao tentar abrir a tela de cadastro de transporte: " + e.getMessage());
            alert.showAndWait();
        }
    }

    public void setACMEAirDrones(ACMEAirDrones app) {
        this.app = app;
    }

    @FXML
    private void handleSair() {
        Platform.exit();
    }
}
