package model.entities;

public class ServiceSales {
	/**
	 * Classe com atributos e m�todos referente a venda de Servi�os.
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
	 * Construtor padr�o.
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
	 * M�todo que retorna o Id Cliente.
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
	 * M�todo que retorna o Id Servi�o.
	 * 
	 * @return Integer
	 */
	public Integer getIdService() {
		return idService;
	}

	/**
	 * M�todo que seta o valor do Id Servi�o.
	 * 
	 * @param idService - Integer
	 */
	public void setIdService(Integer idService) {
		this.idService = idService;
	}

	/**
	 * M�todo que retorna a Data.
	 * 
	 * @return - String
	 */
	public String getDateSale() {
		return dateSale;
	}

	/**
	 * M�todo que seta o valor da Data.
	 * 
	 * @param dateSale - String
	 */
	public void setDateSale(String dateSale) {
		this.dateSale = dateSale;
	}

	/**
	 * M�todo que retorna o objeto Cliente.
	 * 
	 * @return Client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * M�todo que seta o valor do objeto Cliente.
	 * 
	 * @param client - Client
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * M�todo que retorna o objeto Servi�o.
	 * 
	 * @return Service
	 */
	public Service getService() {
		return service;
	}

	/**
	 * M�todo que seta o valor do objeto Servi�o.
	 * 
	 * @param service - Service
	 */
	public void setService(Service service) {
		this.service = service;
	}

	/**
	 * M�todo sobrescrito.
	 * 
	 * @return String - Cadeia de caracteres.
	 */
	@Override
	public String toString() {
		return "ServiceSales [id=" + id + ", idClient=" + idClient + ", idService=" + idService + ", dateSale="
				+ dateSale + ", client=" + client + ", service=" + service + "]";
	}
}
