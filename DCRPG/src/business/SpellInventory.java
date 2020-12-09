package business;

import java.util.ArrayList;
import java.util.List;

public class SpellInventory 
{
	private final String FIELD_SEP = "%";
	
	private int characterSheetId;
	private List<Integer> sig = new ArrayList<Integer>();
	private List<Integer> first = new ArrayList<Integer>();
	private List<Integer> second = new ArrayList<Integer>();
	private List<Integer> third = new ArrayList<Integer>();
	private List<Integer> fourth = new ArrayList<Integer>();
	private String slots1;
	private String slots2;
	private String slots3;
	private String slots4;
	
	public SpellInventory(int characterSheetId) 
	{
		this.characterSheetId = characterSheetId;
	}
	
	public void save(List<Integer> sig, List<Integer> first, List<Integer> second, List<Integer> third, List<Integer> fourth)
	{
		this.sig = sig;
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
	}

	public int getCharacterSheetId() {
		return characterSheetId;
	}

	public void setCharacterSheetId(int characterSheetId) {
		this.characterSheetId = characterSheetId;
	}

	public List<Integer> getSig() {
		return sig;
	}

	public void setSig(List<Integer> sig) 
	{
		List<Integer> save = new ArrayList<Integer>();
		for(Integer i : sig)
		{
			if(i != 0)
				save.add(i);
		}
		this.sig = save;
	}

	public List<Integer> getFirst() {
		return first;
	}

	public void setFirst(List<Integer> first) 
	{
		List<Integer> save = new ArrayList<Integer>();
		for(Integer i : first)
		{
			if(i != 0)
				save.add(i);
		}
		this.first = save;
	}

	public List<Integer> getSecond() {
		return second;
	}

	public void setSecond(List<Integer> second) 
	{
		List<Integer> save = new ArrayList<Integer>();
		for(Integer i : second)
		{
			if(i != 0)
				save.add(i);
		}
		this.second = save;
	}

	public List<Integer> getThird() {
		return third;
	}

	public void setThird(List<Integer> third) 
	{
		List<Integer> save = new ArrayList<Integer>();
		for(Integer i : third)
		{
			if(i != 0)
				save.add(i);
		}
		this.third = save;
	}

	public List<Integer> getFourth() {
		return fourth;
	}

	public void setFourth(List<Integer> fourth) 
	{
		List<Integer> save = new ArrayList<Integer>();
		for(Integer i : fourth)
		{
			if(i != 0)
				save.add(i);
		}
		this.fourth = save;
	}

	public String getFIELD_SEP() {
		return FIELD_SEP;
	}
	
	public String getSigSplit()
	{
		String ret = "";
		for(Integer i : sig)
		{
			ret += i;
			ret += FIELD_SEP;
		}
		ret += "0";
		
		return ret;
	}
	public String getFirstSplit()
	{
		String ret = "";
		for(Integer i : first)
		{
			ret += i;
			ret += FIELD_SEP;
		}
		ret += "0";
		
		return ret;
	}
	public String getSecondSplit()
	{
		String ret = "";
		for(Integer i : second)
		{
			ret += i;
			ret += FIELD_SEP;
		}
		ret += "0";
		
		return ret;
	}
	public String getThirdSplit()
	{
		String ret = "";
		for(Integer i : third)
		{
			ret += i;
			ret += FIELD_SEP;
		}
		ret += "0";
		
		return ret;
	}
	public String getFourthSplit()
	{
		String ret = "";
		for(Integer i : fourth)
		{
			ret += i;
			ret += FIELD_SEP;
		}
		ret += "0";
		
		return ret;
	}
	public String getSlotsSplit()
	{
		if(slots1.equals(""))
			slots1 = "$$blank$$";
		if(slots2.equals(""))
			slots2 = "$$blank$$";
		if(slots3.equals(""))
			slots3 = "$$blank$$";
		if(slots4.equals(""))
			slots4 = "$$blank$$";
		String ret = slots1+"%"+slots2+"%"+slots3+"%"+slots4;
		return ret;
	}
	
	public void setSpellInventory(String sigS, String firstS, String secondS, String thirdS, String fourthS, String slots)
	{
		String[] sigStr = sigS.split(FIELD_SEP);
		String[] firstStr = firstS.split(FIELD_SEP);
		String[] secondStr = secondS.split(FIELD_SEP);
		String[] thirdStr = thirdS.split(FIELD_SEP);
		String[] fourthStr = fourthS.split(FIELD_SEP);
		String[] slotStr = slots.split(FIELD_SEP);
		
		for(String s : sigStr)
		{
			if(!s.equals("0"))
				sig.add(Integer.parseInt(s));
		}
		for(String s : firstStr)
		{
			if(!s.equals("0"))
				first.add(Integer.parseInt(s));
		}
		for(String s : secondStr)
		{
			if(!s.equals("0"))
				second.add(Integer.parseInt(s));
		}
		for(String s : thirdStr)
		{
			if(!s.equals("0"))
				third.add(Integer.parseInt(s));
		}
		for(String s : fourthStr)
		{
			if(!s.equals("0"))
				fourth.add(Integer.parseInt(s));
		}
		slots1 = slotStr[0];
		slots2 = slotStr[1];
		slots3 = slotStr[2];
		slots4 = slotStr[3];
		
		if(slots1.equals("$$blank$$"))
			slots1 = "";
		if(slots2.equals("$$blank$$"))
			slots2 = "";
		if(slots3.equals("$$blank$$"))
			slots3 = "";
		if(slots4.equals("$$blank$$"))
			slots4 = "";
	}

	public String getSlots1() {
		return slots1;
	}

	public void setSlots1(String slots1) {
		this.slots1 = slots1;
	}

	public String getSlots2() {
		return slots2;
	}

	public void setSlots2(String slots2) {
		this.slots2 = slots2;
	}

	public String getSlots3() {
		return slots3;
	}

	public void setSlots3(String slots3) {
		this.slots3 = slots3;
	}

	public String getSlots4() {
		return slots4;
	}

	public void setSlots4(String slots4) {
		this.slots4 = slots4;
	}
	
	
}
