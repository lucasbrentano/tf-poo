package viewcontroller;

import aplicacao.ACMEAirDrones;
import dados.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SimulacaoController {
    @FXML
    private TextField nomeField;

    @FXML
    private Button sairButton;

    private ACMEAirDrones app;

    public void handleCarregar() {
        String nomeArquivoDrones = nomeField.getText() + "-DRONES.CSV";
        String nomeArquivoTransportes = nomeField.getText() + "-TRANSPORTES.CSV";
        Path pathDrones = Paths.get("src/recursos", nomeArquivoDrones);
        Path pathTransportes = Paths.get("src/recursos", nomeArquivoTransportes);
        String linha = null;
        try (BufferedReader leitor = Files.newBufferedReader(pathDrones, Charset.defaultCharset())) {
            leitor.readLine();
            while ((linha = leitor.readLine()) != null) {
                String[] token = linha.split(";");
                if (token.length == 6 && token[0].equals("2")) {
                    try {
                        int codigoDrone = Integer.parseInt(token[1]);
                        double custoFixo = Double.parseDouble(token[2]);
                        double autonomia = Double.parseDouble(token[3]);
                        double pesoMaximo = Double.parseDouble(token[4]);
                        boolean protecao = Boolean.parseBoolean(token[5]);
                        Drone drone = new DroneCargaInanimada(codigoDrone, custoFixo, autonomia, pesoMaximo, protecao);
                        if (app.cadastrarDrone(drone)) {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Drone cadastrado");
                            alert.setHeaderText("Drone cadastrado com sucesso!");
                            alert.setContentText("Novo drone cadastrado com sucesso!\n Código: " + codigoDrone + "\nCusto: R$" + String.format("%.2f",custoFixo) + "\nAutonomia: " + autonomia + " km\nPeso: " + pesoMaximo + " kg");
                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Drone cadastrado");
                            alert.setHeaderText("Erro!");
                            alert.setContentText("Erro ao cadastrar drone!\nCódigo repetido.");
                            alert.showAndWait();
                        }
                    } catch (NumberFormatException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Drone cadastrado");
                        alert.setHeaderText("Erro!");
                        alert.setContentText("Erro ao cadastrar drone!\n" + e);
                        alert.showAndWait();
                    }
                } else if (token.length == 6 && token[0].equals("3")) {
                    try {
                        int codigoDrone = Integer.parseInt(token[1]);
                        double custoFixo = Double.parseDouble(token[2]);
                        double autonomia = Double.parseDouble(token[3]);
                        double pesoMaximo = Double.parseDouble(token[4]);
                        boolean climatizado = Boolean.parseBoolean(token[5]);
                        Drone drone = new DroneCargaViva(codigoDrone, custoFixo, autonomia, pesoMaximo, climatizado);
                        if (app.cadastrarDrone(drone)) {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Drone cadastrado");
                            alert.setHeaderText("Drone cadastrado com sucesso!");
                            alert.setContentText("Novo drone cadastrado com sucesso!\n Código: " + codigoDrone + "\nCusto: R$" + String.format("%.2f",custoFixo) + "\nAutonomia: " + autonomia + " km\nPeso: " + pesoMaximo + " kg");
                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Drone cadastrado");
                            alert.setHeaderText("Erro!");
                            alert.setContentText("Erro ao cadastrar drone!\nCódigo repetido.");
                            alert.showAndWait();
                        }
                    } catch (NumberFormatException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Drone cadastrado");
                        alert.setHeaderText("Erro!");
                        alert.setContentText("Erro ao cadastrar drone!\n" + e);
                        alert.showAndWait();
                    }
                } else if (token.length == 5 && token[0].equals("1")) {
                    try {
                        int codigoDrone = Integer.parseInt(token[1]);
                        double custoFixo = Double.parseDouble(token[2]);
                        double autonomia = Double.parseDouble(token[3]);
                        int qtdMaxPessoas = Integer.parseInt(token[4]);
                        Drone drone = new DronePessoal(codigoDrone, custoFixo, autonomia, qtdMaxPessoas);
                        if (app.cadastrarDrone(drone)) {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Drone cadastrado");
                            alert.setHeaderText("Drone cadastrado com sucesso!");
                            alert.setContentText("Novo drone cadastrado com sucesso!\n Código: " + codigoDrone + "\nCusto: R$" + String.format("%.2f",custoFixo) + "\nAutonomia: " + autonomia + " km\nNúmero máximo de passageiros: " + qtdMaxPessoas);
                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Drone cadastrado");
                            alert.setHeaderText("Erro!");
                            alert.setContentText("Erro ao cadastrar drone!\nCódigo repetido.");
                            alert.showAndWait();
                        }
                    } catch (NumberFormatException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Drone cadastrado");
                        alert.setHeaderText("Erro!");
                        alert.setContentText("Erro ao cadastrar drone!\n" + e);
                        alert.showAndWait();
                    }
                }
            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Drone cadastrado");
            alert.setHeaderText("Erro!");
            alert.setContentText("Erro ao cadastrar drone!\n" + e);
            alert.showAndWait();
        }

        try (BufferedReader leitor = Files.newBufferedReader(pathTransportes, Charset.defaultCharset())) {
            leitor.readLine();
            while ((linha = leitor.readLine()) != null) {
                String[] token = linha.split(";");
                if (token.length == 10 && token[0].equals("2")) {
                    try {
                        int numeroTransporte = Integer.parseInt(token[1]);
                        String nomeCliente = token[2];
                        String descricao = token[3];
                        double pesoCarga = Double.parseDouble(token[4]);
                        double latOrigem = Double.parseDouble(token[5]);
                        double lonOrigem = Double.parseDouble(token[6]);
                        double latDestino = Double.parseDouble(token[7]);
                        double lonDestino = Double.parseDouble(token[8]);
                        boolean perigosa = Boolean.parseBoolean(token[9]);
                        Transporte transporte = new TransporteCargaInanimada(numeroTransporte, nomeCliente, descricao, pesoCarga, latOrigem, latDestino, lonOrigem, lonDestino, perigosa);
                        if (app.cadastrarTransporte(transporte)) {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Transporte cadastrado");
                            alert.setHeaderText("Transporte cadastrado com sucesso!");
                            alert.setContentText("Novo transporte cadastrado com sucesso!\n Código: " + numeroTransporte + "\nNome do Cliente: " + nomeCliente + "\nDescrição: " + descricao + "\nPeso: " + pesoCarga + " kg");
                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Transporte cadastrado");
                            alert.setHeaderText("Erro!");
                            alert.setContentText("Erro ao cadastrar transporte!\nCódigo repetido.");
                            alert.showAndWait();
                        }
                    } catch (NumberFormatException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Drone cadastrado");
                        alert.setHeaderText("Erro!");
                        alert.setContentText("Erro ao cadastrar drone!\n" + e);
                        alert.showAndWait();
                    }
                } else if (token.length == 11 && token[0].equals("3")) {
                    try {
                        int numeroTransporte = Integer.parseInt(token[1]);
                        String nomeCliente = token[2];
                        String descricao = token[3];
                        double pesoCarga = Double.parseDouble(token[4]);
                        double latOrigem = Double.parseDouble(token[5]);
                        double lonOrigem = Double.parseDouble(token[6]);
                        double latDestino = Double.parseDouble(token[7]);
                        double lonDestino = Double.parseDouble(token[8]);
                        double tempMin = Double.parseDouble(token[9]);
                        double tempMax = Double.parseDouble(token[10]);
                        Transporte transporte = new TransporteCargaViva(numeroTransporte, nomeCliente, descricao, pesoCarga, latOrigem, latDestino, lonOrigem, lonDestino, tempMin, tempMax);
                        if (app.cadastrarTransporte(transporte)) {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Transporte cadastrado");
                            alert.setHeaderText("Transporte cadastrado com sucesso!");
                            alert.setContentText("Novo transporte cadastrado com sucesso!\n Código: " + numeroTransporte + "\nNome do Cliente: " + nomeCliente + "\nDescrição: " + descricao + "\nPeso: " + pesoCarga + " kg");
                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Transporte cadastrado");
                            alert.setHeaderText("Erro!");
                            alert.setContentText("Erro ao cadastrar transporte!\nCódigo repetido.");
                            alert.showAndWait();
                        }
                    } catch (NumberFormatException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Drone cadastrado");
                        alert.setHeaderText("Erro!");
                        alert.setContentText("Erro ao cadastrar drone!\n" + e);
                        alert.showAndWait();
                    }
                } else if (token.length == 10 && token[0].equals("1")) {
                    try {
                        int numeroTransporte = Integer.parseInt(token[1]);
                        String nomeCliente = token[2];
                        String descricao = token[3];
                        double pesoCarga = Double.parseDouble(token[4]);
                        double latOrigem = Double.parseDouble(token[5]);
                        double lonOrigem = Double.parseDouble(token[6]);
                        double latDestino = Double.parseDouble(token[7]);
                        double lonDestino = Double.parseDouble(token[8]);
                        int qtdPassageiros = Integer.parseInt(token[9]);
                        Transporte transporte = new TransportePessoal(numeroTransporte, nomeCliente, descricao, pesoCarga, latOrigem, latDestino, lonOrigem, lonDestino, qtdPassageiros);
                        if (app.cadastrarTransporte(transporte)) {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Transporte cadastrado");
                            alert.setHeaderText("Transporte cadastrado com sucesso!");
                            alert.setContentText("Novo transporte cadastrado com sucesso!\n Código: " + numeroTransporte + "\nNome do Cliente: " + nomeCliente + "\nDescrição: " + descricao + "\nPeso: " + pesoCarga + " kg\nPassageiros: " + qtdPassageiros);
                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Transporte cadastrado");
                            alert.setHeaderText("Erro!");
                            alert.setContentText("Erro ao cadastrar transporte!\nCódigo repetido.");
                            alert.showAndWait();
                        }
                    } catch (NumberFormatException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Drone cadastrado");
                        alert.setHeaderText("Erro!");
                        alert.setContentText("Erro ao cadastrar drone!\n" + e);
                        alert.showAndWait();
                    }
                }
            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Drone cadastrado");
            alert.setHeaderText("Erro ao cadastrar drone!");
            alert.setContentText("Erro ao cadastrar drone!\n" + e);
            alert.showAndWait();
        }
    }

    public void setACMEAirDrones(ACMEAirDrones app) {
        this.app = app;
    }

    public void handleSair() {
        Stage stage = (Stage) sairButton.getScene().getWindow();
        stage.close();
    }
}
