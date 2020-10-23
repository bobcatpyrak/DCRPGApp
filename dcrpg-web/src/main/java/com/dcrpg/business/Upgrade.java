package com.dcrpg.business;

public class Upgrade 
{
	private int id;
	private int itemId;
	private String description;
	private String stat;
	private int statBonus;
	
	public Upgrade()
	{
		
	}

	public Upgrade(int id, int itemId, String description, String stat, int statBonus) {
		super();
		this.id = id;
		this.itemId = itemId;
		this.description = description;
		this.stat = stat;
		this.statBonus = statBonus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public int getStatBonus() {
		return statBonus;
	}

	public void setStatBonus(int statBonus) {
		this.statBonus = statBonus;
	}
	
	
}
