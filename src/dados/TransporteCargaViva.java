package dados;

public class TransporteCargaViva extends Transporte {

	private boolean climatizado;

	private double temperaturaMinima;

	private double temperaturaMaxima;

	public TransporteCargaViva(int numero, String nomeCliente, String descricao, double peso, double latitudeOrigem,
							   double latitudeDestino, double longitudeOrigem, double longitudeDestino,
							   double temperaturaMinima, double temperaturaMaxima) {
		super(numero, nomeCliente, descricao, peso, latitudeOrigem, latitudeDestino, longitudeOrigem, longitudeDestino);
		this.temperaturaMinima = temperaturaMinima;
		this.temperaturaMaxima = temperaturaMaxima;
		if (temperaturaMinima < temperaturaMaxima) {
			this.climatizado = true;
		} else {
			this.climatizado = false;
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

}
