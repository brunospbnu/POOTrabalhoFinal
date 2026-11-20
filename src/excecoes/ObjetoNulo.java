package excecoes;

public class ObjetoNulo extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2106106985685285367L;

	public ObjetoNulo(String nome) {
		super(nome + " não pode ser nulo");
	}
	
}
