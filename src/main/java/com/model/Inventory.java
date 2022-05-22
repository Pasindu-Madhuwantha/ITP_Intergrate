package com.model;

public class Inventory {
	
	private int invId;
	private String invType;
	private String description;
	private String insertedDate;
	
	public Inventory(String invType, String description) {
		this.invType = invType;
		this.description = description;
	}
	
	public Inventory(int invId, String invType, String description) {
		this.invId = invId;
		this.invType = invType;
		this.description = description;
	}

	public Inventory(int invId, String invType, String description, String insertedDate) {
		this.invId = invId;
		this.invType = invType;
		this.description = description;
		this.insertedDate = insertedDate;
	}

	public int getInvId() {
		return invId;
	}

	public String getInvType() {
		return invType;
	}

	public String getDescription() {
		return description;
	}

	public String getInsertedDate() {
		return insertedDate;
	}
	
}
