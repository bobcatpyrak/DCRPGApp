package library;

import java.util.ArrayList;
import java.util.List;

public enum Power 
{
	ANIMAL_MIMESIS("Animal Mimesis", 3, "<html><b>Governing Skills: Survival</b><br>"
			+ "This character can tap into the morphogenetic field and use the natural abilities of the creatures around them to alter their own physiology, providing new bonuses and physical talents to their own bodies temporarily.  Animal Mimesis functions as follows: a character’s reach into the morphogenetic field increases as their level increases, providing a range of animal information to draw upon.  Characters must search for specific animals or species, in order to glean new abilities.  They may also tap into a random animal within range to gain or learn about new abilities.  As a character’s level increases, the depth and breadth of their ability increases as well.  Only one creature can be mimicked at a time.<br><br>"
			+ "Glossary Note: A meta-ability would be considered a skill or power that would be an aberration from the genetic norm of a creature’s species - Krypto the Super-Dog has meta-abilities.<br><br>"
			+ "Potency Rate: The character’s ceiling on a skill in proportion to that skill level from the creature the character is mimicking.<br>"
			+ "<br>"
			+ "<b>Lvl. 1</b> - The character can tap into any animal’s abilities within 1000 feet, excluding meta-abilities, at a potency rate of 33%.<br>"
			+ "<b>Lvl. 3</b> - The character can tap into any animal’s abilities within 5000 feet, excluding meta-abilities, at a potency rate of 33%.<br>"
			+ "<b>Lvl. 5</b> - The character can tap into any animal’s abilities within 3 miles, at a potency rate of 50%.  They may also tap into meta-abilities at a maximum level of 1.<br>"
			+ "<b>Lvl. 7</b> - The character can tap into any animal’s abilities within 5 miles, at a potency rate of 75%.  They may also tap into meta-abilities at a maximum level of 3.<br>"
			+ "<b>Lvl. 10</b> - The character can tap into any animal’s abilities within 10 miles, at a potency rate of 100%.  They may also tap into meta-abilities at a maximum level of 5. <br></html>", 
			"1000 feet  33%", "", " 5000 feet 33%", "", "3 miles 50%.  Meta-abilities max level 1.", "", " 5 miles 75%.  Meta-abilities max level of 3.", "", "",  "10 miles 100%.  Meta-abilities max level 5"),

	BIND("Bind", 2, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),
	
	CHEMICAL_MIMESIS("Chemical Mimesis", 3, "<html><b>Governing Skills: Survival</b><br>"
			+ "This character can tap into the building blocks of physics and use the natural properties of the chemicals around them to alter their own physiology, providing new bonuses and physical talents to their own bodies temporarily.  Chemical Mimesis functions as follows: a character’s control of the molecular function of their body increases as their level increases, providing a range of molecular information to draw upon.  Characters must search for specific chemicals, in order to glean new abilities.   As a character’s level increases, the depth and breadth of their ability increases as well.  Only one chemical can be mimicked at a time.<br>"
			+ "Potency Rate: The character’s ceiling on a skill in proportion to that skill level from the chemical the character is mimicking.<br>"
			+ "<br>"
			+ "<b>Lvl. 1</b> - The character can tap into any element’s abilities within 1000 feet, at a potency rate of 33%.  <br>"
			+ "<b>Lvl. 3</b>- The character can tap into any element’s abilities within 5000 feet, at a potency rate of 33%.  <br>"
			+ "<b>Lvl. 5</b> - The character can tap into any element’s abilities within 3 miles, at a potency rate of 50%.  At this level, they can also begin mimicking compounds.<br>"
			+ "<b>Lvl. 7</b> - The character can tap into any element’s abilities within 5 miles, at a potency rate of 75%.<br>"
			+ "<b>Lvl. 10</b>  - The character can tap into any element’s abilities within 10 miles, at a potency rate of 100%. It is only at this level that a character can become entirely comprised of a particular element.<br></html>", 
			"1000 feet  33%", "", " 5000 feet 33%", "", "3 miles 50%.  Meta-abilities max level 1.", "", " 5 miles 75%.  Meta-abilities max level of 3.", "", "",  "10 miles 100%.  Meta-abilities max level 5 Can become an element."),

	CHEMICAL_PROJECTION("Chemical Projection", 3, "<html><b>Governing Skills: Resistance, Thrown Weapons</b><br>"
			+ "Chemical projection involves the release of acid or other dangerous compounds (not disease-based) that can deal potential damage over time or release other effects.  The character’s body produces this chemical in notable quantities on command and ejects the chemical through a chosen orifice in liquid form<br>"
			+ "<br><b>Lvl 1 - </b>The projectile deals 1D6 poison damage and deals an additional 1 point of damage per round unless eradicated or resisted.<br>"
			+ "<b>Lvl 2 -</b> The projectile deals 1D6+1 poison damage and deals an additional 2 points of damage per round unless eradicated or resisted.<br>"
			+ "<b>Lvl 3 -</b> The projectile deals 1D6+2 poison damage and deals an additional 2 points of damage per round unless eradicated or resisted.<br>"
			+ "<b>Lvl 4 -</b> The projectile deals 1D6+3 poison damage and deals an additional 3 points of damage per round unless eradicated or resisted.<br>"
			+ "<b>Lvl 5 -</b> The projectile deals 2D6+1 poison damage and deals an additional 3 points of damage per round unless eradicated or resisted.<br>"
			+ "<b>Lvl 6 -</b> The projectile deals 2D6+2 poison damage and deals an additional 3 points of damage per round unless eradicated or resisted.<br>"
			+ "<b>Lvl 7 -</b> The projectile deals 2D6+3 poison damage and deals an additional 4 points of damage per round unless eradicated or resisted.<br>"
			+ "<b>Lvl 8 -</b> The projectile deals 2D6+4 poison damage and deals an additional 4 points of damage per round unless eradicated or resisted.<br>"
			+ "<b>Lvl 9 - </b>The projectile deals 2D6+5 poison damage and deals an additional 4 points of damage per round unless eradicated or resisted.<br>"
			+ "<b>Lvl 10 -</b> The projectile deals 2D6+5 poison damage and deals an additional 5 points of damage per round unless eradicated or resisted.<br>"
			+ "<br></html>.", "1D6 poison damage and 1 damage per round", "1D6+1 poison damage and 2 damage per round", "1D6+2 poison damage and 2 damage per round", "1D6+3 poison damage and 3 damage per round", "2D6+1 poison damage and 3 damage per round", "2D6+2 poison damage and 3 damage per round", "2D6+3 poison damage and 4 damage per round", "2D6+4 poison damage and 4 damage per round", "2D6+5 poison damage and 4 damage per round", "2D6+5 poison damage and 5 damage per round"),

	CLINGING("Clinging", 1, "<html><b>Governing Skills: Climbing</b><br>"
			+ "Clinging functions in two ways; one, it provides a bonus to climbing equal to its level (a character with Clinging Lvl. 5 receives a +5 bonus to their climbing rolls).  Two, it eradicates any need for climbing equipment and opens up vertical or slick surfaces to climbing roll attempts.  Clinging also allows a character to ride fast-moving objects (not individuals), using climbing rolls to affix the character to the target object.  Finally, Clinging reduces the difficulty modifier of a climbing roll by half of Clinging’s level, rounded down.<br></html>", "", "", "", "", "", "", "", "", "", ""),

	DISEASE("Disease", 2, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	DISINTEGRATION("Disintegration", 4, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),
	
	ELASTICITY("Elasticity", 1, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	DIGESTIVE_ADAPTABILITY("Digestive Adaptability", 2, "<html><b>Governing Skills: Resistance</b><br>"
			+ "The character has a unique digestive system, and as such, they are able to eat unique objects normally considered inedible or dangerous to anyone else, reducing or in some cases negating the penalty that would be caused by imbibing the objects in question.  At level 1, the character will begin to make resistance rolls based on a difficulty level determined by the level of Digestive Adaptability.  Successful rolls, lower the amount of damage received by poisons or objects that could harm the consumer.  At level 3, ingested poison only deals a one-time amount of damage instead of persistent damage.   At level 5, successful rolls can begin to negate all damage rather than reduce it, and digestion has sped up to a rapid pace.  At level 10, only the most volatile of materials will cause damage to the consumer, and objects are almost immediately digested in their entirety<br></html>.", "", "", "", "", "", "", "", "", "", ""), 

	ENERGY_PROJECTION("Energy Projection", 2, "<html><b>Governing Skills: Thrown Weapons</b><br>"
			+ "A character with energy projection can create blasts of energy or or elements that they can send surging towards an enemy target.  At character creation, the player chooses the type of damage the power will deal, and the power will permanently deal that type of damage to any foe, and these can range from light to energy to ice to nature and beyond.  Energy Projection itself deals physical damage.  Energy Projection is governed by Thrown Weapons<br>"
			+ "<br><b>Lvl 1 -</b> The projectile deals 1D6 X-type damage.<br>"
			+ "<b>Lvl 2 -</b> The projectile deals 1D6+1 X-type damage.<br>"
			+ "<b>Lvl 3 -</b> The projectile deals 1D6+2 X-type damage.<br>"
			+ "<b>Lvl 4 -</b> The projectile deals 1D6+3 X-type damage.<br>"
			+ "<b>Lvl 5 -</b> The projectile deals 2D6+1 X-type damage.<br>"
			+ "<b>Lvl 6 -</b> The projectile deals 2D6+2 X-type damage.<br>"
			+ "<b>Lvl 7 -</b> The projectile deals 2D6+3 X-type damage.<br>"
			+ "<b>Lvl 8 - </b>The projectile deals 2D6+4 X-type damage.<br>"
			+ "<b>Lvl 9 - </b>The projectile deals 2D6+5 X-type damage.<br>"
			+ "<b>Lvl 10 -</b> The projectile deals 2D6+6 X-type damage<br></html>", "1D6 X-type damage","1D6+1 X-type damage","1D6+2 X-type damage","1D6+3 X-type damage","2D6+1 X-type damage","2D6+2 X-type damage","2D6+3 X-type damage","2D6+4 X-type damage","2D6+5 X-type damage","2D6+6 X-type damage"),

	ENVIRONMENTAL_IMMUNITY("Environmental Immunity", 1, "<html><b>Governing Skills: Survival</b><br>"
			+ "The character is able to endure extreme conditions without adverse penalties.  This most commonly manifests in ways such as waterbreathing (or a lack of necessity to breathe at all for long periods of time), but it also includes the ability to endure extreme temperatures, extreme pressure, or unusual airborne compounds and atmospheres.  Environmental Immunity’s level determines the maximum level of the difficulty modifier a character may attempt to endure with lesser or completely negated negative effects.  In terms of benchmarks, Environmental Immunity Lvl. 5 would grant waterbreathing, and Lvl. 10 would allow a character to exist for some time in the vacuum of space (usually 48 hours before the effects of solar radiation begin to bear down on the character)<br></html>",  "", "", "", "", "", "", "", "", "", ""), 

	EXTRA_BODY_PART("Extra Body Part", 1, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	IMMUNITY("Immunity", 1, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	INFRAVISION("Infravision", 1, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	INVISIBILITY("Invisibility", 7, "<html><b>Governing Skills: Sneak, Resistance</b><br>"
			+ "A character with the ability to turn their bodies invisible activates this ability in the way a person might tense a muscle, and as such, this is an activated ability.  Invisibility is a fairly cut-and-dried ability, and increasing the level of invisibility simply increases the versatility and intensity of the power.  The longer a character remains invisible, the more likely the exertion of this ability affects their body.  A character can stay invisible for 6 rounds in combat consecutively or for roughly 5 minutes out-of-combat before they began to experience adverse effects<br>"
			+ "<br><b>Lvl. 1 -</b> The character can turn themselves invisible.  This leaves only a faint outline around their body, as if there is a light saran wrap form of a living being replacing their visual presence.  Invisibility is rolled on sneak with no modifiers.<br>"
			+ "<b>Lvl. 3 -</b> At this level the character disappears entirely from view, lowering the difficulty of all sneak roll difficulty modifiers to 1.  Characters who can see only on the visual spectrum will only suss out the presence of the target through alternative means or rolls of 20 or more.  As the level increases, this number rises, reaching a maximum peak of 30.<br>"
			+ "<b>Lvl. 7 -</b> The character can touch other objects, turning them invisible as well.  These objects must be of a mass less than the character’s own.<br>"
			+ "<b>Lvl. 10 -</b> The character can touch any object or individual, turning them invisible, up to 800 pounds in weight.<br></html>.","", "", "Sneak difficulty -1", "", "", "", "Lower mass object Invisibility", "", "", "800lbs max for object invisibility"), 

	LONGEVITY("Longevity", 1, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	MICROWAVE_PROJECTION("Microwave Projection", 4, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	MIMICRY("Mimicry", 10, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	MULTIPLICITY("Multiplicity", 6, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	NATURAL_WEAPONS("Natural Weapons", 2, "<html><b>Governing Skill: Varies, Depending on Type of Weapon</b><br>"
			+ "There are a wide variety of potential natural weapons available to any character, depending on their species.  Characters with more animalistic natures may choose to use claws or fangs or teeth, while extraterrestrial beings may have unusual appendages that are whip-like.  Natural Weapons deal only physical damage and are generally governed by Hand-to-Hand.  A Natural Weapon bolsters the unarmed damage output of a character by an additional +1 per level of Natural Weapons.  This stack can add up significantly.  At level 5 and level 10, instead of +1 to the unarmed damage output, Natural Weapons adds a 1D4 to the unarmed damage output, meaning a maxed out Natural Weapons ability adds a total of 2D4+8 to a character’s UDO.<br></html>", "UDO + 1", "UDO + 2", "UDO +3 ", "UDO + 4", "UDO 1d4+4 ", "UDO 1d4+5", "UDO 1d4+6", "UDO 1d4+7", "UDO 1d4+8 ", "UDO 2d4+8"),

	PLANT_MIMESIS("Plant Mimesis", 2, "<html><b>Governing Skills: Survival</b><br>"
			+ "This character can tap into the Green and use the natural abilities of the plants around them to alter their own physiology, providing new bonuses and physical talents to their own bodies temporarily.  Plant Mimesis functions as follows: a character’s reach into the Green increases as their level increases, providing a range of plant information to draw upon.  Characters must search for specific plants or species, in order to glean new abilities.  They may also tap into a random plant within range to gain or learn about new abilities.  As a character’s level increases, the depth and breadth of their ability increases as well.  Only one plant can be mimicked at a time.<br>"
			+ "<br>Glossary Note: A meta-ability would be considered a skill or power that would be an aberration from the genetic norm of a creature’s species - Solomon Grundy has meta-abilities.<br>"
			+ "<br>Potency Rate: The character’s ceiling on a skill in proportion to that skill level from the plant the character is mimicking<br>"
			+ "<br><b>Lvl. 1 - </b>The character can tap into any plant’s abilities within 1000 feet, excluding meta-abilities, at a potency rate of 33%.  <br>"
			+ "<b>Lvl. 3 - </b>The character can tap into any plant’s abilities within 5000 feet, excluding meta-abilities, at a potency rate of 33%.  <br>"
			+ "<b>Lvl. 5 - </b>The character can tap into any plant’s abilities within 3 miles, at a potency rate of 50%.  They may also tap into meta-abilities at a maximum level of 1. <br>"
			+ "<b>Lvl. 7 -</b> The character can tap into any plant’s abilities within 5 miles, at a potency rate of 75%.  They may also tap into meta-abilities at a maximum level of 3. <br>"
			+ "<b>Lvl. 10 -</b> The character can tap into any plant’s abilities within 10 miles, at a potency rate of 100%.  They may also tap into meta-abilities at a maximum level of 5.<br></html>",  "1000 feet  33%", "", " 5000 feet 33%", "", "3 miles 50%.  Meta-abilities max level 1.", "", " 5 miles 75%.  Meta-abilities max level of 3.", "", "",  "10 miles 100%.  Meta-abilities max level 5"),

	REDIMENSIONALITY("Redimensionality", 5, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	SHAPECHANGING("Shapechanging", 7, "<html><b>Governing Skills: Artist (Disguise), Bluff, Charm, Intimidation, Any Skill Utilized by Natural Abilities.</b><br>"
			+ "A shapeshifter can change their form and appearance at will.  As a shapeshifter’s talent increases, their ability to create increasingly more complex forms of shapeshifting grow as well.  The biggest limiting factor to a shapeshifter is their mass - a shapeshifter is not able to increase or decrease their mass at will.  As a shapeshifter’s abilities reach higher levels, they begin to receive stat boosts for duplicating the natural effects of the items or creatures they become.  These do not include metahuman abilities or magical enchantments.  Shapeshifting is governed most primarily by the Disguise specialization, and it is rolled against a Difficulty Level based upon that.  Extremely complex disguises - such as duplicating specific individuals - may not be available until some later levels.<br>"
			+ "<br><b>Lvl 4 - </b>At this level, characters can begin attempting to duplicate the appearances of specific individuals.<br>"
			+ "<b>Lvl 5 -</b> At this level, characters begin receiving stat boosts for items or creatures they attempt to duplicate.  These statistical boosts are temporary and only exist as long as the changed shape endures.  For item transformations, remember that your rolls will be governed by any ability tied to that transformation.  For example, turning one’s hand into a sword will require melee weapons rolls, and not hand-to-hand rolls.<br>"
			+ "<b>Lvl 10 -</b> At this level, you no longer need to make shapechanging rolls for anything you’ve attempted to duplicate before, aside from specific individuals.<br></html>", "", "", "", "Can begin duplicate attempts", " Begin receiving temporary stat boosts", "", "", "", "", "No roll needed for prior successes"),

	SONAR_SENSE("Sonar Sense", 3, "<html><b>Governing Skills: Perception</b><br>"
			+ "Different from super-hearing, Sonar Sense is an activated ability that temporarily replaces regular hearing so long as it is being used; Sonar Sense can permeate through objects and liquid mediums based on their functional thickness, or more accurately, based on their density.  Sonar Sense gains use as the level increases, becoming more versatile. <br>"
			+ "<br>Sonar Sense is always rolled as a Perception roll, but the difficulty level is determined by Sonar Sense’s current level, meaning a Level 5 Sonar Sense roll is rolled via Perception, but only if a character has Sonar Sense at Level 5 or more."
			+ "<br><br><b>Lvl. 1 - </b>Sonar Sense is acquired.<br>"
			+ "<b>Lvl. 3 - </b>At this level, characters can hear through water or other liquids to determine details about other objects or individuals.<br>"
			+ "<b>Lvl. 5 -</b> At this level, a character can begin to ping objects of note through solid walls.<br>"
			+ "<b>Lvl. 10 -</b> At this level, a character can ping objects through multiple barriers, potentially laying out an entire room in detail..<br></html>", "Sonar Sense acquired", "", "Can hear through liquids", "", "Can ping  through walls", "", "", "", "", "Advance ping through multiple barriers"), 

	SUPERATTRIBUTES("Superattributes", 3, "<html><b>Governing Skills: None</b><br>"
			+ "Superattributes are a flat boost to the raw attribute score of any attribute.  Any effects provided by those attributes are boosted accordingly.  Superattributes must be specialized.  In order to take a superattribute, an attribute must be chosen in order for this boost to take effect.  Characters may take multiple superattributes, each leveled independently of one another.<br></html>", "", "", "", "", "", "", "", "", "", ""), 

	SUPER_REFLEXES("Super Reflexes", 3, "<html><b>Governing Skills: Reflexes</b><br>"
			+ "Superattributes are a flat boost to the raw attribute score of any attribute.  Any effects provided by those attributes are boosted accordingly.  Superattributes must be specialized.  In order to take a superattribute, an attribute must be chosen in order for this boost to take effect.  Characters may take multiple superattributes, each leveled independently of one another.<br></html>", "", "", "", "", "", "", "", "", "", ""), 

	SUPER_COORDINATION("Super Coordination", 3, "<html><b>Governing Skills: Coordination</b><br>"
			+ "Superattributes are a flat boost to the raw attribute score of any attribute.  Any effects provided by those attributes are boosted accordingly.  Superattributes must be specialized.  In order to take a superattribute, an attribute must be chosen in order for this boost to take effect.  Characters may take multiple superattributes, each leveled independently of one another.<br></html>", "", "", "", "", "", "", "", "", "", ""), 

	SUPER_PHYSIQUE("Super Physique", 4, "<html><b>Governing Skills: Physique</b><br>"
			+ "Superattributes are a flat boost to the raw attribute score of any attribute.  Any effects provided by those attributes are boosted accordingly.  Superattributes must be specialized.  In order to take a superattribute, an attribute must be chosen in order for this boost to take effect.  Characters may take multiple superattributes, each leveled independently of one another.<br></html>", "", "", "", "", "", "", "", "", "", ""), 

	SUPER_KNOWLEDGE("Super Knowledge", 3, "<html><b>Governing Skills: Knowledge</b><br>"
			+ "Superattributes are a flat boost to the raw attribute score of any attribute.  Any effects provided by those attributes are boosted accordingly.  Superattributes must be specialized.  In order to take a superattribute, an attribute must be chosen in order for this boost to take effect.  Characters may take multiple superattributes, each leveled independently of one another.<br></html>", "", "", "", "", "", "", "", "", "", ""), 

	SUPER_PERCEPTION("Super Perception", 3, "<html><b>Governing Skills: Perception</b><br>"
			+ "Superattributes are a flat boost to the raw attribute score of any attribute.  Any effects provided by those attributes are boosted accordingly.  Superattributes must be specialized.  In order to take a superattribute, an attribute must be chosen in order for this boost to take effect.  Characters may take multiple superattributes, each leveled independently of one another.<br></html>", "", "", "", "", "", "", "", "", "", ""), 

	SUPER_PRESENCE("Super Presence", 3, "<html><b>Governing Skills: Presence</b><br>"
			+ "Superattributes are a flat boost to the raw attribute score of any attribute.  Any effects provided by those attributes are boosted accordingly.  Superattributes must be specialized.  In order to take a superattribute, an attribute must be chosen in order for this boost to take effect.  Characters may take multiple superattributes, each leveled independently of one another.<br></html>", "", "", "", "", "", "", "", "", "", ""), 

	SUPERBREATH("Superbreath", 2, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	SUPERSENSES("Supersenses", 1,  "<html><b>Governing Skills: Search, Surveillance, Survival</b><br>"
			+ "Characters with super-senses use their ability as natural boosts to rolls involving those senses in the governing skills.  This can provide an entirely new level of information based upon those rolls, and it dramatically increases the likelihood of success with those rolls, turning even low-rolled numbers into notable successes.  Each sense can bring a new world of information, and characters can take this power multiple times, once for each sense.  Supersenses do not lower difficulty modifiers, but rather, when activated, provide their own difficulty modifiers and their own results of success.<br></html>",  "", "", "", "", "", "", "", "", "", ""), 

	SUSPENDED_ANIMATION("Suspended Animation", 2, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	SUSTENANCE("Sustenance", 1, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	ULTRAVENTRILOQUISM("Ultraventriloquism", 2, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	VAMPIRISM("Vampirism", 3, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	X_RAY_VISION("X-Ray Vision", 3, "<html><b>Governing Skills: Perception</b><br>"
			+ "Different from super-vision, X-Ray Vision is an activated ability that temporarily replaces regular sight so long as it is being used; X-Ray vision can permeate through objects based on their functional thickness, or more accurately, based on their density.  X-Ray Vision gains use as the level increases, becoming more versatile.<br>"
			+ "<br>X-Ray Vision is always rolled as a Perception roll, but the difficulty level is determined by X-Ray Visions current level, meaning a Level 5 X-Ray Vision roll is rolled via Perception, but only if a character has X-Ray Vision at Level 5 or more.<br>"
			+ "<br><b>Lvl. 1 - </b>X-Ray Vision is acquired.<br>"
			+ "<b>Lvl. 3 - </b>At this level, characters can see through solid metal walls.<br>"
			+ "<b>Lvl. 5 - </b>At this level, a character can begin to choose a layer of objects to see through.<br>"
			+ "<b>Lvl. 10 - </b>At this level, a character can pick and choose which objects in their line of sight are transparent, including entirely excluding layers of an object intermittently.<br>"
			+ "</html>", "X-Ray vision acquired", "", "See through metal walls", "", "Choice of see through layer", "", "", "", "", "Pick and choose transparency"),

	ANIMATION("Animation", 2, "<html><b>Governing Skills: Persuasion</b><br>"
			+ "Your very touch can coax the cold, dead objects of the world to live, serving you in perfunctory ways so long as you maintain your focus on the object.  Note that animation does not give true sentience to an object; communicating with the inanimate is an entirely separate skillset.  As Animation increases in skill, larger and more complex objects may become subservient to your whim.  At Animation Level 5, you can animate two simultaneous objects.  At Animation Level 10, you can animate three simultaneous objects.<br></html>", "Gain Animation", "", "", "", "Animate up to 2 objects", "", "", "", "", "Animate up to 3 objects"), 

	ASTRAL_FORM("Astral Form", 2, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	EMPATHY("Empathy", 2, "<html><b>Governing Skills: Medicine (Psychology), Willpower</b>"
			+ "<br>A character with empathy can detect other character’s emotions and manipulate them using their willpower.<br>"
			+ "<br><b>Lvl. 1 - </b>This character can roll Medicine (Psychology) to attempt detecting an opposing character’s emotions.  If the opposing character has mental shielding of some sort, they will be too resistant to this discovery.<br>"
			+ "<b>Lvl. 3 - </b>At this level, a character may attempt to project their own active emotion into an opponent’s mind.  As the level of Empathy increases, characters may attempt harder and harder difficulty levels of Willpower to effect this change in demeanor.<br>"
			+ "<b>Lvl. 7 -</b> A character can detect complex emotions and began to project complex emotions.<br>"
			+ "<b>Lvl. 10 - </b>A character can project emotions that even said character is not currently feeling into a target’s mind at any difficulty level.  This can even drive a character mad.<br></html>", "Roll Psychology to detect emotions", "", "Project your own emotions", "", "", "", "Complex emotions available", "", "", "Convey different emotions/ Drive someone mad"), 

	ESP("ESP", 2,"<html><b>Governing Skills: Willpower</b><br>"
			+ "ESP provides a range of psychic amplification to any roll made with the normal senses via search, perception, survival, etc.  These rolls provide information through the psychic field, and as such, can be unpredictable if a character is not in visual range of his query.  The range of an ESP-related roll is equivalent to a radius of 10 times X in feet, where X is the level of ESP.  ESP rolls may provide unexpected results in taste, touch, smell, sight, or sound.  ESP is interfered with by electromagnetic fields and magical auras, spells, and barriers.  ESP also cannot penetrate psychic barriers, such as mental shields.  ESP, ultimately, provides visions related to a query based on the above parameters and can prove extremely useful in certain situations - more than supersenses, even - but also provide leads that are very indecipherable or even no lead at all, depending on the contributing factors in the attempt.  A Nat 1 ESP roll can even damage the character attempting it.<br></html>", "10 foot range", "20 foot range", "30 foot range", "40 foot range", "50 foot range", "60 foot range", "70 foot range", "80 foot range", "90 foot range", "100 foot range"), 

	EXPLOSION("Explosion", 5, "<html><b>Governing Skills: Willpower or Hand-to-Hand</b><br>"
			+ "A character with the ability to create explosions can utilize it in one of two ways, but only one way: either with psychically created explosions, or with tangible character-to-target contact.  Explosions can be delivered once a turn via the medium of their creation, and they deliver a concussive force that always pushes an opposing character backwards, depending on the level.  This explosion can do a standard amount of damage if the explosion created is external.  If the explosion is exposed to the internal components of a target, the damage may be more significant at the narrator’s discretion.  A character with explosion can also sacrifice Body Points of their own to add double the sacrificed amount as additional damage in the explosion.  The force of the explosion can be reduced at the user’s discretion, meaning that radius and pushback can be lowered from the maximum of the character’s current Explosion level.  Pushback from explosions and damage is charted out below:<br>"
			+ "<br><b>Lvl. 1 - </b>1D6; 1 sq. radius, 1 sq. pushback<br>"
			+ "<b>Lvl. 2 -</b> 1D6+1; 1 sq. radius, 2 sq. pushback<br>"
			+ "<b>Lvl. 3 -</b> 1D6+2; 1 sq. radius, 2 sq. pushback<br>"
			+ "<b>Lvl. 4 -</b> 1D6+3; 1 sq. radius, 3 sq. pushback<br>"
			+ "<b>Lvl. 5 -</b> 2D6+1; 4 sq. radius, 3 sq. pushback<br>"
			+ "<b>Lvl. 6 - </b>2D6+2; 4 sq. radius, 3 sq. pushback<br>"
			+ "<b>Lvl. 7 -</b> 2D6+3; 4 sq. radius, 4 sq. pushback<br>"
			+ "<b>Lvl. 8 - </b>2d6+4; 4 sq. radius, 4 sq. pushback<br>"
			+ "<b>Lvl. 9 - </b>2D6+5; 4 sq. radius, 5 sq. pushback<br>"
			+ "<b>Lvl. 10 -</b> 2D6+6; 9 sq. radius, 5 sq. pushback<br></html>","1D6; 1 sq. radius, 1 sq. pushback", "1D6+1; 1 sq. radius, 2 sq. pushback", "1D6+2; 1 sq. radius, 2 sq. pushback", "1D6+3; 1 sq. radius, 3 sq. pushback", "2D6+1; 4 sq. radius, 3 sq. pushback", "2D6+2; 4 sq. radius, 3 sq. pushback", "2D6+3; 4 sq. radius, 4 sq. pushback",  "2d6+4; 4 sq. radius, 4 sq. pushback", "2D6+5; 4 sq. radius, 5 sq. pushback", "2D6+6; 9 sq. radius, 5 sq. pushback"), 

	EXPULSION("Expulsion", 2,"<html><b>Governing Skills: Willpower, Arcane Lore</b><br>"
			+ "Expulsion is the ability to forcefully release any living being from possession, whether psychically or magically or spiritual.  This ability is rather cut-and-dried; for psychic and spiritual attempts at expulsion, a character will roll Willpower, and for magical attempts at expulsion, they roll Arcane Lore.  There is no level chart for Expulsion; instead, Expulsion must be rolled against a difficulty modifier determined by the level of the opposing power being used in the possession attempt.  For example, if the target is under the control of a character with Sorcery Lvl. 3, they will require Expulsion Lvl. 3 to counteract this.  Expulsion works on a range of abilities, including hypnosis.  Characters must have visual contact with the target of their expulsion attempts<br></html>.", "", "", "", "", "", "", "", "", "", ""),

	ILLUSION("Illusion", 4, "<html><b>Governing Skills: Artist, Willpower</b><br>"
			+ "Illusionists are able to create holographic projections with their minds, and the complexity of the Illusion and its size are dictated by the level of Illusion.  The idea for each illusion will be assigned a difficulty level as dictated by the narrator based on the player’s specifications.  Each illusion can roll a basic d20 against an enemy’s willpower to charm or intimidate the opponent.  At level 5, these illusions can also begin to create sound; at level 10, a player’s own charm and intimidation can be rolled against the opponent’s willpower instead of a raw d20.  Illusions cannot grow larger than 2 feet in height at level 1, but by level 10, illusions cast by a character can grow to be as large as a three-story building, and be rather elaborate.  They can also be used to disguise a player, so long as no sense other than sight is involved.  A disguise specialization may be necessary to complete a successful self-illusory attempt.<br></html>", "Gain Illusions", "", "", "", "Illusions can create sound", "", "", "", "", "Can add presence skills to rolls"), 

	LANGUAGE_COMPREHENSION("Language Comprehension", 2, "<html><b>Governing Skills: Languages</b><br>"
			+ "This character has a preternatural ability to understand and interpret other languages, excluding computer ops related languages.  As such, there are significant boosts to rolls involving other languages, even to the point of allowing intentionally indecipherable languages to be understood with ease.<br>"
			+ "<br><b>Lvl 1. - </b>All language rolls are rolled at advantage.<br>"
			+ "<b>Lvl 2. - </b>All language rolls are rolled at advantage.  The difficulty modifier of all Language rolls is decreased by 1.  Each language roll receives a bonus of +1.<br>"
			+ "<b>Lvl 3. - </b>All language rolls are rolled at advantage.  The difficulty modifier of all Language rolls is decreased by 1.  Each language roll receives a bonus of +2.<br>"
			+ "<b>Lvl 4. -</b> All language rolls are rolled at advantage.  The difficulty modifier of all Language rolls is decreased by 2.  Each language roll receives a bonus of +3.<br>"
			+ "<b>Lvl 5. -</b> All language rolls are rolled at double advantage.  The difficulty modifier of all Language rolls is decreased by 2.  Each language roll receives a bonus of +4.<br>"
			+ "<b>Lvl 6. -</b> All language rolls are rolled at double advantage.  The difficulty modifier of all Language rolls is decreased by 3.  Each language roll receives a bonus of +5.<br>"
			+ "<b>Lvl 7. - </b>All language rolls are rolled at double advantage.  The difficulty modifier of all Language rolls is decreased by 3.  Each language roll receives a bonus of +6.<br>"
			+ "<b>Lvl 8. - </b>All language rolls are rolled at double advantage.  The difficulty modifier of all Language rolls is decreased by 4.  Each language roll receives a bonus of +7.<br>"
			+ "<b>Lvl 9. -</b> All language rolls are rolled at double advantage.  The difficulty modifier of all Language rolls is decreased by 4.  Each language roll receives a bonus of +8.<br>"
			+ "<b>Lvl 10. -</b> All language rolls are rolled at triple advantage.  The difficulty modifier of all Language rolls is decreased by 4.  Each language roll receives a bonus of +8.<br></html>", "Roll at advantage", "Roll at advantage +1, Difficulty -1", "Roll at advantage +2, Difficulty -1", "Roll at advantage +3, Difficulty -2", "Roll at 2x advantage +4, Difficulty -2", "Roll at 2x advantage +5, Difficulty -3", "Roll at 2x advantage +6, Difficulty -3", "Roll at 2x advantage +7, Difficulty -4", "Roll at 2x advantage +8, Difficulty -4", "Roll at 3x advantage +8, Difficulty -4"),

	MENTAL_BLAST("Mental Blast", 3, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	POSSESSION("Possession", 5, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	POWER_SENSE("Power Sense", 2, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	PSYCHIC_BLAST("Psychic Blast", 3, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	PSYCHIC_MANIFESTATION("Psychic Manifestation", 2, "<html><b>Governing Skills: Willpower</b><br>"
			+ "A psychic manifestation can take the form of any particular type of weapon as envisioned by the mind of its user.  This manifestation, once created, is anchored to that particular design permanently - unless something significant changes in the mind or soul of its creator.  These manifestations are able to be moved mentally, and independently of the character’s normal combat actions, outside of summoning it.  A psychic manifestation has half the Body Points of its creator, but when destroyed, it can be summoned again.  A psychic manifestation also moves at a rate of 4 sq.<br>"
			+ "<br><b>Damage:</b><br>"
			+ "<b>Lvl. 1 -</b> 1D6<br>"
			+ "<b>Lvl. 2 -</b> 1D6+1<br>"
			+ "<b>Lvl. 3 -</b> 1D6+2<br>"
			+ "<b>Lvl. 4 -</b> 1D6+3<br>"
			+ "<b>Lvl. 5 - </b>2D6+1<br>"
			+ "<b>Lvl. 6 -</b> 2D6+2<br>"
			+ "<b>Lvl. 7 -</b> 2D6+3<br>"
			+ "<b>Lvl. 8 - </b>2d6+4<br>"
			+ "<b>Lvl. 9 - </b>2D6+5<br>"
			+ "<b>Lvl. 10 -</b> 2D6+6<br></html>", "1D6", "1D6+1", "1D6+2", "1D6+3", "2D6+1", "2D6+2", "2D6+3", "2D6+4", "2D6+5", "2D6+6"),

	SPEAK_WITH_ANIMALS("Speak with Animals", 2, "<html><b>Governing Skills: Languages</b><br>"
			+ "Speak with animals is only available to humanoid or plantoid creatures; for animals this ability does not exist, and they simply roll languages in the event that a creature cannot be readily understood.  This ability functions as Language Comprehension for Animals; Animals do not fall under Language Comprehension.<br>"
			+ "<br><b>Lvl 1. -</b> This character can roll Languages to understand animals.<br>"
			+ "<b>Lvl 2. -</b> The difficulty modifier of all Language rolls involving animals is decreased by 1.  Each language roll receives a bonus of +1.<br>"
			+ "<b>Lvl 3. -</b> The difficulty modifier of all Language rolls involving animals is decreased by 1.  Each language roll receives a bonus of +2.<br>"
			+ "<b>Lvl 4. - </b>The difficulty modifier of all Language rolls involving animals is decreased by 2.  Each language roll receives a bonus of +3.<br>"
			+ "<b>Lvl 5. - </b>The difficulty modifier of all Language rolls involving animals is decreased by 2.  Each language roll receives a bonus of +4.<br>"
			+ "<b>Lvl 6. - </b>The difficulty modifier of all Language rolls involving animals is decreased by 3.  Each language roll receives a bonus of +5.<br>"
			+ "<b>Lvl 7. - </b>The difficulty modifier of all Language rolls involving animals is decreased by 3.  Each language roll receives a bonus of +6.<br>"
			+ "<b>Lvl 8. - </b>The difficulty modifier of all Language rolls involving animals is decreased by 4.  Each language roll receives a bonus of +7.<br>"
			+ "<b>Lvl 9. - </b>The difficulty modifier of all Language rolls involving animals is decreased by 4.  Each language roll receives a bonus of +8.<br>"
			+ "<b>Lvl 10. -</b> All language rolls involving animals are rolled at advantage.  The difficulty modifier of all Language rolls is decreased by 4.  Each language roll receives a bonus of +8.<br></html>", "Roll Languages to speak with animals", "Difficulty -1, Modifier +1",  "Difficulty -1, Modifier +2", "Difficulty -2, Modifier +3", "Difficulty -2, Modifier +4",  "Difficulty -3, Modifier +5",  "Difficulty -3, Modifier +6",  "Difficulty -4, Modifier +7",  "Difficulty -4, Modifier +8", "Roll at advantage, Difficulty -4, Modifier +8"),

	SPEAK_WITH_INANIMATES("Speak with Inanimates", 3, "<html><b>Governing Skills: Languages</b><br>"
			+ "This ability functions as Language Comprehension for inanimate objects.<br>"
			+ "<br><b>Lvl 1. -</b> This character can roll Languages to understand inanimate objects.<br>"
			+ "<b>Lvl 2. - </b>The difficulty modifier of all Language rolls involving inanimate objects is decreased by 1.  Each language roll receives a bonus of +1.<br>"
			+ "<b>Lvl 3. -</b> The difficulty modifier of all Language rolls involving inanimate objects is decreased by 1.  Each language roll receives a bonus of +2.<br>"
			+ "<b>Lvl 4. -</b> The difficulty modifier of all Language rolls involving inanimate objects is decreased by 2.  Each language roll receives a bonus of +3.<br>"
			+ "<b>Lvl 5. -</b> The difficulty modifier of all Language rolls involving inanimate objects is decreased by 2.  Each language roll receives a bonus of +4.<br>"
			+ "<b>Lvl 6. - </b>The difficulty modifier of all Language rolls involving inanimate objects is decreased by 3.  Each language roll receives a bonus of +5.<br>"
			+ "<b>Lvl 7. - </b>The difficulty modifier of all Language rolls involving inanimate objects is decreased by 3.  Each language roll receives a bonus of +6.<br>"
			+ "<b>Lvl 8. -</b> The difficulty modifier of all Language rolls involving inanimate objects is decreased by 4.  Each language roll receives a bonus of +7.<br>"
			+ "<b>Lvl 9. - </b>The difficulty modifier of all Language rolls involving inanimate objects is decreased by 4.  Each language roll receives a bonus of +8.<br>"
			+ "<b>Lvl 10. - </b>All language rolls involving inanimate objects are rolled at advantage.  The difficulty modifier of all Language rolls is decreased by 4.  Each language roll receives a bonus of +8.<br></html>", "Roll Languages to speak to objects", "Difficulty -1 Moddifier +1", "Difficulty -1 Moddifier +2", "Difficulty -2 Moddifier +3", "Difficulty -2 Moddifier +4", "Difficulty -3 Moddifier +5", "Difficulty -3 Moddifier +6", "Difficulty -4 Moddifier +7", "Difficulty -4 Moddifier +8", "Roll at Advantage, Difficulty -4 Moddifier +8"), 

	SPEAK_WITH_PLANTS("Speak with Plants", 2, "<html><b>Governing Skills: Languages</b><br>"
			+ "Speak with Plants is only available to humanoid or animal creatures; for plantoids this ability does not exist, and they simply roll languages in the event that a plant cannot be readily understood.  This ability functions as Language Comprehension for Plants ; plants does not fall under Language Comprehension.<br>"
			+ "<br><b>Lvl 1. -</b> This character can roll Languages to understand plants.<br>"
			+ "<b>Lvl 2. - </b>The difficulty modifier of all Language rolls involving plants is decreased by 1.  Each language roll receives a bonus of +1.<br>"
			+ "<b>Lvl 3. -</b> The difficulty modifier of all Language rolls involving plants is decreased by 1.  Each language roll receives a bonus of +2.<br>"
			+ "<b>Lvl 4. - </b>The difficulty modifier of all Language rolls involving plants is decreased by 2.  Each language roll receives a bonus of +3.<br>"
			+ "<b>Lvl 5. - </b>The difficulty modifier of all Language rolls involving plants is decreased by 2.  Each language roll receives a bonus of +4.<br>"
			+ "<b>Lvl 6. -</b> The difficulty modifier of all Language rolls involving plants is decreased by 3.  Each language roll receives a bonus of +5.<br>"
			+ "<b>Lvl 7. - </b>The difficulty modifier of all Language rolls involving plants is decreased by 3.  Each language roll receives a bonus of +6.<br>"
			+ "<b>Lvl 8. -</b> The difficulty modifier of all Language rolls involving plants is decreased by 4.  Each language roll receives a bonus of +7.<br>"
			+ "<b>Lvl 9. -</b> The difficulty modifier of all Language rolls involving plants is decreased by 4.  Each language roll receives a bonus of +8.<br>"
			+ "<b>Lvl 10. - </b>All language rolls involving plants are rolled at advantage.  The difficulty modifier of all Language rolls is decreased by 4.  Each language roll receives a bonus of +8.<br></html>", "Roll Languages to speak to plants", "Difficulty -1 Moddifier +1", "Difficulty -1 Moddifier +2", "Difficulty -2 Moddifier +3", "Difficulty -2 Moddifier +4", "Difficulty -3 Moddifier +5", "Difficulty -3 Modifier +6", "Difficulty -4 Modifier +7", "Difficulty -4 Moddifier +8", "Roll at Advantage, Difficulty -4 Moddifier +8"), 

	TELEKINESIS("Telekinesis", 5, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	TELEPATHY("Telepathy", 5, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	DIMENSIONAL_TRAVEL("Dimensional Travel", 3, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	FLIGHT("Flight", 3, "<html><b>Governing Skills: Acrobatics (Flight)</b><br>"
			+ "The character can increase their height and rate of speed at each level increase.  Movement speed increases are only applicable while flying; flight cannot occur in certain indoor spaces, subject to the narrator’s discretion.  Narrative or non-combat uses of flight will be governed by the Flight specialization of Acrobatics.<br>"
			+ "<br><b>Lvl 1 - </b>200 feet, 4 sq. movement per round.<br>"
			+ "<b>Lvl 2 - </b>300 feet, 4 sq. movement per round.<br>"
			+ "<b>Lvl 3 -</b> 400 feet, 4 sq. movement per round.<br>"
			+ "<b>Lvl 4 -</b> 500 feet, 5 sq. movement per round.<br>"
			+ "<b>Lvl 5 - </b>1000 feet, 6 sq. movement per round.<br>"
			+ "<b>Lvl 6 - </b>2000 feet, 6 sq. movement per round.<br>"
			+ "<b>Lvl 7 - </b>5000 feet, 6 sq. movement per round.<br>"
			+ "<b>Lvl 8 - </b>10000 feet, 6 sq. movement per round.<br>"
			+ "<b>Lvl 9 - </b>You can fly in the vacuum of space, 7 sq. movement per round.<br>"
			+ "<b>Lvl 10 -</b> No flight restrictions aside from the narrator’s discretion, 8 sq. movement per round.<br></html>", "200ft, 4 squares per round", "300ft, 4 squares per round", "400ft, 4 squares per round", "500ft,  5 squares per round", "1000ft, 6 squares per round", "2000ft, 6 squares per round", "5000ft, 6 squares per round", "10000ft, 6 squares per round", "Space flight, 7 squares per round", "No restrictions, 8 squares per round"), 

	SUPERJUMP("Superjump", 2, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),
	
	TELEPORTATION("Teleportation", 3, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	ENERGY_ABSORPTION("Energy Absorption", 3, "<html><b>Governing Skills: Physique</b><br>"
			+ "A character with energy absorption can channel kinetic or electrical energies to increase the amount of damage they deal on their next attack.  By taking damage, a character with Energy Absorption can utilize a percentage of the body points dealt to them to increase their next attack’s damage.  The percentage of damage redirected is always the level of Energy Absorption multiplied by 10, and that damage is always rounded down.  Energy Absorption is used in lieu of blocking or dodging as a defensive response to an attack.  It also can be used in or out of combat to divert large releases of energy from things such as broken engines or waves of cosmic radiation.  Sometimes, these will provide benefits for the character’s next attack, even if damage is not sustained.<br></html>", "10% redirection", "20% redirection", "30% redirection", "40% redirection", "50% redirection", "60% redirection", "70% redirection", "80% redirection", "90% redirection", "100% redirection"), 

	FORCEFIELD("Forcefield", 6, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	HEALING("Healing", 7, "<html><b>Governing Skills: Medicine, Science, Willpower</b><br>"
			+ "Healers have a twofold ability - one, to restore body points to PCs and NPCs, and two, to fix wounds that exist both narratively and non-narratively.  Healers can restore body points out of pools devoted to individual players.  These pools increase as the healers’ level increases, as well as their own body points.  Healers can never resurrect a character from the dead, nor are they able to repair damage done by certain high level magic casters or reality manipulators.  They also cannot heal damage from anti-matter.  If a wound has pre-existed for a long period of time, the body likely has adapted to function around it, so a healer could not, say, heal paralysis from 10 years ago, or restore an eye lost 5 years ago."
			+ "<br>"
			+ "<br><b>Lvl 1 - </b>Healers can restore up to 10 Body Points per character, plus 5% of their own health.  They can repair minor wounds to the body.  Body points can be restored at a rate of 1D6 per turn.<br>"
			+ "<b>Lvl 2 -</b> Healers can restore up to 12 Body Points per character, plus 5% of their own health.  They can repair notable wounds to the body.  Body points can be restored at a rate of 1D6+1 per turn.<br>"
			+ "<b>Lvl 3 -</b> Healers can restore up to 15 Body Points per character, plus 5% of their own health.  They can repair open wounds to the body quickly.  Body points can be restored at a rate of 1D6+2 per turn.<br>"
			+ "<b>Lvl 4 -</b> Healers can restore up to 15 Body Points per character, plus 10% of their own health.  They can repair open wounds to the body quickly, including stitching bones back together in combat.  Body points can be restored at a rate of 1D6+3 per turn.<br>"
			+ "<b>Lvl 5 - </b>Healers can restore up to 18 Body Points per character, plus 10% of their own health.  In or out of combat, they can repair most simple injuries, and they can also eradicate the effects of poison with concentration.  Body points can be restored at a rate of 2D6 per turn.<br>"
			+ "<b>Lvl 6 - </b>Healers can restore up to 18 Body Points per character, plus 15% of their own health.  In or out of combat, they can repair most simple injuries, and they can also eradicate the effects of poison with concentration.  Body points can be restored at a rate of 2D6+1 per turn.<br>"
			+ "<b>Lvl 7 -</b> Healers can restore up to 18 Body Points per character, plus 25% of their own health.  In or out of combat, they can repair most simple injuries, and they can also eradicate the effects of poison with concentration.  Body points can be restored at a rate of 2D6+2 per turn.<br>"
			+ "<b>Lvl 8 -</b> Healers can restore up to 20 Body Points per character, plus 25% of their own health.  In or out of combat, they can repair injuries of nearly any kind outside of the most complex or time-intensive kinds, and they can also eradicate the effects of poison with concentration.  Body points can be restored at a rate of 2D6+3 per turn.<br>"
			+ "<b>Lvl 9 -</b> Healers can restore up to 20 Body Points per character, plus 33% of their own health.  In or out of combat, they can repair injuries of nearly any kind outside of the most complex or time-intensive kinds (including regrowing limbs), and they can also eradicate the effects of poison with concentration.  Body points can be restored at a rate of 2D6+5 per turn.<br>"
			+ "<b>Lvl 10 - </b>Healers can restore up to 20 Body Points per character, plus 50% of their own health.  In or out of combat, they can repair injuries of nearly any kind (including regrowing limbs and organs), and they can also eradicate the effects of poison with concentration.  Body points can be restored at a rate of 3D6+4 per turn."
			+ "<br>"
			+ "<br>If the amount of Body Points restored would go over the maximum Body Point total for a character, those healed points are unused and remain in the pool."
			+ "<br>"
			+ "<br>There are two types of damage: body point damage and penalty damage, that may result in a stat increase.  Body points can be restored at any time by physical contact, but penalty eradication must be done via d20 rolls on skill checks.  For healers, in-combat healing on skill checks must first match the level of healing to the difficulty level before attempting to heal via either Healing or Medicine.  Additional skill checks may be attached for complicated injuries or unusual physiologies at the narrator’s discretion."
			+ "<br></html>", "10BP + 5% at 1d6 per turn", "12BP + 5% at 1d6+1 per turn", "15BP + 5% at 1d6+2 per turn", "15BP + 10% at 1d6+3 per turn", "18BP + 10% at 2d6 per turn", "18BP + 15% at 2d6+1 per turn",  "18BP + 25% at 2d6+2 per turn", "20BP + 25% at 2d6+3 per turn", "20BP + 33% at 2d6+5 per turn", "20BP + 50% at 3d6+4 per turn"), 

	INVULNERABILITY("Invulnerability", 6, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	MENTAL_SHIELD("Mental Shield", 3, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	NATURAL_ARMOR("Natural Armor", 3, "<html><b>Governing Skills: None</b><br>"
			+ "Natural Armor is a layer of skin or exoskeleton that can withstand attacks of a certain magnitude.  This deflected damage can measure in a range of 10-20 Body Points in a battle, depending on the level of Natural Armor.  Natural Armor has a pool of 10+X Body Points that can be deflected at any point in combat via d4 rolls. Each d4 roll dictates the amount of damage negated by Natural Armor, up until the pool is depleted in combat.  At level 5, the d4 becomes a d6, and at level 10, it becomes a d8.  The X in 10+X is the level of Natural Armor.<br></html>", 
			"11 BP at d4", "12 BP at d4", "13 BP at d4", "14 BP at d4", "15 BP at d6", "16 BP at d6", "17 BP at d6", "18 BP at d6", "19 BP at d6", "20BP at d8"),

	POWER_SHIELD("Power Shield", 3, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	RESURRECTION("Resurrection", 6, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	AIR_MANIPULATION("Air Manipulation", 5, "Description Incoming", "1D6 air damage", "1D6+2 air damage", "1D6+4 air damage, bindings available", "2D6+1 air damage", "2D6+2 air damage", "2D6+3 air damage", "2D6+4 air damage", "3D6+1 air damage", "3D6+2 air damage", "3D6+3 air damage"),

	DARKNESS_MANIPULATION("Darkness Manipulation", 5, "<html><b>Governing Skills: Stealth, Willpower, Intimidation, Survival</b><br>"
			+ "A character with Darkness Manipulation can manipulate and use the stuff of shadows to commit dark, dark acts.  This can include traveling through shadows, concealing oneself within shadows and using shadows to bind or harm others.  Darkness, however, is defined by the amount of light emanating from any particular location. There is a Shade Setting in every location, set by the amount of light.  This Shade Setting defines bonuses and damage dealt.  The brighter a location is, the more focused the shadows are, dealing more damage, but the harder it is to accrue a significant amount of shadowstuff for your efforts.  The darker it is, the more shadowstuff is available to you, but the less potent those shadows are when they strike.  A slightly overcast alleyway would be a 5 on the Shade Setting; pitch black darkness is a 10, and pure brightness is a 1.<br>"
			+ "<br><b>Shade Settings:</b><br>"
			+ "<b>Lvl 1. - </b>Damage is increased by +5, but die modifiers for rolls made with Darkness Manipulations receive a -5 bonus.<br>"
			+ "<b>Lvl 2. - </b>Damage is increased by +4, but die modifiers for rolls made with Darkness Manipulations receive a -4 bonus.<br>"
			+ "<b>Lvl 3. -</b> Damage is increased by +3, but die modifiers for rolls made with Darkness Manipulations receive a -2 bonus.<br>"
			+ "<b>Lvl 4. -</b> Damage is increased by +2, but die modifiers for rolls made with Darkness Manipulations receive a -1 bonus.<br>"
			+ "<b>Lvl 5. - </b>Default.  No modifiers.<br>"
			+ "<b>Lvl 6.  -</b> Damage is decreased by -2, but die modifiers for rolls made with Darkness Manipulations receive a +1 bonus.<br>"
			+ "<b>Lvl 7.  -</b> Damage is decreased by -2, but die modifiers for rolls made with Darkness Manipulations receive a +2 bonus.<br>"
			+ "<b>Lvl 8.  -</b> Damage is decreased by -3, but die modifiers for rolls made with Darkness Manipulations receive a +3 bonus.<br>"
			+ "<b>Lvl 9.  -</b> Damage is decreased by -4, but die modifiers for rolls made with Darkness Manipulations receive a +4 bonus.<br>"
			+ "<b>Lvl 10.  - </b>Damage is decreased by -5, but die modifiers for rolls made with Darkness Manipulations receive a +5 bonus.<br>"
			+ "<br><b>Darkness Manipulation Level Increases</b><br>"
			+ "<b>Lvl 1. - </b>A character may draw upon shadows to deal 1D6 shadow damage.  They may also use Shadowing to conceal themselves in shadows.<br>"
			+ "<b>Lvl 2. -</b> A character may draw upon shadows to deal 1D6+2 shadow damage.  The character may now use shadows to move in a concealed way, making Sneak rolls.<br>"
			+ "<b>Lvl 3. - </b>A character may draw upon shadows to deal 1D6+4 shadow damage.  The character may now create weak bindings with their shadows, drawing on their willpower.<br>"
			+ "<b>Lvl 4. -</b> A character may draw upon shadows to deal 2D6+1 shadow damage.  <br>"
			+ "<b>Lvl 5. -</b> A character may draw upon shadows to deal 2D6+2 shadow damage.  A character can sink into shadows and move through them, so long as they are connected to one another.<br>"
			+ "<b>Lvl 6. -</b> A character may draw upon shadows to deal 2D6+3 shadow damage.  Using artist rolls, shadows can be used to draw images or write words in two-dimensional form.<br>"
			+ "<b>Lvl. 7 -</b> A character may draw upon shadows to deal 2D6+4 shadow damage.<br>"
			+ "<b>Lvl. 8 -</b> A character may draw upon shadows to deal 3D6+1 shadow damage.  A player may cloak themselves in a protective layer of shadow that adds an additional 10 Body Points of damage for the duration of each combat, but when those 10 Body points are expended the layer disappears.<br>"
			+ "<b>Lvl. 9 -</b> A character may draw upon shadows to deal 3D6+2 Shadow damage, and they can create crude two-dimensional shadow minions to perform basic tasks, each with 10 Body points.  These created minions cost 4 Body Points to create.<br>"
			+ "<b>Lvl. 10 -</b> A character may draw upon shadows to deal 3D6+3 shadow damage.  A character can sink into shadows and move through them, so long as they are in view of one another.<br></html>", "1D6 shadow damage", "1D6+2 shadow damage, sneak bonus", "1D6+4 shadow damage, bindings available", "2d6+1 shadow damage", "2d6+2 shadow damage, shadow sink", "2d6+3 shadow damage, shadow draw", "2d6+4 shadow damage", "3d6+1 shadow damage, shadow cloak 10BP", "3d6+2 shadow damage, create minions", "3d6+3 shadow damage, shadow travel"),

	DENSITY_MANIPULATION("Density Manipulation", 5, "<html><b>Governing Skills: Athletics</b><br>"
			+ "Density Manipulation focuses a character’s talents on allowing their mass to phase or shift through objects of varying degrees of density, and at higher levels can be used to increase the range of the ability to objects or individuals specifically in tangible contact with the power’s user.  Density Manipulation has an on/off switch.<br>"
			+ "<br><b>At Density Manipulation On:</b> The character (or any character in contact at certain levels) cannot take damage, nor can they deal damage.  A character with Acrobatics rolls at Double Advantage in On Mode, but Speed Manipulation is reduced to zero.  A character with flight increases their height ceiling by double in On Mode, but while floating, reduces the number of squares they can move by half in a horizontal position.  <br>"
			+ "<br><b>At Density Manipulation Off:</b> The character’s abilities function as normal. <br>"
			+ "<br>A character’s attempt to walk or move in On Mode is governed by Athletics, because the user’s mass has been reduced to an infinitesimal amount, meaning that passing through objects is difficult to manage.  However, the lower a character’s physique, the easier it is to phase through objects.  This makes Density Manipulation a unique skill.  Density Manipulation also uses Athletics to determine the length of time a character can remain out-of-phase with the solid world.  Because a character cannot intake air during On Mode, a character must hold their breath when shifting out of a solid state.  Athletics determines the number of rounds a character may phase by dividing by three and rounding up. <br>"
			+ "<br>A character begins their turn in either On or Off mode and remains that way until their next turn. <br>"
			+ "<br><b>Lvl. 1 -</b> A character may utilize Density Manipulation for themselves. <br>"
			+ "<b>Lvl. 3 - </b>A character may utilize Density Manipulation for themselves, their equipment and one additional item. <br>"
			+ "<b>Lvl. 5 - </b>A character may utilize density manipulation for themselves, their equipment, their packs, and their weapons.  They may also partially phase, meaning they can resolidify during their action turn with part of their body. <br>"
			+ "<b>Lvl. 7 - </b>At this level, a character may now touch another object or individual to affect them. <br>"
			+ "<b>Lvl. 9 - </b>At this level, a character may touch two other individuals to affect them. <br>"
			+ "<b>Lvl. 10 - </b>At this level, a character may partially affect other individuals in the same way the character could affect themselves at Level 5. <br></html>", "Self use", "", "usable for equipment", "", "usable on packs and weapons", "", "Object/Person touch", "", "Touch two individuals", "Touch two individuals at level 5 rate"),

	EARTH_MANIPULATION("Earth Manipulation", 5, "Description Incoming", "1D6 earth damage", "1D6+2 earth damage", "1D6+4 earth damage, bindings available", "2D6+1 earth damage", "2D6+2 earth damage", "2D6+3 earth damage", "2D6+4 earth damage", "3D6+1 earth damage", "3D6+2 earth damage", "3D6+3 earth damage"),

	ELECTRICITY_MANIPULATION("Electricity Manipulation", 6, "Description Incoming", "1D6 electric damage", "1D6+2 electric damage", "1D6+4 electric damage, bindings available", "2D6+1 electric damage", "2D6+2 electric damage", "2D6+3 electric damage", "2D6+4 electric damage", "3D6+1 electric damage", "3D6+2 electric damage", "3D6+3 electric damage"),

	ENERGY_MANIPULATION("Energy Manipulation", 9, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),
	
	FIRE_MANIPULATION("Fire Manipulation", 5, "Description Incoming", "1D6 fire damage", "1D6+2 fire damage", "1D6+4 fire damage, bindings available", "2D6+1 fire damage", "2D6+2 fire damage", "2D6+3 fire damage", "2D6+4 fire damage", "3D6+1 fire damage", "3D6+2 fire damage", "3D6+3 fire damage"),

	ICE_MANIPULATION("Ice Manipulation", 5, "Description Incoming", "1D6 ice damage", "1D6+2 ice damage", "1D6+4 ice damage, bindings available", "2D6+1 ice damage", "2D6+2 ice damage", "2D6+3 ice damage", "2D6+4 ice damage", "3D6+1 ice damage", "3D6+2 ice damage", "3D6+3 ice damage"),

	LIGHT_MANIPULATION("Light Manipulation", 5, "<html><b>Governing Skills: Marksmanship, Willpower, Artist</b><br>"
			+ "This individual can harness create and harness raw light in its most primal form and focus it into basic solid construction.  Working inverse of Darkness Manipulation, Light Manipulation functions on the same Shade Setting mechanic but in the complete opposite way.  The chart is below:<br>"
			+ "<br><b>Shade Settings:</b><br>"
			+ "<b>Lvl 1. -</b> Damage is decreased by -5, but die modifiers for rolls made with Light Manipulation receive a +5 bonus.<br>"
			+ "<b>Lvl 2. - </b>Damage is decreased by -4, but die modifiers for rolls made with Light Manipulation receive a +4 bonus.<br>"
			+ "<b>Lvl 3. - </b>Damage is decreased by -3, but die modifiers for rolls made with Light Manipulation receive a +2 bonus.<br>"
			+ "<b>Lvl 4. -</b> Damage is decreased by -2, but die modifiers for rolls made with Light Manipulation receive a +1 bonus.<br>"
			+ "<b>Lvl 5. -</b> Default.  No modifiers.<br>"
			+ "<b>Lvl 6.  -</b> Damage is increased by +2, but die modifiers for rolls made with Light Manipulation receive a -1 bonus.<br>"
			+ "<b>Lvl 7.  - </b>Damage is increased by +2, but die modifiers for rolls made with Light Manipulation receive a -2 bonus.<br>"
			+ "<b>Lvl 8.  -</b> Damage is increased by +3, but die modifiers for rolls made with Light Manipulation receive a -3 bonus.<br>"
			+ "<b>Lvl 9.  -</b> Damage is increased by +4, but die modifiers for rolls made with Light Manipulation receive a -4 bonus.<br>"
			+ "<b>Lvl 10.  -</b> Damage is increased by +5, but die modifiers for rolls made with Light Manipulation receive a -5 bonus.<br><br>"
			+ "<b>Light Manipulation Level Increases</b><br>"
			+ "<b>Lvl 1. - </b>A character may unleash a blast of light to deal 1D6 light damage.  They may also create blinding flashes of light<br>"
			+ "<b>Lvl 2. -</b> A character may unleash a blast of light to deal 1D6+2 light damage.  The character will begin to not suffer penalties for blinding lights.<br>"
			+ "<b>Lvl 3. - </b>A character may unleash a blast of light to deal 1D+4 light damage.   The character may now create weak bindings with their light, drawing on their willpower.<br>"
			+ "<b>Lvl 4. - </b>A character may unleash a blast of light to deal 2D6+1 light damage.    <br>"
			+ "<b>Lvl 5. - </b>A character may unleash a blast of light to deal 2D6+2 light damage.   Light attacks can be used to, instead of damage, deal concussive blasts that both temporarily deafen or blind an opponent, stunning them for a round.<br>"
			+ "<b>Lvl 6. -</b> A character may unleash a blast of light to deal 2D6+3 light damage.   Using artist rolls, light can be used to draw images or write words in two-dimensional form.<br>"
			+ "<b>Lvl. 7 -</b> A character may unleash a blast of light to deal 2D6+4 light damage.  <br>"
			+ "<b>Lvl. 8 - </b>A character may unleash a blast of light to deal 3D6+1 light damage. A player may cloak themselves in a protective layer of light that adds an additional 10 Body Points of damage for the duration of each combat, but when those 10 Body points are expended the layer disappears.<br>"
			+ "<b>Lvl. 9 - </b>A character may unleash a blast of light to deal 3D6+2 light damage, and they can create crude two-dimensional holographic images that lack substance but can be finely detailed.<br>"
			+ "<b>Lvl. 10 -</b> A character may unleash a blast of light to deal 3D6+3 light damage.  A character’s two-dimensional images become three-dimensional, hard light constructs.<br></html>", "1D6 light damage, blinding flashes", "1D6+2 light damage", "1D6+4 light damage, Bindings available", "2D6+1 light damage", "2D6+2 light damage, can choose to impair", "2D6+3 light damage, Light drawing", "2D6+4 light damage", "3D6+1 light damage, 10BP Light cloak", "3D6+2 light damage",  "3D6+3 light damage, hard constructs available"),

	KINETIC_MANIPULATION("Kinetic Manipulation", 8, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	MAGNETIC_MANIPULATION("Magnetic Manipulation", 6, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	MATTER_MANIPULATION("Matter Manipulation", 9, "<html><b>Governing Skills: Science, Resistance, Acrobatics (Flying (Self)), Marksmanship, Physique</b><br>"
			+ "Characters with Matter Manipulation can command different types of inorganic matter in gas, solid or liquid form.  They also can, eventually, turn their own body into raw forms of inorganic matter.  These forms of matter materialize as raw elements or basic compounds.  The character, however, cannot manipulate any form of organic matter.  An element’s number on the periodic table determines the difficulty in manipulating or creating it.<br>"
			+ "<br><b>Lvl. 1 - </b>The hero can alter the shape of raw forms of matter in simple ways, increasing or decreasing the amount present, or changing its shape.<br>"
			+ "<b>Lvl. 2 - </b>The hero can produce raw elements from their body in small projectile quantities.  This can be used as a marksmanship blast at 2D6, and is increased or decreased based upon the element.<br>"
			+ "<b>Lvl. 3 - </b>The character can begin combining raw elements to create basic chemical compounds at this level.<br>"
			+ "<b>Lvl. 4 -</b> The character can move or transport quantities of a raw element at this level.  This quantity starts at 100 pounds, and it doubles for each subsequent level.<br>"
			+ "<b>Lvl. 5 - </b>The character can transmute one form of raw element into another form of raw element. <br>"
			+ "<b>Lvl. 6 - </b>The character can use raw elements as a form of transportation, gathering a transportable amount.  A character’s speed is still set at their base movement rate.<br>"
			+ "<b>Lvl. 7 -</b> The character can now target themselves in the process of transmutation, turning into one specific raw element.  This can allow them to become elastic, diffuse as gas, or extra-durable (gaining a shield-like quality).  The only thing the character is incapable of doing is increasing or reducing their own size.  Each element provides different properties, subject to the narrator’s discretion.<br>"
			+ "<b>Lvl. 8 -</b> Projectile blasts now deal 2D6+6 damage.<br>"
			+ "<b>Lvl. 9 - </b>The hero can spontaneously create matter.  The amount available is subject to the complexity of the raw element.<br>"
			+ "<b>Lvl. 10 - </b>The character can alter and reshape matter into complex forms with multiple forms of inorganic compounds, such as creating swing sets, dishware, etc.<br></html>", "Simple alterations", "Can create 2D6 projectiles", "Raw combinations available", "Can begin to transport elements", "Transmutations available", "Can create transportation", "Can change self composition aside from size", "Projectiles now 2D6+6", "Spontaneous matter creation", "Complex matter reshaping available"), 

	MECHANICAL_MANIPULATION("Mechanical Manipulation", 8, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	PLANT_MANIPULATION("Plant Manipulation", 5, "<html><b>Governing Skills: Melee Weapons, Marksmanship, Survival</b><br>"
			+ "This individual is in tune with the Green and can generate or alter any natural elements around them.  Generally speaking, individuals with plant manipulation feel a kinship with the Green, meaning that their relationship with the natural world is strong and affectionate.  Over time, they may become more removed from other forms of mortal creatures, and at level 10 of Plant Manipulation, the character must make a Willpower roll to determine if they will shutter themselves away inside of a cocoon of trees and flowers.  Plant Manipulation weakens the further a character gets from Earth, so a character may consider bringing many samples of dirt with them if they leave the planet - if a character is able to anchor their feet in mud, dirt or other raw earthen materials, they gain advantage on Plant Manipulation rolls.  If a character is off-Earth without any raw materials of Earth, they roll at disadvantage.<br><br>"
			+ "<b>Plant Manipulation Level Increases</b><br>"
			+ "<b>Lvl. 1 -</b> A character may release vines or needles to deal 1D6 natural damage.  They may also part plant life to avoid obstacles for themselves.<br>"
			+ "<b>Lvl. 2 -</b> A character may release vines or needles to deal 1D6+2 natural damage.  The character may begin growing plants to twice their normal level at this point.<br>"
			+ "<b>Lvl. 3 -</b> A character may release vines or needles to deal 1D6+4 natural damage.  The character can create bindings with their plants.<br>"
			+ "<b>Lvl. 4 -</b> A character may release vines or needles to deal 2D6+1 natural damage.<br>"
			+ "<b>Lvl. 5 -</b> A character may release vines or needles to deal 2D6+2 natural damage.  The character can now spontaneously create any plant, and grow them up to 4x their normal size.<br>"
			+ "<b>Lvl. 6 -</b> A character may release vines or needles to deal 2D6+3 natural damage.  The character can now contort individual aspects of plants to suit their wills, such as creating a single massive leaf to shield them from an attack.<br>"
			+ "<b>Lvl. 7 -</b> A character may release vines or needles to deal 2D6+4 natural damage.<br>"
			+ "<b>Lvl. 8 -</b> A character may release vines or needles to deal 3D6+1 natural damage.  The character can begin to sheathe themselves in a thick bark-like external layer that has its own independent 10 Body Point statistic; this layer can last the duration of combat, but once destroyed, disappears.  If the character has not already done so, their skin begins to turn green at this stage.<br>"
			+ "<b>Lvl. 9 -</b> A character may release vines or needles to deal 3D6+2 natural damage.  Their plants can now reach 6x their normal size.<br>"
			+ "<b>Lvl. 10 -</b> A character may release vines or needles to deal 3D6+3 natural damage.  Their plants can now reach 10x their normal size.<br></html>", "1D6 natural damage", "1D6+2 natural damage", "1D6+4 natural damage, bindings available", "2D6+1 natural damage", "2D6+2 natural damage", "2D6+3 natural damage", "2D6+4 natural damage", "3D6+1 natural damage, 10BP bark skin", "3D6+2 natural damage", "3D6+3 natural damage"),


	SIZE_MANIPULATION("Size Manipulation", 5, "<html><b>Governing Skills: Willpower</b><br>"
			+ "A character with size manipulation can grow or shrink at will.  Each increase or decrease in size has different benefits or obstacles involved in the process.  A character’s natural size contains their base stats, but any growth or shrinkage outside of that size results in bonuses or penalties.<br>"
			+ "<br><b>Growth Rate</b><br>"
			+ "<b>2x Natural Size (Lvl 1.)</b> - The following skills have their difficulty modifier lowered by 1 and gain a +1 bonus to their rolls: all physique skills, physique, and intimidation.  The following skills have their difficulty modifier increased by 1 and gain a -1 penalty to their rolls: sneak, charm, disguise, Coordination. Characters gain a +1D6 modifier to hand-to-hand damage.<br>"
			+ "<b>3x Natural Size (Lvl 2.)</b> - The following skills have their difficulty modifier lowered by 1 and gain a +2 bonus to their rolls: all physique skills, physique, and intimidation.  The following skills have their difficulty modifier increased by 2 and gain a -2 penalty to their rolls: sneak,  charm, disguise, Coordination. Hand-to-Hand suffers a -1 modifier.  Characters gain a +1D6+2 modifier to hand-to-hand damage.<br>"
			+ "<b>4x Natural Size (Lvl 3.)</b> - The following skills have their difficulty modifier lowered by 2 and gain a +2 bonus to their rolls: all physique skills, physique, and intimidation.  The following skills have their difficulty modifier increased by 3 and gain a -2 penalty to their rolls: sneak,  charm, disguise, Coordination. Hand-to-Hand suffers a -2 modifier. Characters gain a +1D6+4 modifier to hand-to-hand damage.<br>"
			+ "<b>5x Natural Size (Lvl 4.)</b> - The following skills have their difficulty modifier lowered by 2 and gain a +3 bonus to their rolls: all physique skills, physique, and intimidation.  The following skills have their difficulty modifier increased by 3 and gain a -3 penalty to their rolls: sneak,  charm, disguise, Coordination. Hand-to-Hand suffers a -2 modifier. Characters gain a +2D6 modifier to hand-to-hand damage.<br>"
			+ "<b>6x Natural Size (Lvl 5.)</b> - The following skills have their difficulty modifier lowered by 3 and gain a +4 bonus to their rolls: all physique skills, physique, and intimidation.  The following skills have their difficulty modifier increased by 3 and gain a -4 penalty to their rolls: sneak, charm, disguise, Coordination. Hand-to-Hand suffers a -3 modifier. Characters gain a +2D6+2 modifier to hand-to-hand damage.<br>"
			+ "<b>7x Natural Size (Lvl 6.)</b> - The following skills have their difficulty modifier lowered by 3 and gain a +5 bonus to their rolls: all physique skills, physique, and intimidation.  The following skills have their difficulty modifier increased by 3 and gain a -5 penalty to their rolls: sneak, charm, disguise, Coordination. Hand-to-Hand suffers a -3 modifier. Characters gain a +2D6+3 modifier to hand-to-hand damage.<br>"
			+ "<b>8x Natural Size (Lvl 7.) </b>- The following skills have their difficulty modifier lowered by 4 and gain a +5 bonus to their rolls: all physique skills, physique, and intimidation.  The following skills have their difficulty modifier increased by 4 and gain a -5 penalty to their rolls: sneak, charm, disguise, Coordination. Hand-to-Hand suffers a -3 modifier. Characters gain a +2D6+4 modifier to hand-to-hand damage.<br>"
			+ "<b>9x Natural Size (Lvl 8.) </b>- The following skills have their difficulty modifier lowered by 4 and gain a +6 bonus to their rolls: all physique skills, physique, and intimidation.  The following skills have their difficulty modifier increased by 5 and gain a -6 penalty to their rolls: sneak, charm, disguise, Coordination. Hand-to-Hand suffers a -4 modifier. Characters gain a +3D6+1 modifier to hand-to-hand damage.<br>"
			+ "<b>10x Natural Size (Lvl 9.)</b> - The following skills have their difficulty modifier lowered by 4 and gain a +7 bonus to their rolls: all physique skills, physique, and intimidation.  The following skills have their difficulty modifier increased by 6 and gain a -7 penalty to their rolls: sneak, charm, disguise, Coordination. Hand-to-Hand suffers a -5 modifier. Characters gain a +3D6+2 modifier to hand-to-hand damage.<br>"
			+ "<b>10x Natural Size EXTREME (Lvl 10.)</b> - The following skills have their difficulty modifier lowered by 5 and gain a +8 bonus to their rolls: all physique skills, physique, and intimidation.  The following skills have their difficulty modifier increased by 6 and gain a -7 penalty to their rolls: sneak, charm, disguise, Coordination. Hand-to-Hand suffers a -5 modifier but is rolled at advantage. Characters gain a +3D6+3 modifier to hand-to-hand damage.<br><br>"
			+ "<b>Shrink Rate</b><br>"
			+ "<b>1/2 Natural Size (Lvl 1.)</b> - The following skills have their difficulty modifier lowered by 1 and gain a +1 bonus to their rolls: sneak, charm, disguise.  The following skills have their difficulty modifier increased by 1 and gain a -1 penalty to their rolls: all physique skills, physique, and intimidation.<br>"
			+ "<b>1/3 Natural Size (Lvl 2.)</b> - The following skills have their difficulty modifier lowered by 1 and gain a +2 bonus to their rolls: sneak, charm, disguise.  The following skills have their difficulty modifier increased by 2 and gain a -2 penalty to their rolls: all physique skills, physique, and intimidation.<br>"
			+ "<b>1/4 Natural Size (Lvl 3.)</b> - The following skills have their difficulty modifier lowered by 2 and gain a +2 bonus to their rolls: sneak, charm, disguise.  The following skills have their difficulty modifier increased by 3 and gain a -2 penalty to their rolls: all physique skills, physique, and intimidation.<br>"
			+ "<b>1/5 Natural Size (Lvl 4.)</b> - The following skills have their difficulty modifier lowered by 2 and gain a +3 bonus to their rolls: sneak, charm, disguise.  The following skills have their difficulty modifier increased by 3 and gain a -3 penalty to their rolls: all physique skills, physique, and intimidation.<br>"
			+ "<b>1/6 Natural Size (Lvl 5.)</b> - The following skills have their difficulty modifier lowered by 3 and gain a +4 bonus to their rolls: sneak, charm, disguise.  The following skills have their difficulty modifier increased by 3 and gain a -4 penalty to their rolls: all physique skills, physique, and intimidation.<br>"
			+ "<b>1/7 Natural Size (Lvl 6.)</b> - The following skills have their difficulty modifier lowered by 3 and gain a +5 bonus to their rolls: sneak, charm, disguise.  The following skills have their difficulty modifier increased by 3 and gain a -5 penalty to their rolls: all physique skills, physique, and intimidation.<br>"
			+ "<b>1/8 Natural Size (Lvl 7.)</b> - The following skills have their difficulty modifier lowered by 4 and gain a +5 bonus to their rolls: sneak, charm, disguise.  The following skills have their difficulty modifier increased by 4 and gain a -5 penalty to their rolls: all physique skills, physique, and intimidation.<br>"
			+ "<b>1/9 Natural Size (Lvl 8.)</b> - The following skills have their difficulty modifier lowered by 4 and gain a +6 bonus to their rolls: sneak, charm, disguise.  The following skills have their difficulty modifier increased by 5 and gain a -6 penalty to their rolls: all physique skills, physique, and intimidation.<br>"
			+ "<b>1/10 Natural Size (Lvl 9.) </b>- The following skills have their difficulty modifier lowered by 4 and gain a +7 bonus to their rolls: sneak, charm, disguise.  The following skills have their difficulty modifier increased by 6 and gain a -7 penalty to their rolls: all physique skills, physique, and intimidation.<br>"
			+ "<b>1/10 Natural Size EXTREME (Lvl 10.)</b> - The following skills have their difficulty modifier lowered by 5 and gain a +8 bonus to their rolls: sneak, charm, disguise.  The following skills have their difficulty modifier increased by 6 and gain a -7 penalty to their rolls: all physique skills, physique, and intimidation.  In addition, the character gains advantage on the following rolls: Reflexes, Coordination.<br></html>", "UDO+1d6, size bonus and penalties +/- 1", "UDO+1d6+2, size bonus and penalties +/- 2", "UDO+1d6+4, size bonus and penalties +/- 2", "UDO+2d6, size bonus and penalties +/- 3", "UDO+2d6+2, size bonus and penalties +/- 4", "UDO+2d6+3, size bonus and penalties +/- 5", "UDO+2d6+4, size bonus and penalties +/- 5", "UDO+3d6+1, size bonus and penalties +/- 6", "UDO+3d6+2, size bonus and penalties +/- 7", "UDO+3d6+3, size bonus and penalties +8/-7"), 

	SONIC_MANIPULATION("Sonic Manipulation", 6, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	SPEED_MANIPULATION("Speed Manipulation", 7, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	WATER_MANIPULATION("Water Manipulation",5, "<html><b>Governing Skills: Melee Weapons, Marksmanship, Survival</b><br>"
			+ "This individual is a water elemental of some sort, turning the ebbs and flow of the tides to their own purposes.  However, as an individual with an exceptional proclivity for controlling the liquid form of water, this character must also ingest a significant amount of water with regularity.  Every half an hour, this individual requires a half gallon of water to drink.  A gallon of water can take one item slot, and if they do not drink, their body point cap is reduced by 5 for each half hour without water.  Once water is ingested, their body points return to normal.<br><br>"
			+ "<b>Water Manipulation Level Increases</b><br>"
			+ "<b>Lvl. 1 -</b> A character may unleash a torrent of water from their hands to deal 1D6 water damage.  They may also breathe water, if necessary.<br>"
			+ "<b>Lvl. 2 -</b> A character may unleash a torrent of water from their hands to deal 1D6+2 water damage.  The character can create basic water spheres which can be used by others to deal 1D6+2 water damage.  Water spheres are thrown weapons, and they always deal 1D6+2 water damage.<br>"
			+ "<b>Lvl. 3 -</b> A character may unleash a torrent of water from their hands to deal 1D6+4 water damage.  The character can create bindings with water.<br>"
			+ "<b>Lvl. 4 -</b> A character may unleash a torrent of water from their hands to deal 2D6+1 water damage.<br>"
			+ "<b>Lvl. 5 -</b> A character may unleash a torrent of water from their hands to deal 2D6+2 water damage.  The character can part waters if necessary, and also create underwater bubbles for individuals to inhabit for brief, one-hour forays before requiring new oxygen.<br>"
			+ "<b>Lvl. 6 - </b>A character may unleash a torrent of water from their hands to deal 2D6+3 water damage.  The character can create whirlpools on land or in water.<br>"
			+ "<b>Lvl. 7 -</b> A character may unleash a torrent of water from their hands to deal 2D6+4 water damage.<br>"
			+ "<b>Lvl. 8 - </b>A character may unleash a torrent of water from their hands to deal 3D6+1 water damage. The character can begin to sheathe themselves in a thin but malleable external layer of bluish-clear water that has its own independent 10 Body Point statistic; this layer can last the duration of combat, but once destroyed, disappears.  <br>"
			+ "<b>Lvl. 9 - </b>A character may unleash a torrent of water from their hands to deal 3D6+2 water damage.  The character can create rudimentary objects out of water, which hold their form so long as the character provides concentration.<br>"
			+ "<b>Lvl. 10 -</b> A character may unleash a torrent of water from their hands to deal 3D6+3 water damage.  They can also summon up tidal force waves if they are close enough to an independent body of water, crashing down on foes and friends alike in a 5 sq. radius.<br></html>", "1D6 water damage", "1D6+2 water damage. Can create water spheres", "1D6+4 water damage. Bindings available", "2D6+1 water damage", "2D6+2 water damage", "2D6+3 water damage", "2D6+4 water damage", "3D6+1 water damage, 10BP water layer", "3D6+2 water damage", "3D6+3 water damage"), 

	WEATHER_MANIPULATION("Weather Manipulation", 6, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	SORCERY("Sorcery", 8, "<html><b>Governing Skills: Arcane Lore, Languages, Willpower</b><br>"
			+ "Sorcery is the devotion of a character to the arcane arts, practicing dark magicks.  Sorcery and wizardry are two sides of the same coin; a character with more villain points than hero points, practicing magic, utilizes sorcery.  A benefit of sorcery is that, in exchange for damaging the caster in terms of body points, the caster can reaccrue spell casting uses.<br><br>"
			+ "Sorcerers begin with a signature spell, created in conjunction with the narrator’s discretion, that has unlimited uses.  However, sorcerers are not limited to one spell.  Throughout the setting of their adventures, sorcerers may attempt to douse, searching for abilities that can be adapted into spells.  These abilities are wild magicks, unbound by convention, and dousing can be used to capture and ensnare these unique abilities honing them into spells.  Doused spells fall into one of four categories, based on their potency.  Any sorcerer has limited uses of these wild magicks per day, for the toll it costs to utilize these abilities is significant.  If a character rests, their spell uses are replenished.<br><br>"
			+ "<b>Lvl 1:</b> 1 Signature Spell, 7 Level 1 Spells, 2 Level 2 Spells, 1 Level 3 Spell<br>"
			+ "<b>Lvl 2:</b> 1 Signature Spell, 8 Level 1 Spells 3 Level 2 Spells, 1 Level 3 Spell<br>"
			+ "<b>Lvl 3:</b> 1 Signature Spell, 9 Level 1 Spells, 3 Level 2 Spells, 1 Level 3 Spell, 1 Level 4 Spell<br>"
			+ "<b>Lvl 4:</b> 1 Signature Spell, 10 Level 1 Spells, 4 Level 2 Spells, 2 Level 3 Spells, 1 Level 4 Spell<br>"
			+ "<b>Lvl 5:</b> 2 Signature Spells, 10 Level 1 Spells, 5 Level 2 Spells, 2 Level 3 Spells, 1 Level 4 Spell<br>"
			+ "<b>Lvl 6:</b> 2 Signature Spells, Unlimited Level 1 Spells, 7 Level 2 Spells, 4 Level 3 Spells, 2 Level 4 Spells<br>"
			+ "<b>Lvl 7:</b> 2 Signature Spells, Unlimited Level 1 Spells, 10 Level 2 Spells, 5 Level 3 Spells, 2 Level 4 Spells<br>"
			+ "<b>Lvl 8:</b> 2 Signature Spells, Unlimited Level 1 Spells, 10 Level 2 Spells, 7 Level 3 Spells, 2 Level 4 Spells<br>"
			+ "<b>Lvl 9:</b> 2 Signature Spells, Unlimited Level 1 Spells, Unlimited Level 2 Spells, 8 Level 3 Spells, 3 Level 4 Spells<br>"
			+ "<b>Lvl 10:</b> 3 Signature Spells, Unlimited Level 1 Spells, Unlimited Level 2 Spells, 10 Level 3 Spells, 4 Level 4 Spells<br><br>"
			+ "The rate at which body points can be spent to reacquire spent spell castings is as follows:<br>"
			+ "<i>Level 1 Spells - 2 Body Points<br>"
			+ "Level 2 Spells - 4 Body Points<br>"
			+ "Level 3 Spells - 6 Body Points<br>"
			+ "Level 4 Spells - 12 Body Points</i><br><br>"
			+ "In order to douse, a character must have an enchanted artifact of some sort, and they must roll an Arcane Lore roll in order to detect wild magicks.  The success of the roll determines if the character can sense any nearby magic.  The difficulty of these rolls is determined by the proximity of the character to the closest wild magick.<br></html>", 
			"1 Sig, 7 Lv 1, 2 Lv 2, 1 Lv 3", 
			"1 Sig, 8 Lv 1, 3 Lv 2, 1 Lv 3", 
			"1 Sig, 9 Lv 1, 3 Lv 2, 1 Lv 3, 1 Lv 4", 
			"1 Sig 10 Lv 1, 4 Lv 2, 2 Lv 3, 1 Lv 4", 
			"2 Sigs, 10 Lv 1, 5 Lv 2 Spells, 2 Lv 3 Spells, 1 Lv 4 Spell", 
			"2 Sigs, Unlimited Lv 1, 7 Lv 2, 4 Lv 3, 2 Lv 4", 
			"2 Sigs, Unlimited Lv 1, 10 Lv 2, 5 Lv 3, 2 Lv 4", 
			"2 Sigs, Unlimited Lv 1, 10 Lv 2, 7 Lv 3, 2 Lv 4", 
			"2 Sigs, Unlimited Lv 1, Unlimited Lv 2, 8 Lv 3, 3 Lv 4", 
			"3 Sigs, Unlimited Lv 1, Unlimited Lv 2, 10 Lv 3, 4 Lv 4"), 

	SPIRIT_MANIPULATION("Spirit Manipulation", 7, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	SUMMONING("Summoning", 4, "Description Incoming", "", "", "", "", "", "", "", "", "", ""),

	WIZARDRY("Wizardry", 8, "<html><b>Governing Skills: Arcane Lore, Languages, Willpower</b><br>"
			+ "Wizardry is the focus and devotion in the study of mystical enchantments and magical wards.  Sorcery and wizardry are two sides of the same coin; a character with more hero points than villain points, practicing magic, utilizes wizardry.  A benefit of wizardry is that, in exchange for expending a Level 3 or 4 spell use, wizards can double the damage done by a cast spell.<br>"
			+ "<br>Wizards begin with a signature spell, created in conjunction with the narrator’s discretion, that has unlimited uses.  However, wizards are not limited to one spell.  Throughout the setting of their adventures, wizards may attempt to douse, searching for abilities that can be adapted into spells.  These abilities are wild magicks, unbound by convention, and dousing can be used to capture and ensnare these unique abilities honing them into spells.  Doused spells fall into one of four categories, based on their potency.  Any wizard has limited uses of these wild magicks per day, for the toll it costs to utilize these abilities is significant.  If a character rests, their spell uses are replenished.<br><br>"
			+ "<b>Lvl 1:</b> 1 Signature Spell, 7 Level 1 Spells, 2 Level 2 Spells, 1 Level 3 Spell<br>"
			+ "<b>Lvl 2:</b> 1 Signature Spell, 8 Level 1 Spells 3 Level 2 Spells, 1 Level 3 Spell<br>"
			+ "<b>Lvl 3:</b> 1 Signature Spell, 9 Level 1 Spells, 3 Level 2 Spells, 1 Level 3 Spell, 1 Level 4 Spell<br>"
			+ "<b>Lvl 4:</b> 1 Signature Spell, 10 Level 1 Spells, 4 Level 2 Spells, 2 Level 3 Spells, 1 Level 4 Spell<br>"
			+ "<b>Lvl 5:</b> 2 Signature Spells, 10 Level 1 Spells, 5 Level 2 Spells, 2 Level 3 Spells, 1 Level 4 Spell<br>"
			+ "<b>Lvl 6:</b> 2 Signature Spells, Unlimited Level 1 Spells, 7 Level 2 Spells, 4 Level 3 Spells, 2 Level 4 Spells<br>"
			+ "<b>Lvl 7:</b> 2 Signature Spells, Unlimited Level 1 Spells, 10 Level 2 Spells, 5 Level 3 Spells, 2 Level 4 Spells<br>"
			+ "<b>Lvl 8:</b> 2 Signature Spells, Unlimited Level 1 Spells, 10 Level 2 Spells, 7 Level 3 Spells, 2 Level 4 Spells<br>"
			+ "<b>Lvl 9:</b> 2 Signature Spells, Unlimited Level 1 Spells, Unlimited Level 2 Spells, 8 Level 3 Spells, 3 Level 4 Spells<br>"
			+ "<b>Lvl 10:</b> 3 Signature Spells, Unlimited Level 1 Spells, Unlimited Level 2 Spells, 10 Level 3 Spells, 4 Level 4 Spells<br><br>"
			+ "In order to douse, a character must have an enchanted artifact of some sort, and they must roll an Arcane Lore roll in order to detect wild magicks.  The success of the roll determines if the character can sense any nearby magic.  The difficulty of these rolls is determined by the proximity of the character to the closest wild magick.<br></html>", "1 Sig, 7 Lv 1, 2 Lv 2, 1 Lv 3", 
			"1 Sig, 8 Lv 1, 3 Lv 2, 1 Lv 3", 
			"1 Sig, 9 Lv 1, 3 Lv 2, 1 Lv 3, 1 Lv 4", 
			"1 Sig 10 Lv 1, 4 Lv 2, 2 Lv 3, 1 Lv 4", 
			"2 Sigs, 10 Lv 1, 5 Lv 2 Spells, 2 Lv 3 Spells, 1 Lv 4 Spell", 
			"2 Sigs, Unlimited Lv 1, 7 Lv 2, 4 Lv 3, 2 Lv 4", 
			"2 Sigs, Unlimited Lv 1, 10 Lv 2, 5 Lv 3, 2 Lv 4", 
			"2 Sigs, Unlimited Lv 1, 10 Lv 2, 7 Lv 3, 2 Lv 4", 
			"2 Sigs, Unlimited Lv 1, Unlimited Lv 2, 8 Lv 3, 3 Lv 4", 
			"3 Sigs, Unlimited Lv 1, Unlimited Lv 2, 10 Lv 3, 4 Lv 4");

	private final String nameP;
	private final int cost;
	private final String description;
	private final String level1;
	private final String level2;
	private final String level3;
	private final String level4;
	private final String level5;
	private final String level6;
	private final String level7;
	private final String level8;
	private final String level9;
	private final String level10;
	
	Power(String name, int cost, String description, String level1, String level2, String level3, String level4, String level5, String level6, String level7, String level8, String level9, String level10)
	{
		nameP = name;
		this.cost = cost;
		this.description = description;
		this.level1 = level1;
		this.level2 = level2;
		this.level3 = level3;
		this.level4 = level4;
		this.level5 = level5;
		this.level6 = level6;
		this.level7 = level7;
		this.level8 = level8;
		this.level9 = level9;
		this.level10 = level10;
	}
	
	public String nameP()
	{
		return nameP;
	}
	public int cost()
	{
		return cost;
	}
	public String description()
	{
		return description;
	}
	public String level1()
	{
		return level1;
	}
	public String level2()
	{
		return level2;
	}
	public String level3()
	{
		return level3;
	}
	public String level4()
	{
		return level4;
	}
	public String level5()
	{
		return level5;
	}
	public String level6()
	{
		return level6;
	}
	public String level7()
	{
		return level7;
	}
	public String level8()
	{
		return level8;
	}
	public String level9()
	{
		return level9;
	}
	public String level10()
	{
		return level10;
	}
	public static List<Power> getAll()
	{
		List<Power> p = new ArrayList<Power>();
		p.add(ANIMAL_MIMESIS);
		p.add(BIND);
		p.add(CHEMICAL_MIMESIS);
		p.add(CHEMICAL_PROJECTION);
		p.add(CLINGING);
		p.add(DISEASE);
		p.add(DISINTEGRATION);
		p.add(ELASTICITY);
		p.add(DIGESTIVE_ADAPTABILITY);
		p.add(ENERGY_PROJECTION);
		p.add(ENVIRONMENTAL_IMMUNITY);
		p.add(EXTRA_BODY_PART);
		p.add(IMMUNITY);
		p.add(INFRAVISION);
		p.add(INVISIBILITY);
		p.add(LONGEVITY);
		p.add(MICROWAVE_PROJECTION);
		p.add(MIMICRY);
		p.add(MULTIPLICITY);
		p.add(NATURAL_WEAPONS);
		p.add(PLANT_MIMESIS);
		p.add(REDIMENSIONALITY);
		p.add(SHAPECHANGING);
		p.add(SONAR_SENSE);
		p.add(SUPER_REFLEXES);
		p.add(SUPER_COORDINATION);
		p.add(SUPER_PHYSIQUE);
		p.add(SUPER_KNOWLEDGE);
		p.add(SUPER_PERCEPTION);
		p.add(SUPER_PRESENCE);
		p.add(SUPERBREATH);
		p.add(SUPERSENSES);
		p.add(SUSPENDED_ANIMATION);
		p.add(SUSTENANCE);
		p.add(ULTRAVENTRILOQUISM);
		p.add(VAMPIRISM);
		p.add(X_RAY_VISION);
		p.add(ANIMATION);
		p.add(ASTRAL_FORM);
		p.add(EMPATHY);
		p.add(ESP);
		p.add(EXPLOSION);
		p.add(EXPULSION);
		p.add(ILLUSION);
		p.add(LANGUAGE_COMPREHENSION);
		p.add(MENTAL_BLAST);
		p.add(POSSESSION);
		p.add(POWER_SENSE);
		p.add(PSYCHIC_BLAST);
		p.add(PSYCHIC_MANIFESTATION);
		p.add(SPEAK_WITH_ANIMALS);
		p.add(SPEAK_WITH_INANIMATES);
		p.add(SPEAK_WITH_PLANTS);
		p.add(TELEKINESIS);
		p.add(TELEPATHY);
		p.add(DIMENSIONAL_TRAVEL);
		p.add(FLIGHT);
		p.add(SUPERJUMP);
		p.add(TELEPORTATION);
		p.add(ENERGY_ABSORPTION);
		p.add(FORCEFIELD);
		p.add(HEALING);
		p.add(INVULNERABILITY);
		p.add(MENTAL_SHIELD);
		p.add(NATURAL_ARMOR);
		p.add(POWER_SHIELD);
		p.add(RESURRECTION);
		p.add(AIR_MANIPULATION);
		p.add(DARKNESS_MANIPULATION);
		p.add(DENSITY_MANIPULATION);
		p.add(EARTH_MANIPULATION);
		p.add(ELECTRICITY_MANIPULATION);
		p.add(ENERGY_MANIPULATION);
		p.add(FIRE_MANIPULATION);
		p.add(ICE_MANIPULATION);
		p.add(LIGHT_MANIPULATION);
		p.add(KINETIC_MANIPULATION);
		p.add(MAGNETIC_MANIPULATION);
		p.add(MATTER_MANIPULATION);
		p.add(MECHANICAL_MANIPULATION);
		p.add(PLANT_MANIPULATION);
		p.add(SIZE_MANIPULATION);
		p.add(SONIC_MANIPULATION);
		p.add(SPEED_MANIPULATION);
		p.add(WATER_MANIPULATION);
		p.add(WEATHER_MANIPULATION);
		p.add(SORCERY);
		p.add(SPIRIT_MANIPULATION);
		p.add(SUMMONING);
		p.add(WIZARDRY);
		
		return p;
	}
}
