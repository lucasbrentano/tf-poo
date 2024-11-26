package viewcontroller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import dados.TransportePessoal;
import aplicacao.ACMEAirDrones;
import dados.Estado;
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
    private TableColumn<TransportePessoal, Double> distanciaTColumn;

    @FXML
    private TableColumn<TransportePessoal, Integer> passageirosTColumn;

    @FXML
    private TableColumn<TransportePessoal, Estado> situacaoTColumn;

    @FXML
    private TableColumn<TransportePessoal, Double> custoTColumn;

    private ACMEAirDrones app;

    public void setACMEAirDrones(ACMEAirDrones app) {
        this.app = app;
        carregarDados();
    }

    @FXML
    private void initialize() {
        codigoTColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getNumero()).asObject());
        nomeTColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNomeCliente()));
        descricaoTColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDescricao()));
        pesoTColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getPeso()).asObject());
        distanciaTColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().calculaDistancia()).asObject());
        passageirosTColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getQtdPessoas()).asObject());
        situacaoTColumn.setCellValueFactory(cellData -> {
            Estado situacao = cellData.getValue().getSituacao();
            return new javafx.beans.property.SimpleObjectProperty<>(situacao);
        });
        custoTColumn.setCellValueFactory(cellData -> {
            TransportePessoal transporte = cellData.getValue();
            double custo = transporte.getDrone() != null ? transporte.calculaCusto() : 0.0;
            return new javafx.beans.property.SimpleDoubleProperty(custo).asObject();
        });
    }

    private void carregarDados() {
        System.out.println("Carregar dados chamado");
        if (app == null) {
            System.out.println("App não está configurado");
            return;
        }
        List<TransportePessoal> transportes = app.getTransportes().stream()
                .filter(TransportePessoal.class::isInstance)
                .map(TransportePessoal.class::cast)
                .collect(Collectors.toList());
        System.out.println("Quantidade de Transportes Pessoais encontrados: " + transportes.size());
        if (app == null) {
            return;
        }
        tabelaTransportePessoal.getItems().clear();
        transportes = app.getTransportes().stream()
                .filter(TransportePessoal.class::isInstance)
                .map(TransportePessoal.class::cast)
                .collect(Collectors.toList());
        tabelaTransportePessoal.getItems().addAll(transportes);
    }
}
