package com.supermarket.JFrame.Index;

import javax.swing.*;
import java.awt.*;

public class IndexBgImage extends JPanel{
	private ImageIcon icon;  
	private Image img;
	
	public IndexBgImage(){
		icon=new ImageIcon(IndexFrame.class.getResource("/images/market.jpg" ));
		icon.setImage(icon.getImage().getScaledInstance(745,446,Image.SCALE_AREA_AVERAGING));
		img=icon.getImage();
	}   
	public void paintComponent(Graphics g)  
	{   
		super.paintComponent(g);  
		g.drawImage(img,0,0,null );
	}   
}
