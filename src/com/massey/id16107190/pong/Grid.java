package com.massey.id16107190.pong;

import java.awt.Color;
import java.awt.Image;

public abstract class Grid {
	
	
	public abstract void trigger();
	public abstract int getPosX();
	public abstract int getPosY();
	public abstract Image getImage();
	public abstract Color getColor();
	public abstract boolean hasCollision();
	public abstract void setPosX(int x);
	public abstract void setPosY(int y);

}
