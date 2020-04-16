package model.dao;

import java.util.List;

import model.entities.Client;

public interface ClientDao {
	
	void insert(Client obj);

	void update(Client obj);

	Client findByCpf(String cpf);

	List<Client> findAll();

	Client findById(Integer id);
}
