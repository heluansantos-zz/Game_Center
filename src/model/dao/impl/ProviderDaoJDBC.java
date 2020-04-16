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
import model.dao.ProviderDao;
import model.entities.Provider;

public class ProviderDaoJDBC implements ProviderDao {
	/**
	 * Classe contendo operações de manipulação de dados de Fornecedor.
	 * 
	 * @author Adriano Queiroz
	 */
	private Connection conn;

	/**
	 * Construtor com argumento.
	 * 
	 * @param conn - Connection
	 */
	public ProviderDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	/**
	 * Método de inserção.
	 * 
	 * @author Adriano Queiroz
	 * @param obj - Provider
	 */
	@Override
	public void insert(Provider obj) {
		PreparedStatement st = null;

		try {
			st = conn.prepareStatement(
					"INSERT INTO `eletrogames`.`provider`(`Name`,`Adress`,`Telephone`,`Email`,`Cnpj`,`Cpf`,`Category`) "
							+ "VALUES " + "(?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getName());
			st.setString(2, obj.getAdress());
			st.setString(3, obj.getTelephone());
			st.setString(4, obj.getEmail());
			st.setString(5, obj.getCnpj());
			st.setString(6, obj.getCpf());
			st.setString(7, obj.getCategory());

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
	 * @param obj - Provider
	 */
	@Override
	public void update(Provider obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE `eletrogames`.`provider`SET `Name` = ?,`Adress` = ?,`Telephone` = ?,`Email` = ?,`Cnpj` = ?,`Cpf` = ?, `Category` = ? "
							+ "WHERE `IdProvider` = ? ");
			st.setString(1, obj.getName());
			st.setString(2, obj.getAdress());
			st.setString(3, obj.getTelephone());
			st.setString(4, obj.getEmail());
			st.setString(5, obj.getCnpj());
			st.setString(6, obj.getCpf());
			st.setString(7, obj.getCategory());
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
	 * @param cpf - String
	 * @return Provider - Objeto
	 */
	@Override
	public Provider findByCpf(String cpf) {

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM eletrogames.provider " + "WHERE Cpf = ?");
			st.setString(1, cpf);
			rs = st.executeQuery();
			if (rs.next()) {
				Provider obj = instantiateProvider(rs);
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
	 * @return Provider - Objeto
	 * @throws SQLException
	 */
	private Provider instantiateProvider(ResultSet rs) throws SQLException {
		Provider obj = new Provider();
		obj.setId(rs.getInt("IdProvider"));
		obj.setName(rs.getString("Name"));
		obj.setAdress(rs.getString("Adress"));
		obj.setTelephone(rs.getString("Telephone"));
		obj.setEmail(rs.getString("Email"));
		obj.setCnpj(rs.getString("Cnpj"));
		obj.setCpf(rs.getString("Cpf"));
		obj.setCategory(rs.getString("Category"));

		return obj;
	}

	/**
	 * Método que retorna todos os objetos de um determinado tipo presentes no Banco
	 * de dados.
	 * 
	 * @author Adriano Queiroz
	 * @return List<Provider> - Lista de Objetos
	 */
	@Override
	public List<Provider> findAll() {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("SELECT * FROM provider");
			rs = st.executeQuery();

			List<Provider> list = new ArrayList<>();

			while (rs.next()) {
				Provider obj = new Provider();
				obj.setId(rs.getInt("IdProvider"));
				obj.setName(rs.getString("Name"));
				obj.setAdress(rs.getString("Adress"));
				obj.setTelephone(rs.getString("Telephone"));
				obj.setEmail(rs.getString("Email"));
				obj.setCnpj(rs.getString("Cnpj"));
				obj.setCpf(rs.getString("Cpf"));
				obj.setCategory(rs.getString("Category"));
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
	 * @return Provider - Objeto
	 */
	@Override
	public Provider findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM eletrogames.provider " + "WHERE idprovider = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Provider obj = instantiateProvider(rs);
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
