package model.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import db.DB;
import db.DbException;
import gui.util.Alerts;
import javafx.scene.control.Alert.AlertType;
import model.dao.ServiceDao;
import model.entities.Service;

public class ServiceDaoJDBC implements ServiceDao {
	/**
	 * Classe que implementa operações referente ao Serviço.
	 * 
	 * @author Adriano Queiroz
	 * @version 1.0
	 */
	private Connection conn;

	/**
	 * Construtor com argumento.
	 * 
	 * @author Adriano Queiroz
	 * @param conn - Connection
	 */
	public ServiceDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	/**
	 * Método de inserção.
	 * 
	 * @author Adriano Queiroz
	 * @param obj - Service
	 */
	@Override
	public void insert(Service obj) {
		java.sql.PreparedStatement st = null;

		try {
			st = conn.prepareStatement("INSERT INTO `eletrogames`.`service`\r\n"
					+ "(`NAME`,`DESCRIPTION`,`STATUS`,`VALUE`,`ID_CLIENT`,`WARRANTY`,`DATE`)VALUES\r\n"
					+ "(?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getName());
			st.setString(2, obj.getProblemDescription());
			st.setString(3, obj.getStatus());
			st.setDouble(4, obj.getValue());
			st.setInt(5, obj.getIdClient());
			st.setString(6, obj.getWarranty());
			st.setString(7, obj.getDate());

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
	 * @param obj - Service
	 */
	@Override
	public void update(Service obj) {

		PreparedStatement st = null;
		try {
			st = (PreparedStatement) conn.prepareStatement("UPDATE `eletrogames`.`service`\r\n"
					+ "SET  `NAME` = ?, `DESCRIPTION` = ?,`STATUS` = ?,`VALUE` = ?,`ID_CLIENT` = ?, `WARRANTY` = ?,`DATE` = ?\r\n"
					+ "WHERE `IDSERVICE` = ? ");
			st.setString(1, obj.getName());
			st.setString(2, obj.getProblemDescription());
			st.setString(3, obj.getStatus());
			st.setDouble(4, obj.getValue());
			st.setInt(5, obj.getIdClient());
			st.setString(6, obj.getWarranty());
			st.setString(7, obj.getDate());
			st.setInt(8, obj.getId());

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	/**
	 * Método que retorna um objeto com base em uma informação passada como
	 * argumento.
	 * 
	 * @author Adriano Queiroz
	 * @param search - String
	 * @return Service - Objeto
	 */
	@Override
	public Service findBySearch(String search) {

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = (PreparedStatement) conn.prepareStatement("SELECT * FROM eletrogames.service " + "WHERE name = ?");
			st.setString(1, search);
			rs = st.executeQuery();
			if (rs.next()) {
				Service obj = instantiateService(rs);
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
	 * Método de instanciação do objeto.
	 * 
	 * @author Adriano Queiroz
	 * @param rs - ResultSet
	 * @return Service - Objeto
	 * @throws SQLException
	 */
	private Service instantiateService(ResultSet rs) throws SQLException {
		Service obj = new Service();
		obj.setId(rs.getInt("idservice"));
		obj.setIdClient(rs.getInt("id_client"));
		obj.setName(rs.getString("name"));
		obj.setProblemDescription(rs.getString("description"));
		obj.setStatus(rs.getString("status"));
		obj.setDate(rs.getString("date"));
		obj.setValue(rs.getDouble("value"));
		obj.setWarranty(rs.getString("warranty"));

		return obj;
	}

	/**
	 * Método que retorna todos os objetos de um um determinado tipo do Banco de
	 * Dados.
	 * 
	 * @author Adriano Queiroz
	 * @return List<Service> - Lista de objetos
	 */
	@Override
	public List<Service> findAll() {

		java.sql.PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT * FROM eletrogames.service");
			rs = st.executeQuery();

			List<Service> list = new ArrayList<>();

			while (rs.next()) {
				Service obj = new Service();
				obj.setId(rs.getInt("idservice"));
				obj.setName(rs.getString("name"));
				obj.setProblemDescription(rs.getString("description"));
				obj.setStatus(rs.getString("status"));
				obj.setValue(rs.getDouble("value"));
				obj.setIdClient(rs.getInt("id_client"));
				obj.setWarranty(rs.getString("warranty"));
				obj.setDate(rs.getString("date"));
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
	 * Método que exclui um objeto do BD.
	 * 
	 * @author Adriano Queiroz
	 * @param id - Integer
	 */
	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = (PreparedStatement) conn.prepareStatement("DELETE FROM Service WHERE idservice = ?");
			st.setInt(1, id);
			int rows = st.executeUpdate();
			if (rows == 0) {
				Alerts.showAlert("Excluir serviço", "Serviço não existe", null, AlertType.ERROR);
			} else {
				Alerts.showAlert("Excluir serviço", "Serviço excluído com sucesso!", null, AlertType.CONFIRMATION);
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}
}
