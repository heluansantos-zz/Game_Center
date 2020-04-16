package model.entities;

public class Reports {
	/**
	 * Classe para relat�rio com refer�ncias para outras classes em m�todos padr�es.
	 * 
	 * @author Adriano Queiroz
	 * @version 1.0
	 */

	private Client client;
	private Product product;
	private Service service;

	/**
	 * Construtor padr�o.
	 */
	public Reports() {

	}

	/**
	 * Construtor com argumnetos.
	 * 
	 * @param client  - Client
	 * @param product - Product
	 * @param service - Service
	 */
	public Reports(Client client, Product product, Service service) {
		this.client = client;
		this.product = product;
		this.service = service;
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
	 * M�todo que seta o valor do objeto.
	 * 
	 * @param client - Client
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * M�todo que retorna o objeto Produto.
	 * 
	 * @return Product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * M�todo que seta o valor do objeto.
	 * 
	 * @param product - Product
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * M�todo que retorna o objeto Service.
	 * 
	 * @return Service
	 */
	public Service getService() {
		return service;
	}

	/**
	 * M�todo que seta o valor do objeto.
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
		return "Reports [client=" + client + ", product=" + product + ", service=" + service + "]";
	}
}
