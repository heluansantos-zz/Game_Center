package model.services;

import java.util.List;

import model.dao.ClientDao;
import model.dao.DaoFactory;
import model.entities.Client;

public class ClientServices {
	/**
	 * Classe com m�todos referentes aos servi�os da classe Cliente.
	 * 
	 * @author Adriano Queiroz
	 * @version 1.0
	 */
	private ClientDao dao = DaoFactory.createClientDao();

	/**
	 * M�todo que chama outro m�todo retornando um lista de objetos.
	 * 
	 * @return List<Client>
	 */
	public List<Client> findAll() {
		return dao.findAll();
	}

	/**
	 * M�todo que com base em uma condi��o, salva ou atualiza dados de um
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
	 * M�todo de pesquisa tendo cpf como argumento.
	 * 
	 * @param cpf - String
	 * @return Client - Objeto
	 */
	public Client search(String cpf) {
		return dao.findByCpf(cpf);
	}

	/**
	 * M�todo de pesquisa tendo como argumento o id.
	 * 
	 * @param id - Integer
	 * @return Client
	 */
	public Client search(Integer id) {
		return dao.findById(id);
	}
}
