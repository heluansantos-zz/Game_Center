package model.dao;

import java.util.List;

import model.entities.ServiceSales;

public interface SalesServiceDao {
	/**
	 * Interface com métodos para manipulação de dados de vendas de Serviços.
	 */

	void insert(ServiceSales obj);

	void update(ServiceSales obj);

	List<ServiceSales> findAll();

	Integer deleteById(Integer id);

	ServiceSales findById(Integer id);

	List<ServiceSales> findReportsSalesService();

}
