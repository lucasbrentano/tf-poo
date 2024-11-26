package viewcontroller;


import aplicacao.ACMEAirDrones;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class RelatorioGeralController {

    private ACMEAirDrones app;

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
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/telas/RelatorioTransporteInanimado.fxml"));
//            Parent root = loader.load();
//
//            RelatorioTransporteInanimado relatorioTransporteInanimado = loader.getController();
//
//            relatorioTransporteInanimado.setACMEAirDrones(app);
//
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root));
//            stage.setTitle("Relatório - Transporte Pessoal");
//            stage.initModality(Modality.APPLICATION_MODAL);
//
//            stage.showAndWait();
//        } catch (IOException e) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Erro ao Carregar Tela de Cadastro");
//            alert.setHeaderText(null);
//            alert.setContentText("Ocorreu um erro ao tentar abrir a tela de cadastro de transporte: " + e.getMessage());
//            alert.showAndWait();
//        }
    }

    public void handleTransporteVivo() {

    }

    public void handleDronePessoal() {

    }

    public void handleDroneInanimado() {

    }

    public void handleDroneVivo() {

    }

    public void setACMEAirDrones(ACMEAirDrones app) {
        this.app = app;
    }
}
