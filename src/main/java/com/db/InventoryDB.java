package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.model.Inventory;
import com.model.Stock;

public class InventoryDB {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/auto_mart";
	private String jdbcUsername = "root";
	private String jdbcPsw = "root";
	
	private static String SELECT_ALL_INVENTORY = "SELECT * FROM inventory";
	private static String SELECT_INVENTORY_RECORD_BY_ID = "SELECT * FROM inventory WHERE inventory_id=?";
	private static String UPDATE_STOCK_RECORD_BY_ID = "UPDATE inventory SET inventory_type=?, inventory_description=? WHERE inventory_id=?";
	private static String DELETE_INVENTORY_RECORD_BY_ID = "DELETE FROM inventory WHERE inventory_id=?";
	private static String INSERT_INVENTORY = "INSERT INTO inventory(inventory_type, inventory_description, inventory_date) VALUES(?, ?, ?)";
	
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
	
	// Select Inventory Table
		public List<Inventory> inventoryTable() {
			
			ArrayList<Inventory> tempInventory = new ArrayList<>();
			
			try (Connection connection = getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_INVENTORY);) {
				System.out.println(preparedStatement);
				
				ResultSet rs = preparedStatement.executeQuery();
				
				while (rs.next()) {
					
					int invId = rs.getInt(1);
					String invType = rs.getString(2);
					String description = rs.getString(3);
					String insertedDate = rs.getString(4);
					
					tempInventory.add(new Inventory(invId, invType, description, insertedDate));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return tempInventory;
			
		}
		
		// Get inventory records
		public Inventory getInventoryRecord(int id) {
			
			Inventory inventoryRecord = null;
			
			try (Connection connection = getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(SELECT_INVENTORY_RECORD_BY_ID);) {
				
				preparedStatement.setInt(1, id);
				System.out.println(preparedStatement);
				
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next()) {
					String invType = rs.getString("inventory_type");
					String description = rs.getString("inventory_description");
					
					inventoryRecord = new Inventory(id, invType, description);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return inventoryRecord;
			
		}
		
		// Update Inventory
		public boolean updateInventory(Inventory inventory) throws SQLException {
			boolean rowUpdated;
			try (Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(UPDATE_STOCK_RECORD_BY_ID);) {
				statement.setString(1, inventory.getInvType());
				statement.setString(2, inventory.getDescription());
				statement.setInt(3, inventory.getInvId());

				rowUpdated = statement.executeUpdate() > 0;
			}
			return rowUpdated;
		}
		
		// Delete Stock
		public boolean deleteInventoryRecord(int id) throws SQLException {
			boolean rowDeleted;
			try (Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(DELETE_INVENTORY_RECORD_BY_ID);) {
				
				statement.setInt(1, id);
				rowDeleted = statement.executeUpdate() > 0;
				
			}
			return rowDeleted;
		}
		
		
		// Insert stocks
		public void insertInventory(Inventory inventory) throws SQLException {
			
			try (Connection connection = getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INVENTORY);) {
				
				Date date = new Date();
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				
				preparedStatement.setString(1, inventory.getInvType());
				preparedStatement.setString(2, inventory.getDescription());
				preparedStatement.setDate(3, sqlDate);
				
				preparedStatement.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	
}
