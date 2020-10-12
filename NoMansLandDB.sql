-- script to create No Man's Land database

use sys;
drop database if exists noMansLand;
create database noMansLand;
use noMansLand;

-- create all tables

-- NEED UPDATE - FK's for all items, powers not determined
create table CharacterSheet (
	ID int not null auto_increment, primary key(ID), 
	-- Demographics
		Name varchar(64) not null, 
		Picture varchar(128), -- filename
		Occupation varchar(64), 
		BaseOfOperations varchar(64),
		LocationID int, -- refers to Location Table, but is not a FK
		Gender varchar(16), 
		Race varchar(16), 
		Height varchar(16), 
		Weight varchar(16), 
		EyeColor varchar(16), 
		HairColor varchar(16),
	-- Misc. Stats
		UDODice varchar(16) DEFAULT "1d6", -- the dice value for UDO, ie. 1d6
		UDOBonus int, -- the bonus to damage added to the UDO, ie. +5
		Speed int DEFAULT 3, 
		HeroPoints int DEFAULT 0, VillainPoints int DEFAULT 0, AvailableRenown int DEFAULT 0, 
		PowerPoints int DEFAULT 0, SkillPoints int DEFAULT 0, 
		BodyPointsCurrent int DEFAULT 1, BodyPointsMax int DEFAULT 1,
	-- Skills (Specializations are referred to by CharacterSheetID FK from SkillSpec)
		Reflexes int not null DEFAULT 1, Acrobatics int not null DEFAULT 0, Dodge int not null DEFAULT 0, HandToHand int not null DEFAULT 0, MeleeWeapons int not null DEFAULT 0, Stealth int not null DEFAULT 0,
		Coordination int not null DEFAULT 1, Catch int not null DEFAULT 0, Climb int not null DEFAULT 0, Drive int not null DEFAULT 0, Marksmanship int not null DEFAULT 0, Thievery int not null DEFAULT 0, ThrownWeapons int not null DEFAULT 0,
		Physique int not null DEFAULT 1, Athletics int not null DEFAULT 0, Leap int not null DEFAULT 0, Lifting int not null DEFAULT 0, Resistance int not null DEFAULT 0, Running int not null DEFAULT 0, Swimming int not null DEFAULT 0,
		Knowledge int not null DEFAULT 1, ArcaneLore int not null DEFAULT 0, Demolitions int not null DEFAULT 0, Languages int not null DEFAULT 0, Medicine int not null DEFAULT 0, Scholar int not null DEFAULT 0, Science int not null DEFAULT 0, Security int not null DEFAULT 0,
		Perception int not null DEFAULT 1, Artist int not null DEFAULT 0, Engineering int not null DEFAULT 0, Search int not null DEFAULT 0, Streetwise int not null DEFAULT 0, Surveillance int not null DEFAULT 0, Survival int not null DEFAULT 0,
		Presence int not null DEFAULT 1, Bluff int not null DEFAULT 0, Charm int not null DEFAULT 0, Intimidation int not null DEFAULT 0, Persuasion int not null DEFAULT 0, Willpower int not null DEFAULT 0,
	-- Advantages and Disadvantages
		-- all referenced from CharacterSheetAdvantage and CharacterSheetDisadvantage tables, via CharacterSheetID FK
	-- Powers 
		-- managed by CharacterSheetPower tableuser
	-- Social Pool
		-- All referenced from Social Pool tables, eg. via SocialID from Contact
	-- Equipment
	KeyItems varchar(1028),
	Cap varchar(64),
	Head varchar(64),
	Neck varchar(64),
	Chest varchar(64),
	Wrists varchar(64),
	Waist varchar(64),
	Ring1 varchar(64),
	Ring2 varchar(64),
	Legs varchar(64),
	Feet varchar(64),
	Weapon varchar(64),
		-- Pack
	Pack1 varchar(64),
	Pack2 varchar(64),
	Pack3 varchar(64),
	Pack4 varchar(64),
	Pack5 varchar(64),
	Pack6 varchar(64),
	Pack7 varchar(64),
	Pack8 varchar(64),
	Pack9 varchar(64),
	Pack10 varchar(64),
	Pack11 varchar(64),
	Pack12 varchar(64),
	Pack13 varchar(64),
	Pack14 varchar(64),
	Pack15 varchar(64)	
);

-- Table for Skill Specializations, each skill spec is unique per CharacterSheet
	create table SkillSpec (
		ID int not null auto_increment, primary key (ID),
		CharacterSheetID int, foreign key (CharacterSheetID) references CharacterSheet(ID), -- FK to CharacterSheet this spec is for
		Skill varchar(32) not null, -- Skill this is a Spec for
		Spec varchar(32) not null, -- Name of the spec, ie. Piano, Double Kick, Biology
		Level int not null DEFAULT 1 check(Level >= 1 and Level <= 3) -- level of the spec, min of 1, max of 3
	);
    
-- The following are the Advantages and Disadvantages related tables
	-- table that stores all Advantages
	create table Advantage (
		ID int not null auto_increment, primary key (ID),
		Name varchar(32) not null,
        Cost int not null,
		Description varchar(256)
	);
	-- table that stores all Disadvantages
	create table Disadvantage (
		ID int not null auto_increment, primary key (ID),
		Name varchar(32) not null,
        Cost int not null,
		Description varchar(256)
	);
	-- Table that manages a specific CharacterSheet's unique list of Advantages
    create table CharacterSheetAdvantage (
		ID int not null auto_increment, primary key (ID),
        CharacterSheetID int, foreign key (CharacterSheetID) references CharacterSheet(ID),
        AdvantageID int, foreign key (AdvantageID) references Advantage(ID)
	);
	-- Table that manages a specific CharacterSheet's unique list of Disadvantages
    create table CharacterSheetDisadvantage (
		ID int not null auto_increment, primary key (ID),
        CharacterSheetID int, foreign key (CharacterSheetID) references CharacterSheet(ID),
        DisadvantageID int, foreign key (DisadvantageID) references Disadvantage(ID)
	);


-- Powers and Packages
	-- is this finished? just need to add correct string lengths I guess
	create table Power (
		ID int not null auto_increment, primary key (ID),
		Name varchar(32) not null,
        Cost int, -- cost of a level of power in terms of Power Points
        Description varchar (2048),
		Level1 varchar(256), 
		Level2 varchar(256), 
		Level3 varchar(256), 
		Level4 varchar(256), 
		Level5 varchar(256), 
		Level6 varchar(256), 
		Level7 varchar(256), 
		Level8 varchar(256), 
		Level9 varchar(256), 
		Level10 varchar(256)
	);
	-- Power Package table - rows here are unique to CharacterSheets, combining level, power, specs, and weaknesses   
	create table CharacterSheetPower (
		ID int not null auto_increment, primary key (ID),
		CharacterSheetID int, foreign key (CharacterSheetID) references CharacterSheet(ID), -- FK to CharacterSheet this powerset belongs to
		Level int check(Level >= 0),
		PowerID int, foreign key (PowerID) references Power(ID), -- FK to Power Description for this Power Package
        Cost int, -- DEFAULT (select Cost from Power p where PowerID = p.ID) -- Cost in Power Points defaults to Cost from Power, can be overwritten, NEED TO FIGURE THIS OUT
		Specializations varchar(256),
		Weakness varchar(256)
	);
		

-- SOCIAL TABLES. Each addition to a table is unique to the CharacterSheet via SocialID FK
	create table Ally (
		ID int not null auto_increment, primary key (ID),
		SocialID int not null, foreign key (SocialID) references CharacterSheet(ID),
		Level int, -- Null is allowed, in case social level doesn't matter
		Name varchar(64) not null,
		Description varchar(256)
	);
	create table Contact (
		ID int not null auto_increment, primary key (ID),
		SocialID int not null, foreign key (SocialID) references CharacterSheet(ID),
		Level int, -- Null is allowed, in case social level doesn't matter
		Name varchar(64) not null,
		Description varchar(256)
	);        
	create table Familiar (
		ID int not null auto_increment, primary key (ID),
		SocialID int not null, foreign key (SocialID) references CharacterSheet(ID),
		Level int, -- Null is allowed, in case social level doesn't matter
		Name varchar(64) not null,
		Description varchar(256)
	);
	create table Follower (
		ID int not null auto_increment, primary key (ID),
		SocialID int not null, foreign key (SocialID) references CharacterSheet(ID),
		Level int, -- Null is allowed, in case social level doesn't matter
		Name varchar(64) not null,
		Description varchar(256)
	);
	create table Patron (
		ID int not null auto_increment, primary key (ID),
		SocialID int not null, foreign key (SocialID) references CharacterSheet(ID),
		Level int, -- Null is allowed, in case social level doesn't matter
		Name varchar(64) not null,
		Description varchar(256)
	);
	create table Dependent (
		ID int not null auto_increment, primary key (ID),
		SocialID int not null, foreign key (SocialID) references CharacterSheet(ID),
		Level int, -- Null is allowed, in case social level doesn't matter
		Name varchar(64) not null,
		Description varchar(256)
	);
	create table Enemy (
		ID int not null auto_increment, primary key (ID),
		SocialID int not null, foreign key (SocialID) references CharacterSheet(ID),
		Level int, -- Null is allowed, in case social level doesn't matter
		Name varchar(64) not null,
		Description varchar(256)
	);
	create table SwornEnemy(
		ID int not null auto_increment, primary key (ID),
		SocialID int not null, foreign key (SocialID) references CharacterSheet(ID),
		Level int, -- Null is allowed, in case social level doesn't matter
		Name varchar(64) not null,
		Description varchar(256)
	);
    
-- LOCATION TABLES
	-- Hubs are places such as Archduchy
	create table Hub (
		ID int not null auto_increment, primary key (ID),
		Name varchar(64) not null,
		LeaderID int, foreign key (LeaderID) references CharacterSheet(ID)-- FK to CharacterSheet who is leader of this Hub
		-- Locales present in Hub are referred to by Locale.HubID FK
		-- Characters present at Hub are referred to by CharacterSheet.LocationID joined to Location.LocaleID FK joined to Locale.HubID FK
	);
	-- Locales are places such as Colgate Heights
	create table Locale (
		ID int not null auto_increment, primary key (ID),
		Name varchar(64) not null,
		HubID int, foreign key (HubID) references Hub(ID), -- FK to Hub this Locale is within
		LeaderID int, foreign key (LeaderID) references CharacterSheet(ID)-- FK to CharacterSheet who is leader of this Locale
		-- Characters present at Locale are referred to by CharacterSheet.LocationID joined to Location.LocaleID FK
		-- Locations present at Locale are referred to by Location.LocaleID FK
	);
	-- Locations are places such as C Building
	create table Location (
		ID int not null auto_increment, primary key (ID),
		Name varchar(64) not null,
		LocaleID int, foreign key (LocaleID) references Locale(ID), -- FK to Locale this Location is within
		LeaderID int, foreign key (SocialID) references CharacterSheet(ID) -- FK to CharacterSheet who is leader of this Location
		-- CharacterSheets present at Location are referred to by CharacterSheet.LocationID
		-- Items present at Location are referred to by Item.LocationID FK
	);
    
    create table Item (
		ID int not null auto_increment, primary key (ID),
        Name varchar(64) not null DEFAULT "Unnamed Item",
		Picture varchar(128), -- filename
		Description varchar(128),
        // YO FIGURE THIS OUT
    
-- Populate Database with Advantages
    insert Advantage (Name, Cost, Description) values
		('Acting Ability', 3, 'This character is adept at creating a character, and therefore receives advantage on Bluff and Charm rolls while maintaining a disguise.'),
		('Acute Balance', 2, 'This character has an innate sense of balance and rarely falls.  If this character fails an Acrobatics roll, they can roll a second acrobatics roll immediately to recover at a +2 Difficulty Level from the previous roll.'),
		('Acute Manual Dexterity', 2, 'This character has dexterous fingers, and therefore gains a +2 modifier on all lockpicking or sleight of hand-related rolls.'),
		('Acute Sense of Direction', 2, 'This character has incredible talent for orienteering.  All navigation-related rolls are modified with a -1 Difficulty Level, and a character can always make a Novice Difficulty Level roll with Survival to find north.'),
		('Acute Sense', 2, 'Each time taken affects one sense. This character has a very well-developed sense.  They gain a +1 modifier to any raw Perception roll utilizing that specific sense.  This advantage must be taken multiple times to affect multiple senses.'),
		('Animal Friendship', 2, 'This character has a natural rapport with animals, and as such receives a -2 difficulty modifier when making survival or driving related rolls in relation to them.'),
		('Attractive Appearance', 2, 'This character is widely considered to be good-looking, and therefore is able to have Advantage on any Seduction roll involving a character who has the capacity to be attracted by the player character’s gender or species.'),
		('Charismatic', 3, 'This character is incredibly friendly and upbeat; if they assist a character who has lower Presence than them with a Charm or Bluff roll, that character rolls with advantage.'),
		('Courage', 2, 'This character receives a -2 difficulty modifier when making willpower rolls against intimidation or interrogation, thanks to their unflappable resolve.  The character also can roll Willpower against fear toxins or fear-based spells at any Difficulty Level.'),
		('Double-Jointed', 2, 'This character’s natural suppleness to his/her joints allows them to bend extraordinarily.  The Thievery (Escape Artistry) specialization receives a +3 modifier to all rolls, and contortion-based rolls in Acrobatics receive a +1 modifier.'),
		('Hardiness', 4, 'If this character has Physique greater than 5, they can take this Advantage, which allows them to increase their body points by 4, permanently.  They also roll Powering Out rolls with a +1 modifier.'),
		('Intimidating Grin', 2, 'Thanks to their unnerving smile, this character has gained a unique specialization to Intimidation which always forces its victim to roll at disadvantage.'),
		('Leadership Ability', 2, 'As a natural leader, this character can use successful Willpower rolls at a matched difficulty to any ally’s Willpower roll to provide them a +1 modifier to said roll, including Powering Out rolls.  This ability only works when in visible range of the ally.'),
		('Mechanical Aptitude', 2, 'This character’s adept skill at creating or repairing machinery gives them a -4 to any Difficulty Level when making mechanical rolls in Science or Engineering.'),
		('Obscure Knowledge', 2, 'This character is filled with useless trivia, and as such can make any Scholar roll that requires a specialization with their basic Knowledge skill at +3 difficulty.  Cannot be taken with Forgetful.'),
		('Observant', 2, 'Thanks to this character’s penchant for observation, this character can reroll a failed Search roll at +3 difficulty, and any Nat 1 using the tracking specialization in Survival is immediately negated to a simple failure.'),
		('Photographic Memory', 4, 'If this character starts with Perception greater than 5, they can remember details with uncanny clarity.  This sort of recall provides them the opportunity to make Skilled DL rolls in Search or Surveillance in order to learn a detail they may have missed in a previously visited scenario.'),
		('Preparedness', 4, 'Once per day, a character can search their pack for a non-unique item of common variety based on a D6 roll.  Any roll above a 2 will result in the character collecting the item; the definition of non-unique item of common variety will be established at the DM’s discretion.'),
		('Self-Healing', 4, 'Only available for certain races.  During long rests in the field, the character can always regenerate full Body Points.'),
		('Sixth Sense', 2, 'Only available for characters with Perception greater than 5.  If the DM calls for a surprise raw Perception roll, it may be rolled with Advantage.'),
		('Speed Draw', 1, 'Each time taken affects one weapon type. Specialized in the art of combat with a specific weapon, a character with Speed Draw gains a +3 to their Initiative so long as a weapon of that type is slotted as the primary weapon on a character.  A character can also retrieve a weapon of the Speed Draw’s type from their pack during combat without using up a turn.'),
		('Status', 2, 'In a particular field of influence, this character has achieved a level of respect and recognition that allows them to lower Charm, Bluff, Intimidation, Interrogation and Persuasion roll difficulties with fellow members of that field of influence by 1.'),
		('Technologically Advanced', 4, 'Only available to specific races; characters with this advantage are from a society far advanced beyond Earth’s capabilities, and as such, earn a -3 difficulty modifier on computer ops rolls for any modern Earth technology.'),
		('Thousand Faces', 2, 'Only available if a character is or can simulate humans of average height and weight.  (5’7-6’1, 130-180 for men, 5’4-5’10, 110-150 for women).  The character is a master of disguise and receives +2 to disguise specialization rolls.'),
		('Ventriloquism', 1, 'This advantage provides players with a unique specialization in Artist; Ventriloquism-related rolls in Charm and Bluff receive +1.'),
		('Wealth', 2, 'This character has deep pockets and a variety of assets; as such, they begin the campaign with $500,000 or the equivalent for their race.')
	;
-- Populate Database with Disadvantages
    insert Disadvantage (Name, Cost, Description) values
		('Blackout', 3, 'During high-pressure situations, this character has a tendency to blackout for several minutes.  They have no recollection of this lost time.  The character might black out whenever they roll a Nat 1, based on a failed Willpower roll.  Failure means that they fall unconscious for 3 turns or 3 minutes without interference.'),
		('Center of Conversation', 1, 'The character feels the need to be in the middle of every conversation, whether they know what they’re talking about or not.  This is a narrative disadvantage.'),
		('Childish Appearance', 1, 'This character is young or looks young, and as such, they very rarely receive the respect they deserve.  All intimidation and persuasion rolls against characters older than the player character are rolled with a -1 modifier.'),
		('Cowardice', 2, 'The hero is easy to intimidate, and as such, always rolls with disadvantage against Intimidation and Interrogation rolls.'),
		('Dark Secret', 1, 'The character possesses some information or has performed some action, where, should it come to light or resurface, could prove dangerous to the character or those around him/her.  This is a narrative Disadvantage.'),
		('Debt', 2, 'This character owes a debt of some form, and when the debtor demands repayment, the player character MUST provide it.  This is a narrative disadvantage.'),
		('Delusions of Grandeur', 3, 'The character is convinced they are the best at what they do.  If this fact is tested by someone else, including another player character, the delusional player character must roll at least a Skilled DL Willpower roll to control themselves.  If they do not, they must immediately stop any activity and argue with the source of dissent.'),
		('Extremely Competitive', 2, 'A player character who considers themselves skilled in one or more ways may often challenge their fellow party members or other NPCs to bets or competitions based on those abilities.  This can potentially scuttle social interactions; the player may make an average Willpower roll to quell their raging competitiveness.'),
		('Fanatic', 3, 'The character is incredibly dedicated to a philosophical ideal.  This ideal is so important that they will attempt to defend or uphold it, even if it would cost them their life.   Only with Master Willpower rolls can they control themselves long enough to avoid their beliefs scuttling whatever social interaction in which they are involved.'),
		('Forgetful', 4, 'Only available if the character has Knowledge less than 5.  A forgetful character rolls any Scholar or Arcane Lore roll with disadvantage.  Cannot be taken with Obscure Knowledge.'),
		('Fugitive', 3, 'The character is wanted by law enforcement for a crime they may or may not have committed.  When interacting with the society where he/she is wanted, the fugitive character may face repercussions if they are not disguised, hidden, or using other mitigating circumstances to roam free.'),
		('Hides Emotions', 1, 'The character rolls with disadvantage on non-specialized Charm rolls.'),
		('Illiterate', 3, 'The character cannot read, and as such, is incapable of taking Arcane Lore, Computer Ops, and Scholar.  They also cannot use Languages for written language-related rolls.'),
		('Impulsiveness', 2, 'The character has little thought for their consequences, often rushing into peril without a plan unless someone stops them.  This is a narrative disadvantage.'),
		('Infamy', 2, 'In a particular field of influence, this character has achieved a level of disrespect and negative recognition that forces them to raise Charm, Bluff, Intimidation, Interrogation and Persuasion roll difficulties with fellow members of that field of influence by 1.'),
		('Kleptomaniacal Tendencies', 2, 'If the character sees something they consider valuable, they must make a successful Skilled willpower roll to resist the urge to steal it.'),
		('Long-Winded', 1, 'The character feels the need to expound forever on any subject when given the opportunity, whether they know what they’re talking about or not.  This is a narrative disadvantage.'),
		('Low Self-Esteem', 2, 'The character constantly berates themselves or has no confidence in their own abilities.  As such, the DM may roll a D6 during what might be considered important actions performed by the player character - if a 1 is rolled, the player character will suffer a -2 to their modifier on rolls pertaining to that important action.'),
		('Medical Problem/Psychological Disorder', 6, 'The player character suffers from a very serious physical ailment.  This may result in modifier penalties for rolls related to the medical issue, and is decided by the player and the DM during character creation.  Examples can include diseases requiring constant medication, partial paralysis, loss of a sense, loss of a limb, etc.'),
		('Narcissistic', 3, 'The character rolls any raw Presence or Perception rolls with a -1 modifier thanks to their self-absorption.'),
		('Nightmares', 4, 'Every night, the player suffers from horrible nightmares.  As a result, the character must make an Average DL Willpower roll successfully, lest they suffer -1 modifiers on all skill rolls for the next 24 hour period.'),
		('Obsessive Tendencies', 2, 'The character is totally focused on some specified course of action once it is decided upon.  If others break from this plan, the character suffers a -1 penalty on all Perception-related rolls until they can reassess the situation.'),
		('Pathological Liar', 2, 'Whenever the player is confronted with a question, they must make a d10 roll.  If that roll is a 1, they are compelled to lie or immensely exaggerate in response.'),
		('Paranoia', 3, 'This character believes there is a massive plot against them.  As such, they may not trust other characters, or even their own party.  This is a narrative disadvantage.'),
		('Phobia', 5, 'Whenever the hero is in the presence of the object of their fear, all attribute and skill rolls are decreased by -5.  The effect lasts until the object of their fear is removed.'),
		('Secret Identity', 3, 'The character has a civilian identity that, if compromised, could affect their friends and family, putting them at significant risk.  Can only be taken if a character has Dependents.'),
		('Shady Background', 2, 'The character has a criminal or shady history, as such, they begin the campaign with an additional 4 villain points, and suffer a -1 modifier to Charm, Bluff and Persuasion when rolled against superheroes.'),
		('Targeted for Assassination', 2, 'A specific criminal organization is after the player character, and as such, they will find themselves dealing with opponents in that organization who target them in combat at the expense of other player characters, as well as facing members of this organization in scenarios with upgraded weapons and equipment.'),
		('Technologically Challenged', 5, 'Only available for specific races.  This character is from an alternative society where technology at the standards of a modern Earth’s might not be readily available.  As such, the character suffers a -5 modifier to Computer Ops rolls, and a -2 to Security rolls.'),
		('Unattractive Appearance', 2, 'The character might be classified as ugly.  As such, this character must roll with disadvantage on any Seduction roll involving a character who has the capacity to be attracted by the player character’s gender or species'),
		('Uncoordinated', 5, 'If the character has less than level 4 Coordination, they are entitled to purchase this disadvantage, which attaches disadvantage to all raw Coordination rolls.')
	;