package library;

import java.util.ArrayList;
import java.util.List;

public enum Advantage
{
	ACTING_ABILITY("Acting Ability", 3, "<html>This character is adept at creating a character, and therefore receives advantage on Bluff and Charm rolls while maintaining a disguise.</html>", false),
	ACUTE_BALANCE("Acute Balance", 2, "<html>This character has an innate sense of balance and rarely falls.  If this character fails an Acrobatics roll, they can roll a second acrobatics roll immediately to recover at a +2 Difficulty Level from the previous roll.</html>", false),
	ACUTE_MANUAL_DEXTERITY("Acute Manual Dexterity", 2, "<html>This character has dexterous fingers, and therefore gains a +2 modifier on all lockpicking or sleight of hand-related rolls.</html>", false),
	ACUTE_SENSE_OF_DIRECTION("Acute Sense of Direction", 2, "<html>This character has incredible talent for orienteering.  All navigation-related rolls are modified with a -1 Difficulty Level, and a character can always make a Novice Difficulty Level roll with Survival to find north.</html>", false),
	ACUTE_SENSE("Acute Sense", 2, "<html>This character has a very well-developed sense.  They gain a +1 modifier to any raw Perception roll utilizing that specific sense.  This advantage must be taken multiple times to affect multiple senses.</html>", true),
	ANIMAL_FRIENDSHIP("Animal Friendship", 2, "<html>This character has a natural rapport with animals, and as such receives a -2 difficulty modifier when making survival or driving related rolls in relation to them.</html>", false),
	ATTRACTIVE_APPEARANCE("Attractive Appearance", 2, "<html>This character is widely considered to be good-looking, and therefore is able to have Advantage on any Seduction roll involving a character who has the capacity to be attracted by the player character�s gender or species.</html>", false),
	CHARISMATIC("Charismatic", 3, "<html>This character is incredibly friendly and upbeat; if they assist a character who has lower Presence than them with a Charm or Bluff roll, that character rolls with advantage.</html>", false),
	COURAGE("Courage", 2, "<html>This character receives a -2 difficulty modifier when making willpower rolls against intimidation or interrogation, thanks to their unflappable resolve.  The character also can roll Willpower against fear toxins or fear-based spells at any Difficulty Level.</html>", false),
	DOUBLE_JOINTED("Double-Jointed", 2, "<html>This character�s natural suppleness to his/her joints allows them to bend extraordinarily.  The Thievery (Escape Artistry) specialization receives a +3 modifier to all rolls, and contortion-based rolls in Acrobatics receive a +1 modifier.</html>", false),
	HARDINESS("Hardiness", 4, "<html>If this character has Physique greater than 5, they can take this Advantage, which allows them to increase their body points by 4, permanently.  They also roll Powering Out rolls with a +1 modifier.</html>", false),
	INTIMIDATING_GRIN("Intimidating Grin", 2, "<html>Thanks to their unnerving smile, this character has gained a unique specialization to Intimidation which always forces its victim to roll at disadvantage.</html>", false),
	LEADERSHIP_ABILITY("Leadership Ability", 2, "<html>As a natural leader, this character can use successful Willpower rolls at a matched difficulty to any ally�s Willpower roll to provide them a +1 modifier to said roll, including Powering Out rolls.  This ability only works when in visible range of the ally.</html>", false),
	MECHANICAL_APTITUDE("Mechanical Aptitude", 2, "<html>This character�s adept skill at creating or repairing machinery gives them a -4 to any Difficulty Level when making mechanical rolls in Science or Engineering.</html>", false),
	OBSCURE_KNOWLEDGE("Obscure Knowledge", 2, "<html>This character is filled with useless trivia, and as such can make any Scholar roll that requires a specialization with their basic Knowledge skill at +3 difficulty.  Cannot be taken with Forgetful.</html>", false),
	OBSERVANT("Observant", 2, "<html>Thanks to this character�s penchant for observation, this character can reroll a failed Search roll at +3 difficulty, and any Nat 1 using the tracking specialization in Survival is immediately negated to a simple failure.</html>", false),
	PHOTOGRAPHIC_MEMORY("Photographic Memory", 4, "<html>If this character starts with Perception greater than 5, they can remember details with uncanny clarity.  This sort of recall provides them the opportunity to make Skilled DL rolls in Search or Surveillance in order to learn a detail they may have missed in a previously visited scenario.</html>", false),
	PREPAREDNESS("Preparedness", 4, "<html>Once per day, a character can search their pack for a non-unique item of common variety based on a D6 roll.  Any roll above a 2 will result in the character collecting the item; the definition of non-unique item of common variety will be established at the DM�s discretion.</html>", false),
	SELF_HEALING("Self-Healing", 4, "<html>Only available for certain races.  During long rests in the field, the character can always regenerate full Body Points.</html>", false),
	SIXTH_SENSE("Sixth Sense", 2, "<html>Only available for characters with Perception greater than 5.  If the DM calls for a surprise raw Perception roll, it may be rolled with Advantage.</html>", false),
	SPEED_DRAW("Speed Draw", 1, "<html>Specialized in the art of combat with a specific weapon, a character with Speed Draw gains a +3 to their Initiative so long as a weapon of that type is slotted as the primary weapon on a character.  A character can also retrieve a weapon of the Speed Draw�s type from their pack during combat without using up a turn.</html>", true),
	STATUS("Status", 2, "<html>In a particular field of influence, this character has achieved a level of respect and recognition that allows them to lower Charm, Bluff, Intimidation, Interrogation and Persuasion roll difficulties with fellow members of that field of influence by 1.</html>", false),
	TECHNOLOGICALLY_ADVANCED("Technologically Advanced", 4, "<html>Only available to specific races; characters with this advantage are from a society far advanced beyond Earth�s capabilities, and as such, earn a -3 difficulty modifier on computer ops rolls for any modern Earth technology.</html>", false),
	THOUSAND_FACES("Thousand Faces", 2, "<html>Only available if a character is or can simulate humans of average height and weight.  (5�7-6�1, 130-180 for men, 5�4-5�10, 110-150 for women).  The character is a master of disguise and receives +2 to disguise specialization rolls.</html>", false),
	VENTRILOQUIST("Ventriloquism", 1, "<html>This advantage provides players with a unique specialization in Artist; Ventriloquism-related rolls in Charm and Bluff receive +1.</html>", false),
	WEALTH("Wealth", 2, "<html>This character has deep pockets and a variety of assets; as such, they begin the campaign with $500,000 or the equivalent for their race.</html>", false);

	private final String nameA;
	private final int cost;
	private final String description;
	private final boolean param;
	
	Advantage(String nameA, int cost, String description, boolean param)
	{
		this.nameA = nameA;
		this.cost = cost;
		this.description = description;
		this.param = param;
	}
	
	public String nameA()
	{
		return nameA;
	}
	
	public int cost()
	{
		return cost;
	}
	
	public String description()
	{
		return description;
	}
	public boolean param()
	{
		return param;
	}
	
	public static List<Advantage> getAll()
	{
		List<Advantage> a = new ArrayList<Advantage>();
		a.add(ACTING_ABILITY);
		a.add(ACUTE_BALANCE);
		a.add(ACUTE_MANUAL_DEXTERITY);
		a.add(ACUTE_SENSE);
		a.add(ACUTE_SENSE_OF_DIRECTION);
		a.add(ANIMAL_FRIENDSHIP);
		a.add(ATTRACTIVE_APPEARANCE);
		a.add(CHARISMATIC);
		a.add(COURAGE);
		a.add(DOUBLE_JOINTED);
		a.add(HARDINESS);
		a.add(INTIMIDATING_GRIN);
		a.add(LEADERSHIP_ABILITY);
		a.add(MECHANICAL_APTITUDE);
		a.add(OBSCURE_KNOWLEDGE);
		a.add(OBSERVANT);
		a.add(PHOTOGRAPHIC_MEMORY);
		a.add(PREPAREDNESS);
		a.add(SELF_HEALING);
		a.add(SIXTH_SENSE);
		a.add(SPEED_DRAW);
		a.add(STATUS);
		a.add(TECHNOLOGICALLY_ADVANCED);
		a.add(THOUSAND_FACES);
		a.add(VENTRILOQUIST);
		a.add(WEALTH);
		
		return a;
	}

}
