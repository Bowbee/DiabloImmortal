package com.massey.id16107190.pong.Tiles;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.massey.id16107190.pong.Colors;
import com.massey.id16107190.pong.Grid;
import com.massey.id16107190.pong.PlayerController;

public class Tile_Trigger1 extends Grid {
	
	private int posX;
	private int posY;
	private Colors c = new Colors();
	private Color color = c.lightGray;
	private boolean collision = false;
	private String imgString = "Assets/tileset1/key_1.png";
	private String imgString2 = "Assets/tileset1/ground_1.png";
	private File f = new File(imgString);
	private File f2 = new File(imgString2);
	private BufferedImage tempImg = null;
	private BufferedImage tempImg2 = null;
	private PlayerController pc = PlayerController.getInstance();
	
	private String item = "key1";
	private boolean collected = false;
	

	
	Tile_Trigger1() {  
		try {
			tempImg = ImageIO.read(f);
			tempImg2 = ImageIO.read(f2);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@Override
	public void trigger() {
		if(!collected){
			System.out.println("Key Collected!");
			pc.getPlayer().addToInventory(item);
			this.collected = true;
		}
	
	}
	@Override
	public void clicked() {
		System.out.println("Clicked Floor1 grid piece"+posX);
		
	}

	@Override
	public int getPosX() {
		return posX;
	}

	@Override
	public int getPosY() {
		return posY;
	}

	@Override
	public BufferedImage getImage() {
		if(collected){
			return tempImg2;
		}
		else{
			return tempImg;
		}
		
	}
	
	@Override
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public boolean hasCollision() {
		return this.collision ;
	}

	@Override
	public void setPosX(int x) {
		this.posX = x;
		
	}

	@Override
	public void setPosY(int y) {
		this.posY = y;
		
	}

	

}
