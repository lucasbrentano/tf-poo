package dados;

public class DronePessoal extends Drone {

	private int qtdMaxPessoas;

	public DronePessoal(int codigo, double custoFixo, double autonomia, int qtdMaxPessoas) {
		super(codigo, custoFixo, autonomia);
		this.qtdMaxPessoas = qtdMaxPessoas;
	}

	public int getQtdMaxPessoas() {
		return qtdMaxPessoas;
	}

	@Override
	public boolean adicionaTransporte(Transporte transporte) {
		return false;
	}

	@Override
	public double calculaCustoKm() {
		double custo = 0;

		custo = getCustoFixo() + (qtdMaxPessoas * 2);

		return custo;
	}

}
