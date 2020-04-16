package model.services;

import java.util.List;

import model.dao.ClientDao;
import model.dao.DaoFactory;
import model.entities.Client;

public class ClientServices {
	/**
	 * Classe com métodos referentes aos serviços da classe Cliente.
	 * 
	 * @author Adriano Queiroz
	 * @version 1.0
	 */
	private ClientDao dao = DaoFactory.createClientDao();

	/**
	 * Método que chama outro método retornando um lista de objetos.
	 * 
	 * @return List<Client>
	 */
	public List<Client> findAll() {
		return dao.findAll();
	}

	/**
	 * Método que com base em uma condição, salva ou atualiza dados de um
	 * determinado objeto.
	 * 
	 * @param obj - Client
	 */
	public void saverOrUpdate(Client obj) {
		if (obj.getId() == null) {
			dao.insert(obj);
		} else {
			dao.update(obj);
		}
	}

	/**
	 * Método de pesquisa tendo cpf como argumento.
	 * 
	 * @param cpf - String
	 * @return Client - Objeto
	 */
	public Client search(String cpf) {
		return dao.findByCpf(cpf);
	}

	/**
	 * Método de pesquisa tendo como argumento o id.
	 * 
	 * @param id - Integer
	 * @return Client
	 */
	public Client search(Integer id) {
		return dao.findById(id);
	}
}
