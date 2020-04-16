package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.ProviderDao;
import model.entities.Provider;

public class ProviderServices {

	private ProviderDao dao = DaoFactory.createProviderDao();
	
	public List<Provider> findAll(){
		return dao.findAll();
	}
	
	public void saverOrUpdate(Provider obj) {
		if(obj.getId() == null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
	public Provider search(String cpf) {
		return dao.findByCpf(cpf);
	}
	
	public Provider searchId(Integer id) {
		return dao.findById(id);
	}
}
