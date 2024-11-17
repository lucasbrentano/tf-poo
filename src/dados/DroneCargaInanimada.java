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
		return 0;
	}

	@Override
	public String geraTexto() {
		if (protecao) {
			return super.geraTexto() + "|Protegido: Sim";
		} else {
			return super.geraTexto() + "|Protegido: Nao";
		}
	}
}
