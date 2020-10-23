package com.dcrpg.business;

public class Item 
{
	private int id;
	private String name;
	private String picture; //filepath
	private String description;
	private int slots; //0 = key item
	private int stacking;
	private int quantity;
	
	public Item()
	{
		name = "Unnamed Item";
		stacking = 1;
		quantity = 1;
	}

	public Item(int id, String name, String picture, String description, int slots, int stacking, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.picture = picture;
		this.description = description;
		this.slots = slots;
		this.stacking = stacking;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSlots() {
		return slots;
	}

	public void setSlots(int slots) {
		this.slots = slots;
	}

	public int getStacking() {
		return stacking;
	}

	public void setStacking(int stacking) {
		this.stacking = stacking;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
