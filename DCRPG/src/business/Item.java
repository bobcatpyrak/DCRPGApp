package business;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.swing.border.LineBorder;

import library.ImageSelection;
import library.Scalr;

public class Item extends JPanel
{
	private BufferedImage img;
	private ImageIcon icon;
	private JLabel slot;
	private JLabel imgLabel;
	private JFormattedTextField description;
	private JFormattedTextField nameField;

	private int id;
	private String descStr = "(description)";
	private String path = "blank";
	private String name = "(name)";
	
	private boolean imgChange = false;
	private boolean newPic = false;


	public Item(int id)
	{	
		super();	
		this.id = id;
		setSize(210, 336);
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		slot = new JLabel();
		slot.setBackground(Color.WHITE);
		slot.setText("test");
		slot.setFont(new Font("Verdana", Font.PLAIN, 11));
		slot.setBounds(2, 2, 206, 20);
		slot.setHorizontalTextPosition(SwingConstants.CENTER);
		slot.setHorizontalAlignment(SwingConstants.CENTER);
		add(slot);
		
		nameField = new JFormattedTextField();
		nameField.setBounds(2, 24, 206, 20);
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setText("(name)");
		add(nameField);
		nameField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				setName(nameField.getText());
			}
		});
		
		icon = new ImageIcon();
		img = null;
		try 
		{
		    img = ImageIO.read(new File("images/items/blank.png"));
		    img = Scalr.resize(img, 206, 266);
		    icon.setImage(img);
		} catch (IOException e) {
			
		}
							
		imgLabel = new JLabel();	
		imgLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imgLabel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		imgLabel.setBackground(Color.WHITE);
		imgLabel.setIcon(icon);
		imgLabel.setBounds(2, 46, 206, 266);
		add(imgLabel);

		imgLabel.setTransferHandler(new ImageSelection());	    

		MouseListener listener = new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				JComponent comp = (JComponent) me.getSource();
				TransferHandler handler = comp.getTransferHandler();
				handler.exportAsDrag(comp, me, TransferHandler.COPY);   
				imgChange = true;
			}
		};

		imgLabel.addMouseListener(listener);

		imgLabel.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				if(imgChange)
				{
					icon = (ImageIcon)imgLabel.getIcon();
					img = Scalr.resize((BufferedImage)icon.getImage(), 206, 266);
					icon.setImage(img);
					newPic = true;
				}
			}
		});	
		
		description = new JFormattedTextField();
		description.setBounds(2, 314, 206, 20);
		description.setHorizontalAlignment(SwingConstants.CENTER);
		description.setText("(description)");
		add(description);
		description.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				setDescStr(description.getText());
			}
		});	
	}
	
	public Item(String slotStr)
	{
		super();
		setSize(210, 336);
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		slot = new JLabel(slotStr);
		slot.setBackground(Color.WHITE);
		slot.setFont(new Font("Verdana", Font.BOLD, 11));
		slot.setBounds(2, 2, 206, 20);
		slot.setHorizontalAlignment(SwingConstants.CENTER);
		add(slot);
		
		nameField = new JFormattedTextField();
		nameField.setBounds(2, 24, 206, 20);
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setText("(name)");
		add(nameField);
		nameField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				setName(nameField.getText());
			}
		});
		
		icon = new ImageIcon();
		img = null;
		try 
		{
		    img = ImageIO.read(new File("images/items/blank.png"));
		    img = Scalr.resize(img, 206, 266);
		    icon.setImage(img);
		} catch (IOException e) {
			
		}
		
		imgLabel = new JLabel();	
		imgLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imgLabel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		imgLabel.setBackground(Color.WHITE);
		imgLabel.setIcon(icon);
		imgLabel.setBounds(2, 46, 206, 266);
		add(imgLabel);

		imgLabel.setTransferHandler(new ImageSelection());	    

		MouseListener listener = new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				JComponent comp = (JComponent) me.getSource();
				TransferHandler handler = comp.getTransferHandler();
				handler.exportAsDrag(comp, me, TransferHandler.COPY);     
				imgChange = true;
			}
		};

		imgLabel.addMouseListener(listener);

		imgLabel.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				if(imgChange)
				{
					icon = (ImageIcon)imgLabel.getIcon();
					img = Scalr.resize((BufferedImage)icon.getImage(), 206, 266);
					icon.setImage(img);
					newPic = true;
				}
			}
		});	
		
		description = new JFormattedTextField();
		description.setBounds(2, 314, 206, 20);
		description.setHorizontalAlignment(SwingConstants.CENTER);
		description.setText("(description)");
		add(description);
		description.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				setDescStr(description.getText());
			}
		});
	}
	
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setDescStr(String descStr)
	{
		this.descStr = descStr;
	}
	public String getDescStr()
	{
		return descStr;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public void set(Item i)
	{
		this.id = i.getId();
		this.descStr = i.getDescStr();
		description.setText(descStr);
		this.name = i.getName();
		nameField.setText(name);
		this.path = i.getPath();
		
		img = null;
		try 
		{
			imgChange = false;
		    img = ImageIO.read(new File("images/items/"+path));
		    img = Scalr.resize(img, 206, 266);
		    icon.setImage(img);
		    imgChange = true;
		} catch (IOException e) {	
		}
		repaint();
	}
	
	public boolean newPic()
	{
		return newPic;
	}
	
	public BufferedImage retrievePic()
	{
		icon = (ImageIcon)imgLabel.getIcon();
	    img = Scalr.resize((BufferedImage)icon.getImage(), 206, 266);
	    icon.setImage(img);
	    newPic = false;
	    
	    return img;
	}
	
}
