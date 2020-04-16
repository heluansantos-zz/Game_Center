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
import model.dao.RegisterDao;
import model.entities.Register;

public class RegisterDaoJDBC implements RegisterDao {

	/**
	 * Classe respons�vel pelas opera��es referente ao registro e opera��es de
	 * manipula��o do dados do usu�rio no Banco de Dados do sistema da aplica��o.
	 * 
	 * @author Adriano Queiroz
	 */
	private Connection conn;

	/**
	 * Construtor
	 * 
	 * @author Adriano Queiroz
	 * @param conn - Connection
	 */
	public RegisterDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	/**
	 * M�todo padr�o para inser��o de dados no BD.
	 * 
	 * @author Adriano Queiroz
	 * @param obj - Register
	 */
	public void insert(Register obj) {

		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO User " + "(name, email, password) " + "VALUES " + "(?, ?, ?) ",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setString(3, obj.getPassword());

			int rowsAffected = st.executeUpdate();
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Erro inesperado! Nenhuma linha afetada!");

			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	/**
	 * M�todo padr�o para atualiza��o de dados.
	 * 
	 * @author Adriano Queiroz
	 * @param obj - Register
	 */
	public void update(Register obj) {

		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE User " + "SET name = ?, email = ?, password = ? " + "WHERE iduser = ? ");
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setString(3, obj.getPassword());
			st.setInt(4, obj.getId());

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	/**
	 * M�todo que retorna todos os objetos de um determinado tipo do Banco de Dados.
	 * 
	 * @author Adriano Queiroz
	 * @return List<Register> - Lista de objetos do tipo especificado.
	 */
	@Override
	public List<Register> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM eletrogames.user");
			rs = st.executeQuery();

			List<Register> list = new ArrayList<>();

			while (rs.next()) {
				Register obj = new Register();
				obj.setId(rs.getInt("iduser"));
				obj.setName(rs.getString("name"));
				obj.setEmail(rs.getString("email"));
				obj.setPassword(rs.getString("password"));
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
	 * M�todo que retorna um objeto com base em uma informa��o passada como
	 * par�metro.
	 * 
	 * @author Adriano Queiroz
	 * @param email - String
	 * @return Register - Um objeto do tipo Register
	 */
	@Override
	public Register findByEmail(String email) {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT * FROM eletrogames.user " + "WHERE email = ?");
			st.setString(1, email);
			rs = st.executeQuery();
			if (rs.next()) {
				Register obj = instantiateClient(rs);
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
	 * M�todo de instancia��o do objeto.
	 * 
	 * @author Adriano Queiroz
	 * @param rs - ResultSet
	 * @return Register - Objeto
	 * @throws SQLException
	 */
	private Register instantiateClient(ResultSet rs) throws SQLException {
		Register obj = new Register();
		obj.setId(rs.getInt("iduser"));
		obj.setName(rs.getString("name"));
		obj.setEmail(rs.getString("email"));
		obj.setPassword(rs.getString("password"));

		return obj;
	}
}
