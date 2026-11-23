package excecoes;

public class NomeInvalidoException extends IllegalArgumentException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NomeInvalidoException(String nome) {
		super(nome + " não pode estar vazio e deve ter 3 letras ou mais");
	}
	
}
