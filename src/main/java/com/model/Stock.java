package com.model;

public class Stock {
	
	private int stockId;
	private String itemName;
	private int quantity;
	private double price;
	private String itemImage;
	private String description;
	private String stockIn;
	private String stockOut;
	private int stockSupId;
	private int stockInvId;
	
	
	public Stock(int stockId, String itemName, int quantity, double price, String description, int stockInvId) {
		this.stockId = stockId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.price = price;
		this.description = description;
		this.stockInvId = stockInvId;
	}


	public Stock(int stockId, String itemName, int quantity, double price, String itemImage, String description,
			int stockInvId) {
		this.stockId = stockId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.price = price;
		this.itemImage = itemImage;
		this.description = description;
		this.stockInvId = stockInvId;
	}



	public Stock(String itemName, int quantity, double price, String itemImage, String description, int stockSupId, int stockInvId) {
		
		this.itemName = itemName;
		this.quantity = quantity;
		this.price = price;
		this.itemImage = itemImage;
		this.description = description;
		this.stockSupId = stockSupId;
		this.stockInvId = stockInvId;
		
	}
	
	public Stock(int stockId, String itemName, int quantity, double price, String itemImage, String description,
			String stockIn, String stockOut, int stockSupId, int stockInvId) {
		
		this.stockId = stockId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.price = price;
		this.itemImage = itemImage;
		this.description = description;
		this.stockIn = stockIn;
		this.stockOut = stockOut;
		this.stockSupId = stockSupId;
		this.stockInvId = stockInvId;
		
	}

	public int getStockId() {
		return stockId;
	}

	public String getItemName() {
		return itemName;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}

	public String getItemImage() {
		return itemImage;
	}

	public String getDescription() {
		return description;
	}

	public String getStockIn() {
		return stockIn;
	}

	public String getStockOut() {
		return stockOut;
	}

	public int getStockSupId() {
		return stockSupId;
	}

	public int getStockInvId() {
		return stockInvId;
	}
}
