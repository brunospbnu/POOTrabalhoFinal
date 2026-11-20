package excecoes;

public class RecursoInsuficienteException extends IllegalArgumentException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4915310792648740945L;

	public RecursoInsuficienteException() {
		super("Recurso insuficiente");
	}
}
