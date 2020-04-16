package model.entities;

public class Product {
	/**
	 * Classe com atributos e m�todos padr�es referente aos dados do Produto.
	 * 
	 * @author Adriano Queiroz
	 * @version 1.0
	 */
	private Integer id;
	private Integer idProvider;
	private String name;
	private Double value;
	private String brand;
	private String model;
	private Integer quantity;
	private String category;

	private Provider provider;

	/**
	 * Construtor padr�o do java.
	 */
	public Product() {
	}

	/**
	 * Construtor com argumentos.
	 * 
	 * @param id         - Integer
	 * @param idProvider - Integer
	 * @param name       - String
	 * @param value      - Double
	 * @param brand      - String
	 * @param model      - String
	 * @param quantity   - Integer
	 * @param category   - String
	 * @param provider   - Provider
	 */
	public Product(Integer id, Integer idProvider, String name, Double value, String brand, String model,
			Integer quantity, String category, Provider provider) {
		this.id = id;
		this.idProvider = idProvider;
		this.name = name;
		this.value = value;
		this.brand = brand;
		this.model = model;
		this.quantity = quantity;
		this.category = category;
		this.provider = provider;
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
	 * M�todo que retorna o Id do Fornecedor.
	 * 
	 * @return - Integer
	 */
	public Integer getIdProvider() {
		return idProvider;
	}

	/**
	 * M�todo que seta o valor do Id do Fornecedor.
	 * 
	 * @param idProvider - Integer
	 */
	public void setIdProvider(Integer idProvider) {
		this.idProvider = idProvider;
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
	 * M�todo que retorna o Valor.
	 * 
	 * @return Double
	 */
	public Double getValue() {
		return value;
	}

	/**
	 * M�todo que seta o Valor.
	 * 
	 * @param value - Double
	 */
	public void setValue(Double value) {
		this.value = value;
	}

	/**
	 * M�todo que retorna a Marca.
	 * 
	 * @return String
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * M�todo que seta o valor da Marca.
	 * 
	 * @param brand - String
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * Metodo que retorna o Modelo.
	 * 
	 * @return String
	 */
	public String getModel() {
		return model;
	}

	/**
	 * M�todo que seta o valor do Modelo.
	 * 
	 * @param model - String
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * M�todo que retorna a Quantidade.
	 * 
	 * @return Integer
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * M�todo que seta o valor da Quantidade.
	 * 
	 * @param quantity - Integer
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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
	 * M�todo que retorna um objeto do tipo Fornecedor.
	 * 
	 * @return Provider
	 */
	public Provider getProvider() {
		return provider;
	}

	/**
	 * M�todo que seta o valor do objeto.
	 * 
	 * @param provider - Provider
	 */
	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	/**
	 * M�todo sobrescrito.
	 * 
	 * @return String - Cadeia de caracteres.
	 */
	@Override
	public String toString() {
		return "PRODUTO\n\n ID:\t" + id + "\n ID FORNECEDOR:\t" + idProvider + "\n NOME:\t" + name + "\n VALOR:\t"
				+ value + "\n MARCA:\t" + brand + "\n MODELO:\t" + model + "\n QUANTIDADE:\t" + quantity
				+ "\n CATEGORIA:\t" + category + "";
	}
}
