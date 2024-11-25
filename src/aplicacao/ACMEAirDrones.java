package aplicacao;

import dados.Drone;
import dados.Transporte;

import java.util.*;

public class ACMEAirDrones {

    private Collection<Drone> frota;
    private Collection<Transporte> transportes;
    private Queue<Transporte> filaTransporte;

    public ACMEAirDrones() {
        frota = new TreeSet<Drone>(Comparator.comparingInt(Drone::getCodigo));
        transportes = new TreeSet<Transporte>(Comparator.comparingInt(Transporte::getNumero));
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

    public Collection<Drone> getFrota() {
        return frota;
    }

    public Collection<Transporte> getTransportes() {
        return transportes;
    }

    public Queue<Transporte> getFilaTransporte() {
        return filaTransporte;
    }
}
