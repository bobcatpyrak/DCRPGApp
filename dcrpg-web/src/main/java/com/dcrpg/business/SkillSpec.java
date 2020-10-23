package com.dcrpg.business;

public class SkillSpec 
{
	//private final String FIELD_SEP = "%";
	
	private int id;
	private CharacterSheet sheet;
	private String skill;
	private String description;
	private int level;
	
	public SkillSpec()
	{
		
	}
	public SkillSpec(int id)
	{
		this.id = id;
	}
	public SkillSpec(int id, CharacterSheet sheet, String skill, String description, int level)
	{
		this.id = id;
		this.sheet = sheet;
		this.skill = skill;
		this.description = description;
		this.level = level;
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
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	
}
