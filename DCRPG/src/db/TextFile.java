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
		sheets = getAllSheets();
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
							ss.setLevel(Integer.parseInt(fields[4]));
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
		int index = t.getId();
		sheets.remove(index);
		sheets.add(index, t);
		return true;
	}
	public boolean updateSpec(SkillSpec t) 
	{
		int index = t.getId();
		specs.remove(index);
		specs.add(index, t);
		return true;
	}
	public boolean updateCSA(CharacterSheetAdvantage t) 
	{
		int index = t.getId();
		advs.remove(index);
		advs.add(index, t);
		return true;
	}
	public boolean updateCSD(CharacterSheetDisadvantage t) 
	{
		int index = t.getId();
		disadvs.remove(index);
		disadvs.add(index, t);
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
				+ FIELD_SEP + ss.getDescription() + FIELD_SEP + ss.getLevel());
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
