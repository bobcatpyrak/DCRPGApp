package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import business.CharacterSheet;
import business.Inventory;
import business.Item;

public class EquipmentPanel extends JPanel 
{

	private CharacterSheet cs;
	private Inventory inv;
	
	private JPanel panel;
	
	public EquipmentPanel() 
	{
		setBackground(new Color(0, 0, 0));
		setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(7, 7, 286, 16);
		add(panel);
		panel.setLayout(null);
			
	}
	
	public void setNewCharacter(CharacterSheet cs)
	{
		this.cs = cs;
		inv = cs.getInv();
		panel.removeAll();
		
		List<String> bonuses = new ArrayList<String>();
		
		List<Integer> equipped = new ArrayList<Integer>();
		
		if(inv.getCap() != 0)
			equipped.add(inv.getCap());
		if(inv.getHead() != 0)
			equipped.add(inv.getHead());
		if(inv.getNeck() != 0)
			equipped.add(inv.getNeck());
		if(inv.getWrists() != 0)
			equipped.add(inv.getWrists());
		if(inv.getChest() != 0)
			equipped.add(inv.getChest());
		if(inv.getWaist() != 0)
			equipped.add(inv.getWaist());
		if(inv.getLegs() != 0)
			equipped.add(inv.getLegs());
		if(inv.getFeet() != 0)
			equipped.add(inv.getFeet());
		if(inv.getRing1() != 0)
			equipped.add(inv.getRing1());
		if(inv.getRing2() != 0)
			equipped.add(inv.getRing2());
		

		for(Integer i : equipped)
		{
			for(Item item : MainWindow.items)
			{
				if(i == item.getId())
				{
					String str = "<html>";
					str += "<b>"+item.getName()+"</b> - ";
					str += item.getDescStr();
					str += "</html>";
					bonuses.add(str);
					break;
				}
			}
		}
		
		int index = 0;
		
		for(String s : bonuses)
		{
			JLabel line = new JLabel(s);
			line.setBounds(2, index, 282, 20);
			int height = (s.length()-20) / 55;
			height *= 12;
			line.setSize(282,  height + 20);
			line.setFont(new Font("Dialog", Font.PLAIN, 12));
			panel.add(line);
			index += line.getHeight();
		}
		
		panel.setSize(286, index);
		this.setSize(300, panel.getHeight()+14);
	}


}
