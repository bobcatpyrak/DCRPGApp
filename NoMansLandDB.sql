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
		Description varchar(128)
	);
	-- table that stores all Disadvantages
	create table Disadvantages (
		ID int not null auto_increment, primary key (ID),
		Name varchar(32) not null,
		Description varchar(128)
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
	-- this is not finished
	create table Powers (
		ID int not null auto_increment, primary key (ID),
		Name varchar(32) not null,
        Cost int, -- cost of a level of power in terms of Power Points
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