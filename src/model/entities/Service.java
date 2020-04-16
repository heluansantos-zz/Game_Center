package model.entities;

public class Service {
	/**
	 * Classe com atributos e métodos referentes ao Cadastro de Serviços.
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
	 * Construtor padrão.
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
	 * Método que reorna o Id.
	 * 
	 * @return Integer
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Método que seta o valor do Id.
	 * 
	 * @param id - Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Método que reorna o Id do Cliente.
	 * 
	 * @return Integer
	 */
	public Integer getIdClient() {
		return idClient;
	}

	/**
	 * Método que seta o valor do Id Cliente.
	 * 
	 * @param idClient - Integer
	 */
	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}

	/**
	 * Método que retorna o Nome.
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
	 * Método que retorna a Descrição Problema.
	 * 
	 * @return String
	 */
	public String getProblemDescription() {
		return problemDescription;
	}

	/**
	 * Método que seta o valor para a Descrição Problema.
	 * 
	 * @param problemDescription - String
	 */
	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}

	/**
	 * Método que retorna o Status.
	 * 
	 * @return String
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Método que seta o valor do Status.
	 * 
	 * @param status - String
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Método que retorna o valor do Serviço.
	 * 
	 * @return Double
	 */
	public Double getValue() {
		return value;
	}

	/**
	 * Método que seta o valor do Serviço.
	 * 
	 * @param value - Double
	 */
	public void setValue(Double value) {
		this.value = value;
	}

	/**
	 * Método que retorna a Garantia.
	 * 
	 * @return String
	 */
	public String getWarranty() {
		return warranty;
	}

	/**
	 * Método que seta o valor da Garantia.
	 * 
	 * @param warranty - String
	 */
	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	/**
	 * Método que retorna a Data.
	 * 
	 * @return String
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Método que seta o valor da Data.
	 * 
	 * @param date - String
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Método sobrescrito.
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
