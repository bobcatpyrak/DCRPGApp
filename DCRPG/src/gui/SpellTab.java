package gui;

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
import business.Item;
import business.Spell;



public class SpellTab extends JScrollPane 
{
	private static List<String> searchList = new ArrayList<String>();

	private JPanel panel;
	private JFormattedTextField nameField;
	private Spell search;
	
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
	}
}
