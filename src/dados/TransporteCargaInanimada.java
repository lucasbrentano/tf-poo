package dados;

public class TransporteCargaInanimada extends Transporte {

	private boolean cargaPerigosa;

	public TransporteCargaInanimada(int numero, String nomeCliente, String descricao, double peso,
									double latitudeOrigem, double latitudeDestino, double longitudeOrigem,
									double longitudeDestino, boolean cargaPerigosa) {
		super(numero, nomeCliente, descricao, peso, latitudeOrigem, latitudeDestino, longitudeOrigem, longitudeDestino);
		this.cargaPerigosa = cargaPerigosa;
	}

	@Override
	public double calculaCusto() {
		if (this.getDrone() == null) {
			return 0;
		}

		if (cargaPerigosa) {
			return (this.getDrone().calculaCustoKm() * this.calculaDistancia()) + 500;
		} else {
			return this.getDrone().calculaCustoKm() * this.calculaDistancia();
		}
	}

	@Override
	public String geraTexto() {
		if (cargaPerigosa) {
			return super.geraTexto() + "|Tipo: Carga Inanimada|Carga Perigosa: Sim|Custo: "
					+ String.format("%.2f", this.calculaCusto()) + "|Status: " + this.getSituacao().getNome();
		}
		else {
			return super.geraTexto() + "Tipo: Carga Inanimada|Carga Pergiosa: Não|Custo: "
					+ String.format("%.2f",this.calculaCusto())	+ "|Status: " + this.getSituacao().getNome();
		}
	}
}
