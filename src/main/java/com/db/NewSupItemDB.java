package com.db;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.model.NewSupItem;
import com.model.Stock;

public class NewSupItemDB {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/auto_mart";
	private String jdbcUsername = "root";
	private String jdbcPsw = "root";
	
	private static String SELECT_NEWLY_ADDED_ITEMS = "SELECT * FROM supplier WHERE supplier_id NOT IN(SELECT stock_supplier_id FROM stock)";
	private static String SELECT_STOCK_BY_ID = "SELECT supplier_item, quantity, price FROM supplier WHERE supplier_id=?";
	private static String INSERT_STOCK = "INSERT INTO stock(item_name, quantity, price, item_image, stock_description, stock_indate, stock_supplier_id, stock_inventory_id)"
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static String SELECT_ALL_STOCK = "SELECT * FROM stock";
	private static String SELECT_STOCK_RECORD_BY_ID = "SELECT item_name, quantity, price, stock_description, stock_inventory_id FROM stock WHERE stock_id=?";
	private static String UPDATE_STOCK_RECORD_BY_ID = "UPDATE stock SET item_name=?, quantity=?, price=?, item_image=?, stock_description=?, stock_inventory_id=? WHERE stock_id=?";
	private static String DELETE_STOCK_BY_ID = "DELETE FROM stock WHERE stock_id=?";
	
	protected Connection getConnection() {
		
		Connection connection = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPsw);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return connection;
		
	}
	
	// Select All Newly Added Stocks
	public List<NewSupItem> selectAllStocks() {
		
		ArrayList<NewSupItem> tempSup = new ArrayList<>();
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NEWLY_ADDED_ITEMS);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				
				int supId = rs.getInt(1);
				String supItem = rs.getString(4);
				int quantity = rs.getInt(6);
				double price = rs.getDouble(7);
				
				tempSup.add(new NewSupItem(supId, supItem, quantity, price));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tempSup;
		
	}
	
	// Select All Stocks/Show Stock Table
	public List<Stock> showStockTable() {
		
		ArrayList<Stock> tempStock = new ArrayList<>();
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STOCK);) {
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				
				int stockId = rs.getInt(1);
				String itemName = rs.getString(2);
				int quantity = rs.getInt(3);
				double price = rs.getDouble(4);
				String itemImage = rs.getString(5);
				String description = rs.getString(6);
				String stockIn = rs.getString(7);
				String stockOut = rs.getString(8);
				int stockSupId = rs.getInt(9);
				int stockInvId = rs.getInt(10);
				
				tempStock.add(new Stock(stockId, itemName, quantity, price, itemImage, description, stockIn, stockOut, stockSupId, stockInvId));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tempStock;
		
	}
	
	// Select stocks by ID
	public NewSupItem selectStock(int id) {
		
		NewSupItem newSupItem = null;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STOCK_BY_ID);) {
			
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				String supItem = rs.getString("supplier_item");
				int quantity = rs.getInt("quantity");
				double price = rs.getDouble("price");
				
				newSupItem = new NewSupItem(id, supItem, quantity, price);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return newSupItem;
		
	}
	
	// Get stock records
		public Stock getStockRecord(int id) {
			
			Stock stockRecord = null;
			
			try (Connection connection = getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STOCK_RECORD_BY_ID);) {
				
				preparedStatement.setInt(1, id);
				System.out.println(preparedStatement);
				
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next()) {
					String itemName = rs.getString("item_name");
					int quantity = rs.getInt("quantity");
					double price = rs.getDouble("price");
					String description = rs.getString("stock_description");
					int invId = rs.getInt("stock_inventory_id");
					
					stockRecord = new Stock(id, itemName, quantity, price, description, invId);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return stockRecord;
			
		}
	
	// Insert stocks
	public void insertStock(Stock stock) throws SQLException {
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STOCK);) {
			
			Date date = new Date();
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			
			preparedStatement.setString(1, stock.getItemName());
			preparedStatement.setInt(2, stock.getQuantity());
			preparedStatement.setDouble(3, stock.getPrice());
			preparedStatement.setString(4, stock.getItemImage());
			preparedStatement.setString(5, stock.getDescription());
			preparedStatement.setDate(6, sqlDate);
			preparedStatement.setInt(7, stock.getStockSupId());
			preparedStatement.setInt(8, stock.getStockInvId());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//"UPDATE stock SET item_name=?, quantity=?, price=?, item_image=?, stock_description=?, stock_inventory_id=? WHERE stock_id=?"
	
	// Update Stock
	public boolean updateStock(Stock stock) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_STOCK_RECORD_BY_ID);) {
			statement.setString(1, stock.getItemName());
			statement.setInt(2, stock.getQuantity());
			statement.setDouble(3, stock.getPrice());
			statement.setString(4, stock.getItemImage());
			statement.setString(5, stock.getDescription());
			statement.setInt(6, stock.getStockInvId());
			statement.setInt(7, stock.getStockId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	// Delete Stock
	public boolean deleteStock(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_STOCK_BY_ID);) {
			
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
			
		}
		return rowDeleted;
	}
	
}
