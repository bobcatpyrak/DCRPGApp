package business;

public class SkillSpec 
{	
	int id;
	int characterSheetId;
	String skill;
	String description;
	
	public SkillSpec()
	{
		
	}
	public SkillSpec(int id)
	{
		this.id = id;
	}
	public SkillSpec(int id, int characterSheetId, String skill, String description)
	{
		this.id = id;
		this.characterSheetId = characterSheetId;
		this.skill = skill;
		this.description = description;
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
	
	
}
