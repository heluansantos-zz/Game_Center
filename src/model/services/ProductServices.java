package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entities.Product;

public class ProductServices {
	/**
	 * Classe com métodos referentes aos serviços da classe Produto.
	 * 
	 * @author Adriano Queiroz
	 * @version 1.0
	 */
	private ProductDao dao = DaoFactory.createProductDao();

	/**
	 * Método que invoca outro método retornando uma lista de objetos.
	 * 
	 * @return List<Product>
	 */
	public List<Product> findAll() {
		return dao.findAll();
	}

	/**
	 * Método que com base em uma condição, salva ou atualiza um objeto.
	 * 
	 * @param obj - Product
	 */
	public void saverOrUpdate(Product obj) {
		if (obj.getId() == null) {
			dao.insert(obj);
		} else {
			dao.update(obj);
		}
	}

	/**
	 * Método que invoca outro método retornando um objeto.
	 * 
	 * @param name - String
	 * @return Product
	 */
	public Product search(String name) {
		return dao.findByName(name);
	}

	/**
	 * Método que invoca outro método excluindo um objeto.
	 * 
	 * @param id - Integer
	 */
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	/**
	 * método que invoca outro método retornando um objeto.
	 * 
	 * @param id - Integer
	 * @return Product
	 */
	public Product searchId(Integer id) {
		return dao.findById(id);
	}

	/**
	 * Método que invoca outro método atualizando uma informação de um objeto.
	 * 
	 * @param obj - Product
	 */
	public void updateProductQuantity(Product obj) {
		dao.updateQuantity(obj);
	}
}
