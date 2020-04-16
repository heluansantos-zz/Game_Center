package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SalesServiceDao;
import model.entities.Client;
import model.entities.Service;
import model.entities.ServiceSales;

public class SalesServiceDaoJDBC implements SalesServiceDao {
	/**
	 * Classe com operações referente a vendas de Serviços.
	 * 
	 * @author Adriano Queiroz
	 * @version 1.0
	 */
	
	private Connection conn;

	/**
	 * Construtor com argumentos.
	 * 
	 * @author Adriano Queiroz
	 * @param conn - Connection
	 */
	public SalesServiceDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	/**
	 * Método de inserção.
	 * 
	 * @author Adriano Queiroz
	 * @param obj - ServiceSales
	 */
	@Override
	public void insert(ServiceSales obj) {
		PreparedStatement st = null;

		try {
			st = conn.prepareStatement("INSERT INTO `eletrogames`.`salesservice`" + "(`DATE`,`ID_CLIENT`,`ID_SERVICE`) "
					+ "VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getDateSale());
			st.setInt(2, obj.getIdClient());
			st.setInt(3, obj.getIdService());

			int rowsAffected = st.executeUpdate();
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Erro! Nenhuma linha afetada!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	/**
	 * Método de atualização.
	 * 
	 * @author Adriano Queiroz
	 * @param obj - ServiceSales
	 */
	@Override
	public void update(ServiceSales obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE `eletrogames`.`salesservice` SET `DATE` = ?,"
					+ "`ID_CLIENT` = ?,`ID_SERVICE` = ? " + "WHERE `IDSALESSERVICE` = ?");
			st.setString(1, obj.getDateSale());
			st.setString(1, obj.getDateSale());
			st.setInt(2, obj.getIdClient());
			st.setInt(3, obj.getIdService());
			st.setInt(4, obj.getId());

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	/**
	 * Método que retorna um objeto de um determinado tipo com base em uma
	 * informação passada como argumneto.
	 * 
	 * @author Adriano Queiroz
	 * @param id - Integer
	 * @return ServiceSales - Objeto
	 */
	@Override
	public ServiceSales findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT * FROM eletrogames.salesservice " + "WHERE idsalesservice = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				ServiceSales obj = instantiateSales(rs);
				return obj;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	/**
	 * Método que retorna todos os objetos de um determinado tipo do Banco de Dados.
	 * 
	 * @author Adriano Queiroz
	 * @return List<ServiceSales> - Lista de objetos
	 */
	@Override
	public List<ServiceSales> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("SELECT * FROM eletrogames.salesservice");
			rs = st.executeQuery();

			List<ServiceSales> list = new ArrayList<>();

			while (rs.next()) {
				ServiceSales obj = new ServiceSales();
				obj.setId(rs.getInt("idsalesservice"));
				obj.setDateSale(rs.getString("date"));
				obj.setIdClient(rs.getInt("id_client"));
				obj.setIdService(rs.getInt("id_service"));
				list.add(obj);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	/**
	 * Método de instanciação do objeto.
	 * 
	 * @author Adriano Queiroz
	 * @param rs - ResultSet
	 * @return ServiceSales - Objeto
	 * @throws SQLException
	 */
	private ServiceSales instantiateSales(ResultSet rs) throws SQLException {
		ServiceSales obj = new ServiceSales();
		obj.setId(rs.getInt("idsalesservice"));
		obj.setDateSale(rs.getString("date"));
		obj.setIdClient(rs.getInt("id_client"));
		obj.setIdService(rs.getInt("id_service"));

		return obj;
	}

	/**
	 * Método que retorna um objeto com base em uma informação passada como
	 * argumento.
	 * 
	 * @author Adriano Queiroz
	 * @param id - Integer
	 * @return Integer - Valor das linhas afetadas
	 */
	@Override
	public Integer deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM Salesservice WHERE idsalesservice = ?");
			st.setInt(1, id);
			int rows = st.executeUpdate();
			return rows;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	/**
	 * Método que retorna todos os objetos com informações específicas, ou seja
	 * filtradas, relevantes.
	 * 
	 * @author Adriano Queiroz
	 * @return List<ServiceSales> - Lista de objetos
	 */
	@Override
	public List<ServiceSales> findReportsSalesService() {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"SELECT SALESSERVICE.IDSALESSERVICE, " + "CLIENT.NAME, CLIENT.TELEPHONE, CLIENT.CPF, "
							+ "SERVICE.NAME, SERVICE.DESCRIPTION, SERVICE.WARRANTY, SALESSERVICE.DATE, SERVICE.VALUE "
							+ " FROM CLIENT " + " INNER JOIN SALESSERVICE " + "ON IDCLIENT = ID_CLIENT "
							+ "INNER JOIN SERVICE " + " ON ID_SERVICE = ID_SERVICE ");
			rs = st.executeQuery();

			List<ServiceSales> list = new ArrayList<>();

			while (rs.next()) {
				Client client = instantiateClient(rs);
				Service service = instantiateServiceReports(rs);
				ServiceSales obj = instantiateSalesReports(rs, client, service);
				list.add(obj);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	/**
	 * Método de instanciação do objeto Client.
	 * 
	 * @author Adriano Queiroz
	 * @param rs - ResultSet
	 * @return Client - Objeto
	 * @throws SQLException
	 */
	private Client instantiateClient(ResultSet rs) throws SQLException {
		Client obj = new Client();
		obj.setName(rs.getString("client.name"));
		obj.setTelephone(rs.getString("client.telephone"));
		obj.setCpf(rs.getString("client.cpf"));

		return obj;
	}

	/**
	 * Método de instanciação do objeto Serviço.
	 * 
	 * @author Adriano Queiroz
	 * @param rs - ResultSet
	 * @return Service - Objeto
	 * @throws SQLException
	 */
	private Service instantiateServiceReports(ResultSet rs) throws SQLException {
		Service obj = new Service();
		obj.setName(rs.getString("service.name"));
		obj.setProblemDescription(rs.getString("service.description"));
		obj.setWarranty(rs.getString("service.warranty"));
		obj.setValue(rs.getDouble("service.value"));

		return obj;
	}

	/**
	 * Método de instanciação do objeto ServiceSales: Relatório.
	 * 
	 * @author Adriano Queiroz
	 * @param rs      - ResultSet
	 * @param client  - Client
	 * @param service - Service
	 * @return ServiceSales - Objeto
	 * @throws SQLException
	 */
	private ServiceSales instantiateSalesReports(ResultSet rs, Client client, Service service) throws SQLException {
		ServiceSales obj = new ServiceSales();
		obj.setId(rs.getInt("salesservice.idsalesservice"));
		obj.setDateSale(rs.getString("salesservice.date"));
		obj.setClient(client);
		obj.setService(service);

		return obj;
	}
}
