package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import business.CharacterSheet;
import business.Inventory;
import business.Item;
import library.Scalr;

import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class EquipmentTab extends JScrollPane 
{
	
	private JPanel panel;
	private JFormattedTextField nameField;
	
	private CharacterSheet cs;
	private Inventory inv;
	
	private Item cap;
	private Item head;
	private Item neck;
	private Item wrists;
	private Item chest;
	private Item weapon;
	private Item ring1;
	private Item waist;
	private Item ring2;
	private Item legs;
	private Item feet;
	private Item pack1;
	private Item pack2;
	private Item pack3;
	private Item pack4;
	private Item pack5;
	private Item pack6;
	private Item pack7;
	private Item pack8;
	private Item pack9;
	private Item pack10;
	private Item pack11;
	private Item pack12;
	private Item pack13;
	private Item pack14;
	private Item pack15;
	
	
	public EquipmentTab(CharacterSheet cs)
	{
		super();
		setSize(1902, 1039);
		setPreferredSize(new Dimension(1902, 1039));
		this.cs = cs;
		
		inv = MainWindow.invs.get(0);
			
		panel = new JPanel();
		setViewportView(panel);
		panel.setLayout(null);
		panel.setSize(1894,  1008);
		panel.setPreferredSize(new Dimension(1894, 1008));
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setBounds(709, 0, 107, 42);
		panel.add(nameLabel);
		nameLabel.setFont(new Font("Dialog", Font.PLAIN, 40));
		
		nameField = new JFormattedTextField();
		nameField.setBounds(826, 0, 367, 42);
		panel.add(nameField);
		nameField.setFont(new Font("Dialog", Font.PLAIN, 24));
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setText(cs.getName());
		nameField.setEnabled(false);
		
		JButton btnSave = new JButton("Save All Items");
		btnSave.setBounds(60, 7, 120, 29);
		panel.add(btnSave);
		btnSave.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				saveItems(true);
			}
		});
		
		JLabel equipmentLabel = new JLabel("Equipment");
		equipmentLabel.setBounds(0, 0, 668, 42);
		equipmentLabel.setFont(new Font("Dialog", Font.PLAIN, 40));
		equipmentLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(equipmentLabel);
		
		JPanel equipment = new JPanel();
		equipment.setBackground(Color.BLACK);
		equipment.setLayout(null);
		equipment.setPreferredSize(new Dimension(645, 1369));
		JScrollPane equipmentScroll = new JScrollPane(equipment);
		equipmentScroll.setBounds(0, 43, 668, 966);
		panel.add(equipmentScroll);
		
		cap = new Item("Cap");
		cap.setLocation(5, 5);
		equipment.add(cap);
		
		head = new Item("Head");
		head.setLocation(220, 5);
		equipment.add(head);
		
		neck = new Item("Neck");
		neck.setLocation(435, 5);
		equipment.add(neck);
		
		wrists = new Item("Wrists");
		wrists.setLocation(5, 346);
		equipment.add(wrists);
		
		chest = new Item("Chest");
		chest.setLocation(220, 346);
		equipment.add(chest);
		
		weapon = new Item("Weapon");
		weapon.setLocation(435, 346);
		equipment.add(weapon);
		
		ring1 = new Item("Ring 1");
		ring1.setLocation(5, 687);
		equipment.add(ring1);
		
		waist = new Item("Waist");
		waist.setLocation(220, 687);
		equipment.add(waist);
		
		ring2 = new Item("Ring 2");
		ring2.setLocation(435, 687);
		equipment.add(ring2);
		
		legs = new Item("Legs");
		legs.setLocation(105, 1028);
		equipment.add(legs);
		
		feet = new Item("Feet");
		feet.setLocation(325, 1028);
		equipment.add(feet);
		
		JPanel pack = new JPanel();
		pack.setLayout(null);
		pack.setPreferredSize(new Dimension(645, 1276));
		JScrollPane packScroll = new JScrollPane(pack);
		packScroll.setBounds(panel.getWidth()-650, 43, 650, 1025);
		panel.add(packScroll);
			
		pack1 = new Item("Pack 1");
		pack1.setLocation(0, 0);
		pack.add(pack1);
		pack2 = new Item("Pack 2");
		pack2.setLocation(215, 0);
		pack.add(pack2);
		pack3 = new Item("Pack 3");
		pack3.setLocation(430, 0);
		pack.add(pack3);
	
		pack4 = new Item("Pack 4");
		pack4.setLocation(0, 319);
		pack.add(pack4);
		pack5 = new Item("Pack 5");
		pack5.setLocation(215, 319);
		pack.add(pack5);
		pack6 = new Item("Pack 6");
		pack6.setLocation(430, 319);
		pack.add(pack6);		
		
		pack7 = new Item("Pack 7");
		pack7.setLocation(0, 638);
		pack.add(pack7);
		pack8 = new Item("Pack 8");
		pack8.setLocation(215, 638);
		pack.add(pack8);
		pack9 = new Item("Pack 9");
		pack9.setLocation(430, 638);
		pack.add(pack9);
		
		pack10 = new Item("Pack 10");
		pack10.setLocation(0, 957);
		pack.add(pack10);
		pack11 = new Item("Pack 11");
		pack11.setLocation(215, 957);
		pack.add(pack11);
		pack12 = new Item("Pack 12");
		pack12.setLocation(430, 957);
		pack.add(pack12);
		
		pack13 = new Item("Pack 13");
		pack13.setLocation(0, 1276);
		pack.add(pack13);
		pack14 = new Item("Pack 14");
		pack14.setLocation(215, 1276);
		pack.add(pack14);
		pack15 = new Item("Pack 15");
		pack15.setLocation(1380, 1276);
		pack.add(pack15);
		
		
		JPanel storage = new JPanel();
		storage.setLayout(null);
		storage.setPreferredSize(new Dimension(645, 1276));
		JScrollPane storageScroll = new JScrollPane(storage);
		storageScroll.setBounds(1302, 43, 650, 1025);
		//panel.add(storageScroll);
		
		Item i1 = new Item("Item 1");
		i1.setLocation(0, 0);
		storage.add(i1);
		Item i2 = new Item("Item 2");
		i2.setLocation(215, 0);
		storage.add(i2);
	}
	
	public void setNewCharacter(CharacterSheet cs, Inventory inv)
	{
		this.cs = cs;
		nameField.setText(cs.getName());

		if(inv == null)
		{
			System.out.println("null inv");
			inv = new Inventory(cs.getId());
			MainWindow.invs.add(inv);
		}
		this.inv = inv;
		
		System.out.println("Inventory for character Id: " + inv.getCharacterSheetId());
		
		cap.set(MainWindow.items.get(inv.getCap()));
		head.set(MainWindow.items.get(inv.getHead()));
		neck.set(MainWindow.items.get(inv.getNeck()));
		wrists.set(MainWindow.items.get(inv.getWrists()));
		chest.set(MainWindow.items.get(inv.getChest()));
		weapon.set(MainWindow.items.get(inv.getWeapon()));
		ring1.set(MainWindow.items.get(inv.getRing1()));
		waist.set(MainWindow.items.get(inv.getWaist()));
		ring2.set(MainWindow.items.get(inv.getRing2()));
		legs.set(MainWindow.items.get(inv.getLegs()));
		feet.set(MainWindow.items.get(inv.getFeet()));
		
		pack1.set(MainWindow.items.get(inv.getPack1()));
		pack2.set(MainWindow.items.get(inv.getPack2()));
		pack3.set(MainWindow.items.get(inv.getPack3()));
		pack4.set(MainWindow.items.get(inv.getPack4()));
		pack5.set(MainWindow.items.get(inv.getPack5()));
		pack6.set(MainWindow.items.get(inv.getPack6()));
		pack7.set(MainWindow.items.get(inv.getPack7()));
		pack8.set(MainWindow.items.get(inv.getPack8()));
		pack9.set(MainWindow.items.get(inv.getPack9()));
		pack10.set(MainWindow.items.get(inv.getPack10()));
		pack11.set(MainWindow.items.get(inv.getPack11()));
		pack12.set(MainWindow.items.get(inv.getPack12()));
		pack13.set(MainWindow.items.get(inv.getPack13()));
		pack14.set(MainWindow.items.get(inv.getPack14()));
		pack15.set(MainWindow.items.get(inv.getPack15()));
		
		
	}
	
	public void saveItems(boolean onlyItems)
	{
		for some reason, this method is blocking the load function from switching back properly. some list proably needs to be cleared or nullified
		
		List<Item> l = new ArrayList<Item>();

		l.add(cap);			
		l.add(head);
		l.add(neck);			
		l.add(wrists);			
		l.add(chest);			
		l.add(weapon);			
		l.add(ring1);			
		l.add(waist);			
		l.add(ring2);			
		l.add(legs);			
		l.add(feet);			
		l.add(pack1);			
		l.add(pack2);			
		l.add(pack3);			
		l.add(pack4);			
		l.add(pack5);			
		l.add(pack6);			
		l.add(pack7);
		l.add(pack8);			
		l.add(pack9);			
		l.add(pack10);			
		l.add(pack11);			
		l.add(pack12);			
		l.add(pack13);			
		l.add(pack14);			
		l.add(pack15);
	
		for(Item li : l)
		{
			if(!li.getName().equals("(name)"))
			{
				boolean newItem = true;
				li.setPath(li.getName().toLowerCase()+".png");

				for(Item i : MainWindow.items)
				{
					if(i.getName() == li.getName())
					{
						li.setId(i.getId());
						i = li;
						MainWindow.dao.updateItem(li);
						newItem = false;
						break;
					}
				}
				
				if(newItem)
				{
					li.setId(MainWindow.nextItemId);
					System.out.println("Saved id is: "+ MainWindow.nextItemId);
					MainWindow.dao.addItem(li);
					MainWindow.items.add(li);
					MainWindow.nextItemId++;
					System.out.println("The new next id is: " + MainWindow.nextItemId);
				}
				
				if(li.newPic())
				{
					try 
					{
					    // retrieve image
				        BufferedImage img = li.retrievePic();
					    					    
					    File outputfile = new File("images/items/"+li.getPath());
					    ImageIO.write(img, "png", outputfile);
					} catch (IOException e) {
						System.out.println(e);
					}
				}
			}
		}
		
		
		inv.setCap(l.get(0).getId());
		inv.setHead(l.get(1).getId());
		inv.setNeck(l.get(2).getId());
		inv.setWrists(l.get(3).getId());
		inv.setChest(l.get(4).getId());
		inv.setWeapon(l.get(5).getId());
		inv.setRing1(l.get(6).getId());
		inv.setWaist(l.get(7).getId());
		inv.setRing2(l.get(8).getId());
		inv.setLegs(l.get(9).getId());
		inv.setFeet(l.get(10).getId());
		inv.setPack1(l.get(11).getId());
		inv.setPack2(l.get(12).getId());
		inv.setPack3(l.get(13).getId());
		inv.setPack4(l.get(14).getId());
		inv.setPack5(l.get(15).getId());
		inv.setPack6(l.get(16).getId());
		inv.setPack7(l.get(17).getId());
		inv.setPack8(l.get(18).getId());
		inv.setPack9(l.get(19).getId());
		inv.setPack10(l.get(20).getId());
		inv.setPack11(l.get(21).getId());
		inv.setPack12(l.get(22).getId());
		inv.setPack13(l.get(23).getId());
		inv.setPack14(l.get(24).getId());
		inv.setPack15(l.get(25).getId());
		
		for(Inventory i : MainWindow.invs)
		{
			if(i.getCharacterSheetId() == inv.getCharacterSheetId())
			{
				i = inv;
				MainWindow.dao.updateInv(inv);
				break;
			}
		}
		
		cs.setInv(MainWindow.invs);
		
		if(onlyItems)
			MainWindow.dao.saveAll();
	}
}
