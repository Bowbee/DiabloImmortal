package com.massey.id16107190.pong.Entities;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Entity_1_Fireball extends Entity {
	
	public double posX = 0;
	public double posY = 0;
	public float velocity = 0;
	public boolean direction = true;
	public float slope = 0f;
	public float radius = 20;
	public Color color = new Color(244,167,66);
	public String type = "spell";
	public int health = -1;
	private int damage = 20;
	private String imgString = "Assets/spells/fireball.png";
	private File f = new File(imgString);
	private BufferedImage tempImg = null;
	private BufferedImage rotatedimage = null;
	private boolean rotated = false;
	private double angle;
	private boolean casting = false;
	private String[] soundClip = {
			"Assets/spells/fireball1.wav",
			"Assets/spells/fireball2.wav",
			"Assets/spells/fireball3.wav",
			"Assets/spells/fireball4.wav",
			"Assets/spells/fireball5.wav",
			};
	private String[] soundClipExplode = {
			"Assets/spells/explode1.wav",
			"Assets/spells/explode2.wav",
			"Assets/spells/explode3.wav",
			};
	private int volume = 0;
	private EntitySpawner es = EntitySpawner.getInstance();

	
	Entity_1_Fireball() {  
		try {
			tempImg = ImageIO.read(f);
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
		return tempImg;
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
	}

	@Override
	public double getRadius() {
		
		return radius;
	}

	@Override
	public Entity hit() {
		es.playSound(soundClipExplode, this.volume);
		return null;	
	}
	@Override
	public Entity hit(Entity other) {
		es.playSound(soundClipExplode, this.volume);
		if(other.getType() == "mob" || other.getType() == "mage"){
			other.setHealth(other.getHealth() - this.damage);			
		}
		return null;	
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
		this.tempImg = img;
		
	}

	@Override
	public BufferedImage getRotatedImage() {
		return this.rotatedimage;
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
