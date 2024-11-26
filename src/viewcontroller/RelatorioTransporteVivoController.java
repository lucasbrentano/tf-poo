package viewcontroller;

import aplicacao.ACMEAirDrones;
import dados.Estado;
import dados.TransporteCargaViva;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class RelatorioTransporteVivoController {

    @FXML
    private TableView<TransporteCargaViva> tabelaTransporteVivo;

    @FXML
    private TableColumn<TransporteCargaViva, Integer> codigoColumn;

    @FXML
    private TableColumn<TransporteCargaViva, String> nomeColumn;

    @FXML
    private TableColumn<TransporteCargaViva, String> descricaoColumn;

    @FXML
    private TableColumn<TransporteCargaViva, Double> pesoColumn;

    @FXML
    private TableColumn<TransporteCargaViva, String> distanciaColumn;

    @FXML
    private TableColumn<TransporteCargaViva, String> climatizadoColumn;

    @FXML
    private TableColumn<TransporteCargaViva, Double> tempMinColumn;

    @FXML
    private TableColumn<TransporteCargaViva, Double> tempMaxColumn;

    @FXML
    private TableColumn<TransporteCargaViva, Estado> situacaoColumn;

    @FXML
    private TableColumn<TransporteCargaViva, String> custoColumn;

    @FXML
    private Button buttonSair;

    private ACMEAirDrones app;

    public void setACMEAirDrones(ACMEAirDrones app) {
        this.app = app;
        carregarDados();
    }

    @FXML
    private void initialize() {
        tabelaTransporteVivo.setPlaceholder(new Label("Nenhum transporte cadastrado."));
        codigoColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getNumero()).asObject());
        nomeColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNomeCliente()));
        descricaoColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDescricao()));
        pesoColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getPeso()).asObject());
        distanciaColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(
                String.format("%.2f", cellData.getValue().calculaDistancia())
        ));
        climatizadoColumn.setCellValueFactory(cellData -> {
            boolean climatizado = cellData.getValue().isClimatizado();
            String climatizadoString = climatizado ? "Sim" : "Não";
            return new javafx.beans.property.SimpleStringProperty(climatizadoString);
        });
        tempMinColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getTemperaturaMinima()).asObject());
        tempMaxColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getTemperaturaMaxima()).asObject());
        situacaoColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getSituacao()));
        custoColumn.setCellValueFactory(cellData -> {
            TransporteCargaViva transporte = cellData.getValue();
            double custo = transporte.getDrone() != null ? transporte.calculaCusto() : 0.0;
            String custoFormatado = String.format("%.2f", custo);
            return new javafx.beans.property.SimpleStringProperty(custoFormatado);
        });
    }

    private void carregarDados() {
        List<TransporteCargaViva> transportes = app.getTransportes().stream()
                .filter(TransporteCargaViva.class::isInstance)
                .map(TransporteCargaViva.class::cast)
                .collect(Collectors.toList());
        tabelaTransporteVivo.getItems().clear();
        tabelaTransporteVivo.getItems().addAll(transportes);
    }

    @FXML
    private void handleSair() {
        Stage stage = (Stage) buttonSair.getScene().getWindow();
        stage.close();
    }
}
