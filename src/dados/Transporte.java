package dados;

public abstract class Transporte {

	private int numero;

	private String nomeCliente;

	private String descricao;

	private double peso;

	private double latitudeOrigem;

	private double latitudeDestino;

	private double longitudeOrigem;

	private double longitudeDestino;

	private Estado situacao;

	private Drone drone;

	public Transporte(int numero, String nomeCliente, String descricao, double peso, double latitudeOrigem,
					  double latitudeDestino, double longitudeOrigem, double longitudeDestino) {
		this.numero = numero;
		this.nomeCliente = nomeCliente;
		this.descricao = descricao;
		this.peso = peso;
		this.latitudeOrigem = latitudeOrigem;
		this.latitudeDestino = latitudeDestino;
		this.longitudeOrigem = longitudeOrigem;
		this.longitudeDestino = longitudeDestino;
		this.situacao = Estado.PENDENTE;
		this.drone = null;
	}

	public int getNumero() {
		return numero;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public String getDescricao() {
		return descricao;
	}

	public double getPeso() {
		return peso;
	}

	public double getLatitudeOrigem() {
		return latitudeOrigem;
	}

	public double getLatitudeDestino() {
		return latitudeDestino;
	}

	public double getLongitudeOrigem() {
		return longitudeOrigem;
	}

	public double getLongitudeDestino() {
		return longitudeDestino;
	}

	public Estado getSituacao() {
		return situacao;
	}

	public Drone getDrone() {
		return drone;
	}

	public abstract double calculaCusto();

	public String geraTexto () {
		return "Código: " + this.numero + "|Nome: " + this.nomeCliente + "|Descrição: " + this.descricao
				+ "|Peso: " + String.format("%.2f",this.peso) + " kg";
	}

	public double calculaDistancia() {
		final int RAIO_TERRA_KM = 6371; // Raio médio da Terra em quilômetros

		// Converter graus para radianos
		double latOrigemRad = Math.toRadians(this.latitudeOrigem);
		double lonOrigemRad = Math.toRadians(this.longitudeOrigem);
		double latDestinoRad = Math.toRadians(this.latitudeDestino);
		double lonDestinoRad = Math.toRadians(this.longitudeDestino);

		// Diferenças das coordenadas
		double deltaLat = latDestinoRad - latOrigemRad;
		double deltaLon = lonDestinoRad - lonOrigemRad;

		// Aplicar a fórmula do Haversine
		double a = Math.pow(Math.sin(deltaLat / 2), 2) +
				Math.cos(latOrigemRad) * Math.cos(latDestinoRad) *
						Math.pow(Math.sin(deltaLon / 2), 2);

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		// Calcular a distância
		return RAIO_TERRA_KM * c;
	}

}
