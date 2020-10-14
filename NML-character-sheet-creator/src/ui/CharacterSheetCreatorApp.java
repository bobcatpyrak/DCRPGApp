package ui;


import java.io.*;
import java.nio.file.*;
import java.util.*;

import business.*;
import db.*;
import ui.console.Console;

public class CharacterSheetCreatorApp 
{

	private static DAO<CharacterSheet> sheetDAO = new CharacterSheetTextFile();

	public static void main(String[] args)
	{
		createDir();
		
		System.out.println("DCRPG Character Stats Builder");

		boolean loop = true;
		while(loop)
		{
			List<CharacterSheet> sheets = sheetDAO.getAll();

			int nextId = 0;
			
			if(sheets.size() > 0)
				nextId = (sheets.get(sheets.size()-1)).getId() + 1;
			
			System.out.println("\nMENU\n"
					+ "1. View all characters\n"
					+ "2. View a single character's stats\n"
					+ "3. Add a character\n"
					+ "4. Edit a character\n"
					+ "5. Remove a character\n"
					+ "6. Give up. Quit.");
			
			int choice = Console.getInt("\n> ", 1, 6);
			
			switch(choice)
			{
			case 1:
				if(sheets.size() > 0)
				{
					System.out.println();
					int column = 0;
					for(CharacterSheet sheet : sheets)
					{
						System.out.print(sheet.getId() + StringUtil.pad(".", 5) + StringUtil.pad(sheet.getName(), 20));
						column++;
						if(column % 5 == 0)
							System.out.println();
					}
				}
				else
					System.out.println("NMLCharaccterSheets.txt is empty.");
				break;
			case 2:
				if(sheets.size() > 0)
				{
					int displayChoice = Console.getInt("\n> Display stats of which character? ", 0, nextId - 1);
					int indexChoice = -1;

					for (CharacterSheet cs: sheets)
					{
						if(cs.getId() == displayChoice)
							indexChoice = sheets.indexOf(cs);
					}
					if(indexChoice == -1)
						System.out.println("Character not found");
					else
						displayStats(sheets.get(indexChoice));
				}
				else
					System.out.println("NMLCharaccterSheets.txt is empty.");
				break;
			case 3:
				if(sheetDAO.add(addCharacter(nextId)))
					System.out.println(sheetDAO.get(sheetDAO.getAll().size() - 1).getName() + " has been successfully added.");
				else
					System.out.println("Error.");
				break;
			case 4:
				if (sheets.size() > 0)
				{
					int editId = Console.getInt("\n> Edit which character? ", 0, nextId - 1);
					int indexChoice = -1;

					for (CharacterSheet cs: sheets)
					{
						if(cs.getId() == editId)
							indexChoice = sheets.indexOf(cs);
					}
					if(indexChoice == -1)
						System.out.println("Character not found");
					else
					{
						if(sheetDAO.update(addCharacter(indexChoice)))
							System.out.println(sheetDAO.get(indexChoice).getName() + " has been successfully edited.");
						else
							System.out.println("Error.");
					}
				}
				else
					System.out.println("NMLCharaccterSheets.txt is empty.");
				break;
			case 5:
				if(sheets.size() > 0)
				{
					int deleteId = Console.getInt("\n> Delete which character? ", 0, nextId - 1);
					int indexChoice = -1;

					for (CharacterSheet cs: sheets)
					{
						if(cs.getId() == deleteId)
							indexChoice = sheets.indexOf(cs);
					}
					if(indexChoice == -1)
						System.out.println("Character not found");
					else
					{
						if(sheetDAO.delete(sheetDAO.get(indexChoice)))
							System.out.println("Success.");
						else
							System.out.println("Error.");
					}
				}
				else
					System.out.println("NMLCharaccterSheets.txt is empty.");
				break;
			case 6:
				System.out.println("Program OVER.");
				loop = false;
				break;
			}
		}
		
		System.out.println("bye");
	}
	
	private static CharacterSheet addCharacter(int nextId)
	{
		CharacterSheet c = new CharacterSheet(nextId);
		c.setName(Console.getRequiredString("Name?: "));
		c.setReflexes(Console.getInt("Reflexes: ", 0, 10));
			c.setAcrobatics(Console.getInt("Acrobatics: ", 0, 10));
			c.setDodge(Console.getInt("Dodge: ", 0, 10));
			c.setHandToHand(Console.getInt("Hand-to-Hand: ", 0, 10));
			c.setMeleeWeapons(Console.getInt("Melee Weapons: ", 0, 10));
			c.setStealth(Console.getInt("Stealth: ", 0, 10));
		c.setCoordination(Console.getInt("Coordination: ", 0, 10));
			c.setCatching(Console.getInt("Catch: ", 0, 10));
			c.setClimb(Console.getInt("Climb: ", 0, 10));
			c.setDrive(Console.getInt("Drive: ", 0, 10));
			c.setMarksmanship(Console.getInt("Marksmanship: ", 0, 10));
			c.setThievery(Console.getInt("Thievery: ", 0, 10));
			c.setThrownWeapons(Console.getInt("Thrown Weapons: ", 0, 10));
		c.setPhysique(Console.getInt("Physique: ", 0, 10));
			c.setAthletics(Console.getInt("Athletics: ", 0, 10));
			c.setLeap(Console.getInt("Leap: ", 0, 10));
			c.setLifting(Console.getInt("Lifting: ", 0, 10));
			c.setResistance(Console.getInt("Resistance: ", 0, 10));
			c.setRunning(Console.getInt("Running: ", 0, 10));
			c.setSwimming(Console.getInt("Swimming: ", 0, 10));
		c.setKnowledge(Console.getInt("Knowledge: ", 0, 10));
			c.setArcaneLore(Console.getInt("Arcane Lore: ", 0, 10));
			c.setDemolitions(Console.getInt("Demolitions: ", 0, 10));
			c.setLanguages(Console.getInt("Languages: ", 0, 10));
			c.setMedicine(Console.getInt("Medicine: ", 0, 10));
			c.setScholar(Console.getInt("Scholar: ", 0, 10));
			c.setScience(Console.getInt("Science: ", 0, 10));
			c.setSecurity(Console.getInt("Security: ", 0, 10));
		c.setPerception(Console.getInt("Perception: ", 0, 10));
			c.setArtist(Console.getInt("Artist: ", 0, 10));
			c.setEngineering(Console.getInt("Engineering: ", 0, 10));
			c.setSearch(Console.getInt("Search: ", 0, 10));
			c.setStreetwise(Console.getInt("Streetwise: ", 0, 10));
			c.setSurveillance(Console.getInt("Surveillance: ", 0, 10));
			c.setSurvival(Console.getInt("Survival: ", 0, 10));
		c.setPresence(Console.getInt("Presence: ", 0, 10));
			c.setBluff(Console.getInt("Bluff: ", 0, 10));
			c.setCharm(Console.getInt("Charm: ", 0, 10));
			c.setIntimidation(Console.getInt("Intimidation: ", 0, 10));
			c.setPersuasion(Console.getInt("Persuasion: ", 0, 10));
			c.setWillpower(Console.getInt("Willpower: ", 0, 10));
			
		return c;
	}
	
	private static void displayStats(CharacterSheet c)
	{
		System.out.println("===========================================================================\n"
						 + "|Name: " + c.getName() + "\n"
						 + "===========================================================================\n"
						 + "|Reflexes:\t\t" 											+ StringUtil.pad(String.valueOf(c.getReflexes()), 2) + 							"| Coordination:\t" 								+ StringUtil.pad(String.valueOf(c.getCoordination()), 2) + 						"| Physique:\t\t" 									+ StringUtil.pad(String.valueOf(c.getPhysique()), 2) + 						"|\n"
						 + "|Acrobatics: "				+ c.getAcrobatics() + "\t\t" 	+ StringUtil.pad(String.valueOf(c.getReflexes()+c.getAcrobatics()), 2) + 		"| Catch: " 		+ c.getCatching() + "\t\t" 		+ StringUtil.pad(String.valueOf(c.getCoordination()+c.getCatching()), 2) + 		"| Athletics: " 	+ c.getAcrobatics() + "\t" 		+ StringUtil.pad(String.valueOf(c.getPhysique()+c.getAthletics()), 2) + 	"|\n"
						 + "|Dodge: "					+ c.getDodge() + "\t\t" 		+ StringUtil.pad(String.valueOf(c.getReflexes()+c.getDodge()), 2) + 			"| Climb: " 		+ c.getClimb() + "\t\t" 		+ StringUtil.pad(String.valueOf(c.getCoordination()+c.getClimb()), 2) + 		"| Leap: " 			+ c.getLeap() + "\t\t" 			+ StringUtil.pad(String.valueOf(c.getPhysique()+c.getLeap()), 2) + 			"|\n"
						 + "|Hand-to-Hand: "			+ c.getHandToHand() + "\t" 		+ StringUtil.pad(String.valueOf(c.getReflexes()+c.getHandToHand()), 2) + 		"| Drive: " 		+ c.getDrive() + "\t\t" 		+ StringUtil.pad(String.valueOf(c.getCoordination()+c.getDrive()), 2) + 		"| Lifting: " 		+ c.getLifting() + "\t\t" 		+ StringUtil.pad(String.valueOf(c.getPhysique()+c.getLifting()), 2) + 		"|\n"
						 + "|Melee Weapons: " 			+ c.getMeleeWeapons() + "\t" 	+ StringUtil.pad(String.valueOf(c.getReflexes()+c.getMeleeWeapons()), 2) + 		"| Marksmanship: " 	+ c.getMarksmanship() + "\t" 	+ StringUtil.pad(String.valueOf(c.getCoordination()+c.getMarksmanship()), 2) + 	"| Resistance: " 	+ c.getResistance() + "\t" 		+ StringUtil.pad(String.valueOf(c.getPhysique()+c.getResistance()), 2) + 	"|\n"
						 + "|Stealth: " 				+ c.getStealth() + "\t\t" 		+ StringUtil.pad(String.valueOf(c.getReflexes()+c.getStealth()), 2) + 			"| Thievery: " 		+ c.getThievery() + "\t\t"	 	+ StringUtil.pad(String.valueOf(c.getCoordination()+c.getThievery()), 2) + 		"| Running: " 		+ c.getRunning() + "\t\t" 		+ StringUtil.pad(String.valueOf(c.getPhysique()+c.getRunning()), 2) + 		"|\n"
						 + "|\t\t\t  | Thrown Weapons: " + c.getThrownWeapons() + "\t"	+ StringUtil.pad(String.valueOf(c.getCoordination()+c.getThrownWeapons()), 2) + "| Swimming: " 		+ c.getSwimming() + "\t\t" 		+ StringUtil.pad(String.valueOf(c.getPhysique()+c.getSwimming()), 2) + 			"|\n"
						 + "---------------------------------------------------------------------------\n"
						 + "|Knowledge:\t\t" 											+ StringUtil.pad(String.valueOf(c.getKnowledge()), 2) + 						"| Perception:\t\t" 								+ StringUtil.pad(String.valueOf(c.getPerception()), 2) + 						"| Presence:\t\t" 									+ StringUtil.pad(String.valueOf(c.getPresence()), 2) + 						"|\n"
						 + "|Arcane Lore: " 			+ c.getArcaneLore() + "\t\t" 	+ StringUtil.pad(String.valueOf(c.getKnowledge()+c.getArcaneLore()), 2) + 		"| Artist: " 		+ c.getArtist() + "\t\t" 		+ StringUtil.pad(String.valueOf(c.getPerception()+c.getArtist()), 2) + 			"| Bluff: " 		+ c.getArcaneLore() + "\t\t" 	+ StringUtil.pad(String.valueOf(c.getPresence()+c.getBluff()), 2) + 		"|\n"
						 + "|Demolitions: " 			+ c.getDemolitions() + "\t\t" 	+ StringUtil.pad(String.valueOf(c.getKnowledge()+c.getDemolitions()), 2) + 		"| Engineering: " 	+ c.getEngineering() + "\t" 	+ StringUtil.pad(String.valueOf(c.getPerception()+c.getEngineering()), 2) + 	"| Charm: " 		+ c.getCharm() + "\t\t" 		+ StringUtil.pad(String.valueOf(c.getPresence()+c.getCharm()), 2) + 		"|\n"
						 + "|Languages: " 				+ c.getLanguages() + "\t\t" 	+ StringUtil.pad(String.valueOf(c.getKnowledge()+c.getLanguages()), 2) + 		"| Search: " 		+ c.getSearch() + "\t\t" 		+ StringUtil.pad(String.valueOf(c.getPerception()+c.getSearch()), 2) + 			"| Intimidation: " 	+ c.getIntimidation() + "\t" 	+ StringUtil.pad(String.valueOf(c.getPresence()+c.getIntimidation()), 2) + 	"|\n"
						 + "|Medicine: " 				+ c.getMedicine() + "\t\t"	 	+ StringUtil.pad(String.valueOf(c.getKnowledge()+c.getMedicine()), 2) + 		"| Streetwise: " 	+ c.getStreetwise() + "\t" 		+ StringUtil.pad(String.valueOf(c.getPerception()+c.getStreetwise()), 2) + 		"| Persuasion: " 	+ c.getPersuasion() + "\t" 		+ StringUtil.pad(String.valueOf(c.getPresence()+c.getPersuasion()), 2) + 	"|\n"
						 + "|Scholar: " 				+ c.getScholar() + "\t\t"	 	+ StringUtil.pad(String.valueOf(c.getKnowledge()+c.getScholar()), 2) + 			"| Surveillance: " 	+ c.getSurveillance() + "\t" 	+ StringUtil.pad(String.valueOf(c.getPerception()+c.getSurveillance()), 2) + 	"| Willpower: " 	+ c.getWillpower() + "\t" 		+ StringUtil.pad(String.valueOf(c.getPresence()+c.getWillpower()), 2) + 	"|\n"
						 + "|Science: " 				+ c.getScience() + "\t\t" 		+ StringUtil.pad(String.valueOf(c.getKnowledge()+c.getScience()), 2) + 			"| Survival: " 		+ c.getSurvival() + "\t\t" 		+ StringUtil.pad(String.valueOf(c.getPerception()+c.getSurvival()), 2) + 		"| \t\t\t  " + 																													"|\n"
						 + "|Security: " 				+ c.getSecurity() + "\t\t" 		+ StringUtil.pad(String.valueOf(c.getKnowledge()+c.getSecurity()), 2) + 		"| \t\t\t" +  																														"  |\t\t\t" + 																													"  |\n"
						 + "===========================================================================\n"

				);

	}
	
	private static void createDir()
	{
		// Creates a folder for sheets if doesn't exist
		String dirString = "c:/Users/Max-Student/Documents/NMLCharacterSheets";
		Path dirPath = Paths.get(dirString);
		if (Files.notExists(dirPath))
			try 
			{
				Files.createDirectories(dirPath);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		
		// Creates a save file if doesn't exist
		String fileString = "NMLCharacterSheets.txt";
		Path filePath = Paths.get(dirString, fileString);
		if (Files.notExists(filePath))
			try {
				Files.createFile(filePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
