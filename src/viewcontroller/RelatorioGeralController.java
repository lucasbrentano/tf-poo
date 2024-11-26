package viewcontroller;


import aplicacao.ACMEAirDrones;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class RelatorioGeralController {

    private ACMEAirDrones app;

    @FXML
    private Button sairButton;

    public RelatorioGeralController() {
        this.app = new ACMEAirDrones();
    }

    public void handleTransportePessoal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/telas/RelatorioTransportePessoal.fxml"));
            Parent root = loader.load();

            RelatorioTransportePessoalController controller = loader.getController();

            controller.setACMEAirDrones(app);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Relatório - Transporte Pessoal");
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

    public void handleTransporteInanimado() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/telas/RelatorioTransporteInanimado.fxml"));
            Parent root = loader.load();

            RelatorioTransporteInanimadoController controller = loader.getController();

            controller.setACMEAirDrones(app);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Relatório - Transporte Carga Inanimada");
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

    public void handleTransporteVivo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/telas/RelatorioTransporteVivo.fxml"));
            Parent root = loader.load();

            RelatorioTransporteVivoController controller = loader.getController();

            controller.setACMEAirDrones(app);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Relatório - Transporte Carga Viva");
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

    public void handleDronePessoal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/telas/RelatorioDronePessoal.fxml"));
            Parent root = loader.load();

            RelatorioDronePessoalController controller = loader.getController();

            controller.setACMEAirDrones(app);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Relatório - Drone Pessoal");
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

    public void handleDroneInanimado() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/telas/RelatorioDroneInanimado.fxml"));
            Parent root = loader.load();

            RelatorioDroneInanimadoController controller = loader.getController();

            controller.setACMEAirDrones(app);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Relatório - Drone Carga Inanimada");
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

    public void handleDroneVivo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/telas/RelatorioDroneVivo.fxml"));
            Parent root = loader.load();

            RelatorioDroneVivoController controller = loader.getController();

            controller.setACMEAirDrones(app);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Relatório - Drone Carga Viva");
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
    public void handleSair() {
        Stage stage = (Stage) sairButton.getScene().getWindow();
        stage.close();
    }
}
