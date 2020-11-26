package business;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import library.Scalr;

public class Item extends ImageIcon 
{
	private BufferedImage img;
	String path;

	public Item()
	{
		super();
		img = null;
		try 
		{
		    img = ImageIO.read(new File("items/"+path));
		    img = Scalr.resize(img, 300, 350);
		    setImage(img);
		} catch (IOException e) {		
		}
		
	}
}
