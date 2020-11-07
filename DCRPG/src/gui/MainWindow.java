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
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import java.awt.Label;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.TextField;

public class MainWindow {

	private static TextFile dao = new TextFile();
	private static List<CharacterSheet> sheets;
	private static String[] sheetNames;
	private static List<SkillSpec> specs;
	private static List<CharacterSheetAdvantage> advs;
	private static List<CharacterSheetDisadvantage> disadvs;
	private static CharacterSheet currentSheet;
	
	private JFrame frmDcrpgCharacterManager;
	private final Action action = new SwingAction();
	private JTextField nameSearchText;
	private JTextField nameField;
	private JTextField udoField;
	private JTextField bodyPointsField;
	private JTextField speedField;

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
					window.frmDcrpgCharacterManager.setVisible(true);
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
	private void initialize() {
		frmDcrpgCharacterManager = new JFrame();
		frmDcrpgCharacterManager.getContentPane().setBackground(UIManager.getColor("Panel.background"));
		frmDcrpgCharacterManager.setTitle("DCRPG App");
		frmDcrpgCharacterManager.setForeground(new Color(204, 0, 51));
		frmDcrpgCharacterManager.setBackground(Color.RED);
		frmDcrpgCharacterManager.setBounds(100, 100, 1920, 1080);
		frmDcrpgCharacterManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDcrpgCharacterManager.getContentPane().setLayout(null);
		
		JButton loadButton = new JButton("Load");
		loadButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for(CharacterSheet s : sheets)
				{
					if(s.getName().equals(nameSearchText.getText()))
							currentSheet = s;
					System.out.println(currentSheet.getName());
				}
			}
		});
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		loadButton.setBounds(167, 11, 69, 23);
		frmDcrpgCharacterManager.getContentPane().add(loadButton);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblNewLabel.setBounds(484, 6, 99, 28);
		frmDcrpgCharacterManager.getContentPane().add(lblNewLabel);
		
		nameSearchText = new JTextField();
		nameSearchText.setText("(type character name)");
		nameSearchText.setBounds(10, 12, 148, 20);
		frmDcrpgCharacterManager.getContentPane().add(nameSearchText);
		nameSearchText.setColumns(10);
		
		nameField = new JTextField();
		nameField.setBounds(570, 12, 112, 20);
		frmDcrpgCharacterManager.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("UDO");
		lblNewLabel_1.setBounds(10, 57, 62, 14);
		frmDcrpgCharacterManager.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Body Points");
		lblNewLabel_2.setBounds(10, 82, 112, 14);
		frmDcrpgCharacterManager.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Speed");
		lblNewLabel_3.setBounds(10, 107, 46, 14);
		frmDcrpgCharacterManager.getContentPane().add(lblNewLabel_3);
		
		udoField = new JTextField();
		udoField.setBounds(83, 54, 86, 20);
		frmDcrpgCharacterManager.getContentPane().add(udoField);
		udoField.setColumns(10);
		
		bodyPointsField = new JTextField();
		bodyPointsField.setBounds(83, 82, 86, 20);
		frmDcrpgCharacterManager.getContentPane().add(bodyPointsField);
		bodyPointsField.setColumns(10);
		
		speedField = new JTextField();
		speedField.setBounds(83, 107, 86, 20);
		frmDcrpgCharacterManager.getContentPane().add(speedField);
		speedField.setColumns(10);
		
		JButton newButton = new JButton("New");
		newButton.setBounds(247, 11, 69, 23);
		frmDcrpgCharacterManager.getContentPane().add(newButton);
		
		Panel reflexesPanel = new Panel();
		reflexesPanel.setBackground(new Color(255, 102, 102));
		reflexesPanel.setBounds(44, 149, 354, 470);
		frmDcrpgCharacterManager.getContentPane().add(reflexesPanel);
		reflexesPanel.setLayout(null);
		
		Label label = new Label("Reflexes");
		label.setFont(new Font("Verdana", Font.BOLD, 22));
		label.setBounds(132, 10, 90, 30);
		reflexesPanel.add(label);
		
		TextField textField = new TextField();
		textField.setBounds(294, 10, 50, 35);
		reflexesPanel.add(textField);
		
		Panel panel = new Panel();
		panel.setBackground(new Color(255, 153, 153));
		panel.setBounds(0, 50, 354, 60);
		reflexesPanel.add(panel);
		panel.setLayout(null);
		
		Label label_1 = new Label("Acrobatics");
		label_1.setBounds(35, 5, 120, 22);
		panel.add(label_1);
		label_1.setFont(new Font("Verdana", Font.BOLD, 13));
		
		TextField textField_1 = new TextField();
		textField_1.setBounds(5, 5, 24, 22);
		panel.add(textField_1);
		
		TextField textField_2 = new TextField();
		textField_2.setBackground(new Color(255, 204, 204));
		textField_2.setBounds(5, 33, 279, 22);
		panel.add(textField_2);
		
		TextField textField_3 = new TextField();
		textField_3.setBounds(294, 13, 50, 34);
		panel.add(textField_3);
		
		Panel panel_6 = new Panel();
		panel_6.setLayout(null);
		panel_6.setBackground(new Color(255, 102, 102));
		panel_6.setBounds(0, 110, 354, 60);
		reflexesPanel.add(panel_6);
		
		Label label_1_1 = new Label("Dodge");
		label_1_1.setFont(new Font("Verdana", Font.BOLD, 13));
		label_1_1.setBounds(35, 5, 120, 22);
		panel_6.add(label_1_1);
		
		TextField textField_1_1 = new TextField();
		textField_1_1.setBounds(5, 5, 24, 22);
		panel_6.add(textField_1_1);
		
		TextField textField_2_1 = new TextField();
		textField_2_1.setBackground(new Color(255, 204, 204));
		textField_2_1.setBounds(5, 33, 279, 22);
		panel_6.add(textField_2_1);
		
		TextField textField_3_1 = new TextField();
		textField_3_1.setBounds(294, 13, 50, 34);
		panel_6.add(textField_3_1);
		
		Panel panel_7 = new Panel();
		panel_7.setLayout(null);
		panel_7.setBackground(new Color(255, 153, 153));
		panel_7.setBounds(0, 170, 354, 60);
		reflexesPanel.add(panel_7);
		
		Label label_1_2 = new Label("Hand-to-Hand");
		label_1_2.setFont(new Font("Verdana", Font.BOLD, 13));
		label_1_2.setBounds(35, 5, 120, 22);
		panel_7.add(label_1_2);
		
		TextField textField_1_2 = new TextField();
		textField_1_2.setBounds(5, 5, 24, 22);
		panel_7.add(textField_1_2);
		
		TextField textField_2_2 = new TextField();
		textField_2_2.setBackground(new Color(255, 204, 204));
		textField_2_2.setBounds(5, 33, 279, 22);
		panel_7.add(textField_2_2);
		
		TextField textField_3_2 = new TextField();
		textField_3_2.setBounds(294, 13, 50, 34);
		panel_7.add(textField_3_2);
		
		Panel panel_8 = new Panel();
		panel_8.setLayout(null);
		panel_8.setBackground(new Color(255, 102, 102));
		panel_8.setBounds(0, 230, 354, 60);
		reflexesPanel.add(panel_8);
		
		Label label_1_3 = new Label("Melee Weapons");
		label_1_3.setFont(new Font("Verdana", Font.BOLD, 13));
		label_1_3.setBounds(35, 5, 120, 22);
		panel_8.add(label_1_3);
		
		TextField textField_1_3 = new TextField();
		textField_1_3.setBounds(5, 5, 24, 22);
		panel_8.add(textField_1_3);
		
		TextField textField_2_3 = new TextField();
		textField_2_3.setBackground(new Color(255, 204, 204));
		textField_2_3.setBounds(5, 33, 279, 22);
		panel_8.add(textField_2_3);
		
		TextField textField_3_3 = new TextField();
		textField_3_3.setBounds(294, 13, 50, 34);
		panel_8.add(textField_3_3);
		
		Panel panel_9 = new Panel();
		panel_9.setLayout(null);
		panel_9.setBackground(new Color(255, 153, 153));
		panel_9.setBounds(0, 290, 354, 60);
		reflexesPanel.add(panel_9);
		
		Label label_1_4 = new Label("Stealth");
		label_1_4.setFont(new Font("Verdana", Font.BOLD, 13));
		label_1_4.setBounds(35, 5, 120, 22);
		panel_9.add(label_1_4);
		
		TextField textField_1_4 = new TextField();
		textField_1_4.setBounds(5, 5, 24, 22);
		panel_9.add(textField_1_4);
		
		TextField textField_2_4 = new TextField();
		textField_2_4.setBackground(new Color(255, 204, 204));
		textField_2_4.setBounds(5, 33, 279, 22);
		panel_9.add(textField_2_4);
		
		TextField textField_3_4 = new TextField();
		textField_3_4.setBounds(294, 13, 50, 34);
		panel_9.add(textField_3_4);
		
		Panel panel_10 = new Panel();
		panel_10.setLayout(null);
		panel_10.setBackground(new Color(255, 102, 102));
		panel_10.setBounds(0, 350, 354, 60);
		reflexesPanel.add(panel_10);
		
		Label label_1_5 = new Label("Thrown Weapons");
		label_1_5.setFont(new Font("Verdana", Font.BOLD, 13));
		label_1_5.setBounds(35, 5, 120, 22);
		panel_10.add(label_1_5);
		
		TextField textField_1_5 = new TextField();
		textField_1_5.setBounds(5, 5, 24, 22);
		panel_10.add(textField_1_5);
		
		TextField textField_2_5 = new TextField();
		textField_2_5.setBackground(new Color(255, 204, 204));
		textField_2_5.setBounds(5, 33, 279, 22);
		panel_10.add(textField_2_5);
		
		TextField textField_3_5 = new TextField();
		textField_3_5.setBounds(294, 13, 50, 34);
		panel_10.add(textField_3_5);
		
		Panel panel_11 = new Panel();
		panel_11.setLayout(null);
		panel_11.setBackground(new Color(255, 153, 153));
		panel_11.setBounds(0, 410, 354, 60);
		reflexesPanel.add(panel_11);
		
		Label label_1_6 = new Label("Placeholder");
		label_1_6.setFont(new Font("Verdana", Font.BOLD, 13));
		label_1_6.setBounds(35, 5, 120, 22);
		panel_11.add(label_1_6);
		
		TextField textField_1_6 = new TextField();
		textField_1_6.setBounds(5, 5, 24, 22);
		panel_11.add(textField_1_6);
		
		TextField textField_2_6 = new TextField();
		textField_2_6.setBackground(new Color(255, 204, 204));
		textField_2_6.setBounds(5, 33, 279, 22);
		panel_11.add(textField_2_6);
		
		TextField textField_3_6 = new TextField();
		textField_3_6.setBounds(294, 13, 50, 34);
		panel_11.add(textField_3_6);
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
