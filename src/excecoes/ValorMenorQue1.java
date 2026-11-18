package excecoes;

public class ValorMenorQue1 extends IllegalArgumentException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ValorMenorQue1(String nome) {
		super(nome + " não pode ser menor ou igual a 0 !!!");
	}
}
