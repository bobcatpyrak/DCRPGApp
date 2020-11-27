package business;

import java.util.ArrayList;

public class Inventory 
{
	private final String FIELD_SEP = "%";
	
	private int characterSheetId;
	private int cap;
	private int head;
	private int neck;
	private int wrists;
	private int chest;
	private int weapon;
	private int ring1;
	private int waist;
	private int ring2;
	private int legs;
	private int feet;
	private int pack1;
	private int pack2;
	private int pack3;
	private int pack4;
	private int pack5;
	private int pack6;
	private int pack7;
	private int pack8;
	private int pack9;
	private int pack10;
	private int pack11;
	private int pack12;
	private int pack13;
	private int pack14;
	private int pack15;
	private ArrayList<Integer> storage;
	
	public Inventory(int characterSheetId)
	{
		this.characterSheetId = characterSheetId;
	}
	
	public void save(int cap,
					int head,
					int neck,
					int wrists,
					int chest,
					int weapon,
					int ring1,
					int waist,
					int ring2,
					int legs,
					int feet,
					int pack1,
					int pack2,
					int pack3,
					int pack4,
					int pack5,
					int pack6,
					int pack7,
					int pack8,
					int pack9,
					int pack10,
					int pack11,
					int pack12,
					int pack13,
					int pack14,
					int pack15,
					ArrayList<Integer> storage)
	{
		this.cap = cap;
		this.head = head;
		this.neck = neck;
		this.wrists = wrists;
		this.chest = chest;
		this.weapon = weapon;
		this.ring1 = ring1;
		this.waist = waist;
		this.ring2 = ring2;
		this.legs = legs;
		this.feet = feet;
		this.pack1 = pack1;
		this.pack2 = pack2;
		this.pack3 = pack3;
		this.pack4 = pack4;
		this.pack5 = pack5;
		this.pack6 = pack6;
		this.pack7 = pack7;
		this.pack8 = pack8;
		this.pack9 = pack9;
		this.pack10 = pack10;
		this.pack11 = pack11;
		this.pack12 = pack12;
		this.pack13 = pack13;
		this.pack14 = pack14;
		this.pack15 = pack15;
		this.storage = storage;
	}

	public int getCharacterSheetId() {
		return characterSheetId;
	}

	public void setCharacterSheetId(int characterSheetId) {
		this.characterSheetId = characterSheetId;
	}

	public int getCap() {
		return cap;
	}

	public void setCap(int cap) {
		this.cap = cap;
	}

	public int getHead() {
		return head;
	}

	public void setHead(int head) {
		this.head = head;
	}

	public int getNeck() {
		return neck;
	}

	public void setNeck(int neck) {
		this.neck = neck;
	}

	public int getWrists() {
		return wrists;
	}

	public void setWrists(int wrists) {
		this.wrists = wrists;
	}

	public int getChest() {
		return chest;
	}

	public void setChest(int chest) {
		this.chest = chest;
	}

	public int getWeapon() {
		return weapon;
	}

	public void setWeapon(int weapon) {
		this.weapon = weapon;
	}

	public int getRing1() {
		return ring1;
	}

	public void setRing1(int ring1) {
		this.ring1 = ring1;
	}

	public int getWaist() {
		return waist;
	}

	public void setWaist(int waist) {
		this.waist = waist;
	}

	public int getRing2() {
		return ring2;
	}

	public void setRing2(int ring2) {
		this.ring2 = ring2;
	}

	public int getLegs() {
		return legs;
	}

	public void setLegs(int legs) {
		this.legs = legs;
	}

	public int getFeet() {
		return feet;
	}

	public void setFeet(int feet) {
		this.feet = feet;
	}

	public int getPack1() {
		return pack1;
	}

	public void setPack1(int pack1) {
		this.pack1 = pack1;
	}

	public int getPack2() {
		return pack2;
	}

	public void setPack2(int pack2) {
		this.pack2 = pack2;
	}

	public int getPack3() {
		return pack3;
	}

	public void setPack3(int pack3) {
		this.pack3 = pack3;
	}

	public int getPack4() {
		return pack4;
	}

	public void setPack4(int pack4) {
		this.pack4 = pack4;
	}

	public int getPack5() {
		return pack5;
	}

	public void setPack5(int pack5) {
		this.pack5 = pack5;
	}

	public int getPack6() {
		return pack6;
	}

	public void setPack6(int pack6) {
		this.pack6 = pack6;
	}

	public int getPack7() {
		return pack7;
	}

	public void setPack7(int pack7) {
		this.pack7 = pack7;
	}

	public int getPack8() {
		return pack8;
	}

	public void setPack8(int pack8) {
		this.pack8 = pack8;
	}

	public int getPack9() {
		return pack9;
	}

	public void setPack9(int pack9) {
		this.pack9 = pack9;
	}

	public int getPack10() {
		return pack10;
	}

	public void setPack10(int pack10) {
		this.pack10 = pack10;
	}

	public int getPack11() {
		return pack11;
	}

	public void setPack11(int pack11) {
		this.pack11 = pack11;
	}

	public int getPack12() {
		return pack12;
	}

	public void setPack12(int pack12) {
		this.pack12 = pack12;
	}

	public int getPack13() {
		return pack13;
	}

	public void setPack13(int pack13) {
		this.pack13 = pack13;
	}

	public int getPack14() {
		return pack14;
	}

	public void setPack14(int pack14) {
		this.pack14 = pack14;
	}

	public int getPack15() {
		return pack15;
	}

	public void setPack15(int pack15) {
		this.pack15 = pack15;
	}

	public ArrayList<Integer> getStorage() {
		return storage;
	}

	public void setStorage(ArrayList<Integer> storage) {
		this.storage = storage;
	}
	
	public String getStorageSplit()
	{
		String ret = "";
		for(Integer i : storage)
		{
			ret += i;
			ret += FIELD_SEP;
		}
		ret += "0";
		
		return ret;
	}
	
	public String getAllHeld()
	{
		String ret = "";
		ret = cap + FIELD_SEP +
		head + FIELD_SEP +
		neck + FIELD_SEP +
		wrists + FIELD_SEP +
		chest + FIELD_SEP +
		weapon + FIELD_SEP +
		ring1 + FIELD_SEP +
		waist + FIELD_SEP +
		ring2 + FIELD_SEP +
		legs + FIELD_SEP +
		feet + FIELD_SEP +
		pack1 + FIELD_SEP +
		pack2 + FIELD_SEP +
		pack3 + FIELD_SEP +
		pack4 + FIELD_SEP +
		pack5 + FIELD_SEP +
		pack6 + FIELD_SEP +
		pack7 + FIELD_SEP +
		pack8 + FIELD_SEP +
		pack9 + FIELD_SEP +
		pack10 + FIELD_SEP +
		pack11 + FIELD_SEP +
		pack12 + FIELD_SEP +
		pack13 + FIELD_SEP +
		pack14 + FIELD_SEP +
		pack15;
		
		return ret;
	}
	
	public void setInventory(String inv, String storageArray)
	{		
		String[] fields = inv.split(FIELD_SEP);
		int[] items = new int[26];
		
		for(int i = 0; i < 26; i++)
		{
			items[i] = Integer.parseInt(fields[i]);
		}
		
		cap = items[0];
		head = items[1];
		neck = items[2];
		wrists = items[3];
		chest = items[4];
		weapon = items[5];
		ring1 = items[6];
		waist = items[7];
		ring2 = items[8];
		legs = items[9];
		feet = items[10];
		pack1 = items[11];
		pack2 = items[12];
		pack3 = items[13];
		pack4 = items[14];
		pack5 = items[15];
		pack6 = items[16];
		pack7 = items[17];
		pack8 = items[18];
		pack9 = items[19];
		pack10 = items[20];
		pack11 = items[21];
		pack12 = items[22];
		pack13 = items[23];
		pack14 = items[24];
		pack15 = items[25];
		
		
		String[] fields2 = inv.split(FIELD_SEP);
	
		for(String s : fields2)
		{
			if(!s.equals("0"))
			storage.add(Integer.parseInt(s));
		}
		storage.add(0);
	}
	
}
