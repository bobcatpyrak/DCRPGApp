package com.dcrpg.business;

public class CharacterSheetDisadvantage 
{
	private int id;
	private CharacterSheet sheet;
	private Disadvantage disadv;
	private String description;
	
	public CharacterSheetDisadvantage()
	{
		
	}
	
	public CharacterSheetDisadvantage(int id)
	{
		this.id = id;
	}
	
	public CharacterSheetDisadvantage(int id, CharacterSheet sheet, Disadvantage disadv)
	{
		this.id = id;
		this.sheet = sheet;
		this.disadv = disadv;
	}
	public CharacterSheetDisadvantage(int id, CharacterSheet sheet, Disadvantage disadv, String description)
	{
		this.id = id;
		this.sheet = sheet;
		this.disadv = disadv;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CharacterSheet getCharacterSheet() {
		return sheet;
	}

	public void setCharacterSheet(CharacterSheet sheet) {
		this.sheet = sheet;
	}

	public Disadvantage getDisadv() {
		return disadv;
	}

	public String getDisadvStr() {
		return disadv.getName();
	}
	public void setDisadv(Disadvantage disadv) {
		this.disadv = disadv;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
