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
		batman.setAllDemographics("CEO%Batcave%45%Male%Human%Tall%Muscular%Dark%Dark%Bruce Wayne");
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
		udoLabel.setBounds(1063, 6, 90, 20);
		dcrpgFrame.getContentPane().add(udoLabel);
		
		JLabel bodyPointsLabel = new JLabel("<html><body>  Body<br>Points</body></html>");
		bodyPointsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bodyPointsLabel.setBounds(933, 6, 60, 42);
		dcrpgFrame.getContentPane().add(bodyPointsLabel);
		
		JLabel speedLabel = new JLabel("Speed");
		speedLabel.setBounds(1063, 29, 90, 20);
		dcrpgFrame.getContentPane().add(speedLabel);
		
		JPanel demographicsPanel = new JPanel();
		demographicsPanel.setBackground(new Color(204, 255, 255));
		demographicsPanel.setBounds(48, 59, 1008, 70);
		dcrpgFrame.getContentPane().add(demographicsPanel);
		demographicsPanel.setLayout(null);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(1, 1, 35, 20);
		demographicsPanel.add(lblGender);
		
		JLabel lblHeight = new JLabel("Height");
		lblHeight.setBounds(1, 25, 90, 20);
		demographicsPanel.add(lblHeight);
		
		JLabel lblWeight = new JLabel("Weight");
		lblWeight.setBounds(1, 49, 90, 20);
		demographicsPanel.add(lblWeight);
		
		JLabel lblHairColor = new JLabel("Hair Color");
		lblHairColor.setBounds(157, 49, 47, 20);
		demographicsPanel.add(lblHairColor);
		
		genderField = new JFormattedTextField();
		genderField.setBounds(51, 1, 86, 20);
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
		
		heightField = new JFormattedTextField();
		heightField.setBounds(51, 25, 86, 20);
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
		weightField.setBounds(51, 49, 86, 20);
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
		
		JFormattedTextField hairColorField = new JFormattedTextField();
		hairColorField.setBounds(219, 49, 86, 20);
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
	
		udoField = new JFormattedTextField();
		udoField.setHorizontalAlignment(SwingConstants.RIGHT);
		udoField.setBounds(1110, 6, 60, 20);
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
		bodyPointsField.setFont(new Font("Arial", Font.BOLD, 20));
		bodyPointsField.setHorizontalAlignment(SwingConstants.CENTER);
		bodyPointsField.setBounds(992, 6, 60, 43);
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

		
		speedField = new JFormattedTextField(nums);
		speedField.setHorizontalAlignment(SwingConstants.RIGHT);
		speedField.setBounds(1110, 29, 60, 20);
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
		
		Panel coordinationPanel = new Panel();
		coordinationPanel.setLayout(null);
		coordinationPanel.setBackground(new Color(255, 133, 0));
		coordinationPanel.setBounds(429, 149, 354, 274);
		dcrpgFrame.getContentPane().add(coordinationPanel);
		
		Label coordinationLabel = new Label("Coordination");
		coordinationLabel.setFont(new Font("Verdana", Font.BOLD, 22));
		coordinationLabel.setBounds(110, 10, 135, 30);
		coordinationPanel.add(coordinationLabel);
		
		JFormattedTextField coordinationLevel = new JFormattedTextField(nums);
		coordinationLevel.setHorizontalAlignment(SwingConstants.CENTER);
		coordinationLevel.setFont(new Font("Arial", Font.BOLD, 22));
		coordinationLevel.setColumns(2);
		coordinationLevel.setBounds(294, 10, 50, 35);
		coordinationPanel.add(coordinationLevel);
		
		Panel catchPanel = new Panel();
		catchPanel.setLayout(null);
		catchPanel.setBackground(new Color(255, 201, 131));
		catchPanel.setBounds(0, 50, 354, 32);
		coordinationPanel.add(catchPanel);
		
		Label catchLabel = new Label("Catch");
		catchLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		catchLabel.setBounds(35, 5, 120, 22);
		catchPanel.add(catchLabel);
		
		JFormattedTextField catchTotal = new JFormattedTextField();
		catchTotal.setHorizontalAlignment(SwingConstants.CENTER);
		catchTotal.setFont(new Font("Arial", Font.BOLD, 18));
		catchTotal.setEditable(false);
		catchTotal.setColumns(2);
		catchTotal.setBounds(294, 5, 50, 22);
		catchPanel.add(catchTotal);
		
		JFormattedTextField catchLevel = new JFormattedTextField(nums);
		catchLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(catchLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(catchLevel.getText());
					int j = Integer.parseInt(coordinationLevel.getText());
					currentSheet.setCatching(i);
					catchTotal.setValue(i+j);	
				}
			}
		});
		catchLevel.setHorizontalAlignment(SwingConstants.CENTER);
		catchLevel.setFont(new Font("Arial", Font.BOLD, 14));
		catchLevel.setColumns(2);
		catchLevel.setBounds(5, 5, 24, 22);
		catchPanel.add(catchLevel);
		
		Panel climbingPanel = new Panel();
		climbingPanel.setLayout(null);
		climbingPanel.setBackground(new Color(255, 173, 84));
		climbingPanel.setBounds(0, 82, 354, 32);
		coordinationPanel.add(climbingPanel);
		
		Label climbingLabel = new Label("Climbing");
		climbingLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		climbingLabel.setBounds(35, 5, 120, 22);
		climbingPanel.add(climbingLabel);
		
		JFormattedTextField climbingTotal = new JFormattedTextField();
		climbingTotal.setHorizontalAlignment(SwingConstants.CENTER);
		climbingTotal.setFont(new Font("Arial", Font.BOLD, 18));
		climbingTotal.setEditable(false);
		climbingTotal.setColumns(2);
		climbingTotal.setBounds(294, 5, 50, 22);
		climbingPanel.add(climbingTotal);
		
		JFormattedTextField climbingLevel = new JFormattedTextField(nums);
		climbingLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(climbingLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(climbingLevel.getText());
					int j = Integer.parseInt(coordinationLevel.getText());
					currentSheet.setClimb(i);
					climbingTotal.setValue(i+j);	
				}
			}
		});
		climbingLevel.setHorizontalAlignment(SwingConstants.CENTER);
		climbingLevel.setFont(new Font("Arial", Font.BOLD, 14));
		climbingLevel.setColumns(2);
		climbingLevel.setBounds(5, 7, 24, 20);
		climbingPanel.add(climbingLevel);
		
		Panel drivingPanel = new Panel();
		drivingPanel.setLayout(null);
		drivingPanel.setBackground(new Color(255, 201, 131));
		drivingPanel.setBounds(0, 114, 354, 32);
		coordinationPanel.add(drivingPanel);
		
		Label drivingLabel = new Label("Driving");
		drivingLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		drivingLabel.setBounds(35, 5, 120, 22);
		drivingPanel.add(drivingLabel);
		
		JFormattedTextField drivingTotal = new JFormattedTextField();
		drivingTotal.setHorizontalAlignment(SwingConstants.CENTER);
		drivingTotal.setFont(new Font("Arial", Font.BOLD, 18));
		drivingTotal.setEditable(false);
		drivingTotal.setColumns(2);
		drivingTotal.setBounds(294, 5, 50, 22);
		drivingPanel.add(drivingTotal);
		
		JFormattedTextField drivingLevel = new JFormattedTextField(nums);
		drivingLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(drivingLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(drivingLevel.getText());
					int j = Integer.parseInt(coordinationLevel.getText());
					currentSheet.setDrive(i);
					drivingTotal.setValue(i+j);	
				}
			}
		});
		drivingLevel.setHorizontalAlignment(SwingConstants.CENTER);
		drivingLevel.setFont(new Font("Arial", Font.BOLD, 14));
		drivingLevel.setColumns(2);
		drivingLevel.setBounds(5, 5, 24, 22);
		drivingPanel.add(drivingLevel);
		
		Panel marksmanshipPanel = new Panel();
		marksmanshipPanel.setLayout(null);
		marksmanshipPanel.setBackground(new Color(255, 173, 84));
		marksmanshipPanel.setBounds(0, 146, 354, 32);
		coordinationPanel.add(marksmanshipPanel);
		
		Label marksmanshipLabel = new Label("Marksmanship");
		marksmanshipLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		marksmanshipLabel.setBounds(35, 5, 120, 22);
		marksmanshipPanel.add(marksmanshipLabel);
		
		JFormattedTextField marksmanshipTotal = new JFormattedTextField();
		marksmanshipTotal.setHorizontalAlignment(SwingConstants.CENTER);
		marksmanshipTotal.setFont(new Font("Arial", Font.BOLD, 18));
		marksmanshipTotal.setEditable(false);
		marksmanshipTotal.setColumns(2);
		marksmanshipTotal.setBounds(294, 5, 50, 22);
		marksmanshipPanel.add(marksmanshipTotal);
		
		JFormattedTextField marksmanshipLevel = new JFormattedTextField(nums);
		marksmanshipLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(marksmanshipLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(marksmanshipLevel.getText());
					int j = Integer.parseInt(coordinationLevel.getText());
					currentSheet.setMarksmanship(i);
					marksmanshipTotal.setValue(i+j);	
				}
			}
		});
		marksmanshipLevel.setHorizontalAlignment(SwingConstants.CENTER);
		marksmanshipLevel.setFont(new Font("Arial", Font.BOLD, 14));
		marksmanshipLevel.setColumns(2);
		marksmanshipLevel.setBounds(5, 7, 24, 20);
		marksmanshipPanel.add(marksmanshipLevel);
		
		Panel thieveryPanel = new Panel();
		thieveryPanel.setLayout(null);
		thieveryPanel.setBackground(new Color(255, 201, 131));
		thieveryPanel.setBounds(0, 178, 354, 32);
		coordinationPanel.add(thieveryPanel);
		
		Label thieveryLabel = new Label("Thievery");
		thieveryLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		thieveryLabel.setBounds(35, 5, 120, 22);
		thieveryPanel.add(thieveryLabel);
		
		JFormattedTextField thieveryTotal = new JFormattedTextField();
		thieveryTotal.setHorizontalAlignment(SwingConstants.CENTER);
		thieveryTotal.setFont(new Font("Arial", Font.BOLD, 18));
		thieveryTotal.setEditable(false);
		thieveryTotal.setColumns(2);
		thieveryTotal.setBounds(294, 5, 50, 22);
		thieveryPanel.add(thieveryTotal);
		
		JFormattedTextField thieveryLevel = new JFormattedTextField(nums);
		thieveryLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(thieveryLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(thieveryLevel.getText());
					int j = Integer.parseInt(coordinationLevel.getText());
					currentSheet.setThievery(i);
					thieveryTotal.setValue(i+j);	
				}
			}
		});
		thieveryLevel.setHorizontalAlignment(SwingConstants.CENTER);
		thieveryLevel.setFont(new Font("Arial", Font.BOLD, 14));
		thieveryLevel.setColumns(2);
		thieveryLevel.setBounds(5, 5, 24, 22);
		thieveryPanel.add(thieveryLevel);
		
		Panel thrownWeaponsPanel = new Panel();
		thrownWeaponsPanel.setLayout(null);
		thrownWeaponsPanel.setBackground(new Color(255, 173, 84));
		thrownWeaponsPanel.setBounds(0, 210, 354, 32);
		coordinationPanel.add(thrownWeaponsPanel);
		
		Label thrownWeaponsLabel = new Label("Thrown Weapons");
		thrownWeaponsLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		thrownWeaponsLabel.setBounds(35, 5, 120, 22);
		thrownWeaponsPanel.add(thrownWeaponsLabel);
		
		JFormattedTextField thrownWeaponsTotal = new JFormattedTextField();
		thrownWeaponsTotal.setHorizontalAlignment(SwingConstants.CENTER);
		thrownWeaponsTotal.setFont(new Font("Arial", Font.BOLD, 18));
		thrownWeaponsTotal.setEditable(false);
		thrownWeaponsTotal.setColumns(2);
		thrownWeaponsTotal.setBounds(294, 5, 50, 22);
		thrownWeaponsPanel.add(thrownWeaponsTotal);
		
		JFormattedTextField thrownWeaponsLevel = new JFormattedTextField(nums);
		thrownWeaponsLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(thrownWeaponsLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(thrownWeaponsLevel.getText());
					int j = Integer.parseInt(coordinationLevel.getText());
					currentSheet.setThrownWeapons(i);
					thrownWeaponsTotal.setValue(i+j);	
				}
			}
		});
		thrownWeaponsLevel.setHorizontalAlignment(SwingConstants.CENTER);
		thrownWeaponsLevel.setFont(new Font("Arial", Font.BOLD, 14));
		thrownWeaponsLevel.setColumns(2);
		thrownWeaponsLevel.setBounds(5, 7, 24, 20);
		thrownWeaponsPanel.add(thrownWeaponsLevel);
		
		coordinationLevel.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) {
				if(coordinationLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(coordinationLevel.getText());
					int j = Integer.parseInt(catchLevel.getText());
					int k = Integer.parseInt(climbingLevel.getText());
					int l = Integer.parseInt(drivingLevel.getText());
					int m = Integer.parseInt(marksmanshipLevel.getText());
					int n = Integer.parseInt(thieveryLevel.getText());
					int o = Integer.parseInt(thrownWeaponsLevel.getText());

					currentSheet.setCoordination(i);
					catchTotal.setValue(i+j);
					climbingTotal.setValue(i+k);
					drivingTotal.setValue(i+l);	
					marksmanshipTotal.setValue(i+m);
					thieveryTotal.setValue(i+n);
					thrownWeaponsTotal.setValue(i+o);
				}
			}
		});
		
		Panel physiquePanel = new Panel();
		physiquePanel.setLayout(null);
		physiquePanel.setBackground(new Color(255, 255, 0));
		physiquePanel.setBounds(816, 149, 354, 274);
		dcrpgFrame.getContentPane().add(physiquePanel);
		
		Label physiqueLabel = new Label("Physique");
		physiqueLabel.setFont(new Font("Verdana", Font.BOLD, 22));
		physiqueLabel.setBounds(110, 10, 135, 30);
		physiquePanel.add(physiqueLabel);
		
		JFormattedTextField physiqueLevel = new JFormattedTextField(nums);
		physiqueLevel.setHorizontalAlignment(SwingConstants.CENTER);
		physiqueLevel.setFont(new Font("Arial", Font.BOLD, 22));
		physiqueLevel.setColumns(2);
		physiqueLevel.setBounds(294, 10, 50, 35);
		physiquePanel.add(physiqueLevel);
		
		Panel athleticsPanel = new Panel();
		athleticsPanel.setLayout(null);
		athleticsPanel.setBackground(new Color(240, 230, 140));
		athleticsPanel.setBounds(0, 50, 354, 32);
		physiquePanel.add(athleticsPanel);
		
		Label athleticsLabel = new Label("Athletics");
		athleticsLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		athleticsLabel.setBounds(35, 5, 120, 22);
		athleticsPanel.add(athleticsLabel);
		
		JFormattedTextField athleticsTotal = new JFormattedTextField();
		athleticsTotal.setHorizontalAlignment(SwingConstants.CENTER);
		athleticsTotal.setFont(new Font("Arial", Font.BOLD, 18));
		athleticsTotal.setEditable(false);
		athleticsTotal.setColumns(2);
		athleticsTotal.setBounds(294, 5, 50, 22);
		athleticsPanel.add(athleticsTotal);
		
		JFormattedTextField athleticsLevel = new JFormattedTextField(nums);
		athleticsLevel.setHorizontalAlignment(SwingConstants.CENTER);
		athleticsLevel.setFont(new Font("Arial", Font.BOLD, 14));
		athleticsLevel.setColumns(2);
		athleticsLevel.setBounds(5, 5, 24, 22);
		athleticsPanel.add(athleticsLevel);
		athleticsLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(athleticsLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(athleticsLevel.getText());
					int j = Integer.parseInt(physiqueLevel.getText());
					currentSheet.setAthletics(i);
					athleticsTotal.setValue(i+j);	
				}
			}
		});
		
		Panel leapPanel = new Panel();
		leapPanel.setLayout(null);
		leapPanel.setBackground(new Color(255, 255, 153));
		leapPanel.setBounds(0, 82, 354, 32);
		physiquePanel.add(leapPanel);
		
		Label leapLabel = new Label("Leap");
		leapLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		leapLabel.setBounds(35, 5, 120, 22);
		leapPanel.add(leapLabel);
		
		JFormattedTextField leapTotal = new JFormattedTextField();
		leapTotal.setHorizontalAlignment(SwingConstants.CENTER);
		leapTotal.setFont(new Font("Arial", Font.BOLD, 18));
		leapTotal.setEditable(false);
		leapTotal.setColumns(2);
		leapTotal.setBounds(294, 5, 50, 22);
		leapPanel.add(leapTotal);
		
		JFormattedTextField leapLevel = new JFormattedTextField(nums);
		leapLevel.setHorizontalAlignment(SwingConstants.CENTER);
		leapLevel.setFont(new Font("Arial", Font.BOLD, 14));
		leapLevel.setColumns(2);
		leapLevel.setBounds(5, 7, 24, 20);
		leapPanel.add(leapLevel);
		leapLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(leapLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(leapLevel.getText());
					int j = Integer.parseInt(physiqueLevel.getText());
					currentSheet.setLeap(i);
					leapTotal.setValue(i+j);	
				}
			}
		});
		
		Panel liftingPanel = new Panel();
		liftingPanel.setLayout(null);
		liftingPanel.setBackground(new Color(240, 230, 140));
		liftingPanel.setBounds(0, 114, 354, 32);
		physiquePanel.add(liftingPanel);
		
		Label liftingLabel = new Label("Lifting");
		liftingLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		liftingLabel.setBounds(35, 5, 120, 22);
		liftingPanel.add(liftingLabel);
		
		JFormattedTextField liftingTotal = new JFormattedTextField();
		liftingTotal.setHorizontalAlignment(SwingConstants.CENTER);
		liftingTotal.setFont(new Font("Arial", Font.BOLD, 18));
		liftingTotal.setEditable(false);
		liftingTotal.setColumns(2);
		liftingTotal.setBounds(294, 5, 50, 22);
		liftingPanel.add(liftingTotal);
		
		JFormattedTextField liftingLevel = new JFormattedTextField(nums);
		liftingLevel.setHorizontalAlignment(SwingConstants.CENTER);
		liftingLevel.setFont(new Font("Arial", Font.BOLD, 14));
		liftingLevel.setColumns(2);
		liftingLevel.setBounds(5, 5, 24, 22);
		liftingPanel.add(liftingLevel);
		liftingLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(liftingLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(liftingLevel.getText());
					int j = Integer.parseInt(physiqueLevel.getText());
					currentSheet.setLifting(i);
					liftingTotal.setValue(i+j);	
				}
			}
		});
		
		Panel resistancePanel = new Panel();
		resistancePanel.setLayout(null);
		resistancePanel.setBackground(new Color(255, 255, 153));
		resistancePanel.setBounds(0, 146, 354, 32);
		physiquePanel.add(resistancePanel);
		
		Label resistanceLabel = new Label("Resistance");
		resistanceLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		resistanceLabel.setBounds(35, 5, 120, 22);
		resistancePanel.add(resistanceLabel);
		
		JFormattedTextField resistanceTotal = new JFormattedTextField();
		resistanceTotal.setHorizontalAlignment(SwingConstants.CENTER);
		resistanceTotal.setFont(new Font("Arial", Font.BOLD, 18));
		resistanceTotal.setEditable(false);
		resistanceTotal.setColumns(2);
		resistanceTotal.setBounds(294, 5, 50, 22);
		resistancePanel.add(resistanceTotal);
		
		JFormattedTextField resistanceLevel = new JFormattedTextField(nums);
		resistanceLevel.setHorizontalAlignment(SwingConstants.CENTER);
		resistanceLevel.setFont(new Font("Arial", Font.BOLD, 14));
		resistanceLevel.setColumns(2);
		resistanceLevel.setBounds(5, 7, 24, 20);
		resistancePanel.add(resistanceLevel);
		resistanceLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(resistanceLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(resistanceLevel.getText());
					int j = Integer.parseInt(physiqueLevel.getText());
					currentSheet.setResistance(i);
					resistanceTotal.setValue(i+j);	
				}
			}
		});
		
		Panel runningPanel = new Panel();
		runningPanel.setLayout(null);
		runningPanel.setBackground(new Color(240, 230, 140));
		runningPanel.setBounds(0, 178, 354, 32);
		physiquePanel.add(runningPanel);
		
		Label runningLabel = new Label("Running");
		runningLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		runningLabel.setBounds(35, 5, 120, 22);
		runningPanel.add(runningLabel);
		
		JFormattedTextField runningTotal = new JFormattedTextField();
		runningTotal.setHorizontalAlignment(SwingConstants.CENTER);
		runningTotal.setFont(new Font("Arial", Font.BOLD, 18));
		runningTotal.setEditable(false);
		runningTotal.setColumns(2);
		runningTotal.setBounds(294, 5, 50, 22);
		runningPanel.add(runningTotal);
		
		JFormattedTextField runningLevel = new JFormattedTextField(nums);
		runningLevel.setHorizontalAlignment(SwingConstants.CENTER);
		runningLevel.setFont(new Font("Arial", Font.BOLD, 14));
		runningLevel.setColumns(2);
		runningLevel.setBounds(5, 5, 24, 22);
		runningPanel.add(runningLevel);
		runningLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(runningLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(runningLevel.getText());
					int j = Integer.parseInt(physiqueLevel.getText());
					currentSheet.setRunning(i);
					runningTotal.setValue(i+j);	
				}
			}
		});
		
		Panel swimmingPanel = new Panel();
		swimmingPanel.setLayout(null);
		swimmingPanel.setBackground(new Color(255, 255, 153));
		swimmingPanel.setBounds(0, 210, 354, 32);
		physiquePanel.add(swimmingPanel);
		
		Label swimmingLabel = new Label("Swimming");
		swimmingLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		swimmingLabel.setBounds(35, 5, 120, 22);
		swimmingPanel.add(swimmingLabel);
		
		JFormattedTextField swimmingTotal = new JFormattedTextField();
		swimmingTotal.setHorizontalAlignment(SwingConstants.CENTER);
		swimmingTotal.setFont(new Font("Arial", Font.BOLD, 18));
		swimmingTotal.setEditable(false);
		swimmingTotal.setColumns(2);
		swimmingTotal.setBounds(294, 5, 50, 22);
		swimmingPanel.add(swimmingTotal);
		
		JFormattedTextField swimmingLevel = new JFormattedTextField(nums);
		swimmingLevel.setHorizontalAlignment(SwingConstants.CENTER);
		swimmingLevel.setFont(new Font("Arial", Font.BOLD, 14));
		swimmingLevel.setColumns(2);
		swimmingLevel.setBounds(5, 7, 24, 20);
		swimmingPanel.add(swimmingLevel);	
		swimmingLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(swimmingLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(swimmingLevel.getText());
					int j = Integer.parseInt(physiqueLevel.getText());
					currentSheet.setSwimming(i);
					swimmingTotal.setValue(i+j);	
				}
			}
		});
		
		physiqueLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(physiqueLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(physiqueLevel.getText());
					int j = Integer.parseInt(athleticsLevel.getText());
					int k = Integer.parseInt(leapLevel.getText());
					int l = Integer.parseInt(liftingLevel.getText());
					int m = Integer.parseInt(resistanceLevel.getText());
					int n = Integer.parseInt(runningLevel.getText());
					int o = Integer.parseInt(swimmingLevel.getText());

					currentSheet.setPhysique(i);
					athleticsTotal.setValue(i+j);
					leapTotal.setValue(i+k);
					liftingTotal.setValue(i+l);	
					resistanceTotal.setValue(i+m);
					runningTotal.setValue(i+n);
					swimmingTotal.setValue(i+o);
				}
			}
		});
		
		Panel knowledgePanel = new Panel();
		knowledgePanel.setLayout(null);
		knowledgePanel.setBackground(new Color(34, 139, 34));
		knowledgePanel.setBounds(44, 454, 354, 274);
		dcrpgFrame.getContentPane().add(knowledgePanel);
		
		Label knowledgeLabel = new Label("Knowledge");
		knowledgeLabel.setFont(new Font("Verdana", Font.BOLD, 22));
		knowledgeLabel.setBounds(115, 10, 143, 30);
		knowledgePanel.add(knowledgeLabel);
		
		JFormattedTextField knowledgeLevel = new JFormattedTextField(nums);
		knowledgeLevel.setHorizontalAlignment(SwingConstants.CENTER);
		knowledgeLevel.setFont(new Font("Arial", Font.BOLD, 22));
		knowledgeLevel.setColumns(2);
		knowledgeLevel.setBounds(294, 10, 50, 35);
		knowledgePanel.add(knowledgeLevel);
		
		Panel arcaneLorePanel = new Panel();
		arcaneLorePanel.setLayout(null);
		arcaneLorePanel.setBackground(new Color(144, 238, 144));
		arcaneLorePanel.setBounds(0, 50, 354, 32);
		knowledgePanel.add(arcaneLorePanel);
		
		Label arcaneLoreLabel = new Label("Arcane Lore");
		arcaneLoreLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		arcaneLoreLabel.setBounds(35, 5, 120, 22);
		arcaneLorePanel.add(arcaneLoreLabel);
		
		JFormattedTextField arcaneLoreTotal = new JFormattedTextField();
		arcaneLoreTotal.setHorizontalAlignment(SwingConstants.CENTER);
		arcaneLoreTotal.setFont(new Font("Arial", Font.BOLD, 18));
		arcaneLoreTotal.setEditable(false);
		arcaneLoreTotal.setColumns(2);
		arcaneLoreTotal.setBounds(294, 5, 50, 22);
		arcaneLorePanel.add(arcaneLoreTotal);
		
		JFormattedTextField arcaneLoreLevel = new JFormattedTextField(nums);
		arcaneLoreLevel.setHorizontalAlignment(SwingConstants.CENTER);
		arcaneLoreLevel.setFont(new Font("Arial", Font.BOLD, 14));
		arcaneLoreLevel.setColumns(2);
		arcaneLoreLevel.setBounds(5, 5, 24, 22);
		arcaneLorePanel.add(arcaneLoreLevel);
		arcaneLoreLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(arcaneLoreLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(arcaneLoreLevel.getText());
					int j = Integer.parseInt(knowledgeLevel.getText());
					currentSheet.setArcaneLore(i);
					arcaneLoreTotal.setValue(i+j);	
				}
			}
		});
		
		Panel demolitionsPanel = new Panel();
		demolitionsPanel.setLayout(null);
		demolitionsPanel.setBackground(new Color(50, 205, 50));
		demolitionsPanel.setBounds(0, 82, 354, 32);
		knowledgePanel.add(demolitionsPanel);
		
		Label demolitionsLabel = new Label("Demolitions");
		demolitionsLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		demolitionsLabel.setBounds(35, 5, 120, 22);
		demolitionsPanel.add(demolitionsLabel);
		
		JFormattedTextField demolitionsTotal = new JFormattedTextField();
		demolitionsTotal.setHorizontalAlignment(SwingConstants.CENTER);
		demolitionsTotal.setFont(new Font("Arial", Font.BOLD, 18));
		demolitionsTotal.setEditable(false);
		demolitionsTotal.setColumns(2);
		demolitionsTotal.setBounds(294, 5, 50, 22);
		demolitionsPanel.add(demolitionsTotal);
		
		JFormattedTextField demolitionsLevel = new JFormattedTextField(nums);
		demolitionsLevel.setHorizontalAlignment(SwingConstants.CENTER);
		demolitionsLevel.setFont(new Font("Arial", Font.BOLD, 14));
		demolitionsLevel.setColumns(2);
		demolitionsLevel.setBounds(5, 7, 24, 20);
		demolitionsPanel.add(demolitionsLevel);
		demolitionsLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(demolitionsLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(demolitionsLevel.getText());
					int j = Integer.parseInt(knowledgeLevel.getText());
					currentSheet.setDemolitions(i);
					demolitionsTotal.setValue(i+j);	
				}
			}
		});
		
		Panel languagesPanel = new Panel();
		languagesPanel.setLayout(null);
		languagesPanel.setBackground(new Color(144, 238, 144));
		languagesPanel.setBounds(0, 114, 354, 32);
		knowledgePanel.add(languagesPanel);
		
		Label languagesLabel = new Label("Languages");
		languagesLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		languagesLabel.setBounds(35, 5, 120, 22);
		languagesPanel.add(languagesLabel);
		
		JFormattedTextField languagesTotal = new JFormattedTextField();
		languagesTotal.setHorizontalAlignment(SwingConstants.CENTER);
		languagesTotal.setFont(new Font("Arial", Font.BOLD, 18));
		languagesTotal.setEditable(false);
		languagesTotal.setColumns(2);
		languagesTotal.setBounds(294, 5, 50, 22);
		languagesPanel.add(languagesTotal);
		
		JFormattedTextField languagesLevel = new JFormattedTextField(nums);
		languagesLevel.setHorizontalAlignment(SwingConstants.CENTER);
		languagesLevel.setFont(new Font("Arial", Font.BOLD, 14));
		languagesLevel.setColumns(2);
		languagesLevel.setBounds(5, 5, 24, 22);
		languagesPanel.add(languagesLevel);
		languagesLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(languagesLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(languagesLevel.getText());
					int j = Integer.parseInt(knowledgeLevel.getText());
					currentSheet.setLanguages(i);
					languagesTotal.setValue(i+j);	
				}
			}
		});
		
		Panel medicinePanel = new Panel();
		medicinePanel.setLayout(null);
		medicinePanel.setBackground(new Color(50, 205, 50));
		medicinePanel.setBounds(0, 146, 354, 32);
		knowledgePanel.add(medicinePanel);
		
		Label medicineLabel = new Label("Medicine");
		medicineLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		medicineLabel.setBounds(35, 5, 120, 22);
		medicinePanel.add(medicineLabel);
		
		JFormattedTextField medicineTotal = new JFormattedTextField();
		medicineTotal.setHorizontalAlignment(SwingConstants.CENTER);
		medicineTotal.setFont(new Font("Arial", Font.BOLD, 18));
		medicineTotal.setEditable(false);
		medicineTotal.setColumns(2);
		medicineTotal.setBounds(294, 5, 50, 22);
		medicinePanel.add(medicineTotal);
		
		JFormattedTextField medicineLevel = new JFormattedTextField(nums);
		medicineLevel.setHorizontalAlignment(SwingConstants.CENTER);
		medicineLevel.setFont(new Font("Arial", Font.BOLD, 14));
		medicineLevel.setColumns(2);
		medicineLevel.setBounds(5, 7, 24, 20);
		medicinePanel.add(medicineLevel);
		medicineLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(medicineLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(medicineLevel.getText());
					int j = Integer.parseInt(knowledgeLevel.getText());
					currentSheet.setMedicine(i);
					medicineTotal.setValue(i+j);	
				}
			}
		});
		
		Panel scholarPanel = new Panel();
		scholarPanel.setLayout(null);
		scholarPanel.setBackground(new Color(144, 238, 144));
		scholarPanel.setBounds(0, 178, 354, 32);
		knowledgePanel.add(scholarPanel);
		
		Label scholarLabel = new Label("Scholar");
		scholarLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		scholarLabel.setBounds(35, 5, 120, 22);
		scholarPanel.add(scholarLabel);
		
		JFormattedTextField scholarTotal = new JFormattedTextField();
		scholarTotal.setHorizontalAlignment(SwingConstants.CENTER);
		scholarTotal.setFont(new Font("Arial", Font.BOLD, 18));
		scholarTotal.setEditable(false);
		scholarTotal.setColumns(2);
		scholarTotal.setBounds(294, 5, 50, 22);
		scholarPanel.add(scholarTotal);
		
		JFormattedTextField scholarLevel = new JFormattedTextField(nums);
		scholarLevel.setHorizontalAlignment(SwingConstants.CENTER);
		scholarLevel.setFont(new Font("Arial", Font.BOLD, 14));
		scholarLevel.setColumns(2);
		scholarLevel.setBounds(5, 5, 24, 22);
		scholarPanel.add(scholarLevel);
		scholarLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(scholarLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(scholarLevel.getText());
					int j = Integer.parseInt(knowledgeLevel.getText());
					currentSheet.setScholar(i);
					scholarTotal.setValue(i+j);	
				}
			}
		});
		
		Panel sciencePanel = new Panel();
		sciencePanel.setLayout(null);
		sciencePanel.setBackground(new Color(50, 205, 50));
		sciencePanel.setBounds(0, 210, 354, 32);
		knowledgePanel.add(sciencePanel);
		
		Label scienceLabel = new Label("Science");
		scienceLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		scienceLabel.setBounds(35, 5, 120, 22);
		sciencePanel.add(scienceLabel);
		
		JFormattedTextField scienceTotal = new JFormattedTextField();
		scienceTotal.setHorizontalAlignment(SwingConstants.CENTER);
		scienceTotal.setFont(new Font("Arial", Font.BOLD, 18));
		scienceTotal.setEditable(false);
		scienceTotal.setColumns(2);
		scienceTotal.setBounds(294, 5, 50, 22);
		sciencePanel.add(scienceTotal);
		
		JFormattedTextField scienceLevel = new JFormattedTextField(nums);
		scienceLevel.setHorizontalAlignment(SwingConstants.CENTER);
		scienceLevel.setFont(new Font("Arial", Font.BOLD, 14));
		scienceLevel.setColumns(2);
		scienceLevel.setBounds(5, 7, 24, 20);
		sciencePanel.add(scienceLevel);
		scienceLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(scienceLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(scienceLevel.getText());
					int j = Integer.parseInt(knowledgeLevel.getText());
					currentSheet.setScience(i);
					scienceTotal.setValue(i+j);	
				}
			}
		});
		
		Panel securityPanel = new Panel();
		securityPanel.setLayout(null);
		securityPanel.setBackground(new Color(144, 238, 144));
		securityPanel.setBounds(0, 242, 354, 32);
		knowledgePanel.add(securityPanel);
		
		Label securityLabel = new Label("Security");
		securityLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		securityLabel.setBounds(35, 5, 120, 22);
		securityPanel.add(securityLabel);
		
		JFormattedTextField securityTotal = new JFormattedTextField();
		securityTotal.setHorizontalAlignment(SwingConstants.CENTER);
		securityTotal.setFont(new Font("Arial", Font.BOLD, 18));
		securityTotal.setEditable(false);
		securityTotal.setColumns(2);
		securityTotal.setBounds(294, 5, 50, 22);
		securityPanel.add(securityTotal);
		
		JFormattedTextField securityLevel = new JFormattedTextField(nums);
		securityLevel.setHorizontalAlignment(SwingConstants.CENTER);
		securityLevel.setFont(new Font("Arial", Font.BOLD, 14));
		securityLevel.setColumns(2);
		securityLevel.setBounds(5, 7, 24, 20);
		securityPanel.add(securityLevel);	
		securityLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(securityLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(securityLevel.getText());
					int j = Integer.parseInt(knowledgeLevel.getText());
					currentSheet.setSecurity(i);
					securityTotal.setValue(i+j);	
				}
			}
		});
		
		knowledgeLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(knowledgeLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(knowledgeLevel.getText());
					int j = Integer.parseInt(arcaneLoreLevel.getText());
					int k = Integer.parseInt(demolitionsLevel.getText());
					int l = Integer.parseInt(languagesLevel.getText());
					int m = Integer.parseInt(medicineLevel.getText());
					int n = Integer.parseInt(scholarLevel.getText());
					int o = Integer.parseInt(scienceLevel.getText());
					int p = Integer.parseInt(securityLevel.getText());


					currentSheet.setKnowledge(i);
					arcaneLoreTotal.setValue(i+j);
					demolitionsTotal.setValue(i+k);
					languagesTotal.setValue(i+l);	
					medicineTotal.setValue(i+m);
					scholarTotal.setValue(i+n);
					scienceTotal.setValue(i+o);
					securityTotal.setValue(i+p);

				}
			}
		});
		
		Panel perceptionPanel = new Panel();
		perceptionPanel.setLayout(null);
		perceptionPanel.setBackground(new Color(25, 128, 255));
		perceptionPanel.setBounds(429, 454, 354, 274);
		dcrpgFrame.getContentPane().add(perceptionPanel);
		
		Label perceptionLabel = new Label("Perception");
		perceptionLabel.setFont(new Font("Verdana", Font.BOLD, 22));
		perceptionLabel.setBounds(110, 10, 135, 30);
		perceptionPanel.add(perceptionLabel);
		
		JFormattedTextField perceptionLevel = new JFormattedTextField(nums);
		perceptionLevel.setHorizontalAlignment(SwingConstants.CENTER);
		perceptionLevel.setFont(new Font("Arial", Font.BOLD, 22));
		perceptionLevel.setColumns(2);
		perceptionLevel.setBounds(294, 10, 50, 35);
		perceptionPanel.add(perceptionLevel);
		
		Panel artistPanel = new Panel();
		artistPanel.setLayout(null);
		artistPanel.setBackground(new Color(135, 206, 250));
		artistPanel.setBounds(0, 50, 354, 32);
		perceptionPanel.add(artistPanel);
		
		Label artistLabel = new Label("Artist");
		artistLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		artistLabel.setBounds(35, 5, 120, 22);
		artistPanel.add(artistLabel);
		
		JFormattedTextField artistTotal = new JFormattedTextField();
		artistTotal.setHorizontalAlignment(SwingConstants.CENTER);
		artistTotal.setFont(new Font("Arial", Font.BOLD, 18));
		artistTotal.setEditable(false);
		artistTotal.setColumns(2);
		artistTotal.setBounds(294, 5, 50, 22);
		artistPanel.add(artistTotal);
		
		JFormattedTextField artistLevel = new JFormattedTextField(nums);
		artistLevel.setHorizontalAlignment(SwingConstants.CENTER);
		artistLevel.setFont(new Font("Arial", Font.BOLD, 14));
		artistLevel.setColumns(2);
		artistLevel.setBounds(5, 5, 24, 22);
		artistPanel.add(artistLevel);
		artistLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(artistLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(artistLevel.getText());
					int j = Integer.parseInt(perceptionLevel.getText());
					currentSheet.setArtist(i);
					artistTotal.setValue(i+j);	
				}
			}
		});
		
		Panel engineeringPanel = new Panel();
		engineeringPanel.setLayout(null);
		engineeringPanel.setBackground(new Color(0, 191, 255));
		engineeringPanel.setBounds(0, 82, 354, 32);
		perceptionPanel.add(engineeringPanel);
		
		Label engineeringLabel = new Label("Engineering");
		engineeringLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		engineeringLabel.setBounds(35, 5, 120, 22);
		engineeringPanel.add(engineeringLabel);
		
		JFormattedTextField engineeringTotal = new JFormattedTextField();
		engineeringTotal.setHorizontalAlignment(SwingConstants.CENTER);
		engineeringTotal.setFont(new Font("Arial", Font.BOLD, 18));
		engineeringTotal.setEditable(false);
		engineeringTotal.setColumns(2);
		engineeringTotal.setBounds(294, 5, 50, 22);
		engineeringPanel.add(engineeringTotal);
		
		JFormattedTextField engineeringLevel = new JFormattedTextField(nums);
		engineeringLevel.setHorizontalAlignment(SwingConstants.CENTER);
		engineeringLevel.setFont(new Font("Arial", Font.BOLD, 14));
		engineeringLevel.setColumns(2);
		engineeringLevel.setBounds(5, 7, 24, 20);
		engineeringPanel.add(engineeringLevel);
		engineeringLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(engineeringLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(engineeringLevel.getText());
					int j = Integer.parseInt(perceptionLevel.getText());
					currentSheet.setEngineering(i);
					engineeringTotal.setValue(i+j);	
				}
			}
		});
		
		Panel searchPanel = new Panel();
		searchPanel.setLayout(null);
		searchPanel.setBackground(new Color(135, 206, 235));
		searchPanel.setBounds(0, 114, 354, 32);
		perceptionPanel.add(searchPanel);
		
		Label searchLabel = new Label("Search");
		searchLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		searchLabel.setBounds(35, 5, 120, 22);
		searchPanel.add(searchLabel);
		
		JFormattedTextField searchTotal = new JFormattedTextField();
		searchTotal.setHorizontalAlignment(SwingConstants.CENTER);
		searchTotal.setFont(new Font("Arial", Font.BOLD, 18));
		searchTotal.setEditable(false);
		searchTotal.setColumns(2);
		searchTotal.setBounds(294, 5, 50, 22);
		searchPanel.add(searchTotal);
		
		JFormattedTextField searchLevel = new JFormattedTextField(nums);
		searchLevel.setHorizontalAlignment(SwingConstants.CENTER);
		searchLevel.setFont(new Font("Arial", Font.BOLD, 14));
		searchLevel.setColumns(2);
		searchLevel.setBounds(5, 5, 24, 22);
		searchPanel.add(searchLevel);
		searchLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(searchLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(searchLevel.getText());
					int j = Integer.parseInt(perceptionLevel.getText());
					currentSheet.setSearch(i);
					searchTotal.setValue(i+j);	
				}
			}
		});
		
		Panel streetwisePanel = new Panel();
		streetwisePanel.setLayout(null);
		streetwisePanel.setBackground(new Color(0, 191, 255));
		streetwisePanel.setBounds(0, 146, 354, 32);
		perceptionPanel.add(streetwisePanel);
		
		Label streetwiseLabel = new Label("Streetwise");
		streetwiseLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		streetwiseLabel.setBounds(35, 5, 120, 22);
		streetwisePanel.add(streetwiseLabel);
		
		JFormattedTextField streetwiseTotal = new JFormattedTextField();
		streetwiseTotal.setHorizontalAlignment(SwingConstants.CENTER);
		streetwiseTotal.setFont(new Font("Arial", Font.BOLD, 18));
		streetwiseTotal.setEditable(false);
		streetwiseTotal.setColumns(2);
		streetwiseTotal.setBounds(294, 5, 50, 22);
		streetwisePanel.add(streetwiseTotal);
		
		JFormattedTextField streetwiseLevel = new JFormattedTextField(nums);
		streetwiseLevel.setHorizontalAlignment(SwingConstants.CENTER);
		streetwiseLevel.setFont(new Font("Arial", Font.BOLD, 14));
		streetwiseLevel.setColumns(2);
		streetwiseLevel.setBounds(5, 7, 24, 20);
		streetwisePanel.add(streetwiseLevel);
		streetwiseLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(streetwiseLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(streetwiseLevel.getText());
					int j = Integer.parseInt(perceptionLevel.getText());
					currentSheet.setStreetwise(i);
					streetwiseTotal.setValue(i+j);	
				}
			}
		});
		
		Panel surveillancePanel = new Panel();
		surveillancePanel.setLayout(null);
		surveillancePanel.setBackground(new Color(135, 206, 235));
		surveillancePanel.setBounds(0, 178, 354, 32);
		perceptionPanel.add(surveillancePanel);
		
		Label surveillanceLabel = new Label("Surveillance");
		surveillanceLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		surveillanceLabel.setBounds(35, 5, 120, 22);
		surveillancePanel.add(surveillanceLabel);
		
		JFormattedTextField surveillanceTotal = new JFormattedTextField();
		surveillanceTotal.setHorizontalAlignment(SwingConstants.CENTER);
		surveillanceTotal.setFont(new Font("Arial", Font.BOLD, 18));
		surveillanceTotal.setEditable(false);
		surveillanceTotal.setColumns(2);
		surveillanceTotal.setBounds(294, 5, 50, 22);
		surveillancePanel.add(surveillanceTotal);
		
		JFormattedTextField surveillanceLevel = new JFormattedTextField(nums);
		surveillanceLevel.setHorizontalAlignment(SwingConstants.CENTER);
		surveillanceLevel.setFont(new Font("Arial", Font.BOLD, 14));
		surveillanceLevel.setColumns(2);
		surveillanceLevel.setBounds(5, 5, 24, 22);
		surveillancePanel.add(surveillanceLevel);
		surveillanceLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(surveillanceLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(surveillanceLevel.getText());
					int j = Integer.parseInt(perceptionLevel.getText());
					currentSheet.setSurveillance(i);
					surveillanceTotal.setValue(i+j);	
				}
			}
		});
		
		Panel survivalPanel = new Panel();
		survivalPanel.setLayout(null);
		survivalPanel.setBackground(new Color(0, 191, 255));
		survivalPanel.setBounds(0, 210, 354, 32);
		perceptionPanel.add(survivalPanel);
		
		Label survivalLabel = new Label("Survival");
		survivalLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		survivalLabel.setBounds(35, 5, 120, 22);
		survivalPanel.add(survivalLabel);
		
		JFormattedTextField survivalTotal = new JFormattedTextField();
		survivalTotal.setHorizontalAlignment(SwingConstants.CENTER);
		survivalTotal.setFont(new Font("Arial", Font.BOLD, 18));
		survivalTotal.setEditable(false);
		survivalTotal.setColumns(2);
		survivalTotal.setBounds(294, 5, 50, 22);
		survivalPanel.add(survivalTotal);
		
		JFormattedTextField survivalLevel = new JFormattedTextField(nums);
		survivalLevel.setHorizontalAlignment(SwingConstants.CENTER);
		survivalLevel.setFont(new Font("Arial", Font.BOLD, 14));
		survivalLevel.setColumns(2);
		survivalLevel.setBounds(5, 7, 24, 20);
		survivalPanel.add(survivalLevel);	
		survivalLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(survivalLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(survivalLevel.getText());
					int j = Integer.parseInt(perceptionLevel.getText());
					currentSheet.setSurvival(i);
					survivalTotal.setValue(i+j);	
				}
			}
		});
		
		perceptionLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(perceptionLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(perceptionLevel.getText());
					int j = Integer.parseInt(artistLevel.getText());
					int k = Integer.parseInt(engineeringLevel.getText());
					int l = Integer.parseInt(searchLevel.getText());
					int m = Integer.parseInt(streetwiseLevel.getText());
					int n = Integer.parseInt(surveillanceLevel.getText());
					int o = Integer.parseInt(survivalLevel.getText());


					currentSheet.setPerception(i);
					artistTotal.setValue(i+j);
					engineeringTotal.setValue(i+k);
					searchTotal.setValue(i+l);	
					streetwiseTotal.setValue(i+m);
					surveillanceTotal.setValue(i+n);
					survivalTotal.setValue(i+o);

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
		
		// CatchSpecs
		List<JFormattedTextField> catchSpecsFields = new ArrayList<JFormattedTextField>();
		JCheckBox chckbxCatch = new JCheckBox("Show specs");
		chckbxCatch.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> catchSpecsList = currentSheet.assignSkillSpecs("Catch");
				if(chckbxCatch.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "Catch", "");
					catchSpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;
					int extra = (catchSpecsList.size())*28;
					
					coordinationPanel.setSize(coordinationPanel.getWidth(), (coordinationPanel.getHeight()+extra));
					catchPanel.setSize(354, (32+extra));
					climbingPanel.setLocation(0, climbingPanel.getY()+extra);
					drivingPanel.setLocation(0, drivingPanel.getY()+extra);
					marksmanshipPanel.setLocation(0, marksmanshipPanel.getY()+extra);
					thieveryPanel.setLocation(0, thieveryPanel.getY()+extra);
					thrownWeaponsPanel.setLocation(0, thrownWeaponsPanel.getY()+extra);
					for(int i = 0; i < catchSpecsList.size(); i++)
					{
						int j = i;
						catchSpecsFields.add(new JFormattedTextField());
						JFormattedTextField catchSpecs = catchSpecsFields.get(i);
						catchSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						catchSpecs.setBackground(new Color(255, 235, 209));
						catchSpecs.setBounds(5, 33+(i*28), 279, 22);
						catchPanel.add(catchSpecs);
						catchSpecs.setText(catchSpecsList.get(i).getDescription());
						catchSpecs.requestFocus();	

						catchSpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = catchSpecsList.get(j);
									editing.setDescription(catchSpecs.getText());
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
										chckbxCatch.setSelected(false);
										chckbxCatch.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxCatch.isSelected())
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

					catchSpecsList.clear();
					for(JFormattedTextField t : catchSpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						catchPanel.remove(t);
						t = null;
					}
					catchSpecsFields.clear();
							
					coordinationPanel.setSize(coordinationPanel.getWidth(), (coordinationPanel.getHeight()-(catchPanel.getHeight()-32)));
					catchPanel.setSize(354, (32));
					climbingPanel.setLocation(0, catchPanel.getY()+32);
					drivingPanel.setLocation(0, climbingPanel.getY()+climbingPanel.getHeight());
					marksmanshipPanel.setLocation(0, drivingPanel.getY()+drivingPanel.getHeight());
					thieveryPanel.setLocation(0, marksmanshipPanel.getY()+marksmanshipPanel.getHeight());
					thrownWeaponsPanel.setLocation(0, thieveryPanel.getY()+thieveryPanel.getHeight());
				}
			}
		});
		chckbxCatch.setBackground(new Color(255, 201, 131));
		chckbxCatch.setBounds(187, 5, 97, 23);
		catchPanel.add(chckbxCatch);
		
		// ClimbingSpecs
		List<JFormattedTextField> climbingSpecsFields = new ArrayList<JFormattedTextField>();
		JCheckBox chckbxClimbing = new JCheckBox("Show specs");
		chckbxClimbing.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> climbingSpecsList = currentSheet.assignSkillSpecs("Climbing");
				if(chckbxClimbing.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "Climbing", "");
					climbingSpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;
					int extra = (climbingSpecsList.size())*28;
					
					coordinationPanel.setSize(coordinationPanel.getWidth(), (coordinationPanel.getHeight()+extra));
					climbingPanel.setSize(354, (32+extra));
					drivingPanel.setLocation(0, drivingPanel.getY()+extra);
					marksmanshipPanel.setLocation(0, marksmanshipPanel.getY()+extra);
					thieveryPanel.setLocation(0, thieveryPanel.getY()+extra);
					thrownWeaponsPanel.setLocation(0, thrownWeaponsPanel.getY()+extra);
					for(int i = 0; i < climbingSpecsList.size(); i++)
					{
						int j = i;
						climbingSpecsFields.add(new JFormattedTextField());
						JFormattedTextField climbingSpecs = climbingSpecsFields.get(i);
						climbingSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						climbingSpecs.setBackground(new Color(255, 235, 209));
						climbingSpecs.setBounds(5, 33+(i*28), 279, 22);
						climbingPanel.add(climbingSpecs);
						climbingSpecs.setText(climbingSpecsList.get(i).getDescription());
						climbingSpecs.requestFocus();	

						climbingSpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = climbingSpecsList.get(j);
									editing.setDescription(climbingSpecs.getText());
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
										chckbxClimbing.setSelected(false);
										chckbxClimbing.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxClimbing.isSelected())
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

					climbingSpecsList.clear();
					for(JFormattedTextField t : climbingSpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						climbingPanel.remove(t);
						t = null;
					}
					climbingSpecsFields.clear();
							
					coordinationPanel.setSize(coordinationPanel.getWidth(), (coordinationPanel.getHeight()-(climbingPanel.getHeight()-32)));
					climbingPanel.setSize(354, (32));
					drivingPanel.setLocation(0, climbingPanel.getY()+climbingPanel.getHeight());
					marksmanshipPanel.setLocation(0, drivingPanel.getY()+drivingPanel.getHeight());
					thieveryPanel.setLocation(0, marksmanshipPanel.getY()+marksmanshipPanel.getHeight());
					thrownWeaponsPanel.setLocation(0, thieveryPanel.getY()+thieveryPanel.getHeight());
				}
			}
		});
		chckbxClimbing.setBackground(new Color(255, 173, 84));
		chckbxClimbing.setBounds(187, 5, 97, 23);
		climbingPanel.add(chckbxClimbing);		
		
		// DrivingSpecs
		List<JFormattedTextField> drivingSpecsFields = new ArrayList<JFormattedTextField>();
		JCheckBox chckbxDriving = new JCheckBox("Show specs");
		chckbxDriving.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> drivingSpecsList = currentSheet.assignSkillSpecs("Driving");
				if(chckbxDriving.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "Driving", "");
					drivingSpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;
					int extra = (drivingSpecsList.size())*28;
					
					coordinationPanel.setSize(coordinationPanel.getWidth(), (coordinationPanel.getHeight()+extra));
					drivingPanel.setSize(354, (32+extra));
					marksmanshipPanel.setLocation(0, marksmanshipPanel.getY()+extra);
					thieveryPanel.setLocation(0, thieveryPanel.getY()+extra);
					thrownWeaponsPanel.setLocation(0, thrownWeaponsPanel.getY()+extra);
					for(int i = 0; i < drivingSpecsList.size(); i++)
					{
						int j = i;
						drivingSpecsFields.add(new JFormattedTextField());
						JFormattedTextField drivingSpecs = drivingSpecsFields.get(i);
						drivingSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						drivingSpecs.setBackground(new Color(255, 235, 209));
						drivingSpecs.setBounds(5, 33+(i*28), 279, 22);
						drivingPanel.add(drivingSpecs);
						drivingSpecs.setText(drivingSpecsList.get(i).getDescription());
						drivingSpecs.requestFocus();	

						drivingSpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = drivingSpecsList.get(j);
									editing.setDescription(drivingSpecs.getText());
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
										chckbxDriving.setSelected(false);
										chckbxDriving.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxDriving.isSelected())
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

					drivingSpecsList.clear();
					for(JFormattedTextField t : drivingSpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						drivingPanel.remove(t);
						t = null;
					}
					drivingSpecsFields.clear();
							
					coordinationPanel.setSize(coordinationPanel.getWidth(), (coordinationPanel.getHeight()-(drivingPanel.getHeight()-32)));
					drivingPanel.setSize(354, (32));
					marksmanshipPanel.setLocation(0, drivingPanel.getY()+drivingPanel.getHeight());
					thieveryPanel.setLocation(0, marksmanshipPanel.getY()+marksmanshipPanel.getHeight());
					thrownWeaponsPanel.setLocation(0, thieveryPanel.getY()+thieveryPanel.getHeight());
				}
			}
		});
		chckbxDriving.setBackground(new Color(255, 201, 131));
		chckbxDriving.setBounds(187, 5, 97, 23);
		drivingPanel.add(chckbxDriving);		
		
		// MarksmanshipSpecs
		List<JFormattedTextField> marksmanshipSpecsFields = new ArrayList<JFormattedTextField>();
		JCheckBox chckbxMarksmanship = new JCheckBox("Show specs");
		chckbxMarksmanship.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> marksmanshipSpecsList = currentSheet.assignSkillSpecs("Marksmanship");
				if(chckbxMarksmanship.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "Marksmanship", "");
					marksmanshipSpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;
					int extra = (marksmanshipSpecsList.size())*28;
					
					coordinationPanel.setSize(coordinationPanel.getWidth(), (coordinationPanel.getHeight()+extra));
					marksmanshipPanel.setSize(354, (32+extra));
					thieveryPanel.setLocation(0, thieveryPanel.getY()+extra);
					thrownWeaponsPanel.setLocation(0, thrownWeaponsPanel.getY()+extra);
					for(int i = 0; i < marksmanshipSpecsList.size(); i++)
					{
						int j = i;
						marksmanshipSpecsFields.add(new JFormattedTextField());
						JFormattedTextField marksmanshipSpecs = marksmanshipSpecsFields.get(i);
						marksmanshipSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						marksmanshipSpecs.setBackground(new Color(255, 235, 209));
						marksmanshipSpecs.setBounds(5, 33+(i*28), 279, 22);
						marksmanshipPanel.add(marksmanshipSpecs);
						marksmanshipSpecs.setText(marksmanshipSpecsList.get(i).getDescription());
						marksmanshipSpecs.requestFocus();	

						marksmanshipSpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = marksmanshipSpecsList.get(j);
									editing.setDescription(marksmanshipSpecs.getText());
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
										chckbxMarksmanship.setSelected(false);
										chckbxMarksmanship.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxMarksmanship.isSelected())
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

					marksmanshipSpecsList.clear();
					for(JFormattedTextField t : marksmanshipSpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						marksmanshipPanel.remove(t);
						t = null;
					}
					marksmanshipSpecsFields.clear();
							
					coordinationPanel.setSize(coordinationPanel.getWidth(), (coordinationPanel.getHeight()-(marksmanshipPanel.getHeight()-32)));
					marksmanshipPanel.setSize(354, (32));
					thieveryPanel.setLocation(0, marksmanshipPanel.getY()+marksmanshipPanel.getHeight());
					thrownWeaponsPanel.setLocation(0, thieveryPanel.getY()+thieveryPanel.getHeight());
				}
			}
		});
		chckbxMarksmanship.setBackground(new Color(255, 173, 84));
		chckbxMarksmanship.setBounds(187, 5, 97, 23);
		marksmanshipPanel.add(chckbxMarksmanship);
		
		// ThieverySpecs
		List<JFormattedTextField> thieverySpecsFields = new ArrayList<JFormattedTextField>();
		JCheckBox chckbxThievery = new JCheckBox("Show specs");
		chckbxThievery.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> thieverySpecsList = currentSheet.assignSkillSpecs("Thievery");
				if(chckbxThievery.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "Thievery", "");
					thieverySpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;
					int extra = (thieverySpecsList.size())*28;
					
					coordinationPanel.setSize(coordinationPanel.getWidth(), (coordinationPanel.getHeight()+extra));
					thieveryPanel.setSize(354, (32+extra));
					thrownWeaponsPanel.setLocation(0, thrownWeaponsPanel.getY()+extra);
					for(int i = 0; i < thieverySpecsList.size(); i++)
					{
						int j = i;
						thieverySpecsFields.add(new JFormattedTextField());
						JFormattedTextField thieverySpecs = thieverySpecsFields.get(i);
						thieverySpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						thieverySpecs.setBackground(new Color(255, 235, 209));
						thieverySpecs.setBounds(5, 33+(i*28), 279, 22);
						thieveryPanel.add(thieverySpecs);
						thieverySpecs.setText(thieverySpecsList.get(i).getDescription());
						thieverySpecs.requestFocus();	

						thieverySpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = thieverySpecsList.get(j);
									editing.setDescription(thieverySpecs.getText());
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
										chckbxThievery.setSelected(false);
										chckbxThievery.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxThievery.isSelected())
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

					thieverySpecsList.clear();
					for(JFormattedTextField t : thieverySpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						thieveryPanel.remove(t);
						t = null;
					}
					thieverySpecsFields.clear();
							
					coordinationPanel.setSize(coordinationPanel.getWidth(), (coordinationPanel.getHeight()-(thieveryPanel.getHeight()-32)));
					thieveryPanel.setSize(354, (32));
					thrownWeaponsPanel.setLocation(0, thieveryPanel.getY()+thieveryPanel.getHeight());
				}
			}
		});
		chckbxThievery.setBackground(new Color(255, 201, 131));
		chckbxThievery.setBounds(187, 5, 97, 23);
		thieveryPanel.add(chckbxThievery);
		
		// ThrownWeaponsSpecs
		List<JFormattedTextField> thrownWeaponsSpecsFields = new ArrayList<JFormattedTextField>();
		JCheckBox chckbxThrownWeapons = new JCheckBox("Show specs");
		chckbxThrownWeapons.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> thrownWeaponsSpecsList = currentSheet.assignSkillSpecs("ThrownWeapons");
				if(chckbxThrownWeapons.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "ThrownWeapons", "");
					thrownWeaponsSpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;
					int extra = (thrownWeaponsSpecsList.size())*28;
					
					coordinationPanel.setSize(coordinationPanel.getWidth(), (coordinationPanel.getHeight()+extra));
					thrownWeaponsPanel.setSize(354, (32+extra));
					for(int i = 0; i < thrownWeaponsSpecsList.size(); i++)
					{
						int j = i;
						thrownWeaponsSpecsFields.add(new JFormattedTextField());
						JFormattedTextField thrownWeaponsSpecs = thrownWeaponsSpecsFields.get(i);
						thrownWeaponsSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						thrownWeaponsSpecs.setBackground(new Color(255, 235, 209));
						thrownWeaponsSpecs.setBounds(5, 33+(i*28), 279, 22);
						thrownWeaponsPanel.add(thrownWeaponsSpecs);
						thrownWeaponsSpecs.setText(thrownWeaponsSpecsList.get(i).getDescription());
						thrownWeaponsSpecs.requestFocus();	

						thrownWeaponsSpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = thrownWeaponsSpecsList.get(j);
									editing.setDescription(thrownWeaponsSpecs.getText());
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
										chckbxThrownWeapons.setSelected(false);
										chckbxThrownWeapons.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxThrownWeapons.isSelected())
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

					thrownWeaponsSpecsList.clear();
					for(JFormattedTextField t : thrownWeaponsSpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						thrownWeaponsPanel.remove(t);
						t = null;
					}
					thrownWeaponsSpecsFields.clear();
							
					coordinationPanel.setSize(coordinationPanel.getWidth(), (coordinationPanel.getHeight()-(thrownWeaponsPanel.getHeight()-32)));
					thrownWeaponsPanel.setSize(354, (32));
				}
			}
		});
		chckbxThrownWeapons.setBackground(new Color(255, 173, 84));
		chckbxThrownWeapons.setBounds(187, 5, 97, 23);
		thrownWeaponsPanel.add(chckbxThrownWeapons);	
		
		// AthleticsSpecs
		List<JFormattedTextField> athleticsSpecsFields = new ArrayList<JFormattedTextField>();
		JCheckBox chckbxAthletics = new JCheckBox("Show specs");
		chckbxAthletics.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> athleticsSpecsList = currentSheet.assignSkillSpecs("Athletics");
				if(chckbxAthletics.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "Athletics", "");
					athleticsSpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;
					int extra = (athleticsSpecsList.size())*28;
					
					physiquePanel.setSize(physiquePanel.getWidth(), (physiquePanel.getHeight()+extra));
					athleticsPanel.setSize(354, (32+extra));
					leapPanel.setLocation(0, leapPanel.getY()+extra);
					liftingPanel.setLocation(0, liftingPanel.getY()+extra);
					resistancePanel.setLocation(0, resistancePanel.getY()+extra);
					runningPanel.setLocation(0, runningPanel.getY()+extra);
					swimmingPanel.setLocation(0, swimmingPanel.getY()+extra);
					for(int i = 0; i < athleticsSpecsList.size(); i++)
					{
						int j = i;
						athleticsSpecsFields.add(new JFormattedTextField());
						JFormattedTextField athleticsSpecs = athleticsSpecsFields.get(i);
						athleticsSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						athleticsSpecs.setBackground(new Color(255, 255, 204));
						athleticsSpecs.setBounds(5, 33+(i*28), 279, 22);
						athleticsPanel.add(athleticsSpecs);
						athleticsSpecs.setText(athleticsSpecsList.get(i).getDescription());
						athleticsSpecs.requestFocus();	

						athleticsSpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = athleticsSpecsList.get(j);
									editing.setDescription(athleticsSpecs.getText());
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
										chckbxAthletics.setSelected(false);
										chckbxAthletics.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxAthletics.isSelected())
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

					athleticsSpecsList.clear();
					for(JFormattedTextField t : athleticsSpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						athleticsPanel.remove(t);
						t = null;
					}
					athleticsSpecsFields.clear();
							
					physiquePanel.setSize(physiquePanel.getWidth(), (physiquePanel.getHeight()-(athleticsPanel.getHeight()-32)));
					athleticsPanel.setSize(354, (32));
					leapPanel.setLocation(0, athleticsPanel.getY()+32);
					liftingPanel.setLocation(0, leapPanel.getY()+leapPanel.getHeight());
					resistancePanel.setLocation(0, liftingPanel.getY()+liftingPanel.getHeight());
					runningPanel.setLocation(0, resistancePanel.getY()+resistancePanel.getHeight());
					swimmingPanel.setLocation(0, runningPanel.getY()+runningPanel.getHeight());
				}
			}
		});
		chckbxAthletics.setBackground(new Color(240, 230, 140));
		chckbxAthletics.setBounds(187, 5, 97, 23);
		athleticsPanel.add(chckbxAthletics);		
		
		// LeapSpecs
		List<JFormattedTextField> leapSpecsFields = new ArrayList<JFormattedTextField>();
		JCheckBox chckbxLeap = new JCheckBox("Show specs");
		chckbxLeap.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> leapSpecsList = currentSheet.assignSkillSpecs("Leap");
				if(chckbxLeap.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "Leap", "");
					leapSpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;
					int extra = (leapSpecsList.size())*28;
					
					physiquePanel.setSize(physiquePanel.getWidth(), (physiquePanel.getHeight()+extra));
					leapPanel.setSize(354, (32+extra));
					liftingPanel.setLocation(0, liftingPanel.getY()+extra);
					resistancePanel.setLocation(0, resistancePanel.getY()+extra);
					runningPanel.setLocation(0, runningPanel.getY()+extra);
					swimmingPanel.setLocation(0, swimmingPanel.getY()+extra);
					for(int i = 0; i < leapSpecsList.size(); i++)
					{
						int j = i;
						leapSpecsFields.add(new JFormattedTextField());
						JFormattedTextField leapSpecs = leapSpecsFields.get(i);
						leapSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						leapSpecs.setBackground(new Color(255, 255, 204));
						leapSpecs.setBounds(5, 33+(i*28), 279, 22);
						leapPanel.add(leapSpecs);
						leapSpecs.setText(leapSpecsList.get(i).getDescription());
						leapSpecs.requestFocus();	

						leapSpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = leapSpecsList.get(j);
									editing.setDescription(leapSpecs.getText());
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
										chckbxLeap.setSelected(false);
										chckbxLeap.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxLeap.isSelected())
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

					leapSpecsList.clear();
					for(JFormattedTextField t : leapSpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						leapPanel.remove(t);
						t = null;
					}
					leapSpecsFields.clear();
							
					physiquePanel.setSize(physiquePanel.getWidth(), (physiquePanel.getHeight()-(leapPanel.getHeight()-32)));
					leapPanel.setSize(354, (32));
					liftingPanel.setLocation(0, leapPanel.getY()+leapPanel.getHeight());
					resistancePanel.setLocation(0, liftingPanel.getY()+liftingPanel.getHeight());
					runningPanel.setLocation(0, resistancePanel.getY()+resistancePanel.getHeight());
					swimmingPanel.setLocation(0, runningPanel.getY()+runningPanel.getHeight());
				}
			}
		});
		chckbxLeap.setBackground(new Color(255, 255, 153));
		chckbxLeap.setBounds(187, 5, 97, 23);
		leapPanel.add(chckbxLeap);		
		
		// LiftingSpecs
		List<JFormattedTextField> liftingSpecsFields = new ArrayList<JFormattedTextField>();
		JCheckBox chckbxLifting = new JCheckBox("Show specs");
		chckbxLifting.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> liftingSpecsList = currentSheet.assignSkillSpecs("Lifting");
				if(chckbxLifting.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "Lifting", "");
					liftingSpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;
					int extra = (liftingSpecsList.size())*28;
					
					physiquePanel.setSize(physiquePanel.getWidth(), (physiquePanel.getHeight()+extra));
					liftingPanel.setSize(354, (32+extra));
					resistancePanel.setLocation(0, resistancePanel.getY()+extra);
					runningPanel.setLocation(0, runningPanel.getY()+extra);
					swimmingPanel.setLocation(0, swimmingPanel.getY()+extra);
					for(int i = 0; i < liftingSpecsList.size(); i++)
					{
						int j = i;
						liftingSpecsFields.add(new JFormattedTextField());
						JFormattedTextField liftingSpecs = liftingSpecsFields.get(i);
						liftingSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						liftingSpecs.setBackground(new Color(255, 255, 204));
						liftingSpecs.setBounds(5, 33+(i*28), 279, 22);
						liftingPanel.add(liftingSpecs);
						liftingSpecs.setText(liftingSpecsList.get(i).getDescription());
						liftingSpecs.requestFocus();	

						liftingSpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = liftingSpecsList.get(j);
									editing.setDescription(liftingSpecs.getText());
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
										chckbxLifting.setSelected(false);
										chckbxLifting.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxLifting.isSelected())
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

					liftingSpecsList.clear();
					for(JFormattedTextField t : liftingSpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						liftingPanel.remove(t);
						t = null;
					}
					liftingSpecsFields.clear();
							
					physiquePanel.setSize(physiquePanel.getWidth(), (physiquePanel.getHeight()-(liftingPanel.getHeight()-32)));
					liftingPanel.setSize(354, (32));
					resistancePanel.setLocation(0, liftingPanel.getY()+liftingPanel.getHeight());
					runningPanel.setLocation(0, resistancePanel.getY()+resistancePanel.getHeight());
					swimmingPanel.setLocation(0, runningPanel.getY()+runningPanel.getHeight());
				}
			}
		});
		chckbxLifting.setBackground(new Color(240, 230, 140));
		chckbxLifting.setBounds(187, 5, 97, 23);
		liftingPanel.add(chckbxLifting);	
		
		// ResistanceSpecs
		List<JFormattedTextField> resistanceSpecsFields = new ArrayList<JFormattedTextField>();
		JCheckBox chckbxResistance = new JCheckBox("Show specs");
		chckbxResistance.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> resistanceSpecsList = currentSheet.assignSkillSpecs("Resistance");
				if(chckbxResistance.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "Resistance", "");
					resistanceSpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;
					int extra = (resistanceSpecsList.size())*28;
					
					physiquePanel.setSize(physiquePanel.getWidth(), (physiquePanel.getHeight()+extra));
					resistancePanel.setSize(354, (32+extra));
					runningPanel.setLocation(0, runningPanel.getY()+extra);
					swimmingPanel.setLocation(0, swimmingPanel.getY()+extra);
					for(int i = 0; i < resistanceSpecsList.size(); i++)
					{
						int j = i;
						resistanceSpecsFields.add(new JFormattedTextField());
						JFormattedTextField resistanceSpecs = resistanceSpecsFields.get(i);
						resistanceSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						resistanceSpecs.setBackground(new Color(255, 255, 204));
						resistanceSpecs.setBounds(5, 33+(i*28), 279, 22);
						resistancePanel.add(resistanceSpecs);
						resistanceSpecs.setText(resistanceSpecsList.get(i).getDescription());
						resistanceSpecs.requestFocus();	

						resistanceSpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = resistanceSpecsList.get(j);
									editing.setDescription(resistanceSpecs.getText());
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
										chckbxResistance.setSelected(false);
										chckbxResistance.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxResistance.isSelected())
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

					resistanceSpecsList.clear();
					for(JFormattedTextField t : resistanceSpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						resistancePanel.remove(t);
						t = null;
					}
					resistanceSpecsFields.clear();
							
					physiquePanel.setSize(physiquePanel.getWidth(), (physiquePanel.getHeight()-(resistancePanel.getHeight()-32)));
					resistancePanel.setSize(354, (32));
					runningPanel.setLocation(0, resistancePanel.getY()+resistancePanel.getHeight());
					swimmingPanel.setLocation(0, runningPanel.getY()+runningPanel.getHeight());
				}
			}
		});
		chckbxResistance.setBackground(new Color(255, 255, 153));
		chckbxResistance.setBounds(187, 5, 97, 23);
		resistancePanel.add(chckbxResistance);		
		
		// RunningSpecs
		List<JFormattedTextField> runningSpecsFields = new ArrayList<JFormattedTextField>();
		JCheckBox chckbxRunning = new JCheckBox("Show specs");
		chckbxRunning.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> runningSpecsList = currentSheet.assignSkillSpecs("Running");
				if(chckbxRunning.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "Running", "");
					runningSpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;
					int extra = (runningSpecsList.size())*28;
					
					physiquePanel.setSize(physiquePanel.getWidth(), (physiquePanel.getHeight()+extra));
					runningPanel.setSize(354, (32+extra));
					swimmingPanel.setLocation(0, swimmingPanel.getY()+extra);
					for(int i = 0; i < runningSpecsList.size(); i++)
					{
						int j = i;
						runningSpecsFields.add(new JFormattedTextField());
						JFormattedTextField runningSpecs = runningSpecsFields.get(i);
						runningSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						runningSpecs.setBackground(new Color(255, 255, 204));
						runningSpecs.setBounds(5, 33+(i*28), 279, 22);
						runningPanel.add(runningSpecs);
						runningSpecs.setText(runningSpecsList.get(i).getDescription());
						runningSpecs.requestFocus();	

						runningSpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = runningSpecsList.get(j);
									editing.setDescription(runningSpecs.getText());
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
										chckbxRunning.setSelected(false);
										chckbxRunning.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxRunning.isSelected())
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

					runningSpecsList.clear();
					for(JFormattedTextField t : runningSpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						runningPanel.remove(t);
						t = null;
					}
					runningSpecsFields.clear();
							
					physiquePanel.setSize(physiquePanel.getWidth(), (physiquePanel.getHeight()-(runningPanel.getHeight()-32)));
					runningPanel.setSize(354, (32));
					swimmingPanel.setLocation(0, runningPanel.getY()+runningPanel.getHeight());
				}
			}
		});
		chckbxRunning.setBackground(new Color(240, 230, 140));
		chckbxRunning.setBounds(187, 5, 97, 23);
		runningPanel.add(chckbxRunning);	
		
		// SwimmingSpecs
		List<JFormattedTextField> swimmingSpecsFields = new ArrayList<JFormattedTextField>();
		JCheckBox chckbxSwimming = new JCheckBox("Show specs");
		chckbxSwimming.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> swimmingSpecsList = currentSheet.assignSkillSpecs("Swimming");
				if(chckbxSwimming.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "Swimming", "");
					swimmingSpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;
					int extra = (swimmingSpecsList.size())*28;
					
					physiquePanel.setSize(physiquePanel.getWidth(), (physiquePanel.getHeight()+extra));
					swimmingPanel.setSize(354, (32+extra));
					for(int i = 0; i < swimmingSpecsList.size(); i++)
					{
						int j = i;
						swimmingSpecsFields.add(new JFormattedTextField());
						JFormattedTextField swimmingSpecs = swimmingSpecsFields.get(i);
						swimmingSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						swimmingSpecs.setBackground(new Color(255, 255, 204));
						swimmingSpecs.setBounds(5, 33+(i*28), 279, 22);
						swimmingPanel.add(swimmingSpecs);
						swimmingSpecs.setText(swimmingSpecsList.get(i).getDescription());
						swimmingSpecs.requestFocus();	

						swimmingSpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = swimmingSpecsList.get(j);
									editing.setDescription(swimmingSpecs.getText());
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
										chckbxSwimming.setSelected(false);
										chckbxSwimming.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxSwimming.isSelected())
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

					swimmingSpecsList.clear();
					for(JFormattedTextField t : swimmingSpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						swimmingPanel.remove(t);
						t = null;
					}
					swimmingSpecsFields.clear();
							
					physiquePanel.setSize(physiquePanel.getWidth(), (physiquePanel.getHeight()-(swimmingPanel.getHeight()-32)));
					swimmingPanel.setSize(354, (32));
				}
			}
		});
		chckbxSwimming.setBackground(new Color(255, 255, 153));
		chckbxSwimming.setBounds(187, 5, 97, 23);
		swimmingPanel.add(chckbxSwimming);		
		
		// ArcaneLoreSpecs
		List<JFormattedTextField> arcaneLoreSpecsFields = new ArrayList<JFormattedTextField>();
		JCheckBox chckbxArcaneLore = new JCheckBox("Show specs");
		chckbxArcaneLore.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> arcaneLoreSpecsList = currentSheet.assignSkillSpecs("Arcane Lore");
				if(chckbxArcaneLore.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "Arcane Lore", "");
					arcaneLoreSpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;

					int extra = (arcaneLoreSpecsList.size())*28;
					
					knowledgePanel.setSize(knowledgePanel.getWidth(), (knowledgePanel.getHeight()+extra));
					arcaneLorePanel.setSize(354, (32+extra));
					demolitionsPanel.setLocation(0, demolitionsPanel.getY()+extra);
					languagesPanel.setLocation(0, languagesPanel.getY()+extra);
					medicinePanel.setLocation(0, medicinePanel.getY()+extra);
					scholarPanel.setLocation(0, scholarPanel.getY()+extra);
					sciencePanel.setLocation(0, sciencePanel.getY()+extra);
					securityPanel.setLocation(0, securityPanel.getY()+extra);

					for(int i = 0; i < arcaneLoreSpecsList.size(); i++)
					{
						int j = i;
						arcaneLoreSpecsFields.add(new JFormattedTextField());
						JFormattedTextField arcaneLoreSpecs = arcaneLoreSpecsFields.get(i);
						arcaneLoreSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						arcaneLoreSpecs.setBackground(new Color(240, 255, 240));
						arcaneLoreSpecs.setBounds(5, 33+(i*28), 279, 22);
						arcaneLorePanel.add(arcaneLoreSpecs);
						arcaneLoreSpecs.setText(arcaneLoreSpecsList.get(i).getDescription());
						arcaneLoreSpecs.requestFocus();	

						arcaneLoreSpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = arcaneLoreSpecsList.get(j);
									editing.setDescription(arcaneLoreSpecs.getText());
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
										chckbxArcaneLore.setSelected(false);
										chckbxArcaneLore.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxArcaneLore.isSelected())
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

					arcaneLoreSpecsList.clear();
					for(JFormattedTextField t : arcaneLoreSpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						arcaneLorePanel.remove(t);
						t = null;
					}
					arcaneLoreSpecsFields.clear();
					
					knowledgePanel.setSize(knowledgePanel.getWidth(), (knowledgePanel.getHeight()-(arcaneLorePanel.getHeight()-32)));
					arcaneLorePanel.setSize(354, (32));
					demolitionsPanel.setLocation(0, arcaneLorePanel.getY()+32);
					languagesPanel.setLocation(0, demolitionsPanel.getY()+demolitionsPanel.getHeight());
					medicinePanel.setLocation(0, languagesPanel.getY()+languagesPanel.getHeight());
					scholarPanel.setLocation(0, medicinePanel.getY()+medicinePanel.getHeight());
					sciencePanel.setLocation(0, scholarPanel.getY()+scholarPanel.getHeight());
					securityPanel.setLocation(0, sciencePanel.getY()+sciencePanel.getHeight());
				}
			}
		});
		chckbxArcaneLore.setBackground(new Color(144, 238, 144));
		chckbxArcaneLore.setBounds(187, 5, 97, 23);
		arcaneLorePanel.add(chckbxArcaneLore);		
		
		// DemolitionsSpecs
		List<JFormattedTextField> demolitionsSpecsFields = new ArrayList<JFormattedTextField>();
		JCheckBox chckbxDemolitions = new JCheckBox("Show specs");
		chckbxDemolitions.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> demolitionsSpecsList = currentSheet.assignSkillSpecs("Demolitions");
				if(chckbxDemolitions.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "Demolitions", "");
					demolitionsSpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;

					int extra = (demolitionsSpecsList.size())*28;
					
					knowledgePanel.setSize(knowledgePanel.getWidth(), (knowledgePanel.getHeight()+extra));
					demolitionsPanel.setSize(354, (32+extra));
					languagesPanel.setLocation(0, languagesPanel.getY()+extra);
					medicinePanel.setLocation(0, medicinePanel.getY()+extra);
					scholarPanel.setLocation(0, scholarPanel.getY()+extra);
					sciencePanel.setLocation(0, sciencePanel.getY()+extra);
					securityPanel.setLocation(0, securityPanel.getY()+extra);

					for(int i = 0; i < demolitionsSpecsList.size(); i++)
					{
						int j = i;
						demolitionsSpecsFields.add(new JFormattedTextField());
						JFormattedTextField demolitionsSpecs = demolitionsSpecsFields.get(i);
						demolitionsSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						demolitionsSpecs.setBackground(new Color(240, 255, 240));
						demolitionsSpecs.setBounds(5, 33+(i*28), 279, 22);
						demolitionsPanel.add(demolitionsSpecs);
						demolitionsSpecs.setText(demolitionsSpecsList.get(i).getDescription());
						demolitionsSpecs.requestFocus();	

						demolitionsSpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = demolitionsSpecsList.get(j);
									editing.setDescription(demolitionsSpecs.getText());
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
										chckbxDemolitions.setSelected(false);
										chckbxDemolitions.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxDemolitions.isSelected())
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

					demolitionsSpecsList.clear();
					for(JFormattedTextField t : demolitionsSpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						demolitionsPanel.remove(t);
						t = null;
					}
					demolitionsSpecsFields.clear();
					
					knowledgePanel.setSize(knowledgePanel.getWidth(), (knowledgePanel.getHeight()-(demolitionsPanel.getHeight()-32)));
					demolitionsPanel.setSize(354, (32));
					languagesPanel.setLocation(0, demolitionsPanel.getY()+demolitionsPanel.getHeight());
					medicinePanel.setLocation(0, languagesPanel.getY()+languagesPanel.getHeight());
					scholarPanel.setLocation(0, medicinePanel.getY()+medicinePanel.getHeight());
					sciencePanel.setLocation(0, scholarPanel.getY()+scholarPanel.getHeight());
					securityPanel.setLocation(0, sciencePanel.getY()+sciencePanel.getHeight());
				}
			}
		});
		chckbxDemolitions.setBackground(new Color(50, 205, 50));
		chckbxDemolitions.setBounds(187, 5, 97, 23);
		demolitionsPanel.add(chckbxDemolitions);		
		
		// LanguagesSpecs
		List<JFormattedTextField> languagesSpecsFields = new ArrayList<JFormattedTextField>();
		JCheckBox chckbxLanguages = new JCheckBox("Show specs");
		chckbxLanguages.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> languagesSpecsList = currentSheet.assignSkillSpecs("Languages");
				if(chckbxLanguages.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "Languages", "");
					languagesSpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;

					int extra = (languagesSpecsList.size())*28;
					
					knowledgePanel.setSize(knowledgePanel.getWidth(), (knowledgePanel.getHeight()+extra));
					languagesPanel.setSize(354, (32+extra));
					medicinePanel.setLocation(0, medicinePanel.getY()+extra);
					scholarPanel.setLocation(0, scholarPanel.getY()+extra);
					sciencePanel.setLocation(0, sciencePanel.getY()+extra);
					securityPanel.setLocation(0, securityPanel.getY()+extra);

					for(int i = 0; i < languagesSpecsList.size(); i++)
					{
						int j = i;
						languagesSpecsFields.add(new JFormattedTextField());
						JFormattedTextField languagesSpecs = languagesSpecsFields.get(i);
						languagesSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						languagesSpecs.setBackground(new Color(240, 255, 240));
						languagesSpecs.setBounds(5, 33+(i*28), 279, 22);
						languagesPanel.add(languagesSpecs);
						languagesSpecs.setText(languagesSpecsList.get(i).getDescription());
						languagesSpecs.requestFocus();	

						languagesSpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = languagesSpecsList.get(j);
									editing.setDescription(languagesSpecs.getText());
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
										chckbxLanguages.setSelected(false);
										chckbxLanguages.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxLanguages.isSelected())
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

					languagesSpecsList.clear();
					for(JFormattedTextField t : languagesSpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						languagesPanel.remove(t);
						t = null;
					}
					languagesSpecsFields.clear();
					
					knowledgePanel.setSize(knowledgePanel.getWidth(), (knowledgePanel.getHeight()-(languagesPanel.getHeight()-32)));
					languagesPanel.setSize(354, (32));
					medicinePanel.setLocation(0, languagesPanel.getY()+languagesPanel.getHeight());
					scholarPanel.setLocation(0, medicinePanel.getY()+medicinePanel.getHeight());
					sciencePanel.setLocation(0, scholarPanel.getY()+scholarPanel.getHeight());
					securityPanel.setLocation(0, sciencePanel.getY()+sciencePanel.getHeight());
				}
			}
		});
		chckbxLanguages.setBackground(new Color(144, 238, 144));
		chckbxLanguages.setBounds(187, 5, 97, 23);
		languagesPanel.add(chckbxLanguages);	
		
		// MedicineSpecs
		List<JFormattedTextField> medicineSpecsFields = new ArrayList<JFormattedTextField>();
		JCheckBox chckbxMedicine = new JCheckBox("Show specs");
		chckbxMedicine.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> medicineSpecsList = currentSheet.assignSkillSpecs("Medicine");
				if(chckbxMedicine.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "Medicine", "");
					medicineSpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;

					int extra = (medicineSpecsList.size())*28;
					
					knowledgePanel.setSize(knowledgePanel.getWidth(), (knowledgePanel.getHeight()+extra));
					medicinePanel.setSize(354, (32+extra));
					scholarPanel.setLocation(0, scholarPanel.getY()+extra);
					sciencePanel.setLocation(0, sciencePanel.getY()+extra);
					securityPanel.setLocation(0, securityPanel.getY()+extra);

					for(int i = 0; i < medicineSpecsList.size(); i++)
					{
						int j = i;
						medicineSpecsFields.add(new JFormattedTextField());
						JFormattedTextField medicineSpecs = medicineSpecsFields.get(i);
						medicineSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						medicineSpecs.setBackground(new Color(240, 255, 240));
						medicineSpecs.setBounds(5, 33+(i*28), 279, 22);
						medicinePanel.add(medicineSpecs);
						medicineSpecs.setText(medicineSpecsList.get(i).getDescription());
						medicineSpecs.requestFocus();	

						medicineSpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = medicineSpecsList.get(j);
									editing.setDescription(medicineSpecs.getText());
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
										chckbxMedicine.setSelected(false);
										chckbxMedicine.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxMedicine.isSelected())
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

					medicineSpecsList.clear();
					for(JFormattedTextField t : medicineSpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						medicinePanel.remove(t);
						t = null;
					}
					medicineSpecsFields.clear();
					
					knowledgePanel.setSize(knowledgePanel.getWidth(), (knowledgePanel.getHeight()-(medicinePanel.getHeight()-32)));
					medicinePanel.setSize(354, (32));
					scholarPanel.setLocation(0, medicinePanel.getY()+medicinePanel.getHeight());
					sciencePanel.setLocation(0, scholarPanel.getY()+scholarPanel.getHeight());
					securityPanel.setLocation(0, sciencePanel.getY()+sciencePanel.getHeight());
				}
			}
		});
		chckbxMedicine.setBackground(new Color(50, 205, 50));
		chckbxMedicine.setBounds(187, 5, 97, 23);
		medicinePanel.add(chckbxMedicine);		
		
		// ScholarSpecs
		List<JFormattedTextField> scholarSpecsFields = new ArrayList<JFormattedTextField>();
		JCheckBox chckbxScholar = new JCheckBox("Show specs");
		chckbxScholar.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> scholarSpecsList = currentSheet.assignSkillSpecs("Scholar");
				if(chckbxScholar.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "Scholar", "");
					scholarSpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;

					int extra = (scholarSpecsList.size())*28;
					
					knowledgePanel.setSize(knowledgePanel.getWidth(), (knowledgePanel.getHeight()+extra));
					scholarPanel.setSize(354, (32+extra));
					sciencePanel.setLocation(0, sciencePanel.getY()+extra);
					securityPanel.setLocation(0, securityPanel.getY()+extra);

					for(int i = 0; i < scholarSpecsList.size(); i++)
					{
						int j = i;
						scholarSpecsFields.add(new JFormattedTextField());
						JFormattedTextField scholarSpecs = scholarSpecsFields.get(i);
						scholarSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						scholarSpecs.setBackground(new Color(240, 255, 240));
						scholarSpecs.setBounds(5, 33+(i*28), 279, 22);
						scholarPanel.add(scholarSpecs);
						scholarSpecs.setText(scholarSpecsList.get(i).getDescription());
						scholarSpecs.requestFocus();	

						scholarSpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = scholarSpecsList.get(j);
									editing.setDescription(scholarSpecs.getText());
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
										chckbxScholar.setSelected(false);
										chckbxScholar.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxScholar.isSelected())
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

					scholarSpecsList.clear();
					for(JFormattedTextField t : scholarSpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						scholarPanel.remove(t);
						t = null;
					}
					scholarSpecsFields.clear();
					
					knowledgePanel.setSize(knowledgePanel.getWidth(), (knowledgePanel.getHeight()-(scholarPanel.getHeight()-32)));
					scholarPanel.setSize(354, (32));
					sciencePanel.setLocation(0, scholarPanel.getY()+scholarPanel.getHeight());
					securityPanel.setLocation(0, sciencePanel.getY()+sciencePanel.getHeight());
				}
			}
		});
		chckbxScholar.setBackground(new Color(144, 238, 144));
		chckbxScholar.setBounds(187, 5, 97, 23);
		scholarPanel.add(chckbxScholar);
		
		// ScienceSpecs
		List<JFormattedTextField> scienceSpecsFields = new ArrayList<JFormattedTextField>();
		JCheckBox chckbxScience = new JCheckBox("Show specs");
		chckbxScience.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> scienceSpecsList = currentSheet.assignSkillSpecs("Science");
				if(chckbxScience.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "Science", "");
					scienceSpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;

					int extra = (scienceSpecsList.size())*28;
					
					knowledgePanel.setSize(knowledgePanel.getWidth(), (knowledgePanel.getHeight()+extra));
					sciencePanel.setSize(354, (32+extra));
					securityPanel.setLocation(0, securityPanel.getY()+extra);

					for(int i = 0; i < scienceSpecsList.size(); i++)
					{
						int j = i;
						scienceSpecsFields.add(new JFormattedTextField());
						JFormattedTextField scienceSpecs = scienceSpecsFields.get(i);
						scienceSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						scienceSpecs.setBackground(new Color(240, 255, 240));
						scienceSpecs.setBounds(5, 33+(i*28), 279, 22);
						sciencePanel.add(scienceSpecs);
						scienceSpecs.setText(scienceSpecsList.get(i).getDescription());
						scienceSpecs.requestFocus();	

						scienceSpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = scienceSpecsList.get(j);
									editing.setDescription(scienceSpecs.getText());
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
										chckbxScience.setSelected(false);
										chckbxScience.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxScience.isSelected())
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

					scienceSpecsList.clear();
					for(JFormattedTextField t : scienceSpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						sciencePanel.remove(t);
						t = null;
					}
					scienceSpecsFields.clear();
					
					knowledgePanel.setSize(knowledgePanel.getWidth(), (knowledgePanel.getHeight()-(sciencePanel.getHeight()-32)));
					sciencePanel.setSize(354, (32));
					securityPanel.setLocation(0, sciencePanel.getY()+sciencePanel.getHeight());
				}
			}
		});
		chckbxScience.setBackground(new Color(50, 205, 50));
		chckbxScience.setBounds(187, 5, 97, 23);
		sciencePanel.add(chckbxScience);		
		
		// SecuritySpecs
		List<JFormattedTextField> securitySpecsFields = new ArrayList<JFormattedTextField>();
		JCheckBox chckbxSecurity = new JCheckBox("Show specs");
		chckbxSecurity.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> securitySpecsList = currentSheet.assignSkillSpecs("Security");
				if(chckbxSecurity.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "Security", "");
					securitySpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;

					int extra = (securitySpecsList.size())*28;
					
					knowledgePanel.setSize(knowledgePanel.getWidth(), (knowledgePanel.getHeight()+extra));
					securityPanel.setSize(354, (32+extra));

					for(int i = 0; i < securitySpecsList.size(); i++)
					{
						int j = i;
						securitySpecsFields.add(new JFormattedTextField());
						JFormattedTextField securitySpecs = securitySpecsFields.get(i);
						securitySpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						securitySpecs.setBackground(new Color(240, 255, 240));
						securitySpecs.setBounds(5, 33+(i*28), 279, 22);
						securityPanel.add(securitySpecs);
						securitySpecs.setText(securitySpecsList.get(i).getDescription());
						securitySpecs.requestFocus();	

						securitySpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = securitySpecsList.get(j);
									editing.setDescription(securitySpecs.getText());
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
										chckbxSecurity.setSelected(false);
										chckbxSecurity.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxSecurity.isSelected())
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

					securitySpecsList.clear();
					for(JFormattedTextField t : securitySpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						securityPanel.remove(t);
						t = null;
					}
					securitySpecsFields.clear();
					
					knowledgePanel.setSize(knowledgePanel.getWidth(), (knowledgePanel.getHeight()-(securityPanel.getHeight()-32)));
					securityPanel.setSize(354, (32));
				}
			}
		});
		chckbxSecurity.setBackground(new Color(144, 238, 144));
		chckbxSecurity.setBounds(187, 5, 97, 23);
		securityPanel.add(chckbxSecurity);			
		
		// ArtistSpecs
		List<JFormattedTextField> artistSpecsFields = new ArrayList<JFormattedTextField>();
		JCheckBox chckbxArtist = new JCheckBox("Show specs");
		chckbxArtist.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> artistSpecsList = currentSheet.assignSkillSpecs("Artist");
				if(chckbxArtist.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "Artist", "");
					artistSpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;
					int extra = (artistSpecsList.size())*28;
					
					perceptionPanel.setSize(perceptionPanel.getWidth(), (perceptionPanel.getHeight()+extra));
					artistPanel.setSize(354, (32+extra));
					engineeringPanel.setLocation(0, engineeringPanel.getY()+extra);
					searchPanel.setLocation(0, searchPanel.getY()+extra);
					streetwisePanel.setLocation(0, streetwisePanel.getY()+extra);
					surveillancePanel.setLocation(0, surveillancePanel.getY()+extra);
					survivalPanel.setLocation(0, survivalPanel.getY()+extra);
					for(int i = 0; i < artistSpecsList.size(); i++)
					{
						int j = i;
						artistSpecsFields.add(new JFormattedTextField());
						JFormattedTextField artistSpecs = artistSpecsFields.get(i);
						artistSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						artistSpecs.setBackground(new Color(224, 255, 255));
						artistSpecs.setBounds(5, 33+(i*28), 279, 22);
						artistPanel.add(artistSpecs);
						artistSpecs.setText(artistSpecsList.get(i).getDescription());
						artistSpecs.requestFocus();	

						artistSpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = artistSpecsList.get(j);
									editing.setDescription(artistSpecs.getText());
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
										chckbxArtist.setSelected(false);
										chckbxArtist.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxArtist.isSelected())
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

					artistSpecsList.clear();
					for(JFormattedTextField t : artistSpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						artistPanel.remove(t);
						t = null;
					}
					artistSpecsFields.clear();
							
					perceptionPanel.setSize(perceptionPanel.getWidth(), (perceptionPanel.getHeight()-(artistPanel.getHeight()-32)));
					artistPanel.setSize(354, (32));
					engineeringPanel.setLocation(0, artistPanel.getY()+32);
					searchPanel.setLocation(0, engineeringPanel.getY()+engineeringPanel.getHeight());
					streetwisePanel.setLocation(0, searchPanel.getY()+searchPanel.getHeight());
					surveillancePanel.setLocation(0, streetwisePanel.getY()+streetwisePanel.getHeight());
					survivalPanel.setLocation(0, surveillancePanel.getY()+surveillancePanel.getHeight());
				}
			}
		});
		chckbxArtist.setBackground(new Color(135, 206, 235));
		chckbxArtist.setBounds(187, 5, 97, 23);
		artistPanel.add(chckbxArtist);			
		
		// EngineeringSpecs
		List<JFormattedTextField> engineeringSpecsFields = new ArrayList<JFormattedTextField>();
		JCheckBox chckbxEngineering = new JCheckBox("Show specs");
		chckbxEngineering.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> engineeringSpecsList = currentSheet.assignSkillSpecs("Engineering");
				if(chckbxEngineering.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "Engineering", "");
					engineeringSpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;
					int extra = (engineeringSpecsList.size())*28;
					
					perceptionPanel.setSize(perceptionPanel.getWidth(), (perceptionPanel.getHeight()+extra));
					engineeringPanel.setSize(354, (32+extra));
					searchPanel.setLocation(0, searchPanel.getY()+extra);
					streetwisePanel.setLocation(0, streetwisePanel.getY()+extra);
					surveillancePanel.setLocation(0, surveillancePanel.getY()+extra);
					survivalPanel.setLocation(0, survivalPanel.getY()+extra);
					for(int i = 0; i < engineeringSpecsList.size(); i++)
					{
						int j = i;
						engineeringSpecsFields.add(new JFormattedTextField());
						JFormattedTextField engineeringSpecs = engineeringSpecsFields.get(i);
						engineeringSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						engineeringSpecs.setBackground(new Color(224, 255, 255));
						engineeringSpecs.setBounds(5, 33+(i*28), 279, 22);
						engineeringPanel.add(engineeringSpecs);
						engineeringSpecs.setText(engineeringSpecsList.get(i).getDescription());
						engineeringSpecs.requestFocus();	

						engineeringSpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = engineeringSpecsList.get(j);
									editing.setDescription(engineeringSpecs.getText());
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
										chckbxEngineering.setSelected(false);
										chckbxEngineering.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxEngineering.isSelected())
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

					engineeringSpecsList.clear();
					for(JFormattedTextField t : engineeringSpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						engineeringPanel.remove(t);
						t = null;
					}
					engineeringSpecsFields.clear();
							
					perceptionPanel.setSize(perceptionPanel.getWidth(), (perceptionPanel.getHeight()-(engineeringPanel.getHeight()-32)));
					engineeringPanel.setSize(354, (32));
					searchPanel.setLocation(0, engineeringPanel.getY()+engineeringPanel.getHeight());
					streetwisePanel.setLocation(0, searchPanel.getY()+searchPanel.getHeight());
					surveillancePanel.setLocation(0, streetwisePanel.getY()+streetwisePanel.getHeight());
					survivalPanel.setLocation(0, surveillancePanel.getY()+surveillancePanel.getHeight());
				}
			}
		});
		chckbxEngineering.setBackground(new Color(0, 191, 255));
		chckbxEngineering.setBounds(187, 5, 97, 23);
		engineeringPanel.add(chckbxEngineering);		
		
		// SearchSpecs
		List<JFormattedTextField> searchSpecsFields = new ArrayList<JFormattedTextField>();
		JCheckBox chckbxSearch = new JCheckBox("Show specs");
		chckbxSearch.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> searchSpecsList = currentSheet.assignSkillSpecs("Search");
				if(chckbxSearch.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "Search", "");
					searchSpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;
					int extra = (searchSpecsList.size())*28;
					
					perceptionPanel.setSize(perceptionPanel.getWidth(), (perceptionPanel.getHeight()+extra));
					searchPanel.setSize(354, (32+extra));
					streetwisePanel.setLocation(0, streetwisePanel.getY()+extra);
					surveillancePanel.setLocation(0, surveillancePanel.getY()+extra);
					survivalPanel.setLocation(0, survivalPanel.getY()+extra);
					for(int i = 0; i < searchSpecsList.size(); i++)
					{
						int j = i;
						searchSpecsFields.add(new JFormattedTextField());
						JFormattedTextField searchSpecs = searchSpecsFields.get(i);
						searchSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						searchSpecs.setBackground(new Color(224, 255, 255));
						searchSpecs.setBounds(5, 33+(i*28), 279, 22);
						searchPanel.add(searchSpecs);
						searchSpecs.setText(searchSpecsList.get(i).getDescription());
						searchSpecs.requestFocus();	

						searchSpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = searchSpecsList.get(j);
									editing.setDescription(searchSpecs.getText());
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
										chckbxSearch.setSelected(false);
										chckbxSearch.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxSearch.isSelected())
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

					searchSpecsList.clear();
					for(JFormattedTextField t : searchSpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						searchPanel.remove(t);
						t = null;
					}
					searchSpecsFields.clear();
							
					perceptionPanel.setSize(perceptionPanel.getWidth(), (perceptionPanel.getHeight()-(searchPanel.getHeight()-32)));
					searchPanel.setSize(354, (32));
					streetwisePanel.setLocation(0, searchPanel.getY()+searchPanel.getHeight());
					surveillancePanel.setLocation(0, streetwisePanel.getY()+streetwisePanel.getHeight());
					survivalPanel.setLocation(0, surveillancePanel.getY()+surveillancePanel.getHeight());
				}
			}
		});
		chckbxSearch.setBackground(new Color(135, 206, 235));
		chckbxSearch.setBounds(187, 5, 97, 23);
		searchPanel.add(chckbxSearch);			
		
		// StreetwiseSpecs
		List<JFormattedTextField> streetwiseSpecsFields = new ArrayList<JFormattedTextField>();
		JCheckBox chckbxStreetwise = new JCheckBox("Show specs");
		chckbxStreetwise.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> streetwiseSpecsList = currentSheet.assignSkillSpecs("Streetwise");
				if(chckbxStreetwise.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "Streetwise", "");
					streetwiseSpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;
					int extra = (streetwiseSpecsList.size())*28;
					
					perceptionPanel.setSize(perceptionPanel.getWidth(), (perceptionPanel.getHeight()+extra));
					streetwisePanel.setSize(354, (32+extra));
					surveillancePanel.setLocation(0, surveillancePanel.getY()+extra);
					survivalPanel.setLocation(0, survivalPanel.getY()+extra);
					for(int i = 0; i < streetwiseSpecsList.size(); i++)
					{
						int j = i;
						streetwiseSpecsFields.add(new JFormattedTextField());
						JFormattedTextField streetwiseSpecs = streetwiseSpecsFields.get(i);
						streetwiseSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						streetwiseSpecs.setBackground(new Color(224, 255, 255));
						streetwiseSpecs.setBounds(5, 33+(i*28), 279, 22);
						streetwisePanel.add(streetwiseSpecs);
						streetwiseSpecs.setText(streetwiseSpecsList.get(i).getDescription());
						streetwiseSpecs.requestFocus();	

						streetwiseSpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = streetwiseSpecsList.get(j);
									editing.setDescription(streetwiseSpecs.getText());
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
										chckbxStreetwise.setSelected(false);
										chckbxStreetwise.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxStreetwise.isSelected())
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

					streetwiseSpecsList.clear();
					for(JFormattedTextField t : streetwiseSpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						streetwisePanel.remove(t);
						t = null;
					}
					streetwiseSpecsFields.clear();
							
					perceptionPanel.setSize(perceptionPanel.getWidth(), (perceptionPanel.getHeight()-(streetwisePanel.getHeight()-32)));
					streetwisePanel.setSize(354, (32));
					surveillancePanel.setLocation(0, streetwisePanel.getY()+streetwisePanel.getHeight());
					survivalPanel.setLocation(0, surveillancePanel.getY()+surveillancePanel.getHeight());
				}
			}
		});
		chckbxStreetwise.setBackground(new Color(0, 191, 255));
		chckbxStreetwise.setBounds(187, 5, 97, 23);
		streetwisePanel.add(chckbxStreetwise);		
		
		// SurveillanceSpecs
		List<JFormattedTextField> surveillanceSpecsFields = new ArrayList<JFormattedTextField>();
		JCheckBox chckbxSurveillance = new JCheckBox("Show specs");
		chckbxSurveillance.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> surveillanceSpecsList = currentSheet.assignSkillSpecs("Surveillance");
				if(chckbxSurveillance.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "Surveillance", "");
					surveillanceSpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;
					int extra = (surveillanceSpecsList.size())*28;
					
					perceptionPanel.setSize(perceptionPanel.getWidth(), (perceptionPanel.getHeight()+extra));
					surveillancePanel.setSize(354, (32+extra));
					survivalPanel.setLocation(0, survivalPanel.getY()+extra);
					for(int i = 0; i < surveillanceSpecsList.size(); i++)
					{
						int j = i;
						surveillanceSpecsFields.add(new JFormattedTextField());
						JFormattedTextField surveillanceSpecs = surveillanceSpecsFields.get(i);
						surveillanceSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						surveillanceSpecs.setBackground(new Color(224, 255, 255));
						surveillanceSpecs.setBounds(5, 33+(i*28), 279, 22);
						surveillancePanel.add(surveillanceSpecs);
						surveillanceSpecs.setText(surveillanceSpecsList.get(i).getDescription());
						surveillanceSpecs.requestFocus();	

						surveillanceSpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = surveillanceSpecsList.get(j);
									editing.setDescription(surveillanceSpecs.getText());
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
										chckbxSurveillance.setSelected(false);
										chckbxSurveillance.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxSurveillance.isSelected())
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

					surveillanceSpecsList.clear();
					for(JFormattedTextField t : surveillanceSpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						surveillancePanel.remove(t);
						t = null;
					}
					surveillanceSpecsFields.clear();
							
					perceptionPanel.setSize(perceptionPanel.getWidth(), (perceptionPanel.getHeight()-(surveillancePanel.getHeight()-32)));
					surveillancePanel.setSize(354, (32));
					survivalPanel.setLocation(0, surveillancePanel.getY()+surveillancePanel.getHeight());
				}
			}
		});
		chckbxSurveillance.setBackground(new Color(135, 206, 235));
		chckbxSurveillance.setBounds(187, 5, 97, 23);
		surveillancePanel.add(chckbxSurveillance);		
		
		// SurvivalSpecs
		List<JFormattedTextField> survivalSpecsFields = new ArrayList<JFormattedTextField>();
		JCheckBox chckbxSurvival = new JCheckBox("Show specs");
		chckbxSurvival.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> survivalSpecsList = currentSheet.assignSkillSpecs("Survival");
				if(chckbxSurvival.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "Survival", "");
					survivalSpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;
					int extra = (survivalSpecsList.size())*28;
					
					perceptionPanel.setSize(perceptionPanel.getWidth(), (perceptionPanel.getHeight()+extra));
					survivalPanel.setSize(354, (32+extra));
					for(int i = 0; i < survivalSpecsList.size(); i++)
					{
						int j = i;
						survivalSpecsFields.add(new JFormattedTextField());
						JFormattedTextField survivalSpecs = survivalSpecsFields.get(i);
						survivalSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						survivalSpecs.setBackground(new Color(224, 255, 255));
						survivalSpecs.setBounds(5, 33+(i*28), 279, 22);
						survivalPanel.add(survivalSpecs);
						survivalSpecs.setText(survivalSpecsList.get(i).getDescription());
						survivalSpecs.requestFocus();	

						survivalSpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = survivalSpecsList.get(j);
									editing.setDescription(survivalSpecs.getText());
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
										chckbxSurvival.setSelected(false);
										chckbxSurvival.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxSurvival.isSelected())
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

					survivalSpecsList.clear();
					for(JFormattedTextField t : survivalSpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						survivalPanel.remove(t);
						t = null;
					}
					survivalSpecsFields.clear();
							
					perceptionPanel.setSize(perceptionPanel.getWidth(), (perceptionPanel.getHeight()-(survivalPanel.getHeight()-32)));
					survivalPanel.setSize(354, (32));
				}
			}
		});
		chckbxSurvival.setBackground(new Color(0, 191, 255));
		chckbxSurvival.setBounds(187, 5, 97, 23);
		survivalPanel.add(chckbxSurvival);			
		
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
		genderField.setText(currentSheet.getGender());
		heightField.setText(currentSheet.getHeight());
		weightField.setText(currentSheet.getWeight());
		hairColorField.setText(currentSheet.getHairColor());
				
		JLabel lblRace = new JLabel("Race");
		lblRace.setBounds(157, 1, 90, 20);
		demographicsPanel.add(lblRace);
				
		raceField = new JFormattedTextField();
		raceField.setBounds(219, 1, 86, 20);
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
		raceField.setText(currentSheet.getRace());
			
		JLabel lblEyeColor = new JLabel("Eye Color");
		lblEyeColor.setBounds(157, 25, 90, 20);
		demographicsPanel.add(lblEyeColor);
				
		JFormattedTextField eyeColorField = new JFormattedTextField();
		eyeColorField.setBounds(219, 25, 86, 20);
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
		eyeColorField.setText(currentSheet.getEyeColor());
				
		JLabel lblFullName = new JLabel("Full Name");
		lblFullName.setBounds(315, 1, 90, 20);
		demographicsPanel.add(lblFullName);
				
		JFormattedTextField fullNameField = new JFormattedTextField();
		fullNameField.setBounds(384, 1, 201, 20);
		demographicsPanel.add(fullNameField);
		fullNameField.setHorizontalAlignment(SwingConstants.RIGHT);
		fullNameField.setColumns(10);
		fullNameField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				currentSheet.setFullName(fullNameField.getText());
			}
		});
		fullNameField.setText(currentSheet.getFullName());
				
		JLabel lblOccupation = new JLabel("Occupation");
		lblOccupation.setBounds(315, 25, 54, 20);
		demographicsPanel.add(lblOccupation);
		
		occupationField = new JFormattedTextField();
		occupationField.setBounds(384, 25, 201, 20);
		demographicsPanel.add(occupationField);
		occupationField.setHorizontalAlignment(SwingConstants.RIGHT);
		occupationField.setColumns(10);
		occupationField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				currentSheet.setOccupation(occupationField.getText());
			}
		});
		occupationField.setText(currentSheet.getOccupation());
		
				
		JLabel lblHomeBase = new JLabel("Home Base");
		lblHomeBase.setBounds(315, 49, 90, 20);
		demographicsPanel.add(lblHomeBase);
				
		baseOfOperationsField = new JFormattedTextField();
		baseOfOperationsField.setBounds(384, 49, 201, 20);
		demographicsPanel.add(baseOfOperationsField);
		baseOfOperationsField.setHorizontalAlignment(SwingConstants.RIGHT);
		baseOfOperationsField.setColumns(10);
		baseOfOperationsField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				currentSheet.setBaseOfOperations(baseOfOperationsField.getText());
			}
		});
		baseOfOperationsField.setText(currentSheet.getBaseOfOperations());
		
		JLabel lblHeroPoints = new JLabel("Hero Points");
		lblHeroPoints.setBounds(605, 1, 90, 20);
		demographicsPanel.add(lblHeroPoints);
		
				
		JLabel lblVillainPoints = new JLabel("Villain Points");
		lblVillainPoints.setBounds(605, 25, 58, 20);
		demographicsPanel.add(lblVillainPoints);
												
		JLabel lblAvailableRenown = new JLabel("Renown");
		lblAvailableRenown.setBounds(605, 49, 90, 20);
		demographicsPanel.add(lblAvailableRenown);
				
		heroPointsField = new JFormattedTextField(nums);
		heroPointsField.setBounds(678, 1, 86, 20);
		demographicsPanel.add(heroPointsField);
		heroPointsField.setHorizontalAlignment(SwingConstants.RIGHT);
		heroPointsField.setColumns(10);
		heroPointsField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				currentSheet.setHeroPoints(Integer.parseInt(heroPointsField.getText()));
			}
		});
		heroPointsField.setValue(currentSheet.getHeroPoints());
		
		villainPointsField = new JFormattedTextField(nums);
		villainPointsField.setBounds(678, 25, 86, 20);
		demographicsPanel.add(villainPointsField);
		villainPointsField.setHorizontalAlignment(SwingConstants.RIGHT);
		villainPointsField.setColumns(10);
		villainPointsField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				currentSheet.setVillainPoints(Integer.parseInt(villainPointsField.getText()));
			}
		});
		villainPointsField.setValue(currentSheet.getVillainPoints());
		
		JFormattedTextField availableRenownField = new JFormattedTextField(nums);
		availableRenownField.setBounds(678, 49, 86, 20);
		demographicsPanel.add(availableRenownField);
		availableRenownField.setHorizontalAlignment(SwingConstants.RIGHT);
		availableRenownField.setColumns(10);
		availableRenownField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				currentSheet.setAvailableRenown(Integer.parseInt(availableRenownField.getText()));
			}
		});
		availableRenownField.setValue(currentSheet.getAvailableRenown());
				
						
		JLabel lblPowerPoints = new JLabel("Power Points");
		lblPowerPoints.setBounds(784, 1, 62, 20);
		demographicsPanel.add(lblPowerPoints);
				
						
		JLabel lblSkillPoints = new JLabel("Skill Points");
		lblSkillPoints.setBounds(784, 25, 90, 20);
		demographicsPanel.add(lblSkillPoints);
						
		powerPointsField = new JFormattedTextField(nums);
		powerPointsField.setBounds(866, 1, 86, 20);
		demographicsPanel.add(powerPointsField);
		powerPointsField.setHorizontalAlignment(SwingConstants.RIGHT);
		powerPointsField.setColumns(10);
		powerPointsField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				currentSheet.setPowerPoints(Integer.parseInt(powerPointsField.getText()));
			}
		});
		powerPointsField.setValue(currentSheet.getPowerPoints());
				
						
		skillPointsField = new JFormattedTextField(nums);
		skillPointsField.setBounds(866, 25, 86, 20);
		demographicsPanel.add(skillPointsField);
		skillPointsField.setHorizontalAlignment(SwingConstants.RIGHT);
		skillPointsField.setColumns(10);
		skillPointsField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				currentSheet.setSkillPoints(Integer.parseInt(skillPointsField.getText()));
			}
		});
		skillPointsField.setValue(currentSheet.getSkillPoints());
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
				
		coordinationLevel.setValue(currentSheet.getCoordination());
		catchLevel.setValue(currentSheet.getCatching());
		climbingLevel.setValue(currentSheet.getClimb());
		drivingLevel.setValue(currentSheet.getDrive());
		marksmanshipLevel.setValue(currentSheet.getMarksmanship());
		thieveryLevel.setValue(currentSheet.getThievery());
		thrownWeaponsLevel.setValue(currentSheet.getThrownWeapons());
		catchTotal.setValue(currentSheet.getCatching() + currentSheet.getCoordination());
		climbingTotal.setValue(currentSheet.getClimb() + currentSheet.getCoordination());
		drivingTotal.setValue(currentSheet.getDrive() + currentSheet.getCoordination());
		marksmanshipTotal.setValue(currentSheet.getMarksmanship() + currentSheet.getCoordination());
		thieveryTotal.setValue(currentSheet.getThievery() + currentSheet.getCoordination());
		thrownWeaponsTotal.setValue(currentSheet.getThrownWeapons() + currentSheet.getCoordination());
				
		physiqueLevel.setValue(currentSheet.getPhysique());
		athleticsLevel.setValue(currentSheet.getAthletics());
		leapLevel.setValue(currentSheet.getLeap());
		liftingLevel.setValue(currentSheet.getLifting());
		resistanceLevel.setValue(currentSheet.getResistance());
		runningLevel.setValue(currentSheet.getRunning());
		swimmingLevel.setValue(currentSheet.getSwimming());
		athleticsTotal.setValue(currentSheet.getAthletics() + currentSheet.getPhysique());
		leapTotal.setValue(currentSheet.getLeap() + currentSheet.getPhysique());
		liftingTotal.setValue(currentSheet.getLifting() + currentSheet.getPhysique());
		resistanceTotal.setValue(currentSheet.getResistance() + currentSheet.getPhysique());
		runningTotal.setValue(currentSheet.getRunning() + currentSheet.getPhysique());
		swimmingTotal.setValue(currentSheet.getSwimming() + currentSheet.getPhysique());
				
		knowledgeLevel.setValue(currentSheet.getKnowledge());
		arcaneLoreLevel.setValue(currentSheet.getArcaneLore());
		demolitionsLevel.setValue(currentSheet.getDemolitions());
		languagesLevel.setValue(currentSheet.getLanguages());
		medicineLevel.setValue(currentSheet.getMedicine());
		scholarLevel.setValue(currentSheet.getScholar());
		scienceLevel.setValue(currentSheet.getScience());
		securityLevel.setValue(currentSheet.getSecurity());
		arcaneLoreTotal.setValue(currentSheet.getArcaneLore() + currentSheet.getKnowledge());
		demolitionsTotal.setValue(currentSheet.getDemolitions() + currentSheet.getKnowledge());
		languagesTotal.setValue(currentSheet.getLanguages() + currentSheet.getKnowledge());
		medicineTotal.setValue(currentSheet.getMedicine() + currentSheet.getKnowledge());
		scholarTotal.setValue(currentSheet.getScholar() + currentSheet.getKnowledge());
		scienceTotal.setValue(currentSheet.getScience() + currentSheet.getKnowledge());
		securityTotal.setValue(currentSheet.getSecurity() + currentSheet.getKnowledge());
				
		perceptionLevel.setValue(currentSheet.getPerception());
		artistLevel.setValue(currentSheet.getArtist());
		engineeringLevel.setValue(currentSheet.getEngineering());
		searchLevel.setValue(currentSheet.getSearch());
		streetwiseLevel.setValue(currentSheet.getStreetwise());
		surveillanceLevel.setValue(currentSheet.getSurveillance());
		survivalLevel.setValue(currentSheet.getSurvival());
		artistTotal.setValue(currentSheet.getArtist() + currentSheet.getPerception());
		engineeringTotal.setValue(currentSheet.getEngineering() + currentSheet.getPerception());
		searchTotal.setValue(currentSheet.getSearch() + currentSheet.getPerception());
		streetwiseTotal.setValue(currentSheet.getStreetwise() + currentSheet.getPerception());
		surveillanceTotal.setValue(currentSheet.getSurveillance() + currentSheet.getPerception());
		survivalTotal.setValue(currentSheet.getSurvival() + currentSheet.getPerception());		
		
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
									
							chckbxCatch.setSelected(false);
							chckbxClimbing.setSelected(false);
							chckbxDriving.setSelected(false);
							chckbxMarksmanship.setSelected(false);
							chckbxThievery.setSelected(false);
							chckbxThrownWeapons.setSelected(false);
									
							chckbxAthletics.setSelected(false);
							chckbxLeap.setSelected(false);
							chckbxLifting.setSelected(false);
							chckbxResistance.setSelected(false);
							chckbxRunning.setSelected(false);
							chckbxSwimming.setSelected(false);
									
							chckbxArcaneLore.setSelected(false);
							chckbxDemolitions.setSelected(false);
							chckbxLanguages.setSelected(false);
							chckbxMedicine.setSelected(false);
							chckbxScholar.setSelected(false);
							chckbxScience.setSelected(false);
							chckbxSecurity.setSelected(false);
							
							chckbxArtist.setSelected(false);
							chckbxEngineering.setSelected(false);
							chckbxSearch.setSelected(false);
							chckbxStreetwise.setSelected(false);
							chckbxSurveillance.setSelected(false);
							chckbxSurvival.setSelected(false);

								
							currentSheet = s;
							// load the entire dang sheet
							nameField.setText(currentSheet.getName());
							fullNameField.setText(currentSheet.getFullName());
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
							coordinationLevel.setValue(currentSheet.getCoordination());
							catchLevel.setValue(currentSheet.getCatching());
							climbingLevel.setValue(currentSheet.getClimb());
							drivingLevel.setValue(currentSheet.getDrive());
							marksmanshipLevel.setValue(currentSheet.getMarksmanship());
							thieveryLevel.setValue(currentSheet.getThievery());
							thrownWeaponsLevel.setValue(currentSheet.getThrownWeapons());
							catchTotal.setValue(currentSheet.getCatching() + currentSheet.getCoordination());
							climbingTotal.setValue(currentSheet.getClimb() + currentSheet.getCoordination());
							drivingTotal.setValue(currentSheet.getDrive() + currentSheet.getCoordination());
							marksmanshipTotal.setValue(currentSheet.getMarksmanship() + currentSheet.getCoordination());
							thieveryTotal.setValue(currentSheet.getThievery() + currentSheet.getCoordination());
							thrownWeaponsTotal.setValue(currentSheet.getThrownWeapons() + currentSheet.getCoordination());
							physiqueLevel.setValue(currentSheet.getPhysique());
							athleticsLevel.setValue(currentSheet.getAthletics());
							leapLevel.setValue(currentSheet.getLeap());
							liftingLevel.setValue(currentSheet.getLifting());
							resistanceLevel.setValue(currentSheet.getResistance());
							runningLevel.setValue(currentSheet.getRunning());
							swimmingLevel.setValue(currentSheet.getSwimming());
							athleticsTotal.setValue(currentSheet.getAthletics() + currentSheet.getPhysique());
							leapTotal.setValue(currentSheet.getLeap() + currentSheet.getPhysique());
							liftingTotal.setValue(currentSheet.getLifting() + currentSheet.getPhysique());
							resistanceTotal.setValue(currentSheet.getResistance() + currentSheet.getPhysique());
							runningTotal.setValue(currentSheet.getRunning() + currentSheet.getPhysique());
							swimmingTotal.setValue(currentSheet.getSwimming() + currentSheet.getPhysique());
							knowledgeLevel.setValue(currentSheet.getKnowledge());
							arcaneLoreLevel.setValue(currentSheet.getArcaneLore());
							demolitionsLevel.setValue(currentSheet.getDemolitions());
							languagesLevel.setValue(currentSheet.getLanguages());
							medicineLevel.setValue(currentSheet.getMedicine());
							scholarLevel.setValue(currentSheet.getScholar());
							scienceLevel.setValue(currentSheet.getScience());
							securityLevel.setValue(currentSheet.getSecurity());
							arcaneLoreTotal.setValue(currentSheet.getArcaneLore() + currentSheet.getKnowledge());
							demolitionsTotal.setValue(currentSheet.getDemolitions() + currentSheet.getKnowledge());
							languagesTotal.setValue(currentSheet.getLanguages() + currentSheet.getKnowledge());
							medicineTotal.setValue(currentSheet.getMedicine() + currentSheet.getKnowledge());
							scholarTotal.setValue(currentSheet.getScholar() + currentSheet.getKnowledge());
							scienceTotal.setValue(currentSheet.getScience() + currentSheet.getKnowledge());
							securityTotal.setValue(currentSheet.getSecurity() + currentSheet.getKnowledge());
							perceptionLevel.setValue(currentSheet.getPerception());
							artistLevel.setValue(currentSheet.getArtist());
							engineeringLevel.setValue(currentSheet.getEngineering());
							searchLevel.setValue(currentSheet.getSearch());
							streetwiseLevel.setValue(currentSheet.getStreetwise());
							surveillanceLevel.setValue(currentSheet.getSurveillance());
							survivalLevel.setValue(currentSheet.getSurvival());
							artistTotal.setValue(currentSheet.getArtist() + currentSheet.getPerception());
							engineeringTotal.setValue(currentSheet.getEngineering() + currentSheet.getPerception());
							searchTotal.setValue(currentSheet.getSearch() + currentSheet.getPerception());
							streetwiseTotal.setValue(currentSheet.getStreetwise() + currentSheet.getPerception());
							surveillanceTotal.setValue(currentSheet.getSurveillance() + currentSheet.getPerception());
							survivalTotal.setValue(currentSheet.getSurvival() + currentSheet.getPerception());	
														
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
