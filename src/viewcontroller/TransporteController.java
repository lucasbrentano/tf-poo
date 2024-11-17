package viewcontroller;

import aplicacao.ACMEAirDrones;
import dados.Transporte;
import dados.TransporteCargaInanimada;
import dados.TransporteCargaViva;
import dados.TransportePessoal;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TransporteController {

    @FXML
    private TextField codigoField;

    @FXML
    private TextField nomeField;

    @FXML
    private TextField pesoField;

    @FXML
    private TextField descricaoField;

    @FXML
    private TextField latitudeOField;

    @FXML
    private TextField latitudeDField;

    @FXML
    private TextField longitudeOField;

    @FXML
    private TextField longitudeDField;

    @FXML
    private TextArea imprimeTextArea;

    @FXML
    private ChoiceBox<String> transporteChoiceBox;

    private String[] transporte = {"Pessoal","Carga Inanimada","Carga Viva"};

    @FXML
    private TextField numeroPassageirosField;

    @FXML
    private RadioButton cargaPerigosaSim;

    @FXML
    private RadioButton cargaPerigosaNao;

    @FXML
    private TextField temperaturaMaximaField;

    @FXML
    private TextField temperaturaMinimaField;

    @FXML
    private Button cadastrarButton;

    private ACMEAirDrones app;

    @FXML
    public void initialize() {
        transporteChoiceBox.getItems().addAll(transporte);
        transporteChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                switch (newValue) {
                    case "Pessoal":
                        numeroPassageirosField.setDisable(false);

                        cargaPerigosaSim.setDisable(true);
                        cargaPerigosaNao.setDisable(true);
                        temperaturaMaximaField.setDisable(true);
                        temperaturaMinimaField.setDisable(true);
                        break;

                    case "Carga Inanimada":
                        cargaPerigosaSim.setDisable(false);
                        cargaPerigosaNao.setDisable(false);

                        numeroPassageirosField.setDisable(true);
                        temperaturaMaximaField.setDisable(true);
                        temperaturaMinimaField.setDisable(true);
                        break;
                    case "Carga Viva":
                        temperaturaMaximaField.setDisable(false);
                        temperaturaMinimaField.setDisable(false);

                        numeroPassageirosField.setDisable(true);
                        cargaPerigosaSim.setDisable(true);
                        cargaPerigosaNao.setDisable(true);
                        break;
                    default:
                        numeroPassageirosField.setDisable(true);
                        cargaPerigosaSim.setDisable(true);
                        cargaPerigosaNao.setDisable(true);
                        temperaturaMaximaField.setDisable(true);
                        temperaturaMinimaField.setDisable(true);
                        break;
                }
            }
        });
        numeroPassageirosField.setDisable(true);
        cargaPerigosaSim.setDisable(true);
        cargaPerigosaNao.setDisable(true);
        temperaturaMaximaField.setDisable(true);
        temperaturaMinimaField.setDisable(true);
    }

    public void setACMEAirDrones(ACMEAirDrones app) {
        this.app = app;
    }

    @FXML
    private void handleCadastro() {
        String tipoTransporte = transporteChoiceBox.getValue();
        if (tipoTransporte == null) {
            imprimeTextArea.setText("Erro: Selecione um tipo de transporte.");
            return;
        }

        try {
            String[] camposObrigatorios;

            switch (tipoTransporte) {
                case "Pessoal":
                    camposObrigatorios = new String[]{
                            codigoField.getText(),
                            nomeField.getText(),
                            descricaoField.getText(),
                            pesoField.getText(),
                            latitudeOField.getText(),
                            latitudeDField.getText(),
                            longitudeOField.getText(),
                            longitudeDField.getText(),
                            numeroPassageirosField.getText()
                    };
                    destacarCamposObrigatorios(codigoField, nomeField, descricaoField, pesoField, latitudeOField,
                            latitudeDField, longitudeOField, longitudeDField, numeroPassageirosField);
                    break;
                case "Carga Inanimada":
                    if (!cargaPerigosaSim.isSelected() && !cargaPerigosaNao.isSelected()) {
                        imprimeTextArea.setText("Erro: Selecione se a carga é perigosa ou não.");
                        return;
                    }
                    camposObrigatorios = new String[]{
                            codigoField.getText(),
                            nomeField.getText(),
                            descricaoField.getText(),
                            pesoField.getText(),
                            latitudeOField.getText(),
                            latitudeDField.getText(),
                            longitudeOField.getText(),
                            longitudeDField.getText(),
                    };
                    destacarCamposObrigatorios(codigoField, nomeField, descricaoField, pesoField, latitudeOField,
                            latitudeDField, longitudeOField, longitudeDField);
                    break;
                case "Carga Viva":
                    camposObrigatorios = new String[]{
                            codigoField.getText(),
                            nomeField.getText(),
                            descricaoField.getText(),
                            pesoField.getText(),
                            latitudeOField.getText(),
                            latitudeDField.getText(),
                            longitudeOField.getText(),
                            longitudeDField.getText(),
                            temperaturaMaximaField.getText(),
                            temperaturaMinimaField.getText()
                    };
                    destacarCamposObrigatorios(codigoField, nomeField, descricaoField, pesoField, latitudeOField,
                            latitudeDField, longitudeOField, longitudeDField, temperaturaMaximaField,
                            temperaturaMinimaField);
                    break;
                default:
                    imprimeTextArea.setText("Erro: Selecione um tipo de transporte.");
                    return;
            };

            for (String campo : camposObrigatorios) {
                if (campo == null || campo.trim().isEmpty()) {
                    imprimeTextArea.setText("Erro: Todos os campos obrigatórios devem ser preenchidos.");
                    return;
                }
            }

            int numero = Integer.parseInt(codigoField.getText());
            String nome = nomeField.getText();
            String descricao = descricaoField.getText();
            double peso = Double.parseDouble(pesoField.getText());
            double latitudeOrigem = Double.parseDouble(latitudeOField.getText());
            double latitudeDestino = Double.parseDouble(latitudeDField.getText());
            double longitudeOrigem = Double.parseDouble(longitudeOField.getText());
            double longitudeDestino = Double.parseDouble(longitudeDField.getText());

            switch (tipoTransporte) {
                case "Pessoal":
                    int numeroPassageiros = Integer.parseInt(numeroPassageirosField.getText());
                    Transporte transportePessoal = new TransportePessoal(numero, nome, descricao, peso, latitudeOrigem,
                            latitudeDestino, longitudeOrigem, longitudeDestino, numeroPassageiros);
                    if (app.cadastrarTransporte(transportePessoal)) {
                        imprimeTextArea.setText("Cadastrado com sucesso!\n" + transportePessoal.geraTexto());
                    } else {
                        imprimeTextArea.setText("Erro: Código repetido!");
                    }
                    break;
                case "Carga Inanimada":
                    boolean cargaPerigosa = cargaPerigosaSim.isSelected();
                    Transporte transporteCargaInanimada = new TransporteCargaInanimada(numero, nome, descricao, peso,
                            latitudeOrigem, latitudeDestino, longitudeOrigem, longitudeDestino, cargaPerigosa);
                    if (app.cadastrarTransporte(transporteCargaInanimada)) {
                        imprimeTextArea.setText("Cadastrado com sucesso!\n" + transporteCargaInanimada.geraTexto());
                    } else {
                        imprimeTextArea.setText("Erro: Código repetido!");
                    }
                    break;
                case "Carga Viva":
                    double temperaturaMaxima = Double.parseDouble(temperaturaMaximaField.getText());
                    double temperaturaMinima = Double.parseDouble(temperaturaMinimaField.getText());
                    Transporte tranporteCargaViva = new TransporteCargaViva(numero, nome, descricao, peso,
                            latitudeOrigem, latitudeDestino, longitudeOrigem, longitudeDestino, temperaturaMaxima,
                            temperaturaMinima);
                    if (app.cadastrarTransporte(tranporteCargaViva)) {
                        imprimeTextArea.setText("Cadastrado com sucesso!\n" + tranporteCargaViva.geraTexto());
                    } else {
                        imprimeTextArea.setText("Erro: Código repetido!");
                    }
                    break;
                default:
                    imprimeTextArea.setText("Erro: Selecione um tipo de transporte.");
                    return;
            }
        } catch (NumberFormatException e) {
            imprimeTextArea.setText("Erro: Entrada inválida de dados. Verifique os campos numéricos.");
        } catch (Exception e) {
            imprimeTextArea.setText("Erro ao cadastrar transporte: " + e.getMessage());
        }
    }

    private void destacarCamposObrigatorios(TextField... campos) {
        for (TextField campo : campos) {
            if (campo.getText() == null || campo.getText().trim().isEmpty()) {
                campo.setStyle("-fx-border-color: red");
            } else {
                campo.setStyle(null);
            }
        }
    }

    @FXML
    private void handleCarregar() {
        imprimeTextArea.clear();
        if (app == null || app.getTransportes().isEmpty()) {
            imprimeTextArea.setText("Nenhum transporte cadastrado!");
            return;
        }

        StringBuilder lista = new StringBuilder();
        for (Transporte transporte : app.getTransportes()) {
            lista.append(transporte.geraTexto()).append("\n");
        }

        imprimeTextArea.setText(lista.toString());
    }

    @FXML
    private void handleLimpar() {
        codigoField.clear();
        nomeField.clear();
        descricaoField.clear();
        pesoField.clear();
        latitudeOField.clear();
        latitudeDField.clear();
        longitudeOField.clear();
        longitudeDField.clear();
        numeroPassageirosField.clear();
        temperaturaMaximaField.clear();
        temperaturaMinimaField.clear();
        imprimeTextArea.clear();
        cargaPerigosaSim.setSelected(false);
        cargaPerigosaNao.setSelected(false);
        resetarEstilos();
    }

    private void resetarEstilos() {
        codigoField.setStyle(null);
        nomeField.setStyle(null);
        pesoField.setStyle(null);
        descricaoField.setStyle(null);
        latitudeOField.setStyle(null);
        latitudeDField.setStyle(null);
        longitudeOField.setStyle(null);
        longitudeDField.setStyle(null);
        numeroPassageirosField.setStyle(null);
        temperaturaMaximaField.setStyle(null);
        temperaturaMinimaField.setStyle(null);
    }

    @FXML
    private void handleSair() {
        Platform.exit();
    }
}
