package com.dcrpg.business;

public class CharacterSheetPower 
{
	private int id;
	private int characterSheetId;
	private int level;
	private int powerId;
	private int cost;
	private String specializations;
	private String weakness;
	
	public CharacterSheetPower()
	{
		
	}

	public CharacterSheetPower(int id, int characterSheetId, int level, int powerId, int cost, String specializations,
			String weakness) {
		super();
		this.id = id;
		this.characterSheetId = characterSheetId;
		this.level = level;
		this.powerId = powerId;
		this.cost = cost;
		this.specializations = specializations;
		this.weakness = weakness;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCharacterSheetId() {
		return characterSheetId;
	}

	public void setCharacterSheetId(int characterSheetId) {
		this.characterSheetId = characterSheetId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getPowerId() {
		return powerId;
	}

	public void setPowerId(int powerId) {
		this.powerId = powerId;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getSpecializations() {
		return specializations;
	}

	public void setSpecializations(String specializations) {
		this.specializations = specializations;
	}

	public String getWeakness() {
		return weakness;
	}

	public void setWeakness(String weakness) {
		this.weakness = weakness;
	}
	
	
}
