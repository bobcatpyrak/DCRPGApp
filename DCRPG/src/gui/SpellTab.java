package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
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
import business.Inventory;
import business.Item;
import business.Spell;
import business.SpellInventory;



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
	private JFormattedTextField level1Current;
	private JFormattedTextField level2Current;
	private JFormattedTextField level3Current;
	private JFormattedTextField level4Current;
	
	private JPanel sigSpells;
	private JPanel level1Spells;
	private JPanel level2Spells;
	private JPanel level3Spells;
	private JPanel level4Spells;
	
	private CharacterSheet cs;
	private List<Spell> sig = new ArrayList<Spell>();
	private List<Spell> first = new ArrayList<Spell>();
	private List<Spell> second = new ArrayList<Spell>();
	private List<Spell> third = new ArrayList<Spell>();
	private List<Spell> fourth = new ArrayList<Spell>();
	
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
		nameField.setBounds(117, 0, 340, 42);
		panel.add(nameField);
		nameField.setFont(new Font("Dialog", Font.PLAIN, 22));
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
				saveSpells(true);
			}
		});
		
		JButton btnReset = new JButton("Reset Spell Slots");
		btnReset.setBounds(135, 47, 140, 29);
		panel.add(btnReset);
		btnReset.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				resetSlots();
			}
		});
		
		JLabel searchLabel = new JLabel("Search");
		searchLabel.setBounds(467, 0, 157, 22);
		panel.add(searchLabel);
		searchLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		JFormattedTextField searchField = new JFormattedTextField();
		searchField.setBounds(searchLabel.getX(), searchLabel.getY()+searchLabel.getHeight()+5, 157, 29);
		panel.add(searchField);
		searchField.setHorizontalAlignment(SwingConstants.RIGHT);
		searchField.setText("(search for spell by name)");
		
		search = new Spell();
		search.setLocation(634, 0);
		panel.add(search);
		search.set(0);
		
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
		btnLoad.setBounds(searchField.getX(), searchField.getY()+searchField.getHeight()+5, 65, 24);
		panel.add(btnLoad);
		
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
		
		/*JLabel saveLabel = new JLabel("Save");
		saveLabel.setBounds(btnLoad.getX(), btnLoad.getY()+btnLoad.getHeight()+10, 157, 22);
		panel.add(saveLabel);
		saveLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		JFormattedTextField saveField = new JFormattedTextField();
		saveField.setBounds(saveLabel.getX(), saveLabel.getY()+saveLabel.getHeight()+5, 157, 29);
		panel.add(saveField);
		saveField.setHorizontalAlignment(SwingConstants.RIGHT);
		saveField.setText("(name of spell to save)");
		
		JButton btnSaveSpell = new JButton("Save");
		btnSaveSpell.setBounds(saveField.getX(), saveField.getY()+saveField.getHeight()+5, 65, 24);
		panel.add(btnSaveSpell);
		btnSaveSpell.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				saveSpell(saveField.getText(), search);
			}
		});*/
		
		btnLoad.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				for(Spell s : MainWindow.spells)
				{
					if(s.getName().toLowerCase().equals(searchField.getText().toLowerCase()))
					{
						search.set(s.getId());
					//	saveField.setText(s.getName());
						break;
					}
				}
				
			}
		});
		
		JLabel slotsLabel = new JLabel("Spell Slots");
		slotsLabel.setBounds(search.getX()+search.getWidth()+10, 0, 200, 42);
		panel.add(slotsLabel);
		slotsLabel.setFont(new Font("Dialog", Font.PLAIN, 40));
		
		JLabel slots1Label = new JLabel("Level 1");
		slots1Label.setBounds(slotsLabel.getWidth()+slotsLabel.getX()+10, 0, 140, 42);
		panel.add(slots1Label);
		slots1Label.setFont(new Font("Dialog", Font.PLAIN, 40));
		
		level1Current = new JFormattedTextField();
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
		
		level2Current = new JFormattedTextField();
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
		
		level3Current = new JFormattedTextField();
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
		
		level4Current = new JFormattedTextField();
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

		sigSpells = new JPanel();
		sigSpells.setBackground(Color.BLACK);
		sigSpells.setLayout(null);
		sigSpells.setPreferredSize(new Dimension(364, 184));
		JScrollPane sigSpellsScroll = new JScrollPane(sigSpells);
		sigSpellsScroll.setBounds(0, 250, 382, 760);
		panel.add(sigSpellsScroll);
		sigSpellsScroll.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);

		
		JButton btnAddSig = new JButton("+");
		btnAddSig.setBackground(new Color(0, 250, 154));
		btnAddSig.setMargin(new Insets(0,0,0,0));
		btnAddSig.setFont(new Font("Dialog", Font.BOLD, 40));
		btnAddSig.setBounds(sigSpellsScroll.getX()+sigSpellsScroll.getWidth()-40, sigSpellsScroll.getY()-40, 40, 40);
		panel.add(btnAddSig);
		btnAddSig.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				addSpell(sigSpells, sigSpellsScroll, sig);
			}
		});
		
		JLabel level1Label = new JLabel("Level 1");
		level1Label.setBounds(383, 205, 382, 42);
		panel.add(level1Label);
		level1Label.setFont(new Font("Dialog", Font.PLAIN, 40));
		level1Label.setHorizontalAlignment(SwingConstants.CENTER);
		
		level1Spells = new JPanel();
		level1Spells.setBackground(Color.BLACK);
		level1Spells.setLayout(null);
		level1Spells.setPreferredSize(new Dimension(364, 184));
		JScrollPane level1SpellsScroll = new JScrollPane(level1Spells);
		level1SpellsScroll.setBounds(383, 250, 382, 760);
		panel.add(level1SpellsScroll);
		level1SpellsScroll.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);

		
		JButton btnAdd1st = new JButton("+");
		btnAdd1st.setBackground(Color.magenta);
		btnAdd1st.setMargin(new Insets(0,0,0,0));
		btnAdd1st.setFont(new Font("Dialog", Font.BOLD, 40));
		btnAdd1st.setBounds(level1SpellsScroll.getX()+level1SpellsScroll.getWidth()-40, level1SpellsScroll.getY()-40, 40, 40);
		panel.add(btnAdd1st);
		btnAdd1st.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				addSpell(level1Spells, level1SpellsScroll, first);
			}
		});
		
		JLabel level2Label = new JLabel("Level 2");
		level2Label.setBounds(766, 205, 382, 42);
		panel.add(level2Label);
		level2Label.setFont(new Font("Dialog", Font.PLAIN, 40));
		level2Label.setHorizontalAlignment(SwingConstants.CENTER);
		
		level2Spells = new JPanel();
		level2Spells.setBackground(Color.BLACK);
		level2Spells.setLayout(null);
		level2Spells.setPreferredSize(new Dimension(364, 184));
		JScrollPane level2SpellsScroll = new JScrollPane(level2Spells);
		level2SpellsScroll.setBounds(766, 250, 382, 760);
		panel.add(level2SpellsScroll);
		level2SpellsScroll.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);

		
		JButton btnAdd2nd = new JButton("+");
		btnAdd2nd.setBackground(new Color(0, 250, 154));
		btnAdd2nd.setMargin(new Insets(0,0,0,0));
		btnAdd2nd.setFont(new Font("Dialog", Font.BOLD, 40));
		btnAdd2nd.setBounds(level2SpellsScroll.getX()+level2SpellsScroll.getWidth()-40, level2SpellsScroll.getY()-40, 40, 40);
		panel.add(btnAdd2nd);
		btnAdd2nd.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				addSpell(level2Spells, level2SpellsScroll, second);
			}
		});
		
		JLabel level3Label = new JLabel("Level 3");
		level3Label.setBounds(1149, 205, 382, 42);
		panel.add(level3Label);
		level3Label.setFont(new Font("Dialog", Font.PLAIN, 40));
		level3Label.setHorizontalAlignment(SwingConstants.CENTER);
		
		level3Spells = new JPanel();
		level3Spells.setBackground(Color.BLACK);
		level3Spells.setLayout(null);
		level3Spells.setPreferredSize(new Dimension(364, 184));
		JScrollPane level3SpellsScroll = new JScrollPane(level3Spells);
		level3SpellsScroll.setBounds(1149, 250, 382, 760);
		panel.add(level3SpellsScroll);
		level3SpellsScroll.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);

		
		JButton btnAdd3rd = new JButton("+");
		btnAdd3rd.setBackground(Color.magenta);
		btnAdd3rd.setMargin(new Insets(0,0,0,0));
		btnAdd3rd.setFont(new Font("Dialog", Font.BOLD, 40));
		btnAdd3rd.setBounds(level3SpellsScroll.getX()+level3SpellsScroll.getWidth()-40, level3SpellsScroll.getY()-40, 40, 40);
		panel.add(btnAdd3rd);
		btnAdd3rd.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				addSpell(level3Spells, level3SpellsScroll, third);
			}
		});
		
		JLabel level4Label = new JLabel("Level 4");
		level4Label.setBounds(1512, 205, 382, 42);
		panel.add(level4Label);
		level4Label.setFont(new Font("Dialog", Font.PLAIN, 40));
		level4Label.setHorizontalAlignment(SwingConstants.CENTER);
		
		level4Spells = new JPanel();
		level4Spells.setBackground(Color.BLACK);
		level4Spells.setLayout(null);
		level4Spells.setPreferredSize(new Dimension(364, 184));
		JScrollPane level4SpellsScroll = new JScrollPane(level4Spells);
		level4SpellsScroll.setBounds(1512, 250, 382, 760);
		panel.add(level4SpellsScroll);
		level4SpellsScroll.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);

		
		JButton btnAdd4th = new JButton("+");
		btnAdd4th.setBackground(new Color(0, 250, 154));
		btnAdd4th.setMargin(new Insets(0,0,0,0));
		btnAdd4th.setFont(new Font("Dialog", Font.BOLD, 40));
		btnAdd4th.setBounds(level4SpellsScroll.getX()+level4SpellsScroll.getWidth()-40, level4SpellsScroll.getY()-40, 40, 40);
		panel.add(btnAdd4th);
		btnAdd4th.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				addSpell(level4Spells, level4SpellsScroll, fourth);
			}
		});
		
		setNewCharacter(cs);
	}
	
	public void setNewCharacter(CharacterSheet cs)
	{
		this.cs = cs;
		List<CharacterSheetPower> pwrs = cs.getCSP();
		nameField.setText(cs.getName());
		
		sig.clear();
		first.clear();
		second.clear();
		third.clear();
		fourth.clear();
		
		sigSpells.removeAll();
		level1Spells.removeAll();
		level2Spells.removeAll();
		level3Spells.removeAll();
		level4Spells.removeAll();
		
		level1Max.setText("/ 0");
		level2Max.setText("/ 0");
		level3Max.setText("/ 0");
		level4Max.setText("/ 0");
		level1Current.setText("");
		level2Current.setText("");
		level3Current.setText("");
		level4Current.setText("");
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
				else if(csp.getLevel() == 5)
				{
					level1Max.setText("/10");
					level2Max.setText("/ 5");
					level3Max.setText("/ 2");
					level4Max.setText("/ 1");
				}
				else if(csp.getLevel() == 4)
				{
					level1Max.setText("/10");
					level2Max.setText("/ 4");
					level3Max.setText("/ 2");
					level4Max.setText("/ 1");
				}
				else if(csp.getLevel() == 3)
				{
					level1Max.setText("/ 9");
					level2Max.setText("/ 3");
					level3Max.setText("/ 1");
					level4Max.setText("/ 1");
				}
				else if(csp.getLevel() == 2)
				{
					level1Max.setText("/ 8");
					level2Max.setText("/ 3");
					level3Max.setText("/ 1");
					level4Max.setText("/ 0");
				}
				else if(csp.getLevel() == 1)
				{
					level1Max.setText("/ 7");
					level2Max.setText("/ 2");
					level3Max.setText("/ 1");
					level4Max.setText("/ 0");
				}
				break;
			}
		}
		
		SpellInventory inv = cs.getSpellInv();
		if(inv != null)
		{
			int index = 0;
			for(Integer i : inv.getSig())
			{
				Spell s = new Spell();
				s.setLocation(0, index);
				s.set(i);
				sigSpells.add(s);	
				sig.add(s);
				index += 184;
			}
			sigSpells.setPreferredSize(new Dimension(364, index));
			index = 0;
			for(Integer i : inv.getFirst())
			{
				Spell s = new Spell();
				s.setLocation(0, index);
				s.set(i);
				level1Spells.add(s);	
				first.add(s);
				index += 184;
			}
			level1Spells.setPreferredSize(new Dimension(364, index));
			index = 0;
			for(Integer i : inv.getSecond())
			{
				Spell s = new Spell();
				s.setLocation(0, index);
				s.set(i);
				level2Spells.add(s);	
				second.add(s);
				index += 184;
			}
			level2Spells.setPreferredSize(new Dimension(364, index));
			index = 0;
			for(Integer i : inv.getThird())
			{
				Spell s = new Spell();
				s.setLocation(0, index);
				s.set(i);
				level3Spells.add(s);	
				third.add(s);
				index += 184;
			}
			level3Spells.setPreferredSize(new Dimension(364, index));
			index = 0;
			for(Integer i : inv.getFourth())
			{
				Spell s = new Spell();
				s.setLocation(0, index);
				s.set(i);
				level4Spells.add(s);	
				fourth.add(s);
				index += 184;
			}	
			level4Spells.setPreferredSize(new Dimension(364, index));
			
			level1Current.setText(inv.getSlots1());
			level2Current.setText(inv.getSlots2());
			level3Current.setText(inv.getSlots3());
			level4Current.setText(inv.getSlots4());
		}
		
	}
	
	public void resetSlots()
	{
		List<CharacterSheetPower> pwrs = cs.getCSP();
		for(CharacterSheetPower csp : pwrs)
		{
			if(csp.getPowerStr().equalsIgnoreCase("WIZARDRY") || csp.getPowerStr().equalsIgnoreCase("SORCERY"))
			{
				if(csp.getLevel() == 10)
				{
					level1Current.setText("X");
					level2Current.setText("X");
					level3Current.setText("10");
					level4Current.setText("4");
				}
				else if(csp.getLevel() == 9)
				{
					level1Current.setText("X");
					level2Current.setText("X");
					level3Current.setText("8");
					level4Current.setText("3");
				}
				else if(csp.getLevel() == 8)
				{
					level1Current.setText("X");
					level2Current.setText("10");
					level3Current.setText("7");
					level4Current.setText("2");
				}
				else if(csp.getLevel() == 7)
				{
					level1Current.setText("X");
					level2Current.setText("10");
					level3Current.setText("5");
					level4Current.setText("2");
				}
				else if(csp.getLevel() == 6)
				{
					level1Current.setText("X");
					level2Current.setText("7");
					level3Current.setText("4");
					level4Current.setText("2");
				}
				else if(csp.getLevel() == 5)
				{
					level1Current.setText("10");
					level2Current.setText("5");
					level3Current.setText("2");
					level4Current.setText("1");
				}
				else if(csp.getLevel() == 4)
				{
					level1Current.setText("10");
					level2Current.setText("4");
					level3Current.setText("2");
					level4Current.setText("1");
				}
				else if(csp.getLevel() == 3)
				{
					level1Current.setText("9");
					level2Current.setText("3");
					level3Current.setText("1");
					level4Current.setText("1");
				}
				else if(csp.getLevel() == 2)
				{
					level1Current.setText("8");
					level2Current.setText("3");
					level3Current.setText("1");
					level4Current.setText("0");
				}
				else if(csp.getLevel() == 1)
				{
					level1Current.setText("7");
					level2Current.setText("2");
					level3Current.setText("1");
					level4Current.setText("0");
				}
				break;
			}
		}
	}
	
	public void addSpell(JPanel pane, JScrollPane scroll, List<Spell> list)
	{
		int index = 184*pane.getComponentCount();
		Spell s = new Spell();
		s.setLocation(0, index);
		s.set(0);
		pane.add(s);
		list.add(s);
		pane.setPreferredSize(new Dimension(364, 184+index));
		pane.repaint();
		scroll.revalidate();
	}
	
	public void saveSpell(String name, Spell save)
	{
		boolean rewrite = false;
	
		if(!name.equals("(name of spell to save)"))
		{
			for(Spell s : MainWindow.spells)
			{
				if(s.getName().equals(save.getName()))
				{
					rewrite = true;
					save.setId(s.getId());
					save.setName(name);
					save.setPath(save.getName().toLowerCase()+".png");
					break;
				}
			}
			
			if(rewrite)
			{
				MainWindow.dao.updateSpell(save);
				MainWindow.dao.saveAll();
				MainWindow.spells = MainWindow.dao.getAllSpells();
			}
			if(!rewrite)
			{
				save.setId(MainWindow.nextSpellId);
				save.setName(name);
				save.setPath(save.getName().toLowerCase()+".png");
				MainWindow.dao.addSpell(save);
				MainWindow.dao.saveAll();
				MainWindow.spells = MainWindow.dao.getAllSpells();
			}
			
			if(save.retrievePic() != null)
			{
				try 
				{
				    // retrieve image
			        BufferedImage img = save.retrievePic();
			        
				    					    
				    File outputfile = new File("images/spells/"+save.getPath());
				    ImageIO.write(img, "png", outputfile);
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
	}
	
	
	public void saveSpells(boolean onlySpells)
	{
		SpellInventory save = new SpellInventory(cs.getId());
		
		List<Integer> sigs = new ArrayList<Integer>();
		for(Spell s : sig)
		{
			sigs.add((s.getId()));
		}
		List<Integer> firsts = new ArrayList<Integer>();
		for(Spell s : first)
		{
			firsts.add((s.getId()));
		}
		List<Integer> seconds = new ArrayList<Integer>();
		for(Spell s : second)
		{
			seconds.add((s.getId()));
		}
		List<Integer> thirds = new ArrayList<Integer>();
		for(Spell s : third)
		{
			thirds.add((s.getId()));
		}
		List<Integer> fourths = new ArrayList<Integer>();
		for(Spell s : fourth)
		{
			fourths.add((s.getId()));
		}
		
		save.setSlots1(level1Current.getText());
		save.setSlots2(level2Current.getText());
		save.setSlots3(level3Current.getText());
		save.setSlots4(level4Current.getText());
		
		save.save(sigs, firsts, seconds, thirds, fourths);
		
		if(cs.getSpellInv() != null)
			MainWindow.dao.updateSpellInv(save);
		else
			MainWindow.dao.addSpellInv(save);
				
		cs.setSpellInv(MainWindow.spellInvs);
				
		if(onlySpells)
			MainWindow.dao.saveAll();
	
	}
}
