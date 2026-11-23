package excecoes;

public class ObjetoNuloException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2106106985685285367L;

	public ObjetoNuloException(String nome) {
		super(nome + " não pode ser nulo");
	}
	
}
