package business;
import library.*;

public class CharacterSheetDisadvantage implements Comparable
{
	int id;
	int characterSheetId;
	Disadvantage disadv;
	String description;
	
	public CharacterSheetDisadvantage()
	{
		
	}
	
	public CharacterSheetDisadvantage(int id)
	{
		this.id = id;
	}
	
	public CharacterSheetDisadvantage(int id, int characterSheetId, Disadvantage disadv)
	{
		this.id = id;
		this.characterSheetId = characterSheetId;
		this.disadv = disadv;
	}
	public CharacterSheetDisadvantage(int id, int characterSheetId, Disadvantage disadv, String description)
	{
		this.id = id;
		this.characterSheetId = characterSheetId;
		this.disadv = disadv;
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

	public Disadvantage getDisadv() {
		return disadv;
	}

	public String getDisadvStr() {
		return disadv.name();
	}
	public void setDisadv(Disadvantage disadv) {
		this.disadv = disadv;
	}
	public void setDisadv(String str) {
		this.disadv = Disadvantage.valueOf(str);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int compareTo(Object csd2) 
	{
		return disadv.nameD().compareTo(((CharacterSheetDisadvantage) csd2).getDisadv().nameD());
	}
	
	
}
