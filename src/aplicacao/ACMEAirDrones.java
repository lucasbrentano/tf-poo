package aplicacao;

import dados.Drone;
import dados.Transporte;

import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

public class ACMEAirDrones {

    private Collection<Drone> frota;
    private Collection<Transporte> transportes;

    public ACMEAirDrones() {
        frota = new TreeSet<Drone>(Comparator.comparingInt(Drone::getCodigo));
        transportes = new TreeSet<Transporte>(Comparator.comparingInt(Transporte::getNumero));
    }

    public void executar() {

    }

    public boolean cadastrarTransporte(Transporte transporte) {
        boolean existe = transportes.stream().anyMatch(t -> t.getNumero() == transporte.getNumero());
        if (!existe) {
            return transportes.add(transporte);
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
}
