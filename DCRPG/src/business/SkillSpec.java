package business;

public class SkillSpec 
{
	private final String FIELD_SEP = "%";
	
	int id;
	int characterSheetId;
	String skill;
	String description;
	int level;
	
	public SkillSpec()
	{
		
	}
	
	public SkillSpec(int id, int characterSheetId, String skill, String description, int level)
	{
		this.id = id;
		this.characterSheetId = characterSheetId;
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
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	
}
