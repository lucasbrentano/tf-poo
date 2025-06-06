package viewcontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import dados.TransportePessoal;
import aplicacao.ACMEAirDrones;
import dados.Estado;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class RelatorioTransportePessoalController {

    @FXML
    private TableView<TransportePessoal> tabelaTransportePessoal;

    @FXML
    private TableColumn<TransportePessoal, Integer> codigoTColumn;

    @FXML
    private TableColumn<TransportePessoal, String> nomeTColumn;

    @FXML
    private TableColumn<TransportePessoal, String> descricaoTColumn;

    @FXML
    private TableColumn<TransportePessoal, Double> pesoTColumn;

    @FXML
    private TableColumn<TransportePessoal, String> distanciaTColumn;

    @FXML
    private TableColumn<TransportePessoal, Integer> passageirosTColumn;

    @FXML
    private TableColumn<TransportePessoal, Estado> situacaoTColumn;

    @FXML
    private TableColumn<TransportePessoal, String> custoTColumn;

    @FXML
    private Button sairButton;

    private ACMEAirDrones app;

    public void setACMEAirDrones(ACMEAirDrones app) {
        this.app = app;
        carregarDados();
    }

    @FXML
    private void initialize() {
        tabelaTransportePessoal.setPlaceholder(new javafx.scene.control.Label("Nenhum transporte cadastrado."));
        codigoTColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getNumero()).asObject());
        nomeTColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNomeCliente()));
        descricaoTColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDescricao()));
        pesoTColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getPeso()).asObject());
        distanciaTColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(
                String.format("%.2f", cellData.getValue().calculaDistancia())
        ));
        passageirosTColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getQtdPessoas()).asObject());
        situacaoTColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getSituacao()));
        custoTColumn.setCellValueFactory(cellData -> {
            TransportePessoal transporte = cellData.getValue();
            double custo = transporte.getDrone() != null ? transporte.calculaCusto() : 0.0;
            String custoFormatado = String.format("%.2f", custo);
            return new javafx.beans.property.SimpleStringProperty(custoFormatado);
        });
    }

    private void carregarDados() {
        List<TransportePessoal> transportes = app.getTransportes().stream()
                .filter(TransportePessoal.class::isInstance)
                .map(TransportePessoal.class::cast)
                .collect(Collectors.toList());
        tabelaTransportePessoal.getItems().clear();
        tabelaTransportePessoal.getItems().addAll(transportes);
    }

    @FXML
    private void handleSair() {
        Stage stage = (Stage) sairButton.getScene().getWindow();
        stage.close();
    }
}

