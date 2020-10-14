package business;

public class CharacterSheetAdvantage 
{
	int id;
	int characterSheetId;
	int advantageId;
	String description;
	
	public CharacterSheetAdvantage()
	{
		
	}
	
	public CharacterSheetAdvantage(int id)
	{
		this.id = id;
	}
	
	public CharacterSheetAdvantage(int id, int characterSheetId, int advantageId)
	{
		this.id = id;
		this.characterSheetId = characterSheetId;
		this.advantageId = advantageId;
	}
	public CharacterSheetAdvantage(int id, int characterSheetId, int advantageId, String description)
	{
		this.id = id;
		this.characterSheetId = characterSheetId;
		this.advantageId = advantageId;
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

	public int getAdvantageId() {
		return advantageId;
	}

	public void setAdvantageId(int advantageId) {
		this.advantageId = advantageId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
