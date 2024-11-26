package viewcontroller;

import aplicacao.ACMEAirDrones;
import dados.Estado;
import dados.TransporteCargaInanimada;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class RelatorioTransporteInanimadoController {

    @FXML
    private TableView<TransporteCargaInanimada> tabelaTransporteInanimado;

    @FXML
    private TableColumn<TransporteCargaInanimada, Integer> codigoTColumn;

    @FXML
    private TableColumn<TransporteCargaInanimada, String> nomeTColumn;

    @FXML
    private TableColumn<TransporteCargaInanimada, String> descricaoTColumn;

    @FXML
    private TableColumn<TransporteCargaInanimada, Double> pesoTColumn;

    @FXML
    private TableColumn<TransporteCargaInanimada, String> distanciaTColumn;

    @FXML
    private TableColumn<TransporteCargaInanimada, String> protegidoTColumn;

    @FXML
    private TableColumn<TransporteCargaInanimada, Estado> situacaoTColumn;

    @FXML
    private TableColumn<TransporteCargaInanimada, String> custoTColumn;

    @FXML
    private Button sairButton;

    private ACMEAirDrones app;

    public void setACMEAirDrones(ACMEAirDrones app) {
        this.app = app;
        carregarDados();
    }

    @FXML
    private void initialize() {
        tabelaTransporteInanimado.setPlaceholder(new javafx.scene.control.Label("Nenhum transporte cadastrado."));
        codigoTColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getNumero()).asObject());
        nomeTColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNomeCliente()));
        descricaoTColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDescricao()));
        pesoTColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getPeso()).asObject());
        distanciaTColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(
                String.format("%.2f", cellData.getValue().calculaDistancia())
        ));
        protegidoTColumn.setCellValueFactory(cellData -> {
            boolean protegido = cellData.getValue().isCargaPerigosa();
            String protegidoString = protegido ? "Sim" : "Não";
            return new javafx.beans.property.SimpleStringProperty(protegidoString);
        });
        situacaoTColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getSituacao()));
        custoTColumn.setCellValueFactory(cellData -> {
            TransporteCargaInanimada transporte = cellData.getValue();
            double custo = transporte.getDrone() != null ? transporte.calculaCusto() : 0.0;
            String custoFormatado = String.format("%.2f", custo);
            return new javafx.beans.property.SimpleStringProperty(custoFormatado);
        });
    }

    private void carregarDados() {
        List<TransporteCargaInanimada> transportes = app.getTransportes().stream()
                .filter(TransporteCargaInanimada.class::isInstance)
                .map(TransporteCargaInanimada.class::cast)
                .collect(Collectors.toList());
        tabelaTransporteInanimado.getItems().clear();
        tabelaTransporteInanimado.getItems().addAll(transportes);
    }

    @FXML
    private void handleSair() {
        Stage stage = (Stage) sairButton.getScene().getWindow();
        stage.close();
    }
}
