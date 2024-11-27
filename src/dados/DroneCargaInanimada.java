package dados;

public class DroneCargaInanimada extends DroneCarga {

	private boolean protecao;

	public DroneCargaInanimada(int codigo, double custoFixo, double autonomia, double pesoMaximo, boolean protecao) {
		super(codigo, custoFixo, autonomia, pesoMaximo);
		this.protecao = protecao;
	}

	public boolean isProtecao() {
		return protecao;
	}

	@Override
	public boolean adicionaTransporte(Transporte transporte) {
		return false;
	}

	@Override
	public double calculaCustoKm() {
		double custo;
		if (protecao) {
			custo = getCustoFixo() + 10;
		} else {
			custo = getCustoFixo() + 5;
		}
		return custo;
	}

}
