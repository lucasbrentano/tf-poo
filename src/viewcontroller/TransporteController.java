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
    private Label cargaPerigosaLabel;

    @FXML
    private RadioButton cargaPerigosaSim;

    @FXML
    private RadioButton cargaPerigosaNao;

    @FXML
    private TextField temperaturaMaximaField;

    @FXML
    private TextField temperaturaMinimaField;

    @FXML
    private Label climatizadoLabel;

    @FXML
    private RadioButton climatizadoSim;

    @FXML
    private RadioButton climatizadoNao;

    private ACMEAirDrones app;

    @FXML
    public void initialize() {
        cargaPerigosaLabel.setVisible(false);
        cargaPerigosaSim.setVisible(false);
        cargaPerigosaNao.setVisible(false);
        climatizadoLabel.setVisible(false);
        climatizadoSim.setVisible(false);
        climatizadoNao.setVisible(false);
        transporteChoiceBox.getItems().addAll(transporte);
        transporteChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                switch (newValue) {
                    case "Pessoal":
                        numeroPassageirosField.setDisable(false);

                        cargaPerigosaLabel.setVisible(false);
                        cargaPerigosaSim.setVisible(false);
                        cargaPerigosaNao.setVisible(false);
                        cargaPerigosaSim.setDisable(true);
                        cargaPerigosaNao.setDisable(true);

                        climatizadoLabel.setVisible(false);
                        climatizadoSim.setVisible(false);
                        climatizadoNao.setVisible(false);
                        climatizadoSim.setDisable(true);
                        climatizadoNao.setDisable(true);
                        temperaturaMaximaField.setDisable(true);
                        temperaturaMinimaField.setDisable(true);
                        break;

                    case "Carga Inanimada":
                        cargaPerigosaSim.setDisable(false);
                        cargaPerigosaNao.setDisable(false);
                        cargaPerigosaLabel.setVisible(true);
                        cargaPerigosaSim.setVisible(true);
                        cargaPerigosaNao.setVisible(true);

                        climatizadoLabel.setVisible(false);
                        climatizadoSim.setVisible(false);
                        climatizadoNao.setVisible(false);
                        climatizadoSim.setDisable(true);
                        climatizadoNao.setDisable(true);
                        numeroPassageirosField.setDisable(true);
                        temperaturaMaximaField.setDisable(true);
                        temperaturaMinimaField.setDisable(true);
                        break;
                    case "Carga Viva":
                        climatizadoLabel.setVisible(true);
                        climatizadoSim.setVisible(true);
                        climatizadoNao.setVisible(true);
                        climatizadoSim.setDisable(false);
                        climatizadoNao.setDisable(false);


                        numeroPassageirosField.setDisable(true);
                        cargaPerigosaLabel.setVisible(false);
                        cargaPerigosaSim.setVisible(false);
                        cargaPerigosaNao.setVisible(false);
                        cargaPerigosaSim.setDisable(true);
                        cargaPerigosaNao.setDisable(true);
                        break;
                    default:
                        numeroPassageirosField.setDisable(true);
                        cargaPerigosaLabel.setVisible(false);
                        cargaPerigosaSim.setDisable(true);
                        cargaPerigosaNao.setDisable(true);
                        climatizadoLabel.setVisible(false);
                        climatizadoSim.setDisable(true);
                        climatizadoNao.setDisable(true);
                        temperaturaMaximaField.setDisable(true);
                        temperaturaMinimaField.setDisable(true);
                        break;
                }
            }
        });

        climatizadoSim.selectedProperty().addListener((obs,wasSelected,isSelected) -> {
            if (isSelected) {
                temperaturaMaximaField.setDisable(false);
                temperaturaMinimaField.setDisable(false);
            }
        });
        climatizadoNao.selectedProperty().addListener((obs,wasSelected,isSelected) -> {
            if (isSelected) {
                temperaturaMaximaField.setDisable(true);
                temperaturaMinimaField.setDisable(true);
                temperaturaMaximaField.setText("0");
                temperaturaMinimaField.setText("0");
            }
        });

        numeroPassageirosField.setDisable(true);
        cargaPerigosaLabel.setVisible(false);
        cargaPerigosaSim.setDisable(true);
        cargaPerigosaNao.setDisable(true);
        climatizadoLabel.setVisible(false);
        climatizadoSim.setDisable(true);
        climatizadoNao.setDisable(true);
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
                    if (!climatizadoSim.isSelected() && !climatizadoNao.isSelected()) {
                        imprimeTextArea.setText("Erro: Selecione se a carga precisa ser climatizada");
                        return;
                    }
                    camposObrigatorios = new String [] {
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
                    if (climatizadoSim.isSelected()) {
                        camposObrigatorios = new String [] {
                                temperaturaMinimaField.getText(),
                                temperaturaMaximaField.getText(),
                        };
                        destacarCamposObrigatorios(temperaturaMaximaField, temperaturaMinimaField);
                    }
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

            resetarEstilos();

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
                    cadastraTransporte(transportePessoal);
                    break;
                case "Carga Inanimada":
                    boolean cargaPerigosa = cargaPerigosaSim.isSelected();
                    Transporte transporteCargaInanimada = new TransporteCargaInanimada(numero, nome, descricao, peso,
                            latitudeOrigem, latitudeDestino, longitudeOrigem, longitudeDestino, cargaPerigosa);
                    cadastraTransporte(transporteCargaInanimada);
                    break;
                case "Carga Viva":
                    boolean climatizado = climatizadoSim.isSelected();
                    double temperaturaMinima = climatizado ? Double.parseDouble(temperaturaMinimaField.getText()) : 0;
                    double temperaturaMaxima = climatizado ? Double.parseDouble(temperaturaMaximaField.getText()) : 0;

                    if (temperaturaMinima > temperaturaMaxima) {
                        imprimeTextArea.setText("Erro: Temperatura mínima maior que a máxima!");
                        temperaturaMinimaField.setStyle("-fx-border-color: red;");
                        temperaturaMaximaField.setStyle("-fx-border-color: red;");
                        return;
                    }

                    Transporte transporteCargaViva = new TransporteCargaViva(numero, nome, descricao, peso,
                            latitudeOrigem, latitudeDestino, longitudeOrigem, longitudeDestino, climatizado,
                            temperaturaMinima, temperaturaMaxima);
                    cadastraTransporte(transporteCargaViva);
                    break;
                default:
                    imprimeTextArea.setText("Erro: Selecione um tipo de transporte.");
                    return;
            }
        } catch (NumberFormatException e) {
            imprimeTextArea.setText("Erro: Entrada inválida de dados. Verifique os campos numéricos.");
            destacarCamposInvalidos();
        } catch (Exception e) {
            imprimeTextArea.setText("Erro ao cadastrar transporte: " + e.getMessage());
        }
    }

    private void cadastraTransporte(Transporte transporte) {
        if (app.cadastrarTransporte(transporte)) {
            imprimeTextArea.setText("Cadastrado com sucesso!\n" + transporte.geraTexto());
        } else {
            imprimeTextArea.setText("Erro: Código repetido!");
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

    private void destacarCamposInvalidos() {
        try {
            Integer.parseInt(codigoField.getText());
        } catch (NumberFormatException e) {
            codigoField.setStyle("-fx-border-color: red");
        }

        try {
            Double.parseDouble(pesoField.getText());
        } catch (NumberFormatException e) {
            pesoField.setStyle("-fx-border-color: red");
        }

        try {
            Double.parseDouble(latitudeOField.getText());
        } catch (NumberFormatException e) {
            latitudeOField.setStyle("-fx-border-color: red");
        }

        try {
            Double.parseDouble(latitudeDField.getText());
        } catch (NumberFormatException e) {
            latitudeDField.setStyle("-fx-border-color: red");
        }

        try {
            Double.parseDouble(longitudeOField.getText());
        } catch (NumberFormatException e) {
            longitudeOField.setStyle("-fx-border-color: red");
        }

        try {
            Double.parseDouble(longitudeDField.getText());
        } catch (NumberFormatException e) {
            longitudeDField.setStyle("-fx-border-color: red");
        }

        try {
            if (transporteChoiceBox.getValue() != null && "Pessoal".equals(transporteChoiceBox.getValue())) {
                Integer.parseInt(numeroPassageirosField.getText());
            }
        } catch (NumberFormatException e) {
            numeroPassageirosField.setStyle("-fx-border-color: red");
        }

        if (transporteChoiceBox.getValue() != null && "Carga Viva".equals(transporteChoiceBox.getValue()) && climatizadoSim.isSelected()) {
            try {
                Double.parseDouble(temperaturaMinimaField.getText());
            } catch (NumberFormatException e) {
                temperaturaMinimaField.setStyle("-fx-border-color: red");
            }

            try {
                Double.parseDouble(temperaturaMaximaField.getText());
            } catch (NumberFormatException e) {
                temperaturaMaximaField.setStyle("-fx-border-color: red");
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
