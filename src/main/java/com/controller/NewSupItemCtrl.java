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

import com.db.NewSupItemDB;
import com.model.NewSupItem;
import com.model.Stock;
import com.mysql.fabric.Response;

/**
 * Servlet implementation class NewSupItemCtrl
 */
@WebServlet("/NewSupItemsCtrl")
public class NewSupItemCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private NewSupItemDB newsupitemDB;
	
    public NewSupItemCtrl() {
        this.newsupitemDB = new NewSupItemDB();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String action = request.getServletPath();
		
		switch (action) {
			
		case "/add":
			try {
				autoFillForm(request, response);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ServletException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
			
		case "/addDB":
			try {
				insertStock(request, response);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		
		case "/stockView":
			try {
				displayStockTable(request, response);
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
			
		case "/edit":
			try {
				editStockRecords(request, response);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ServletException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
			
		case "/delete":
			try {
				deleteUser(request, response);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			break;
			
		case "/updateDB":
			try {
				updateStock(request, response);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
			
		case "/newStock":
			try {
				listNewSupplierItems(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		default:
			try {
				redirect(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		
		}
		
	}
	
	private void listNewSupplierItems(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		
		List<NewSupItem> tempSup = newsupitemDB.selectAllStocks();
		request.setAttribute("tempSup", tempSup);
		RequestDispatcher dispatcher = request.getRequestDispatcher("NewStocks.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void displayStockTable(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		
		List<Stock> tempdisplayStock = newsupitemDB.showStockTable();
		request.setAttribute("tempdisplayStock", tempdisplayStock);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Inventory-ctrl-panel.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void autoFillForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		
		List<Stock> tempdisplayStock = newsupitemDB.showStockTable();
		request.setAttribute("tempdisplayStock", tempdisplayStock);
		
		int id = Integer.parseInt(request.getParameter("id"));
		NewSupItem existingStock = newsupitemDB.selectStock(id);
		request.setAttribute("stock", existingStock);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Inventory-ctrl-panel.jsp");
		dispatcher.forward(request, response);
	}
	
	private void editStockRecords(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		
		List<Stock> tempdisplayStock = newsupitemDB.showStockTable();
		request.setAttribute("tempdisplayStock", tempdisplayStock);
		
		int id = Integer.parseInt(request.getParameter("id"));
		Stock existingStockRecord = newsupitemDB.getStockRecord(id);
		request.setAttribute("stockRecord", existingStockRecord);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Inventory-ctrl-panel.jsp");
		dispatcher.forward(request, response);

	}
	
	private void insertStock(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		
		String itemName = request.getParameter("itemName");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		double sellPrice = Double.parseDouble(request.getParameter("sellPrice"));
		String description = request.getParameter("description");
		String itemImage = request.getParameter("itemImage");
		int stockSupId = Integer.parseInt(request.getParameter("id"));
		int stockInvId = Integer.parseInt(request.getParameter("invType"));
		
		Stock newStock = new Stock(itemName, quantity, sellPrice, itemImage, description, stockSupId, stockInvId);
		newsupitemDB.insertStock(newStock);
		response.sendRedirect("stockView");
	}
	
	//UPDATE stock SET item_name=?, quantity=?, price=?, item_image=?, stock_description=?, stock_inventory_id=? WHERE stock_id=?"
	
	private void updateStock(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String itemName = request.getParameter("itemName");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		double sellPrice = Double.parseDouble(request.getParameter("sellPrice"));
		String description = request.getParameter("description");
		String itemImage = request.getParameter("itemImage");
		int stockInvId = Integer.parseInt(request.getParameter("invType"));

		Stock stock = new Stock(id, itemName, quantity, sellPrice, itemImage, description, stockInvId);
		newsupitemDB.updateStock(stock);
		response.sendRedirect("stockView");
	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		newsupitemDB.deleteStock(id);
		response.sendRedirect("stockView");

	}
	
	private void redirect(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		
		/*List<Stock> tempdisplayStock = newsupitemDB.showStockTable();
		request.setAttribute("tempdisplayStock", tempdisplayStock);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Inventory-ctrl-panel.jsp");
		dispatcher.forward(request, response);*/
		response.sendRedirect("stockView");
		
	}

}
