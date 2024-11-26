package dados;

public class TransporteCargaViva extends Transporte {

	private boolean climatizado;

	private double temperaturaMinima;

	private double temperaturaMaxima;

	public TransporteCargaViva(int numero, String nomeCliente, String descricao, double peso, double latitudeOrigem,
							   double latitudeDestino, double longitudeOrigem, double longitudeDestino,
							   boolean climatizado, double temperaturaMinima, double temperaturaMaxima) {
		super(numero, nomeCliente, descricao, peso, latitudeOrigem, latitudeDestino, longitudeOrigem, longitudeDestino);
		this.climatizado = climatizado;
		if (climatizado) {
			this.temperaturaMinima = temperaturaMinima;
			this.temperaturaMaxima = temperaturaMaxima;
		} else {
			this.temperaturaMinima = 0;
			this.temperaturaMaxima = 0;
		}
	}

	public double getTemperaturaMinima() {
		return temperaturaMinima;
	}

	public double getTemperaturaMaxima() {
		return temperaturaMaxima;
	}

	public boolean isClimatizado() {
		return climatizado;
	}

	@Override
	public double calculaCusto() {
		if (this.getDrone() == null) {
			return 0;
		}

		if (temperaturaMaxima - temperaturaMinima > 10) {
			return (this.getDrone().calculaCustoKm() * this.calculaDistancia()) + 1000;
		} else {
			return (this.getDrone().calculaCustoKm() * this.calculaDistancia());
		}
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

		String climatizadoString = climatizado ? "|Climatizado: SIM|Temperatura Mínima: " + this.temperaturaMinima
				+ "ºC|Temperatura Máxima: " + this.temperaturaMaxima + "ºC" : "|Climatizado: NÃO";

		return super.geraTexto() + "|Tipo: Carga Viva" + climatizadoString + custoString + "|Status: "
				+ this.getSituacao().getNome();
	}
}
