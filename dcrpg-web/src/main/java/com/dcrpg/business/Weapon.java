package com.dcrpg.business;

public class Weapon extends Equipment 
{
	private int id;
	private String governingSkill;
	private String damage;
	private int weaponRange;
	private int firingRate;
	
	public Weapon() 
	{
		super();
	}

	public Weapon(int id, String name, String picture, String description, int slots, int stacking, int quantity) {
		super(id, name, picture, description, slots, stacking, quantity);
		// TODO Auto-generated constructor stub
	}

	public Weapon(int id, String equipSlot, String statRequirement, int statRequirementLevel, boolean isEquipped) 
	{
		super(id, equipSlot, statRequirement, statRequirementLevel, isEquipped);
	}

	public Weapon(int id, String governingSkill, String damage, int weaponRange, int firingRate) {
		super();
		this.id = id;
		this.governingSkill = governingSkill;
		this.damage = damage;
		this.weaponRange = weaponRange;
		this.firingRate = firingRate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGoverningSkill() {
		return governingSkill;
	}

	public void setGoverningSkill(String governingSkill) {
		this.governingSkill = governingSkill;
	}

	public String getDamage() {
		return damage;
	}

	public void setDamage(String damage) {
		this.damage = damage;
	}

	public int getWeaponRange() {
		return weaponRange;
	}

	public void setWeaponRange(int weaponRange) {
		this.weaponRange = weaponRange;
	}

	public int getFiringRate() {
		return firingRate;
	}

	public void setFiringRate(int firingRate) {
		this.firingRate = firingRate;
	}
	
	

}
