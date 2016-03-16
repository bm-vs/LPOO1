
public class Pendular extends Comboio{
	public Pendular(String nome) {
		super(nome);
		servico_a_bordo = new ServicoSemEnjoos();
	}

	public String getDescricaoServico() {
		return "Servico sem enjoos.";
	}

}
