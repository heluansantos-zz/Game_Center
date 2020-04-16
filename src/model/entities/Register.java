package model.entities;

public class Register {
	/**
	 * Classe com atributos e m�todos referente ao Cadastro do usu�rio.
	 * 
	 * @author Adriano Queiroz
	 * @vsersion 1.0
	 */
	private Integer id;
	private String name;
	private String password;
	private String email;

	/**
	 * Construtor padr�o.
	 */
	public Register() {

	}

	/**
	 * Construtor com argumnetos.
	 * 
	 * @param id       - Integer
	 * @param name     - String
	 * @param password - String
	 * @param email    - String
	 */
	public Register(Integer id, String name, String password, String email) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
	}

	/**
	 * M�todo que retorna o Id.
	 * 
	 * @return Integer
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * M�todo que seta o valor do Id.
	 * 
	 * @param id - Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * M�todo que retorna o Nome.
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * M�todo que seta o valor do Nome.
	 * 
	 * @param name - String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * M�todo que retorna a Senha.
	 * 
	 * @return String
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * M�todo que seta o valor da Senha.
	 * 
	 * @param password - String
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * M�todo que retorna o Email.
	 * 
	 * @return String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * M�todo que seta o valor do Email.
	 * 
	 * @param email - String
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * M�todo sobrescrito.
	 * 
	 * @return String - Cadeia de caracteres.
	 */
	@Override
	public String toString() {
		return "Register [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + "]";
	}
}
