-- script to create No Man's Land database

use sys;
drop database if exists noMansLand;
create database noMansLand;
use noMansLand;

-- create all tables
-- NEED UPDATE - FK's for all items, socials in Character Table
create table Characters (
	ID int not null auto_increment, primary key(ID), 
    -- Demographics
		Name varchar(64) not null, 
		Picture varchar(128), -- filename
		Occupation varchar(64), 
		BaseOfOperations varchar(64),
		LocationID int, -- FK to Location Table
		Gender varchar(16), 
		Race varchar(16), 
		Height varchar(16), 
		Weight varchar(16), 
		EyeColor varchar(16), 
		HairColor varchar(16),
    -- Misc. Stats
		UDODice varchar(16), -- the dice value for UDO, ie. 1d6
		UDOBonus int, -- the bonus to damage added to the UDO, ie. +5
		Speed int, 
		HeroPoints int, VillainPoints int, AvailableRenown int, 
		PowerPoints int, SkillPoints int, 
		BodyPointsCurrent int, BodyPointsMax int,
    -- Stats
		Reflexes int not null, Acrobatics int not null, Dodge int not null, HandToHand int not null, MeleeWeapons int not null, Stealth int not null,
		Coordination int not null, Catch int not null, Climb int not null, Drive int not null, Marksmanship int not null, Thievery int not null, ThrownWeapons int not null,
		Physique int not null, Athletics int not null, Leap int not null, Lifting int not null, Resistance int not null, Running int not null, Swimming int not null,
		Knowledge int not null, ArcaneLore int not null, Demolitions int not null, Languages int not null, Medicine int not null, Scholar int not null, Science int not null, Security int not null,
		Perception int not null, Artist int not null, Engineering int not null, Search int not null, Streetwise int not null, Surveillance int not null, Survival int not null,
		Presence int not null, Bluff int not null, Charm int not null, Intimidation int not null, Persuasion int not null, Willpower int not null,
    -- Advantages and Disadvantages (need to convert into reads from tables)
    Advantages varchar(1028),
    Disadvantages varchar(1028),
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
		-- Positive
	Allies varchar(1028),
    Contacts varchar(1028),
    Familiars varchar(1028),
    Followers varchar(1028),
    Patrons varchar(1028),
		-- Negative
	Dependents varchar(1028),
    Enemies varchar(1028),
    SwornEnemies varchar(1028),
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
    )
    
    create table SkillSpecs (
    ID int not null auto_increment, primary key (ID),
    CharacterID int, -- FK to Character this spec is for
    Skill varchar(32), -- Skill this is a Spec for
    Spec varchar(32), -- Name of the spec, ie. Piano, Double Kick, Biology
    Level int -- level of the spec, max of 3
    )
    -- Locations are places such as C Building
    create table Locations (
    ID int not null auto_increment, primary key (ID),
    Name varchar(64) not null,
    AreaID int, -- FK to Area this Location is within
    LeaderID int -- FK to Character who is leader of this Location
    -- Characters present at location are referred to by LocationID FK in Character table
    -- Items present at location are referred to by LocationID FK in Item table
    )
    
    -- Areas are places such as Colgate Heights
    create table Areas (
    ID int not null auto_increment, primary key (ID),
    Name varchar(64) not null,
    HubID int, -- FK to Hub this Area is within
    LeaderID int, -- FK to Character who is leader of this Area
    -- Characters present at Area are referred to by LocationID FK joined to AreaID FK from Character to Location tables
    -- Locations present at Area are referred to by AreaID FK from Location table
    )
    
    -- Hubs are places such as Archduchy
    create table Hubs (
    ID int not null auto_increment, primary key (ID),
    Name varchar(64) not null,
    LeaderID int -- FK to Character who is leader of this Area
    -- Areas present in Hub are referred to by HubID FK from Area Table
    -- Character present at Hub are referred to by LocationID FK joined to AreaID FK joined to HubID FK from Character to Location to Area tables
    )
    
    
    create table Powers (
    ID int not null auto_increment, primary key (ID),
    Name varchar(16) not null,
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
    )
    
    