package business;

public class CharacterSheetDisadvantage 
{
	int id;
	int characterSheetId;
	int disadvantageId;
	String description;
	
	public CharacterSheetDisadvantage()
	{
		
	}
	
	public CharacterSheetDisadvantage(int id)
	{
		this.id = id;
	}
	
	public CharacterSheetDisadvantage(int id, int characterSheetId, int disadvantageId)
	{
		this.id = id;
		this.characterSheetId = characterSheetId;
		this.disadvantageId = disadvantageId;
	}
	public CharacterSheetDisadvantage(int id, int characterSheetId, int disadvantageId, String description)
	{
		this.id = id;
		this.characterSheetId = characterSheetId;
		this.disadvantageId = disadvantageId;
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

	public int getDisadvantageId() {
		return disadvantageId;
	}

	public void setDisadvantageId(int disadvantageId) {
		this.disadvantageId = disadvantageId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
