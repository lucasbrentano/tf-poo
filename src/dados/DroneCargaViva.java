package dados;

public class DroneCargaViva extends DroneCarga {

	private boolean climatizado;

	public DroneCargaViva(int codigo, double custoFixo, double autonomia, double pesoMaximo, boolean climatizado) {
		super(codigo, custoFixo, autonomia, pesoMaximo);
		this.climatizado = climatizado;
	}

	public boolean isClimatizado() {
		return climatizado;
	}

	@Override
	public boolean adicionaTransporte(Transporte transporte) {
		return false;
	}

	@Override
	public double calculaCustoKm() {
		double custo;
		if (climatizado) {
			custo = getCustoFixo() + 20;
		} else {
			custo = getCustoFixo() + 10;
		}
		return custo;
	}
}
