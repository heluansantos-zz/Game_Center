package model.dao;

import java.util.List;

import model.entities.ServiceSales;

public interface SalesServiceDao {
	/**
	 * Interface com m�todos para manipula��o de dados de vendas de Servi�os.
	 */

	void insert(ServiceSales obj);

	void update(ServiceSales obj);

	List<ServiceSales> findAll();

	Integer deleteById(Integer id);

	ServiceSales findById(Integer id);

	List<ServiceSales> findReportsSalesService();

}
