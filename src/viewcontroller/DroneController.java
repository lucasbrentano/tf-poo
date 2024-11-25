package viewcontroller;

import aplicacao.ACMEAirDrones;
import dados.Drone;
import dados.DroneCargaInanimada;
import dados.DroneCargaViva;
import dados.DronePessoal;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class DroneController {

    @FXML
    private Label tituloLabel, custoFixoLabel, autonomiaLabel, pesoMaximoLabel, codigoLabel;

    @FXML
    private TextField custoFixoTField, autonomiaTField, pesoMaximoTField, codigoTField, passageirosTField, imprimeTField;

    @FXML
    private RadioButton cargaVivaRButton, cargaInanimadaRButton;

    @FXML
    private CheckBox climatizadoCB, protecaoCB;

    @FXML
    private Button cadastrarButton, limparButton, carregarButton, sairButton;

    @FXML
    private ChoiceBox<String> tipoDroneChoiceBox;

    @FXML
    private ToggleGroup grupoDroneCarga = new ToggleGroup();

    private ACMEAirDrones app;

    @FXML
    public void initialize() {
        climatizadoCB.setDisable(true);
        protecaoCB.setDisable(true);
        pesoMaximoTField.setDisable(true);
        passageirosTField.setDisable(true);

        sairButton.setOnAction(event -> sair());
        cadastrarButton.setOnAction(event -> cadastrar());
        limparButton.setOnAction(event -> limpar());
        carregarButton.setOnAction(event -> mostrar());

        tipoDroneChoiceBox.getItems().addAll("Pessoal", "Carga Inanimada", "Carga Viva");
        tipoDroneChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                switch (newValue) {
                    case "Pessoal":
                        passageirosTField.setDisable(false);
                        pesoMaximoTField.setDisable(true);
                        protecaoCB.setDisable(true);
                        climatizadoCB.setDisable(true);
                        protecaoCB.setSelected(false);
                        climatizadoCB.setSelected(false);
                        break;
                    case "Carga Inanimada":
                        pesoMaximoTField.setDisable(false);
                        protecaoCB.setDisable(false);
                        passageirosTField.setDisable(true);
                        climatizadoCB.setDisable(true);
                        passageirosTField.clear();
                        climatizadoCB.setSelected(false);
                        break;
                    case "Carga Viva":
                        pesoMaximoTField.setDisable(false);
                        climatizadoCB.setDisable(false);
                        protecaoCB.setDisable(true);
                        passageirosTField.setDisable(true);
                        passageirosTField.clear();
                        protecaoCB.setSelected(false);
                        break;
                    default:
                        passageirosTField.setDisable(true);
                        pesoMaximoTField.setDisable(true);
                        protecaoCB.setDisable(true);
                        climatizadoCB.setDisable(true);
                        passageirosTField.clear();
                        protecaoCB.setSelected(false);
                        climatizadoCB.setSelected(false);
                        break;
                }
            }
        });
    }

    public void setACMEAirDrones(ACMEAirDrones app) {
        this.app = app;
    }

    public void limpar() {
        codigoTField.clear();
        custoFixoTField.clear();
        autonomiaTField.clear();
        pesoMaximoTField.clear();
        passageirosTField.clear();
        imprimeTField.clear();
        cargaVivaRButton.setSelected(false);
        cargaInanimadaRButton.setSelected(false);
        climatizadoCB.setSelected(false);
        protecaoCB.setSelected(false);
        tipoDroneChoiceBox.getSelectionModel().clearSelection();
    }

    public void mostrar() {
        imprimeTField.clear();
        if (app == null || app.getFrota().isEmpty()) {
            imprimeTField.setText("Nenhum drone cadastrado!");
            return;
        }
    }

    @FXML
    public void cadastrar() {
        try {
            String tipoDrone = tipoDroneChoiceBox.getValue();
            if (tipoDrone != null) {
                int codigo = Integer.parseInt(codigoTField.getText());
                for (Drone drone : app.getFrota()) {
                    if (drone.getCodigo() == codigo) {
                        imprimeTField.setText("Codigo ja existente!");
                        return;
                    }
                }
                double custoFixo = Double.parseDouble(custoFixoTField.getText());
                double autonomia = Double.parseDouble(autonomiaTField.getText());
                switch (tipoDrone) {
                    case "Pessoal":
                        int numeroPassageiros = Integer.parseInt(passageirosTField.getText());
                        Drone dronePessoal = new DronePessoal(codigo, custoFixo, autonomia, numeroPassageiros);
                        if (app.cadastrarDrone(dronePessoal)) {
                            imprimeTField.setText("Drone cadastrado com sucesso!");
                        }
                        break;
                    case "Carga Inanimada":
                        boolean protecao = protecaoCB.isSelected();
                        double peso = Double.parseDouble(pesoMaximoTField.getText());
                        Drone droneCargaInanimada = new DroneCargaInanimada(codigo, custoFixo, autonomia, peso, protecao);
                        if (app.cadastrarDrone(droneCargaInanimada)) {
                            imprimeTField.setText("Drone cadastrado com sucesso!");
                        }
                        break;
                    case "Carga Viva":
                        boolean climatizado = climatizadoCB.isSelected();
                        peso = Double.parseDouble(pesoMaximoTField.getText());
                        Drone droneCargaViva = new DroneCargaViva(codigo, custoFixo, autonomia, peso, climatizado);
                        if (app.cadastrarDrone(droneCargaViva)) {
                            imprimeTField.setText("Drone cadastrado com sucesso!");
                        }
                        break;
                    default:
                        imprimeTField.setText("Erro: Algum tipo de drone deve ser selecionado");
                        break;
                }
            } else {
                imprimeTField.setText("Erro: Um tipo de drone deve ser selecionado");
                return;
            }
        } catch (NumberFormatException e) {
            imprimeTField.setText("Digite apenas numeros!");
        } catch (NullPointerException e) {
            imprimeTField.setText("Preencha todos os dados!");
        }
    }

    private void sair() {
        Stage stage = (Stage) sairButton.getScene().getWindow();
        stage.close();
    }
}
