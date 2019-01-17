package com.massey.id16107190.pong;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game extends GameEngine {
	// Settings data
	private ClientSettings cs = ClientSettings.getInstance();
	private boolean paused = true;
	private boolean gameExited = false;
	private boolean titleSet = false;
	private boolean newGame = true;
	// GameObjects
	// Player controls etc
	private PlayerController pc = new PlayerController();
	public void init() {
		// Initialize Window Size
		setWindowSize(cs.getResX(), cs.getResY());	
	}

	public static void start() {
		
	}
	
	public static void quit() {
		Menus m = new Menus();
		m.init();
		GameEngine.createGame(m, 144);
	}
	
	public void reset(){
		// Paddle reset
		pc.resetPC();
		// Round reset + timer
		paused = true;
	}

	@Override
	public void update(double dt) {
		if(titleSet == false){
			titleSet = true;
			mFrame.setTitle("Pong!");		
		}
		pc.Update();
	}

	public void paintComponent() {
		// Clear the background to black
		changeBackgroundColor(black);
		clearBackground(cs.getResX(), cs.getResY());
		
		changeColor(red);
		Integer[] coords = pc.getPlayerCoords();
		drawSolidRectangle(coords[0], coords[1], 50, 50);
	}
	
	public void keyPressed(KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.VK_ESCAPE){ // Exit the game
			if(gameExited == false){
				gameExited = true;
				mFrame.dispose();
				Game.quit();
			}
		}
		if(paused == true){
			if(event.getKeyCode() == KeyEvent.VK_ENTER){ // Start the game
				paused = false;
				newGame = false;
			}
		}
		pc.evaluateKeyPress(event);
		

	}
	
	public void keyReleased(KeyEvent event){
		pc.evaluateKeyRelease(event);

	}
	
	//System.out.println(event.getX() + " " + event.getY());
	public void mouseClicked(MouseEvent event) {
		if(event.getButton() == 1){ //if left click
		}		
	}

}
