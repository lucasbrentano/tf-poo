package viewcontroller;

import aplicacao.ACMEAirDrones;
import dados.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private TableView<DronePessoal> pessoalTableView;
    @FXML
    private TableColumn<DronePessoal, Integer> codigoPessoalColumn;
    @FXML
    private TableColumn<DronePessoal, Double> custoPessoalColumn;
    @FXML
    private TableColumn<DronePessoal, Double> autonomiaPessoalColumn;
    @FXML
    private TableColumn<DronePessoal, Integer> passageirosColumn;

    @FXML
    private TableView<DroneCargaInanimada> inanimadaTableView;
    @FXML
    private TableColumn<DroneCargaInanimada, Integer> codigoInanimadoColumn;
    @FXML
    private TableColumn<DroneCargaInanimada, Double> custoInanimadoColumn;
    @FXML
    private TableColumn<DroneCargaInanimada, Double> autonomiaInanimadoColumn;
    @FXML
    private TableColumn<DroneCargaInanimada, Double> pesoInanimadoColumn;
    @FXML
    private TableColumn<DroneCargaInanimada, Boolean> protecaoColumn;

    @FXML
    private TableView<DroneCargaViva> vivaTableView;
    @FXML
    private TableColumn<DroneCargaViva, Integer> codigoVivaColumn;
    @FXML
    private TableColumn<DroneCargaViva, Double> custoVivaColumn;
    @FXML
    private TableColumn<DroneCargaViva, Double> autonomiaVivaColumn;
    @FXML
    private TableColumn<DroneCargaViva, Double> pesoVivaColumn;
    @FXML
    private TableColumn<DroneCargaViva, Boolean> climatizadoColumn;

    private ACMEAirDrones app;

    @FXML
    public void initialize() {
        codigoPessoalColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getCodigo()).asObject());
        custoPessoalColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getCustoFixo()).asObject());
        autonomiaPessoalColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getAutonomia()).asObject());
        passageirosColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getQtdMaxPessoas()).asObject());

        codigoInanimadoColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getCodigo()).asObject());
        custoInanimadoColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getCustoFixo()).asObject());
        autonomiaInanimadoColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getAutonomia()).asObject());
        pesoInanimadoColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getPesoMaximo()).asObject());
        protecaoColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleBooleanProperty(cellData.getValue().isProtecao()).asObject());
        protecaoColumn.setCellFactory(col -> new TableCell<DroneCargaInanimada, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item ? "Sim" : "Não");
                }
            }
        });

        codigoVivaColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getCodigo()).asObject());
        custoVivaColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getCustoFixo()).asObject());
        autonomiaVivaColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getAutonomia()).asObject());
        pesoVivaColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getPesoMaximo()).asObject());
        climatizadoColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleBooleanProperty(cellData.getValue().isClimatizado()).asObject());
        climatizadoColumn.setCellFactory(col -> new TableCell<DroneCargaViva, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item ? "Sim" : "Não");
                }
            }
        });

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

    @FXML
    private void mostrar() {
        imprimeTField.clear();

        if (app == null || app.getFrota().isEmpty()) {
            imprimeTField.setText("Nenhum drone cadastrado!");
            return;
        }

        String tipoSelecionado = tipoDroneChoiceBox.getValue();
        if (tipoSelecionado == null) {
            imprimeTField.setText("Erro: Selecione um tipo de drone.");
            return;
        }

        ObservableList<Drone> droneList;

        switch (tipoSelecionado) {
            case "Pessoal":
                droneList = FXCollections.observableArrayList();
                for (Drone drone : app.getFrota()) {
                    if (drone instanceof DronePessoal) {
                        droneList.add(drone);
                    }
                }

                pessoalTableView.setVisible(true);
                inanimadaTableView.setVisible(false);
                vivaTableView.setVisible(false);

                pessoalTableView.setItems(FXCollections.observableArrayList(droneList.stream().filter(d -> d instanceof DronePessoal).map(d -> (DronePessoal) d).toList()));
                break;

            case "Carga Inanimada":
                ObservableList<DroneCargaInanimada> droneCargaInanimadaList = FXCollections.observableArrayList();
                for (Drone drone : app.getFrota()) {
                    if (drone instanceof DroneCargaInanimada) {
                        droneCargaInanimadaList.add((DroneCargaInanimada) drone);
                    }
                }

                inanimadaTableView.setVisible(true);
                pessoalTableView.setVisible(false);
                vivaTableView.setVisible(false);

                inanimadaTableView.setItems(droneCargaInanimadaList);
                break;

            case "Carga Viva":
                ObservableList<DroneCargaViva> droneCargaVivaList = FXCollections.observableArrayList();
                for (Drone drone : app.getFrota()) {
                    if (drone instanceof DroneCargaViva) {
                        droneCargaVivaList.add((DroneCargaViva) drone);
                    }
                }

                vivaTableView.setVisible(true);
                pessoalTableView.setVisible(false);
                inanimadaTableView.setVisible(false);

                vivaTableView.setItems(droneCargaVivaList);
                break;

            default:
                imprimeTField.setText("Erro: Selecione um tipo de drone válido.");
                return;
        }
    }



    private void sair() {
        Stage stage = (Stage) sairButton.getScene().getWindow();
        stage.close();
    }
}
