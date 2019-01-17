package com.massey.id16107190.pong;

import java.awt.Color;
import java.awt.event.KeyEvent;

public final class ClientSettings{
	 
    private static ClientSettings INSTANCE;
	private int resX = 600;
	private int resY = 600;
	private int fps = 60;
	
	private int pu = KeyEvent.VK_W;
	private int pd = KeyEvent.VK_S;
	private int pl = KeyEvent.VK_A;
	private int pr = KeyEvent.VK_D;
	
	private double waitTime = 1500; //ms, sec/1000
	
	public Color menuBackColor = new Color(240,240,240);
	private boolean muted = false;
	public boolean mouseCooldown = false;
	public String keysPressed = "";
	
     
    private ClientSettings() {  
    }
     
    public static ClientSettings getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ClientSettings();
        }
         
        return INSTANCE;
    }
 
	public int getResX() {
		return resX;
	}
	public void setResX(int resX) {
		this.resX = resX;
	}
	public int getResY() {
		return resY;
	}
	public void setResY(int resY) {
		this.resY = resY;
	}
	public int getFPS(){
		return fps;
	}
	public boolean getMouseCooldown(){
		return mouseCooldown;
	}
	public void setMouseCooldown(boolean b){
		mouseCooldown = b;
	}
	
	
	public int getUpBind(){
		return pu;	
	}
	public int getDownBind(){
		return pd;	
	}
	public int getLeftBind(){
		return pl;	
	}
	public int getRightBind(){
		return pr;	
	}

	
	public void setPu(int p1u) {
		this.pu = p1u;
	}

	public void setPd(int p1d) {
		this.pd = p1d;
	}

	public void setPl(int p2u) {
		this.pl = p2u;
	}

	public void setPr(int p2d) {
		this.pr = p2d;
	}

	public double getWaitTime() {
		return waitTime ;
	}

	public boolean getMuted() {
		return muted ;
	}

	public void setMuted(boolean b) {
		this.muted = b;
		
	}
}
