package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SalesServiceDao;
import model.entities.ServiceSales;

public class SalesServiceServices {

	private SalesServiceDao dao = DaoFactory.createSalesServiceDao();
	
	public List<ServiceSales> findAll() {
		return dao.findAll();
	}

	public void saveOrUpdate(ServiceSales obj) {
		if (obj.getId() == null) {
			dao.insert(obj);
		} else {
			dao.update(obj);
		}
	}

	public ServiceSales search(Integer id) {
		return dao.findById(id);
	}
	
	public void delete(Integer id) {
		dao.deleteById(id);
	}
}
