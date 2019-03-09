/*
#################################################

  Thomas Debenham - id 16107190 - Assessment 2

#################################################
*/

package com.massey.id16107190.pong.Entities;

import java.awt.Color;
import java.awt.image.BufferedImage;

public abstract class Entity {

	public abstract void trigger();
	public abstract int getHealth();
	public abstract void setHealth(int hp);
	public abstract double getPosX();
	public abstract double getPosY();
	public float velocity;
	public boolean direction;
	public abstract boolean getRotated();
	public abstract void setRotated(boolean boo);
	public abstract Entity hit();
	public abstract Entity hit(Entity other);
	public abstract String getType();
	public abstract BufferedImage getImage();
	public abstract BufferedImage getRotatedImage();
	public abstract Color getColor();
	public abstract boolean hasCollision();
	public abstract void setPosX(double x);
	public abstract void setPosY(double y);
	public abstract void cast(float slope, boolean isLeft);
	public abstract double getRadius();
	public abstract void setAngle(double angle);
	public abstract void setVelocity(float vel);
	public abstract double getAngle();
	public abstract double getVelocity();
	public abstract int getDamage();
	public abstract void setImage(BufferedImage img);
	public abstract void setRotatedImage(BufferedImage img);
	public abstract boolean getCasting();
	public abstract void setCasting(boolean boo);
	public abstract String[] getSound();
	public abstract void setSound(String[] sound);
	public abstract int getVolume();
	public abstract void setVolume(int vol);
}
