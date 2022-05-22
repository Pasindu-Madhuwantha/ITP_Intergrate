package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.InventoryDB;
import com.model.Inventory;
/**
 * Servlet implementation class NewInvCtrl
 */
@WebServlet("/NewInvCtrl")
public class NewInvCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private InventoryDB invDB;

    public NewInvCtrl() {
        this.invDB = new InventoryDB();
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String action = request.getServletPath();
		
		switch (action) {
		
		case "/inventoryView":
			try {
				displayInventoryTable(request, response);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ServletException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		
		case "/editInventory":
			try {
				editStockRecords(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "/addInventoryDB":
			try {
				insertInventory(request, response);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			break;
			
		case "/deleteInventory":
			try {
				deleteInventoryRecord(request, response);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
			
		case "/updateInventoryDB":
			try {
				updateInventory(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		default:
			/*try {
				listNewSupplierItems(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			break;
		
		}
		
	}

	
	private void displayInventoryTable(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		
		List<Inventory> displayInventory = invDB.inventoryTable();
		request.setAttribute("displayInventory", displayInventory);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Inventory-controller.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void editStockRecords(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		
		List<Inventory> displayInventory = invDB.inventoryTable();
		request.setAttribute("displayInventory", displayInventory);
		
		int id = Integer.parseInt(request.getParameter("id"));
		Inventory existingInventoryRecord = invDB.getInventoryRecord(id);
		request.setAttribute("inventoryRecord", existingInventoryRecord);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Inventory-controller.jsp");
		dispatcher.forward(request, response);

	}
	
	
	private void updateInventory(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String invType = request.getParameter("invType");
		String description = request.getParameter("description");

		Inventory inventory = new Inventory(id, invType, description);
		invDB.updateInventory(inventory);
		response.sendRedirect("inventoryView");
	}
	
	private void deleteInventoryRecord(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		invDB.deleteInventoryRecord(id);
		response.sendRedirect("inventoryView");

	}
	
	private void insertInventory(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		
		String invType = request.getParameter("invType");
		String description = request.getParameter("description");
		
		Inventory inventory = new Inventory(invType, description);
		invDB.insertInventory(inventory);
		response.sendRedirect("inventoryView");
	}

}
