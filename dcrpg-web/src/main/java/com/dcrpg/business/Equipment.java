package com.dcrpg.business;

public class Equipment extends Item 
{
	private int id;
	private String equipSlot;
	private String statRequirement;
	private int statRequirementLevel;
	private boolean isEquipped;
	
	public Equipment() 
	{
		super();
		isEquipped = false;
		
	}

	public Equipment(int id, String name, String picture, String description, int slots, int stacking, int quantity) 
	{
		super(id, name, picture, description, slots, stacking, quantity);
	}

	public Equipment(int id, String equipSlot, String statRequirement, int statRequirementLevel, boolean isEquipped) {
		super();
		this.id = id;
		this.equipSlot = equipSlot;
		this.statRequirement = statRequirement;
		this.statRequirementLevel = statRequirementLevel;
		this.isEquipped = isEquipped;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEquipSlot() {
		return equipSlot;
	}

	public void setEquipSlot(String equipSlot) {
		this.equipSlot = equipSlot;
	}

	public String getStatRequirement() {
		return statRequirement;
	}

	public void setStatRequirement(String statRequirement) {
		this.statRequirement = statRequirement;
	}

	public int getStatRequirementLevel() {
		return statRequirementLevel;
	}

	public void setStatRequirementLevel(int statRequirementLevel) {
		this.statRequirementLevel = statRequirementLevel;
	}

	public boolean isEquipped() {
		return isEquipped;
	}

	public void setEquipped(boolean isEquipped) {
		this.isEquipped = isEquipped;
	}
	
	

}
