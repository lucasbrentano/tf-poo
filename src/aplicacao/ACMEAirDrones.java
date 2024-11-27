package aplicacao;

import dados.Drone;
import dados.Transporte;

import java.util.*;

public class ACMEAirDrones {

    private List<Drone> frota;
    private List<Transporte> transportes;
    private Queue<Transporte> filaTransporte;

    public ACMEAirDrones() {
        frota = new ArrayList<Drone>();
        transportes = new ArrayList<Transporte>();
        filaTransporte = new LinkedList<>();
    }

    public void executar() {

    }

    public boolean cadastrarTransporte(Transporte transporte) {
        boolean existe = transportes.stream().anyMatch(t -> t.getNumero() == transporte.getNumero());
        if (!existe) {
            transportes.add(transporte);
            filaTransporte.add(transporte);
            return true;
        } else {
            return false;
        }
    }

    public boolean cadastrarDrone(Drone drone) {
        boolean existe = frota.stream().anyMatch(d -> d.getCodigo() == drone.getCodigo());
        if (!existe) {
            return frota.add(drone);
        } else {
            return false;
        }
    }

    public List<Drone> getFrota() {
        return frota;
    }

    public List<Transporte> getTransportes() {
        return transportes;
    }

    public Queue<Transporte> getFilaTransporte() {
        return filaTransporte;
    }

    public Drone getDroneByCodigo(int codigo) {
        for (Drone d : frota) {
            if (d.getCodigo() == codigo) {
                return d;
            }
        }
        return null;
    }
}
