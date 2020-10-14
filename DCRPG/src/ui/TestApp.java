package ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import business.*;
import db.*;

public class TestApp 
{
	private static TextFile dao = new TextFile();

	public static void main(String[] args) 
	{
		createDir();
		
		List<CharacterSheet> sheets = dao.getAllSheets();
		List<SkillSpec> specs = dao.getAllSpecs();
		
		int nextSheetId = 0;
		int nextSpecId = 0;
		
		if(sheets.size() > 0)
			nextSheetId = sheets.get(sheets.size()-1).getId() + 1;
		if(specs.size() > 0)
			nextSpecId = specs.get(specs.size()-1).getId() + 1;

		
		CharacterSheet batman = new CharacterSheet(nextSheetId);
		batman.setName("Batman");
		batman.setPicture("batman.png");
		batman.setAllDemographics("CEO%Batcave%45%Male%Human%Tall%Muscular%Dark%Dark");
		batman.setAllMiscStats("2d6%6%5%68%3%250%0%17%45%46");
		batman.setAllStats("6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6%6");
		
		SkillSpec batarang = new SkillSpec(nextSpecId, nextSheetId, "Thrown Weapons", "Batarangs", 3);
		
		sheets.add(batman);
		specs.add(batarang);
		
		dao.saveAll();
	}


	private static void createDir()
	{
		// Creates a folder for sheets if doesn't exist
		String dirString = "DCRPGCharacterSheets";
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
		String fileString = "DCRPGCharacterSheets.txt";
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
