package viewcontroller;

import aplicacao.ACMEAirDrones;
import dados.Transporte;
import dados.Estado;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Collection;
import java.util.Optional;

public class AtualizaTransporteController {

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
    private TextField statusField;
    @FXML
    private ChoiceBox<Estado> novoStatusBox;
    @FXML
    private Button sairButton;

    private Collection<Transporte> transportes;

    private ACMEAirDrones app;

    public void initialize() {
        if (app != null) {
            this.transportes = app.getTransportes();
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
                temperaturaMaximaField.setText(String.valueOf(((dados.TransporteCargaViva) transporte).getTemperaturaMaxima()));
                climatizadoField.setText(((dados.TransporteCargaViva) transporte).isClimatizado() ? "Sim" : "Não");
            } else {
                temperaturaMinimaField.setText(" - ");
                temperaturaMaximaField.setText(" - ");
                climatizadoField.setText(" - ");
            }

            if (transporte instanceof dados.TransporteCargaInanimada) {
                perigosaField.setText(((dados.TransporteCargaInanimada) transporte).isCargaPerigosa() ? "Sim" : "Não");
            } else {
                perigosaField.setText(" - ");
            }

            statusField.setText(transporte.getSituacao() != null ? transporte.getSituacao().toString() : " - ");

            novoStatusBox.setDisable(false);
            configurarNovoStatusBox(transporte.getSituacao());
        } else {
            clearFields();
        }
    }

    private void configurarNovoStatusBox(Estado estadoAtual) {
        novoStatusBox.getItems().clear();
        novoStatusBox.setDisable(false); // Certifique-se de que a ChoiceBox está habilitada

        if (estadoAtual == Estado.PENDENTE) {
            novoStatusBox.getItems().add(Estado.CANCELADO);
        } else if (estadoAtual == Estado.ALOCADO) {
            novoStatusBox.getItems().addAll(Estado.CANCELADO, Estado.TERMINADO);
        } else {
            novoStatusBox.setDisable(true); // Se o estado for TERMINADO ou CANCELADO, desabilite a caixa
        }
    }

    @FXML
    private void handleAlterar() {
        Integer selectedCodigo = codigoChoiceBox.getValue();
        if (selectedCodigo == null) {
            mostrarAlerta("Erro", "Nenhum transporte selecionado", "Por favor, selecione um transporte para alterar o status.");
            return;
        }

        Optional<Transporte> transporteOpt = transportes.stream()
                .filter(t -> t.getNumero() == selectedCodigo)
                .findFirst();

        if (transporteOpt.isPresent()) {
            Transporte transporte = transporteOpt.get();
            Estado novoEstado = novoStatusBox.getValue();

            if (novoEstado == null) {
                mostrarAlerta("Erro", "Novo status não selecionado", "Por favor, selecione um novo status para o transporte.");
                return;
            }

            if (transporte.getSituacao() == Estado.TERMINADO || transporte.getSituacao() == Estado.CANCELADO) {
                mostrarAlerta("Erro", "Alteração não permitida", "Não é possível alterar o status de um transporte que já está TERMINADO ou CANCELADO.");
            } else {
                transporte.setSituacao(novoEstado);
                if (novoEstado == Estado.CANCELADO || novoEstado == Estado.TERMINADO) {
                    app.getFilaTransporte().remove(transporte);
                }
                handleCarregar();
                mostrarAlerta("Sucesso", "Status alterado", "O status do transporte foi alterado com sucesso.");
            }
        } else {
            mostrarAlerta("Erro", "Transporte não encontrado", "O transporte selecionado não foi encontrado.");
        }
    }

    private void mostrarAlerta(String titulo, String cabecalho, String conteudo) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(conteudo);
        alert.showAndWait();
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
        statusField.setText(" - ");
        novoStatusBox.getItems().clear();
        novoStatusBox.setDisable(false);
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
