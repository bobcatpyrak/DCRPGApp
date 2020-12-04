package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import business.CharacterSheet;



public class SpellTab extends JScrollPane 
{
	private JPanel panel;
	private JFormattedTextField nameField;
	
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
	}
}
