package model.dao;

import java.util.List;

import model.entities.Provider;

public interface ProviderDao {
	/**
	 * Interface com m�todos para manipula��o de dados do Fornecedor.
	 */

	void insert(Provider obj);

	void update(Provider obj);

	Provider findByCpf(String cpf);

	List<Provider> findAll();

	Provider findById(Integer id);
}
