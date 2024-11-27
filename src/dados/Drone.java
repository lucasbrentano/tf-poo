package dados;

import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;

public abstract class Drone implements Comparable<Drone> {

	private int codigo;

	private double custoFixo;

	private double autonomia;

	private Collection<Transporte> transporte;

	public Drone(int codigo, double custoFixo, double autonomia) {
		this.codigo = codigo;
		this.custoFixo = custoFixo;
		this.autonomia = autonomia;
		this.transporte = new HashSet<Transporte>();
	}

	public int getCodigo() {
		return codigo;
	}

	public double getCustoFixo() {
		return custoFixo;
	}

	public double getAutonomia() {
		return autonomia;
	}

	public Collection<Transporte> getTransporte() {
		return new TreeSet<>(transporte);
	}

	public boolean adicionaTransporte(Transporte transporte) {
		return this.transporte.add(transporte);
	}

	public abstract double calculaCustoKm();

	@Override
	public int compareTo(Drone d) {
		return Integer.compare(this.codigo, d.getCodigo());
	}
}
