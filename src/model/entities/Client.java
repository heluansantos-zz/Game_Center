package model.entities;

public class Client {
	/**
	 * Classe com atributos e m�todos padr�es referente aos dados do Cliente.
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
	 * Construtor padr�o.
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
	 * M�todo que retorna o nome do cliente.
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
	 * M�todo que retorna o Endere�o do Cliente.
	 * 
	 * @return String
	 */
	public String getAdress() {
		return adress;
	}

	/**
	 * M�todo que seta o valor do Endere�o.
	 * 
	 * @param adress - String
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}

	/**
	 * M�todo que retorna o Telefone do Cliente.
	 * 
	 * @return String
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * M�todo que seta o valor do Telefone.
	 * 
	 * @param telephone
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * M�todo que retorna o Cpf do Cliente.
	 * 
	 * @return - String
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * M�todo que seta o valor do Cpf.
	 * 
	 * @param cpf
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * M�todo que retorna o Id do Cliente.
	 * 
	 * @return Integer
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * M�todo que seta o valor do Id.
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * M�todo sobrescrito.
	 * 
	 * @return String - Cadeia de caracteres.
	 */
	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", adress=" + adress + ", telephone=" + telephone + ", cpf="
				+ cpf + "]";
	}
}
