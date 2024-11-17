package dados;

import java.util.Collection;
import java.util.TreeSet;

public abstract class Drone {

	private int codigo;

	private double custoFixo;

	private double autonomia;

	private Collection<Transporte> transporte;

	public Drone(int codigo, double custoFixo, double autonomia) {
		this.codigo = codigo;
		this.custoFixo = custoFixo;
		this.autonomia = autonomia;
		this.transporte = new TreeSet<Transporte>();
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

	public abstract boolean adicionaTransporte(Transporte transporte);

	public abstract double calculaCustoKm();

	public String geraTexto() {
		return "Código: " + this.codigo + "|Custo Fixo: " + this.custoFixo + "|Autonomia: " + this.autonomia;
	}
}
