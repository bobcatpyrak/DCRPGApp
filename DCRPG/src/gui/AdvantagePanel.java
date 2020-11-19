package gui;
import business.*;
import library.*;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class AdvantagePanel extends JPanel 
{
	
	private CharacterSheet cs;
	private List<CharacterSheetAdvantage> advs = new ArrayList<>();
	private List<CharacterSheetDisadvantage> disadvs = new ArrayList<>();
	
	/**
	 * @wbp.parser.constructor
	 */
	public AdvantagePanel(int x, int y, CharacterSheet cs) 
	{
		super();
		this.cs = cs;
		advs = cs.getCSA();
		disadvs = cs.getCSD();
		
		setBackground(new Color(0, 0, 0));
		setLayout(null);
		
		JPanel advPanel = new JPanel();
		advPanel.setBackground(new Color(255, 99, 71));
		add(advPanel);
		advPanel.setLayout(null);
		
		JLabel advLabel = new JLabel("Advantages");
		advLabel.setBounds(7, 7, 123, 16);
		advLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		advLabel.setHorizontalAlignment(SwingConstants.LEFT);
		advPanel.add(advLabel);
		
		JPanel disadvPanel = new JPanel();
		disadvPanel.setBounds(7, advPanel.getY()+advPanel.getHeight()+10, 745, 20);
		disadvPanel.setBackground(new Color(32, 178, 170));
		add(disadvPanel);
		disadvPanel.setLayout(null);
		
		JLabel disadvLabel = new JLabel("Disadvantages");
		disadvLabel.setBounds(634, 7, 104, 16);
		disadvLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		disadvPanel.add(disadvLabel);
		
		int advLocation = 7;
		for(CharacterSheetAdvantage csa : advs)
		{
			advLocation += 20;
			String csaStr = "<html><b>";
			csaStr += csa.getAdv().nameA()+"</b>";
			if(csa.getAdv().param())
			{
				csaStr += " (" + csa.getDescription() + ")";
			}
			csaStr += " - " + csa.getAdv().description();
			csaStr += "</html>";
			
			JLabel csaLabel = new JLabel(csaStr);
			csaLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
			csaLabel.setHorizontalAlignment(SwingConstants.LEFT);
			
			csaLabel.setBounds(27, advLocation, 500, 0);
			for(int j = 0; j < csaStr.length(); j+=100)
			{
				csaLabel.setSize(500, csaLabel.getHeight()+20);
			}
			csaLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
			advPanel.add(csaLabel);
			
			//new stuff here
			JButton csdX = new JButton();
			csdX.setBounds(7, advLocation, 20, 20);
			csdX.setFont(new Font("Dialog", Font.PLAIN, 20));
			csdX.setText("x");
			csdX.setHorizontalTextPosition(JButton.LEFT);
			csdX.setVerticalTextPosition(JButton.TOP);
			csdX.setMargin(new Insets(0,0,0,0));
			advPanel.add(csdX);
			csdX.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					advs.remove(csa);
					cs.setCSA(advs);
					setNewCharacter(x, y, cs);
				}
			});
			// new stuff here
			advLocation = advLocation+csaLabel.getHeight();
		}
		if(advs.size()==0)
			advLocation+=20;
		
		advPanel.setBounds(7, 7, 745, advLocation+7);

		
		int disadvLocation = 7;
		for(CharacterSheetDisadvantage csd : disadvs)
		{
			disadvLocation += 20;
			String csdStr = "<html><b>";
			csdStr += csd.getDisadv().nameD()+"</b>";
			if(csd.getDisadv().param())
			{
				csdStr += " (" + csd.getDescription() + ")";
			}
			csdStr += " - " + csd.getDisadv().description();
			csdStr += "</html>";
			
			JLabel csdLabel = new JLabel(csdStr);
			csdLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
			csdLabel.setHorizontalAlignment(SwingConstants.LEFT);
			
			csdLabel.setBounds(228, disadvLocation, 500, 0);
			for(int j = 0; j < csdStr.length(); j+=100)
			{
				csdLabel.setSize(500, csdLabel.getHeight()+20);
			}
			csdLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
			disadvPanel.add(csdLabel);			
			
			disadvLocation = disadvLocation+csdLabel.getHeight();
		}
		if(disadvs.size()==0)
			disadvLocation+=20;
		disadvPanel.setBounds(7, 7+advPanel.getY()+advPanel.getHeight(), 745, disadvLocation+7);
		
	    setBounds(x, y, 759, 21+advPanel.getHeight()+disadvPanel.getHeight());


	}
	
	public void setNewCharacter(int x, int y, CharacterSheet cs)
	{
		removeAll();
		
		this.cs = cs;
		advs.clear();
		disadvs.clear();
		advs = cs.getCSA();
		disadvs = cs.getCSD();

		
		setBackground(new Color(0, 0, 0));
		setLayout(null);
		
		JPanel advPanel = new JPanel();
		advPanel.setBackground(new Color(255, 99, 71));
		add(advPanel);
		advPanel.setLayout(null);
		
		JLabel advLabel = new JLabel("Advantages");
		advLabel.setBounds(7, 7, 123, 16);
		advLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		advLabel.setHorizontalAlignment(SwingConstants.LEFT);
		advPanel.add(advLabel);
		
		JPanel disadvPanel = new JPanel();
		disadvPanel.setBounds(7, advPanel.getY()+advPanel.getHeight()+10, 745, 20);
		disadvPanel.setBackground(new Color(32, 178, 170));
		add(disadvPanel);
		disadvPanel.setLayout(null);
		
		JLabel disadvLabel = new JLabel("Disadvantages");
		disadvLabel.setBounds(634, 7, 104, 16);
		disadvLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		disadvPanel.add(disadvLabel);
		
		int advLocation = 7;
		for(CharacterSheetAdvantage csa : advs)
		{
			advLocation += 20;
			String csaStr = "<html><b>";
			csaStr += csa.getAdv().nameA()+"</b>";
			if(csa.getAdv().param())
			{
				csaStr += " (" + csa.getDescription() + ")";
			}
			csaStr += " - " + csa.getAdv().description();
			csaStr += "</html>";
			
			JLabel csaLabel = new JLabel(csaStr);
			csaLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
			csaLabel.setHorizontalAlignment(SwingConstants.LEFT);
			
			csaLabel.setBounds(27, advLocation, 500, 0);
			for(int j = 0; j < csaStr.length(); j+=100)
			{
				csaLabel.setSize(500, csaLabel.getHeight()+20);
			}
			csaLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
			advPanel.add(csaLabel);
			
			JButton csdX = new JButton();
			csdX.setBounds(7, advLocation, 20, 20);
			csdX.setFont(new Font("Dialog", Font.PLAIN, 20));
			csdX.setText("x");
			csdX.setHorizontalTextPosition(JButton.LEFT);
			csdX.setVerticalTextPosition(JButton.TOP);
			csdX.setMargin(new Insets(0,0,0,0));
			advPanel.add(csdX);
			csdX.addActionListener(new ActionListener() // right now, this button removes every single advantage and disadvantage
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					System.out.println("Removing " + csa);
					advs.remove(csa);
					for(CharacterSheetAdvantage aaa : advs)
						System.out.println("Still have CSA#: "+aaa);
					cs.setCSA(advs);
					setNewCharacter(x, y, cs);
				}
			});
			
			advLocation = advLocation+csaLabel.getHeight();
		}
		if(advs.size()==0)
			advLocation+=20;
		advPanel.setBounds(7, 7, 745, advLocation+7);

		
		int disadvLocation = 7;
		for(CharacterSheetDisadvantage csd : disadvs)
		{
			disadvLocation += 20;
			String csdStr = "<html><b>";
			csdStr += csd.getDisadv().nameD()+"</b>";
			if(csd.getDisadv().param())
			{
				csdStr += " (" + csd.getDescription() + ")";
			}
			csdStr += " - " + csd.getDisadv().description();
			csdStr += "</html>";
			
			JLabel csdLabel = new JLabel(csdStr);
			csdLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
			csdLabel.setHorizontalAlignment(SwingConstants.LEFT);
			
			csdLabel.setBounds(238, disadvLocation, 500, 0);
			for(int j = 0; j < csdStr.length(); j+=100)
			{
				csdLabel.setSize(500, csdLabel.getHeight()+20);
			}
			csdLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
			disadvPanel.add(csdLabel);
			disadvLocation = disadvLocation+csdLabel.getHeight();
		}
		if(disadvs.size()==0)
			disadvLocation+=20;
		disadvPanel.setBounds(7, 7+advPanel.getY()+advPanel.getHeight(), 745, disadvLocation+7);
		
	    setBounds(x, y, 759, 21+advPanel.getHeight()+disadvPanel.getHeight());
	}
	
	public List<CharacterSheetAdvantage> saveAdvs()
	{
		return advs;
	}
	public List<CharacterSheetDisadvantage> saveDisadvs()
	{
		return disadvs;
	}
}
