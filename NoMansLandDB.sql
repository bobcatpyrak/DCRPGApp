-- script to create No Man's Land database

use sys;
drop database if exists noMansLand;
create database noMansLand;
use noMansLand;

-- create all tables

-- NEED UPDATE - FK's for all items, powers not determined
create table Characters (
	ID int not null auto_increment, primary key(ID), 
	-- Demographics
		Name varchar(64) not null, 
		Picture varchar(128), -- filename
		Occupation varchar(64), 
		BaseOfOperations varchar(64),
		LocationID int, foreign key (LocationID) references Location(ID),-- FK to Locations Table, MIGHT NEED THIS TO NOT BE FOREIGN KEY
		Gender varchar(16), 
		Race varchar(16), 
		Height varchar(16), 
		Weight varchar(16), 
		EyeColor varchar(16), 
		HairColor varchar(16),
	-- Misc. Stats
		UDODice varchar(16), -- the dice value for UDO, ie. 1d6
		UDOBonus int, -- the bonus to damage added to the UDO, ie. +5
		Speed int DEFAULT 3, 
		HeroPoints int DEFAULT 0, VillainPoints int DEFAULT 0, AvailableRenown int DEFAULT 0, 
		PowerPoints int DEFAULT 0, SkillPoints int DEFAULT 0, 
		BodyPointsCurrent int DEFAULT 1, BodyPointsMax int DEFAULT 1,
	-- Skills (Specializations are referred to by CharacterID FK from SkillSpecs)
		Reflexes int not null DEFAULT 1, Acrobatics int not null DEFAULT 0, Dodge int not null DEFAULT 0, HandToHand int not null DEFAULT 0, MeleeWeapons int not null DEFAULT 0, Stealth int not null DEFAULT 0,
		Coordination int not null DEFAULT 1, Catch int not null DEFAULT 0, Climb int not null DEFAULT 0, Drive int not null DEFAULT 0, Marksmanship int not null DEFAULT 0, Thievery int not null DEFAULT 0, ThrownWeapons int not null DEFAULT 0,
		Physique int not null DEFAULT 1, Athletics int not null DEFAULT 0, Leap int not null DEFAULT 0, Lifting int not null DEFAULT 0, Resistance int not null DEFAULT 0, Running int not null DEFAULT 0, Swimming int not null DEFAULT 0,
		Knowledge int not null DEFAULT 1, ArcaneLore int not null DEFAULT 0, Demolitions int not null DEFAULT 0, Languages int not null DEFAULT 0, Medicine int not null DEFAULT 0, Scholar int not null DEFAULT 0, Science int not null DEFAULT 0, Security int not null DEFAULT 0,
		Perception int not null DEFAULT 1, Artist int not null DEFAULT 0, Engineering int not null DEFAULT 0, Search int not null DEFAULT 0, Streetwise int not null DEFAULT 0, Surveillance int not null DEFAULT 0, Survival int not null DEFAULT 0,
		Presence int not null DEFAULT 1, Bluff int not null DEFAULT 0, Charm int not null DEFAULT 0, Intimidation int not null DEFAULT 0, Persuasion int not null DEFAULT 0, Willpower int not null DEFAULT 0,
	-- Advantages and Disadvantages (need to convert into reads from tables)
		-- all referenced from CharacterAdvantages and CharacterDisadvantages tables, via CharacterID FK
	-- Powers (I think this works, though it's limited to 4 powers... could maybe fix it by making Power Field a separate table?)
	Power1Level int,
	Power1ID int, -- FK to ID PK from Powers table
	Power2Level int,
	Power2ID int, -- FK to ID PK from Powers table
	Power3Level int,
	Power3ID int, -- FK to ID PK from Powers table
	Power4Level int,
	Power4ID int, -- FK to ID PK from Powers table
	-- Social Pool
		-- All referenced from Social Pool tables, eg. via SocialID from Contacts
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

-- Table for Skill Specializations, each skill spec is unique per character
	create table SkillSpecs (
		ID int not null auto_increment, primary key (ID),
		CharacterID int, foreign key (CharacterID) references Characters(ID), -- FK to Character this spec is for
		Skill varchar(32) not null, -- Skill this is a Spec for
		Spec varchar(32) not null, -- Name of the spec, ie. Piano, Double Kick, Biology
		Level int not null DEFAULT 1 check(Level >= 1 and Level <= 3) -- level of the spec, min of 1, max of 3
	);
    
-- The following are the Advantages and Disadvantages related tables
	-- table that stores all Advantages
	create table Advantages (
		ID int not null auto_increment, primary key (ID),
		Name varchar(32) not null,
        Cost int not null,
		Description varchar(256)
	);
	-- table that stores all Disadvantages
	create table Disadvantages (
		ID int not null auto_increment, primary key (ID),
		Name varchar(32) not null,
		Description varchar(256)
	);
	-- Table that manages a specific Character's unique list of Advantages
    create table CharacterAdvantages (
		ID int not null auto_increment, primary key (ID),
        CharacterID int, foreign key (CharacterID) references Characters(ID),
        AdvantageID int, foreign key (AdvantageID) references Advantages(ID)
	);
	-- Table that manages a specific Character's unique list of Disadvantages
    create table CharacterDisadvantages (
		ID int not null auto_increment, primary key (ID),
        CharacterID int, foreign key (CharacterID) references Characters(ID),
        DisadvantageID int, foreign key (DisadvantageID) references Disadvantages(ID)
	);


-- Powers and Packages
	-- is this finished? just need to add correct string lengths I guess
	create table Powers (
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
	-- Power Package table - rows here are unique to characters, combining level, power, specs, and weaknesses   
	create table CharacterPowers (
		ID int not null auto_increment, primary key (ID),
		CharacterID int, foreign key (CharacterID) references Characters(ID), -- FK to Character this powerset belongs to
		Level int check(Level >= 0),
		PowerID int, foreign key (PowerID) references Powers(ID), -- FK to Power Description for this Power Package
        Cost int, -- DEFAULT (select Cost from Powers p where PowerID = p.ID) -- Cost in Power Points defaults to Cost from Power, can be overwritten, NEED TO FIGURE THIS OUT
		Specializations varchar(256),
		Weakness varchar(32)
	);
		

-- SOCIAL TABLES. Each addition to a table is unique to the character via SocialID FK
	create table Allies (
		ID int not null auto_increment, primary key (ID),
		SocialID int not null, foreign key (SocialID) references Characters(ID),
		Level int, -- Null is allowed, in case social level doesn't matter
		Name varchar(64) not null,
		Description varchar(256)
	);
	create table Contacts (
		ID int not null auto_increment, primary key (ID),
		SocialID int not null, foreign key (SocialID) references Characters(ID),
		Level int, -- Null is allowed, in case social level doesn't matter
		Name varchar(64) not null,
		Description varchar(256)
	);        
	create table Familiars (
		ID int not null auto_increment, primary key (ID),
		SocialID int not null, foreign key (SocialID) references Characters(ID),
		Level int, -- Null is allowed, in case social level doesn't matter
		Name varchar(64) not null,
		Description varchar(256)
	);
	create table Followers (
		ID int not null auto_increment, primary key (ID),
		SocialID int not null, foreign key (SocialID) references Characters(ID),
		Level int, -- Null is allowed, in case social level doesn't matter
		Name varchar(64) not null,
		Description varchar(256)
	);
	create table Patrons (
		ID int not null auto_increment, primary key (ID),
		SocialID int not null, foreign key (SocialID) references Characters(ID),
		Level int, -- Null is allowed, in case social level doesn't matter
		Name varchar(64) not null,
		Description varchar(256)
	);
	create table Dependents (
		ID int not null auto_increment, primary key (ID),
		SocialID int not null, foreign key (SocialID) references Characters(ID),
		Level int, -- Null is allowed, in case social level doesn't matter
		Name varchar(64) not null,
		Description varchar(256)
	);
	create table Enemies (
		ID int not null auto_increment, primary key (ID),
		SocialID int not null, foreign key (SocialID) references Characters(ID),
		Level int, -- Null is allowed, in case social level doesn't matter
		Name varchar(64) not null,
		Description varchar(256)
	);
	create table SwornEnemies (
		ID int not null auto_increment, primary key (ID),
		SocialID int not null, foreign key (SocialID) references Characters(ID),
		Level int, -- Null is allowed, in case social level doesn't matter
		Name varchar(64) not null,
		Description varchar(256)
	);
    
-- LOCATION TABLES
	-- Hubs are places such as Archduchy
	create table Hubs (
		ID int not null auto_increment, primary key (ID),
		Name varchar(64) not null,
		LeaderID int, foreign key (LeaderID) references Characters(ID)-- FK to Character who is leader of this Area
		-- Areas present in Hub are referred to by (Area)HubID FK
		-- Character present at Hub are referred to by (Character)LocationID FK joined to (Location)AreaID FK joined to (Area)HubID FK
	);
	-- Areas are places such as Colgate Heights
	create table Areas (
		ID int not null auto_increment, primary key (ID),
		Name varchar(64) not null,
		HubID int, foreign key (HubID) references Hubs(ID), -- FK to Hub this Area is within
		LeaderID int, foreign key (LeaderID) references Characters(ID)-- FK to Character who is leader of this Area
		-- Characters present at Area are referred to by (Character)LocationID FK joined to (Location)AreaID FK
		-- Locations present at Area are referred to by (Location)AreaID FK
	);
	-- Locations are places such as C Building
	create table Locations (
		ID int not null auto_increment, primary key (ID),
		Name varchar(64) not null,
		AreaID int, foreign key (AreaID) references Areas(ID), -- FK to Area this Location is within
		LeaderID int, foreign key (SocialID) references Characters(ID) -- FK to Character who is leader of this Location
		-- Characters present at location are referred to by (Character)LocationID FK
		-- Items present at location are referred to by (Item)LocationID FK
	);
    
-- Populate Database with Advantages
    insert Advantages (Name, Cost, Description) values
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
				