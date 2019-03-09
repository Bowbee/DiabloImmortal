package com.massey.id16107190.pong.Tiles;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.massey.id16107190.pong.Colors;
import com.massey.id16107190.pong.Grid;
import com.massey.id16107190.pong.PlayerController;

public class Tile_Exit1_4 extends Grid {
	
	private int posX;
	private int posY;
	private Colors c = new Colors();
	private Color color = c.lightGray;
	private boolean collision = false;
	private String imgString = "Assets/tileset1/exit_1_4.png";
	private File f = new File(imgString);
	private BufferedImage tempImg = null;
	private PlayerController pc = PlayerController.getInstance();

	private String item = "key1";

	
	Tile_Exit1_4() {  
		try {
			tempImg = ImageIO.read(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@Override
	public void trigger() {
		if(pc.getPlayer().getInventory().contains(item)){
			System.out.println("Level ended, next level");
			pc.getGame().changeLevel(1);
			pc.getPlayer().setInventory(new ArrayList<>());
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
		return tempImg;
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
