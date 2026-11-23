package excecoes;

public class ValorMenorQue1Exception extends IllegalArgumentException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValorMenorQue1Exception(String nome) {
		super(nome + " não pode ser menor ou igual a 0 !!!");
	}
}
