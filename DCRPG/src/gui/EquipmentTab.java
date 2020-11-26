package gui;

import java.awt.Dimension;

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import business.CharacterSheet;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class EquipmentTab extends JScrollPane 
{
	
	private JPanel panel;
	private CharacterSheet cs;
	
	
	public EquipmentTab(CharacterSheet cs)
	{
		super();
		setSize(1902, 1039);
		setPreferredSize(new Dimension(1902, 1039));
		this.cs = cs;
			
		panel = new JPanel();
		setViewportView(panel);
		panel.setLayout(null);
		panel.setSize(1894,  1008);
		panel.setPreferredSize(new Dimension(1894, 1008));
		
	}
	
	public void setNewCharacter(CharacterSheet cs)
	{
		this.cs = cs;
	
	}
}
