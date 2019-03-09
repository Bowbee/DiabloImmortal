package com.massey.id16107190.pong.Entities;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Entity_3_Mage extends Entity {
	
	public double posX = 0;
	public double posY = 0;
	public float velocity = 0;
	public boolean direction = true;
	public float slope = 0f;
	public float radius = 50;
	public Color color = Color.black;
	public String type = "mage";
	public int health = 100;
	public int damage = 20;
	
	private EntitySpawner es = EntitySpawner.getInstance();
	private int lastCast = 0;
	private int counter;
	private boolean rotated = false;
	private String imgString = "Assets/characters/mage.png";
	private File f = new File(imgString);
	private BufferedImage tempImg = null;
	
	private String imgStringC = "Assets/characters/mage_cast.png";
	private File fC = new File(imgStringC);
	private BufferedImage tempImgC = null;
	
	private double angle = 0;
	private boolean casting = false;
	private BufferedImage rotatedimage = null;
	private BufferedImage rotatedimageCast = null;
	private String[] soundClip = {};
	
	private int volume = 0;
	
	public Entity_3_Mage() {  
		try {
			tempImg = ImageIO.read(f);
			tempImgC = ImageIO.read(fC);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@Override
	public String getType(){
		return type;
	}
	@Override
	public void trigger() {
		
	}

	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public void setHealth(int hp) {
		this.health = hp;
	}

	@Override
	public double getPosX() {
		return posX;
	}

	@Override
	public double getPosY() {
		return posY;
	}

	@Override
	public BufferedImage getImage() {
		if(this.casting){
			return this.tempImgC;
		}
		else{
			return this.tempImg;
		}
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public boolean hasCollision() {
		return false;
	}

	@Override
	public void setPosX(double x) {
		posX = x;
		
	}

	@Override
	public void setPosY(double y) {
		posY = y;
		
	}

	@Override
	public void cast(float slope, boolean isLeft) {
		counter += 1;
		if(lastCast + 60 < counter){
			int px = (int) (this.posX);
			int py = (int) (this.posY);
			es.spawnEntity(4, px, py, -5, this.angle, this.volume);
			lastCast = counter;
		}
		
	}

	@Override
	public double getRadius() {
		return radius;
	}
	@Override
	public Entity hit() {
		return this;
	}
	@Override
	public void setAngle(double angle) {
		this.angle = angle;
		
	}
	@Override
	public void setVelocity(float vel) {
		this.velocity = vel;
	
	}
	@Override
	public double getAngle() {
		return this.angle;
	}
	@Override
	public double getVelocity() {
		return this.velocity;
	}
	@Override
	public Entity hit(Entity other) {
		return this;
	}
	@Override
	public int getDamage() {
		return this.damage;
	}
	
	@Override
	public boolean getRotated() {
		return rotated;
	}

	@Override
	public void setRotated(boolean boo) {
		rotated = boo;
		
	}
	@Override
	public void setImage(BufferedImage img) {
		if(this.casting){
			this.tempImgC = img;
		}
		else{
			this.tempImg = img;
		}
		
		
	}
	@Override
	public BufferedImage getRotatedImage() {
		if(this.casting){
			return this.rotatedimageCast;
		}
		else{
			return this.rotatedimage;
		}

	}

	@Override
	public void setRotatedImage(BufferedImage img) {
		this.rotatedimage = img;
	}
	@Override
	public boolean getCasting() {
		return this.casting;
	}

	@Override
	public void setCasting(boolean boo) {
		this.casting = boo;
		
	}
	
	@Override
	public String[] getSound() {
		return this.soundClip;
	}

	@Override
	public void setSound(String[] sound) {
		this.soundClip = sound;
		
	}
	@Override
	public int getVolume() {
		return this.volume;
		
	}

	@Override
	public void setVolume(int vol) {
		this.volume = vol;
	}



	
	

}
