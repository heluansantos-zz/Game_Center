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
import gui.util.Alerts;
import javafx.scene.control.Alert.AlertType;
import model.dao.ProductDao;
import model.entities.Product;

public class ProductDaoJDBC implements ProductDao {
	/**
	 * Classe contendo operações de manipulação de dados de Produtos.
	 * 
	 * @author Adriano Queiroz
	 */

	private Connection conn;

	/**
	 * Contrutor com argumento.
	 * 
	 * @author Adriano Queiroz
	 * @param conn - Connection
	 */
	public ProductDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	/**
	 * Método de inserção.
	 * 
	 * @author Adriano Queiroz
	 * @param obj - Product
	 */
	@Override
	public void insert(Product obj) {
		PreparedStatement st = null;

		try {
			st = conn.prepareStatement(
					"INSERT INTO Product " + "(name, brand, model, quantity, value,id_provider,category) " + "VALUES "
							+ "(?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getName());
			st.setString(2, obj.getBrand());
			st.setString(3, obj.getModel());
			st.setInt(4, obj.getQuantity());
			st.setDouble(5, obj.getValue());
			st.setInt(6, obj.getIdProvider());
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
	 * @param obj - Product
	 */
	@Override
	public void update(Product obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE Product "
					+ "SET name = ?, brand = ?, model = ?, quantity = ?, value = ?, id_provider = ?, category = ? "
					+ "WHERE idproduct = ? ");
			st.setString(1, obj.getName());
			st.setString(2, obj.getBrand());
			st.setString(3, obj.getModel());
			st.setInt(4, obj.getQuantity());
			st.setDouble(5, obj.getValue());
			st.setInt(6, obj.getIdProvider());
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
	 * Métodoque retorna um objeto com base em uma informação passada como
	 * argumento.
	 * 
	 * @author Adriano Queiroz
	 * @param name - String
	 * @return Product - Objeto
	 */
	@Override
	public Product findByName(String name) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM eletrogames.product " + "WHERE name = ?");
			st.setString(1, name);
			rs = st.executeQuery();
			if (rs.next()) {
				Product obj = instatiateProduct(rs);
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
	 * @return Product - Objeto
	 * @throws SQLException
	 */
	private Product instatiateProduct(ResultSet rs) throws SQLException {
		Product obj = new Product();
		obj.setId(rs.getInt("idproduct"));
		obj.setName(rs.getString("name"));
		obj.setBrand(rs.getString("brand"));
		obj.setModel(rs.getString("model"));
		obj.setQuantity(rs.getInt("quantity"));
		obj.setValue(rs.getDouble("value"));
		obj.setIdProvider(rs.getInt("id_provider"));
		obj.setCategory(rs.getString("category"));
		return obj;
	}

	/**
	 * Método que retorna todos os objetos do tipo Product do Banco de Dados.
	 * 
	 * @author Adriano Queiroz
	 * @return List<Product> - Lista de objetos
	 */
	@Override
	public List<Product> findAll() {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("SELECT * FROM eletrogames.product");
			rs = st.executeQuery();

			List<Product> list = new ArrayList<>();

			while (rs.next()) {
				Product obj = new Product();
				obj.setId(rs.getInt("idproduct"));
				obj.setIdProvider(rs.getInt("id_provider"));
				obj.setName(rs.getString("name"));
				obj.setBrand(rs.getString("brand"));
				obj.setModel(rs.getString("model"));
				obj.setQuantity(rs.getInt("quantity"));
				obj.setValue(rs.getDouble("value"));
				obj.setCategory(rs.getString("category"));
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
	 * Método que exclui um objeto do Banco de Dados.
	 * 
	 * @author Adriano Queiroz
	 * @param id - Integer
	 */
	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM Product WHERE idproduct = ?");
			st.setInt(1, id);
			int rows = st.executeUpdate();
			if (rows == 0) {
				Alerts.showAlert("Excluir Produto", "Produto não existe!", null, AlertType.ERROR);
			} else {
				Alerts.showAlert("Excluir Produto", "Produto excluído com sucesso!", null, AlertType.CONFIRMATION);
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	/**
	 * Método que atualiza uma informação de um objeto de um determinado tipo.
	 * 
	 * @author Adriano Queiroz
	 * @param obj - Product
	 */
	@Override
	public void updateQuantity(Product obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE Product "
					+ "SET name = ?, brand = ?, model = ?, quantity = ?, value = ?, id_provider = ?, category = ? "
					+ "WHERE idproduct = ? ");
			st.setString(1, obj.getName());
			st.setString(2, obj.getBrand());
			st.setString(3, obj.getModel());
			st.setInt(4, obj.getQuantity() - 1);
			st.setDouble(5, obj.getValue());
			st.setInt(6, obj.getIdProvider());
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
	 * Método que retorna um objeto baseado em uma informação passada como
	 * argumento.
	 * 
	 * @author Adriano Queiroz
	 * @param id - Integer
	 * @return Product - Objeto
	 */
	@Override
	public Product findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM eletrogames.product " + "WHERE idproduct = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Product obj = instatiateProduct(rs);
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
