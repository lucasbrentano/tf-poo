package viewcontroller;

import aplicacao.ACMEAirDrones;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import dados.Drone;
import dados.Transporte;
import dados.TransportePessoal;
import dados.TransporteCargaViva;
import dados.TransporteCargaInanimada;
import dados.Estado;

public class SalvarController {
    @FXML
    private TextField nomeField;

    @FXML
    private Button salvarButton;

    @FXML
    private Button sairButton;

    private ACMEAirDrones app;

    public void setACMEAirDrones(ACMEAirDrones app) {
        this.app = app;
    }

    @FXML
    private void handleSalvar() {
        String nomeBase = nomeField.getText();
        if (nomeBase == null || nomeBase.trim().isEmpty()) {
            mostrarAlerta("Erro", "Nome inválido", "Por favor, informe um nome válido para o arquivo.");
            return;
        }

        salvarDronesCsv("src/recursos/" + nomeBase + "-DRONES.csv");
        salvarTransportesCsv("src/recursos/" + nomeBase + "-TRANSPORTES.csv");
    }

    private void salvarDronesCsv(String nomeArquivo) {
        List<Drone> drones = app.getFrota();
        try (PrintWriter writer = new PrintWriter(new File(nomeArquivo))) {
            StringBuilder sb = new StringBuilder();
            sb.append("tipo;codigo;custofixo;autonomia;qtdmaxpessoas_pesomaximo;protecao_climatizado\n");
            for (Drone drone : drones) {
                if (drone instanceof dados.DronePessoal) {
                    sb.append("1;")
                            .append(drone.getCodigo()).append(";")
                            .append(drone.getCustoFixo()).append(";")
                            .append(drone.getAutonomia()).append(";")
                            .append(((dados.DronePessoal) drone).getQtdMaxPessoas()).append("\n");
                } else if (drone instanceof dados.DroneCargaInanimada) {
                    sb.append("2;")
                            .append(drone.getCodigo()).append(";")
                            .append(drone.getCustoFixo()).append(";")
                            .append(drone.getAutonomia()).append(";")
                            .append(((dados.DroneCargaInanimada) drone).getPesoMaximo()).append(";")
                            .append(((dados.DroneCargaInanimada) drone).isProtecao()).append("\n");
                } else if (drone instanceof dados.DroneCargaViva) {
                    sb.append("3;")
                            .append(drone.getCodigo()).append(";")
                            .append(drone.getCustoFixo()).append(";")
                            .append(drone.getAutonomia()).append(";")
                            .append(((dados.DroneCargaViva) drone).getPesoMaximo()).append(";")
                            .append(((dados.DroneCargaViva) drone).isClimatizado()).append("\n");
                }
            }
            writer.write(sb.toString());
            mostrarAlerta("Sucesso", "Drones Salvos", "Os drones foram salvos com sucesso no arquivo " + nomeArquivo);
        } catch (FileNotFoundException e) {
            mostrarAlerta("Erro", "Erro ao salvar Drones", "Ocorreu um erro ao salvar os drones: " + e.getMessage());
        }
    }

    private void salvarTransportesCsv(String nomeArquivo) {
        List<Transporte> transportes = app.getTransportes();
        try (PrintWriter writer = new PrintWriter(new File(nomeArquivo))) {
            StringBuilder sb = new StringBuilder();
            sb.append("tipo;numero;nomecliente;descricao;peso;latorigem;longorigem;latdestino;longdestino;qtdpessoas_perigosa_tempmin;tempmax;estado;droneassociado\n");
            for (Transporte transporte : transportes) {
                if (transporte instanceof TransportePessoal) {
                    sb.append("1;")
                            .append(transporte.getNumero()).append(";")
                            .append(transporte.getNomeCliente()).append(";")
                            .append(transporte.getDescricao()).append(";")
                            .append(transporte.getPeso()).append(";")
                            .append(transporte.getLatitudeOrigem()).append(";")
                            .append(transporte.getLongitudeOrigem()).append(";")
                            .append(transporte.getLatitudeDestino()).append(";")
                            .append(transporte.getLongitudeDestino()).append(";")
                            .append(((TransportePessoal) transporte).getQtdPessoas()).append(";")
                            .append(";")
                            .append(transporte.getSituacao()).append(";")
                            .append(transporte.getDrone() != null ? transporte.getDrone().getCodigo() : "null").append("\n");
                } else if (transporte instanceof TransporteCargaInanimada) {
                    sb.append("2;")
                            .append(transporte.getNumero()).append(";")
                            .append(transporte.getNomeCliente()).append(";")
                            .append(transporte.getDescricao()).append(";")
                            .append(transporte.getPeso()).append(";")
                            .append(transporte.getLatitudeOrigem()).append(";")
                            .append(transporte.getLongitudeOrigem()).append(";")
                            .append(transporte.getLatitudeDestino()).append(";")
                            .append(transporte.getLongitudeDestino()).append(";")
                            .append(((TransporteCargaInanimada) transporte).isCargaPerigosa()).append(";")
                            .append(";")
                            .append(transporte.getSituacao()).append(";")
                            .append(transporte.getDrone() != null ? transporte.getDrone().getCodigo() : "null").append("\n");
                } else if (transporte instanceof TransporteCargaViva) {
                    sb.append("3;")
                            .append(transporte.getNumero()).append(";")
                            .append(transporte.getNomeCliente()).append(";")
                            .append(transporte.getDescricao()).append(";")
                            .append(transporte.getPeso()).append(";")
                            .append(transporte.getLatitudeOrigem()).append(";")
                            .append(transporte.getLongitudeOrigem()).append(";")
                            .append(transporte.getLatitudeDestino()).append(";")
                            .append(transporte.getLongitudeDestino()).append(";")
                            .append(((TransporteCargaViva) transporte).getTemperaturaMinima()).append(";")
                            .append(((TransporteCargaViva) transporte).getTemperaturaMaxima()).append(";")
                            .append(transporte.getSituacao()).append(";")
                            .append(transporte.getDrone() != null ? transporte.getDrone().getCodigo() : "null").append("\n");
                }
            }
            writer.write(sb.toString());
            mostrarAlerta("Sucesso", "Transportes Salvos", "Os transportes foram salvos com sucesso no arquivo " + nomeArquivo);
        } catch (FileNotFoundException e) {
            mostrarAlerta("Erro", "Erro ao salvar Transportes", "Ocorreu um erro ao salvar os transportes: " + e.getMessage());
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
