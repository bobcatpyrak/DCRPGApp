package business;

import library.*;

public class CharacterSheetPower 
{
	int id;
	int characterSheetId;
	int level;
	Power power;
	int cost;
	String specs;
	String weakness;
	
	public CharacterSheetPower(int id)
	{
		this.id = id;		
	}
	
	public CharacterSheetPower(int id, int characterSheetId, int level, Power power)
	{
		this.id = id;
		this.characterSheetId = characterSheetId;
		this.level = level;
		this.power = power;
		cost = power.cost();
		
	}
	
	public CharacterSheetPower(int id, int characterSheetId, int level, Power power, int cost, String specs, String weakness)
	{
		this.id = id;
		this.characterSheetId = characterSheetId;
		this.level = level;
		this.power = power;
		this.cost = cost;
		this.specs = specs;
		this.weakness = weakness;
	}
	
	public String getShorthand()
	{
		String shorthand = "";
		if(level == 10)
		{
			if(!power.level10().equals(""))
				shorthand = power.level10();
		}
		else if (level >= 9 && shorthand.equals(""))
		{
			if(!power.level9().equals(""))
				shorthand = power.level9();
		}
		else if (level >= 8 && shorthand.equals(""))
		{
			if(!power.level8().equals(""))
				shorthand = power.level8();
		}
		else if (level >= 7 && shorthand.equals(""))
		{
			if(!power.level7().equals(""))
				shorthand = power.level7();
		}
		else if (level >= 6 && shorthand.equals(""))
		{
			if(!power.level6().equals(""))
				shorthand = power.level6();
		}
		else if (level >= 5 && shorthand.equals(""))
		{
			if(!power.level5().equals(""))
				shorthand = power.level5();
		}
		else if (level >= 4 && shorthand.equals(""))
		{
			if(!power.level4().equals(""))
				shorthand = power.level4();
		}
		else if (level >= 3 && shorthand.equals(""))
		{
			if(!power.level3().equals(""))
				shorthand = power.level3();
		}
		else if (level >= 2 && shorthand.equals(""))
		{
			if(!power.level2().equals(""))
				shorthand = power.level2();
		}
		else if (level >= 1 && shorthand.equals(""))
		{
			if(!power.level1().equals(""))
				shorthand = power.level1();
		}
		
		return shorthand;
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Power getPower() {
		return power;
	}
	
	public String getPowerStr() {
		return power.name();
	}

	public void setPower(Power power) {
		this.power = power;
	}

	public void setPower(String power) {
		this.power = Power.valueOf(power);
	}
	
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getSpecs() {
		return specs;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}

	public String getWeakness() {
		return weakness;
	}

	public void setWeakness(String weakness) {
		this.weakness = weakness;
	}
	
	
	
	
}
