use sys;
drop database if exists noMansLand;
create database noMansLand;
use noMansLand;

-- create all tables
create table Characters (
	ID int not null auto_increment, primary key(ID), 
    -- Demographics
    Name varchar(60), 
    Picture varchar(128), -- filename
    Occupation varchar(60), 
    BaseOfOperations varchar(60),
    LocationID int, -- foreign key to Location Table
    Gender varchar(15), 
    Race varchar(25), 
    Height varchar(60), 
    Weight varchar(60), 
    EyeColor varchar(15), 
    HairColor varchar(15),
    -- Misc. Stats
    UDO varchar(15), 
    Speed int, 
    HeroPoints int, 
    VillainPoints int, 
    AvailableRenown int, 
    PowerPoints int, 
    SkillPoints int, 
    BodyPointsCurrent int, 
    BodyPointsMax int,
    -- Stats
    Reflexes int, 
    Acrobatics int,
    AcrobaticsSpecs varchar(128),
	Dodge int,
    DodgeSpecs varchar(128)
	HandToHand int,
    HandToHandSpecs varchar(128)
    MeleeWeapons int,
    MeleeWeaponsSpecs varchar(128)
    Stealth int,
    StealthSpecs varchar(128)
    Coordination int,
    Catch int,
    CatchSpecs varchar(128)
    Climb int,
    ClimbSpecs varchar(128)
    Drive int,
    DriveSpecs varchar(128)
    Marksmanship int,
    MarksmanshipSpecs varchar(128),
    Thievery int,
    ThieverySpecs varchar(128),
    ThrownWeapons int,
    ThrownWeaponsSpecs varchar(128),
    Physique int,
    Athletics int,
    AthleticsSpecs varchar(128),
    Leap int,
    LeapSpecs varchar(128),
    Lifting int,
    LiftingSpecs varchar(128),
    Resistance int,
    ResistanceSpecs varchar(128),
    Running int, 
    RunningSpecs varchar(128),
    Swimming int,
    SwimmingSpecs varchar(128),
    Knowledge int,
    ArcaneLore int,
    ArcaneLoreSpecs varchar(128),
    Demolitions int,
    DemolitionsSpecs varchar(128),
    Languages int,
    LanguagesSpecs varchar(128),
    Medicine int,
    MedicineSpecs varchar(128),
    Scholar int,
    ScholarSpecs varchar(128),
    Science int,
    ScienceSpecs varchar(128),
    Security int,
    SecuritySpecs varchar(128),
    Perception int,
    Artist int,
    ArtistSpecs varchar(128),
    Engineering int,
    EngineeringSpecs varchar(128),
    Search int,
    SearchSpecs varchar(128),
    Streetwise int,
    StreetwiseSpecs varchar(128),
    Surveillance int,
    SurveillanceSpecs varchar(128),
    Survival int,
    SurvivalSpecs varchar(128),
    Presence int,
    Bluff int,
    BluffSpecs varchar(128),
    Charm int,
    CharmSpecs varchar(128),
    Intimidation int,
    IntimidationSpecs varchar(128),
    Persuasion int,
    PersuasionSpecs varchar(128),
    Willpower int,
    WillpowerSpecs varchar(128),
    -- Advantages and Disadvantages
    Advantages varchar(1028),
    Disadvantages varchar(1028),
    -- Powers
    Powers varchar(1028) -- we'll need to work on this one
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
    
    -- Locations are places such as C Building
    create table Locations (
    ID int not null auto_increment, primary key (ID),
    Name varchar(64),
    Area varchar(64),
    Leader int -- foreign key to Character
    -- This line reserved for list of characters present
    -- This line reserved for list of items present
    )
    
    -- Areas are places such as Colgate Heights
    create table Areas (
    ID int not null auto_increment, primary key (ID),
    Name varchar(64),
    Leader int, -- foreign key to Character
    -- This line reserved for list of characters present
    -- This line reserved for list of Locations present
    )
    
    -- Hubs are places such as Archduchy
    create table Hubs (
    ID int not null auto_increment, primary key (ID),
    Name varchar(64),
    Leader int -- foreign key to Character
    -- This line reserved for list of Areas present
    )
    