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
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JSpinner;

public class PowerPanel extends JPanel 
{
	
	private CharacterSheet cs;
	private List<CharacterSheetPower> pwrs = new ArrayList<>();
	private List<CharacterSheetPower> pwrsList = new ArrayList<>();
	List<Power> powers = Power.getAll();
	private Power disp;
	private int x;
	private int y;
	
	/**
	 * @wbp.parser.constructor
	 */
	public PowerPanel(int x, int y, CharacterSheet cs, List<CharacterSheetPower> pwrsList) 
	{
		super();
		setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setNewCharacter(x, y, cs, pwrsList);
	}
	
	public void setNewCharacter(int x, int y, CharacterSheet cs, List<CharacterSheetPower> pwrsList)
	{
		removeAll();
		
		this.cs = cs;
		pwrs.clear();
		disp = null;
		this.x = x;
		this.y = y;
		this.pwrsList = pwrsList;

		for(CharacterSheetPower csp : pwrsList)
		{
			if(csp.getCharacterSheetId() == cs.getId())
				pwrs.add(csp);
		}
		
		setBackground(new Color(69, 240, 208));
		setLayout(null);
		
		JLabel pwrLabel = new JLabel("Powers");
		pwrLabel.setBounds(5, 5, 60, 16);
		pwrLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		pwrLabel.setHorizontalAlignment(SwingConstants.LEFT);
		this.add(pwrLabel);

		JButton cspAdd = new JButton();
		cspAdd.setBounds(pwrLabel.getX()+pwrLabel.getWidth(), pwrLabel.getY(), 16, 16);
		cspAdd.setFont(new Font("Dialog", Font.BOLD, 17));
		cspAdd.setText("+");
		cspAdd.setHorizontalTextPosition(SwingConstants.CENTER);
		cspAdd.setMargin(new Insets(0,0,0,0));
		add(cspAdd);
		cspAdd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				powerPicker();
			}
		});
		
		int pwrLocation = 24;
		boolean color = true;
		for(CharacterSheetPower csp : pwrs)
		{
			JPanel pwrPanel = new JPanel();
			if(color)
			{
				pwrPanel.setBackground(new Color(238, 133, 225));
				color = false;
			}
			else
			{
				pwrPanel.setBackground(new Color(44, 189, 162));
				color = true;
			}
			


			pwrPanel.setBounds(5, pwrLocation, this.getWidth()-10, 40);
			if(csp.getShorthand().equals(""))
			{
				pwrPanel.setSize(this.getWidth()-10, 20);
				pwrLocation += 25;
			}
			else
				pwrLocation += 45;

			add(pwrPanel);
			pwrPanel.setLayout(null);
			
			JLabel cspLabel = new JLabel(csp.getPower().nameP());
			cspLabel.setFont(new Font("Dialog", Font.BOLD, 12));
			cspLabel.setHorizontalAlignment(SwingConstants.LEFT);	
			cspLabel.setBounds(25, 0, 200, 20);
			pwrPanel.add(cspLabel);
			
			JLabel lvlLabel = new JLabel("Level: " + csp.getLevel());
			lvlLabel.setFont(new Font("Dialog", Font.BOLD, 12));
			lvlLabel.setHorizontalAlignment(SwingConstants.LEFT);	
			lvlLabel.setBounds(pwrPanel.getWidth()-50, 0, 50, 20);
			pwrPanel.add(lvlLabel);
			
			JLabel shorthandLabel = new JLabel(csp.getShorthand());
			shorthandLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
			shorthandLabel.setHorizontalAlignment(SwingConstants.LEFT);	
			shorthandLabel.setBounds(5, 20, pwrPanel.getWidth(), 20);
			pwrPanel.add(shorthandLabel);

			
			JButton cspX = new JButton();
			cspX.setBounds(5, 0, 16, 20);
			cspX.setFont(new Font("Dialog", Font.BOLD, 15));
			cspX.setText("X");
			cspX.setHorizontalTextPosition(JButton.LEFT);
			cspX.setVerticalTextPosition(JButton.TOP);
			cspX.setMargin(new Insets(0,0,0,0));
			pwrPanel.add(cspX);
			cspX.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					pwrsList.remove(csp);
					cs.setCSP(pwrsList);
					setNewCharacter(x, y, cs, pwrsList);
				}
			});
		}
		if(pwrs.size()==0)
			pwrLocation+=20;
		
	    setBounds(x, y, MainWindow.advPowerPanel.getWidth()-x, pwrLocation);
	    
	   // MainWindow.panel.setPreferredSize(new Dimension(MainWindow.panel.getWidth(), this.getY()+this.getHeight()+30));
	    MainWindow.dcrpgFrame.revalidate();
	}
	
	private void powerPicker()
	{
		JFrame picker = new JFrame();
		picker.setBounds(MainWindow.dcrpgFrame.getX() + 200, MainWindow.dcrpgFrame.getY() + 100, MainWindow.dcrpgFrame.getWidth() - 400, MainWindow.dcrpgFrame.getHeight() - 200);
		picker.setVisible(true);
		picker.getContentPane().setLayout(null);
		picker.setFocusable(true);
		
		JPanel l = new JPanel();
		l.setLayout(null);
		l.setBorder(new LineBorder(Color.black));
		picker.getContentPane().add(l);
		l.setBounds(0, 0, picker.getWidth()-16, picker.getHeight()-39);
		
		List<String> s = new ArrayList<String>();

		for(Power p : powers)
		{
			s.add(p.nameP());
		}
		
		
		JList list = new JList(s.toArray());
		JScrollPane jsp = new JScrollPane(list);
		jsp.setBounds(0, 0, l.getWidth()/7, l.getHeight());
		l.add(jsp);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel desc = new JLabel();
		desc.setFont(new Font("Dialog", Font.PLAIN, 12));
		desc.setBounds(l.getWidth()/8, 0, (l.getWidth()/8)*7, l.getHeight()-34);	
		l.add(desc);


		JButton add = new JButton();
		add.setBounds(desc.getX()+(desc.getWidth()/2) - 35,  l.getHeight()-25,  70,  20);
		add.setText("Add");
		l.add(add);
		
		SpinnerModel sm = new SpinnerNumberModel(1, 1, 10, 1);
	    JSpinner spinner = new JSpinner(sm);
	    spinner.setBounds(add.getX() - 60, add.getY(), 50, 20);
	    l.add(spinner);
	    
	    JLabel levelLabel = new JLabel("Level: ");
		levelLabel.setFont(new Font("Dialog", Font.BOLD, 14));
	    levelLabel.setBounds(spinner.getX() - 50, spinner.getY(), 50, 20);
	    l.add(levelLabel);
		
		JLabel warning = new JLabel();
		warning.setBounds(add.getX()-50, add.getY()-20, 170, 20);
		warning.setForeground(Color.red);
		l.add(warning);
		
		ListSelectionListener listSelectionListener = new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent arg0) 
			{
		       	for(Power p : powers)
		       	{
		       		if(p.nameP() == (String)list.getSelectedValue())
		       		disp = p;	
		       	}
		        desc.setText(disp.description());
		        warning.setText("");
			}	
		};
		list.addListSelectionListener(listSelectionListener);
		
		add.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(disp == null)
				{
					warning.setText("Please select a Power");
				}
				else
				{
					CharacterSheetPower csp = new CharacterSheetPower(MainWindow.nextCSPId, cs.getId(), Integer.parseInt(spinner.getValue().toString()), disp);
					pwrsList.add(csp);
					picker.dispose();
					setNewCharacter(x, y, cs, pwrsList);
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
			//	disp = null;
			//	picker.dispose();
			}
		});
	}
	
	public List<CharacterSheetPower> savePwrs()
	{
		return pwrsList;
	}
}
