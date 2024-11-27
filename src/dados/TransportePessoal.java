package dados;

public class TransportePessoal extends Transporte {

	private int qtdPessoas;

	public TransportePessoal() {}

	public TransportePessoal(int numero, String nomeCliente, String descricao, double peso, double latitudeOrigem,
							 double latitudeDestino, double longitudeOrigem, double longitudeDestino, int qtdPessoas) {
		super(numero, nomeCliente, descricao, peso, latitudeOrigem, latitudeDestino, longitudeOrigem, longitudeDestino);
		this.qtdPessoas = qtdPessoas;
	}

	public int getQtdPessoas() {
		return qtdPessoas;
	}

	@Override
	public double calculaCusto() {
		if (this.getDrone() == null) {
			return 0;
		}

        return (this.getDrone().calculaCustoKm() * this.calculaDistancia()) + (this.qtdPessoas * 10);
	}
}
