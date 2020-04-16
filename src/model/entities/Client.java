package model.entities;

public class Client {
	/**
	 * Classe com atributos e métodos padrões referente aos dados do Cliente.
	 * 
	 * @author Adriano Queiroz
	 * @version 1.0
	 */

	private Integer id;
	private String name;
	private String adress;
	private String telephone;
	private String cpf;

	/**
	 * Construtor padrão.
	 */
	public Client() {

	}

	/**
	 * Construtor com argumentos.
	 * 
	 * @param id        - Integer
	 * @param name      - String
	 * @param adress    - String
	 * @param telephone - String
	 * @param cpf       - String
	 */
	public Client(Integer id, String name, String adress, String telephone, String cpf) {
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.telephone = telephone;
		this.cpf = cpf;
	}

	/**
	 * Método que retorna o nome do cliente.
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Método que seta o valor do Nome.
	 * 
	 * @param name - String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Método que retorna o Endereço do Cliente.
	 * 
	 * @return String
	 */
	public String getAdress() {
		return adress;
	}

	/**
	 * Método que seta o valor do Endereço.
	 * 
	 * @param adress - String
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}

	/**
	 * Método que retorna o Telefone do Cliente.
	 * 
	 * @return String
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * Método que seta o valor do Telefone.
	 * 
	 * @param telephone
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * Método que retorna o Cpf do Cliente.
	 * 
	 * @return - String
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * Método que seta o valor do Cpf.
	 * 
	 * @param cpf
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * Método que retorna o Id do Cliente.
	 * 
	 * @return Integer
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Método que seta o valor do Id.
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Método sobrescrito.
	 * 
	 * @return String - Cadeia de caracteres.
	 */
	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", adress=" + adress + ", telephone=" + telephone + ", cpf="
				+ cpf + "]";
	}
}
