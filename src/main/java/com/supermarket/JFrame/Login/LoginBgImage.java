package com.supermarket.JFrame.Login;

import com.supermarket.JFrame.Index.IndexFrame;

import javax.swing.*;
import java.awt.*;

public class LoginBgImage extends JPanel{
	private ImageIcon icon;
	private Image img;

	public LoginBgImage(){
		icon=new ImageIcon(IndexFrame.class.getResource("/images/login.jpg" ));
		icon.setImage(icon.getImage().getScaledInstance(450,290,Image.SCALE_DEFAULT));
		img=icon.getImage();
	}   
	public void paintComponent(Graphics g)  
	{   
		super.paintComponent(g);  
		g.drawImage(img,0,0,null );
	}   
}
