package gui;
import business.*;
import library.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Font;
import java.awt.Insets;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class AdvantagePanel extends JPanel 
{
	
	private CharacterSheet cs;
	private List<CharacterSheetAdvantage> advs = new ArrayList<>();
	private List<CharacterSheetDisadvantage> disadvs = new ArrayList<>();
	private List<CharacterSheetAdvantage> advsList = new ArrayList<>();
	private List<CharacterSheetDisadvantage> disadvsList = new ArrayList<>();
	List<Advantage> advantages = Advantage.getAll();
	List<Disadvantage> disadvantages = Disadvantage.getAll();
	private Advantage dispA;
	private Disadvantage dispD;
	private int x;
	private int y;
	
	/**
	 * @wbp.parser.constructor
	 */
	public AdvantagePanel(int x, int y, CharacterSheet cs, List<CharacterSheetAdvantage> advsList, List<CharacterSheetDisadvantage> disadvsList) 
	{
		super();
		setNewCharacter(x, y, cs, advsList, disadvsList);
	}
	
	public void setNewCharacter(int x, int y, CharacterSheet cs, List<CharacterSheetAdvantage> advsList, List<CharacterSheetDisadvantage> disadvsList)
	{
		removeAll();
		
		this.cs = cs;
		advs.clear();
		disadvs.clear();
		dispA = null;
		dispD = null;
		this.x = x;
		this.y = y;
		this.advsList = advsList;
		this.disadvsList = disadvsList;

		for(CharacterSheetAdvantage csa : advsList)
		{
			if(csa.getCharacterSheetId() == cs.getId())
				advs.add(csa);
		}

		for(CharacterSheetDisadvantage csd : disadvsList)
		{
			if(csd.getCharacterSheetId() == cs.getId())
				disadvs.add(csd);
		}
		
		Collections.sort(advs);
		Collections.sort(disadvs);

		
		setBackground(new Color(0, 0, 0));
		setLayout(null);
		
		JPanel advPanel = new JPanel();
		advPanel.setBackground(new Color(255, 99, 71));
		advPanel.setBounds(5, 5, 649, 20);
		add(advPanel);
		advPanel.setLayout(null);
		
		JLabel advLabel = new JLabel("Advantages");
		advLabel.setBounds(5, 5, 83, 16);
		advLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		advLabel.setHorizontalAlignment(SwingConstants.LEFT);
		advPanel.add(advLabel);
		
		JButton csaAdd = new JButton();
		csaAdd.setBounds(advLabel.getX()+advLabel.getWidth()+5, advLabel.getY(), 16, 16);
		csaAdd.setFont(new Font("Dialog", Font.BOLD, 17));
		csaAdd.setText("+");
		csaAdd.setHorizontalTextPosition(SwingConstants.CENTER);
		csaAdd.setMargin(new Insets(0,0,0,0));
		advPanel.add(csaAdd);
		csaAdd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				advantagePicker(true);
			}
		});
		
		JPanel disadvPanel = new JPanel();
		disadvPanel.setBackground(new Color(32, 178, 170));
		disadvPanel.setBounds(5, 5+advPanel.getY()+advPanel.getHeight(), 649, 20);
		add(disadvPanel);
		disadvPanel.setLayout(null);
		
		JLabel disadvLabel = new JLabel("Disadvantages");
		disadvLabel.setBounds(disadvPanel.getWidth()-111, 5, 104, 16);
		disadvLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		disadvPanel.add(disadvLabel);
		
		JButton csdAdd = new JButton();
		csdAdd.setBounds(disadvLabel.getX()-23, advLabel.getY(), 16, 16);
		csdAdd.setFont(new Font("Dialog", Font.BOLD, 17));
		csdAdd.setText("+");
		csdAdd.setHorizontalTextPosition(SwingConstants.CENTER);
		csdAdd.setMargin(new Insets(0,0,0,0));
		disadvPanel.add(csdAdd);
		csdAdd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				advantagePicker(false);

			}
		});
		
		int advLocation = 25;
		for(CharacterSheetAdvantage csa : advs)
		{
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
			csaLabel.setVerticalAlignment(SwingConstants.TOP);
			
			csaLabel.setBounds(25, advLocation, 500, 0);
			for(int j = 0; j < csaStr.length()-20; j+=98)
			{
				csaLabel.setSize(500, csaLabel.getHeight()+20);
			}
			advPanel.add(csaLabel);
			
			JButton csaX = new JButton();
			csaX.setBounds(5, advLocation, 16, 20);
			csaX.setFont(new Font("Dialog", Font.BOLD, 15));
			csaX.setText("X");
			csaX.setHorizontalTextPosition(JButton.LEFT);
			csaX.setVerticalTextPosition(JButton.TOP);
			csaX.setMargin(new Insets(0,0,0,0));
			advPanel.add(csaX);
			csaX.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					advsList.remove(csa);
					cs.setCSA(advsList);
					setNewCharacter(x, y, cs, advsList, disadvsList);
				}
			});
			
			advLocation = advLocation+csaLabel.getHeight();
		}
		if(advs.size()==0)
			advLocation+=20;
		advPanel.setBounds(5, 5, 649, advLocation+5);

		
		int disadvLocation = 25;
		for(CharacterSheetDisadvantage csd : disadvs)
		{
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
			csdLabel.setVerticalAlignment(SwingConstants.TOP);

			
			csdLabel.setBounds(128, disadvLocation, 500, 0);
			for(int j = 0; j < csdStr.length(); j+=98)
			{
				csdLabel.setSize(500, csdLabel.getHeight()+20);
			}
			if(csdStr.length() < 110)
				csdLabel.setSize(500, 30);
			disadvPanel.add(csdLabel);			
			
			JButton csdX = new JButton();
			csdX.setBounds(csdLabel.getX()-20, disadvLocation, 16, 20);
			csdX.setFont(new Font("Dialog", Font.BOLD, 15));
			csdX.setText("X");
			csdX.setHorizontalTextPosition(JButton.LEFT);
			csdX.setVerticalTextPosition(JButton.TOP);
			csdX.setMargin(new Insets(0,0,0,0));
			disadvPanel.add(csdX);
			csdX.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					disadvsList.remove(csd);
					cs.setCSD(disadvsList);
					setNewCharacter(x, y, cs, advsList, disadvsList);
				}
			});
			
			disadvLocation = disadvLocation+csdLabel.getHeight();
		}
		if(disadvs.size()==0)
			disadvLocation+=20;
		disadvPanel.setBounds(5, 5+advPanel.getY()+advPanel.getHeight(), 649, disadvLocation+5);
		
	    setBounds(x, y, 659, 15+advPanel.getHeight()+disadvPanel.getHeight());
	    MainWindow.panel.setPreferredSize(new Dimension(MainWindow.panel.getWidth(), this.getY()+this.getHeight()+30));
	    MainWindow.dcrpgFrame.revalidate();
	}
	
	private void advantagePicker(boolean isAdv)
	{
		JFrame picker = new JFrame();
		picker.setBounds(MainWindow.dcrpgFrame.getWidth()/2 - 200, MainWindow.dcrpgFrame.getHeight()/2 - 160, 400, 320);
		picker.setVisible(true);
		picker.getContentPane().setLayout(null);
		picker.setFocusable(true);
		
		JPanel l = new JPanel();
		l.setLayout(null);
		picker.getContentPane().add(l);
		l.setBounds(0, 0, 400, 298);
		
		List<String> s = new ArrayList<String>();

		if(isAdv)
		{
			for(Advantage a : advantages)
			{
				s.add(a.nameA());
			}
		}
		else
		{
			for(Disadvantage d : disadvantages)
			{
				s.add(d.nameD());
			}
		}
		
		JList list = new JList(s.toArray());
		JScrollPane jsp = new JScrollPane(list);
		jsp.setBounds(0, 0, 200, 283);
		l.add(jsp);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		
		JLabel desc = new JLabel();
		desc.setBounds(207, 0, 170, 220);
		l.add(desc);
		
		JFormattedTextField param= new JFormattedTextField();
		param.setBounds(207, 231, 170, 20);
		l.add(param);
		param.setHorizontalAlignment(SwingConstants.LEFT);
		param.setColumns(10);
		param.setEnabled(false);

		JButton add = new JButton();
		add.setBounds(257,  258,  70,  20);
		add.setText("Add");
		l.add(add);
		
		JLabel warning = new JLabel();
		warning.setBounds(207, 204, 170, 20);
		warning.setForeground(Color.red);
		l.add(warning);
		
		ListSelectionListener listSelectionListener = new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent arg0) 
			{
				if(isAdv)
	        	{
		        	for(Advantage a : advantages)
		        	{
		        		if(a.nameA() == (String)list.getSelectedValue())
		        		dispA = a;	
		        	}
		        	desc.setText(dispA.description());
		        	if(dispA.param())
						param.setEnabled(true);
		        	else
		        		param.setEnabled(false);
	        	}
	        	else
	        	{
		        	for(Disadvantage d : disadvantages)
		        	{
		        		if(d.nameD() == (String)list.getSelectedValue())
		        		dispD = d;	
		        	}
		        	desc.setText(dispD.description());
		        	if(dispD.param())
		        		param.setEnabled(true);
		        	else
		        		param.setEnabled(false);
	        	}
	        	warning.setText("");	
			}	
		};
		list.addListSelectionListener(listSelectionListener);
		
		add.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(isAdv)
				{
					boolean duplicate = false;
					CharacterSheetAdvantage csa = new CharacterSheetAdvantage(MainWindow.nextCSAId, cs.getId(), dispA);
					for(CharacterSheetAdvantage c : advs)
					{
						if (csa.getAdvStr().equals(c.getAdvStr()))
							duplicate = true;
					}
					if(dispA == null)
					{
						warning.setText("Please select an Advantage");
					}
					else if(duplicate && !dispA.param())
					{
						warning.setText("Duplicate Advantage");
					}
					else if(dispA != null && dispA.param() && !param.getText().equals(""))
					{
						csa = new CharacterSheetAdvantage(MainWindow.nextCSAId, cs.getId(), dispA, param.getText());
						advsList.add(csa);
						MainWindow.nextCSAId++;
						picker.dispose();
						setNewCharacter(x, y, cs, advsList, disadvsList);
					}
					else if(dispA != null && !dispA.param())
					{
						advsList.add(csa);
						MainWindow.nextCSAId++;
						picker.dispose();
						setNewCharacter(x, y, cs, advsList, disadvsList);
					}
					else if(dispA.param() && param.getText().equals(""))
					{
						warning.setText("Add parameter please");
					}
				}
				else
				{
					boolean duplicate = false;
					CharacterSheetDisadvantage csd = new CharacterSheetDisadvantage(MainWindow.nextCSDId, cs.getId(), dispD);
					for(CharacterSheetDisadvantage c : disadvs)
					{
						if (csd.getDisadvStr().equals(c.getDisadvStr()))
							duplicate = true;
					}
					if(dispD == null)
					{
						warning.setText("Please select a Disadvantage");
					}
					else if(duplicate && !dispD.param())
					{
						warning.setText("Duplicate Disadvantage");
					}
					else if(dispD != null && dispD.param() && !param.getText().equals(""))
					{
						csd = new CharacterSheetDisadvantage(MainWindow.nextCSDId, cs.getId(), dispD, param.getText());
						disadvsList.add(csd);
						MainWindow.nextCSDId++;
						picker.dispose();
						setNewCharacter(x, y, cs, advsList, disadvsList);
					}
					else if(dispD != null && !dispD.param() || dispD.param() && !param.getText().equals(""))
					{
						disadvsList.add(csd);
						MainWindow.nextCSDId++;
						picker.dispose();
						setNewCharacter(x, y, cs, advsList, disadvsList);
					}
					else if(dispD.param() && param.getText().equals(""))
					{
						warning.setText("Add parameter please");
					}
				}
			}
		});
		
		picker.addWindowFocusListener(new WindowFocusListener()
		{
			@Override
			public void windowGainedFocus(WindowEvent arg0) {}
			@Override
			public void windowLostFocus(WindowEvent arg0) 
			{
				dispA = null;
				dispD = null;
				picker.dispose();
			}
		});
	}
	
	public List<CharacterSheetAdvantage> saveAdvs()
	{
		return advsList;
	}
	public List<CharacterSheetDisadvantage> saveDisadvs()
	{
		return disadvsList;
	}
}
