package model.entities;

public class Provider {
	/**
	 * Classe com atributos e m�todos referente aos dados do Fornecedor.
	 * 
	 * @author Adriano Queiroz
	 * @version 1.0
	 */
	private Integer id;
	private String name;
	private String adress;
	private String telephone;
	private String email;
	private String cpf;
	private String cnpj;
	private String category;

	/**
	 * Contrutor padr�o.
	 */
	public Provider() {

	}

	/**
	 * Contrutor com argumnetos.
	 * 
	 * @param id        - Integer
	 * @param name      - String
	 * @param adress    - String
	 * @param telephone - String
	 * @param email     - String
	 * @param cpf       - String
	 * @param cnpj      - String
	 * @param category  - String
	 */
	public Provider(Integer id, String name, String adress, String telephone, String email, String cpf, String cnpj,
			String category) {
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.telephone = telephone;
		this.email = email;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.category = category;
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
	 * M�todo que retorna o Endere�o.
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
	 * M�todo que retorna o Telefone.
	 * 
	 * @return String
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * M�todo que seta o valor do Telefone.
	 * 
	 * @param telephone - String
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * M�todo que retorna o Cpf.
	 * 
	 * @return - String
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * M�todo que seta o valor do Cpf.
	 * 
	 * @param cpf - String
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * M�todo que retorna o Cnpj.
	 * 
	 * @return - String
	 */
	public String getCnpj() {
		return cnpj;
	}

	/**
	 * M�todo que seta o valor do Cnpj.
	 * 
	 * @param cnpj - String
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	/**
	 * M�todo que retorna a Categoria.
	 * 
	 * @return String
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * M�todo que seta o valor da Categoria.
	 * 
	 * @param category - String
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * M�todo sobrescrito.
	 * 
	 * @return String - cadeia de caracteres.,
	 */
	@Override
	public String toString() {
		return "Provider [id=" + id + ", name=" + name + ", adress=" + adress + ", telephone=" + telephone + ", email="
				+ email + ", cpf=" + cpf + ", cnpj=" + cnpj + ", category=" + category + "]";
	}

}
