package model.dao;

import db.DB;
import model.dao.impl.ClientDaoJDBC;
import model.dao.impl.ProductDaoJDBC;
import model.dao.impl.ProviderDaoJDBC;
import model.dao.impl.RegisterDaoJDBC;
import model.dao.impl.SalesDaoJDBC;
import model.dao.impl.SalesServiceDaoJDBC;
import model.dao.impl.ServiceDaoJDBC;

public class DaoFactory {

	public static RegisterDao createRegisterDao() {
		return new RegisterDaoJDBC(DB.getConnection());
	}

	public static ClientDao createClientDao() {
		return new ClientDaoJDBC(DB.getConnection());
	}

	public static ProductDao createProductDao() {
		return new ProductDaoJDBC(DB.getConnection());
	}

	public static ServiceDao createServiceDao() {
		return new ServiceDaoJDBC(DB.getConnection());
	}

	public static ProviderDao createProviderDao() {
		return new ProviderDaoJDBC(DB.getConnection());
	}

	public static SalesDao createSalesDao() {
		return new SalesDaoJDBC(DB.getConnection());
	}
	public static SalesServiceDao createSalesServiceDao() {
		return new SalesServiceDaoJDBC(DB.getConnection());
	}
}
