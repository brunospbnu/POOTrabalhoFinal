package excecoes;

public class ValorMenorQue0Exception extends IllegalArgumentException {
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6271670468128317025L;

	public ValorMenorQue0Exception(String nome) {
		
		super(nome + " não pode ser menor que 0 !!! ");
	}
}
