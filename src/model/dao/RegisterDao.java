package model.dao;

import java.util.List;

import model.entities.Register;

public interface RegisterDao {
	/**
	 * Interface com métodos para manipulação de dados do Cadastro.
	 */

	void insert(Register obj);

	void update(Register obj);

	List<Register> findAll();

	Register findByEmail(String email);

}
