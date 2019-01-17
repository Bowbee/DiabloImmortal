package com.massey.id16107190.pong;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Menus extends GameEngine {
	
	private static ClientSettings cs = ClientSettings.getInstance();
	private Colors c = new Colors();

	private boolean gameStarted = false;
	private boolean titleSet = false;
	
	private long mouseTime;
	private boolean serverPinging;
	
	public void init() {
		
		// Initialise Window Size
		setWindowSize(cs.getResX(), cs.getResY());
		
	}

	public static void start() {
		
		Game pong = new Game();
		
		GameEngine.createGame(pong, cs.getFPS());
	}

	@Override
	public void update(double dt) {
		if(titleSet == false){
			mFrame.setTitle("Pong Options");
			titleSet = true;
		}
		if(cs.mouseCooldown == true){
			if(time - mouseTime >= 200){
				cs.mouseCooldown = false;
			}
		}
	}
	
	public void paintComponent() {
		// Clear the background to black
		changeBackgroundColor(cs.menuBackColor);
		clearBackground(cs.getResX(), cs.getResY());

		// Draw Board on the screen
		changeColor(white);

		changeColor(white);

	}
	private void mouseCD() {
		mouseTime = time;
		cs.mouseCooldown = true;
	}
	
	public void keyPressed(KeyEvent event) {
		if(this.gameStarted == false){
			if(event.getKeyChar() != KeyEvent.VK_BACK_SPACE){
				if(event.getKeyChar() != KeyEvent.VK_ENTER){
					if(cs.keysPressed.length() <= 16){
						cs.keysPressed += String.valueOf(event.getKeyChar());
					}
				}
			}
			else{
				if(cs.keysPressed.length() > 0){
					cs.keysPressed = cs.keysPressed.substring(0, cs.keysPressed.length() - 1);
				}
			}
		}
		else{
			cs.keysPressed = "";
		}
	}
	
	public void mouseClicked(MouseEvent event) {
		if(cs.mouseCooldown == false){
			if(event.getButton() == 1){ //if left click
				
			}
		}		
	}
}
