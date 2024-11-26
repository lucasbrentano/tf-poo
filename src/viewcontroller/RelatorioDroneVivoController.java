package viewcontroller;

import aplicacao.ACMEAirDrones;
import dados.DroneCargaViva;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class RelatorioDroneVivoController {

    @FXML
    private TableView<DroneCargaViva> tabelaDroneVivo;

    @FXML
    private TableColumn<DroneCargaViva, Integer> codigoColumn;

    @FXML
    private TableColumn<DroneCargaViva, Double> autonomiaColumn;

    @FXML
    private TableColumn<DroneCargaViva, Double> pesoColumn;

    @FXML
    private TableColumn<DroneCargaViva, String> climatizadoColumn;

    @FXML
    private TableColumn<DroneCargaViva, String> custoFixoColumn;

    @FXML
    private Button sairButton;

    private ACMEAirDrones app;

    public void setACMEAirDrones(ACMEAirDrones app) {
        this.app = app;
        carregarDados();
    }

    @FXML
    private void initialize() {
        tabelaDroneVivo.setPlaceholder(new Label("Nenhum drone cadastrado."));
        codigoColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getCodigo()).asObject());
        autonomiaColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getAutonomia()).asObject());
        pesoColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getPesoMaximo()).asObject());
        climatizadoColumn.setCellValueFactory(cellData -> {
            boolean climatizado = cellData.getValue().isClimatizado();
            String climatizadoString = climatizado ? "Sim" : "Não";
            return new javafx.beans.property.SimpleStringProperty(climatizadoString);
        });
        custoFixoColumn.setCellValueFactory(cellData -> {
            String custoFormatado = String.format("%.2f", cellData.getValue().getCustoFixo());
            return new javafx.beans.property.SimpleStringProperty(custoFormatado);
        });
    }

    private void carregarDados() {
        List<DroneCargaViva> drones = app.getFrota().stream()
                .filter(DroneCargaViva.class::isInstance)
                .map(DroneCargaViva.class::cast)
                .collect(Collectors.toList());
        tabelaDroneVivo.getItems().clear();
        tabelaDroneVivo.getItems().addAll(drones);
    }

    @FXML
    private void handleSair() {
        Stage stage = (Stage) sairButton.getScene().getWindow();
        stage.close();
    }
}
