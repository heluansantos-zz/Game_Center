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
import model.dao.SalesDao;
import model.entities.Client;
import model.entities.Product;
import model.entities.Sales;

public class SalesDaoJDBC implements SalesDao {
	/**
	 * Classe com operações para manipulação de dados referentes a vendas de
	 * Produtos.
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
	public SalesDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	/**
	 * Método de inserção.
	 * 
	 * @author Adriano Queiroz
	 * @param obj - Sales
	 */
	@Override
	public void insert(Sales obj) {

		PreparedStatement st = null;

		try {
			st = conn.prepareStatement(
					"INSERT INTO `eletrogames`.`sales`" + "(`DATE`,`ID_CLIENT`,`ID_PRODUCT`) " + "VALUES (?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getDateSale());
			st.setInt(2, obj.getIdClient());
			st.setInt(3, obj.getIdProduct());

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
	 * @param obj - Sales
	 */
	@Override
	public void update(Sales obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE `eletrogames`.`sales` SET `DATE` = ?,"
					+ "`ID_CLIENT` = ?,`ID_PRODUCT` = ?," + "WHERE `IDSALES` = ?");
			st.setString(1, obj.getDateSale());
			st.setInt(2, obj.getIdClient());
			st.setInt(3, obj.getIdProduct());
			st.setInt(4, obj.getId());

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	/**
	 * Método que retorna todos os objetos do BD de um determinado tipo.
	 * 
	 * @author Adriano Queiroz
	 * @return List<Sales> - Lista de objetos
	 */
	@Override
	public List<Sales> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("SELECT * FROM eletrogames.sales");
			rs = st.executeQuery();

			List<Sales> list = new ArrayList<>();

			while (rs.next()) {
				Sales obj = new Sales();
				obj.setId(rs.getInt("idsales"));
				obj.setDateSale(rs.getString("date"));
				obj.setIdClient(rs.getInt("id_client"));
				obj.setIdProduct(rs.getInt("id_product"));
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
	 * @return Sales - Objeto
	 */
	@Override
	public Sales findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT * FROM eletrogames.sales " + "WHERE idsales = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Sales obj = instantiateSales(rs);
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
	 * Método de instanciação do objeto Product.
	 * 
	 * @author Adriano Queiroz
	 * @param rs - ResultSet
	 * @return Product - Objeto
	 * @throws SQLException
	 */
	private Product instantiateProduct(ResultSet rs) throws SQLException {
		Product obj = new Product();
		obj.setName(rs.getString("product.name"));
		obj.setValue(rs.getDouble("product.value"));
		obj.setBrand(rs.getString("product.brand"));

		return obj;
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
	 * Método de instanciação do objeto: Relatório.
	 * 
	 * @author Adriano Queiroz
	 * @param rs - ResultSet
	 * @return Sales - Objeto
	 * @throws SQLException
	 */
	private Sales instantiateSalesReports(ResultSet rs, Product product, Client client) throws SQLException {
		Sales obj = new Sales();
		obj.setId(rs.getInt("sales.idsales"));
		obj.setDateSale(rs.getString("sales.date"));
		obj.setProduct(product);
		obj.setClient(client);

		return obj;
	}

	/**
	 * Método de instanciação do objeto.
	 * 
	 * @author Adriano Queiroz
	 * @param rs - ResultSet
	 * @return Sales - Objeto
	 * @throws SQLException
	 */
	private Sales instantiateSales(ResultSet rs) throws SQLException {
		Sales obj = new Sales();
		obj.setId(rs.getInt("idsales"));
		obj.setDateSale(rs.getString("date"));
		obj.setIdClient(rs.getInt("id_client"));
		obj.setIdProduct(rs.getInt("id_product"));

		return obj;
	}

	/**
	 * Método que exclui um objeto do Banco de Dados com base em uma informação
	 * passada como argumento.
	 * 
	 * @author Adriano Queiroz
	 * @param id - Integer
	 * @return Integer - Valor das linhas afetadas.
	 */
	@Override
	public Integer deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM Sales WHERE idsales = ?");
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
	 * Método que retorna uma lista de objetos com informações relevantes, ou seja,
	 * apenas as informações mais importantes para o relatório.
	 * 
	 * @author Adriano Queiroz
	 * @return List<Sales> - Lista de objetos
	 */
	@Override
	public List<Sales> findByReports() {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT  CLIENT.NAME, CLIENT.TELEPHONE, CLIENT.CPF, PRODUCT.NAME, PRODUCT.BRAND,"
					+ "PRODUCT.VALUE,SALES.DATE, SALES.IDSALES " + "FROM CLIENT " + "INNER JOIN SALES "
					+ "ON IDCLIENT = ID_CLIENT " + "INNER JOIN PRODUCT " + "ON IDPRODUCT = ID_PRODUCT ");
			rs = st.executeQuery();

			List<Sales> list = new ArrayList<>();

			while (rs.next()) {
				Product product = instantiateProduct(rs);
				Client client = instantiateClient(rs);
				Sales obj = instantiateSalesReports(rs, product, client);
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
}
