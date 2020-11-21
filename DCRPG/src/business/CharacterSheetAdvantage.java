package business;
import library.*;

public class CharacterSheetAdvantage implements Comparable<CharacterSheetAdvantage>
{
	int id;
	int characterSheetId;
	Advantage adv;
	String description;
	
	public CharacterSheetAdvantage()
	{
		
	}
	
	public CharacterSheetAdvantage(int id)
	{
		this.id = id;
	}
	
	public CharacterSheetAdvantage(int id, int characterSheetId, Advantage adv)
	{
		this.id = id;
		this.characterSheetId = characterSheetId;
		this.adv = adv;
	}
	public CharacterSheetAdvantage(int id, int characterSheetId, Advantage adv, String description)
	{
		this.id = id;
		this.characterSheetId = characterSheetId;
		this.adv = adv;
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

	public Advantage getAdv() {
		return adv;
	}
	
	public String getAdvStr()
	{
		return adv.name();
	}

	public void setAdv(Advantage adv) {
		this.adv = adv;
	}
	
	public void setAdv(String adv) {
		this.adv = Advantage.valueOf(adv);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString()
	{
		return id + " " + characterSheetId + " " + adv.name();
	}

	@Override
	public int compareTo(CharacterSheetAdvantage csa2) 
	{
		System.out.println("Trying");
		return adv.nameA().compareTo(csa2.getAdv().nameA());
	}
	
	
}
