package gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import business.*;
import db.*;
import library.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainWindow {

	private static TextFile dao = new TextFile();
	private static List<CharacterSheet> sheets;
	private static String[] sheetNames;
	private static List<SkillSpec> specs;
	private static List<CharacterSheetAdvantage> advs;
	private static List<CharacterSheetDisadvantage> disadvs;
	private static CharacterSheet currentSheet;
	
	private static int nextSheetId;
	private static int nextSpecId;
	private static int nextCSAId;
	private static int nextCSDId;
	
	private JFrame dcrpgFrame;
	private JFormattedTextField nameSearchField;
	private JFormattedTextField nameField;
	private JFormattedTextField udoField;
	private JFormattedTextField bodyPointsField;
	private JFormattedTextField speedField;
	private JFormattedTextField genderField;
	private JFormattedTextField raceField;
	private JFormattedTextField heightField;
	private JFormattedTextField weightField;
	private JFormattedTextField heroPointsField;
	private JFormattedTextField villainPointsField;
	private JFormattedTextField powerPointsField;
	private JFormattedTextField skillPointsField;
	private JFormattedTextField occupationField;
	private JFormattedTextField baseOfOperationsField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		createDir();
		
		sheets = dao.getAllSheets();
		specs = dao.getAllSpecs();
		advs = dao.getAllCSA();
		disadvs = dao.getAllCSD();
		
		nextSheetId = 0;
		nextSpecId = 0;
		nextCSAId = 0;
		nextCSDId = 0;
		
		if(sheets.size() > 0)
			nextSheetId = sheets.get(sheets.size()-1).getId() + 1;
		if(specs.size() > 0)
			nextSpecId = specs.get(specs.size()-1).getId() + 1;
		if(advs.size() > 0)
			nextCSAId = advs.get(advs.size()-1).getId() + 1;
		if(disadvs.size() > 0)
			nextCSDId = disadvs.get(disadvs.size()-1).getId() + 1;
		
		//this needs to rerun when a new sheet is saved
		sheetNames = new String[sheets.size()];
		int nameIndex = 0;
		for(CharacterSheet s : sheets)
		{
			sheetNames[nameIndex] = s.getName();
		}
		
		// initialize currentSheet
		currentSheet = sheets.get(0);

		
		CharacterSheet batman = new CharacterSheet(nextSheetId);
		batman.setName("Batman2");
		batman.setPicture("batman.png");
		batman.setAllDemographics("CEO%Batcave%45%Male%Human%Tall%Muscular%Dark%Dark");
		batman.setAllMiscStats("2d6%6%5%68%3%250%0%17%45%46");
		batman.setAllStats("6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6");
		
		SkillSpec batarang = new SkillSpec(nextSpecId, nextSheetId, "Thrown Weapons", "Batarangs");
		CharacterSheetAdvantage batmanWealth = new CharacterSheetAdvantage(nextCSAId, nextSheetId, Advantage.WEALTH);
		CharacterSheetDisadvantage batmanSecretIdentity = new CharacterSheetDisadvantage(nextCSDId, nextSheetId, Disadvantage.SECRET_IDENTITY);
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.dcrpgFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	private static void createDir()
	{
		// Creates a folder for sheets if doesn't exist
		String dirString = "DCRPGCharacterSheets";
		Path dirPath = Paths.get(dirString);
		if (Files.notExists(dirPath))
			try 
			{
				Files.createDirectories(dirPath);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		
		// Creates a save file if doesn't exist
		String fileString = "DCRPGCharacterSheets.txt";
		Path filePath = Paths.get(dirString, fileString);
		if (Files.notExists(filePath))
			try {
				Files.createFile(filePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
	

	
	
	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		PropertyChangeListener pcl = new PropertyChangeListener()
	    {
	    public void propertyChange(final PropertyChangeEvent e)
	    {
	        if (e.getNewValue() instanceof JFormattedTextField)
	        {
	            SwingUtilities.invokeLater(new Runnable()
	            {
	                public void run()
	                {
	                    JFormattedTextField textField = (JFormattedTextField)e.getNewValue();
	                    textField.selectAll();
	                }
	            });

	        }
	    }
	};
		
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("permanentFocusOwner", pcl);
		
		dcrpgFrame = new JFrame();
		dcrpgFrame.getContentPane().setBackground(UIManager.getColor("Panel.background"));
		dcrpgFrame.setTitle("DCRPG App");
		dcrpgFrame.setForeground(new Color(204, 0, 51));
		dcrpgFrame.setBackground(Color.RED);
		dcrpgFrame.setBounds(100, 100, 1920, 1080);
		dcrpgFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dcrpgFrame.getContentPane().setLayout(null);
		
		
		NumberFormat nums = NumberFormat.getNumberInstance();
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
		nameLabel.setBounds(429, 6, 131, 42);
		dcrpgFrame.getContentPane().add(nameLabel);
		
		nameSearchField = new JFormattedTextField();
		nameSearchField.setHorizontalAlignment(SwingConstants.RIGHT);
		nameSearchField.setText("(type character name)");
		nameSearchField.setBounds(10, 12, 148, 20);
		dcrpgFrame.getContentPane().add(nameSearchField);
		nameSearchField.setColumns(10);
		
		nameField = new JFormattedTextField();
		nameField.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setBounds(570, 6, 367, 42);
		dcrpgFrame.getContentPane().add(nameField);
		nameField.setColumns(10);
		nameField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				currentSheet.setName(nameField.getText());
			}
		});
		
		JLabel udoLabel = new JLabel("UDO");
		udoLabel.setBounds(774, 59, 90, 20);
		dcrpgFrame.getContentPane().add(udoLabel);
		
		JLabel bodyPointsLabel = new JLabel("Body Points");
		bodyPointsLabel.setBounds(774, 84, 90, 20);
		dcrpgFrame.getContentPane().add(bodyPointsLabel);
		
		JLabel speedLabel = new JLabel("Speed");
		speedLabel.setBounds(774, 109, 90, 20);
		dcrpgFrame.getContentPane().add(speedLabel);
		
		JPanel demographicsPanel = new JPanel();
		demographicsPanel.setBackground(new Color(204, 255, 255));
		demographicsPanel.setBounds(44, 59, 298, 70);
		dcrpgFrame.getContentPane().add(demographicsPanel);
		demographicsPanel.setLayout(null);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(0, 0, 90, 20);
		demographicsPanel.add(lblGender);
		
		JLabel lblRace = new JLabel("Race");
		lblRace.setBounds(0, 25, 90, 20);
		demographicsPanel.add(lblRace);
		
		JLabel lblEyeColor = new JLabel("Eye Color");
		lblEyeColor.setBounds(0, 50, 90, 20);
		demographicsPanel.add(lblEyeColor);
		
		JLabel lblHeight = new JLabel("Height");
		lblHeight.setBounds(156, 0, 90, 20);
		demographicsPanel.add(lblHeight);
		
		JLabel lblWeight = new JLabel("Weight");
		lblWeight.setBounds(156, 25, 90, 20);
		demographicsPanel.add(lblWeight);
		
		JLabel lblHairColor = new JLabel("Hair Color");
		lblHairColor.setBounds(156, 50, 90, 20);
		demographicsPanel.add(lblHairColor);
		
		genderField = new JFormattedTextField();
		genderField.setBounds(53, 0, 86, 20);
		demographicsPanel.add(genderField);
		genderField.setHorizontalAlignment(SwingConstants.RIGHT);
		genderField.setColumns(10);
		genderField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				currentSheet.setGender(genderField.getText());
			}
		});
		
		raceField = new JFormattedTextField();
		raceField.setBounds(53, 25, 86, 20);
		demographicsPanel.add(raceField);
		raceField.setHorizontalAlignment(SwingConstants.RIGHT);
		raceField.setColumns(10);
		raceField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				currentSheet.setRace(raceField.getText());
			}
		});
		
		JFormattedTextField eyeColorField = new JFormattedTextField();
		eyeColorField.setBounds(53, 50, 86, 20);
		demographicsPanel.add(eyeColorField);
		eyeColorField.setHorizontalAlignment(SwingConstants.RIGHT);
		eyeColorField.setColumns(10);
		eyeColorField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				currentSheet.setEyeColor(eyeColorField.getText());
			}
		});
		
		heightField = new JFormattedTextField();
		heightField.setBounds(212, 0, 86, 20);
		demographicsPanel.add(heightField);
		heightField.setHorizontalAlignment(SwingConstants.RIGHT);
		heightField.setColumns(10);
		heightField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				currentSheet.setHeight(heightField.getText());
			}
		});
		
		weightField = new JFormattedTextField();
		weightField.setBounds(212, 25, 86, 20);
		demographicsPanel.add(weightField);
		weightField.setHorizontalAlignment(SwingConstants.RIGHT);
		weightField.setColumns(10);
		weightField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				currentSheet.setWeight(weightField.getText());
			}
		});
		
		JFormattedTextField hairColorField = new JFormattedTextField((Format) null);
		hairColorField.setBounds(212, 50, 86, 20);
		demographicsPanel.add(hairColorField);
		hairColorField.setHorizontalAlignment(SwingConstants.RIGHT);
		hairColorField.setColumns(10);
		hairColorField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				currentSheet.setHairColor(hairColorField.getText());
			}
		});
		
		JCheckBox chckbxDemographics = new JCheckBox("Show Demographics");
		chckbxDemographics.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(chckbxDemographics.isSelected())
					demographicsPanel.setVisible(true);
				else if(!chckbxDemographics.isSelected())
					demographicsPanel.setVisible(false);
			}
		});
		chckbxDemographics.setSelected(true);
		chckbxDemographics.setBounds(38, 39, 164, 23);
		dcrpgFrame.getContentPane().add(chckbxDemographics);
		
		JLabel lblHeroPoints = new JLabel("Hero Points");
		lblHeroPoints.setBounds(367, 59, 90, 20);
		dcrpgFrame.getContentPane().add(lblHeroPoints);
		
		heroPointsField = new JFormattedTextField(nums);
		heroPointsField.setHorizontalAlignment(SwingConstants.RIGHT);
		heroPointsField.setColumns(10);
		heroPointsField.setBounds(460, 59, 86, 20);
		dcrpgFrame.getContentPane().add(heroPointsField);
		heroPointsField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				currentSheet.setHeroPoints(Integer.parseInt(heroPointsField.getText()));
			}
		});

		
		JLabel lblVillainPoints = new JLabel("Villain Points");
		lblVillainPoints.setBounds(367, 84, 90, 20);
		dcrpgFrame.getContentPane().add(lblVillainPoints);
		
		villainPointsField = new JFormattedTextField(nums);
		villainPointsField.setHorizontalAlignment(SwingConstants.RIGHT);
		villainPointsField.setColumns(10);
		villainPointsField.setBounds(460, 84, 86, 20);
		dcrpgFrame.getContentPane().add(villainPointsField);
		villainPointsField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				currentSheet.setVillainPoints(Integer.parseInt(villainPointsField.getText()));
			}
		});

		
		JLabel lblAvailableRenown = new JLabel("Available Renown");
		lblAvailableRenown.setBounds(367, 109, 90, 20);
		dcrpgFrame.getContentPane().add(lblAvailableRenown);
		
		JFormattedTextField availableRenownField = new JFormattedTextField(nums);
		availableRenownField.setHorizontalAlignment(SwingConstants.RIGHT);
		availableRenownField.setColumns(10);
		availableRenownField.setBounds(460, 109, 86, 20);
		dcrpgFrame.getContentPane().add(availableRenownField);
		availableRenownField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				currentSheet.setAvailableRenown(Integer.parseInt(availableRenownField.getText()));
			}
		});

		
		JLabel lblPowerPoints = new JLabel("Power Points");
		lblPowerPoints.setBounds(570, 59, 90, 20);
		dcrpgFrame.getContentPane().add(lblPowerPoints);
		
		powerPointsField = new JFormattedTextField(nums);
		powerPointsField.setHorizontalAlignment(SwingConstants.RIGHT);
		powerPointsField.setColumns(10);
		powerPointsField.setBounds(663, 59, 86, 20);
		dcrpgFrame.getContentPane().add(powerPointsField);
		powerPointsField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				currentSheet.setPowerPoints(Integer.parseInt(powerPointsField.getText()));
			}
		});

		
		JLabel lblSkillPoints = new JLabel("Skill Points");
		lblSkillPoints.setBounds(570, 84, 90, 20);
		dcrpgFrame.getContentPane().add(lblSkillPoints);
				
		JLabel lblOccupation = new JLabel("Occupation");
		lblOccupation.setBounds(947, 6, 90, 20);
		dcrpgFrame.getContentPane().add(lblOccupation);
		
		occupationField = new JFormattedTextField();
		occupationField.setHorizontalAlignment(SwingConstants.RIGHT);
		occupationField.setColumns(10);
		occupationField.setBounds(1016, 6, 176, 20);
		dcrpgFrame.getContentPane().add(occupationField);
		occupationField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				currentSheet.setOccupation(occupationField.getText());
			}
		});

		
		JLabel lblHomeBase = new JLabel("Home Base");
		lblHomeBase.setBounds(947, 31, 90, 20);
		dcrpgFrame.getContentPane().add(lblHomeBase);
		
		baseOfOperationsField = new JFormattedTextField();
		baseOfOperationsField.setHorizontalAlignment(SwingConstants.RIGHT);
		baseOfOperationsField.setColumns(10);
		baseOfOperationsField.setBounds(1016, 30, 176, 20);
		dcrpgFrame.getContentPane().add(baseOfOperationsField);
		baseOfOperationsField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				currentSheet.setBaseOfOperations(baseOfOperationsField.getText());
			}
		});
	
		udoField = new JFormattedTextField();
		udoField.setHorizontalAlignment(SwingConstants.RIGHT);
		udoField.setBounds(867, 59, 86, 20);
		dcrpgFrame.getContentPane().add(udoField);
		udoField.setColumns(10);
		udoField.addKeyListener(new KeyAdapter()////////////////this doesn't work
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				String[] udos = udoField.getText().split("[+]");
				if(udos.length > 0)
					currentSheet.setUdoDice(udos[0]);
				if(udos.length > 1)
					currentSheet.setUdoBonus(Integer.parseInt(udos[1]));
			}
		});
		
		bodyPointsField = new JFormattedTextField();
		bodyPointsField.setHorizontalAlignment(SwingConstants.RIGHT);
		bodyPointsField.setBounds(867, 84, 86, 20);
		dcrpgFrame.getContentPane().add(bodyPointsField);
		bodyPointsField.setColumns(10);
		bodyPointsField.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				String[] bps = bodyPointsField.getText().split("/");
				if(bps.length > 0)
					currentSheet.setBodyPointsCurrent(Integer.parseInt(bps[0]));
				if(bps.length > 1)
					currentSheet.setBodyPointsMax(Integer.parseInt(bps[1]));
			}
		});

		
		skillPointsField = new JFormattedTextField(nums);
		skillPointsField.setHorizontalAlignment(SwingConstants.RIGHT);
		skillPointsField.setColumns(10);
		skillPointsField.setBounds(663, 84, 86, 20);
		dcrpgFrame.getContentPane().add(skillPointsField);
		skillPointsField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				currentSheet.setSkillPoints(Integer.parseInt(skillPointsField.getText()));
			}
		});

		
		speedField = new JFormattedTextField(nums);
		speedField.setHorizontalAlignment(SwingConstants.RIGHT);
		speedField.setBounds(867, 109, 86, 20);
		dcrpgFrame.getContentPane().add(speedField);
		speedField.setColumns(10);
		speedField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				currentSheet.setSpeed(Integer.parseInt(speedField.getText()));
			}
		});

		
		JButton btnNew = new JButton("New");
		btnNew.setBounds(247, 11, 69, 23);
		dcrpgFrame.getContentPane().add(btnNew);
		
		
		Panel reflexesPanel = new Panel();
		reflexesPanel.setBackground(new Color(255, 51, 51));
		reflexesPanel.setBounds(44, 149, 354, 274);
		dcrpgFrame.getContentPane().add(reflexesPanel);
		reflexesPanel.setLayout(null);
		
		Label reflexesLabel = new Label("Reflexes");
		reflexesLabel.setFont(new Font("Verdana", Font.BOLD, 22));
		reflexesLabel.setBounds(132, 10, 90, 30);
		reflexesPanel.add(reflexesLabel);
		
		JFormattedTextField reflexesLevel = new JFormattedTextField(nums);
		reflexesLevel.setColumns(2);
		reflexesLevel.setFont(new Font("Arial", Font.BOLD, 22));
		reflexesLevel.setHorizontalAlignment(SwingConstants.CENTER);
		reflexesLevel.setBounds(294, 10, 50, 35);
		reflexesPanel.add(reflexesLevel);
		
		Panel acroPanel = new Panel();
		acroPanel.setBackground(new Color(255, 153, 153));
		acroPanel.setBounds(0, 50, 354, 32);
		reflexesPanel.add(acroPanel);
		acroPanel.setLayout(null);
		
		Label acroLabel = new Label("Acrobatics");
		acroLabel.setBounds(35, 5, 120, 22);
		acroPanel.add(acroLabel);
		acroLabel.setFont(new Font("Verdana", Font.BOLD, 13));
				
		JFormattedTextField acroTotal = new JFormattedTextField();
		acroTotal.setHorizontalAlignment(SwingConstants.CENTER);
		acroTotal.setColumns(2);
		acroTotal.setEditable(false);
		acroTotal.setFont(new Font("Arial", Font.BOLD, 18));
		acroTotal.setBounds(294, 5, 50, 22);
		acroPanel.add(acroTotal);
		
		JFormattedTextField acroLevel = new JFormattedTextField(nums);
		acroLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(acroLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(acroLevel.getText());
					int j = Integer.parseInt(reflexesLevel.getText());
					currentSheet.setAcrobatics(i);
					acroTotal.setValue(i+j);	
				}
			}
		});
		
		acroLevel.setColumns(2);
		acroLevel.setFont(new Font("Arial", Font.BOLD, 14));
		acroLevel.setHorizontalAlignment(SwingConstants.CENTER);
		acroLevel.setBounds(5, 5, 24, 22);
		acroPanel.add(acroLevel);
		
		Panel dodgePanel = new Panel();
		dodgePanel.setLayout(null);
		dodgePanel.setBackground(new Color(255, 102, 102));
		dodgePanel.setBounds(0, 82, 354, 32);
		reflexesPanel.add(dodgePanel);
		
		Label dodgeLabel = new Label("Dodge");
		dodgeLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		dodgeLabel.setBounds(35, 5, 120, 22);
		dodgePanel.add(dodgeLabel);
		
		JFormattedTextField dodgeTotal = new JFormattedTextField();
		dodgeTotal.setColumns(2);
		dodgeTotal.setEditable(false);
		dodgeTotal.setHorizontalAlignment(SwingConstants.CENTER);
		dodgeTotal.setFont(new Font("Arial", Font.BOLD, 18));
		dodgeTotal.setBounds(294, 5, 50, 22);
		dodgePanel.add(dodgeTotal);
		
		JFormattedTextField dodgeLevel = new JFormattedTextField(nums);
		dodgeLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(dodgeLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(dodgeLevel.getText());
					int j = Integer.parseInt(reflexesLevel.getText());
					currentSheet.setDodge(i);
					dodgeTotal.setValue(i+j);	
				}
			}
		});
		dodgeLevel.setColumns(2);
		dodgeLevel.setFont(new Font("Arial", Font.BOLD, 14));
		dodgeLevel.setHorizontalAlignment(SwingConstants.CENTER);
		dodgeLevel.setBounds(5, 7, 24, 20);
		dodgePanel.add(dodgeLevel);
		

		
		Panel handToHandPanel = new Panel();
		handToHandPanel.setLayout(null);
		handToHandPanel.setBackground(new Color(255, 153, 153));
		handToHandPanel.setBounds(0, 114, 354, 32);
		reflexesPanel.add(handToHandPanel);
		
		Label handToHandLabel = new Label("Hand-to-Hand");
		handToHandLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		handToHandLabel.setBounds(35, 5, 120, 22);
		handToHandPanel.add(handToHandLabel);

		JFormattedTextField handToHandTotal = new JFormattedTextField();
		handToHandTotal.setColumns(2);
		handToHandTotal.setEditable(false);
		handToHandTotal.setFont(new Font("Arial", Font.BOLD, 18));
		handToHandTotal.setHorizontalAlignment(SwingConstants.CENTER);
		handToHandTotal.setBounds(294, 5, 50, 22);
		handToHandPanel.add(handToHandTotal);
		
		JFormattedTextField handToHandLevel = new JFormattedTextField(nums);
		handToHandLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(handToHandLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(handToHandLevel.getText());
					int j = Integer.parseInt(reflexesLevel.getText());
					currentSheet.setHandToHand(i);
					handToHandTotal.setValue(i+j);	
				}
			}
		});
		handToHandLevel.setColumns(2);
		handToHandLevel.setFont(new Font("Arial", Font.BOLD, 14));
		handToHandLevel.setHorizontalAlignment(SwingConstants.CENTER);
		handToHandLevel.setBounds(5, 5, 24, 22);
		handToHandPanel.add(handToHandLevel);
		
		
		
		Panel meleeWeaponsPanel = new Panel();
		meleeWeaponsPanel.setLayout(null);
		meleeWeaponsPanel.setBackground(new Color(255, 102, 102));
		meleeWeaponsPanel.setBounds(0, 146, 354, 32);
		reflexesPanel.add(meleeWeaponsPanel);
		
		Label meleeWeaponsLabel = new Label("Melee Weapons");
		meleeWeaponsLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		meleeWeaponsLabel.setBounds(35, 5, 120, 22);
		meleeWeaponsPanel.add(meleeWeaponsLabel);
		
		JFormattedTextField meleeWeaponsTotal = new JFormattedTextField();
		meleeWeaponsTotal.setColumns(2);
		meleeWeaponsTotal.setEditable(false);
		meleeWeaponsTotal.setHorizontalAlignment(SwingConstants.CENTER);
		meleeWeaponsTotal.setFont(new Font("Arial", Font.BOLD, 18));
		meleeWeaponsTotal.setBounds(294, 5, 50, 22);
		meleeWeaponsPanel.add(meleeWeaponsTotal);
		
		JFormattedTextField meleeWeaponsLevel = new JFormattedTextField(nums);
		meleeWeaponsLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(meleeWeaponsLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(meleeWeaponsLevel.getText());
					int j = Integer.parseInt(reflexesLevel.getText());
					currentSheet.setMeleeWeapons(i);
					meleeWeaponsTotal.setValue(i+j);	
				}
			}
		});
		meleeWeaponsLevel.setColumns(2);
		meleeWeaponsLevel.setFont(new Font("Arial", Font.BOLD, 14));
		meleeWeaponsLevel.setHorizontalAlignment(SwingConstants.CENTER);
		meleeWeaponsLevel.setBounds(5, 7, 24, 20);
		meleeWeaponsPanel.add(meleeWeaponsLevel);
		
		
		
		Panel stealthPanel = new Panel();
		stealthPanel.setLayout(null);
		stealthPanel.setBackground(new Color(255, 153, 153));
		stealthPanel.setBounds(0, 178, 354, 32);
		reflexesPanel.add(stealthPanel);
		
		Label stealthLabel = new Label("Stealth");
		stealthLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		stealthLabel.setBounds(35, 5, 120, 22);
		stealthPanel.add(stealthLabel);
		
		JFormattedTextField stealthTotal = new JFormattedTextField();
		stealthTotal.setHorizontalAlignment(SwingConstants.CENTER);
		stealthTotal.setColumns(2);
		stealthTotal.setEditable(false);
		stealthTotal.setFont(new Font("Arial", Font.BOLD, 18));
		stealthTotal.setBounds(294, 5, 50, 22);
		stealthPanel.add(stealthTotal);
		
		JFormattedTextField stealthLevel = new JFormattedTextField(nums);
		stealthLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(stealthLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(stealthLevel.getText());
					int j = Integer.parseInt(reflexesLevel.getText());
					currentSheet.setStealth(i);
					stealthTotal.setValue(i+j);	
				}
			}
		});
		stealthLevel.setColumns(2);
		stealthLevel.setHorizontalAlignment(SwingConstants.CENTER);
		stealthLevel.setFont(new Font("Arial", Font.BOLD, 14));
		stealthLevel.setBounds(5, 5, 24, 22);
		stealthPanel.add(stealthLevel);
		
		
		
		Panel placePanel = new Panel();
		placePanel.setLayout(null);
		placePanel.setBackground(new Color(255, 102, 102));
		placePanel.setBounds(0, 210, 354, 32);
		reflexesPanel.add(placePanel);
		
		Label placeLabel = new Label("Placeholder");
		placeLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		placeLabel.setBounds(35, 5, 120, 22);
		placePanel.add(placeLabel);
		
		JFormattedTextField placeTotal = new JFormattedTextField();
		placeTotal.setColumns(2);
		placeTotal.setEditable(false);
		placeTotal.setHorizontalAlignment(SwingConstants.CENTER);
		placeTotal.setFont(new Font("Arial", Font.BOLD, 18));
		placeTotal.setBounds(294, 5, 50, 22);
		placePanel.add(placeTotal);
		
		JFormattedTextField placeLevel = new JFormattedTextField(nums);
		placeLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(placeLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(placeLevel.getText());
					int j = Integer.parseInt(reflexesLevel.getText());
					placeTotal.setValue(i+j);	
				}
			}
		});
		placeLevel.setColumns(2);
		placeLevel.setFont(new Font("Arial", Font.BOLD, 14));
		placeLevel.setHorizontalAlignment(SwingConstants.CENTER);
		placeLevel.setBounds(5, 7, 24, 20);
		placeLevel.setValue(0); //DELET THIS
		placePanel.add(placeLevel);
		
		
		
		Panel place2Panel = new Panel();
		place2Panel.setLayout(null);
		place2Panel.setBackground(new Color(255, 153, 153));
		place2Panel.setBounds(0, 242, 354, 32);
		reflexesPanel.add(place2Panel);
		
		Label place2Label = new Label("Placeholder");
		place2Label.setFont(new Font("Verdana", Font.BOLD, 13));
		place2Label.setBounds(35, 5, 120, 22);
		place2Panel.add(place2Label);
		
		JFormattedTextField place2Total = new JFormattedTextField();
		place2Total.setHorizontalAlignment(SwingConstants.CENTER);
		place2Total.setColumns(2);
		place2Total.setEditable(false);
		place2Total.setFont(new Font("Arial", Font.BOLD, 18));
		place2Total.setBounds(294, 5, 50, 22);
		place2Panel.add(place2Total);
		
		JFormattedTextField place2Level = new JFormattedTextField(nums);
		place2Level.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(place2Level.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(place2Level.getText());
					int j = Integer.parseInt(reflexesLevel.getText());
					place2Total.setValue(i+j);	
				}
			}
		});
		place2Level.setColumns(2);
		place2Level.setHorizontalAlignment(SwingConstants.CENTER);
		place2Level.setFont(new Font("Arial", Font.BOLD, 14));
		place2Level.setBounds(5, 7, 24, 20);
		place2Level.setValue(0); //DELET THIS
		place2Panel.add(place2Level);
		
		reflexesLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(reflexesLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(reflexesLevel.getText());
					int j = Integer.parseInt(acroLevel.getText());
					int k = Integer.parseInt(dodgeLevel.getText());
					int l = Integer.parseInt(handToHandLevel.getText());
					int m = Integer.parseInt(meleeWeaponsLevel.getText());
					int n = Integer.parseInt(stealthLevel.getText());
					int o = Integer.parseInt(placeLevel.getText());
					int p = Integer.parseInt(place2Level.getText());

					currentSheet.setReflexes(i);
					acroTotal.setValue(i+j);
					dodgeTotal.setValue(i+k);
					handToHandTotal.setValue(i+l);	
					meleeWeaponsTotal.setValue(i+m);
					stealthTotal.setValue(i+n);
					placeTotal.setValue(i+o);
					place2Total.setValue(i+p);
				}
			}
		});
		
		// AcroSpecs
		List<JFormattedTextField> acroSpecsFields = new ArrayList<JFormattedTextField>();
		JCheckBox chckbxAcro = new JCheckBox("Show specs");
		chckbxAcro.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> acroSpecsList = currentSheet.assignSkillSpecs("Acrobatics");
				if(chckbxAcro.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "Acrobatics", "");
					acroSpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;

					int extra = (acroSpecsList.size())*28;
					
					reflexesPanel.setSize(reflexesPanel.getWidth(), (reflexesPanel.getHeight()+extra));
					acroPanel.setSize(354, (32+extra));
					dodgePanel.setLocation(0, dodgePanel.getY()+extra);
					handToHandPanel.setLocation(0, handToHandPanel.getY()+extra);
					meleeWeaponsPanel.setLocation(0, meleeWeaponsPanel.getY()+extra);
					stealthPanel.setLocation(0, stealthPanel.getY()+extra);
					placePanel.setLocation(0, placePanel.getY()+extra);
					place2Panel.setLocation(0, place2Panel.getY()+extra);

					for(int i = 0; i < acroSpecsList.size(); i++)
					{
						int j = i;
						acroSpecsFields.add(new JFormattedTextField());
						JFormattedTextField acroSpecs = acroSpecsFields.get(i);
						acroSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						acroSpecs.setBackground(new Color(255, 204, 204));
						acroSpecs.setBounds(5, 33+(i*28), 279, 22);
						acroPanel.add(acroSpecs);
						acroSpecs.setText(acroSpecsList.get(i).getDescription());
						acroSpecs.requestFocus();	

						acroSpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = acroSpecsList.get(j);
									editing.setDescription(acroSpecs.getText());
									for(SkillSpec ss : specs)
									{
										if(ss.getId() == editing.getId() && !ss.getDescription().replace(" ", "").equals(""))
										{
											ss.setDescription(editing.getDescription());
											currentSheet.setSkillSpecs(specs);
											break;
										}
									}
									if(e.getKeyCode() == KeyEvent.VK_ENTER)
									{
										chckbxAcro.setSelected(false);
										chckbxAcro.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxAcro.isSelected())
				{
					// WHY DUPLICATING A BLANK SPACE
					if(specs.size() > blankId && specs.get(blankId).getDescription().replace(" ", "").equals(""))
					{
						specs.remove(blankId);
						if(blankId == (nextSpecId-1))
							nextSpecId--;		
					}
										 
					boolean removingEmpty = true;
					while(removingEmpty)
					{
						for(int i = 0; i < specs.size(); i++)
						{
							for(SkillSpec ss : specs)
							{
								if(ss.getDescription().replace(" ", "").equals(""))
								{		
									System.out.println("destroyed");
									specs.remove(ss);
									currentSheet.setSkillSpecs(specs);
									break;
								}
							}
						}
						removingEmpty = false;
					}
						
					if(specs.size() > 0)
						nextSpecId = specs.get(specs.size()-1).getId() + 1;

					acroSpecsList.clear();
					for(JFormattedTextField t : acroSpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						acroPanel.remove(t);
						t = null;
					}
					acroSpecsFields.clear();
					
					reflexesPanel.setSize(reflexesPanel.getWidth(), (reflexesPanel.getHeight()-(acroPanel.getHeight()-32)));
					acroPanel.setSize(354, (32));
					dodgePanel.setLocation(0, acroPanel.getY()+32);
					handToHandPanel.setLocation(0, dodgePanel.getY()+dodgePanel.getHeight());
					meleeWeaponsPanel.setLocation(0, handToHandPanel.getY()+handToHandPanel.getHeight());
					stealthPanel.setLocation(0, meleeWeaponsPanel.getY()+meleeWeaponsPanel.getHeight());
					placePanel.setLocation(0, stealthPanel.getY()+stealthPanel.getHeight());
					place2Panel.setLocation(0, placePanel.getY()+placePanel.getHeight());
				}
			}
		});
		chckbxAcro.setBackground(new Color(255, 153, 153));
		chckbxAcro.setBounds(187, 5, 97, 23);
		acroPanel.add(chckbxAcro);
		
		// DodgeSpecs
		List<JFormattedTextField> dodgeSpecsFields = new ArrayList<JFormattedTextField>();
		JCheckBox chckbxDodge = new JCheckBox("Show specs");
		chckbxDodge.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> dodgeSpecsList = currentSheet.assignSkillSpecs("Dodge");
				if(chckbxDodge.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "Dodge", "");
					dodgeSpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;

					int extra = (dodgeSpecsList.size())*28;
					
					reflexesPanel.setSize(reflexesPanel.getWidth(), (reflexesPanel.getHeight()+extra));
					dodgePanel.setSize(354, (32+extra));
					handToHandPanel.setLocation(0, handToHandPanel.getY()+extra);
					meleeWeaponsPanel.setLocation(0, meleeWeaponsPanel.getY()+extra);
					stealthPanel.setLocation(0, stealthPanel.getY()+extra);
					placePanel.setLocation(0, placePanel.getY()+extra);
					place2Panel.setLocation(0, place2Panel.getY()+extra);

					for(int i = 0; i < dodgeSpecsList.size(); i++)
					{
						int j = i;
						dodgeSpecsFields.add(new JFormattedTextField());
						JFormattedTextField dodgeSpecs = dodgeSpecsFields.get(i);
						dodgeSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						dodgeSpecs.setBackground(new Color(255, 204, 204));
						dodgeSpecs.setBounds(5, 33+(i*28), 279, 22);
						dodgePanel.add(dodgeSpecs);
						dodgeSpecs.setText(dodgeSpecsList.get(i).getDescription());
						dodgeSpecs.requestFocus();	

						dodgeSpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = dodgeSpecsList.get(j);
									editing.setDescription(dodgeSpecs.getText());
									for(SkillSpec ss : specs)
									{
										if(ss.getId() == editing.getId() && !ss.getDescription().replace(" ", "").equals(""))
										{
											ss.setDescription(editing.getDescription());
											currentSheet.setSkillSpecs(specs);
											break;
										}
									}
									if(e.getKeyCode() == KeyEvent.VK_ENTER)
									{
										chckbxDodge.setSelected(false);
										chckbxDodge.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxDodge.isSelected())
				{
					if(specs.size() > blankId && specs.get(blankId).getDescription().replace(" ", "").equals(""))
					{
						specs.remove(blankId);
						if(blankId == (nextSpecId-1))
							nextSpecId--;		
					}
										 
					boolean removingEmpty = true;
					while(removingEmpty)
					{
						for(int i = 0; i < specs.size(); i++)
						{
							for(SkillSpec ss : specs)
							{
								if(ss.getDescription().replace(" ", "").equals(""))
								{		
									System.out.println("destroyed");
									specs.remove(ss);
									currentSheet.setSkillSpecs(specs);
									break;
								}
							}
						}
						removingEmpty = false;
					}
						
					if(specs.size() > 0)
						nextSpecId = specs.get(specs.size()-1).getId() + 1;

					dodgeSpecsList.clear();
					for(JFormattedTextField t : dodgeSpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						dodgePanel.remove(t);
						t = null;
					}
					dodgeSpecsFields.clear();
					
					reflexesPanel.setSize(reflexesPanel.getWidth(), (reflexesPanel.getHeight()-(dodgePanel.getHeight()-32)));
					dodgePanel.setSize(354, (32));
					handToHandPanel.setLocation(0, dodgePanel.getY()+dodgePanel.getHeight());
					meleeWeaponsPanel.setLocation(0, handToHandPanel.getY()+handToHandPanel.getHeight());
					stealthPanel.setLocation(0, meleeWeaponsPanel.getY()+meleeWeaponsPanel.getHeight());
					placePanel.setLocation(0, stealthPanel.getY()+stealthPanel.getHeight());
					place2Panel.setLocation(0, placePanel.getY()+placePanel.getHeight());
				}
			}
		});
		chckbxDodge.setBackground(new Color(255, 102, 102));
		chckbxDodge.setBounds(187, 5, 97, 23);
		dodgePanel.add(chckbxDodge);
		
		// HandToHandSpecs
		List<JFormattedTextField> handToHandSpecsFields = new ArrayList<JFormattedTextField>();
		JCheckBox chckbxHandToHand = new JCheckBox("Show specs");
		chckbxHandToHand.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> handToHandSpecsList = currentSheet.assignSkillSpecs("HandToHand");
				if(chckbxHandToHand.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "HandToHand", "");
					handToHandSpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;

					int extra = (handToHandSpecsList.size())*28;
					
					reflexesPanel.setSize(reflexesPanel.getWidth(), (reflexesPanel.getHeight()+extra));
					handToHandPanel.setSize(354, (32+extra));
					meleeWeaponsPanel.setLocation(0, meleeWeaponsPanel.getY()+extra);
					stealthPanel.setLocation(0, stealthPanel.getY()+extra);
					placePanel.setLocation(0, placePanel.getY()+extra);
					place2Panel.setLocation(0, place2Panel.getY()+extra);

					for(int i = 0; i < handToHandSpecsList.size(); i++)
					{
						int j = i;
						handToHandSpecsFields.add(new JFormattedTextField());
						JFormattedTextField handToHandSpecs = handToHandSpecsFields.get(i);
						handToHandSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						handToHandSpecs.setBackground(new Color(255, 204, 204));
						handToHandSpecs.setBounds(5, 33+(i*28), 279, 22);
						handToHandPanel.add(handToHandSpecs);
						handToHandSpecs.setText(handToHandSpecsList.get(i).getDescription());
						handToHandSpecs.requestFocus();	

						handToHandSpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = handToHandSpecsList.get(j);
									editing.setDescription(handToHandSpecs.getText());
									for(SkillSpec ss : specs)
									{
										if(ss.getId() == editing.getId() && !ss.getDescription().replace(" ", "").equals(""))
										{
											ss.setDescription(editing.getDescription());
											currentSheet.setSkillSpecs(specs);
											break;
										}
									}
									if(e.getKeyCode() == KeyEvent.VK_ENTER)
									{
										chckbxHandToHand.setSelected(false);
										chckbxHandToHand.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxHandToHand.isSelected())
				{
					if(specs.size() > blankId && specs.get(blankId).getDescription().replace(" ", "").equals(""))
					{
						specs.remove(blankId);
						if(blankId == (nextSpecId-1))
							nextSpecId--;		
					}
										 
					boolean removingEmpty = true;
					while(removingEmpty)
					{
						for(int i = 0; i < specs.size(); i++)
						{
							for(SkillSpec ss : specs)
							{
								if(ss.getDescription().replace(" ", "").equals(""))
								{		
									System.out.println("destroyed");
									specs.remove(ss);
									currentSheet.setSkillSpecs(specs);
									break;
								}
							}
						}
						removingEmpty = false;
					}
						
					if(specs.size() > 0)
						nextSpecId = specs.get(specs.size()-1).getId() + 1;

					handToHandSpecsList.clear();
					for(JFormattedTextField t : handToHandSpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						handToHandPanel.remove(t);
						t = null;
					}
					handToHandSpecsFields.clear();
					
					reflexesPanel.setSize(reflexesPanel.getWidth(), (reflexesPanel.getHeight()-(handToHandPanel.getHeight()-32)));
					handToHandPanel.setSize(354, (32));
					meleeWeaponsPanel.setLocation(0, handToHandPanel.getY()+handToHandPanel.getHeight());
					stealthPanel.setLocation(0, meleeWeaponsPanel.getY()+meleeWeaponsPanel.getHeight());
					placePanel.setLocation(0, stealthPanel.getY()+stealthPanel.getHeight());
					place2Panel.setLocation(0, placePanel.getY()+placePanel.getHeight());
				}
			}
		});
		chckbxHandToHand.setBackground(new Color(255, 153, 153));
		chckbxHandToHand.setBounds(187, 5, 97, 23);
		handToHandPanel.add(chckbxHandToHand);
		
		// MeleeWeaponsSpecs
		List<JFormattedTextField> meleeWeaponsSpecsFields = new ArrayList<JFormattedTextField>();
		JCheckBox chckbxMeleeWeapons = new JCheckBox("Show specs");
		chckbxMeleeWeapons.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> meleeWeaponsSpecsList = currentSheet.assignSkillSpecs("MeleeWeapons");
				if(chckbxMeleeWeapons.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "MeleeWeapons", "");
					meleeWeaponsSpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;

					int extra = (meleeWeaponsSpecsList.size())*28;
					
					reflexesPanel.setSize(reflexesPanel.getWidth(), (reflexesPanel.getHeight()+extra));
					meleeWeaponsPanel.setSize(354, (32+extra));
					stealthPanel.setLocation(0, stealthPanel.getY()+extra);
					placePanel.setLocation(0, placePanel.getY()+extra);
					place2Panel.setLocation(0, place2Panel.getY()+extra);

					for(int i = 0; i < meleeWeaponsSpecsList.size(); i++)
					{
						int j = i;
						meleeWeaponsSpecsFields.add(new JFormattedTextField());
						JFormattedTextField meleeWeaponsSpecs = meleeWeaponsSpecsFields.get(i);
						meleeWeaponsSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						meleeWeaponsSpecs.setBackground(new Color(255, 204, 204));
						meleeWeaponsSpecs.setBounds(5, 33+(i*28), 279, 22);
						meleeWeaponsPanel.add(meleeWeaponsSpecs);
						meleeWeaponsSpecs.setText(meleeWeaponsSpecsList.get(i).getDescription());
						meleeWeaponsSpecs.requestFocus();	

						meleeWeaponsSpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = meleeWeaponsSpecsList.get(j);
									editing.setDescription(meleeWeaponsSpecs.getText());
									for(SkillSpec ss : specs)
									{
										if(ss.getId() == editing.getId() && !ss.getDescription().replace(" ", "").equals(""))
										{
											ss.setDescription(editing.getDescription());
											currentSheet.setSkillSpecs(specs);
											break;
										}
									}
									if(e.getKeyCode() == KeyEvent.VK_ENTER)
									{
										chckbxMeleeWeapons.setSelected(false);
										chckbxMeleeWeapons.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxMeleeWeapons.isSelected())
				{
					if(specs.size() > blankId && specs.get(blankId).getDescription().replace(" ", "").equals(""))
					{
						specs.remove(blankId);
						if(blankId == (nextSpecId-1))
							nextSpecId--;		
					}
										 
					boolean removingEmpty = true;
					while(removingEmpty)
					{
						for(int i = 0; i < specs.size(); i++)
						{
							for(SkillSpec ss : specs)
							{
								if(ss.getDescription().replace(" ", "").equals(""))
								{		
									System.out.println("destroyed");
									specs.remove(ss);
									currentSheet.setSkillSpecs(specs);
									break;
								}
							}
						}
						removingEmpty = false;
					}
						
					if(specs.size() > 0)
						nextSpecId = specs.get(specs.size()-1).getId() + 1;

					meleeWeaponsSpecsList.clear();
					for(JFormattedTextField t : meleeWeaponsSpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						meleeWeaponsPanel.remove(t);
						t = null;
					}
					meleeWeaponsSpecsFields.clear();
					
					reflexesPanel.setSize(reflexesPanel.getWidth(), (reflexesPanel.getHeight()-(meleeWeaponsPanel.getHeight()-32)));
					meleeWeaponsPanel.setSize(354, (32));
					stealthPanel.setLocation(0, meleeWeaponsPanel.getY()+meleeWeaponsPanel.getHeight());
					placePanel.setLocation(0, stealthPanel.getY()+stealthPanel.getHeight());
					place2Panel.setLocation(0, placePanel.getY()+placePanel.getHeight());
				}
			}
		});
		chckbxMeleeWeapons.setBackground(new Color(255, 102, 102));
		chckbxMeleeWeapons.setBounds(187, 5, 97, 23);
		meleeWeaponsPanel.add(chckbxMeleeWeapons);
		
		// StealthSpecs
		List<JFormattedTextField> stealthSpecsFields = new ArrayList<JFormattedTextField>();
		JCheckBox chckbxStealth = new JCheckBox("Show specs");
		chckbxStealth.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> stealthSpecsList = currentSheet.assignSkillSpecs("Stealth");
				if(chckbxStealth.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "Stealth", "");
					stealthSpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;
					int extra = (stealthSpecsList.size())*28;
					
					reflexesPanel.setSize(reflexesPanel.getWidth(), (reflexesPanel.getHeight()+extra));
					stealthPanel.setSize(354, (32+extra));
					placePanel.setLocation(0, placePanel.getY()+extra);
					place2Panel.setLocation(0, place2Panel.getY()+extra);
					
					for(int i = 0; i < stealthSpecsList.size(); i++)
					{
						int j = i;
						stealthSpecsFields.add(new JFormattedTextField());
						JFormattedTextField stealthSpecs = stealthSpecsFields.get(i);
						stealthSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						stealthSpecs.setBackground(new Color(255, 204, 204));
						stealthSpecs.setBounds(5, 33+(i*28), 279, 22);
						stealthPanel.add(stealthSpecs);
						stealthSpecs.setText(stealthSpecsList.get(i).getDescription());
						stealthSpecs.requestFocus();	
						stealthSpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = stealthSpecsList.get(j);
									editing.setDescription(stealthSpecs.getText());
									for(SkillSpec ss : specs)
									{
										if(ss.getId() == editing.getId() && !ss.getDescription().replace(" ", "").equals(""))
										{
											ss.setDescription(editing.getDescription());
											currentSheet.setSkillSpecs(specs);
											break;
										}
									}
									if(e.getKeyCode() == KeyEvent.VK_ENTER)
									{
										chckbxStealth.setSelected(false);
										chckbxStealth.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxStealth.isSelected())
				{
					if(specs.size() > blankId && specs.get(blankId).getDescription().replace(" ", "").equals(""))
					{
						specs.remove(blankId);
						if(blankId == (nextSpecId-1))
							nextSpecId--;		
					}
										 
					boolean removingEmpty = true;
					while(removingEmpty)
					{
						for(int i = 0; i < specs.size(); i++)
						{
							for(SkillSpec ss : specs)
							{
								if(ss.getDescription().replace(" ", "").equals(""))
								{		
									System.out.println("destroyed");
									specs.remove(ss);
									currentSheet.setSkillSpecs(specs);
									break;
								}
							}
						}
						removingEmpty = false;
					}
						
					if(specs.size() > 0)
						nextSpecId = specs.get(specs.size()-1).getId() + 1;
						stealthSpecsList.clear();
					for(JFormattedTextField t : stealthSpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						stealthPanel.remove(t);
						t = null;
					}
					stealthSpecsFields.clear();
					
					reflexesPanel.setSize(reflexesPanel.getWidth(), (reflexesPanel.getHeight()-(stealthPanel.getHeight()-32)));
					stealthPanel.setSize(354, (32));
					placePanel.setLocation(0, stealthPanel.getY()+stealthPanel.getHeight());
					place2Panel.setLocation(0, placePanel.getY()+placePanel.getHeight());
				}
			}
		});
		chckbxStealth.setBackground(new Color(255, 153, 153));
		chckbxStealth.setBounds(187, 5, 97, 23);
		stealthPanel.add(chckbxStealth);
		
		// PlaceSpecs
		List<JFormattedTextField> placeSpecsFields = new ArrayList<JFormattedTextField>();
		JCheckBox chckbxPlace = new JCheckBox("Show specs");
		chckbxPlace.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> placeSpecsList = currentSheet.assignSkillSpecs("Place");
				if(chckbxPlace.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "Place", "");
					placeSpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;
					int extra = (placeSpecsList.size())*28;
					
					reflexesPanel.setSize(reflexesPanel.getWidth(), (reflexesPanel.getHeight()+extra));
					placePanel.setSize(354, (32+extra));
					place2Panel.setLocation(0, place2Panel.getY()+extra);
					
					for(int i = 0; i < placeSpecsList.size(); i++)
					{
						int j = i;
						placeSpecsFields.add(new JFormattedTextField());
						JFormattedTextField placeSpecs = placeSpecsFields.get(i);
						placeSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						placeSpecs.setBackground(new Color(255, 204, 204));
						placeSpecs.setBounds(5, 33+(i*28), 279, 22);
						placePanel.add(placeSpecs);
						placeSpecs.setText(placeSpecsList.get(i).getDescription());
						placeSpecs.requestFocus();	
						placeSpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = placeSpecsList.get(j);
									editing.setDescription(placeSpecs.getText());
									for(SkillSpec ss : specs)
									{
										if(ss.getId() == editing.getId() && !ss.getDescription().replace(" ", "").equals(""))
										{
											ss.setDescription(editing.getDescription());
											currentSheet.setSkillSpecs(specs);
											break;
										}
									}
									if(e.getKeyCode() == KeyEvent.VK_ENTER)
									{
										chckbxPlace.setSelected(false);
										chckbxPlace.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxPlace.isSelected())
				{
					if(specs.size() > blankId && specs.get(blankId).getDescription().replace(" ", "").equals(""))
					{
						specs.remove(blankId);
						if(blankId == (nextSpecId-1))
							nextSpecId--;		
					}
										 
					boolean removingEmpty = true;
					while(removingEmpty)
					{
						for(int i = 0; i < specs.size(); i++)
						{
							for(SkillSpec ss : specs)
							{
								if(ss.getDescription().replace(" ", "").equals(""))
								{		
									System.out.println("destroyed");
									specs.remove(ss);
									currentSheet.setSkillSpecs(specs);
									break;
								}
							}
						}
						removingEmpty = false;
					}
						
					if(specs.size() > 0)
						nextSpecId = specs.get(specs.size()-1).getId() + 1;
						placeSpecsList.clear();
					for(JFormattedTextField t : placeSpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						placePanel.remove(t);
						t = null;
					}
					placeSpecsFields.clear();
					
					reflexesPanel.setSize(reflexesPanel.getWidth(), (reflexesPanel.getHeight()-(placePanel.getHeight()-32)));
					placePanel.setSize(354, (32));
					place2Panel.setLocation(0, placePanel.getY()+placePanel.getHeight());
				}
			}
		});
		chckbxPlace.setBackground(new Color(255, 102, 102));
		chckbxPlace.setBounds(187, 5, 97, 23);
		placePanel.add(chckbxPlace);
		
		// Place2Specs
		List<JFormattedTextField> place2SpecsFields = new ArrayList<JFormattedTextField>();
		JCheckBox chckbxPlace2 = new JCheckBox("Show specs");
		chckbxPlace2.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> place2SpecsList = currentSheet.assignSkillSpecs("Place2");
				if(chckbxPlace2.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "Place2", "");
					place2SpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;
					int extra = (place2SpecsList.size())*28;
					
					reflexesPanel.setSize(reflexesPanel.getWidth(), (reflexesPanel.getHeight()+extra));
					place2Panel.setSize(354, (32+extra));
	
					
					for(int i = 0; i < place2SpecsList.size(); i++)
					{
						int j = i;
						place2SpecsFields.add(new JFormattedTextField());
						JFormattedTextField place2Specs = place2SpecsFields.get(i);
						place2Specs.setFont(new Font("Verdana", Font.PLAIN, 13));
						place2Specs.setBackground(new Color(255, 204, 204));
						place2Specs.setBounds(5, 33+(i*28), 279, 22);
						place2Panel.add(place2Specs);
						place2Specs.setText(place2SpecsList.get(i).getDescription());
						place2Specs.requestFocus();	
						place2Specs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = place2SpecsList.get(j);
									editing.setDescription(place2Specs.getText());
									for(SkillSpec ss : specs)
									{
										if(ss.getId() == editing.getId() && !ss.getDescription().replace(" ", "").equals(""))
										{
											ss.setDescription(editing.getDescription());
											currentSheet.setSkillSpecs(specs);
											break;
										}
									}
									if(e.getKeyCode() == KeyEvent.VK_ENTER)
									{
										chckbxPlace2.setSelected(false);
										chckbxPlace2.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxPlace2.isSelected())
				{
					if(specs.size() > blankId && specs.get(blankId).getDescription().replace(" ", "").equals(""))
					{
						specs.remove(blankId);
						if(blankId == (nextSpecId-1))
							nextSpecId--;		
					}
										 
					boolean removingEmpty = true;
					while(removingEmpty)
					{
						for(int i = 0; i < specs.size(); i++)
						{
							for(SkillSpec ss : specs)
							{
								if(ss.getDescription().replace(" ", "").equals(""))
								{		
									System.out.println("destroyed");
									specs.remove(ss);
									currentSheet.setSkillSpecs(specs);
									break;
								}
							}
						}
						removingEmpty = false;
					}
						
					if(specs.size() > 0)
						nextSpecId = specs.get(specs.size()-1).getId() + 1;
						place2SpecsList.clear();
					for(JFormattedTextField t : place2SpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						place2Panel.remove(t);
						t = null;
					}
					place2SpecsFields.clear();
					
					reflexesPanel.setSize(reflexesPanel.getWidth(), (reflexesPanel.getHeight()-(place2Panel.getHeight()-32)));
					place2Panel.setSize(354, (32));				
				}
			}
		});
		chckbxPlace2.setBackground(new Color(255, 153, 153));
		chckbxPlace2.setBounds(187, 5, 97, 23);
		place2Panel.add(chckbxPlace2);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(329, 11, 69, 23);
		dcrpgFrame.getContentPane().add(btnSave);
		btnSave.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				chckbxAcro.setSelected(false);
				chckbxDodge.setSelected(false);
				chckbxHandToHand.setSelected(false);
				chckbxMeleeWeapons.setSelected(false);
				chckbxStealth.setSelected(false);
				chckbxPlace.setSelected(false);
				chckbxPlace2.setSelected(false);
				
				// Write save code here - should be easy
				dao.update(currentSheet);
				for(SkillSpec ss : currentSheet.getSkillSpecs())
				{
					dao.updateSpec(ss);
				}
				dao.saveAll();
			}
		});
		
		//load initial sheet
				nameField.setText(currentSheet.getName());
				occupationField.setText(currentSheet.getOccupation());
				baseOfOperationsField.setText(currentSheet.getBaseOfOperations());
				genderField.setText(currentSheet.getGender());
				raceField.setText(currentSheet.getRace());
				heightField.setText(currentSheet.getHeight());
				weightField.setText(currentSheet.getWeight());
				eyeColorField.setText(currentSheet.getEyeColor());
				hairColorField.setText(currentSheet.getHairColor());
				heroPointsField.setValue(currentSheet.getHeroPoints());
				villainPointsField.setValue(currentSheet.getVillainPoints());
				powerPointsField.setValue(currentSheet.getPowerPoints());
				skillPointsField.setValue(currentSheet.getSkillPoints());
				availableRenownField.setValue(currentSheet.getAvailableRenown());
				udoField.setText(currentSheet.getUdoDice() + "+" + currentSheet.getUdoBonus());
				bodyPointsField.setText(currentSheet.getBodyPointsCurrent() + "/" + currentSheet.getBodyPointsMax());
				speedField.setValue(currentSheet.getSpeed());
				
				reflexesLevel.setValue(currentSheet.getReflexes());
				acroLevel.setValue(currentSheet.getAcrobatics());
				dodgeLevel.setValue(currentSheet.getDodge());
				handToHandLevel.setValue(currentSheet.getHandToHand());
				meleeWeaponsLevel.setValue(currentSheet.getMeleeWeapons());
				stealthLevel.setValue(currentSheet.getStealth());
				acroTotal.setValue(currentSheet.getAcrobatics() + currentSheet.getReflexes());
				dodgeTotal.setValue(currentSheet.getDodge() + currentSheet.getReflexes());
				handToHandTotal.setValue(currentSheet.getHandToHand() + currentSheet.getReflexes());
				meleeWeaponsTotal.setValue(currentSheet.getMeleeWeapons() + currentSheet.getReflexes());
				stealthTotal.setValue(currentSheet.getStealth() + currentSheet.getReflexes());
				
				
				JButton btnLoad = new JButton("Load");
				btnLoad.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0) 
					{
						for(CharacterSheet s : sheets)
						{
							if(s.getName().equals(nameSearchField.getText()))
							{
									chckbxAcro.setSelected(false);
									chckbxDodge.setSelected(false);
									chckbxHandToHand.setSelected(false);
									chckbxMeleeWeapons.setSelected(false);
									chckbxStealth.setSelected(false);
									chckbxPlace.setSelected(false);
									chckbxPlace2.setSelected(false);

								
									currentSheet = s;
									// load the entire dang sheet
									nameField.setText(currentSheet.getName());
									occupationField.setText(currentSheet.getOccupation());
									baseOfOperationsField.setText(currentSheet.getBaseOfOperations());
									genderField.setText(currentSheet.getGender());
									raceField.setText(currentSheet.getRace());
									heightField.setText(currentSheet.getHeight());
									weightField.setText(currentSheet.getWeight());
									eyeColorField.setText(currentSheet.getEyeColor());
									hairColorField.setText(currentSheet.getHairColor());
									heroPointsField.setText(Integer.toString(currentSheet.getHeroPoints()));
									villainPointsField.setText(Integer.toString(currentSheet.getVillainPoints()));
									availableRenownField.setText(Integer.toString(currentSheet.getAvailableRenown()));
									powerPointsField.setText(Integer.toString(currentSheet.getPowerPoints()));
									skillPointsField.setText(Integer.toString(currentSheet.getSkillPoints()));
									udoField.setText(currentSheet.getUdoDice() + "+" + currentSheet.getUdoBonus());
									bodyPointsField.setText(currentSheet.getBodyPointsCurrent() + "/" + currentSheet.getBodyPointsMax());
									speedField.setValue(currentSheet.getSpeed());
									
									reflexesLevel.setValue(currentSheet.getReflexes());
									acroLevel.setValue(currentSheet.getAcrobatics());
									dodgeLevel.setValue(currentSheet.getDodge());
									handToHandLevel.setValue(currentSheet.getHandToHand());
									meleeWeaponsLevel.setValue(currentSheet.getMeleeWeapons());
									stealthLevel.setValue(currentSheet.getStealth());
									acroTotal.setValue(currentSheet.getAcrobatics() + currentSheet.getReflexes());
									dodgeTotal.setValue(currentSheet.getDodge() + currentSheet.getReflexes());
									handToHandTotal.setValue(currentSheet.getHandToHand() + currentSheet.getReflexes());
									meleeWeaponsTotal.setValue(currentSheet.getMeleeWeapons() + currentSheet.getReflexes());
									stealthTotal.setValue(currentSheet.getStealth() + currentSheet.getReflexes());
																
							}
						}
					}
				});
				
				btnLoad.setBounds(167, 11, 69, 23);
				dcrpgFrame.getContentPane().add(btnLoad);
				
				nameSearchField.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) 
					{
						if(e.getKeyCode() == KeyEvent.VK_ENTER)
						{
							btnLoad.doClick();
						}
					}
				});
				
	}
}
