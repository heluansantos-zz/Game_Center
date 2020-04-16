package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SalesDao;
import model.entities.Sales;

public class SalesService {

	private SalesDao dao = DaoFactory.createSalesDao();

	public List<Sales> findAll() {
		return dao.findAll();
	}

	public void saveOrUpdate(Sales obj) {
		if (obj.getId() == null) {
			dao.insert(obj);
		} else {
			dao.update(obj);
		}
	}

	public Sales search(Integer id) {
		return dao.findById(id);
	}

	public void delete(Integer id) {
		dao.deleteById(id);
	}

	public List<Sales> reports() {
		return dao.findByReports();
	}

}
