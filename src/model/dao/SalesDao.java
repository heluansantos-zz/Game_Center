package model.dao;

import java.util.List;

import model.entities.Sales;

public interface SalesDao {
	/**
	 * Interface com m�todos para manipula��o de dados das Vendas de Produtos.
	 */
	void insert(Sales obj);

	void update(Sales obj);

	List<Sales> findAll();

	Integer deleteById(Integer id);

	Sales findById(Integer id);

	List<Sales> findByReports();
}
