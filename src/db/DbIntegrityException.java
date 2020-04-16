package db;

public class DbIntegrityException extends RuntimeException {
	/**
	 * Classe com método que exibe mensagem.
	 * 
	 * @author Adriano Queiroz
	 * @version 1.0
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Exibe mensagem.
	 * 
	 * @param msg - String
	 */
	public DbIntegrityException(String msg) {
		super(msg);
	}
}
