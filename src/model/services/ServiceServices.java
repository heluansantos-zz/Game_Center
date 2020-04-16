package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.ServiceDao;
import model.entities.Service;

public class ServiceServices {

	private ServiceDao dao = DaoFactory.createServiceDao();

	public List<Service> findAll() {
		return dao.findAll();
	}

	public void saveOrUpdate(Service obj) {
		if (obj.getId() == null) {
			dao.insert(obj);
		} else {
			dao.update(obj);
		}

	}

	public Service search(String search) {
		return dao.findBySearch(search);
	}
	
	public void delete(Integer id) {
		dao.deleteById(id);
	}
}
