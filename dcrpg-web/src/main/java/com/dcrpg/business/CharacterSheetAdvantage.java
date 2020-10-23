package com.dcrpg.business;

public class CharacterSheetAdvantage 
{
	private int id;
	private CharacterSheet sheet;
	private Advantage adv;
	private String description;
	
	public CharacterSheetAdvantage()
	{
		
	}
	
	public CharacterSheetAdvantage(int id)
	{
		this.id = id;
	}
	
	public CharacterSheetAdvantage(int id, CharacterSheet sheet, Advantage adv)
	{
		this.id = id;
		this.sheet = sheet;
		this.adv = adv;
	}
	public CharacterSheetAdvantage(int id, CharacterSheet sheet, Advantage adv, String description)
	{
		this.id = id;
		this.sheet = sheet;
		this.adv = adv;
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

	public Advantage getAdv() {
		return adv;
	}
	
	public String getAdvStr()
	{
		return adv.getName();
	}

	public void setAdv(Advantage adv) {
		this.adv = adv;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
