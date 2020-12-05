package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import business.CharacterSheet;
import business.CharacterSheetPower;
import business.Item;
import business.Spell;



public class SpellTab extends JScrollPane 
{
	private static List<String> searchList = new ArrayList<String>();

	private JPanel panel;
	private JFormattedTextField nameField;
	private Spell search;
	private JLabel level1Max;
	private JLabel level2Max;
	private JLabel level3Max;
	private JLabel level4Max;
	
	private CharacterSheet cs;
	
	public SpellTab(CharacterSheet cs)
	{
		super();
		setPreferredSize(MainWindow.tabbedPane.getSize());
		
		this.cs = cs;
		
		panel = new JPanel();
		setViewportView(panel);
		panel.setLayout(null);
		panel.setSize(1894,  1008);
		panel.setPreferredSize(new Dimension(1894, 1008));
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setBounds(0, 0, 107, 42);
		panel.add(nameLabel);
		nameLabel.setFont(new Font("Dialog", Font.PLAIN, 40));
		
		nameField = new JFormattedTextField();
		nameField.setBounds(117, 0, 367, 42);
		panel.add(nameField);
		nameField.setFont(new Font("Dialog", Font.PLAIN, 24));
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setText(cs.getName());
		nameField.setEnabled(false);
		
		JButton btnSave = new JButton("Save All Spells");
		btnSave.setBounds(5, 47, 120, 29);
		panel.add(btnSave);
		btnSave.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				//saveSpells(true);
			}
		});
		
		JButton btnReset = new JButton("Reset Spell Slots");
		btnReset.setBounds(135, 47, 140, 29);
		panel.add(btnReset);
		btnReset.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				//saveSpells(true);
			}
		});
		
		JLabel searchLabel = new JLabel("Search");
		searchLabel.setBounds(494, 0, 157, 42);
		panel.add(searchLabel);
		searchLabel.setFont(new Font("Dialog", Font.PLAIN, 40));
		
		JFormattedTextField searchField = new JFormattedTextField();
		searchField.setBounds(494, 47, 157, 29);
		panel.add(searchField);
		searchField.setHorizontalAlignment(SwingConstants.RIGHT);
		searchField.setText("(search for spell by name)");
		
		search = new Spell();
		search.setLocation(661, 0);
		panel.add(search);
		
		JPanel searches = new JPanel();
		searches.setLayout(null);
		searches.setBounds(searchField.getX(), searchField.getY()+searchField.getHeight(), searchField.getWidth(), 100);
		JList searchesList = new JList();
		searchesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jsp = new JScrollPane(searchesList);
		jsp.setBounds(0, 0, searches.getWidth(), searches.getHeight());
		searches.add(jsp);
		panel.add(searches);
		searches.setVisible(false);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.setBounds(494, 81, 65, 24);
		panel.add(btnLoad);
		btnLoad.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				for(Spell s : MainWindow.spells)
				{
					if(s.getName().toLowerCase().equals(searchField.getText().toLowerCase()))
					{
						search.set(s.getId());
						break;
					}
				}
				
			}
		});
		
		searchField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) 
			{
				searchList.clear();
				if(!searchField.getText().equals(""))
				{
					for(Spell s : MainWindow.spells)
					{
						if(s.getName().toLowerCase().contains(searchField.getText().toLowerCase()))
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
		
		searchField.addFocusListener(new FocusAdapter() 
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
					searchField.setText(searchesList.getSelectedValue().toString());
				panel.grabFocus();
			}	
		};
		searchesList.addListSelectionListener(listSelectionListener);
		
		JLabel slotsLabel = new JLabel("Spell Slots");
		slotsLabel.setBounds(search.getX()+search.getWidth()+10, 0, 200, 42);
		panel.add(slotsLabel);
		slotsLabel.setFont(new Font("Dialog", Font.PLAIN, 40));
		
		JLabel slots1Label = new JLabel("Level 1");
		slots1Label.setBounds(slotsLabel.getWidth()+slotsLabel.getX()+10, 0, 140, 42);
		panel.add(slots1Label);
		slots1Label.setFont(new Font("Dialog", Font.PLAIN, 40));
		
		JFormattedTextField level1Current = new JFormattedTextField();
		level1Current.setBounds(slots1Label.getX()+slots1Label.getWidth(), 0, 70, 70);
		panel.add(level1Current);
		level1Current.setFont(new Font("Dialog", Font.PLAIN, 70));
		level1Current.setHorizontalAlignment(SwingConstants.CENTER);
		
		level1Max = new JLabel("/ 0");
		level1Max.setBounds(level1Current.getX()+level1Current.getWidth()+20, 0, 100, 70);
		panel.add(level1Max);
		level1Max.setFont(new Font("Dialog", Font.PLAIN, 70));
		
		JLabel slots2Label = new JLabel("Level 2");
		slots2Label.setBounds(slots1Label.getX()+slots1Label.getWidth()+200, 0, 140, 42);
		panel.add(slots2Label);
		slots2Label.setFont(new Font("Dialog", Font.PLAIN, 40));
		
		JFormattedTextField level2Current = new JFormattedTextField();
		level2Current.setBounds(slots2Label.getX()+slots2Label.getWidth(), 0, 70, 70);
		panel.add(level2Current);
		level2Current.setFont(new Font("Dialog", Font.PLAIN, 70));
		level2Current.setHorizontalAlignment(SwingConstants.CENTER);
		
		level2Max = new JLabel("/ 0");
		level2Max.setBounds(level2Current.getX()+level2Current.getWidth()+20, 0, 100, 70);
		panel.add(level2Max);
		level2Max.setFont(new Font("Dialog", Font.PLAIN, 70));
		
		JLabel slots3Label = new JLabel("Level 3");
		slots3Label.setBounds(slotsLabel.getWidth()+slotsLabel.getX()+10, 100, 140, 42);
		panel.add(slots3Label);
		slots3Label.setFont(new Font("Dialog", Font.PLAIN, 40));
		
		JFormattedTextField level3Current = new JFormattedTextField();
		level3Current.setBounds(slots3Label.getX()+slots3Label.getWidth(), 100, 70, 70);
		panel.add(level3Current);
		level3Current.setFont(new Font("Dialog", Font.PLAIN, 70));
		level3Current.setHorizontalAlignment(SwingConstants.CENTER);
		
		level3Max = new JLabel("/ 0");
		level3Max.setBounds(level3Current.getX()+level3Current.getWidth()+20, 100, 100, 70);
		panel.add(level3Max);
		level3Max.setFont(new Font("Dialog", Font.PLAIN, 70));
		
		JLabel slots4Label = new JLabel("Level 4");
		slots4Label.setBounds(slots1Label.getX()+slots1Label.getWidth()+200, 100, 140, 42);
		panel.add(slots4Label);
		slots4Label.setFont(new Font("Dialog", Font.PLAIN, 40));
		
		JFormattedTextField level4Current = new JFormattedTextField();
		level4Current.setBounds(slots4Label.getX()+slots4Label.getWidth(), 100, 70, 70);
		panel.add(level4Current);
		level4Current.setFont(new Font("Dialog", Font.PLAIN, 70));
		level4Current.setHorizontalAlignment(SwingConstants.CENTER);
		
		level4Max = new JLabel("/ 0");
		level4Max.setBounds(level4Current.getX()+level4Current.getWidth()+20, 100, 100, 70);
		panel.add(level4Max);
		level4Max.setFont(new Font("Dialog", Font.PLAIN, 70));
		
		JLabel sigLabel = new JLabel("Signature Spells");
		sigLabel.setBounds(0, 205, 382, 42);
		panel.add(sigLabel);
		sigLabel.setFont(new Font("Dialog", Font.PLAIN, 40));
		sigLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel sigSpells = new JPanel();
		sigSpells.setBackground(Color.BLACK);
		sigSpells.setLayout(null);
		sigSpells.setPreferredSize(new Dimension(364, 1369));
		JScrollPane sigSpellsScroll = new JScrollPane(sigSpells);
		sigSpellsScroll.setBounds(0, 250, 382, 760);
		panel.add(sigSpellsScroll);
		
		JLabel level1Label = new JLabel("Level 1");
		level1Label.setBounds(383, 205, 382, 42);
		panel.add(level1Label);
		level1Label.setFont(new Font("Dialog", Font.PLAIN, 40));
		level1Label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel level1Spells = new JPanel();
		level1Spells.setBackground(Color.BLACK);
		level1Spells.setLayout(null);
		level1Spells.setPreferredSize(new Dimension(364, 1369));
		JScrollPane level1SpellsScroll = new JScrollPane(level1Spells);
		level1SpellsScroll.setBounds(383, 250, 382, 760);
		panel.add(level1SpellsScroll);
		
		JLabel level2Label = new JLabel("Level 2");
		level2Label.setBounds(766, 205, 382, 42);
		panel.add(level2Label);
		level2Label.setFont(new Font("Dialog", Font.PLAIN, 40));
		level2Label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel level2Spells = new JPanel();
		level2Spells.setBackground(Color.BLACK);
		level2Spells.setLayout(null);
		level2Spells.setPreferredSize(new Dimension(364, 1369));
		JScrollPane level2SpellsScroll = new JScrollPane(level2Spells);
		level2SpellsScroll.setBounds(766, 250, 382, 760);
		panel.add(level2SpellsScroll);
		
		JLabel level3Label = new JLabel("Level 3");
		level3Label.setBounds(1149, 205, 382, 42);
		panel.add(level3Label);
		level3Label.setFont(new Font("Dialog", Font.PLAIN, 40));
		level3Label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel level3Spells = new JPanel();
		level3Spells.setBackground(Color.BLACK);
		level3Spells.setLayout(null);
		level3Spells.setPreferredSize(new Dimension(364, 1369));
		JScrollPane level3SpellsScroll = new JScrollPane(level3Spells);
		level3SpellsScroll.setBounds(1149, 250, 382, 760);
		panel.add(level3SpellsScroll);
		
		JLabel level4Label = new JLabel("Level 4");
		level4Label.setBounds(1512, 205, 382, 42);
		panel.add(level4Label);
		level4Label.setFont(new Font("Dialog", Font.PLAIN, 40));
		level4Label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel level4Spells = new JPanel();
		level4Spells.setBackground(Color.BLACK);
		level4Spells.setLayout(null);
		level4Spells.setPreferredSize(new Dimension(364, 1369));
		JScrollPane level4SpellsScroll = new JScrollPane(level4Spells);
		level4SpellsScroll.setBounds(1512, 250, 382, 760);
		panel.add(level4SpellsScroll);
		
		setNewCharacter(cs);
	}
	
	public void setNewCharacter(CharacterSheet cs)
	{
		this.cs = cs;
		List<CharacterSheetPower> pwrs = cs.getCSP();
		for(CharacterSheetPower csp : pwrs)
		{
			if(csp.getPowerStr().equalsIgnoreCase("WIZARDRY") || csp.getPowerStr().equalsIgnoreCase("SORCERY"))
			{
				if(csp.getLevel() == 10)
				{
					level1Max.setText("/ X");
					level2Max.setText("/ X");
					level3Max.setText("/10");
					level4Max.setText("/ 4");
				}
				else if(csp.getLevel() == 9)
				{
					level1Max.setText("/ X");
					level2Max.setText("/ X");
					level3Max.setText("/ 8");
					level4Max.setText("/ 3");
				}
				else if(csp.getLevel() == 8)
				{
					level1Max.setText("/ X");
					level2Max.setText("/10");
					level3Max.setText("/ 7");
					level4Max.setText("/ 2");
				}
				else if(csp.getLevel() == 7)
				{
					level1Max.setText("/ X");
					level2Max.setText("/10");
					level3Max.setText("/ 5");
					level4Max.setText("/ 2");
				}
				else if(csp.getLevel() == 6)
				{
					level1Max.setText("/ X");
					level2Max.setText("/ 7");
					level3Max.setText("/ 4");
					level4Max.setText("/ 2");
				}
			}
		}
	}
	
	
}
