package db;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import business.*;

public class TextFile implements DAO<CharacterSheet> 
{
	private List<CharacterSheet> sheets = null;
	private List<SkillSpec> specs = null;
	private List<CharacterSheetAdvantage> advs = null;
	private List<CharacterSheetDisadvantage> disadvs = null;
	private List<CharacterSheetPower> powers = null;
	private List<Inventory> invs = null;
	private List<Item> items = null;
	private Path sheetsPath = null;
	private File sheetsFile = null;
	private final String FIELD_SEP = "\t";
	
	public TextFile()
	{
		String dirString = "DCRPGCharacterSheets";

		
		sheetsPath = Paths.get("DCRPGCharacterSheets/DCRPGCharacterSheets.txt");
		sheetsFile = sheetsPath.toFile();
		specs = getAllSpecs();
		advs = getAllCSA();
		disadvs = getAllCSD();
		powers = getAllCSP();
		invs = getAllInv();
		items = getAllItems();
		sheets = getAllSheets();
		
		saveAll();
	}
	@Override
	public CharacterSheet get(int id) 
	{
		return sheets.get(id);
	}
	public CharacterSheet getSheet(int id) 
	{
		return sheets.get(id);
	}
	public SkillSpec getSpec(int id) 
	{
		return specs.get(id);
	}
	public CharacterSheetAdvantage getCSA(int id) 
	{
		return advs.get(id);
	}
	public CharacterSheetDisadvantage getCSD(int id) 
	{
		return disadvs.get(id);
	}
	public CharacterSheetPower getCSP(int id) 
	{
		return powers.get(id);
	}
	public Inventory getInv(int id)
	{
		return invs.get(id);
	}
	public Item getItem(int id)
	{
		return items.get(id);
	}
	
	@Override
	public List<CharacterSheet> getAll() 
	{	
		if(sheets != null)
			return sheets;
		
		sheets = new ArrayList<>();
		if(Files.exists(sheetsPath))
		{
			try (BufferedReader in = new BufferedReader(new FileReader(sheetsFile)))
			{
				
				String line = in.readLine();
				boolean reading = false;
				
				if (line != null)
					reading = true;
				
				while (reading)
				{
					if (line.equals("$$CharacterSheetBegin$$"))
					{
						line = in.readLine();
						while (!line.equals("$$CharacterSheetEnd$$"))
						{
							String[] fields = line.split(FIELD_SEP);
							CharacterSheet cs = new CharacterSheet(Integer.parseInt(fields[0]));
							cs.setName(fields[1]);
							cs.setPicture(fields[2]);
							cs.setAllDemographics(fields[3]);
							cs.setAllMiscStats(fields[4]);
							cs.setAllStats(fields[5]);
							sheets.add(cs);
							line = in.readLine();
						}
						reading = false;
					}
					else
						line = in.readLine();
				}
				return sheets;
			}
			catch(IOException ioe)
			{
				System.out.println(ioe);
				return null;
			}
		}	
		else
		{
			System.out.println(sheetsPath + " is empty.");
			return null;
		}
	}
	public List<CharacterSheet> getAllSheets() 
	{	
		if(sheets != null)
			return sheets;
		
		sheets = new ArrayList<>();
		if(Files.exists(sheetsPath))
		{
			try (BufferedReader in = new BufferedReader(new FileReader(sheetsFile)))
			{
				
				String line = in.readLine();
				boolean reading = false;
				
				if (line != null)
					reading = true;
				
			//	todo - autogenerate sheets for pictures, add drag and drop for items, finish equipment tab, then spells, then rolling
				while (reading)
				{
					if (line.equals("$$CharacterSheetBegin$$"))
					{
						line = in.readLine();
						while (!line.equals("$$CharacterSheetEnd$$"))
						{
							String[] fields = line.split(FIELD_SEP);
							CharacterSheet cs = new CharacterSheet(Integer.parseInt(fields[0]));
							cs.setName(fields[1]);
							cs.setPicture(fields[2]);
							cs.setAllDemographics(fields[3]);
							cs.setAllMiscStats(fields[4]);
							cs.setAllStats(fields[5]);
							cs.setSkillSpecs(specs);
							cs.setCSA(advs);
							cs.setCSD(disadvs);
							cs.setCSP(powers);
							cs.setInv(invs);
							sheets.add(cs);
							line = in.readLine();
						}
						reading = false;
					}
					else
					{
						line = in.readLine();
						if (line == null)
							reading = false;
					}
				}
				return sheets;
			}
			catch(IOException ioe)
			{
				System.out.println(ioe);
				return null;
			}
		}	
		else
		{
			System.out.println(sheetsPath + " is empty.");
			return null;
		}
	}
	public List<SkillSpec> getAllSpecs() 
	{	
		if(specs != null)
			return specs;
		
		specs = new ArrayList<>();
		if(Files.exists(sheetsPath))
		{
			try (BufferedReader in = new BufferedReader(new FileReader(sheetsFile)))
			{
				
				String line = in.readLine();
				boolean reading = false;
				
				if (line != null)
					reading = true;
				
				while (reading)
				{
					if (line.equals("$$SkillSpecBegin$$"))
					{
						line = in.readLine();
						while (!line.equals("$$SkillSpecEnd$$"))
						{
							String[] fields = line.split(FIELD_SEP);
							SkillSpec ss = new SkillSpec(Integer.parseInt(fields[0]));
							ss.setCharacterSheetId(Integer.parseInt(fields[1]));
							ss.setSkill(fields[2]);
							ss.setDescription(fields[3]);
							specs.add(ss);
							line = in.readLine();
						}
						reading = false;
					}
					else
					{
						line = in.readLine();
						if (line == null)
							reading = false;
					}
				}
				return specs;
			}
			catch(IOException ioe)
			{
				System.out.println(ioe);
				return null;
			}
		}	
		else
		{
			System.out.println(sheetsPath + " is empty.");
			return null;
		}
	}
	public List<CharacterSheetAdvantage> getAllCSA() 
	{	
		if(advs != null)
			return advs;
		
		advs = new ArrayList<>();
		if(Files.exists(sheetsPath))
		{
			try (BufferedReader in = new BufferedReader(new FileReader(sheetsFile)))
			{
				
				String line = in.readLine();
				boolean reading = false;
				
				if (line != null)
					reading = true;
				
				while (reading)
				{
					if (line.equals("$$CharacterSheetAdvantageBegin$$"))
					{
						line = in.readLine();
						while (!line.equals("$$CharacterSheetAdvantageEnd$$"))
						{
							String[] fields = line.split(FIELD_SEP);
							CharacterSheetAdvantage csa = new CharacterSheetAdvantage(Integer.parseInt(fields[0]));
							csa.setCharacterSheetId(Integer.parseInt(fields[1]));
							csa.setAdv(fields[2]);
							csa.setDescription(fields[3]);
							advs.add(csa);
							line = in.readLine();
						}
						reading = false;
					}
					else
					{
						line = in.readLine();
						if (line == null)
							reading = false;
					}
				}
				return advs;
			}
			catch(IOException ioe)
			{
				System.out.println(ioe);
				return null;
			}
		}	
		else
		{
			System.out.println(sheetsPath + " is empty.");
			return null;
		}
	}
	public List<CharacterSheetDisadvantage> getAllCSD() 
	{	
		if(disadvs != null)
			return disadvs;
		
		disadvs = new ArrayList<>();
		if(Files.exists(sheetsPath))
		{
			try (BufferedReader in = new BufferedReader(new FileReader(sheetsFile)))
			{
				
				String line = in.readLine();
				boolean reading = false;
				
				if (line != null)
					reading = true;
				
				while (reading)
				{
					if (line.equals("$$CharacterSheetDisadvantageBegin$$"))
					{
						line = in.readLine();
						while (!line.equals("$$CharacterSheetDisadvantageEnd$$"))
						{
							String[] fields = line.split(FIELD_SEP);
							CharacterSheetDisadvantage csd = new CharacterSheetDisadvantage(Integer.parseInt(fields[0]));
							csd.setCharacterSheetId(Integer.parseInt(fields[1]));
							csd.setDisadv(fields[2]);
							csd.setDescription(fields[3]);
							disadvs.add(csd);
							line = in.readLine();
						}
						reading = false;
					}
					else
					{
						line = in.readLine();
						if (line == null)
							reading = false;
					}
				}
				return disadvs;
			}
			catch(IOException ioe)
			{
				System.out.println(ioe);
				return null;
			}
		}	
		else
		{
			System.out.println(sheetsPath + " is empty.");
			return null;
		}
	}	
	public List<CharacterSheetPower> getAllCSP() 
	{	
		if(powers != null)
			return powers;
		
		powers = new ArrayList<>();
		if(Files.exists(sheetsPath))
		{
			try (BufferedReader in = new BufferedReader(new FileReader(sheetsFile)))
			{
				
				String line = in.readLine();
				boolean reading = false;
				
				if (line != null)
					reading = true;
				
				while (reading)
				{
					if (line.equals("$$CharacterSheetPowerBegin$$"))
					{
						line = in.readLine();
						while (!line.equals("$$CharacterSheetPowerEnd$$"))
						{
							String[] fields = line.split(FIELD_SEP);
							CharacterSheetPower csp = new CharacterSheetPower(Integer.parseInt(fields[0]));
							csp.setCharacterSheetId(Integer.parseInt(fields[1]));
							csp.setLevel(Integer.parseInt(fields[2]));
							csp.setPower(fields[3]);
							csp.setCost(Integer.parseInt(fields[4]));
							csp.setSpecs(fields[5]);
							csp.setWeakness(fields[6]);
							powers.add(csp);
							line = in.readLine();
						}
						reading = false;
					}
					else
					{
						line = in.readLine();
						if (line == null)
							reading = false;
					}
				}
				return powers;
			}
			catch(IOException ioe)
			{
				System.out.println(ioe);
				return null;
			}
		}	
		else
		{
			System.out.println(sheetsPath + " is empty.");
			return null;
		}
	}	
	public List<Inventory> getAllInv() 
	{	
		if(invs != null)
			return invs;
		
		invs = new ArrayList<>();
		if(Files.exists(sheetsPath))
		{
			try (BufferedReader in = new BufferedReader(new FileReader(sheetsFile)))
			{
				
				String line = in.readLine();
				boolean reading = false;
				
				if (line != null)
					reading = true;
				
				while (reading)
				{
					if (line.equals("$$InventoryBegin$$"))
					{
						line = in.readLine();
						while (!line.equals("$$InventoryEnd$$"))
						{
							String[] fields = line.split(FIELD_SEP);
							Inventory i = new Inventory(Integer.parseInt(fields[0]));
							i.setInventory(fields[1], fields[2]);
							invs.add(i);
							line = in.readLine();
						}
						reading = false;
					}
					else
					{
						line = in.readLine();
						if (line == null)
							reading = false;
					}
				}
				return invs;
			}
			catch(IOException ioe)
			{
				System.out.println(ioe);
				return null;
			}
		}	
		else
		{
			System.out.println(sheetsPath + " is empty.");
			return null;
		}
	}	
	public List<Item> getAllItems() 
	{	
		if(items != null)
			return items;
		
		items = new ArrayList<>();
		if(Files.exists(sheetsPath))
		{
			try (BufferedReader in = new BufferedReader(new FileReader(sheetsFile)))
			{
				
				String line = in.readLine();
				boolean reading = false;
				
				if (line != null)
					reading = true;
				
				String[] paths;
				File f = new File("images/items");
				paths = f.list();
				
				List<String> images = new ArrayList<String>();
				for(int i = 0; i < paths.length; i++)
					images.add(paths[i]);
				
				while (reading)
				{
					if (line.equals("$$ItemBegin$$"))
					{
						line = in.readLine();
						while (!line.equals("$$ItemEnd$$"))
						{
							String[] fields = line.split(FIELD_SEP);
							Item i = new Item(Integer.parseInt(fields[0]));
							i.setName(fields[1]);
							i.setDescStr(fields[2]);
							i.setPath(fields[3]);
							
							for(String path : images)
							{
								if(path.toLowerCase().equals(fields[3].toLowerCase()))
								{
									images.remove(path);
									break;
								}
							}
							
							items.add(i);
							line = in.readLine();
						}
						reading = false;
					}
					else
					{
						line = in.readLine();
						if (line == null)
							reading = false;
					}
				}
				
				int nextItemId = 0;
				
				if(items.size() > 0)
					nextItemId = items.get(items.size()-1).getId() + 1;
				
				for(String path : images)
				{
					Item i = new Item(nextItemId);
					i.setPath(path);
					String name = path.substring(0, path.indexOf('.'));
					i.setName(name);
					nextItemId++;
					items.add(i);
				}			
				return items;
			}
			catch(IOException ioe)
			{
				System.out.println(ioe);
				return null;
			}
		}	
		else
		{
			System.out.println(sheetsPath + " is empty.");
			return null;
		}
	}	
	
	@Override
	public boolean add(CharacterSheet t) 
	{
		sheets.add(t);
		return true;
	}
	public boolean addSheet(CharacterSheet t) 
	{
		sheets.add(t);
		return true;
	}
	public boolean addSpec(SkillSpec t) 
	{
		specs.add(t);
		return true;
	}
	public boolean addCSA(CharacterSheetAdvantage t) 
	{
		advs.add(t);
		return true;
	}
	public boolean addCSD(CharacterSheetDisadvantage t) 
	{
		disadvs.add(t);
		return true;
	}
	public boolean addCSP(CharacterSheetPower t) 
	{
		powers.add(t);
		return true;
	}
	public boolean addInv(Inventory t) 
	{
		invs.add(t);
		return true;
	}
	public boolean addItem(Item t) 
	{
		items.add(t);
		return true;
	}

	@Override
	public boolean update(CharacterSheet t) 
	{
		int index = t.getId();
		sheets.remove(index);
		sheets.add(index, t);
		return true;
	}
	public boolean updateSheet(CharacterSheet t) 
	{
		int index = -1;
		for(CharacterSheet cs : sheets)
		{
			if(cs.getId()==t.getId())
				index = sheets.indexOf(cs);
		}
		if(index >= 0)
		{
			sheets.remove(index);
			sheets.add(index, t);
		}
		return true;
	}
	public boolean updateSpec(SkillSpec t) 
	{
		int index = -1;
		for(SkillSpec ss : specs)
		{
			if(ss.getId()==t.getId())
				index = specs.indexOf(ss);
		}
		if(index >= 0)
		{
			specs.remove(index);
			specs.add(index, t);
		}
		return true;
	}
	public boolean updateCSA(CharacterSheetAdvantage t) 
	{
		int index = -1;
		for(CharacterSheetAdvantage csa : advs)
		{
			if(csa.getId()==t.getId())
				index = advs.indexOf(csa);
		}
		if(index >= 0)
		{
			advs.remove(index);
			advs.add(index, t);
		}
		return true;
	}
	public boolean updateCSD(CharacterSheetDisadvantage t) 
	{
		int index = -1;
		for(CharacterSheetDisadvantage csd : disadvs)
		{
			if(csd.getId()==t.getId())
				index = disadvs.indexOf(csd);
		}
		if(index >= 0)
		{
			disadvs.remove(index);
			disadvs.add(index, t);
		}
		return true;
	}
	public boolean updateCSP(CharacterSheetPower t) 
	{
		int index = -1;
		for(CharacterSheetPower csp : powers)
		{
			if(csp.getId()==t.getId())
				index = powers.indexOf(csp);
		}
		if(index >= 0)
		{
			powers.remove(index);
			powers.add(index, t);
		}
		return true;
	}
	public boolean updateInv(Inventory t) 
	{
		int index = -1;
		for(Inventory i : invs)
		{
			if(i.getCharacterSheetId()==t.getCharacterSheetId())
				index = invs.indexOf(i);
		}
		if(index >= 0)
		{
			invs.remove(index);
			invs.add(index, t);
		}
		return true;
	}
	public boolean updateItem(Item t) 
	{
		int index = -1;
		for(Item i : items)
		{
			if(i.getId()==t.getId())
				index = items.indexOf(i);
		}
		if(index >= 0)
		{
			items.remove(index);
			items.add(index, t);
		}
		return true;
	}

	@Override
	public boolean delete(CharacterSheet t) 
	{
		sheets.remove(t);
		return true;
	}
	public boolean deleteSheet(CharacterSheet t) 
	{
		sheets.remove(t);
		return true;
	}
	public boolean deleteSpec(SkillSpec t) 
	{
		specs.remove(t);
		return true;
	}
	public boolean deleteCSA(CharacterSheetAdvantage t) 
	{
		advs.remove(t);
		return true;
	}
	public boolean deleteCSD(CharacterSheetDisadvantage t) 
	{
		disadvs.remove(t);
		return true;
	}
	public boolean deleteCSP(CharacterSheetPower t) 
	{
		powers.remove(t);
		return true;
	}
	public boolean deleteInv(Inventory t) 
	{
		invs.remove(t);
		return true;
	}
	public boolean deleteItem(Item t) 
	{
		items.remove(t);
		return true;
	}

	public boolean saveAll()
	{
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(sheetsFile))))
		{
			// CharacterSheet
			out.println("$$CharacterSheetBegin$$");
			for (CharacterSheet cs : sheets)
			{
				out.println(cs.getId() + FIELD_SEP + cs.getName() + FIELD_SEP + cs.getPicture() 
				+ FIELD_SEP + cs.getAllDemographics() + FIELD_SEP + cs.getAllMiscStats() 
				+ FIELD_SEP + cs.getAllStats());
			}
			out.println("$$CharacterSheetEnd$$");
			out.println();
			// SkillSpec
			out.println("$$SkillSpecBegin$$");
			for (SkillSpec ss : specs)
			{
				out.println(ss.getId() + FIELD_SEP + ss.getCharacterSheetId() + FIELD_SEP + ss.getSkill() 
				+ FIELD_SEP + ss.getDescription());
			}
			out.println("$$SkillSpecEnd$$");
			out.println();
			// CSA
			out.println("$$CharacterSheetAdvantageBegin$$");
			for (CharacterSheetAdvantage csa : advs)
			{
				out.println(csa.getId() + FIELD_SEP + csa.getCharacterSheetId() + FIELD_SEP + csa.getAdvStr() 
				+ FIELD_SEP + csa.getDescription());
			}
			out.println("$$CharacterSheetAdvantageEnd$$");
			out.println();
			// CSD
			out.println("$$CharacterSheetDisadvantageBegin$$");
			for (CharacterSheetDisadvantage csd : disadvs)
			{
				out.println(csd.getId() + FIELD_SEP + csd.getCharacterSheetId() + FIELD_SEP + csd.getDisadvStr() 
				+ FIELD_SEP + csd.getDescription());
			}
			out.println("$$CharacterSheetDisadvantageEnd$$");
			out.println();
			// CSP
			out.println("$$CharacterSheetPowerBegin$$");
			for (CharacterSheetPower csp : powers)
			{
				out.println(csp.getId() + FIELD_SEP + csp.getCharacterSheetId() + FIELD_SEP + csp.getLevel() + FIELD_SEP + csp.getPowerStr() 
				+ FIELD_SEP + csp.getCost() + FIELD_SEP + csp.getSpecs() + FIELD_SEP + csp.getWeakness());
			}
			out.println("$$CharacterSheetPowerEnd$$");
			out.println();
			// Inventory
			out.println("$$InventoryBegin$$");
			for (Inventory i : invs)
			{
				out.println(i.getCharacterSheetId() + FIELD_SEP + i.getAllHeld() + FIELD_SEP + i.getStorageSplit());
			}
			out.println("$$InventoryEnd$$");
			out.println();
			// Items
			out.println("$$ItemBegin$$");
			for (Item i : items)
			{
				out.println(i.getId() + FIELD_SEP + i.getName() + FIELD_SEP + i.getDescStr() + FIELD_SEP + i.getPath());
			}
			out.println("$$ItemEnd$$");
			out.println();
			return true;
		}
		catch(IOException ioe)
		{
			System.out.println(ioe);
			System.out.println("Failed to save.");
			return false;
		}
	}
}
