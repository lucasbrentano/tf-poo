package viewcontroller;

import aplicacao.ACMEAirDrones;
import dados.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.Queue;

public class ProcessarTransporteController {
    @FXML
    private TableView<Transporte> transporteTView;

    @FXML
    private TableColumn<Transporte, Integer> posicaoColumn;
    @FXML
    private TableColumn<Transporte, Integer> codigoColumn;
    @FXML
    private TableColumn<Transporte, String> clienteColumn;
    @FXML
    private TableColumn<Transporte, String> descricaoColumn;
    @FXML
    private TableColumn<Transporte, String> tipoColumn;

    @FXML
    private Button processarButton, sairButton;

    private ACMEAirDrones app;

    public void initialize() {
        transporteTView.setPlaceholder(new Label("Nenhum transporte na fila."));
        posicaoColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(transporteTView.getItems().indexOf(cellData.getValue()) + 1).asObject());
        codigoColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getNumero()).asObject());
        clienteColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNomeCliente()));
        descricaoColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDescricao()));
        tipoColumn.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof TransporteCargaInanimada) {
                return new javafx.beans.property.SimpleStringProperty("Carga Inanimada");
            } else if (cellData.getValue() instanceof TransporteCargaViva) {
                return new javafx.beans.property.SimpleStringProperty("Carga Viva");
            } else if (cellData.getValue() instanceof TransportePessoal) {
                return new javafx.beans.property.SimpleStringProperty("Pessoal");
            } else {
                return new javafx.beans.property.SimpleStringProperty("Inválido");
            }
        });
    }

    @FXML
    private void handleProcessar() {
        if (app != null && !app.getFilaTransporte().isEmpty()) {
            Queue<Transporte> filaTransporte = app.getFilaTransporte();
            boolean algumTransporteProcessado = false;

            for (Transporte transporte : filaTransporte) {
                Optional<Drone> droneDisponivel = app.getFrota().stream()
                        .filter(drone -> isDroneCompativelComTransporte(drone, transporte))
                        .findFirst();

                if (droneDisponivel.isPresent()) {
                    Drone drone = droneDisponivel.get();
                    filaTransporte.remove(transporte);
                    transporte.setDrone(drone);
                    transporte.setSituacao(Estado.ALOCADO);
                    algumTransporteProcessado = true;

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Transporte Processado");
                    alert.setHeaderText(null);
                    alert.setContentText("Transporte " + transporte.getNumero() + " foi designado ao drone " + drone.getCodigo() + ".");
                    alert.showAndWait();
                }
            }

            if (!algumTransporteProcessado) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Nenhum Drone Disponível");
                alert.setHeaderText(null);
                alert.setContentText("Nenhum drone disponível para realizar o transporte no momento.");
                alert.showAndWait();
            }

            carregarTransportesNaFila();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fila Vazia");
            alert.setHeaderText(null);
            alert.setContentText("Não há transportes na fila para processar.");
            alert.showAndWait();
        }
    }

    private boolean isDroneCompativelComTransporte(Drone drone, Transporte transporte) {
        if (drone instanceof DronePessoal && transporte instanceof TransportePessoal) {
            return ((TransportePessoal) transporte).getQtdPessoas() <= ((DronePessoal) drone).getQtdMaxPessoas();
        } else if (drone instanceof DroneCargaInanimada && transporte instanceof TransporteCargaInanimada) {
            return transporte.getPeso() <= ((DroneCargaInanimada) drone).getPesoMaximo() && ((TransporteCargaInanimada) transporte).isCargaPerigosa() == ((DroneCargaInanimada) drone).isProtecao();
        } else if (drone instanceof DroneCargaViva && transporte instanceof TransporteCargaViva) {
            return transporte.getPeso() <= ((DroneCargaViva) drone).getPesoMaximo() && ((TransporteCargaViva) transporte).isClimatizado() == ((DroneCargaViva) drone).isClimatizado();
        } else {
            return false;
        }
    }


    public void setACMEAirDrones(ACMEAirDrones app) {
        this.app = app;
        carregarTransportesNaFila();
    }

    private void carregarTransportesNaFila() {
        if (app != null) {
            ObservableList<Transporte> transporteList = FXCollections.observableArrayList(app.getFilaTransporte());
            transporteTView.setItems(transporteList);
        }
    }

    public void handleSair() {
        Stage stage = (Stage) sairButton.getScene().getWindow();
        stage.close();
    }
}
