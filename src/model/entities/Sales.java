package model.entities;

public class Sales {
	/**
	 * Classe com atributos e métodos referentes a venda de Produtos.
	 * 
	 * @author Adriano Queiroz
	 * @version 1.0
	 */
	
	private Integer id;
	private Integer idProduct;
	private Integer idClient;
	private String dateSale;

	private Product product;
	private Client client;

	/**
	 * Construtor padrão.
	 */
	public Sales() {

	}

	/**
	 * Construtor com argumnrtos.
	 * 
	 * @param id        - Integer
	 * @param idProduct - Integer
	 * @param idClient  - Integer
	 * @param dateSale  - String
	 * @param product   - Product
	 * @param client    - Client
	 */
	public Sales(Integer id, Integer idProduct, Integer idClient, String dateSale, Product product, Client client) {
		this.id = id;
		this.idProduct = idProduct;
		this.idClient = idClient;
		this.dateSale = dateSale;
		this.product = product;
		this.client = client;
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
	 * Método que retorna o Id do Produto.
	 * 
	 * @return Integer
	 */
	public Integer getIdProduct() {
		return idProduct;
	}

	/**
	 * Método que seta o valor do Id Produto.
	 * 
	 * @param idProduct - Integer
	 */
	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}

	/**
	 * Método que retorna o Id do Client.
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
	 * Método que retorna a Data.
	 * 
	 * @return String
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
	 * Método que retorna o valor do objeto Produto.
	 * 
	 * @return Product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * Método que seta o valor do objeto Produto.
	 * 
	 * @param product - Product
	 */
	public void setProduct(Product product) {
		this.product = product;
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
	 * Método que seta o valor do Cliente.
	 * 
	 * @param client - Client
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * Método sobrescrito.
	 * 
	 * @return String - Cadeia de caracteres.
	 */
	@Override
	public String toString() {
		return "Sales [id=" + id + ", idProduct=" + idProduct + ", idClient=" + idClient + ", dateSale=" + dateSale
				+ ", product=" + product + ", client=" + client + "]";
	}
}
