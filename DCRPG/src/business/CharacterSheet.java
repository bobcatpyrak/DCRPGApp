package business;

import java.util.ArrayList;
import java.util.List;

public class CharacterSheet 
{
	private final String FIELD_SEP = "%";
	
	int id;
	String name;
	String picture;
	// demographics
	String occupation;
	String baseOfOperations;
	String location;
	String gender;
	String race;
	String height;
	String weight;
	String eyeColor;
	String hairColor;
	// misc stats
	String udoDice;
	int udoBonus;
	int speed;
	int heroPoints;
	int villainPoints;
	int availableRenown;
	int powerPoints;
	int skillPoints;
	int bodyPointsCurrent;
	int bodyPointsMax;
	// stats
	int reflexes; 		int acrobatics; int dodge; 			int handToHand; 	int meleeWeapons; 	int stealth;
	int coordination; 	int catching; 	int climb; 			int drive; 			int marksmanship; 	int thievery; 		int thrownWeapons;
	int physique; 		int athletics; 	int leap; 			int lifting; 		int resistance; 	int running; 		int swimming;
	int knowledge; 		int arcaneLore; int demolitions; 	int languages; 		int medicine; 		int scholar; 		int science; 		int security;
	int perception; 	int artist; 	int engineering; 	int search; 		int streetwise; 	int surveillance; 	int survival;
	int presence; 		int bluff; 		int charm; 			int intimidation; 	int persuasion; 	int willpower;
	// other stat arrays
	List<SkillSpec> skillSpecs = new ArrayList<SkillSpec>();
	
	public CharacterSheet(int id)
	{
		this.id = id;
		speed = 3;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getBaseOfOperations() {
		return baseOfOperations;
	}
	public void setBaseOfOperations(String baseOfOperations) {
		this.baseOfOperations = baseOfOperations;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getEyeColor() {
		return eyeColor;
	}
	public void setEyeColor(String eyeColor) {
		this.eyeColor = eyeColor;
	}
	public String getHairColor() {
		return hairColor;
	}
	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}
	public String getUdoDice() {
		return udoDice;
	}
	public void setUdoDice(String udoDice) {
		this.udoDice = udoDice;
	}
	public int getUdoBonus() {
		return udoBonus;
	}
	public void setUdoBonus(int udoBonus) {
		this.udoBonus = udoBonus;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getHeroPoints() {
		return heroPoints;
	}
	public void setHeroPoints(int heroPoints) {
		this.heroPoints = heroPoints;
	}
	public int getVillainPoints() {
		return villainPoints;
	}
	public void setVillainPoints(int villainPoints) {
		this.villainPoints = villainPoints;
	}
	public int getAvailableRenown() {
		return availableRenown;
	}
	public void setAvailableRenown(int availableRenown) {
		this.availableRenown = availableRenown;
	}
	public int getPowerPoints() {
		return powerPoints;
	}
	public void setPowerPoints(int powerPoints) {
		this.powerPoints = powerPoints;
	}
	public int getSkillPoints() {
		return skillPoints;
	}
	public void setSkillPoints(int skillPoints) {
		this.skillPoints = skillPoints;
	}
	public int getBodyPointsCurrent() {
		return bodyPointsCurrent;
	}
	public void setBodyPointsCurrent(int bodyPointsCurrent) {
		this.bodyPointsCurrent = bodyPointsCurrent;
	}
	public int getBodyPointsMax() {
		return bodyPointsMax;
	}
	public void setBodyPointsMax(int bodyPointsMax) {
		this.bodyPointsMax = bodyPointsMax;
	}
	public int getReflexes() {
		return reflexes;
	}
	public void setReflexes(int reflexes) {
		this.reflexes = reflexes;
	}
	public int getAcrobatics() {
		return acrobatics;
	}
	public void setAcrobatics(int acrobatics) {
		this.acrobatics = acrobatics;
	}
	public int getDodge() {
		return dodge;
	}
	public void setDodge(int dodge) {
		this.dodge = dodge;
	}
	public int getHandToHand() {
		return handToHand;
	}
	public void setHandToHand(int handToHand) {
		this.handToHand = handToHand;
	}
	public int getMeleeWeapons() {
		return meleeWeapons;
	}
	public void setMeleeWeapons(int meleeWeapons) {
		this.meleeWeapons = meleeWeapons;
	}
	public int getStealth() {
		return stealth;
	}
	public void setStealth(int stealth) {
		this.stealth = stealth;
	}
	public int getCoordination() {
		return coordination;
	}
	public void setCoordination(int coordination) {
		this.coordination = coordination;
	}
	public int getCatching() {
		return catching;
	}
	public void setCatching(int catching) {
		this.catching = catching;
	}
	public int getClimb() {
		return climb;
	}
	public void setClimb(int climb) {
		this.climb = climb;
	}
	public int getDrive() {
		return drive;
	}
	public void setDrive(int drive) {
		this.drive = drive;
	}
	public int getMarksmanship() {
		return marksmanship;
	}
	public void setMarksmanship(int marksmanship) {
		this.marksmanship = marksmanship;
	}
	public int getThievery() {
		return thievery;
	}
	public void setThievery(int thievery) {
		this.thievery = thievery;
	}
	public int getThrownWeapons() {
		return thrownWeapons;
	}
	public void setThrownWeapons(int thrownWeapons) {
		this.thrownWeapons = thrownWeapons;
	}
	public int getPhysique() {
		return physique;
	}
	public void setPhysique(int physique) {
		this.physique = physique;
	}
	public int getAthletics() {
		return athletics;
	}
	public void setAthletics(int athletics) {
		this.athletics = athletics;
	}
	public int getLeap() {
		return leap;
	}
	public void setLeap(int leap) {
		this.leap = leap;
	}
	public int getLifting() {
		return lifting;
	}
	public void setLifting(int lifting) {
		this.lifting = lifting;
	}
	public int getResistance() {
		return resistance;
	}
	public void setResistance(int resistance) {
		this.resistance = resistance;
	}
	public int getRunning() {
		return running;
	}
	public void setRunning(int running) {
		this.running = running;
		setSpeed(3);
	}
	public int getSwimming() {
		return swimming;
	}
	public void setSwimming(int swimming) {
		this.swimming = swimming;
	}
	public int getKnowledge() {
		return knowledge;
	}
	public void setKnowledge(int knowledge) {
		this.knowledge = knowledge;
	}
	public int getArcaneLore() {
		return arcaneLore;
	}
	public void setArcaneLore(int arcaneLore) {
		this.arcaneLore = arcaneLore;
	}
	public int getDemolitions() {
		return demolitions;
	}
	public void setDemolitions(int demolitions) {
		this.demolitions = demolitions;
	}
	public int getLanguages() {
		return languages;
	}
	public void setLanguages(int languages) {
		this.languages = languages;
	}
	public int getMedicine() {
		return medicine;
	}
	public void setMedicine(int medicine) {
		this.medicine = medicine;
	}
	public int getScholar() {
		return scholar;
	}
	public void setScholar(int scholar) {
		this.scholar = scholar;
	}
	public int getScience() {
		return science;
	}
	public void setScience(int science) {
		this.science = science;
	}
	public int getSecurity() {
		return security;
	}
	public void setSecurity(int security) {
		this.security = security;
	}
	public int getPerception() {
		return perception;
	}
	public void setPerception(int perception) {
		this.perception = perception;
	}
	public int getArtist() {
		return artist;
	}
	public void setArtist(int artist) {
		this.artist = artist;
	}
	public int getEngineering() {
		return engineering;
	}
	public void setEngineering(int engineering) {
		this.engineering = engineering;
	}
	public int getSearch() {
		return search;
	}
	public void setSearch(int search) {
		this.search = search;
	}
	public int getStreetwise() {
		return streetwise;
	}
	public void setStreetwise(int streetwise) {
		this.streetwise = streetwise;
	}
	public int getSurveillance() {
		return surveillance;
	}
	public void setSurveillance(int surveillance) {
		this.surveillance = surveillance;
	}
	public int getSurvival() {
		return survival;
	}
	public void setSurvival(int survival) {
		this.survival = survival;
	}
	public int getPresence() {
		return presence;
	}
	public void setPresence(int presence) {
		this.presence = presence;
	}
	public int getBluff() {
		return bluff;
	}
	public void setBluff(int bluff) {
		this.bluff = bluff;
	}
	public int getCharm() {
		return charm;
	}
	public void setCharm(int charm) {
		this.charm = charm;
	}
	public int getIntimidation() {
		return intimidation;
	}
	public void setIntimidation(int intimidation) {
		this.intimidation = intimidation;
	}
	public int getPersuasion() {
		return persuasion;
	}
	public void setPersuasion(int persuasion) {
		this.persuasion = persuasion;
	}
	public int getWillpower() {
		return willpower;
	}
	public void setWillpower(int willpower) {
		this.willpower = willpower;
	}
	
	// methods for Skill Specializations
	public void setSkillSpecs(List<SkillSpec> skillSpecDAO)
	{
		skillSpecs.clear();
		for (SkillSpec spec : skillSpecDAO)
		{	
			if(spec.getCharacterSheetId() == id)
			{
				skillSpecs.add(spec);
			}
		}
	}
	public List<SkillSpec> getSkillSpecs ()
	{
		return skillSpecs;
	}
	public List<SkillSpec> assignSkillSpecs(String skill)
	{
		List<SkillSpec> specs = new ArrayList<SkillSpec>();
		for (SkillSpec spec : skillSpecs)
		{
			System.out.println("Checking Spec # " + spec.getId()+spec.getDescription());
			if(spec.getSkill().equals(skill))
			{
				System.out.println("Adding Spec # " + spec.getId()+spec.getDescription());
				specs.add(spec);
			}
		}
		return specs;
	}
	
	// Methods for getting and setting all demographics
	public String getAllDemographics()
	{
		return getOccupation() + FIELD_SEP + getBaseOfOperations() 
		+ FIELD_SEP + getLocation() + FIELD_SEP + getGender() 
		+ FIELD_SEP + getRace() + FIELD_SEP + getHeight() 
		+ FIELD_SEP + getWeight() + FIELD_SEP + getEyeColor() 
		+ FIELD_SEP + getHairColor();
	}
	public void setAllDemographics(String cs)
	{
		String[] fields = cs.split(FIELD_SEP);
		
		setOccupation(fields[0]);
		setBaseOfOperations(fields[1]);
		setLocation(fields[2]);
		setGender(fields[3]);
		setRace(fields[4]);
		setHeight(fields[5]);
		setWeight(fields[6]);
		setEyeColor(fields[7]);
		setHairColor(fields[7]);
	}
	
	// Methods for getting and setting all misc stats
	public String getAllMiscStats()
	{
		return getUdoDice() + FIELD_SEP + getUdoBonus() + FIELD_SEP + getSpeed() 
		+ FIELD_SEP + getHeroPoints() + FIELD_SEP + getVillainPoints()
		+ FIELD_SEP + getAvailableRenown() + FIELD_SEP + getPowerPoints()
		+ FIELD_SEP + getSkillPoints() + FIELD_SEP + getBodyPointsCurrent()
		+ FIELD_SEP + getBodyPointsMax();
	}
	public void setAllMiscStats(String cs)
	{
		String[] fields = cs.split(FIELD_SEP);

		setUdoDice(fields[0]);
		setUdoBonus(Integer.parseInt(fields[1]));
		setSpeed(Integer.parseInt(fields[2]));
		setHeroPoints(Integer.parseInt(fields[3]));
		setVillainPoints(Integer.parseInt(fields[4]));
		setAvailableRenown(Integer.parseInt(fields[5]));
		setPowerPoints(Integer.parseInt(fields[6]));
		setSkillPoints(Integer.parseInt(fields[7]));
		setBodyPointsCurrent(Integer.parseInt(fields[8]));
		setBodyPointsMax(Integer.parseInt(fields[9]));
	}
	
	// Methods for getting and setting all stats
	public String getAllStats()
	{
		return getReflexes() + FIELD_SEP + getAcrobatics() + FIELD_SEP + getDodge() + FIELD_SEP + getHandToHand() + FIELD_SEP + getMeleeWeapons() + FIELD_SEP + getStealth() + FIELD_SEP
				 + getCoordination() + FIELD_SEP + getCatching() + FIELD_SEP + getClimb() + FIELD_SEP + getDrive() + FIELD_SEP + getMarksmanship() + FIELD_SEP + getThievery() + FIELD_SEP + getThrownWeapons() + FIELD_SEP
				 + getPhysique() + FIELD_SEP + getAthletics() + FIELD_SEP + getLeap() + FIELD_SEP + getLifting() + FIELD_SEP + getResistance() + FIELD_SEP + getRunning() + FIELD_SEP + getSwimming() + FIELD_SEP
				 + getKnowledge() + FIELD_SEP + getArcaneLore() + FIELD_SEP + getDemolitions() + FIELD_SEP + getLanguages() + FIELD_SEP + getMedicine() + FIELD_SEP + getScholar() + FIELD_SEP + getScience() + FIELD_SEP + getSecurity() + FIELD_SEP
				 + getPerception() + FIELD_SEP + getArtist() + FIELD_SEP + getEngineering() + FIELD_SEP + getSearch() + FIELD_SEP + getStreetwise() + FIELD_SEP + getSurveillance() + FIELD_SEP + getSurvival() + FIELD_SEP
				 + getPresence() + FIELD_SEP + getBluff() + FIELD_SEP + getCharm() + FIELD_SEP + getIntimidation() + FIELD_SEP + getPersuasion() + FIELD_SEP + getWillpower();
	}
	public void setAllStats(String cs)
	{
		String[] fields = cs.split(FIELD_SEP);
		
		setReflexes(Integer.parseInt(fields[0]));
		setAcrobatics(Integer.parseInt(fields[1]));
		setDodge(Integer.parseInt(fields[2]));
		setHandToHand(Integer.parseInt(fields[3]));
		setMeleeWeapons(Integer.parseInt(fields[4]));
		setStealth(Integer.parseInt(fields[5]));
		setCoordination(Integer.parseInt(fields[6]));
		setCatching(Integer.parseInt(fields[7]));
		setClimb(Integer.parseInt(fields[8]));
		setDrive(Integer.parseInt(fields[9]));
		setMarksmanship(Integer.parseInt(fields[10]));
		setThievery(Integer.parseInt(fields[11]));
		setThrownWeapons(Integer.parseInt(fields[12]));
		setPhysique(Integer.parseInt(fields[13]));
		setAthletics(Integer.parseInt(fields[14]));
		setLeap(Integer.parseInt(fields[15]));
		setLifting(Integer.parseInt(fields[16]));
		setResistance(Integer.parseInt(fields[17]));
		setRunning(Integer.parseInt(fields[18]));
		setSwimming(Integer.parseInt(fields[19]));
		setKnowledge(Integer.parseInt(fields[20]));
		setArcaneLore(Integer.parseInt(fields[21]));
		setDemolitions(Integer.parseInt(fields[22]));
		setLanguages(Integer.parseInt(fields[23]));
		setMedicine(Integer.parseInt(fields[24]));
		setScholar(Integer.parseInt(fields[25]));
		setScience(Integer.parseInt(fields[26]));
		setSecurity(Integer.parseInt(fields[27]));
		setPerception(Integer.parseInt(fields[28]));
		setArtist(Integer.parseInt(fields[29]));
		setEngineering(Integer.parseInt(fields[30]));
		setSearch(Integer.parseInt(fields[31]));
		setStreetwise(Integer.parseInt(fields[32]));
		setSurveillance(Integer.parseInt(fields[33]));
		setSurvival(Integer.parseInt(fields[34]));
		setPresence(Integer.parseInt(fields[35]));
		setBluff(Integer.parseInt(fields[36]));
		setCharm(Integer.parseInt(fields[37]));
		setIntimidation(Integer.parseInt(fields[38]));
		setPersuasion(Integer.parseInt(fields[39]));
		setWillpower(Integer.parseInt(fields[40]));
	}
}
