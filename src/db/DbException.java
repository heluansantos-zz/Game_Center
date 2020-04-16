package db;

public class DbException extends RuntimeException {
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
	public DbException(String msg) {
		super(msg);
	}
}
