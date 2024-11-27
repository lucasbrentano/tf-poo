package viewcontroller;

import aplicacao.ACMEAirDrones;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import dados.Drone;
import dados.DronePessoal;
import dados.DroneCargaViva;
import dados.DroneCargaInanimada;
import dados.Transporte;
import dados.TransportePessoal;
import dados.TransporteCargaViva;
import dados.TransporteCargaInanimada;
import dados.Estado;

public class CarregarController {
    @FXML
    private TextField nomeField;

    @FXML
    private Button carregarButton;

    @FXML
    private Button sairButton;

    private ACMEAirDrones app;

    public void setACMEAirDrones(ACMEAirDrones app) {
        this.app = app;
    }

    @FXML
    private void handleCarregar() {
        String nomeBase = nomeField.getText();
        if (nomeBase == null || nomeBase.trim().isEmpty()) {
            mostrarAlerta("Erro", "Nome inválido", "Por favor, informe um nome válido para o arquivo.");
            return;
        }

        carregarDronesCsv("src/recursos/" + nomeBase + "-DRONES.csv");
        carregarTransportesCsv("src/recursos/" + nomeBase + "-TRANSPORTES.csv");
    }

    private void carregarDronesCsv(String nomeArquivo) {
        File file = new File(nomeArquivo);
        if (!file.exists()) {
            mostrarAlerta("Erro", "Arquivo não encontrado", "O arquivo " + nomeArquivo + " não foi encontrado.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            //Set<Drone> drones = new HashSet<>();

            while ((line = br.readLine()) != null) {
                String[] token = line.split(";");
                int tipo = Integer.parseInt(token[0]);
                int codigo = Integer.parseInt(token[1]);
                double custoFixo = Double.parseDouble(token[2]);
                double autonomia = Double.parseDouble(token[3]);
                Drone drone;
                switch (tipo) {
                    case 1:
                        int qtdMaxPessoas = Integer.parseInt(token[4]);
                        drone = new DronePessoal(codigo, custoFixo, autonomia, qtdMaxPessoas);
                        app.cadastrarDrone(drone);
                        break;
                    case 2:
                        double pesoMaximo = Double.parseDouble(token[4]);
                        boolean protecao = Boolean.parseBoolean(token[5]);
                        drone = new DroneCargaInanimada(codigo, custoFixo, autonomia, pesoMaximo, protecao);
                        app.cadastrarDrone(drone);
                        break;
                    case 3:
                        double pesoMaximoViva = Double.parseDouble(token[4]);
                        boolean climatizado = Boolean.parseBoolean(token[5]);
                        drone = new DroneCargaViva(codigo, custoFixo, autonomia, pesoMaximoViva, climatizado);
                        app.cadastrarDrone(drone);
                        break;
                }
            }

            if (app != null) {
                //app.getFrota().addAll(drones);
                mostrarAlerta("Sucesso", "Drones Carregados", "Os drones foram carregados com sucesso do arquivo " + nomeArquivo);
            }
        } catch (IOException e) {
            mostrarAlerta("Erro", "Erro ao carregar Drones", "Ocorreu um erro ao carregar os drones: " + e.getMessage());
        }
    }

    private void carregarTransportesCsv(String nomeArquivo) {
        File file = new File(nomeArquivo);
        if (!file.exists()) {
            mostrarAlerta("Erro", "Arquivo não encontrado", "O arquivo " + nomeArquivo + " não foi encontrado.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            Set<Transporte> transportes = new HashSet<>();

            while ((line = br.readLine()) != null) {
                String[] token = line.split(";");
                int tipo = Integer.parseInt(token[0]);
                int numero = Integer.parseInt(token[1]);
                String nomeCliente = token[2];
                String descricao = token[3];
                double peso = Double.parseDouble(token[4]);
                double latOrigem = Double.parseDouble(token[5]);
                double longOrigem = Double.parseDouble(token[6]);
                double latDestino = Double.parseDouble(token[7]);
                double longDestino = Double.parseDouble(token[8]);
                Estado situacao = Estado.valueOf(token[11]);
                String droneAssociado = token[12];
                Transporte transporte;

                switch (tipo) {
                    case 1:
                        int qtdPessoas = Integer.parseInt(token[9]);
                        transporte = new TransportePessoal(numero, nomeCliente, descricao, peso, latOrigem, latDestino, longOrigem, longDestino, qtdPessoas);
                        transporte.setSituacao(situacao);
                        if (!"null".equals(droneAssociado)) {
                            transporte.setDrone(app.getDroneByCodigo(Integer.parseInt(droneAssociado)));
                        }
                        if (app.cadastrarTransporte(transporte)) {
                            transportes.add(transporte);
                        };
                        break;
                    case 2:
                        boolean perigosa = Boolean.parseBoolean(token[9]);
                        transporte = new TransporteCargaInanimada(numero, nomeCliente, descricao, peso, latOrigem, latDestino, longOrigem, longDestino, perigosa);
                        transporte.setSituacao(situacao);
                        if (!"null".equals(droneAssociado)) {
                            transporte.setDrone(app.getDroneByCodigo(Integer.parseInt(droneAssociado)));
                        }
                        if (app.cadastrarTransporte(transporte)) {
                            transportes.add(transporte);
                        };
                        break;
                    case 3:
                        double tempMin = Double.parseDouble(token[9]);
                        double tempMax = Double.parseDouble(token[10]);
                        transporte = new TransporteCargaViva(numero, nomeCliente, descricao, peso, latOrigem, latDestino, longOrigem, longDestino, tempMin, tempMax);
                        transporte.setSituacao(situacao);
                        if (!"null".equals(droneAssociado)) {
                            transporte.setDrone(app.getDroneByCodigo(Integer.parseInt(droneAssociado)));
                        }
                        if(app.cadastrarTransporte(transporte)) {
                            transportes.add(transporte);
                        };
                        break;
                }
            }

            if (app != null) {
                //app.getTransportes().addAll(transportes);
                transportes.stream()
                        .filter(transporte -> transporte.getSituacao() == Estado.PENDENTE)
                        .forEach(app.getFilaTransporte()::add);
                mostrarAlerta("Sucesso", "Transportes Carregados", "Os transportes foram carregados com sucesso do arquivo " + nomeArquivo);
            }
        } catch (IOException e) {
            mostrarAlerta("Erro", "Erro ao carregar Transportes", "Ocorreu um erro ao carregar os transportes: " + e.getMessage());
        }
    }

    private void mostrarAlerta(String titulo, String cabecalho, String conteudo) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(conteudo);
        alert.showAndWait();
    }

    @FXML
    public void handleSair() {
        Stage stage = (Stage) sairButton.getScene().getWindow();
        stage.close();
    }
}
