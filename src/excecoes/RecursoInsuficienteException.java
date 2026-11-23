package excecoes;

public class RecursoInsuficienteException extends IllegalArgumentException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecursoInsuficienteException() {
		super("Recurso insuficiente");
	}
}
