package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entities.Product;

public class ProductServices {
	/**
	 * Classe com m�todos referentes aos servi�os da classe Produto.
	 * 
	 * @author Adriano Queiroz
	 * @version 1.0
	 */
	private ProductDao dao = DaoFactory.createProductDao();

	/**
	 * M�todo que invoca outro m�todo retornando uma lista de objetos.
	 * 
	 * @return List<Product>
	 */
	public List<Product> findAll() {
		return dao.findAll();
	}

	/**
	 * M�todo que com base em uma condi��o, salva ou atualiza um objeto.
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
	 * M�todo que invoca outro m�todo retornando um objeto.
	 * 
	 * @param name - String
	 * @return Product
	 */
	public Product search(String name) {
		return dao.findByName(name);
	}

	/**
	 * M�todo que invoca outro m�todo excluindo um objeto.
	 * 
	 * @param id - Integer
	 */
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	/**
	 * m�todo que invoca outro m�todo retornando um objeto.
	 * 
	 * @param id - Integer
	 * @return Product
	 */
	public Product searchId(Integer id) {
		return dao.findById(id);
	}

	/**
	 * M�todo que invoca outro m�todo atualizando uma informa��o de um objeto.
	 * 
	 * @param obj - Product
	 */
	public void updateProductQuantity(Product obj) {
		dao.updateQuantity(obj);
	}
}
