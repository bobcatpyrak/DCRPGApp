package business;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Label;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.swing.border.LineBorder;

import gui.EquipmentTab;
import gui.MainWindow;
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
	private BufferedImage origImg;

	private int id = 0;
	private int width = 206;
	private int height = 266;
	private int firstLoadCount = 0;
	private String descStr = "(description)";
	private String path = "blank";
	private String name = "(name)";
	
	private boolean imgChange = false;
	private boolean newPic = false;
	private boolean big = false;

	public Item(int id)
	{	
		super();	
		this.id = id;
		setSize(210, 336);
		setBackground(Color.LIGHT_GRAY);
		setBorder(new LineBorder(Color.black, 2));
		setLayout(null);
		
		slot = new JLabel();
		slot.setBackground(Color.WHITE);
		slot.setText("test");
		slot.setFont(new Font("Verdana", Font.PLAIN, 11));
		slot.setBounds(20, 2, 170, 20);
		slot.setHorizontalTextPosition(SwingConstants.CENTER);
		slot.setHorizontalAlignment(SwingConstants.CENTER);
		add(slot);
		
		JButton xBtn = new JButton();
		xBtn.setBounds(getWidth()-18, 2, 16, 16);
		xBtn.setFont(new Font("Dialog", Font.BOLD, 12));
		xBtn.setText("x");
		xBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		xBtn.setMargin(new Insets(0,0,0,0));
		add(xBtn);
		xBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				clearItem();
			}
		});
		
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
				MainWindow.newItem = Item.this;
				EquipmentTab.search.set(MainWindow.items, Item.this.getId());
				handler.exportAsDrag(comp, me, TransferHandler.COPY);   
				imgChange = true;
			}
		};

		imgLabel.addMouseListener(listener);

		imgLabel.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				if(imgChange)
				{
					newPic = true;
					icon = (ImageIcon)imgLabel.getIcon();
					if(MainWindow.newItem != null)
					{
						origImg = MainWindow.newItem.origImg;
						int flip = MainWindow.newItem.getId();
						MainWindow.newItem.set(MainWindow.items, Item.this.getId());
						Item.this.set(MainWindow.items, flip);
						Item.this.setPath(path);
						MainWindow.newItem = null;
						newPic = false;
					}
					else 
					{
						origImg = (BufferedImage)icon.getImage();
					}
					img = Scalr.resize((BufferedImage)icon.getImage(), 206, 266);
					icon.setImage(img);
					
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
	
	/**
	 * @wbp.parser.constructor
	 */
	public Item(String slotStr)
	{
		super();
		setSize(210, 336);
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		setBorder(new LineBorder(Color.black, 2));

		
		slot = new JLabel(slotStr);
		slot.setBackground(Color.WHITE);
		slot.setFont(new Font("Verdana", Font.BOLD, 11));
		slot.setBounds(2, 2, 206, 20);
		slot.setHorizontalAlignment(SwingConstants.CENTER);
		add(slot);
		
		JButton xBtn = new JButton();
		xBtn.setBounds(getWidth()-18, 2, 16, 16);
		xBtn.setFont(new Font("Dialog", Font.BOLD, 12));
		xBtn.setText("x");
		xBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		xBtn.setMargin(new Insets(0,0,0,0));
		add(xBtn);
		xBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				clearItem();
			}
		});
		
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
				MainWindow.newItem = Item.this;
				EquipmentTab.search.set(MainWindow.items, Item.this.getId());
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
					if (firstLoadCount > 1) {
						newPic = true;
					}
					else {
						firstLoadCount++;
					}
					
					icon = (ImageIcon)imgLabel.getIcon();
					if(MainWindow.newItem != null)
					{
						origImg = MainWindow.newItem.origImg;
						int flip = MainWindow.newItem.getId();
						MainWindow.newItem.set(MainWindow.items, Item.this.getId());
						Item.this.set(MainWindow.items, flip);
						MainWindow.newItem = null;
						newPic = false;
					}
					else
					{
						origImg = (BufferedImage)icon.getImage();
					}
					img = Scalr.resize((BufferedImage)icon.getImage(), 206, 266);
					icon.setImage(img);
					
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
	
	public Item(String slotStr, int width, int height)
	{
		super();
		this.big = big;
		this.width = width;
		this.height = height;
		setSize(width+4, height+70);
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		setBorder(new LineBorder(Color.black, 2));

		
		slot = new JLabel(slotStr);
		slot.setBackground(Color.WHITE);
		slot.setFont(new Font("Verdana", Font.BOLD, 11));
		slot.setBounds(2, 2, width, 20);
		slot.setHorizontalAlignment(SwingConstants.CENTER);
		add(slot);
		
		JButton xBtn = new JButton();
		xBtn.setBounds(getWidth()-18, 2, 16, 16);
		xBtn.setFont(new Font("Dialog", Font.BOLD, 12));
		xBtn.setText("x");
		xBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		xBtn.setMargin(new Insets(0,0,0,0));
		add(xBtn);
		xBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				clearItem();
			}
		});
		
		nameField = new JFormattedTextField();
		nameField.setBounds(2, 24, width, 20);
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
		    img = Scalr.resize(img, width, height);
		    icon.setImage(img);
		} catch (IOException e) {
			
		}
		
		imgLabel = new JLabel();	
		imgLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imgLabel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		imgLabel.setBackground(Color.WHITE);
		imgLabel.setIcon(icon);
		imgLabel.setBounds(2, 46, width, height);
		add(imgLabel);

		imgLabel.setTransferHandler(new ImageSelection());	    

		MouseListener listener = new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				JComponent comp = (JComponent) me.getSource();
				MainWindow.newItem = Item.this;
				EquipmentTab.search.set(MainWindow.items, Item.this.getId());
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
					newPic = true;
					icon = (ImageIcon)imgLabel.getIcon();
					if(MainWindow.newItem != null)
					{
						origImg = MainWindow.newItem.origImg;
						int flip = MainWindow.newItem.getId();
						MainWindow.newItem.set(MainWindow.items, Item.this.getId());
						Item.this.set(MainWindow.items, flip);
						MainWindow.newItem = null;
						newPic = false;
					}
					else
					{
						origImg = (BufferedImage)icon.getImage();
					}
					//origImg = (BufferedImage)icon.getImage();
					img = Scalr.resize((BufferedImage)icon.getImage(), width, height);
					icon.setImage(img);
					
				}
			}
		});	
		
		description = new JFormattedTextField();
		description.setBounds(2, 46+height+2, width, 20);
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
	
	public void setFirstLoad() {
		firstLoadCount = 0;
	}
	
	public void set(List<Item> items, int itemId)
	{

		Item i = null;
		for(Item j : items)
		{
			if(j.getId()==itemId)
				i = j;
		}
		if(i != null)
		{
			this.id = i.getId();
			this.descStr = i.getDescStr();
			description.setText(descStr);
			this.name = i.getName();
			nameField.setText(name);
			this.path = i.getPath();
			
			img = null;
			origImg = null;
			newPic = false;
			try 
			{
				imgChange = false;
			    img = ImageIO.read(new File("images/items/"+path));
			    origImg = ImageIO.read(new File("images/items/"+path));
			    img = Scalr.resize(img, width, height);
			    icon.setImage(img);
			    imgChange = true;
			} catch (IOException e) {	
				imgChange = true;
			}
			repaint();
		}
		else
		{
			this.id = 0;
			this.name = "(name)";
			this.descStr = "(description)";
			this.path = "blank.png";
			nameField.setText(name);
			description.setText(descStr);
			
			img = null;
			origImg = null;
			newPic = false;
			try 
			{
				imgChange = false;
			    img = ImageIO.read(new File("images/items/"+path));
			    img = Scalr.resize(img, width, height);
			    icon.setImage(img);
			    imgChange = true;
			} catch (IOException e) {	
				imgChange = true;
			}
			repaint();
		}
	}
	
	public boolean newPic()
	{
		return newPic;
	}
	
	public BufferedImage retrievePic()
	{
		newPic = false;
		return origImg;
	}
	
	public void clearItem()
	{
		this.id = 0;
		this.name = "(name)";
		this.descStr = "(description)";
		this.path = "blank.png";
		nameField.setText(name);
		description.setText(descStr);
		
		img = null;
		origImg = null;
		newPic = false;
		try 
		{
			imgChange = false;
		    img = ImageIO.read(new File("images/items/"+path));
		    img = Scalr.resize(img, width, height);
		    icon.setImage(img);
		    imgChange = true;
		} catch (IOException e) {	
			imgChange = true;
		}
		repaint();
	}
	
	public void setDisabled()
	{
		description.setEnabled(false);
		nameField.setEnabled(false);
	}
	
}
