			//	todo - finish equipment tab, then spells, then rolling


package gui;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import business.*;
import db.*;
import library.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainWindow {

	public static TextFile dao = new TextFile();
	private static List<CharacterSheet> sheets;
	private static List<String> searchList = new ArrayList<String>();
	private static List<SkillSpec> specs;
	private static List<CharacterSheetAdvantage> advs;
	private static List<CharacterSheetDisadvantage> disadvs;
	private static List<CharacterSheetPower> powers;
	public static List<Inventory> invs;
	public static List<Item> items;
	public static List<Spell> spells;
	public static List<SpellInventory> spellInvs;
	private static CharacterSheet currentSheet;
	
	public static int nextSheetId;
	private static int nextSpecId;
	public static int nextCSAId;
	public static int nextCSDId;
	public static int nextCSPId;
	public static int nextItemId;
	public static int nextSpellId;
	
	public static Item newItem;
	public static Spell draggedSpell;
	
	public static JFrame dcrpgFrame;
	public static JPanel panel;
	public static JTabbedPane tabbedPane;
	private JFormattedTextField nameSearchField;
	private JFormattedTextField nameField;
	private JFormattedTextField udoField;
	private JFormattedTextField bodyPointsField;
	private JFormattedTextField speedField;
	private DemographicsPanel demographicsPanel;
	private JPanel mentalStatsPanel;
	private JPanel physStatsPanel;
	public static JPanel advPowerPanel;
	private BufferedImage img;
	private BufferedImage origImg;
	private ImageIcon icon;
	private AdvantagePanel advantagePanel;
	private PowerPanel powerPanel;
	
	private boolean imgChange = true;
	boolean isNew = false;
	boolean newPic = false;

	

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
		powers = dao.getAllCSP();
		invs = dao.getAllInv();
		items = dao.getAllItems();
		spells = dao.getAllSpells();
		spellInvs = dao.getAllSpellInv();
		
		nextSheetId = 0;
		nextSpecId = 0;
		nextCSAId = 0;
		nextCSDId = 0;
		nextCSPId = 0;
		nextItemId = 0;
		nextSpellId = 0;
		
		if(sheets.size() > 0)
			nextSheetId = sheets.get(sheets.size()-1).getId() + 1;
		if(specs.size() > 0)
			nextSpecId = specs.get(specs.size()-1).getId() + 1;
		if(advs.size() > 0)
			nextCSAId = advs.get(advs.size()-1).getId() + 1;
		if(disadvs.size() > 0)
			nextCSDId = disadvs.get(disadvs.size()-1).getId() + 1;
		if(powers.size() > 0)
			nextCSPId = powers.get(powers.size()-1).getId() + 1;
		if(items.size() > 0)
			nextItemId = items.get(items.size()-1).getId() + 1;
		if(spells.size() > 0)
			nextSpellId = spells.get(spells.size()-1).getId() + 1;
		
		currentSheet = sheets.get(0);
		
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
		// Creates a folder for images if doesn't exist
		String imgString = "images";
		Path imgPath = Paths.get(imgString);
		if (Files.notExists(imgPath))
			try 
			{
				Files.createDirectories(imgPath);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		// Creates a folder for character images if doesn't exist
		String imgSheetString = "images/sheets";
		Path imgSheetPath = Paths.get(imgSheetString);
		if (Files.notExists(imgSheetPath))
			try 
			{
				Files.createDirectories(imgSheetPath);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		// Creates a folder for item images if doesn't exist
		String imgItemString = "images/items";
		Path imgItemPath = Paths.get(imgItemString);
		if (Files.notExists(imgItemPath))
			try 
			{
				Files.createDirectories(imgItemPath);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		// Creates a folder for spell images if doesn't exist
		String imgSpellString = "images/spells";
		Path imgSpellPath = Paths.get(imgSpellString);
		if (Files.notExists(imgSpellPath))
			try 
			{
				Files.createDirectories(imgSpellPath);
			} 
			catch (IOException e) 
			{
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
		dcrpgFrame.setLocation(0, 0);
		dcrpgFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		dcrpgFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		// Prevents negatives or fractions being put into stat inputs
		//NumberFormat nums = NumberFormat.getIntegerInstance();
		DecimalFormat nums = new DecimalFormat("#,##0; #");
		
		
		// AcroSpecs
		List<JFormattedTextField> acroSpecsFields = new ArrayList<JFormattedTextField>();
		// DodgeSpecs
		List<JFormattedTextField> dodgeSpecsFields = new ArrayList<JFormattedTextField>();
		// HandToHandSpecs
		List<JFormattedTextField> handToHandSpecsFields = new ArrayList<JFormattedTextField>();
		// MeleeWeaponsSpecs
		List<JFormattedTextField> meleeWeaponsSpecsFields = new ArrayList<JFormattedTextField>();
		// StealthSpecs
		List<JFormattedTextField> stealthSpecsFields = new ArrayList<JFormattedTextField>();
		// CatchSpecs
		List<JFormattedTextField> catchSpecsFields = new ArrayList<JFormattedTextField>();		
		// ClimbingSpecs
		List<JFormattedTextField> climbingSpecsFields = new ArrayList<JFormattedTextField>();		
		// DrivingSpecs
		List<JFormattedTextField> drivingSpecsFields = new ArrayList<JFormattedTextField>();		
		// MarksmanshipSpecs
		List<JFormattedTextField> marksmanshipSpecsFields = new ArrayList<JFormattedTextField>();		
		// ThieverySpecs
		List<JFormattedTextField> thieverySpecsFields = new ArrayList<JFormattedTextField>();		
		// ThrownWeaponsSpecs
		List<JFormattedTextField> thrownWeaponsSpecsFields = new ArrayList<JFormattedTextField>();
		// AthleticsSpecs
		List<JFormattedTextField> athleticsSpecsFields = new ArrayList<JFormattedTextField>();		
		// LeapSpecs
		List<JFormattedTextField> leapSpecsFields = new ArrayList<JFormattedTextField>();		
		// LiftingSpecs
		List<JFormattedTextField> liftingSpecsFields = new ArrayList<JFormattedTextField>();		
		// ResistanceSpecs
		List<JFormattedTextField> resistanceSpecsFields = new ArrayList<JFormattedTextField>();		
		// RunningSpecs
		List<JFormattedTextField> runningSpecsFields = new ArrayList<JFormattedTextField>();		
		// SwimmingSpecs
		List<JFormattedTextField> swimmingSpecsFields = new ArrayList<JFormattedTextField>();
		// ArcaneLoreSpecs
		List<JFormattedTextField> arcaneLoreSpecsFields = new ArrayList<JFormattedTextField>();		
		// DemolitionsSpecs
		List<JFormattedTextField> demolitionsSpecsFields = new ArrayList<JFormattedTextField>();		
		// LanguagesSpecs
		List<JFormattedTextField> languagesSpecsFields = new ArrayList<JFormattedTextField>();		
		// MedicineSpecs
		List<JFormattedTextField> medicineSpecsFields = new ArrayList<JFormattedTextField>();		
		// ScholarSpecs
		List<JFormattedTextField> scholarSpecsFields = new ArrayList<JFormattedTextField>();		
		// ScienceSpecs
		List<JFormattedTextField> scienceSpecsFields = new ArrayList<JFormattedTextField>();		
		// SecuritySpecs
		List<JFormattedTextField> securitySpecsFields = new ArrayList<JFormattedTextField>();
		// ArtistSpecs
		List<JFormattedTextField> artistSpecsFields = new ArrayList<JFormattedTextField>();		
		// EngineeringSpecs
		List<JFormattedTextField> engineeringSpecsFields = new ArrayList<JFormattedTextField>();		
		// SearchSpecs
		List<JFormattedTextField> searchSpecsFields = new ArrayList<JFormattedTextField>();		
		// StreetwiseSpecs
		List<JFormattedTextField> streetwiseSpecsFields = new ArrayList<JFormattedTextField>();		
		// SurveillanceSpecs
		List<JFormattedTextField> surveillanceSpecsFields = new ArrayList<JFormattedTextField>();		
		// SurvivalSpecs
		List<JFormattedTextField> survivalSpecsFields = new ArrayList<JFormattedTextField>();
		// BluffSpecs
		List<JFormattedTextField> bluffSpecsFields = new ArrayList<JFormattedTextField>();		
		// CharmSpecs
		List<JFormattedTextField> charmSpecsFields = new ArrayList<JFormattedTextField>();		
		// IntimidationSpecs
		List<JFormattedTextField> intimidationSpecsFields = new ArrayList<JFormattedTextField>();		
		// PersuasionSpecs
		List<JFormattedTextField> persuasionSpecsFields = new ArrayList<JFormattedTextField>();		
		// WillpowerSpecs
		List<JFormattedTextField> willpowerSpecsFields = new ArrayList<JFormattedTextField>();
		
		
		dcrpgFrame.getContentPane().setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, dcrpgFrame.getWidth()-18, dcrpgFrame.getHeight()-41);
		dcrpgFrame.getContentPane().add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(tabbedPane.getSize());
		tabbedPane.addTab("Character Sheet", null, scrollPane, null);

		dcrpgFrame.getContentPane().setSize(dcrpgFrame.getSize());
		
		EquipmentTab etab = new EquipmentTab(currentSheet);
		tabbedPane.addTab("Equipment", null, etab, null);
		
		SpellTab stab = new SpellTab(currentSheet);
		tabbedPane.addTab("Spells", null, stab, null);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(1884, 1035));
		
		dcrpgFrame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	tabbedPane.setSize(dcrpgFrame.getWidth()-18, dcrpgFrame.getHeight()-41);
        		scrollPane.setSize(dcrpgFrame.getWidth()-18, dcrpgFrame.getHeight()-41);
        		etab.setSize(dcrpgFrame.getWidth()-18, dcrpgFrame.getHeight()-41);
        		stab.setSize(dcrpgFrame.getWidth()-18, dcrpgFrame.getHeight()-41);
            }
        });
		
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setBounds(468, 0, 107, 42);
		panel.add(nameLabel);
		nameLabel.setFont(new Font("Dialog", Font.PLAIN, 40));
		
		nameSearchField = new JFormattedTextField();
		nameSearchField.setBounds(10, 10, 148, 23);
		panel.add(nameSearchField);
		nameSearchField.setHorizontalAlignment(SwingConstants.RIGHT);
		nameSearchField.setText("(type character name)");
		nameSearchField.setColumns(10);
		
		JPanel searches = new JPanel();
		searches.setLayout(null);
		searches.setBounds(nameSearchField.getX(), nameSearchField.getY()+nameSearchField.getHeight(), nameSearchField.getWidth()+50, 100);
		JList searchesList = new JList();
		searchesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jsp = new JScrollPane(searchesList);
		jsp.setBounds(0, 0, searches.getWidth(), searches.getHeight());
		searches.add(jsp);
		panel.add(searches);
		searches.setVisible(false);
		
		nameField = new JFormattedTextField();
		nameField.setBounds(585, 0, 367, 42);
		panel.add(nameField);
		nameField.setFont(new Font("Dialog", Font.PLAIN, 24));
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setColumns(10);
		nameField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				currentSheet.setName(nameField.getText());
			}
		});
		
		
		nameField.setText(currentSheet.getName());
		
		JLabel udoLabel = new JLabel("UDO");
		udoLabel.setBounds(1112, 0, 60, 20);
		panel.add(udoLabel);
		
		JLabel locationLabel = new JLabel("Location");
		locationLabel.setBounds(1112, 23, 60, 20);
		panel.add(locationLabel);
		
		JLabel bodyPointsLabel = new JLabel(" Body Points");
		bodyPointsLabel.setBounds(962, 0, 75, 20);
		panel.add(bodyPointsLabel);
		
		JLabel speedLabel = new JLabel("Speed");
		speedLabel.setBounds(962, 23, 75, 20);
		panel.add(speedLabel);
		
		demographicsPanel = new DemographicsPanel(currentSheet);
		panel.add(demographicsPanel);	
		
		udoField = new JFormattedTextField();
		udoField.setBounds(1172, 0, 110, 20);
		panel.add(udoField);
		udoField.setHorizontalAlignment(SwingConstants.LEFT);
		udoField.setColumns(10);
		udoField.addKeyListener(new KeyAdapter()
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
		udoField.setText(currentSheet.getUdoDice() + "+" + currentSheet.getUdoBonus());
		
		JFormattedTextField locationField = new JFormattedTextField();
		locationField.setBounds(1172, 23, 110, 20);
		panel.add(locationField);
		locationField.setHorizontalAlignment(SwingConstants.LEFT);
		locationField.setColumns(10);
		locationField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				currentSheet.setLocation(locationField.getText());
			}
		});
		locationField.setValue(currentSheet.getLocation());	
		
		bodyPointsField = new JFormattedTextField();
		bodyPointsField.setBounds(1042, 0, 60, 20);
		panel.add(bodyPointsField);
		bodyPointsField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		bodyPointsField.setHorizontalAlignment(SwingConstants.LEFT);
		bodyPointsField.setColumns(10);
		bodyPointsField.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				if(bodyPointsField.getText().contains("/"))
				{
					String[] bps = bodyPointsField.getText().split("/");
					if(bps.length > 0)
						currentSheet.setBodyPointsCurrent(Integer.parseInt(bps[0]));
					if(bps.length > 1)
						currentSheet.setBodyPointsMax(Integer.parseInt(bps[1]));
				}
			}
		});
		bodyPointsField.setText("100/100");
			
					
		speedField = new JFormattedTextField();
		speedField.setBounds(1042, 23, 60, 20);
		panel.add(speedField);
		speedField.setHorizontalAlignment(SwingConstants.LEFT);
		speedField.setColumns(10);
		speedField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				currentSheet.setSpeed(speedField.getText());
			}
		});
		speedField.setValue(currentSheet.getSpeed());			
					
							
		JButton btnNew = new JButton("New");
		btnNew.setBounds(243, 10, 65, 23);
		panel.add(btnNew);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.setBounds(168, 10, 65, 23);
		panel.add(btnLoad);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(318, 10, 65, 23);
		panel.add(btnSave);
		
	    JButton btnCopy = new JButton("Copy");
	    btnCopy.setBounds(393, 10, 65, 23);
	    panel.add(btnCopy);
							
		physStatsPanel = new JPanel();
		physStatsPanel.setBounds(35, 154, 1152, 247);
		panel.add(physStatsPanel);
		physStatsPanel.setBackground(new Color(192, 192, 192));
		physStatsPanel.setLayout(null);

		Panel reflexesPanel = new Panel();
		reflexesPanel.setBounds(0, 0, 364, 247);
		physStatsPanel.add(reflexesPanel);
		reflexesPanel.setBackground(new Color(255, 51, 51));
		reflexesPanel.setLayout(null);

		Label reflexesLabel = new Label("Reflexes");
		reflexesLabel.setFont(new Font("Verdana", Font.BOLD, 22));
		reflexesLabel.setBounds(132, 10, 90, 30);
		reflexesPanel.add(reflexesLabel);

		JFormattedTextField reflexesLevel = new JFormattedTextField(nums);
		reflexesLevel.setColumns(2);
		reflexesLevel.setFont(new Font("Arial", Font.BOLD, 22));
		reflexesLevel.setHorizontalAlignment(SwingConstants.CENTER);
		reflexesLevel.setBounds(299, 10, 50, 35);
		reflexesPanel.add(reflexesLevel);

		Panel acroPanel = new Panel();
		acroPanel.setBackground(new Color(255, 153, 153));
		acroPanel.setBounds(5, 50, 354, 32);
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
		dodgePanel.setBounds(5, 82, 354, 32);
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
		handToHandPanel.setBounds(5, 114, 354, 32);
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
		meleeWeaponsPanel.setBounds(5, 146, 354, 32);
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
		stealthPanel.setBounds(5, 178, 354, 32);
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

					currentSheet.setReflexes(i);
					acroTotal.setValue(i+j);
					dodgeTotal.setValue(i+k);
					handToHandTotal.setValue(i+l);	
					meleeWeaponsTotal.setValue(i+m);
					stealthTotal.setValue(i+n);
				}
			}
		});


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
		
		Panel coordinationPanel = new Panel();
		coordinationPanel.setBounds(394, 0, 364, 247);
		physStatsPanel.add(coordinationPanel);
		coordinationPanel.setLayout(null);
		coordinationPanel.setBackground(new Color(255, 133, 0));
		
		Label coordinationLabel = new Label("Coordination");
		coordinationLabel.setFont(new Font("Verdana", Font.BOLD, 22));
		coordinationLabel.setBounds(110, 10, 135, 30);
		coordinationPanel.add(coordinationLabel);
		
		JFormattedTextField coordinationLevel = new JFormattedTextField(nums);
		coordinationLevel.setHorizontalAlignment(SwingConstants.CENTER);
		coordinationLevel.setFont(new Font("Arial", Font.BOLD, 22));
		coordinationLevel.setColumns(2);
		coordinationLevel.setBounds(299, 10, 50, 35);
		coordinationPanel.add(coordinationLevel);
		
		Panel catchPanel = new Panel();
		catchPanel.setLayout(null);
		catchPanel.setBackground(new Color(255, 201, 131));
		catchPanel.setBounds(5, 50, 354, 32);
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
		climbingPanel.setBounds(5, 82, 354, 32);
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
		drivingPanel.setBounds(5, 114, 354, 32);
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
		marksmanshipPanel.setBounds(5, 146, 354, 32);
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
		thieveryPanel.setBounds(5, 178, 354, 32);
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
		thrownWeaponsPanel.setBounds(5, 210, 354, 32);
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
		
		Panel physiquePanel = new Panel();
		physiquePanel.setBounds(788, 0, 364, 247);
		physStatsPanel.add(physiquePanel);
		physiquePanel.setLayout(null);
		physiquePanel.setBackground(new Color(247, 247, 0));
		
		Label physiqueLabel = new Label("Physique");
		physiqueLabel.setFont(new Font("Verdana", Font.BOLD, 22));
		physiqueLabel.setBounds(110, 10, 135, 30);
		physiquePanel.add(physiqueLabel);
		
		JFormattedTextField physiqueLevel = new JFormattedTextField(nums);
		physiqueLevel.setHorizontalAlignment(SwingConstants.CENTER);
		physiqueLevel.setFont(new Font("Arial", Font.BOLD, 22));
		physiqueLevel.setColumns(2);
		physiqueLevel.setBounds(299, 10, 50, 35);
		physiquePanel.add(physiqueLevel);
		
		Panel athleticsPanel = new Panel();
		athleticsPanel.setLayout(null);
		athleticsPanel.setBackground(new Color(240, 230, 140));
		athleticsPanel.setBounds(5, 50, 354, 32);
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
		leapPanel.setBounds(5, 82, 354, 32);
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
		liftingPanel.setBounds(5, 114, 354, 32);
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
		resistancePanel.setBounds(5, 146, 354, 32);
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
		runningPanel.setBounds(5, 178, 354, 32);
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
		swimmingPanel.setBounds(5, 210, 354, 32);
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
					dodgePanel.setLocation(5, dodgePanel.getY()+extra);
					handToHandPanel.setLocation(5, handToHandPanel.getY()+extra);
					meleeWeaponsPanel.setLocation(5, meleeWeaponsPanel.getY()+extra);
					stealthPanel.setLocation(5, stealthPanel.getY()+extra);
					physStatsPanel.setSize(physStatsPanel.getWidth(), setPanelSize(reflexesPanel.getHeight(), coordinationPanel.getHeight(), physiquePanel.getHeight()));
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), physStatsPanel.getY()+physStatsPanel.getHeight()+30);	
					advPowerPanel.setLocation(advPowerPanel.getX(), mentalStatsPanel.getY()+mentalStatsPanel.getHeight()+30);
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
					
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
					dodgePanel.setLocation(5, acroPanel.getY()+32);
					handToHandPanel.setLocation(5, dodgePanel.getY()+dodgePanel.getHeight());
					meleeWeaponsPanel.setLocation(5, handToHandPanel.getY()+handToHandPanel.getHeight());
					stealthPanel.setLocation(5, meleeWeaponsPanel.getY()+meleeWeaponsPanel.getHeight());
					physStatsPanel.setSize(physStatsPanel.getWidth(), setPanelSize(reflexesPanel.getHeight(), coordinationPanel.getHeight(), physiquePanel.getHeight()));
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), physStatsPanel.getY()+physStatsPanel.getHeight()+30);
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxAcro.setBackground(new Color(255, 153, 153));
		chckbxAcro.setBounds(187, 5, 97, 23);
		acroPanel.add(chckbxAcro);
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
					handToHandPanel.setLocation(5, handToHandPanel.getY()+extra);
					meleeWeaponsPanel.setLocation(5, meleeWeaponsPanel.getY()+extra);
					stealthPanel.setLocation(5, stealthPanel.getY()+extra);
					physStatsPanel.setSize(physStatsPanel.getWidth(), setPanelSize(reflexesPanel.getHeight(), coordinationPanel.getHeight(), physiquePanel.getHeight()));
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), physStatsPanel.getY()+physStatsPanel.getHeight()+30);
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();

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
					handToHandPanel.setLocation(5, dodgePanel.getY()+dodgePanel.getHeight());
					meleeWeaponsPanel.setLocation(5, handToHandPanel.getY()+handToHandPanel.getHeight());
					stealthPanel.setLocation(5, meleeWeaponsPanel.getY()+meleeWeaponsPanel.getHeight());
					physStatsPanel.setSize(physStatsPanel.getWidth(), setPanelSize(reflexesPanel.getHeight(), coordinationPanel.getHeight(), physiquePanel.getHeight()));
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), physStatsPanel.getY()+physStatsPanel.getHeight()+30);
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxDodge.setBackground(new Color(255, 102, 102));
		chckbxDodge.setBounds(187, 5, 97, 23);
		dodgePanel.add(chckbxDodge);
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
					meleeWeaponsPanel.setLocation(5, meleeWeaponsPanel.getY()+extra);
					stealthPanel.setLocation(5, stealthPanel.getY()+extra);
					physStatsPanel.setSize(physStatsPanel.getWidth(), setPanelSize(reflexesPanel.getHeight(), coordinationPanel.getHeight(), physiquePanel.getHeight()));
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), physStatsPanel.getY()+physStatsPanel.getHeight()+30);
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();

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
					meleeWeaponsPanel.setLocation(5, handToHandPanel.getY()+handToHandPanel.getHeight());
					stealthPanel.setLocation(5, meleeWeaponsPanel.getY()+meleeWeaponsPanel.getHeight());
					physStatsPanel.setSize(physStatsPanel.getWidth(), setPanelSize(reflexesPanel.getHeight(), coordinationPanel.getHeight(), physiquePanel.getHeight()));
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), physStatsPanel.getY()+physStatsPanel.getHeight()+30);
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxHandToHand.setBackground(new Color(255, 153, 153));
		chckbxHandToHand.setBounds(187, 5, 97, 23);
		handToHandPanel.add(chckbxHandToHand);
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
					stealthPanel.setLocation(5, stealthPanel.getY()+extra);
					physStatsPanel.setSize(physStatsPanel.getWidth(), setPanelSize(reflexesPanel.getHeight(), coordinationPanel.getHeight(), physiquePanel.getHeight()));
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), physStatsPanel.getY()+physStatsPanel.getHeight()+30);
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();

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
					stealthPanel.setLocation(5, meleeWeaponsPanel.getY()+meleeWeaponsPanel.getHeight());
					physStatsPanel.setSize(physStatsPanel.getWidth(), setPanelSize(reflexesPanel.getHeight(), coordinationPanel.getHeight(), physiquePanel.getHeight()));
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), physStatsPanel.getY()+physStatsPanel.getHeight()+30);
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxMeleeWeapons.setBackground(new Color(255, 102, 102));
		chckbxMeleeWeapons.setBounds(187, 5, 97, 23);
		meleeWeaponsPanel.add(chckbxMeleeWeapons);
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
					physStatsPanel.setSize(physStatsPanel.getWidth(), setPanelSize(reflexesPanel.getHeight(), coordinationPanel.getHeight(), physiquePanel.getHeight()));
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), physStatsPanel.getY()+physStatsPanel.getHeight()+30);
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
					
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
					physStatsPanel.setSize(physStatsPanel.getWidth(), setPanelSize(reflexesPanel.getHeight(), coordinationPanel.getHeight(), physiquePanel.getHeight()));
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), physStatsPanel.getY()+physStatsPanel.getHeight()+30);
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxStealth.setBackground(new Color(255, 153, 153));
		chckbxStealth.setBounds(187, 5, 97, 23);
		stealthPanel.add(chckbxStealth);
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
					climbingPanel.setLocation(5, climbingPanel.getY()+extra);
					drivingPanel.setLocation(5, drivingPanel.getY()+extra);
					marksmanshipPanel.setLocation(5, marksmanshipPanel.getY()+extra);
					thieveryPanel.setLocation(5, thieveryPanel.getY()+extra);
					thrownWeaponsPanel.setLocation(5, thrownWeaponsPanel.getY()+extra);
					physStatsPanel.setSize(physStatsPanel.getWidth(), setPanelSize(reflexesPanel.getHeight(), coordinationPanel.getHeight(), physiquePanel.getHeight()));
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), physStatsPanel.getY()+physStatsPanel.getHeight()+30);
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
					
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
					climbingPanel.setLocation(5, catchPanel.getY()+32);
					drivingPanel.setLocation(5, climbingPanel.getY()+climbingPanel.getHeight());
					marksmanshipPanel.setLocation(5, drivingPanel.getY()+drivingPanel.getHeight());
					thieveryPanel.setLocation(5, marksmanshipPanel.getY()+marksmanshipPanel.getHeight());
					thrownWeaponsPanel.setLocation(5, thieveryPanel.getY()+thieveryPanel.getHeight());
					physStatsPanel.setSize(physStatsPanel.getWidth(), setPanelSize(reflexesPanel.getHeight(), coordinationPanel.getHeight(), physiquePanel.getHeight()));
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), physStatsPanel.getY()+physStatsPanel.getHeight()+30);
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxCatch.setBackground(new Color(255, 201, 131));
		chckbxCatch.setBounds(187, 5, 97, 23);
		catchPanel.add(chckbxCatch);
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
					drivingPanel.setLocation(5, drivingPanel.getY()+extra);
					marksmanshipPanel.setLocation(5, marksmanshipPanel.getY()+extra);
					thieveryPanel.setLocation(5, thieveryPanel.getY()+extra);
					thrownWeaponsPanel.setLocation(5, thrownWeaponsPanel.getY()+extra);
					physStatsPanel.setSize(physStatsPanel.getWidth(), setPanelSize(reflexesPanel.getHeight(), coordinationPanel.getHeight(), physiquePanel.getHeight()));
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), physStatsPanel.getY()+physStatsPanel.getHeight()+30);
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
					
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
					drivingPanel.setLocation(5, climbingPanel.getY()+climbingPanel.getHeight());
					marksmanshipPanel.setLocation(5, drivingPanel.getY()+drivingPanel.getHeight());
					thieveryPanel.setLocation(5, marksmanshipPanel.getY()+marksmanshipPanel.getHeight());
					thrownWeaponsPanel.setLocation(5, thieveryPanel.getY()+thieveryPanel.getHeight());
					physStatsPanel.setSize(physStatsPanel.getWidth(), setPanelSize(reflexesPanel.getHeight(), coordinationPanel.getHeight(), physiquePanel.getHeight()));
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), physStatsPanel.getY()+physStatsPanel.getHeight()+30);
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxClimbing.setBackground(new Color(255, 173, 84));
		chckbxClimbing.setBounds(187, 5, 97, 23);
		climbingPanel.add(chckbxClimbing);		
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
					marksmanshipPanel.setLocation(5, marksmanshipPanel.getY()+extra);
					thieveryPanel.setLocation(5, thieveryPanel.getY()+extra);
					thrownWeaponsPanel.setLocation(5, thrownWeaponsPanel.getY()+extra);
					physStatsPanel.setSize(physStatsPanel.getWidth(), setPanelSize(reflexesPanel.getHeight(), coordinationPanel.getHeight(), physiquePanel.getHeight()));
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), physStatsPanel.getY()+physStatsPanel.getHeight()+30);
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
					
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
					marksmanshipPanel.setLocation(5, drivingPanel.getY()+drivingPanel.getHeight());
					thieveryPanel.setLocation(5, marksmanshipPanel.getY()+marksmanshipPanel.getHeight());
					thrownWeaponsPanel.setLocation(5, thieveryPanel.getY()+thieveryPanel.getHeight());
					physStatsPanel.setSize(physStatsPanel.getWidth(), setPanelSize(reflexesPanel.getHeight(), coordinationPanel.getHeight(), physiquePanel.getHeight()));
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), physStatsPanel.getY()+physStatsPanel.getHeight()+30);
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxDriving.setBackground(new Color(255, 201, 131));
		chckbxDriving.setBounds(187, 5, 97, 23);
		drivingPanel.add(chckbxDriving);		
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
					thieveryPanel.setLocation(5, thieveryPanel.getY()+extra);
					thrownWeaponsPanel.setLocation(5, thrownWeaponsPanel.getY()+extra);
					physStatsPanel.setSize(physStatsPanel.getWidth(), setPanelSize(reflexesPanel.getHeight(), coordinationPanel.getHeight(), physiquePanel.getHeight()));
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), physStatsPanel.getY()+physStatsPanel.getHeight()+30);
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
					
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
					thieveryPanel.setLocation(5, marksmanshipPanel.getY()+marksmanshipPanel.getHeight());
					thrownWeaponsPanel.setLocation(5, thieveryPanel.getY()+thieveryPanel.getHeight());
					physStatsPanel.setSize(physStatsPanel.getWidth(), setPanelSize(reflexesPanel.getHeight(), coordinationPanel.getHeight(), physiquePanel.getHeight()));
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), physStatsPanel.getY()+physStatsPanel.getHeight()+30);
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxMarksmanship.setBackground(new Color(255, 173, 84));
		chckbxMarksmanship.setBounds(187, 5, 97, 23);
		marksmanshipPanel.add(chckbxMarksmanship);
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
					thrownWeaponsPanel.setLocation(5, thrownWeaponsPanel.getY()+extra);
					physStatsPanel.setSize(physStatsPanel.getWidth(), setPanelSize(reflexesPanel.getHeight(), coordinationPanel.getHeight(), physiquePanel.getHeight()));
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), physStatsPanel.getY()+physStatsPanel.getHeight()+30);
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
					
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
					thrownWeaponsPanel.setLocation(5, thieveryPanel.getY()+thieveryPanel.getHeight());
					physStatsPanel.setSize(physStatsPanel.getWidth(), setPanelSize(reflexesPanel.getHeight(), coordinationPanel.getHeight(), physiquePanel.getHeight()));
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), physStatsPanel.getY()+physStatsPanel.getHeight()+30);
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxThievery.setBackground(new Color(255, 201, 131));
		chckbxThievery.setBounds(187, 5, 97, 23);
		thieveryPanel.add(chckbxThievery);
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
					physStatsPanel.setSize(physStatsPanel.getWidth(), setPanelSize(reflexesPanel.getHeight(), coordinationPanel.getHeight(), physiquePanel.getHeight()));
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), physStatsPanel.getY()+physStatsPanel.getHeight()+30);
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
					
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
					physStatsPanel.setSize(physStatsPanel.getWidth(), setPanelSize(reflexesPanel.getHeight(), coordinationPanel.getHeight(), physiquePanel.getHeight()));
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), physStatsPanel.getY()+physStatsPanel.getHeight()+30);
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxThrownWeapons.setBackground(new Color(255, 173, 84));
		chckbxThrownWeapons.setBounds(187, 5, 97, 23);
		thrownWeaponsPanel.add(chckbxThrownWeapons);	
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
					leapPanel.setLocation(5, leapPanel.getY()+extra);
					liftingPanel.setLocation(5, liftingPanel.getY()+extra);
					resistancePanel.setLocation(5, resistancePanel.getY()+extra);
					runningPanel.setLocation(5, runningPanel.getY()+extra);
					swimmingPanel.setLocation(5, swimmingPanel.getY()+extra);
					physStatsPanel.setSize(physStatsPanel.getWidth(), setPanelSize(reflexesPanel.getHeight(), coordinationPanel.getHeight(), physiquePanel.getHeight()));
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), physStatsPanel.getY()+physStatsPanel.getHeight()+30);
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
					
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
					leapPanel.setLocation(5, athleticsPanel.getY()+32);
					liftingPanel.setLocation(5, leapPanel.getY()+leapPanel.getHeight());
					resistancePanel.setLocation(5, liftingPanel.getY()+liftingPanel.getHeight());
					runningPanel.setLocation(5, resistancePanel.getY()+resistancePanel.getHeight());
					swimmingPanel.setLocation(5, runningPanel.getY()+runningPanel.getHeight());
					physStatsPanel.setSize(physStatsPanel.getWidth(), setPanelSize(reflexesPanel.getHeight(), coordinationPanel.getHeight(), physiquePanel.getHeight()));
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), physStatsPanel.getY()+physStatsPanel.getHeight()+30);
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxAthletics.setBackground(new Color(240, 230, 140));
		chckbxAthletics.setBounds(187, 5, 97, 23);
		athleticsPanel.add(chckbxAthletics);		
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
					liftingPanel.setLocation(5, liftingPanel.getY()+extra);
					resistancePanel.setLocation(5, resistancePanel.getY()+extra);
					runningPanel.setLocation(5, runningPanel.getY()+extra);
					swimmingPanel.setLocation(5, swimmingPanel.getY()+extra);
					physStatsPanel.setSize(physStatsPanel.getWidth(), setPanelSize(reflexesPanel.getHeight(), coordinationPanel.getHeight(), physiquePanel.getHeight()));
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), physStatsPanel.getY()+physStatsPanel.getHeight()+30);
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
					
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
					liftingPanel.setLocation(5, leapPanel.getY()+leapPanel.getHeight());
					resistancePanel.setLocation(5, liftingPanel.getY()+liftingPanel.getHeight());
					runningPanel.setLocation(5, resistancePanel.getY()+resistancePanel.getHeight());
					swimmingPanel.setLocation(5, runningPanel.getY()+runningPanel.getHeight());
					physStatsPanel.setSize(physStatsPanel.getWidth(), setPanelSize(reflexesPanel.getHeight(), coordinationPanel.getHeight(), physiquePanel.getHeight()));
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), physStatsPanel.getY()+physStatsPanel.getHeight()+30);
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxLeap.setBackground(new Color(255, 255, 153));
		chckbxLeap.setBounds(187, 5, 97, 23);
		leapPanel.add(chckbxLeap);		
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
					resistancePanel.setLocation(5, resistancePanel.getY()+extra);
					runningPanel.setLocation(5, runningPanel.getY()+extra);
					swimmingPanel.setLocation(5, swimmingPanel.getY()+extra);
					physStatsPanel.setSize(physStatsPanel.getWidth(), setPanelSize(reflexesPanel.getHeight(), coordinationPanel.getHeight(), physiquePanel.getHeight()));
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), physStatsPanel.getY()+physStatsPanel.getHeight()+30);
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
					
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
					resistancePanel.setLocation(5, liftingPanel.getY()+liftingPanel.getHeight());
					runningPanel.setLocation(5, resistancePanel.getY()+resistancePanel.getHeight());
					swimmingPanel.setLocation(5, runningPanel.getY()+runningPanel.getHeight());
					physStatsPanel.setSize(physStatsPanel.getWidth(), setPanelSize(reflexesPanel.getHeight(), coordinationPanel.getHeight(), physiquePanel.getHeight()));
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), physStatsPanel.getY()+physStatsPanel.getHeight()+30);
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxLifting.setBackground(new Color(240, 230, 140));
		chckbxLifting.setBounds(187, 5, 97, 23);
		liftingPanel.add(chckbxLifting);	
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
					runningPanel.setLocation(5, runningPanel.getY()+extra);
					swimmingPanel.setLocation(5, swimmingPanel.getY()+extra);
					physStatsPanel.setSize(physStatsPanel.getWidth(), setPanelSize(reflexesPanel.getHeight(), coordinationPanel.getHeight(), physiquePanel.getHeight()));
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), physStatsPanel.getY()+physStatsPanel.getHeight()+30);
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
					
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
					runningPanel.setLocation(5, resistancePanel.getY()+resistancePanel.getHeight());
					swimmingPanel.setLocation(5, runningPanel.getY()+runningPanel.getHeight());
					physStatsPanel.setSize(physStatsPanel.getWidth(), setPanelSize(reflexesPanel.getHeight(), coordinationPanel.getHeight(), physiquePanel.getHeight()));
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), physStatsPanel.getY()+physStatsPanel.getHeight()+30);
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxResistance.setBackground(new Color(255, 255, 153));
		chckbxResistance.setBounds(187, 5, 97, 23);
		resistancePanel.add(chckbxResistance);		
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
					swimmingPanel.setLocation(5, swimmingPanel.getY()+extra);
					physStatsPanel.setSize(physStatsPanel.getWidth(), setPanelSize(reflexesPanel.getHeight(), coordinationPanel.getHeight(), physiquePanel.getHeight()));
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), physStatsPanel.getY()+physStatsPanel.getHeight()+30);
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
					
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
					swimmingPanel.setLocation(5, runningPanel.getY()+runningPanel.getHeight());
					physStatsPanel.setSize(physStatsPanel.getWidth(), setPanelSize(reflexesPanel.getHeight(), coordinationPanel.getHeight(), physiquePanel.getHeight()));
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), physStatsPanel.getY()+physStatsPanel.getHeight()+30);
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxRunning.setBackground(new Color(240, 230, 140));
		chckbxRunning.setBounds(187, 5, 97, 23);
		runningPanel.add(chckbxRunning);	
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
					physStatsPanel.setSize(physStatsPanel.getWidth(), setPanelSize(reflexesPanel.getHeight(), coordinationPanel.getHeight(), physiquePanel.getHeight()));
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), physStatsPanel.getY()+physStatsPanel.getHeight()+30);
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
					
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
					physStatsPanel.setSize(physStatsPanel.getWidth(), setPanelSize(reflexesPanel.getHeight(), coordinationPanel.getHeight(), physiquePanel.getHeight()));
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), physStatsPanel.getY()+physStatsPanel.getHeight()+30);
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxSwimming.setBackground(new Color(255, 255, 153));
		chckbxSwimming.setBounds(187, 5, 97, 23);
		swimmingPanel.add(chckbxSwimming);		
		
		mentalStatsPanel = new JPanel();
		mentalStatsPanel.setBounds(35, 431, 1152, 279);
		panel.add(mentalStatsPanel);
		mentalStatsPanel.setBackground(new Color(192, 192, 192));
		mentalStatsPanel.setLayout(null);
		
		advPowerPanel = new JPanel();
		advPowerPanel.setBounds(35, mentalStatsPanel.getY()+mentalStatsPanel.getHeight()+30, 1152, 300);
		panel.add(advPowerPanel);
		advPowerPanel.setBackground(new Color(192, 192, 192));
		advPowerPanel.setLayout(null);
		
		
		
		Panel knowledgePanel = new Panel();
		knowledgePanel.setBounds(0, 0, 364, 279);
		mentalStatsPanel.add(knowledgePanel);
		knowledgePanel.setLayout(null);
		knowledgePanel.setBackground(new Color(34, 139, 34));
		
		Label knowledgeLabel = new Label("Knowledge");
		knowledgeLabel.setFont(new Font("Verdana", Font.BOLD, 22));
		knowledgeLabel.setBounds(115, 10, 143, 30);
		knowledgePanel.add(knowledgeLabel);
		
		JFormattedTextField knowledgeLevel = new JFormattedTextField(nums);
		knowledgeLevel.setHorizontalAlignment(SwingConstants.CENTER);
		knowledgeLevel.setFont(new Font("Arial", Font.BOLD, 22));
		knowledgeLevel.setColumns(2);
		knowledgeLevel.setBounds(299, 10, 50, 35);
		knowledgePanel.add(knowledgeLevel);
		
		Panel arcaneLorePanel = new Panel();
		arcaneLorePanel.setLayout(null);
		arcaneLorePanel.setBackground(new Color(144, 238, 144));
		arcaneLorePanel.setBounds(5, 50, 354, 32);
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
		demolitionsPanel.setBounds(5, 82, 354, 32);
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
		languagesPanel.setBounds(5, 114, 354, 32);
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
		medicinePanel.setBounds(5, 146, 354, 32);
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
		scholarPanel.setBounds(5, 178, 354, 32);
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
		sciencePanel.setBounds(5, 210, 354, 32);
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
		securityPanel.setBounds(5, 242, 354, 32);
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
		
		Panel perceptionPanel = new Panel();
		perceptionPanel.setBounds(394, 0, 364, 279);
		mentalStatsPanel.add(perceptionPanel);
		perceptionPanel.setLayout(null);
		perceptionPanel.setBackground(new Color(25, 128, 255));
		
		Label perceptionLabel = new Label("Perception");
		perceptionLabel.setFont(new Font("Verdana", Font.BOLD, 22));
		perceptionLabel.setBounds(110, 10, 135, 30);
		perceptionPanel.add(perceptionLabel);
		
		JFormattedTextField perceptionLevel = new JFormattedTextField(nums);
		perceptionLevel.setHorizontalAlignment(SwingConstants.CENTER);
		perceptionLevel.setFont(new Font("Arial", Font.BOLD, 22));
		perceptionLevel.setColumns(2);
		perceptionLevel.setBounds(299, 10, 50, 35);
		perceptionPanel.add(perceptionLevel);
		
		Panel artistPanel = new Panel();
		artistPanel.setLayout(null);
		artistPanel.setBackground(new Color(135, 206, 250));
		artistPanel.setBounds(5, 50, 354, 32);
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
		engineeringPanel.setBounds(5, 82, 354, 32);
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
		searchPanel.setBounds(5, 114, 354, 32);
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
		streetwisePanel.setBounds(5, 146, 354, 32);
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
		surveillancePanel.setBounds(5, 178, 354, 32);
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
		survivalPanel.setBounds(5, 210, 354, 32);
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
		
		Panel presencePanel = new Panel();
		presencePanel.setBounds(788, 0, 364, 279);
		mentalStatsPanel.add(presencePanel);
		presencePanel.setLayout(null);
		presencePanel.setBackground(new Color(186, 85, 211));
		
		Label presenceLabel = new Label("Presence");
		presenceLabel.setFont(new Font("Verdana", Font.BOLD, 22));
		presenceLabel.setBounds(110, 10, 135, 30);
		presencePanel.add(presenceLabel);
		
		JFormattedTextField presenceLevel = new JFormattedTextField(nums);
		presenceLevel.setHorizontalAlignment(SwingConstants.CENTER);
		presenceLevel.setFont(new Font("Arial", Font.BOLD, 22));
		presenceLevel.setColumns(2);
		presenceLevel.setBounds(299, 10, 50, 35);
		presencePanel.add(presenceLevel);
		
		Panel bluffPanel = new Panel();
		bluffPanel.setLayout(null);
		bluffPanel.setBackground(new Color(221, 160, 221));
		bluffPanel.setBounds(5, 50, 354, 32);
		presencePanel.add(bluffPanel);
		
		Label bluffLabel = new Label("Bluff");
		bluffLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		bluffLabel.setBounds(35, 5, 120, 22);
		bluffPanel.add(bluffLabel);
		
		JFormattedTextField bluffTotal = new JFormattedTextField();
		bluffTotal.setHorizontalAlignment(SwingConstants.CENTER);
		bluffTotal.setFont(new Font("Arial", Font.BOLD, 18));
		bluffTotal.setEditable(false);
		bluffTotal.setColumns(2);
		bluffTotal.setBounds(294, 5, 50, 22);
		bluffPanel.add(bluffTotal);
		
		JFormattedTextField bluffLevel = new JFormattedTextField(nums);
		bluffLevel.setHorizontalAlignment(SwingConstants.CENTER);
		bluffLevel.setFont(new Font("Arial", Font.BOLD, 14));
		bluffLevel.setColumns(2);
		bluffLevel.setBounds(5, 5, 24, 22);
		bluffPanel.add(bluffLevel);
		bluffLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(bluffLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(bluffLevel.getText());
					int j = Integer.parseInt(presenceLevel.getText());
					currentSheet.setBluff(i);
					bluffTotal.setValue(i+j);	
				}
			}
		});
		
		Panel charmPanel = new Panel();
		charmPanel.setLayout(null);
		charmPanel.setBackground(new Color(218, 112, 214));
		charmPanel.setBounds(5, 82, 354, 32);
		presencePanel.add(charmPanel);
		
		Label charmLabel = new Label("Charm");
		charmLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		charmLabel.setBounds(35, 5, 120, 22);
		charmPanel.add(charmLabel);
		
		JFormattedTextField charmTotal = new JFormattedTextField();
		charmTotal.setHorizontalAlignment(SwingConstants.CENTER);
		charmTotal.setFont(new Font("Arial", Font.BOLD, 18));
		charmTotal.setEditable(false);
		charmTotal.setColumns(2);
		charmTotal.setBounds(294, 5, 50, 22);
		charmPanel.add(charmTotal);
		
		JFormattedTextField charmLevel = new JFormattedTextField(nums);
		charmLevel.setHorizontalAlignment(SwingConstants.CENTER);
		charmLevel.setFont(new Font("Arial", Font.BOLD, 14));
		charmLevel.setColumns(2);
		charmLevel.setBounds(5, 7, 24, 20);
		charmPanel.add(charmLevel);
		charmLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(charmLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(charmLevel.getText());
					int j = Integer.parseInt(presenceLevel.getText());
					currentSheet.setCharm(i);
					charmTotal.setValue(i+j);	
				}
			}
		});
		
		Panel intimidationPanel = new Panel();
		intimidationPanel.setLayout(null);
		intimidationPanel.setBackground(new Color(221, 160, 221));
		intimidationPanel.setBounds(5, 114, 354, 32);
		presencePanel.add(intimidationPanel);
		
		Label intimidationLabel = new Label("Intimidation");
		intimidationLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		intimidationLabel.setBounds(35, 5, 120, 22);
		intimidationPanel.add(intimidationLabel);
		
		JFormattedTextField intimidationTotal = new JFormattedTextField();
		intimidationTotal.setHorizontalAlignment(SwingConstants.CENTER);
		intimidationTotal.setFont(new Font("Arial", Font.BOLD, 18));
		intimidationTotal.setEditable(false);
		intimidationTotal.setColumns(2);
		intimidationTotal.setBounds(294, 5, 50, 22);
		intimidationPanel.add(intimidationTotal);
		
		JFormattedTextField intimidationLevel = new JFormattedTextField(nums);
		intimidationLevel.setHorizontalAlignment(SwingConstants.CENTER);
		intimidationLevel.setFont(new Font("Arial", Font.BOLD, 14));
		intimidationLevel.setColumns(2);
		intimidationLevel.setBounds(5, 5, 24, 22);
		intimidationPanel.add(intimidationLevel);
		intimidationLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(intimidationLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(intimidationLevel.getText());
					int j = Integer.parseInt(presenceLevel.getText());
					currentSheet.setIntimidation(i);
					intimidationTotal.setValue(i+j);	
				}
			}
		});
		
		Panel persuasionPanel = new Panel();
		persuasionPanel.setLayout(null);
		persuasionPanel.setBackground(new Color(218, 112, 214));
		persuasionPanel.setBounds(5, 146, 354, 32);
		presencePanel.add(persuasionPanel);
		
		Label persuasionLabel = new Label("Persuasion");
		persuasionLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		persuasionLabel.setBounds(35, 5, 120, 22);
		persuasionPanel.add(persuasionLabel);
		
		JFormattedTextField persuasionTotal = new JFormattedTextField();
		persuasionTotal.setHorizontalAlignment(SwingConstants.CENTER);
		persuasionTotal.setFont(new Font("Arial", Font.BOLD, 18));
		persuasionTotal.setEditable(false);
		persuasionTotal.setColumns(2);
		persuasionTotal.setBounds(294, 5, 50, 22);
		persuasionPanel.add(persuasionTotal);
		
		JFormattedTextField persuasionLevel = new JFormattedTextField(nums);
		persuasionLevel.setHorizontalAlignment(SwingConstants.CENTER);
		persuasionLevel.setFont(new Font("Arial", Font.BOLD, 14));
		persuasionLevel.setColumns(2);
		persuasionLevel.setBounds(5, 7, 24, 20);
		persuasionPanel.add(persuasionLevel);
		persuasionLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(persuasionLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(persuasionLevel.getText());
					int j = Integer.parseInt(presenceLevel.getText());
					currentSheet.setPersuasion(i);
					persuasionTotal.setValue(i+j);	
				}
			}
		});
		
		Panel willpowerPanel = new Panel();
		willpowerPanel.setLayout(null);
		willpowerPanel.setBackground(new Color(221, 160, 221));
		willpowerPanel.setBounds(5, 178, 354, 32);
		presencePanel.add(willpowerPanel);
		
		Label willpowerLabel = new Label("Willpower");
		willpowerLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		willpowerLabel.setBounds(35, 5, 120, 22);
		willpowerPanel.add(willpowerLabel);
		
		JFormattedTextField willpowerTotal = new JFormattedTextField();
		willpowerTotal.setHorizontalAlignment(SwingConstants.CENTER);
		willpowerTotal.setFont(new Font("Arial", Font.BOLD, 18));
		willpowerTotal.setEditable(false);
		willpowerTotal.setColumns(2);
		willpowerTotal.setBounds(294, 5, 50, 22);
		willpowerPanel.add(willpowerTotal);
		
		JFormattedTextField willpowerLevel = new JFormattedTextField(nums);
		willpowerLevel.setHorizontalAlignment(SwingConstants.CENTER);
		willpowerLevel.setFont(new Font("Arial", Font.BOLD, 14));
		willpowerLevel.setColumns(2);
		willpowerLevel.setBounds(5, 5, 24, 22);
		willpowerPanel.add(willpowerLevel);
		willpowerLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(willpowerLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(willpowerLevel.getText());
					int j = Integer.parseInt(presenceLevel.getText());
					currentSheet.setWillpower(i);
					willpowerTotal.setValue(i+j);	
				}
			}
		});
		
		presenceLevel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(perceptionLevel.getText().matches("[0-9]+"))
				{
					int i = Integer.parseInt(presenceLevel.getText());
					int j = Integer.parseInt(bluffLevel.getText());
					int k = Integer.parseInt(charmLevel.getText());
					int l = Integer.parseInt(intimidationLevel.getText());
					int m = Integer.parseInt(persuasionLevel.getText());
					int n = Integer.parseInt(willpowerLevel.getText());

					currentSheet.setPresence(i);
					bluffTotal.setValue(i+j);
					charmTotal.setValue(i+k);
					intimidationTotal.setValue(i+l);	
					persuasionTotal.setValue(i+m);
					willpowerTotal.setValue(i+n);
				}
			}
		});
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
					demolitionsPanel.setLocation(5, demolitionsPanel.getY()+extra);
					languagesPanel.setLocation(5, languagesPanel.getY()+extra);
					medicinePanel.setLocation(5, medicinePanel.getY()+extra);
					scholarPanel.setLocation(5, scholarPanel.getY()+extra);
					sciencePanel.setLocation(5, sciencePanel.getY()+extra);
					securityPanel.setLocation(5, securityPanel.getY()+extra);
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();

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
					demolitionsPanel.setLocation(5, arcaneLorePanel.getY()+32);
					languagesPanel.setLocation(5, demolitionsPanel.getY()+demolitionsPanel.getHeight());
					medicinePanel.setLocation(5, languagesPanel.getY()+languagesPanel.getHeight());
					scholarPanel.setLocation(5, medicinePanel.getY()+medicinePanel.getHeight());
					sciencePanel.setLocation(5, scholarPanel.getY()+scholarPanel.getHeight());
					securityPanel.setLocation(5, sciencePanel.getY()+sciencePanel.getHeight());
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxArcaneLore.setBackground(new Color(144, 238, 144));
		chckbxArcaneLore.setBounds(187, 5, 97, 23);
		arcaneLorePanel.add(chckbxArcaneLore);		
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
					languagesPanel.setLocation(5, languagesPanel.getY()+extra);
					medicinePanel.setLocation(5, medicinePanel.getY()+extra);
					scholarPanel.setLocation(5, scholarPanel.getY()+extra);
					sciencePanel.setLocation(5, sciencePanel.getY()+extra);
					securityPanel.setLocation(5, securityPanel.getY()+extra);
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();

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
					languagesPanel.setLocation(5, demolitionsPanel.getY()+demolitionsPanel.getHeight());
					medicinePanel.setLocation(5, languagesPanel.getY()+languagesPanel.getHeight());
					scholarPanel.setLocation(5, medicinePanel.getY()+medicinePanel.getHeight());
					sciencePanel.setLocation(5, scholarPanel.getY()+scholarPanel.getHeight());
					securityPanel.setLocation(5, sciencePanel.getY()+sciencePanel.getHeight());
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxDemolitions.setBackground(new Color(50, 205, 50));
		chckbxDemolitions.setBounds(187, 5, 97, 23);
		demolitionsPanel.add(chckbxDemolitions);	
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
					medicinePanel.setLocation(5, medicinePanel.getY()+extra);
					scholarPanel.setLocation(5, scholarPanel.getY()+extra);
					sciencePanel.setLocation(5, sciencePanel.getY()+extra);
					securityPanel.setLocation(5, securityPanel.getY()+extra);
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();

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
					medicinePanel.setLocation(5, languagesPanel.getY()+languagesPanel.getHeight());
					scholarPanel.setLocation(5, medicinePanel.getY()+medicinePanel.getHeight());
					sciencePanel.setLocation(5, scholarPanel.getY()+scholarPanel.getHeight());
					securityPanel.setLocation(5, sciencePanel.getY()+sciencePanel.getHeight());
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxLanguages.setBackground(new Color(144, 238, 144));
		chckbxLanguages.setBounds(187, 5, 97, 23);
		languagesPanel.add(chckbxLanguages);	
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
					scholarPanel.setLocation(5, scholarPanel.getY()+extra);
					sciencePanel.setLocation(5, sciencePanel.getY()+extra);
					securityPanel.setLocation(5, securityPanel.getY()+extra);
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
					
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
					scholarPanel.setLocation(5, medicinePanel.getY()+medicinePanel.getHeight());
					sciencePanel.setLocation(5, scholarPanel.getY()+scholarPanel.getHeight());
					securityPanel.setLocation(5, sciencePanel.getY()+sciencePanel.getHeight());
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxMedicine.setBackground(new Color(50, 205, 50));
		chckbxMedicine.setBounds(187, 5, 97, 23);
		medicinePanel.add(chckbxMedicine);		
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
					sciencePanel.setLocation(5, sciencePanel.getY()+extra);
					securityPanel.setLocation(5, securityPanel.getY()+extra);
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
					
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
					sciencePanel.setLocation(5, scholarPanel.getY()+scholarPanel.getHeight());
					securityPanel.setLocation(5, sciencePanel.getY()+sciencePanel.getHeight());
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxScholar.setBackground(new Color(144, 238, 144));
		chckbxScholar.setBounds(187, 5, 97, 23);
		scholarPanel.add(chckbxScholar);
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
					securityPanel.setLocation(5, securityPanel.getY()+extra);
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
					
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
					securityPanel.setLocation(5, sciencePanel.getY()+sciencePanel.getHeight());
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxScience.setBackground(new Color(50, 205, 50));
		chckbxScience.setBounds(187, 5, 97, 23);
		sciencePanel.add(chckbxScience);		
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
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();

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
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxSecurity.setBackground(new Color(144, 238, 144));
		chckbxSecurity.setBounds(187, 5, 97, 23);
		securityPanel.add(chckbxSecurity);
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
					engineeringPanel.setLocation(5, engineeringPanel.getY()+extra);
					searchPanel.setLocation(5, searchPanel.getY()+extra);
					streetwisePanel.setLocation(5, streetwisePanel.getY()+extra);
					surveillancePanel.setLocation(5, surveillancePanel.getY()+extra);
					survivalPanel.setLocation(5, survivalPanel.getY()+extra);
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();

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
					engineeringPanel.setLocation(5, artistPanel.getY()+32);
					searchPanel.setLocation(5, engineeringPanel.getY()+engineeringPanel.getHeight());
					streetwisePanel.setLocation(5, searchPanel.getY()+searchPanel.getHeight());
					surveillancePanel.setLocation(5, streetwisePanel.getY()+streetwisePanel.getHeight());
					survivalPanel.setLocation(5, surveillancePanel.getY()+surveillancePanel.getHeight());
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxArtist.setBackground(new Color(135, 206, 235));
		chckbxArtist.setBounds(187, 5, 97, 23);
		artistPanel.add(chckbxArtist);			
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
					searchPanel.setLocation(5, searchPanel.getY()+extra);
					streetwisePanel.setLocation(5, streetwisePanel.getY()+extra);
					surveillancePanel.setLocation(5, surveillancePanel.getY()+extra);
					survivalPanel.setLocation(5, survivalPanel.getY()+extra);
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();

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
					searchPanel.setLocation(5, engineeringPanel.getY()+engineeringPanel.getHeight());
					streetwisePanel.setLocation(5, searchPanel.getY()+searchPanel.getHeight());
					surveillancePanel.setLocation(5, streetwisePanel.getY()+streetwisePanel.getHeight());
					survivalPanel.setLocation(5, surveillancePanel.getY()+surveillancePanel.getHeight());
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxEngineering.setBackground(new Color(5, 191, 255));
		chckbxEngineering.setBounds(187, 5, 97, 23);
		engineeringPanel.add(chckbxEngineering);	
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
					streetwisePanel.setLocation(5, streetwisePanel.getY()+extra);
					surveillancePanel.setLocation(5, surveillancePanel.getY()+extra);
					survivalPanel.setLocation(5, survivalPanel.getY()+extra);
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();

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
					streetwisePanel.setLocation(5, searchPanel.getY()+searchPanel.getHeight());
					surveillancePanel.setLocation(5, streetwisePanel.getY()+streetwisePanel.getHeight());
					survivalPanel.setLocation(5, surveillancePanel.getY()+surveillancePanel.getHeight());
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxSearch.setBackground(new Color(135, 206, 235));
		chckbxSearch.setBounds(187, 5, 97, 23);
		searchPanel.add(chckbxSearch);		
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
					surveillancePanel.setLocation(5, surveillancePanel.getY()+extra);
					survivalPanel.setLocation(5, survivalPanel.getY()+extra);
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();

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
					surveillancePanel.setLocation(5, streetwisePanel.getY()+streetwisePanel.getHeight());
					survivalPanel.setLocation(5, surveillancePanel.getY()+surveillancePanel.getHeight());
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxStreetwise.setBackground(new Color(5, 191, 255));
		chckbxStreetwise.setBounds(187, 5, 97, 23);
		streetwisePanel.add(chckbxStreetwise);		
		
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
					survivalPanel.setLocation(5, survivalPanel.getY()+extra);
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();

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
					survivalPanel.setLocation(5, surveillancePanel.getY()+surveillancePanel.getHeight());
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxSurveillance.setBackground(new Color(135, 206, 235));
		chckbxSurveillance.setBounds(187, 5, 97, 23);
		surveillancePanel.add(chckbxSurveillance);		
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
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();

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
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxSurvival.setBackground(new Color(5, 191, 255));
		chckbxSurvival.setBounds(187, 5, 97, 23);
		survivalPanel.add(chckbxSurvival);		
		JCheckBox chckbxBluff = new JCheckBox("Show specs");
		chckbxBluff.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> bluffSpecsList = currentSheet.assignSkillSpecs("Bluff");
				if(chckbxBluff.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "Bluff", "");
					bluffSpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;

					int extra = (bluffSpecsList.size())*28;
					
					presencePanel.setSize(presencePanel.getWidth(), (presencePanel.getHeight()+extra));
					bluffPanel.setSize(354, (32+extra));
					charmPanel.setLocation(5, charmPanel.getY()+extra);
					intimidationPanel.setLocation(5, intimidationPanel.getY()+extra);
					persuasionPanel.setLocation(5, persuasionPanel.getY()+extra);
					willpowerPanel.setLocation(5, willpowerPanel.getY()+extra);
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();

					for(int i = 0; i < bluffSpecsList.size(); i++)
					{
						int j = i;
						bluffSpecsFields.add(new JFormattedTextField());
						JFormattedTextField bluffSpecs = bluffSpecsFields.get(i);
						bluffSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						bluffSpecs.setBackground(new Color(230, 230, 250));
						bluffSpecs.setBounds(5, 33+(i*28), 279, 22);
						bluffPanel.add(bluffSpecs);
						bluffSpecs.setText(bluffSpecsList.get(i).getDescription());
						bluffSpecs.requestFocus();	

						bluffSpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = bluffSpecsList.get(j);
									editing.setDescription(bluffSpecs.getText());
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
										chckbxBluff.setSelected(false);
										chckbxBluff.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxBluff.isSelected())
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

					bluffSpecsList.clear();
					for(JFormattedTextField t : bluffSpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						bluffPanel.remove(t);
						t = null;
					}
					bluffSpecsFields.clear();
					
					presencePanel.setSize(presencePanel.getWidth(), (presencePanel.getHeight()-(bluffPanel.getHeight()-32)));
					bluffPanel.setSize(354, (32));
					charmPanel.setLocation(5, bluffPanel.getY()+32);
					intimidationPanel.setLocation(5, charmPanel.getY()+charmPanel.getHeight());
					persuasionPanel.setLocation(5, intimidationPanel.getY()+intimidationPanel.getHeight());
					willpowerPanel.setLocation(5, persuasionPanel.getY()+persuasionPanel.getHeight());
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxBluff.setBackground(new Color(221, 160, 221));
		chckbxBluff.setBounds(187, 5, 97, 23);
		bluffPanel.add(chckbxBluff);	
		JCheckBox chckbxCharm = new JCheckBox("Show specs");
		chckbxCharm.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> charmSpecsList = currentSheet.assignSkillSpecs("Charm");
				if(chckbxCharm.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "Charm", "");
					charmSpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;

					int extra = (charmSpecsList.size())*28;
					
					presencePanel.setSize(presencePanel.getWidth(), (presencePanel.getHeight()+extra));
					charmPanel.setSize(354, (32+extra));
					intimidationPanel.setLocation(5, intimidationPanel.getY()+extra);
					persuasionPanel.setLocation(5, persuasionPanel.getY()+extra);
					willpowerPanel.setLocation(5, willpowerPanel.getY()+extra);
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();

					for(int i = 0; i < charmSpecsList.size(); i++)
					{
						int j = i;
						charmSpecsFields.add(new JFormattedTextField());
						JFormattedTextField charmSpecs = charmSpecsFields.get(i);
						charmSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						charmSpecs.setBackground(new Color(230, 230, 250));
						charmSpecs.setBounds(5, 33+(i*28), 279, 22);
						charmPanel.add(charmSpecs);
						charmSpecs.setText(charmSpecsList.get(i).getDescription());
						charmSpecs.requestFocus();	

						charmSpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = charmSpecsList.get(j);
									editing.setDescription(charmSpecs.getText());
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
										chckbxCharm.setSelected(false);
										chckbxCharm.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxCharm.isSelected())
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

					charmSpecsList.clear();
					for(JFormattedTextField t : charmSpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						charmPanel.remove(t);
						t = null;
					}
					charmSpecsFields.clear();
					
					presencePanel.setSize(presencePanel.getWidth(), (presencePanel.getHeight()-(charmPanel.getHeight()-32)));
					charmPanel.setSize(354, (32));
					intimidationPanel.setLocation(5, charmPanel.getY()+charmPanel.getHeight());
					persuasionPanel.setLocation(5, intimidationPanel.getY()+intimidationPanel.getHeight());
					willpowerPanel.setLocation(5, persuasionPanel.getY()+persuasionPanel.getHeight());
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxCharm.setBackground(new Color(218, 112, 214));
		chckbxCharm.setBounds(187, 5, 97, 23);
		charmPanel.add(chckbxCharm);		
		JCheckBox chckbxIntimidation = new JCheckBox("Show specs");
		chckbxIntimidation.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> intimidationSpecsList = currentSheet.assignSkillSpecs("Intimidation");
				if(chckbxIntimidation.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "Intimidation", "");
					intimidationSpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;

					int extra = (intimidationSpecsList.size())*28;
					
					presencePanel.setSize(presencePanel.getWidth(), (presencePanel.getHeight()+extra));
					intimidationPanel.setSize(354, (32+extra));
					persuasionPanel.setLocation(5, persuasionPanel.getY()+extra);
					willpowerPanel.setLocation(5, willpowerPanel.getY()+extra);
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();

					for(int i = 0; i < intimidationSpecsList.size(); i++)
					{
						int j = i;
						intimidationSpecsFields.add(new JFormattedTextField());
						JFormattedTextField intimidationSpecs = intimidationSpecsFields.get(i);
						intimidationSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						intimidationSpecs.setBackground(new Color(230, 230, 250));
						intimidationSpecs.setBounds(5, 33+(i*28), 279, 22);
						intimidationPanel.add(intimidationSpecs);
						intimidationSpecs.setText(intimidationSpecsList.get(i).getDescription());
						intimidationSpecs.requestFocus();	

						intimidationSpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = intimidationSpecsList.get(j);
									editing.setDescription(intimidationSpecs.getText());
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
										chckbxIntimidation.setSelected(false);
										chckbxIntimidation.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxIntimidation.isSelected())
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

					intimidationSpecsList.clear();
					for(JFormattedTextField t : intimidationSpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						intimidationPanel.remove(t);
						t = null;
					}
					intimidationSpecsFields.clear();
					
					presencePanel.setSize(presencePanel.getWidth(), (presencePanel.getHeight()-(intimidationPanel.getHeight()-32)));
					intimidationPanel.setSize(354, (32));
					persuasionPanel.setLocation(5, intimidationPanel.getY()+intimidationPanel.getHeight());
					willpowerPanel.setLocation(5, persuasionPanel.getY()+persuasionPanel.getHeight());
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxIntimidation.setBackground(new Color(221, 160, 221));
		chckbxIntimidation.setBounds(187, 5, 97, 23);
		intimidationPanel.add(chckbxIntimidation);	
		
		JCheckBox chckbxPersuasion = new JCheckBox("Show specs");
		chckbxPersuasion.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> persuasionSpecsList = currentSheet.assignSkillSpecs("Persuasion");
				if(chckbxPersuasion.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "Persuasion", "");
					persuasionSpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;

					int extra = (persuasionSpecsList.size())*28;
					
					presencePanel.setSize(presencePanel.getWidth(), (presencePanel.getHeight()+extra));
					persuasionPanel.setSize(354, (32+extra));
					willpowerPanel.setLocation(5, willpowerPanel.getY()+extra);
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();

					for(int i = 0; i < persuasionSpecsList.size(); i++)
					{
						int j = i;
						persuasionSpecsFields.add(new JFormattedTextField());
						JFormattedTextField persuasionSpecs = persuasionSpecsFields.get(i);
						persuasionSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						persuasionSpecs.setBackground(new Color(230, 230, 250));
						persuasionSpecs.setBounds(5, 33+(i*28), 279, 22);
						persuasionPanel.add(persuasionSpecs);
						persuasionSpecs.setText(persuasionSpecsList.get(i).getDescription());
						persuasionSpecs.requestFocus();	

						persuasionSpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = persuasionSpecsList.get(j);
									editing.setDescription(persuasionSpecs.getText());
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
										chckbxPersuasion.setSelected(false);
										chckbxPersuasion.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxPersuasion.isSelected())
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

					persuasionSpecsList.clear();
					for(JFormattedTextField t : persuasionSpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						persuasionPanel.remove(t);
						t = null;
					}
					persuasionSpecsFields.clear();
					
					presencePanel.setSize(presencePanel.getWidth(), (presencePanel.getHeight()-(persuasionPanel.getHeight()-32)));
					persuasionPanel.setSize(354, (32));
					willpowerPanel.setLocation(5, persuasionPanel.getY()+persuasionPanel.getHeight());
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxPersuasion.setBackground(new Color(218, 112, 214));
		chckbxPersuasion.setBounds(187, 5, 97, 23);
		persuasionPanel.add(chckbxPersuasion);		
		JCheckBox chckbxWillpower = new JCheckBox("Show specs");
		chckbxWillpower.addItemListener(new ItemListener() 
		{
			int blankId;
			public void itemStateChanged(ItemEvent arg0) 
			{		
				List<SkillSpec> willpowerSpecsList = currentSheet.assignSkillSpecs("Willpower");
				if(chckbxWillpower.isSelected())
				{
					blankId = nextSpecId;
					SkillSpec blank = new SkillSpec(blankId, currentSheet.getId(), "Willpower", "");
					willpowerSpecsList.add(blank);
					specs.add(blank);
					nextSpecId++;

					int extra = (willpowerSpecsList.size())*28;
					
					presencePanel.setSize(presencePanel.getWidth(), (presencePanel.getHeight()+extra));
					willpowerPanel.setSize(354, (32+extra));
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();

					for(int i = 0; i < willpowerSpecsList.size(); i++)
					{
						int j = i;
						willpowerSpecsFields.add(new JFormattedTextField());
						JFormattedTextField willpowerSpecs = willpowerSpecsFields.get(i);
						willpowerSpecs.setFont(new Font("Verdana", Font.PLAIN, 13));
						willpowerSpecs.setBackground(new Color(230, 230, 250));
						willpowerSpecs.setBounds(5, 33+(i*28), 279, 22);
						willpowerPanel.add(willpowerSpecs);
						willpowerSpecs.setText(willpowerSpecsList.get(i).getDescription());
						willpowerSpecs.requestFocus();	

						willpowerSpecs.addKeyListener(new KeyAdapter() 
						{
							@Override
							public void keyReleased(KeyEvent e) 
							{
								SkillSpec editing = willpowerSpecsList.get(j);
									editing.setDescription(willpowerSpecs.getText());
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
										chckbxWillpower.setSelected(false);
										chckbxWillpower.setSelected(true);
									}
							}
						});
					}
				}
				else if(!chckbxWillpower.isSelected())
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

					willpowerSpecsList.clear();
					for(JFormattedTextField t : willpowerSpecsFields) // DOES THIS EVEN DO ANYTHING?	
					{
						willpowerPanel.remove(t);
						t = null;
					}
					willpowerSpecsFields.clear();
					
					presencePanel.setSize(presencePanel.getWidth(), (presencePanel.getHeight()-(willpowerPanel.getHeight()-32)));
					willpowerPanel.setSize(354, (32));
					mentalStatsPanel.setSize(mentalStatsPanel.getWidth(), setPanelSize(knowledgePanel.getHeight(), perceptionPanel.getHeight(), presencePanel.getHeight()));
					panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()+30));
					dcrpgFrame.revalidate();
				}
			}
		});
		chckbxWillpower.setBackground(new Color(221, 160, 221));
		chckbxWillpower.setBounds(187, 5, 97, 23);
		willpowerPanel.add(chckbxWillpower);
		
		presenceLevel.setValue(currentSheet.getPresence());
		bluffLevel.setValue(currentSheet.getBluff());
		charmLevel.setValue(currentSheet.getCharm());
		intimidationLevel.setValue(currentSheet.getIntimidation());
		persuasionLevel.setValue(currentSheet.getPersuasion());
		willpowerLevel.setValue(currentSheet.getWillpower());
		bluffTotal.setValue(currentSheet.getBluff() + currentSheet.getPresence());
		charmTotal.setValue(currentSheet.getCharm() + currentSheet.getPresence());
		intimidationTotal.setValue(currentSheet.getIntimidation() + currentSheet.getPresence());
		persuasionTotal.setValue(currentSheet.getPersuasion() + currentSheet.getPresence());
		willpowerTotal.setValue(currentSheet.getWillpower() + currentSheet.getPresence());
		
		JCheckBox chckbxDemographics = new JCheckBox("Show Demographics");
		chckbxDemographics.setBounds(35, 33, 164, 20);
		panel.add(chckbxDemographics);
		chckbxDemographics.setSelected(true);
		
	    advantagePanel = new AdvantagePanel(0, 0, currentSheet, advs, disadvs);
	    advPowerPanel.add(advantagePanel);
		
		powerPanel = new PowerPanel(advantagePanel.getX()+advantagePanel.getWidth()+30, 0, currentSheet, powers);
		powerPanel.setSize(advPowerPanel.getWidth()-powerPanel.getX(), powerPanel.getHeight());
		advPowerPanel.add(powerPanel);
		
		int apPanelHeight = advantagePanel.getHeight() >= powerPanel.getHeight() ? advantagePanel.getHeight() : powerPanel.getHeight();
		advPowerPanel.setSize(advPowerPanel.getWidth(), apPanelHeight);
		
		icon = new ImageIcon();
		img = null;
		try 
		{
		    img = ImageIO.read(new File("images/sheets/"+currentSheet.getPicture()));
		    img = Scalr.resize(img, 300, 350);
		    icon.setImage(img);
		} catch (IOException e) {
			
		}
		
		
		JLabel imgLabel = new JLabel();

		imgLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imgLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		imgLabel.setBackground(Color.WHITE);
		imgLabel.setIcon(icon);
		imgLabel.setBounds(1217, 53, 300, 350);
		panel.add(imgLabel);
		
	    imgLabel.setTransferHandler(new ImageSelection());	    
	    
	    MouseListener listener = new MouseAdapter() {
	      public void mousePressed(MouseEvent me) {
	        JComponent comp = (JComponent) me.getSource();
	        TransferHandler handler = comp.getTransferHandler();
	        handler.exportAsDrag(comp, me, TransferHandler.COPY);     
	      }
	    };
	    
	    imgLabel.addMouseListener(listener);
	    
		imgLabel.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				if(imgChange)
				{
			        icon = (ImageIcon)imgLabel.getIcon();
			        origImg = (BufferedImage)icon.getImage();
				    img = Scalr.resize((BufferedImage)icon.getImage(), 300, 350);
				    icon.setImage(img);
				    newPic = true;
				}
			}
		});
	    
		EquipmentPanel ePane = new EquipmentPanel();
		ePane.setBounds(imgLabel.getX(), mentalStatsPanel.getY(), imgLabel.getWidth(), 30);
		panel.add(ePane);
		
		Item weapon = new Item("Weapon", 296, 382);
		weapon.set(items, 0);
		weapon.setLocation(ePane.getX(), ePane.getY()+ePane.getHeight()+30);
		weapon.setDisabled();
		panel.add(weapon);
		
		btnSave.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				chckbxAcro.setSelected(false);
				chckbxDodge.setSelected(false);
				chckbxHandToHand.setSelected(false);
				chckbxMeleeWeapons.setSelected(false);
				chckbxStealth.setSelected(false);
				
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
				
				chckbxBluff.setSelected(false);
				chckbxCharm.setSelected(false);
				chckbxIntimidation.setSelected(false);
				chckbxPersuasion.setSelected(false);
				chckbxWillpower.setSelected(false);
				
				// Write save code here - should be easy
				String duplicate = "Did not save: Duplicate Name";
				boolean isDuplicate = false;
				boolean isOverwrite = false;
				for(CharacterSheet cs: sheets)
				{
					if(cs.getName().toLowerCase().equals(currentSheet.getName().toLowerCase())
							|| cs.getName().toLowerCase().equals(duplicate.toLowerCase()))
					{
						isDuplicate = true;
						if(cs.getId()==currentSheet.getId())
							isOverwrite = true;
					}
					
				}
				


				if(!isDuplicate || isOverwrite)
				{
					if(isNew)
					{
						nextSheetId++;
						sheets.add(currentSheet);
						isNew = false;
					}
								
					
					List<SkillSpec> oldSpecs = dao.getSheet(currentSheet.getId()).getSkillSpecs();
					List<CharacterSheetAdvantage> oldCSA = dao.getSheet(currentSheet.getId()).getCSA();
					List<CharacterSheetDisadvantage> oldCSD = dao.getSheet(currentSheet.getId()).getCSD();
					
					dao.updateSheet(currentSheet);
					
					
					
					for(SkillSpec ss : currentSheet.getSkillSpecs())
						dao.updateSpec(ss);
					boolean deleting = true;
					while(deleting)
					{
						for(SkillSpec s : oldSpecs)
						{
							boolean delete = true;
							
							for(SkillSpec ss : currentSheet.getSkillSpecs())
							{
								if(ss.getId()==s.getId())
									delete = false;
							}
	
							if(delete)
							{
								dao.deleteSpec(s);
								break;
							}
						}
						deleting = false;
					}					
					
					
					for(CharacterSheetAdvantage csa : currentSheet.getCSA())
						dao.updateCSA(csa);
					deleting = true;
					while(deleting)
					{
						for(CharacterSheetAdvantage csa : oldCSA)
						{
							boolean delete = true;
							
							for(CharacterSheetAdvantage csas : currentSheet.getCSA())
							{
								if(csas.getId()==csa.getId())
									delete = false;
							}
	
							if(delete)
							{
								dao.deleteCSA(csa);
								break;
							}
						}
						deleting = false;
					}	
					
					for(CharacterSheetDisadvantage csd : currentSheet.getCSD())
						dao.updateCSD(csd);
					deleting = true;
					while(deleting)
					{
						for(CharacterSheetDisadvantage csd : oldCSD)
						{
							boolean delete = true;
							
							for(CharacterSheetDisadvantage csds : currentSheet.getCSD())
							{
								if(csds.getId()==csd.getId())
									delete = false;
							}
	
							if(delete)
							{
								dao.deleteCSD(csd);
								break;
							}
						}
						deleting = false;
					}	
					
					etab.saveItems(false);
					stab.saveSpells(false);
		
					dao.saveAll();
					
					
					if(newPic)
					{
						try 
						{
						    String n = currentSheet.getName().toLowerCase();
						    
						    File outputfile = new File("images/sheets/"+n+".png");
						    ImageIO.write(origImg, "png", outputfile);
						    newPic = false;
						} catch (IOException e) {
							System.out.println(e);
						}
					}
				}
				else
					nameField.setValue(duplicate);
			}
		});

		btnLoad.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				for(CharacterSheet s : sheets)
				{
					if(s.getName().toLowerCase().equals(nameSearchField.getText().toLowerCase()))
					{
							chckbxAcro.setSelected(false);
							chckbxDodge.setSelected(false);
							chckbxHandToHand.setSelected(false);
							chckbxMeleeWeapons.setSelected(false);
							chckbxStealth.setSelected(false);
									
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
							
							chckbxBluff.setSelected(false);
							chckbxCharm.setSelected(false);
							chckbxIntimidation.setSelected(false);
							chckbxPersuasion.setSelected(false);
							chckbxWillpower.setSelected(false);
								
							newItem = null;
							currentSheet = s;
							// load the entire dang sheet
							demographicsPanel.setNewCharacter(currentSheet);
							etab.setNewCharacter(currentSheet);
							stab.setNewCharacter(currentSheet);
							ePane.setNewCharacter(currentSheet);
							weapon.set(items, currentSheet.getInv().getWeapon());
							weapon.setLocation(ePane.getX(), ePane.getY()+ePane.getHeight()+30);

							
							nameField.setText(currentSheet.getName());					
							udoField.setText(currentSheet.getUdoDice() + "+" + currentSheet.getUdoBonus());
							bodyPointsField.setText(currentSheet.getBodyPointsCurrent() + "/" + currentSheet.getBodyPointsMax());
							speedField.setValue(currentSheet.getSpeed());
							locationField.setValue(currentSheet.getLocation());
							
							
							imgChange = false;
							try 
							{
							    origImg = ImageIO.read(new File("images/sheets/"+currentSheet.getPicture()));
							    imgLabel.setIcon(null);
								img = null;
							    img = ImageIO.read(new File("images/sheets/"+currentSheet.getPicture()));
							    img = Scalr.resize(img, 300, 350);
							    icon.setImage(img);
							    imgLabel.setIcon(icon);
							} catch (IOException e) 
							{
								try
								{
									imgLabel.setIcon(null);
									img = null;
								    img = ImageIO.read(new File("images/sheets/blank.png"));
								    img = Scalr.resize(img, 300, 350);
								    icon.setImage(img);
								    imgLabel.setIcon(icon);
								    newPic = false;
								}
								catch (IOException e2)
								{
									
								}
							} 
							imgChange = true;
									
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
							presenceLevel.setValue(currentSheet.getPresence());
							bluffLevel.setValue(currentSheet.getBluff());
							charmLevel.setValue(currentSheet.getCharm());
							intimidationLevel.setValue(currentSheet.getIntimidation());
							persuasionLevel.setValue(currentSheet.getPersuasion());
							willpowerLevel.setValue(currentSheet.getWillpower());
							bluffTotal.setValue(currentSheet.getBluff() + currentSheet.getPresence());
							charmTotal.setValue(currentSheet.getCharm() + currentSheet.getPresence());
							intimidationTotal.setValue(currentSheet.getIntimidation() + currentSheet.getPresence());
							persuasionTotal.setValue(currentSheet.getPersuasion() + currentSheet.getPresence());
							willpowerTotal.setValue(currentSheet.getWillpower() + currentSheet.getPresence());	
							
							advs = advantagePanel.saveAdvs();
							disadvs = advantagePanel.saveDisadvs();
							powers = powerPanel.savePwrs();
							advantagePanel.setNewCharacter(0, 0, currentSheet, advs, disadvs);
							powerPanel.setNewCharacter(advantagePanel.getX()+advantagePanel.getWidth()+30, 0, currentSheet, powers);
							int apPanelHeight = advantagePanel.getHeight() >= powerPanel.getHeight() ? advantagePanel.getHeight() : powerPanel.getHeight();
							advPowerPanel.setSize(advPowerPanel.getWidth(), apPanelHeight);
							powerPanel.setSize(advPowerPanel.getWidth()-powerPanel.getX(), powerPanel.getHeight());
							panel.setPreferredSize(new Dimension(panel.getWidth(), panel.getHeight()+advPowerPanel.getHeight()));
							
							isNew = false;
							break;
						}
					}
				}
			});
		chckbxDemographics.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(chckbxDemographics.isSelected())
				{
					demographicsPanel.setVisible(true);
					physStatsPanel.setLocation(physStatsPanel.getX(), physStatsPanel.getY()+demographicsPanel.getHeight());
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), mentalStatsPanel.getY()+demographicsPanel.getHeight());
				}
				else if(!chckbxDemographics.isSelected())
				{
					demographicsPanel.setVisible(false);
					physStatsPanel.setLocation(physStatsPanel.getX(), physStatsPanel.getY()-demographicsPanel.getHeight());
					mentalStatsPanel.setLocation(mentalStatsPanel.getX(), mentalStatsPanel.getY()-demographicsPanel.getHeight());
				}
			}
		});
		
		class ResizeListener extends ComponentAdapter 
		{
	        public void componentResized(ComponentEvent e) 
	        {
	        	advPowerPanel.setLocation(advPowerPanel.getX(), mentalStatsPanel.getY()+mentalStatsPanel.getHeight()+30);
	        	panel.setSize(panel.getWidth(), advPowerPanel.getY()+advPowerPanel.getHeight()+30);
	        }
	        public void componentMoved(ComponentEvent e)
	        {
	        	advPowerPanel.setLocation(advPowerPanel.getX(), mentalStatsPanel.getY()+mentalStatsPanel.getHeight()+30);
	        	panel.setSize(panel.getWidth(), advPowerPanel.getY()+advPowerPanel.getHeight()+30);
	        }
		}
		mentalStatsPanel.addComponentListener(new ResizeListener());
				
		// new
		btnNew.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				CharacterSheet newCS = new CharacterSheet(nextSheetId);
				newCS.setName("");
				newCS.setPicture("blank.png");
				newCS.setAllDemographics(" % % % % % % % % % ");
				newCS.setAllMiscStats("1d6%0%3%0%0%0%0%0%30%30");
				newCS.setAllStats("2%0%0%0%0%0%2%0%0%0%0%0%0%2%0%0%0%0%0%0%2%0%0%0%0%0%0%0%2%0%0%0%0%0%0%2%0%0%0%0%0");
				
				newItem = null;
				currentSheet = newCS;

				chckbxAcro.setSelected(false);
				chckbxDodge.setSelected(false);
				chckbxHandToHand.setSelected(false);
				chckbxMeleeWeapons.setSelected(false);
				chckbxStealth.setSelected(false);

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

				chckbxBluff.setSelected(false);
				chckbxCharm.setSelected(false);
				chckbxIntimidation.setSelected(false);
				chckbxPersuasion.setSelected(false);
				chckbxWillpower.setSelected(false);

				
				nameSearchField.setText("");
				// load the entire dang sheet
				nameField.setText(currentSheet.getName());
				demographicsPanel.setNewCharacter(currentSheet);
				etab.setNewCharacter(currentSheet);	
				stab.setNewCharacter(currentSheet);
				ePane.setNewCharacter(currentSheet);
				weapon.set(items, currentSheet.getInv().getWeapon());
				weapon.setLocation(ePane.getX(), ePane.getY()+ePane.getHeight()+30);
				udoField.setText(currentSheet.getUdoDice() + "+" + currentSheet.getUdoBonus());
				bodyPointsField.setText(currentSheet.getBodyPointsCurrent() + "/" + currentSheet.getBodyPointsMax());
				speedField.setValue(currentSheet.getSpeed());
				locationField.setValue(currentSheet.getLocation());


				imgChange = false;
				try
				{
					imgLabel.setIcon(null);
					img = null;
					img = ImageIO.read(new File("images/sheets/blank.png"));
					img = Scalr.resize(img, 300, 350);
					icon.setImage(img);
					imgLabel.setIcon(icon);
					newPic = false;
				}
				catch (IOException e)
				{
				}
				 
				imgChange = true;

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
				presenceLevel.setValue(currentSheet.getPresence());
				bluffLevel.setValue(currentSheet.getBluff());
				charmLevel.setValue(currentSheet.getCharm());
				intimidationLevel.setValue(currentSheet.getIntimidation());
				persuasionLevel.setValue(currentSheet.getPersuasion());
				willpowerLevel.setValue(currentSheet.getWillpower());
				bluffTotal.setValue(currentSheet.getBluff() + currentSheet.getPresence());
				charmTotal.setValue(currentSheet.getCharm() + currentSheet.getPresence());
				intimidationTotal.setValue(currentSheet.getIntimidation() + currentSheet.getPresence());
				persuasionTotal.setValue(currentSheet.getPersuasion() + currentSheet.getPresence());
				willpowerTotal.setValue(currentSheet.getWillpower() + currentSheet.getPresence());	
				
				advs = advantagePanel.saveAdvs();
				disadvs = advantagePanel.saveDisadvs();
				powers = powerPanel.savePwrs();
				advantagePanel.setNewCharacter(0, 0, currentSheet, advs, disadvs);
				powerPanel.setNewCharacter(advantagePanel.getX()+advantagePanel.getWidth()+30, 0, currentSheet, powers);
				int apPanelHeight = advantagePanel.getHeight() >= powerPanel.getHeight() ? advantagePanel.getHeight() : powerPanel.getHeight();
				advPowerPanel.setSize(advPowerPanel.getWidth(), apPanelHeight);
				powerPanel.setSize(advPowerPanel.getWidth()-powerPanel.getX(), powerPanel.getHeight());
				panel.setPreferredSize(new Dimension(panel.getWidth(), panel.getHeight()+advPowerPanel.getHeight()));

				isNew = true;
			}
		});
		// copy
		btnCopy.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				
				CharacterSheet newCS = new CharacterSheet(nextSheetId);
				newCS.setName("Copy of " + currentSheet.getName());
				newCS.setPicture(currentSheet.getPicture());
				newCS.setAllDemographics(currentSheet.getAllDemographics());
				newCS.setAllMiscStats(currentSheet.getAllMiscStats());
				newCS.setAllStats(currentSheet.getAllStats());
				
				for(SkillSpec spec : currentSheet.getSkillSpecs())
				{
					SkillSpec newSpec = new SkillSpec(nextSpecId, newCS.getId(), spec.getSkill(), spec.getDescription());
					specs.add(newSpec);
					nextSpecId++;
				}			
				newCS.setSkillSpecs(specs);
				
				for(CharacterSheetAdvantage csa : currentSheet.getCSA())
				{
					CharacterSheetAdvantage newCSA = new CharacterSheetAdvantage(nextCSAId, newCS.getId(), csa.getAdv(), csa.getDescription());
					advs.add(newCSA);
					nextCSAId++;
				}
				newCS.setCSA(advs);
				
				for(CharacterSheetDisadvantage csd : currentSheet.getCSD())
				{
					CharacterSheetDisadvantage newCSD = new CharacterSheetDisadvantage(nextCSDId, newCS.getId(), csd.getDisadv(), csd.getDescription());
					disadvs.add(newCSD);
					nextCSDId++;
				}
				newCS.setCSD(disadvs);
				
				for(CharacterSheetPower csp : currentSheet.getCSP())
				{
					CharacterSheetPower newCSP = new CharacterSheetPower(nextCSPId, newCS.getId(), csp.getLevel(), csp.getPower());
					if(csp.getSpecs() != null)
						newCSP.setSpecs(csp.getSpecs());
					if(csp.getWeakness() != null)
						newCSP.setWeakness(csp.getWeakness());
					powers.add(newCSP);
					nextCSPId++;
				}
				newCS.setCSP(powers);
				
				Inventory newInv = new Inventory(newCS.getId());
				newInv.setInventory(currentSheet.getInv().getAllHeld(), currentSheet.getInv().getStorageSplit());
				invs.add(newInv);
				newCS.setInv(invs);
				
				SpellInventory spellInv = currentSheet.getSpellInv();
				SpellInventory newSpellInv = new SpellInventory(newCS.getId());
				newSpellInv.setSpellInventory(spellInv.getSigSplit(), spellInv.getFirstSplit(), spellInv.getSecondSplit(), spellInv.getThirdSplit(), spellInv.getFourthSplit(), spellInv.getSlotsSplit());
				spellInvs.add(newSpellInv);
				newCS.setSpellInv(spellInvs);
				
				
				newItem = null;
				currentSheet = newCS;
				
				chckbxAcro.setSelected(false);
				chckbxDodge.setSelected(false);
				chckbxHandToHand.setSelected(false);
				chckbxMeleeWeapons.setSelected(false);
				chckbxStealth.setSelected(false);
				
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
				
				chckbxBluff.setSelected(false);
				chckbxCharm.setSelected(false);
				chckbxIntimidation.setSelected(false);
				chckbxPersuasion.setSelected(false);
				chckbxWillpower.setSelected(false);
				
				
				nameSearchField.setText("");
				// load the entire dang sheet
				nameField.setText(currentSheet.getName());
				demographicsPanel.setNewCharacter(currentSheet);
				etab.setNewCharacter(currentSheet);	
				stab.setNewCharacter(currentSheet);
				ePane.setNewCharacter(currentSheet);
				weapon.set(items, currentSheet.getInv().getWeapon());
				weapon.setLocation(ePane.getX(), ePane.getY()+ePane.getHeight()+30);
				udoField.setText(currentSheet.getUdoDice() + "+" + currentSheet.getUdoBonus());
				bodyPointsField.setText(currentSheet.getBodyPointsCurrent() + "/" + currentSheet.getBodyPointsMax());
				speedField.setValue(currentSheet.getSpeed());
				locationField.setValue(currentSheet.getLocation());
				
				

				
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
				presenceLevel.setValue(currentSheet.getPresence());
				bluffLevel.setValue(currentSheet.getBluff());
				charmLevel.setValue(currentSheet.getCharm());
				intimidationLevel.setValue(currentSheet.getIntimidation());
				persuasionLevel.setValue(currentSheet.getPersuasion());
				willpowerLevel.setValue(currentSheet.getWillpower());
				bluffTotal.setValue(currentSheet.getBluff() + currentSheet.getPresence());
				charmTotal.setValue(currentSheet.getCharm() + currentSheet.getPresence());
				intimidationTotal.setValue(currentSheet.getIntimidation() + currentSheet.getPresence());
				persuasionTotal.setValue(currentSheet.getPersuasion() + currentSheet.getPresence());
				willpowerTotal.setValue(currentSheet.getWillpower() + currentSheet.getPresence());	
				
				advs = advantagePanel.saveAdvs();
				disadvs = advantagePanel.saveDisadvs();
				powers = powerPanel.savePwrs();
				advantagePanel.setNewCharacter(0, 0, currentSheet, advs, disadvs);
				powerPanel.setNewCharacter(advantagePanel.getX()+advantagePanel.getWidth()+30, 0, currentSheet, powers);
				int apPanelHeight = advantagePanel.getHeight() >= powerPanel.getHeight() ? advantagePanel.getHeight() : powerPanel.getHeight();
				advPowerPanel.setSize(advPowerPanel.getWidth(), apPanelHeight);
				powerPanel.setSize(advPowerPanel.getWidth()-powerPanel.getX(), powerPanel.getHeight());
				panel.setPreferredSize(new Dimension(panel.getWidth(), panel.getHeight()+advPowerPanel.getHeight()));
				
				isNew = true;
			}
		});

				
		nameSearchField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) 
			{
				searchList.clear();
				if(!nameSearchField.getText().equals(""))
				{
					for(CharacterSheet s : sheets)
					{
						if(s.getName().toLowerCase().contains(nameSearchField.getText().toLowerCase()))
							searchList.add(s.getName());
					}
					Collections.sort(searchList);
				}
				if(!searchList.isEmpty())
				{
					searchesList.setListData(searchList.toArray());
					searches.setVisible(true);
				}
				if(searchList.isEmpty())
				{
					searches.setVisible(false);
				}
					
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					btnLoad.doClick();
					btnLoad.requestFocus();
				}
			}
		});
		
		nameSearchField.addFocusListener(new FocusAdapter() 
		{
			@Override
			public void focusGained(FocusEvent e) 
			{	
				if(!searchList.isEmpty())
				{
					searchesList.setListData(searchList.toArray());
					searches.setVisible(true);
				}
				if(searchList.isEmpty())
				{
					searches.setVisible(false);
				}
			}
			
			@Override
			public void focusLost(FocusEvent e)
			{
				searches.setVisible(false);
			}
		});
		
		ListSelectionListener listSelectionListener = new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent arg0) 
			{
				if(searchesList.getSelectedValue() != null)
					nameSearchField.setText(searchesList.getSelectedValue().toString());
				panel.grabFocus();
			}	
		};
		searchesList.addListSelectionListener(listSelectionListener);
		
		
		advantagePanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				if(advantagePanel.getHeight() >= powerPanel.getHeight())
					advPowerPanel.setSize(advPowerPanel.getWidth(), advantagePanel.getHeight());
				else
					advPowerPanel.setSize(advPowerPanel.getWidth(), powerPanel.getHeight());
				resize(panel, advPowerPanel);
			}
		});
		powerPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) 
			{
				if(advantagePanel.getHeight() >= powerPanel.getHeight())
					advPowerPanel.setSize(advPowerPanel.getWidth(), advantagePanel.getHeight());
				else
					advPowerPanel.setSize(advPowerPanel.getWidth(), powerPanel.getHeight());
				resize(panel, advPowerPanel);
			}
		});		
		
		panel.setPreferredSize(new Dimension(1884, advPowerPanel.getY()+advPowerPanel.getHeight()));

	}
	
	public void resize(JPanel pane, JPanel advPow)
	{
		pane.setPreferredSize(new Dimension(pane.getWidth(), advPow.getY()+advPow.getHeight()+30));
	}
	
	public int setPanelSize(int h1, int h2, int h3)
	{
		if(h1 >= h2 && h1 >= h3)
			return h1;
		else if(h2 >= h3)
			return h2;
		else
			return h3;
	}
}
