package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import business.CharacterSheet;
import business.Inventory;
import business.Item;
import library.Scalr;

import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

public class EquipmentTab extends JScrollPane 
{
	
	private JPanel panel;
	private JFormattedTextField nameField;
	private static List<String> searchList = new ArrayList<String>();
	private JPanel storage;
	
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
	
	private List<Item> storageList = new ArrayList<Item>();
	JScrollPane storageScroll;
	
	public static Item search;
	
	
	public EquipmentTab(CharacterSheet cs)
	{
		super();
		setPreferredSize(MainWindow.tabbedPane.getSize());
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
		
		JLabel searchLabel = new JLabel("Search");
		searchLabel.setBounds(679, 60, 107, 24);
		panel.add(searchLabel);
		searchLabel.setFont(new Font("Dialog", Font.PLAIN, 24));
		
		JFormattedTextField searchField = new JFormattedTextField();
		searchField.setBounds(679, 86, 157, 23);
		panel.add(searchField);
		searchField.setHorizontalAlignment(SwingConstants.RIGHT);
		searchField.setText("(search for item by name)");
		
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
		btnLoad.setBounds(766, 60, 65, 24);
		panel.add(btnLoad);
		btnLoad.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				for(Item i : MainWindow.items)
				{
					if(i.getName().toLowerCase().equals(searchField.getText().toLowerCase()))
					{
						search.set(MainWindow.items, i.getId());
						break;
					}
				}
				
			}
		});
		
		search = new Item("Selected", 350, 452);
		search.setLocation(860, 60);
		search.setDisabled();
		panel.add(search);
		
		searchField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) 
			{
				searchList.clear();
				if(!searchField.getText().equals(""))
				{
					for(Item i : MainWindow.items)
					{
						if(i.getName().toLowerCase().contains(searchField.getText().toLowerCase()))
							searchList.add(i.getName());
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
		
		JCheckBox delBox = new JCheckBox("Confirm Delete");
		add(delBox);
		delBox.setBounds(679, searchField.getY()+searchField.getHeight()+2, 157, 23);
		delBox.repaint();
		
		JButton btnDelete = new JButton("Delete Displayed Item");
		btnDelete.setBounds(679, delBox.getY()+delBox.getHeight()+2, 157, 23);
		btnDelete.setBackground(Color.red);
		panel.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(delBox.isSelected())
				{
					for(Item i : MainWindow.items)
					{
						if(i.getId() == search.getId() && i.getId() != 0)
						{
							MainWindow.dao.deleteItem(i);
							File f = new File("images/items/"+i.getPath());
							f.delete();
						}
					}
					searchField.setText("");
					search.clearItem();
					MainWindow.dao.saveAll();
				}
				delBox.setSelected(false);
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
		pack.setBackground(Color.black);
		pack.setPreferredSize(new Dimension(645, 1710));
		JScrollPane packScroll = new JScrollPane(pack);
		packScroll.setBounds(panel.getWidth()-668, 43, 668, 966);
		panel.add(packScroll);
		
		JLabel packLabel = new JLabel("Pack");
		packLabel.setBounds(packScroll.getX(), 0, 668, 42);
		packLabel.setFont(new Font("Dialog", Font.PLAIN, 40));
		packLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(packLabel);
			
		pack1 = new Item("Pack 1");
		pack1.setLocation(5, 5);
		pack.add(pack1);
		pack2 = new Item("Pack 2");
		pack2.setLocation(220, 5);
		pack.add(pack2);
		pack3 = new Item("Pack 3");
		pack3.setLocation(435, 5);
		pack.add(pack3);
	
		pack4 = new Item("Pack 4");
		pack4.setLocation(5, 346);
		pack.add(pack4);
		pack5 = new Item("Pack 5");
		pack5.setLocation(220, 346);
		pack.add(pack5);
		pack6 = new Item("Pack 6");
		pack6.setLocation(435, 346);
		pack.add(pack6);		
		
		pack7 = new Item("Pack 7");
		pack7.setLocation(5, 687);
		pack.add(pack7);
		pack8 = new Item("Pack 8");
		pack8.setLocation(220, 687);
		pack.add(pack8);
		pack9 = new Item("Pack 9");
		pack9.setLocation(435, 687);
		pack.add(pack9);
		
		pack10 = new Item("Pack 10");
		pack10.setLocation(5, 1028);
		pack.add(pack10);
		pack11 = new Item("Pack 11");
		pack11.setLocation(220, 1028);
		pack.add(pack11);
		pack12 = new Item("Pack 12");
		pack12.setLocation(435, 1028);
		pack.add(pack12);
		
		pack13 = new Item("Pack 13");
		pack13.setLocation(5, 1369);
		pack.add(pack13);
		pack14 = new Item("Pack 14");
		pack14.setLocation(220, 1369);
		pack.add(pack14);
		pack15 = new Item("Pack 15");
		pack15.setLocation(435, 1369);
		pack.add(pack15);
		
		
		storage = new JPanel();
		storage.setBackground(Color.black);
		storage.setLayout(null);
		storage.setPreferredSize(new Dimension(430, 256));
		storageScroll = new JScrollPane(storage);
		storageScroll.setBounds(panel.getWidth()/2 - 226, 630, 452, panel.getHeight()-630);
		storageScroll.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(storageScroll); 
		
		JLabel storageLabel = new JLabel("Storage");
		storageLabel.setBounds(storageScroll.getX(), storageScroll.getY()-46, storageScroll.getWidth(), 45);
		storageLabel.setFont(new Font("Dialog", Font.PLAIN, 38));
		storageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(storageLabel);
		
		JButton btnAddRow = new JButton("Add Row");
		btnAddRow.setBounds(storageLabel.getX()+storageLabel.getWidth()-90, storageLabel.getY()+22, 90, 23);
		panel.add(btnAddRow);
		btnAddRow.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				addRow();
			}
		});
		
		setNewCharacter(cs);
		
	}
	
	public void addRow()
	{
		int count = storage.getComponents().length + 1;
		int y = (count/2)*341 + 5;
		
		Item item1 = new Item("Item " + count);
		item1.setLocation(5, y);
		item1.set(MainWindow.items, 0);
		storage.add(item1);
		storageList.add(item1);

		
		count++;
		
		Item item2 = new Item("Item " + count);
		item2.setLocation(220, y);
		item2.set(MainWindow.items, 0);
		storage.add(item2);
		storageList.add(item2);

		
		int panelHeight = (storage.getComponents().length/2) * 341 + 5;
		storage.setPreferredSize(new Dimension(430, panelHeight));
		storageScroll.revalidate();
		
	}
	
	public void setNewCharacter(CharacterSheet cs)
	{
		this.cs = cs;
		storageList.clear();
		
		nameField.setText(cs.getName());
		
		inv = null;

		if(cs.getInv() == null)
		{
			System.out.println("null inv");
			inv = new Inventory(cs.getId());
			MainWindow.invs.add(inv);
			cs.setInv(MainWindow.invs);
		}
		else
			inv = cs.getInv();
		
	
		cap.set(MainWindow.items, inv.getCap());
		head.set(MainWindow.items, inv.getHead());
		neck.set(MainWindow.items, inv.getNeck());
		wrists.set(MainWindow.items, inv.getWrists());
		chest.set(MainWindow.items, inv.getChest());
		weapon.set(MainWindow.items, inv.getWeapon());
		ring1.set(MainWindow.items, inv.getRing1());
		waist.set(MainWindow.items, inv.getWaist());
		ring2.set(MainWindow.items, inv.getRing2());
		legs.set(MainWindow.items, inv.getLegs());
		feet.set(MainWindow.items, inv.getFeet());
		
		pack1.set(MainWindow.items, inv.getPack1());
		pack2.set(MainWindow.items, inv.getPack2());
		pack3.set(MainWindow.items, inv.getPack3());
		pack4.set(MainWindow.items, inv.getPack4());
		pack5.set(MainWindow.items, inv.getPack5());
		pack6.set(MainWindow.items, inv.getPack6());
		pack7.set(MainWindow.items, inv.getPack7());
		pack8.set(MainWindow.items, inv.getPack8());
		pack9.set(MainWindow.items, inv.getPack9());
		pack10.set(MainWindow.items, inv.getPack10());
		pack11.set(MainWindow.items, inv.getPack11());
		pack12.set(MainWindow.items, inv.getPack12());
		pack13.set(MainWindow.items, inv.getPack13());
		pack14.set(MainWindow.items, inv.getPack14());
		pack15.set(MainWindow.items, inv.getPack15());
		
		
		storage.removeAll();

		boolean onLeft = true;
		int count = 1;
		int y = 5;
		for(Integer i : inv.getStorage())
		{
			Item item = new Item("Item " + count);
			if(onLeft)
				item.setLocation(5, y);
			else
			{
				item.setLocation(220, y);
				y += 341;
			}
			item.set(MainWindow.items, i);
			item.setFirstLoad();
			storageList.add(item);
			storage.add(item);
			
			onLeft = !onLeft;
			count++;
		}
		if(!onLeft)
		{
			Item item = new Item("Item " + count);
			item.setLocation(220, y);
			item.set(MainWindow.items, 0);
			storage.add(item);
		}	
		int panelHeight = (storage.getComponents().length/2) * 341 + 5;
		storage.setPreferredSize(new Dimension(430, panelHeight));
	}
	
	public void saveItems(boolean onlyItems)
	{
		List<Item> l = new ArrayList<Item>();
		List<Integer> storageSave = new ArrayList<Integer>();

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
	
		for(Item i : storageList)
		{
			l.add(i);
		}
		
		for(Item li : l)
		{
			if(!li.getName().equals("(name)"))
			{
				boolean newItem = true;
				li.setPath(li.getName().toLowerCase()+".png");

				for(Item i : MainWindow.items)
				{
					if(i.getName().toLowerCase().equals(li.getName().toLowerCase()))
					{
						li.setId(i.getId());
						i.setName(li.getName());
						i.setDescStr(li.getDescStr());
						i.setPath(li.getPath());
						MainWindow.dao.updateItem(i);
						newItem = false;
						break;
					}
				}
				
				if(newItem)
				{					
					Item i = new Item(MainWindow.nextItemId);
					li.setId(i.getId());
					i.setDescStr(li.getDescStr());
					i.setPath(li.getPath());
					i.setName(li.getName());
					MainWindow.dao.addItem(i);
					MainWindow.nextItemId++;
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
		
		for(Item i : storageList)
		{
			storageSave.add(i.getId());
		}
		
		inv.setCharacterSheetId(cs.getId());
		
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
		inv.setStorage(storageSave);
		
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
		
		if(cs.getInv() != null)
			MainWindow.weapon.set(MainWindow.items, cs.getInv().getWeapon());

				
		if(onlyItems)
			MainWindow.dao.saveAll();
	}
}
