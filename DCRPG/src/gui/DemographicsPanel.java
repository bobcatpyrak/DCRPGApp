package gui;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import business.CharacterSheet;

public class DemographicsPanel extends JPanel
{
	private CharacterSheet cs;
	
	public DemographicsPanel(CharacterSheet cs)
	{
		this.cs = cs;
		setBounds(0, 97, 851, 70);
		setBackground(new Color(204, 255, 255));
		setBorder(new LineBorder(Color.black, 2));
		setLayout(null);
		
		setNewCharacter(cs);
	}
	
	@SuppressWarnings("deprecation")
	public void setNewCharacter(CharacterSheet cs)
	{
		removeAll();
		
		this.cs = cs;
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(3, 3, 54, 20);
		add(lblGender);
		
		JLabel lblHeight = new JLabel("Height");
		lblHeight.setBounds(3, 25, 54, 20);
		add(lblHeight);
		
		JLabel lblWeight = new JLabel("Weight");
		lblWeight.setBounds(3, 47, 54, 20);
		add(lblWeight);
		
		JLabel lblHairColor = new JLabel("Hair Color");
		lblHairColor.setBounds(130, 47, 74, 20);
		add(lblHairColor);
		
		JFormattedTextField genderField = new JFormattedTextField();
		genderField.setBounds(45, 3, 80, 20);
		add(genderField);
		genderField.setHorizontalAlignment(SwingConstants.RIGHT);
		genderField.setColumns(10);
		genderField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				cs.setGender(genderField.getText());
			}
		});

		
		JFormattedTextField heightField = new JFormattedTextField();
		heightField.setBounds(45, 25, 80, 20);
		add(heightField);
		heightField.setHorizontalAlignment(SwingConstants.RIGHT);
		heightField.setColumns(10);
		heightField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				cs.setHeight(heightField.getText());
			}
		});
		
		JFormattedTextField weightField = new JFormattedTextField();
		weightField.setBounds(45, 47, 80, 20);
		add(weightField);
		weightField.setHorizontalAlignment(SwingConstants.RIGHT);
		weightField.setColumns(10);
		weightField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				cs.setWeight(weightField.getText());
			}
		});
		
		JFormattedTextField hairColorField = new JFormattedTextField();
		hairColorField.setBounds(190, 47, 80, 20);
		add(hairColorField);
		hairColorField.setHorizontalAlignment(SwingConstants.RIGHT);
		hairColorField.setColumns(10);
		hairColorField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				cs.setHairColor(hairColorField.getText());
			}
		});
		genderField.setText(cs.getGender());
		heightField.setText(cs.getHeight());
		weightField.setText(cs.getWeight());
		hairColorField.setText(cs.getHairColor());
		
		JLabel lblRace = new JLabel("Race");
		lblRace.setBounds(130, 3, 90, 20);
		add(lblRace);
		
		JFormattedTextField raceField = new JFormattedTextField();
		raceField.setBounds(190, 3, 80, 20);
		add(raceField);
		raceField.setHorizontalAlignment(SwingConstants.RIGHT);
		raceField.setColumns(10);
		raceField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				cs.setRace(raceField.getText());
			}
		});
		raceField.setText(cs.getRace());
		
		JLabel lblEyeColor = new JLabel("Eye Color");
		lblEyeColor.setBounds(130, 25, 90, 20);
		add(lblEyeColor);
		
		JFormattedTextField eyeColorField = new JFormattedTextField();
		eyeColorField.setBounds(190, 25, 80, 20);
		add(eyeColorField);
		eyeColorField.setHorizontalAlignment(SwingConstants.RIGHT);
		eyeColorField.setColumns(10);
		eyeColorField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				cs.setEyeColor(eyeColorField.getText());
			}
		});
		eyeColorField.setText(cs.getEyeColor());
		
		JLabel lblFullName = new JLabel("Full Name");
		lblFullName.setBounds(275, 3, 90, 20);
		add(lblFullName);
		
		JFormattedTextField fullNameField = new JFormattedTextField();
		fullNameField.setBounds(345, 3, 211, 20);
		add(fullNameField);
		fullNameField.setHorizontalAlignment(SwingConstants.RIGHT);
		fullNameField.setColumns(10);
		fullNameField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				cs.setFullName(fullNameField.getText());
			}
		});
		fullNameField.setText(cs.getFullName());
		
		JLabel lblOccupation = new JLabel("Occupation");
		lblOccupation.setBounds(275, 25, 74, 20);
		add(lblOccupation);
		
		JFormattedTextField occupationField = new JFormattedTextField();
		occupationField.setBounds(345, 25, 211, 20);
		add(occupationField);
		occupationField.setHorizontalAlignment(SwingConstants.RIGHT);
		occupationField.setColumns(10);
		occupationField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				cs.setOccupation(occupationField.getText());
			}
		});
		occupationField.setText(cs.getOccupation());
		
				
		JLabel lblHomeBase = new JLabel("Home Base");
		lblHomeBase.setBounds(275, 47, 90, 20);
		add(lblHomeBase);
		
		JFormattedTextField baseOfOperationsField = new JFormattedTextField();
		baseOfOperationsField.setBounds(345, 47, 211, 20);
		add(baseOfOperationsField);
		baseOfOperationsField.setHorizontalAlignment(SwingConstants.RIGHT);
		baseOfOperationsField.setColumns(10);
		baseOfOperationsField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				cs.setBaseOfOperations(baseOfOperationsField.getText());
			}
		});
		baseOfOperationsField.setText(cs.getBaseOfOperations());
		
		JLabel lblHeroPoints = new JLabel("Hero Points");
		lblHeroPoints.setBounds(561, 3, 90, 20);
		add(lblHeroPoints);
		
				
		JLabel lblVillainPoints = new JLabel("Villain Points");
		lblVillainPoints.setBounds(561, 25, 74, 20);
		add(lblVillainPoints);
		
		JLabel lblAvailableRenown = new JLabel("Renown");
		lblAvailableRenown.setBounds(561, 47, 90, 20);
		add(lblAvailableRenown);
		
		JFormattedTextField heroPointsField = new JFormattedTextField();
		heroPointsField.setBounds(646, 3, 50, 20);
		add(heroPointsField);
		heroPointsField.setHorizontalAlignment(SwingConstants.RIGHT);
		heroPointsField.setColumns(10);
		heroPointsField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				cs.setHeroPoints(heroPointsField.getText());
			}
		});
		heroPointsField.setValue(cs.getHeroPoints());
		
		JFormattedTextField villainPointsField = new JFormattedTextField();
		villainPointsField.setBounds(646, 25, 50, 20);
		add(villainPointsField);
		villainPointsField.setHorizontalAlignment(SwingConstants.RIGHT);
		villainPointsField.setColumns(10);
		villainPointsField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				cs.setVillainPoints(villainPointsField.getText());
			}
		});
		villainPointsField.setValue(cs.getVillainPoints());
		
		JFormattedTextField availableRenownField = new JFormattedTextField();
		availableRenownField.setBounds(646, 47, 50, 20);
		add(availableRenownField);
		availableRenownField.setHorizontalAlignment(SwingConstants.RIGHT);
		availableRenownField.setColumns(10);
		availableRenownField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				cs.setAvailableRenown(availableRenownField.getText());
			}
		});
		availableRenownField.setValue(cs.getAvailableRenown());
		
				
		JLabel lblPowerPoints = new JLabel("Power Points");
		lblPowerPoints.setBounds(701, 3, 90, 20);
		add(lblPowerPoints);
		
				
		JLabel lblSkillPoints = new JLabel("Skill Points");
		lblSkillPoints.setBounds(701, 25, 90, 20);
		add(lblSkillPoints);
		
		JFormattedTextField powerPointsField = new JFormattedTextField();
		powerPointsField.setBounds(791, 3, 50, 20);
		add(powerPointsField);
		powerPointsField.setHorizontalAlignment(SwingConstants.RIGHT);
		powerPointsField.setColumns(10);
		powerPointsField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				cs.setPowerPoints(powerPointsField.getText());
			}
		});
		powerPointsField.setValue(cs.getPowerPoints());
		
				
		JFormattedTextField skillPointsField = new JFormattedTextField();
		skillPointsField.setBounds(791, 25, 50, 20);
		add(skillPointsField);
		skillPointsField.setHorizontalAlignment(SwingConstants.RIGHT);
		skillPointsField.setColumns(10);
		skillPointsField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				cs.setSkillPoints(skillPointsField.getText());
			}
		});
		skillPointsField.setValue(cs.getSkillPoints());
		
		
		genderField.setNextFocusableComponent(heightField);
		heightField.setNextFocusableComponent(weightField);
		weightField.setNextFocusableComponent(raceField);
		raceField.setNextFocusableComponent(eyeColorField);
		eyeColorField.setNextFocusableComponent(hairColorField);
		hairColorField.setNextFocusableComponent(fullNameField);
		fullNameField.setNextFocusableComponent(occupationField);
		occupationField.setNextFocusableComponent(baseOfOperationsField);
		baseOfOperationsField.setNextFocusableComponent(heroPointsField);
		heroPointsField.setNextFocusableComponent(villainPointsField);
		villainPointsField.setNextFocusableComponent(availableRenownField);
		availableRenownField.setNextFocusableComponent(powerPointsField);
		powerPointsField.setNextFocusableComponent(skillPointsField);
		skillPointsField.setNextFocusableComponent(genderField);
	}
}
