package dados;

public enum Estado {
	PENDENTE(1,"Pendente"), ALOCADO(2,"Alocado"), TERMINADO(3,"Terminado"),
	CANCELADO(0,"Cancelado");

	private final int numero;
	private final String nome;

	private Estado(int numero, String nome) {
		this.numero = numero;
		this.nome = nome;
	}

	public int getNumero() {
		return numero;
	}

	public String getNome() {
		return nome;
	}
}
