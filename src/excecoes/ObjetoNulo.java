package excecoes;

public class ObjetoNulo extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ObjetoNulo(String nome) {
		super(nome + " não pode ser nulo");
	}
	
}
