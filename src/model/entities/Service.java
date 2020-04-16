package model.entities;

public class Service {
	/**
	 * Classe com atributos e m�todos referentes ao Cadastro de Servi�os.
	 * 
	 * @author Adriano Queiroz
	 * @version 1.0
	 */

	private Integer id;
	private Integer idClient;
	private String name;
	private String problemDescription;
	private String status;
	private Double value;
	private String warranty;
	private String date;

	/**
	 * Construtor padr�o.
	 */
	public Service() {

	}

	/**
	 * Construtor com argumnetos.
	 * 
	 * @param id                 - Integer
	 * @param idClient           - Integer
	 * @param name               - String
	 * @param problemDescription - String
	 * @param status             - String
	 * @param value              - Double
	 * @param warranty           - String
	 * @param date               - String
	 */
	public Service(Integer id, Integer idClient, String name, String problemDescription, String status, Double value,
			String warranty, String date) {
		this.id = id;
		this.idClient = idClient;
		this.name = name;
		this.problemDescription = problemDescription;
		this.status = status;
		this.value = value;
		this.warranty = warranty;
		this.date = date;
	}

	/**
	 * M�todo que reorna o Id.
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
	 * M�todo que reorna o Id do Cliente.
	 * 
	 * @return Integer
	 */
	public Integer getIdClient() {
		return idClient;
	}

	/**
	 * M�todo que seta o valor do Id Cliente.
	 * 
	 * @param idClient - Integer
	 */
	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
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
	 * M�todo que retorna a Descri��o Problema.
	 * 
	 * @return String
	 */
	public String getProblemDescription() {
		return problemDescription;
	}

	/**
	 * M�todo que seta o valor para a Descri��o Problema.
	 * 
	 * @param problemDescription - String
	 */
	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}

	/**
	 * M�todo que retorna o Status.
	 * 
	 * @return String
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * M�todo que seta o valor do Status.
	 * 
	 * @param status - String
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * M�todo que retorna o valor do Servi�o.
	 * 
	 * @return Double
	 */
	public Double getValue() {
		return value;
	}

	/**
	 * M�todo que seta o valor do Servi�o.
	 * 
	 * @param value - Double
	 */
	public void setValue(Double value) {
		this.value = value;
	}

	/**
	 * M�todo que retorna a Garantia.
	 * 
	 * @return String
	 */
	public String getWarranty() {
		return warranty;
	}

	/**
	 * M�todo que seta o valor da Garantia.
	 * 
	 * @param warranty - String
	 */
	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	/**
	 * M�todo que retorna a Data.
	 * 
	 * @return String
	 */
	public String getDate() {
		return date;
	}

	/**
	 * M�todo que seta o valor da Data.
	 * 
	 * @param date - String
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * M�todo sobrescrito.
	 * 
	 * @return String - Cadeia de caracteres.
	 */
	@Override
	public String toString() {
		return "Service [id=" + id + ", idClient=" + idClient + ", name=" + name + ", problemDescription="
				+ problemDescription + ", status=" + status + ", value=" + value + ", warranty=" + warranty + ", date="
				+ date + "]";
	}
}
