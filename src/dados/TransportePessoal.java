package dados;

public class TransportePessoal extends Transporte {

	private int qtdPessoas;

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

	@Override
	public String geraTexto() {
		double custo = this.calculaCusto();
		String custoString;

		if (getDrone() == null) {
			custoString = "|Custo: em orçamento";
		} else {
			custoString = "|Custo: " + String.format("%.2f",custo);
		}

		return super.geraTexto() + "|Tipo: Transporte Pessoal|Quantidade de passageiros: " + qtdPessoas + custoString
				+ "|Status: " + this.getSituacao().getNome();
	}
}
