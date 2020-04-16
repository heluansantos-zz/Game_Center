package model.dao;

import java.util.List;

import model.entities.Service;

public interface ServiceDao {
	/**
	 * Interface com m�todos para manipula��o de dados do registro de Servi�os.
	 */

	void insert(Service obj);

	void update(Service obj);

	Service findBySearch(String search);

	public List<Service> findAll();

	void deleteById(Integer id);

}
