package excecoes;

public class NomeInvalidoException extends IllegalArgumentException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 455983921374758555L;

	public NomeInvalidoException(String nome) {
		super(nome + " não pode estar vazio e deve ter 3 letras ou mais");
	}
	
}
