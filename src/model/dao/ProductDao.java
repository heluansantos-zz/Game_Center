package model.dao;

import java.util.List;

import model.entities.Product;

public interface ProductDao {
	/**
	 * Interface com métodos para manipulação de dados do Produto.
	 */

	void insert(Product obj);

	void update(Product obj);

	Product findByName(String name);

	Product findById(Integer id);

	List<Product> findAll();

	void deleteById(Integer id);

	void updateQuantity(Product obj);
}
