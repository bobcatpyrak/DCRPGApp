package com.dcrpg.business;

public class SocialLink 
{
	private int id;
	private CharacterSheet sheet;
	private int level;
	private String name;
	private String description;
	private String category;
	
	public SocialLink()
	{
		
	}

	public SocialLink(int id, CharacterSheet sheet, int level, String name, String description, String category) {
		super();
		this.id = id;
		this.sheet = sheet;
		this.level = level;
		this.name = name;
		this.description = description;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CharacterSheet getSheet() {
		return sheet;
	}

	public void setSheet(CharacterSheet sheet) {
		this.sheet = sheet;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
