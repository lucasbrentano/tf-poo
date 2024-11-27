package viewcontroller;

import aplicacao.ACMEAirDrones;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import dados.Transporte;
import dados.Drone;
import javafx.stage.Stage;

import java.util.Collection;
import java.util.Optional;

public class RelatorioTransporteController {

    @FXML
    private ChoiceBox<Integer> codigoChoiceBox;
    @FXML
    private TextField nomeField;
    @FXML
    private TextField pesoField;
    @FXML
    private TextField descricaoField;
    @FXML
    private TextField tipoField;
    @FXML
    private TextField latitudeOField;
    @FXML
    private TextField latitudeDField;
    @FXML
    private TextField longitudeOField;
    @FXML
    private TextField longitudeDField;
    @FXML
    private TextField numeroPassageirosField;
    @FXML
    private TextField custoTotalField;
    @FXML
    private TextField temperaturaMinimaField;
    @FXML
    private TextField temperaturaMaximaField;
    @FXML
    private TextField perigosaField;
    @FXML
    private TextField climatizadoField;
    @FXML
    private TextField codigoDroneField;
    @FXML
    private TextField tipoDroneField;
    @FXML
    private TextField custoFixoField;
    @FXML
    private TextField autonomiaField;
    @FXML
    private TextField pesoMaximoField;
    @FXML
    private TextField limitePassageirosField;
    @FXML
    private TextField protegidoDroneField;
    @FXML
    private TextField climatizadoDroneField;
    @FXML
    private TextField statusField;
    @FXML
    private Button sairButton;

    private Collection<Transporte> transportes;

    private ACMEAirDrones app;

    public void initialize() {
        if (app != null) {
            this.transportes = app.getTransportes(); // Supondo que app tenha um método getTransportes()
            codigoChoiceBox.getItems().addAll(transportes.stream().map(Transporte::getNumero).toList());
        }
    }

    @FXML
    private void handleCarregar() {
        Integer selectedCodigo = codigoChoiceBox.getValue();
        if (selectedCodigo == null) {
            clearFields();
            return;
        }

        Optional<Transporte> transporteOpt = transportes.stream()
                .filter(t -> t.getNumero() == selectedCodigo)
                .findFirst();

        if (transporteOpt.isPresent()) {
            Transporte transporte = transporteOpt.get();
            nomeField.setText(transporte.getNomeCliente() != null ? transporte.getNomeCliente() : " - ");
            statusField.setText(transporte.getSituacao() != null ? transporte.getSituacao().toString() : " - ");
            pesoField.setText(transporte.getPeso() != 0 ? String.valueOf(transporte.getPeso()) : " - ");
            descricaoField.setText(transporte.getDescricao() != null ? transporte.getDescricao() : " - ");
            tipoField.setText(transporte instanceof dados.TransportePessoal ? "Pessoal" : transporte instanceof dados.TransporteCargaInanimada ? "Carga Inanimada" : transporte instanceof dados.TransporteCargaViva ? "Carga Viva" : " - ");
            latitudeOField.setText(transporte.getLatitudeOrigem() != 0 ? String.valueOf(transporte.getLatitudeOrigem()) : " - ");
            latitudeDField.setText(transporte.getLatitudeDestino() != 0 ? String.valueOf(transporte.getLatitudeDestino()) : " - ");
            longitudeOField.setText(transporte.getLongitudeOrigem() != 0 ? String.valueOf(transporte.getLongitudeOrigem()) : " - ");
            longitudeDField.setText(transporte.getLongitudeDestino() != 0 ? String.valueOf(transporte.getLongitudeDestino()) : " - ");

            if (transporte instanceof dados.TransportePessoal) {
                numeroPassageirosField.setText(String.valueOf(((dados.TransportePessoal) transporte).getQtdPessoas()));
            } else {
                numeroPassageirosField.setText(" - ");
            }
            custoTotalField.setText(transporte.calculaCusto() != 0 ? String.format("%.2f", transporte.calculaCusto()) : " - ");

            if (transporte instanceof dados.TransporteCargaViva) {
                temperaturaMinimaField.setText(String.valueOf(((dados.TransporteCargaViva) transporte).getTemperaturaMinima()));
            } else {
                temperaturaMinimaField.setText(" - ");
            }

            if (transporte instanceof dados.TransporteCargaViva) {
                temperaturaMaximaField.setText(String.valueOf(((dados.TransporteCargaViva) transporte).getTemperaturaMaxima()));
            } else {
                temperaturaMaximaField.setText(" - ");
            }
            if (transporte instanceof dados.TransporteCargaInanimada) {
                perigosaField.setText(((dados.TransporteCargaInanimada) transporte).isCargaPerigosa() ? "Sim" : "Não");
            } else {
                perigosaField.setText(" - ");
            }

            if (transporte instanceof dados.TransporteCargaViva) {
                climatizadoField.setText(((dados.TransporteCargaViva) transporte).isClimatizado() ? "Sim" : "Não");
            } else {
                climatizadoField.setText(" - ");
            }

            Drone drone = transporte.getDrone();
            if (drone != null) {
                codigoDroneField.setText(String.valueOf(drone.getCodigo()));
                tipoDroneField.setText(drone instanceof dados.DronePessoal ? "Pessoal" : drone instanceof dados.DroneCargaInanimada ? "Carga Inanimada" : drone instanceof dados.DroneCargaViva ? "Carga Viva" : " - ");
                custoFixoField.setText(drone.getCustoFixo() != 0 ? String.valueOf(drone.getCustoFixo()) : " - ");
                autonomiaField.setText(drone.getAutonomia() != 0 ? String.valueOf(drone.getAutonomia()) : " - ");
                if (drone instanceof dados.DroneCargaInanimada || drone instanceof dados.DroneCargaViva) {
                    pesoMaximoField.setText(String.valueOf(((dados.DroneCarga) drone).getPesoMaximo()));
                } else {
                    pesoMaximoField.setText(" - ");
                }                if (drone instanceof dados.DronePessoal) {
                    limitePassageirosField.setText(String.valueOf(((dados.DronePessoal) drone).getQtdMaxPessoas()));
                } else {
                    limitePassageirosField.setText(" - ");
                }

                if (drone instanceof dados.DroneCargaInanimada) {
                    protegidoDroneField.setText(((dados.DroneCargaInanimada) drone).isProtecao() ? "Sim" : "Não");
                } else {
                    protegidoDroneField.setText(" - ");
                }

                if (drone instanceof dados.DroneCargaViva) {
                    climatizadoDroneField.setText(((dados.DroneCargaViva) drone).isClimatizado() ? "Sim" : "Não");
                } else {
                    climatizadoDroneField.setText(" - ");
                }
            } else {
                clearDroneFields();
            }
        } else {
            clearFields();
        }
    }

    private void clearFields() {
        nomeField.setText(" - ");
        pesoField.setText(" - ");
        descricaoField.setText(" - ");
        tipoField.setText(" - ");
        latitudeOField.setText(" - ");
        latitudeDField.setText(" - ");
        longitudeOField.setText(" - ");
        longitudeDField.setText(" - ");
        numeroPassageirosField.setText(" - ");
        custoTotalField.setText(" - ");
        temperaturaMinimaField.setText(" - ");
        temperaturaMaximaField.setText(" - ");
        perigosaField.setText(" - ");
        climatizadoField.setText(" - ");
        clearDroneFields();
    }

    private void clearDroneFields() {
        codigoDroneField.setText(" - ");
        tipoDroneField.setText(" - ");
        custoFixoField.setText(" - ");
        autonomiaField.setText(" - ");
        pesoMaximoField.setText(" - ");
        limitePassageirosField.setText(" - ");
        protegidoDroneField.setText(" - ");
        climatizadoDroneField.setText(" - ");
    }

    public void setACMEAirDrones(ACMEAirDrones app) {
        this.app = app;
        initialize();
    }

    @FXML
    public void handleSair() {
        Stage stage = (Stage) sairButton.getScene().getWindow();
        stage.close();
    }
}
