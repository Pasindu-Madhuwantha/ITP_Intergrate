package com.model;

public class NewSupItem {
	
	private int supId;
	private String supItem;
	private int quantity;
	private double price;
	
	public NewSupItem(int supId, String supItem, int quantity, double price) {
		this.supId = supId;
		this.supItem = supItem;
		this.quantity = quantity;
		this.price = price;
	}

	public int getSupId() {
		return supId;
	}

	public String getSupItem() {
		return supItem;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}
	
}
