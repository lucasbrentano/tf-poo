package viewcontroller;

import aplicacao.ACMEAirDrones;
import dados.DronePessoal;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class RelatorioDronePessoalController {

    @FXML
    private TableView<DronePessoal> tabelaDronePessoal;

    @FXML
    private TableColumn<DronePessoal, Integer> codigoColumn;

    @FXML
    private TableColumn<DronePessoal, Double> autonomiaColumn;

    @FXML
    private TableColumn<DronePessoal, Integer> passageirosColumn;

    @FXML
    private TableColumn<DronePessoal, String> custoFixoColumn;

    @FXML
    private Button sairButton;

    private ACMEAirDrones app;

    public void setACMEAirDrones(ACMEAirDrones app) {
        this.app = app;
        carregarDados();
    }

    @FXML
    private void initialize() {
        tabelaDronePessoal.setPlaceholder(new Label("Nenhum drone cadastrado."));
        codigoColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getCodigo()).asObject());
        autonomiaColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getAutonomia()).asObject());
        passageirosColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getQtdMaxPessoas()).asObject());
        custoFixoColumn.setCellValueFactory(cellData -> {
            String custoFormatado = String.format("%.2f", cellData.getValue().getCustoFixo());
            return new javafx.beans.property.SimpleStringProperty(custoFormatado);
        });
    }

    private void carregarDados() {
        List<DronePessoal> drones = app.getFrota().stream()
                .filter(DronePessoal.class::isInstance)
                .map(DronePessoal.class::cast)
                .collect(Collectors.toList());
        tabelaDronePessoal.getItems().clear();
        tabelaDronePessoal.getItems().addAll(drones);
    }

    @FXML
    private void handleSair() {
        Stage stage = (Stage) sairButton.getScene().getWindow();
        stage.close();
    }
}
