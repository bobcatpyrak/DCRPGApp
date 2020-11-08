package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JList;
import javax.swing.border.BevelBorder;

import business.CharacterSheet;
import business.CharacterSheetAdvantage;
import business.CharacterSheetDisadvantage;
import business.SkillSpec;
import db.TextFile;
import library.Advantage;
import library.Disadvantage;
import javax.swing.UIManager;
import javax.swing.AbstractListModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.Label;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;
import java.awt.TextField;
import javax.swing.JFormattedTextField;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.Format;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class MainWindow {

	private static TextFile dao = new TextFile();
	private static List<CharacterSheet> sheets;
	private static String[] sheetNames;
	private static List<SkillSpec> specs;
	private static List<CharacterSheetAdvantage> advs;
	private static List<CharacterSheetDisadvantage> disadvs;
	private static CharacterSheet currentSheet;
	
	private JFrame dcrpgFrame;
	private final Action action = new SwingAction();
	private JTextField nameSearchField;
	private JTextField nameField;
	private JTextField udoField;
	private JTextField bodyPointsField;
	private JFormattedTextField speedField;
	private JTextField genderField;
	private JTextField raceField;
	private JTextField heightField;
	private JTextField weightField;
	private JTextField eyeColorField;
	private JTextField hairColorField;
	private JTextField heroPointsField;
	private JTextField villainPointsField;
	private JTextField powerPointsField;
	private JTextField skillPointsField;
	private JTextField occupationField;
	private JTextField baseOfOperationsField;

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
		
		int nextSheetId = 0;
		int nextSpecId = 0;
		int nextCSAId = 0;
		int nextCSDId = 0;
		
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
		
		SkillSpec batarang = new SkillSpec(nextSpecId, nextSheetId, "Thrown Weapons", "Batarangs", 3);
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
				// TODO Auto-generated catch block
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
	        if (e.getNewValue() instanceof JTextField)
	        {
	            SwingUtilities.invokeLater(new Runnable()
	            {
	                public void run()
	                {
	                    JTextField textField = (JTextField)e.getNewValue();
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
		
		nameSearchField = new JTextField();
		nameSearchField.setHorizontalAlignment(SwingConstants.RIGHT);
		nameSearchField.setText("(type character name)");
		nameSearchField.setBounds(10, 12, 148, 20);
		dcrpgFrame.getContentPane().add(nameSearchField);
		nameSearchField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setBounds(570, 6, 367, 42);
		dcrpgFrame.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel udoLabel = new JLabel("UDO");
		udoLabel.setBounds(774, 59, 90, 20);
		dcrpgFrame.getContentPane().add(udoLabel);
		
		JLabel bodyPointsLabel = new JLabel("Body Points");
		bodyPointsLabel.setBounds(774, 84, 90, 20);
		dcrpgFrame.getContentPane().add(bodyPointsLabel);
		
		JLabel speedLabel = new JLabel("Speed");
		speedLabel.setBounds(774, 109, 90, 20);
		dcrpgFrame.getContentPane().add(speedLabel);
		
		udoField = new JTextField();
		udoField.setHorizontalAlignment(SwingConstants.RIGHT);
		udoField.setBounds(867, 59, 86, 20);
		dcrpgFrame.getContentPane().add(udoField);
		udoField.setColumns(10);
		
		bodyPointsField = new JTextField();
		bodyPointsField.setHorizontalAlignment(SwingConstants.RIGHT);
		bodyPointsField.setBounds(867, 84, 86, 20);
		dcrpgFrame.getContentPane().add(bodyPointsField);
		bodyPointsField.setColumns(10);
		
		speedField = new JFormattedTextField(nums);
		speedField.setHorizontalAlignment(SwingConstants.RIGHT);
		speedField.setBounds(867, 109, 86, 20);
		dcrpgFrame.getContentPane().add(speedField);
		speedField.setColumns(10);
		
		JButton btnNew = new JButton("New");
		btnNew.setBounds(247, 11, 69, 23);
		dcrpgFrame.getContentPane().add(btnNew);
		
		Panel reflexesPanel = new Panel();
		reflexesPanel.setBackground(new Color(255, 102, 102));
		reflexesPanel.setBounds(44, 149, 354, 470);
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
		acroPanel.setBounds(0, 50, 354, 60);
		reflexesPanel.add(acroPanel);
		acroPanel.setLayout(null);
		
		Label acroLabel = new Label("Acrobatics");
		acroLabel.setBounds(35, 5, 120, 22);
		acroPanel.add(acroLabel);
		acroLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		
		TextField acroSpecs = new TextField();
		acroSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
		acroSpecs.setBackground(new Color(255, 204, 204));
		acroSpecs.setBounds(5, 33, 279, 22);
		acroPanel.add(acroSpecs);
				
		JFormattedTextField acroTotal = new JFormattedTextField();
		acroTotal.setHorizontalAlignment(SwingConstants.CENTER);
		acroTotal.setColumns(2);
		acroTotal.setEditable(false);
		acroTotal.setFont(new Font("Arial", Font.BOLD, 22));
		acroTotal.setBounds(294, 13, 50, 34);
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
		dodgePanel.setBounds(0, 110, 354, 60);
		reflexesPanel.add(dodgePanel);
		
		Label dodgeLabel = new Label("Dodge");
		dodgeLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		dodgeLabel.setBounds(35, 5, 120, 22);
		dodgePanel.add(dodgeLabel);
		
		TextField dodgeSpecs = new TextField();
		dodgeSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
		dodgeSpecs.setBackground(new Color(255, 204, 204));
		dodgeSpecs.setBounds(5, 33, 279, 22);
		dodgePanel.add(dodgeSpecs);
		
		JFormattedTextField dodgeTotal = new JFormattedTextField();
		dodgeTotal.setColumns(2);
		dodgeTotal.setEditable(false);
		dodgeTotal.setHorizontalAlignment(SwingConstants.CENTER);
		dodgeTotal.setFont(new Font("Arial", Font.BOLD, 22));
		dodgeTotal.setBounds(294, 13, 50, 34);
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
		handToHandPanel.setBounds(0, 170, 354, 60);
		reflexesPanel.add(handToHandPanel);
		
		Label handToHandLabel = new Label("Hand-to-Hand");
		handToHandLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		handToHandLabel.setBounds(35, 5, 120, 22);
		handToHandPanel.add(handToHandLabel);
		
		TextField handToHandSpecs = new TextField();
		handToHandSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
		handToHandSpecs.setBackground(new Color(255, 204, 204));
		handToHandSpecs.setBounds(5, 33, 279, 22);
		handToHandPanel.add(handToHandSpecs);
		
		JFormattedTextField handToHandTotal = new JFormattedTextField();
		handToHandTotal.setColumns(2);
		handToHandTotal.setEditable(false);
		handToHandTotal.setFont(new Font("Arial", Font.BOLD, 22));
		handToHandTotal.setHorizontalAlignment(SwingConstants.CENTER);
		handToHandTotal.setBounds(294, 13, 50, 34);
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
		meleeWeaponsPanel.setBounds(0, 230, 354, 60);
		reflexesPanel.add(meleeWeaponsPanel);
		
		Label meleeWeaponsLabel = new Label("Melee Weapons");
		meleeWeaponsLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		meleeWeaponsLabel.setBounds(35, 5, 120, 22);
		meleeWeaponsPanel.add(meleeWeaponsLabel);
		
		TextField meleeWeaponsSpecs = new TextField();
		meleeWeaponsSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
		meleeWeaponsSpecs.setBackground(new Color(255, 204, 204));
		meleeWeaponsSpecs.setBounds(5, 33, 279, 22);
		meleeWeaponsPanel.add(meleeWeaponsSpecs);
		
		JFormattedTextField meleeWeaponsTotal = new JFormattedTextField();
		meleeWeaponsTotal.setColumns(2);
		meleeWeaponsTotal.setEditable(false);
		meleeWeaponsTotal.setHorizontalAlignment(SwingConstants.CENTER);
		meleeWeaponsTotal.setFont(new Font("Arial", Font.BOLD, 22));
		meleeWeaponsTotal.setBounds(294, 13, 50, 34);
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
		stealthPanel.setBounds(0, 290, 354, 60);
		reflexesPanel.add(stealthPanel);
		
		Label stealthLabel = new Label("Stealth");
		stealthLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		stealthLabel.setBounds(35, 5, 120, 22);
		stealthPanel.add(stealthLabel);
		
		TextField stealthSpecs = new TextField();
		stealthSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
		stealthSpecs.setBackground(new Color(255, 204, 204));
		stealthSpecs.setBounds(5, 33, 279, 22);
		stealthPanel.add(stealthSpecs);
		
		JFormattedTextField stealthTotal = new JFormattedTextField();
		stealthTotal.setHorizontalAlignment(SwingConstants.CENTER);
		stealthTotal.setColumns(2);
		stealthTotal.setEditable(false);
		stealthTotal.setFont(new Font("Arial", Font.BOLD, 22));
		stealthTotal.setBounds(294, 13, 50, 34);
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
		placePanel.setBounds(0, 350, 354, 60);
		reflexesPanel.add(placePanel);
		
		Label placeLabel = new Label("Thrown Weapons");
		placeLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		placeLabel.setBounds(35, 5, 120, 22);
		placePanel.add(placeLabel);
		
		TextField placeSpecs = new TextField();
		placeSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
		placeSpecs.setBackground(new Color(255, 204, 204));
		placeSpecs.setBounds(5, 33, 279, 22);
		placePanel.add(placeSpecs);
		
		JFormattedTextField placeTotal = new JFormattedTextField();
		placeTotal.setColumns(2);
		placeTotal.setEditable(false);
		placeTotal.setHorizontalAlignment(SwingConstants.CENTER);
		placeTotal.setFont(new Font("Arial", Font.BOLD, 22));
		placeTotal.setBounds(294, 13, 50, 34);
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
		place2Panel.setBounds(0, 410, 354, 60);
		reflexesPanel.add(place2Panel);
		
		Label place2Label = new Label("Placeholder");
		place2Label.setFont(new Font("Verdana", Font.BOLD, 13));
		place2Label.setBounds(35, 5, 120, 22);
		place2Panel.add(place2Label);
		
		TextField place2Specs = new TextField();
		place2Specs.setFont(new Font("Verdana", Font.PLAIN, 13));
		place2Specs.setBackground(new Color(255, 204, 204));
		place2Specs.setBounds(5, 33, 279, 22);
		place2Panel.add(place2Specs);
		
		JFormattedTextField place2Total = new JFormattedTextField();
		place2Total.setHorizontalAlignment(SwingConstants.CENTER);
		place2Total.setColumns(2);
		place2Total.setEditable(false);
		place2Total.setFont(new Font("Arial", Font.BOLD, 22));
		place2Total.setBounds(294, 13, 50, 34);
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
		
		//load initial sheet
		nameField.setText(currentSheet.getName());

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
		btnLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for(CharacterSheet s : sheets)
				{
					if(s.getName().equals(nameSearchField.getText()))
					{
							currentSheet = s;
							// load the entire dang sheet
							nameField.setText(currentSheet.getName());
							genderField.setText(currentSheet.getGender());
							raceField.setText(currentSheet.getRace());
							heightField.setText(currentSheet.getHeight());
							weightField.setText(currentSheet.getWeight());
							eyeColorField.setText(currentSheet.getEyeColor());
							hairColorField.setText(currentSheet.getHairColor());
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
		
		genderField = new JTextField();
		genderField.setBounds(53, 0, 86, 20);
		demographicsPanel.add(genderField);
		genderField.setText("<dynamic>+0");
		genderField.setHorizontalAlignment(SwingConstants.RIGHT);
		genderField.setColumns(10);
		
		genderField.setText(currentSheet.getGender());
		
		raceField = new JTextField();
		raceField.setBounds(53, 25, 86, 20);
		demographicsPanel.add(raceField);
		raceField.setText("0/0");
		raceField.setHorizontalAlignment(SwingConstants.RIGHT);
		raceField.setColumns(10);
		raceField.setText(currentSheet.getRace());
		
		JFormattedTextField eyeColorField_1 = new JFormattedTextField((Format) null);
		eyeColorField_1.setBounds(53, 50, 86, 20);
		demographicsPanel.add(eyeColorField_1);
		eyeColorField_1.setHorizontalAlignment(SwingConstants.RIGHT);
		eyeColorField_1.setColumns(10);
		eyeColorField_1.setText(currentSheet.getEyeColor());
		
		heightField = new JTextField();
		heightField.setBounds(212, 0, 86, 20);
		demographicsPanel.add(heightField);
		heightField.setText("<dynamic>+0");
		heightField.setHorizontalAlignment(SwingConstants.RIGHT);
		heightField.setColumns(10);
		heightField.setText(currentSheet.getHeight());
		
		weightField = new JTextField();
		weightField.setBounds(212, 25, 86, 20);
		demographicsPanel.add(weightField);
		weightField.setText("0/0");
		weightField.setHorizontalAlignment(SwingConstants.RIGHT);
		weightField.setColumns(10);
		weightField.setText(currentSheet.getWeight());
		
		JFormattedTextField hairColorField_1 = new JFormattedTextField((Format) null);
		hairColorField_1.setBounds(212, 50, 86, 20);
		demographicsPanel.add(hairColorField_1);
		hairColorField_1.setHorizontalAlignment(SwingConstants.RIGHT);
		hairColorField_1.setColumns(10);
		hairColorField_1.setText(currentSheet.getHairColor());
		
		JCheckBox demographicsCheckBox = new JCheckBox("Show Demographics");
		demographicsCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(demographicsCheckBox.isSelected())
					demographicsPanel.setVisible(true);
				else if(!demographicsCheckBox.isSelected())
					demographicsPanel.setVisible(false);
			}
		});
		demographicsCheckBox.setSelected(true);
		demographicsCheckBox.setBounds(38, 39, 136, 23);
		dcrpgFrame.getContentPane().add(demographicsCheckBox);
		
		JLabel lblHeroPoints = new JLabel("Hero Points");
		lblHeroPoints.setBounds(367, 59, 90, 20);
		dcrpgFrame.getContentPane().add(lblHeroPoints);
		
		heroPointsField = new JTextField();
		heroPointsField.setText("<dynamic>+0");
		heroPointsField.setHorizontalAlignment(SwingConstants.RIGHT);
		heroPointsField.setColumns(10);
		heroPointsField.setBounds(460, 59, 86, 20);
		dcrpgFrame.getContentPane().add(heroPointsField);
		
		JLabel lblVillainPoints = new JLabel("Villain Points");
		lblVillainPoints.setBounds(367, 84, 90, 20);
		dcrpgFrame.getContentPane().add(lblVillainPoints);
		
		villainPointsField = new JTextField();
		villainPointsField.setText("0/0");
		villainPointsField.setHorizontalAlignment(SwingConstants.RIGHT);
		villainPointsField.setColumns(10);
		villainPointsField.setBounds(460, 84, 86, 20);
		dcrpgFrame.getContentPane().add(villainPointsField);
		
		JLabel lblAvailableRenown = new JLabel("Available Renown");
		lblAvailableRenown.setBounds(367, 109, 90, 20);
		dcrpgFrame.getContentPane().add(lblAvailableRenown);
		
		JFormattedTextField availableRenownField = new JFormattedTextField((Format) null);
		availableRenownField.setHorizontalAlignment(SwingConstants.RIGHT);
		availableRenownField.setColumns(10);
		availableRenownField.setBounds(460, 109, 86, 20);
		dcrpgFrame.getContentPane().add(availableRenownField);
		
		JLabel lblPowerPoints = new JLabel("Power Points");
		lblPowerPoints.setBounds(570, 59, 90, 20);
		dcrpgFrame.getContentPane().add(lblPowerPoints);
		
		powerPointsField = new JTextField();
		powerPointsField.setText("<dynamic>+0");
		powerPointsField.setHorizontalAlignment(SwingConstants.RIGHT);
		powerPointsField.setColumns(10);
		powerPointsField.setBounds(663, 59, 86, 20);
		dcrpgFrame.getContentPane().add(powerPointsField);
		
		JLabel lblSkillPoints = new JLabel("Skill Points");
		lblSkillPoints.setBounds(570, 84, 90, 20);
		dcrpgFrame.getContentPane().add(lblSkillPoints);
		
		skillPointsField = new JTextField();
		skillPointsField.setText("0/0");
		skillPointsField.setHorizontalAlignment(SwingConstants.RIGHT);
		skillPointsField.setColumns(10);
		skillPointsField.setBounds(663, 84, 86, 20);
		dcrpgFrame.getContentPane().add(skillPointsField);
		
		JLabel lblOccupation = new JLabel("Occupation");
		lblOccupation.setBounds(947, 6, 90, 20);
		dcrpgFrame.getContentPane().add(lblOccupation);
		
		occupationField = new JTextField();
		occupationField.setText("<dynamic>+0");
		occupationField.setHorizontalAlignment(SwingConstants.RIGHT);
		occupationField.setColumns(10);
		occupationField.setBounds(1040, 6, 86, 20);
		dcrpgFrame.getContentPane().add(occupationField);
		
		JLabel lblHomeBase = new JLabel("Home Base");
		lblHomeBase.setBounds(947, 31, 90, 20);
		dcrpgFrame.getContentPane().add(lblHomeBase);
		
		baseOfOperationsField = new JTextField();
		baseOfOperationsField.setText("0/0");
		baseOfOperationsField.setHorizontalAlignment(SwingConstants.RIGHT);
		baseOfOperationsField.setColumns(10);
		baseOfOperationsField.setBounds(1040, 31, 86, 20);
		dcrpgFrame.getContentPane().add(baseOfOperationsField);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(329, 11, 69, 23);
		dcrpgFrame.getContentPane().add(btnSave);

	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
