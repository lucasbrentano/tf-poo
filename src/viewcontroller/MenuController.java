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
            alert.setContentText("Ocorreu um erro ao tentar abrir a tela de cadastro de drones: " + e.getMessage());
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
            alert.setTitle("Erro ao Carregar Tela de Processar");
            alert.setHeaderText(null);
            alert.setContentText("Ocorreu um erro ao tentar abrir a tela de processar transporte: " + e.getMessage());
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
            alert.setTitle("Erro ao Carregar Tela de Relatório Geral");
            alert.setHeaderText(null);
            alert.setContentText("Ocorreu um erro ao tentar abrir a tela de relatório geral: " + e.getMessage());
            alert.showAndWait();
        }
    }

    public void handleRelatorioTransporte() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/telas/RelatorioTransporte.fxml"));
            Parent root = loader.load();

            RelatorioTransporteController relatorioTransporteController = loader.getController();
            relatorioTransporteController.setACMEAirDrones(app);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Relatorio Transporte");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao Carregar Tela de Relatório Transporte");
            alert.setHeaderText(null);
            alert.setContentText("Ocorreu um erro ao tentar abrir a tela de relatório de transporte: " + e.getMessage());
            alert.showAndWait();
        }
    }

    public void handleAtualizaTransporte() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/telas/AtualizaTransporte.fxml"));
            Parent root = loader.load();

            AtualizaTransporteController atualizaTransporteController = loader.getController();
            atualizaTransporteController.setACMEAirDrones(app);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Atualiza Transporte");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao Carregar Tela de Atualizar Transporte");
            alert.setHeaderText(null);
            alert.setContentText("Ocorreu um erro ao tentar abrir a tela de atualizar transporte: " + e.getMessage());
            alert.showAndWait();
        }
    }

    public void handleSimulacao() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/telas/Simulacao.fxml"));
            Parent root = loader.load();

            SimulacaoController simulacaoController = loader.getController();
            simulacaoController.setACMEAirDrones(app);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Simulação");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao Carregar Tela de Simulação");
            alert.setHeaderText(null);
            alert.setContentText("Ocorreu um erro ao tentar abrir a tela de simulação: " + e.getMessage());
            alert.showAndWait();
        }
    }

    public void handleSalvar () {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/telas/Salvar.fxml"));
            Parent root = loader.load();

            SalvarController salvarController = loader.getController();
            salvarController.setACMEAirDrones(app);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Salvar");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao Carregar Tela de Salvar");
            alert.setHeaderText(null);
            alert.setContentText("Ocorreu um erro ao tentar abrir a tela de salvar arquivos: " + e.getMessage());
            alert.showAndWait();
        }
    }

    public void handleCarregar () {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/telas/Carregar.fxml"));
            Parent root = loader.load();

            CarregarController carregarController = loader.getController();
            carregarController.setACMEAirDrones(app);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Carregar");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao Carregar Tela de Carregar");
            alert.setHeaderText(null);
            alert.setContentText("Ocorreu um erro ao tentar abrir a tela de carregar arquivos" + e.getMessage());
            alert.showAndWait();
        }
    }

    public void setACMEAirDrones(ACMEAirDrones app) {
        this.app = app;
    }

    @FXML
    public void handleSair() {
        Platform.exit();
    }
}
