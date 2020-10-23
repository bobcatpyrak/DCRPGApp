package com.dcrpg.business;

public class Location 
{
	private int id;
	private String name;
	private CharacterSheet sheet;
	private Location locale;
	
	public Location()
	{
		
	}
	
	public Location(int id, String name, Location locale) {
		super();
		this.id = id;
		this.name = name;
		sheet = null;
		this.locale = locale;
	}
	public Location(int id, String name, CharacterSheet sheet, Location locale) {
		super();
		this.id = id;
		this.name = name;
		this.sheet = sheet;
		this.locale = locale;
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

	public CharacterSheet getSheet() {
		return sheet;
	}

	public void setSheet(CharacterSheet sheet) {
		this.sheet = sheet;
	}

	public Location getLocale() {
		return locale;
	}

	public void setLocale(Location locale) {
		this.locale = locale;
	}
	
	
}
