package com.dcrpg.business;

public class EquipBonus 
{
	private int id;
	private int equipmentId;
	private String stat;
	private int statBonus;
	
	public EquipBonus()
	{
		
	}
	
	public EquipBonus(int id, int equipmentId, String stat, int statBonus) {
		super();
		this.id = id;
		this.equipmentId = equipmentId;
		this.stat = stat;
		this.statBonus = statBonus;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
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
