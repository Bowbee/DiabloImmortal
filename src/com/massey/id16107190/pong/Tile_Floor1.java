package com.massey.id16107190.pong;

import java.awt.Color;
import java.awt.Image;

public class Tile_Floor1 extends Grid {
	
	private int posX;
	private int posY;
	private Colors c = new Colors();
	private Color color = c.lightGray;
	private boolean collision = true;
	
	@Override
	public void trigger() {
		System.out.println("Triggered Floor1 grid piece"+posX);
		
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
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
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
