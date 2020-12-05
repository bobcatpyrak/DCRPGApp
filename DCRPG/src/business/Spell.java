package business;

import java.awt.Color;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.swing.border.LineBorder;

import gui.EquipmentTab;
import gui.MainWindow;
import library.ImageSelection;
import library.Scalr;

public class Spell extends JPanel 
{
	private BufferedImage img;
	private ImageIcon icon;
	private JLabel imgLabel;
	
	private int id = 0;
	private String name = "(name)";
	private String path = "blank.png";
	
	private boolean imgChange = false;
	
	public Spell()
	{
		super();
		setSize(364, 184);
		setBackground(Color.LIGHT_GRAY);
		setBorder(new LineBorder(Color.black));
		setLayout(null);
		
		icon = new ImageIcon();
	/*	img = null;
		try 
		{
		    img = ImageIO.read(new File("images/items/blank.png"));
		    icon.setImage(img);
		} catch (IOException e) {
		}*/
		
		imgLabel = new JLabel();	
		imgLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imgLabel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		imgLabel.setBackground(Color.WHITE);
		imgLabel.setIcon(icon);
		imgLabel.setBounds(2, 2, 360, 180);
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
					//newPic = true;
				/*	if(MainWindow.newItem != null)
					{
						int flip = MainWindow.newItem.getId();
						if(!Item.this.getName().equals("(name)"))
							MainWindow.newItem.set(MainWindow.items, Item.this.getId());
						Item.this.set(MainWindow.items, flip);
						Item.this.setPath(path);
						MainWindow.newItem = null;
						newPic = false;
					}*/
					icon = (ImageIcon)imgLabel.getIcon();
				    img = Scalr.resize((BufferedImage)icon.getImage(), 360, 180);
					//origImg = (BufferedImage)icon.getImage();
					icon.setImage(img);
					
				}
			}
		});	
	}
	
	public void set(int id)
	{
		for(Spell s : MainWindow.spells)
		{
			if(s.getId() == id)
			{
				setId(s.getId());
				setName(s.getName());
				setPath(s.getPath());
				
				img = null;
				try 
				{
				    img = ImageIO.read(new File("images/spells/"+path));
				    img = Scalr.resize(img, 360, 180);
				    icon.setImage(img);
				} catch (IOException e) {
				}
				break;
			}
		}
		repaint();
	}
	
	public int getId()
	{
		return this.id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getName()
	{
		return this.name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getPath()
	{
		return this.path;
	}
	public void setPath(String path)
	{
		this.path = path;
	}
}
