package business;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JButton;
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
	private BufferedImage origImg;
	private ImageIcon icon;
	private JLabel imgLabel;
	
	private int id = 0;
	private String name = "(name)";
	private String path = "blank.png";
	
	private boolean imgChange = false;
	private boolean newPic = false;
	
	public Spell()
	{
		super();
		setSize(364, 184);
		setBackground(Color.LIGHT_GRAY);
		setBorder(new LineBorder(Color.black));
		setLayout(null);
		
		icon = new ImageIcon();
		
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
				MainWindow.draggedSpell = Spell.this;
			}
		};

		imgLabel.addMouseListener(listener);

		imgLabel.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				if(imgChange)
				{
					if(MainWindow.draggedSpell != null)
					{
						Spell flip = new Spell();
						flip.set(MainWindow.draggedSpell.getId());
						MainWindow.draggedSpell.set(0);
						Spell.this.set(flip.getId());
						MainWindow.draggedSpell = null;
					}

					try 
					{
						icon = (ImageIcon)imgLabel.getIcon();
						img = Scalr.resize((BufferedImage)icon.getImage(), 360, 180);
						origImg = (BufferedImage)icon.getImage();
						icon.setImage(img);
						newPic = true;
					}
					catch (IllegalArgumentException e)
					{
						
					}
				}
			}
		});	
	}
	
	public void set(int id)
	{
		imgChange = false;
		for(Spell s : MainWindow.spells)
		{
			if(id == 0)
			{
				setId(0);
				setName("null");
				setPath("null.png");
				icon = new ImageIcon();
				imgLabel.setIcon(icon);
				break;
			}
			if(s.getId() == id)
			{
				setId(s.getId());
				setName(s.getName());
				setPath(s.getPath());
				
				img = null;
				try 
				{
				    img = ImageIO.read(new File("images/spells/"+path));
				    origImg = img;
				    img = Scalr.resize(img, 360, 180);
				    icon.setImage(img);
				} catch (IOException e) {
				}
				break;
			}
		}
		repaint();
		imgChange = true;
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
	public boolean newPic()
	{
		return newPic;
	}
	public BufferedImage retrievePic()
	{
		newPic = false;
		return origImg;
	}
}
