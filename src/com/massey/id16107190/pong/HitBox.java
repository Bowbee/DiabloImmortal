package com.massey.id16107190.pong;

public class HitBox {
	
	private int radius = 0;
	private int width = 0;
	private int height = 0;

	public HitBox(int i) {
		this.setRadius(i);
	}
	public HitBox(int w, int h) {
		this.setWidth(w);
		this.setHeight(h);
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	

}
