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
import model.dao.ClientDao;
import model.entities.Client;

public class ClientDaoJDBC implements ClientDao {
	/**
	 * Classe contendo operações de manipulação de dados do cliente.
	 * 
	 * @author Adriano Queiroz
	 */
	
	private Connection conn;

	/**
	 * Construtor com argumento.
	 * 
	 * @author Adriano Queiroz
	 * @param conn - Connection
	 */
	public ClientDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	/**
	 * Método de inserção de dados no banco de dados.
	 * 
	 * @author Adriano Queiroz
	 * @param obj - Client
	 */
	@Override
	public void insert(Client obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO Client " + "(name, adress, telephone, cpf) " + "VALUES " + "(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getName());
			st.setString(2, obj.getAdress());
			st.setString(3, obj.getTelephone());
			st.setString(4, obj.getCpf());
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
	 * Método de atualização dos dados.
	 * 
	 * @author Adriano Queiroz
	 * @param obj - Client
	 */
	@Override
	public void update(Client obj) {

		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE Client " + "SET name = ?, adress = ?, telephone = ?, cpf = ? " + "WHERE idclient = ? ");
			st.setString(1, obj.getName());
			st.setString(2, obj.getAdress());
			st.setString(3, obj.getTelephone());
			st.setString(4, obj.getCpf());
			st.setInt(5, obj.getId());

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	/**
	 * Método que retorna um objeto de um determinado tipo com base em uma
	 * informação passada como argumento.
	 * 
	 * @author Adriano Queiroz
	 * @param cpf - String
	 * @return Client - Objeto
	 */
	@Override
	public Client findByCpf(String cpf) {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT * FROM eletrogames.client " + "WHERE cpf = ?");
			st.setString(1, cpf);
			rs = st.executeQuery();
			if (rs.next()) {
				Client obj = instantiateClient(rs);
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
	 * @return Client - Objeto
	 * @throws SQLException
	 */
	private Client instantiateClient(ResultSet rs) throws SQLException {
		Client obj = new Client();
		obj.setId(rs.getInt("idclient"));
		obj.setName(rs.getString("name"));
		obj.setAdress(rs.getString("adress"));
		obj.setTelephone(rs.getString("telephone"));
		obj.setCpf(rs.getString("cpf"));

		return obj;
	}

	/**
	 * Método que retorna todos os objetos de um determinado tipo.
	 * 
	 * @author Adriano Queiroz
	 * @return List<Client> - Lista de objetos
	 */
	@Override
	public List<Client> findAll() {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("SELECT * FROM eletrogames.client");
			rs = st.executeQuery();

			List<Client> list = new ArrayList<>();

			while (rs.next()) {
				Client obj = new Client();
				obj.setId(rs.getInt("idclient"));
				obj.setName(rs.getString("name"));
				obj.setAdress(rs.getString("adress"));
				obj.setTelephone(rs.getString("telephone"));
				obj.setCpf(rs.getString("cpf"));
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
	 * Método que retorna um objeto com base em uma informação passada como
	 * argumento.
	 * 
	 * @author Adriano Queiroz
	 * @param id - Integer
	 * @return Client - Objeto refente a informação fornecida
	 */
	@Override
	public Client findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT * FROM eletrogames.client " + "WHERE idclient = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Client obj = instantiateClient(rs);
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
}
