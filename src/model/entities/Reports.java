package model.entities;

public class Reports {
	/**
	 * Classe para relatório com referências para outras classes em métodos padrões.
	 * 
	 * @author Adriano Queiroz
	 * @version 1.0
	 */

	private Client client;
	private Product product;
	private Service service;

	/**
	 * Construtor padrão.
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
	 * Método que retorna o objeto Cliente.
	 * 
	 * @return Client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * Método que seta o valor do objeto.
	 * 
	 * @param client - Client
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * Método que retorna o objeto Produto.
	 * 
	 * @return Product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * Método que seta o valor do objeto.
	 * 
	 * @param product - Product
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * Método que retorna o objeto Service.
	 * 
	 * @return Service
	 */
	public Service getService() {
		return service;
	}

	/**
	 * Método que seta o valor do objeto.
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
		return "Reports [client=" + client + ", product=" + product + ", service=" + service + "]";
	}
}
