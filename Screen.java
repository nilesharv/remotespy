package com.Server;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Screen extends JFrame {
BufferedImage img;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Screen()
	{try
	{
		Robot r=new Robot();
		img = r.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));//ImageIO.read(new 
		this.setSize(600,600);
	}catch(Exception ex)
	{
		
	}
	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	this.setResizable(true);
	}
	public void paint(Graphics g)
	{
		BufferedImage imgtemp=new BufferedImage(600,600,img.getType());
		Graphics2D g1=imgtemp.createGraphics();
		g1.drawImage(img, 0, 0,600,600, null);
		g1.dispose();
		g.clearRect(0, 0,600,600);
	g.drawImage(imgtemp, 0,0,600,600,0,0,600,600,Color.WHITE,this);
		
	}
	
	
	public static void main(String[] args) throws Exception
	{
		Screen sc=new Screen();
		sc.setAlwaysOnTop(true);
		sc.setVisible(true);
		
		ServerSocket ss=new ServerSocket(6666);
		Socket s=ss.accept();//establishes connection 

		ObjectInputStream dis=new ObjectInputStream(s.getInputStream());
		int i=100;
		while(i-->0)
		{
			
		byte[] buffer=(byte[])dis.readObject();
		InputStream in =new ByteArrayInputStream(buffer);
		sc.img=ImageIO.read(in);
		sc.repaint();
		Thread.sleep(33);
		
		}

		ss.close();

	}

}
