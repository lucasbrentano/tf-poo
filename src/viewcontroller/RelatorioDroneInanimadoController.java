package viewcontroller;

import aplicacao.ACMEAirDrones;
import dados.DroneCargaInanimada;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class RelatorioDroneInanimadoController {

    @FXML
    private TableView<DroneCargaInanimada> tabelaDroneInanimado;

    @FXML
    private TableColumn<DroneCargaInanimada, Integer> codigoColumn;

    @FXML
    private TableColumn<DroneCargaInanimada, Double> autonomiaColumn;

    @FXML
    private TableColumn<DroneCargaInanimada, Double> pesoMaximoColumn;

    @FXML
    private TableColumn<DroneCargaInanimada, String> protecaoColumn;

    @FXML
    private TableColumn<DroneCargaInanimada, String> custoFixoColumn;

    @FXML
    private Button sairButton;

    private ACMEAirDrones app;

    public void setACMEAirDrones(ACMEAirDrones app) {
        this.app = app;
        carregarDados();
    }

    @FXML
    private void initialize() {
        tabelaDroneInanimado.setPlaceholder(new Label("Nenhum drone cadastrado."));
        codigoColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getCodigo()).asObject());
        autonomiaColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getAutonomia()).asObject());
        pesoMaximoColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getPesoMaximo()).asObject());
        protecaoColumn.setCellValueFactory(cellData -> {
            boolean protegido = cellData.getValue().isProtecao();
            String protecaoString = protegido ? "Sim" : "Não";
            return new javafx.beans.property.SimpleStringProperty(protecaoString);
        });
        custoFixoColumn.setCellValueFactory(cellData -> {
            String custoFormatado = String.format("%.2f", cellData.getValue().getCustoFixo());
            return new javafx.beans.property.SimpleStringProperty(custoFormatado);
        });
    }

    private void carregarDados() {
        List<DroneCargaInanimada> drones = app.getFrota().stream()
                .filter(DroneCargaInanimada.class::isInstance)
                .map(DroneCargaInanimada.class::cast)
                .collect(Collectors.toList());
        tabelaDroneInanimado.getItems().clear();
        tabelaDroneInanimado.getItems().addAll(drones);
    }

    @FXML
    private void handleSair() {
        Stage stage = (Stage) sairButton.getScene().getWindow();
        stage.close();
    }
}
