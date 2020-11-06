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

public class MainWindow {

	private static TextFile dao = new TextFile();
	private static List<CharacterSheet> sheets;
	private static List<SkillSpec> specs;
	private static List<CharacterSheetAdvantage> advs;
	private static List<CharacterSheetDisadvantage> disadvs;
	
	private JFrame frmDcrpgCharacterManager;
	private final Action action = new SwingAction();

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

		
		CharacterSheet batman = new CharacterSheet(nextSheetId);
		batman.setName("Batman");
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
		frmDcrpgCharacterManager.setBounds(100, 100, 1400, 800);
		frmDcrpgCharacterManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDcrpgCharacterManager.getContentPane().setLayout(null);
		
		JList<CharacterSheet> list = new JList<>();
		list.setBackground(new Color(255, 255, 255));
		list.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		list.setBounds(12, 82, 98, -69);
		list.setListData((CharacterSheet[])sheets.toArray());
		frmDcrpgCharacterManager.getContentPane().add(list);
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
