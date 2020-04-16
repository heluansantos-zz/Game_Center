package model.entities;

public class ServiceSales {
	/**
	 * Classe com atributos e métodos referente a venda de Serviços.
	 * 
	 * @author Adriano Queiroz
	 * @version 1.0
	 */
	private Integer id;
	private Integer idClient;
	private Integer idService;
	private String dateSale;

	private Client client;
	private Service service;

	/**
	 * Construtor padrão.
	 */
	public ServiceSales() {

	}

	/**
	 * Construtor com argumentos.
	 * 
	 * @param id        - Integer
	 * @param idClient  - Integer
	 * @param idService - Integer
	 * @param dateSale  - String
	 * @param client    - Client
	 * @param service   - Service
	 */
	public ServiceSales(Integer id, Integer idClient, Integer idService, String dateSale, Client client,
			Service service) {
		this.id = id;
		this.idClient = idClient;
		this.idService = idService;
		this.dateSale = dateSale;
		this.client = client;
		this.service = service;
	}

	/**
	 * Método que retorna o Id.
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
	 * Método que retorna o Id Cliente.
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
	 * Método que retorna o Id Serviço.
	 * 
	 * @return Integer
	 */
	public Integer getIdService() {
		return idService;
	}

	/**
	 * Método que seta o valor do Id Serviço.
	 * 
	 * @param idService - Integer
	 */
	public void setIdService(Integer idService) {
		this.idService = idService;
	}

	/**
	 * Método que retorna a Data.
	 * 
	 * @return - String
	 */
	public String getDateSale() {
		return dateSale;
	}

	/**
	 * Método que seta o valor da Data.
	 * 
	 * @param dateSale - String
	 */
	public void setDateSale(String dateSale) {
		this.dateSale = dateSale;
	}

	/**
	 * Método que retorna o objeto Cliente.
	 * 
	 * @return Client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * Método que seta o valor do objeto Cliente.
	 * 
	 * @param client - Client
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * Método que retorna o objeto Serviço.
	 * 
	 * @return Service
	 */
	public Service getService() {
		return service;
	}

	/**
	 * Método que seta o valor do objeto Serviço.
	 * 
	 * @param service - Service
	 */
	public void setService(Service service) {
		this.service = service;
	}

	/**
	 * Método sobrescrito.
	 * 
	 * @return String - Cadeia de caracteres.
	 */
	@Override
	public String toString() {
		return "ServiceSales [id=" + id + ", idClient=" + idClient + ", idService=" + idService + ", dateSale="
				+ dateSale + ", client=" + client + ", service=" + service + "]";
	}
}
