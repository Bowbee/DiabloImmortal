package com.massey.id16107190.pong;

import java.awt.event.KeyEvent;

public class PlayerController {
	
	private ClientSettings cs = ClientSettings.getInstance();
	private int playerMoveY = 0;
	private int playerMoveX = 0;
	private boolean kbPressedUP = false;
	private boolean kbPressedDOWN = false;
	private boolean kbPressedLEFT = false;
	private boolean kbPressedRIGHT = false;
	
	private Integer[] playerCoords = new Integer[2];
	
	private int speedX = 1;
	private int speedY = 1;
	
	public PlayerController(){
		playerCoords[0] = 0;
		playerCoords[1] = 0;
	}
	public void Update() {
		movePlayer();
	}
	
	private void movePlayer() {
		int pcX = playerCoords[0] + (speedX  * playerMoveX *-1);
		int pcY = playerCoords[1] + (speedY  * playerMoveY) *-1;	
		playerCoords[0] = pcX;
		playerCoords[1] = pcY;
	}
	
	public void resetPC(){
		playerMoveY = 0;
		playerMoveX = 0;
		kbPressedUP = false;
		kbPressedDOWN = false;
		kbPressedLEFT = false;
		kbPressedRIGHT = false;
		playerCoords[0] = 0;
		playerCoords[1] = 0;
	}
	
	public void evaluateKeyPress(KeyEvent event){
		// Up + Down pressed handler
		if(event.getKeyCode() == cs.getUpBind()){
			playerMoveY = 1;
			kbPressedUP = true;
		}
		if(event.getKeyCode() == cs.getDownBind()){
			playerMoveY = -1;
			kbPressedDOWN = true;
		}
		if(event.getKeyCode() == cs.getDownBind() && event.getKeyCode() == cs.getUpBind()){
			playerMoveY = 0;
		}
		// Left + Right pressed handler
		if(event.getKeyCode() == cs.getLeftBind()){
			playerMoveX = 1;
			kbPressedLEFT = true;
		}
		if(event.getKeyCode() == cs.getRightBind()){
			playerMoveX = -1;
			kbPressedRIGHT = true;
		}
		if(event.getKeyCode() == cs.getRightBind() && event.getKeyCode() == cs.getLeftBind()){
			playerMoveX = 0;
		}
	}
	
	public void evaluateKeyRelease(KeyEvent event){
		
		//Up + Down release handler
		if(event.getKeyCode() == cs.getUpBind() && kbPressedDOWN == false){
			playerMoveY = 0;
			kbPressedUP = false;
		}
		if(event.getKeyCode() == cs.getDownBind() && kbPressedUP == false){
			playerMoveY = 0;
			kbPressedDOWN = false;
		}
		if(event.getKeyCode() == cs.getDownBind()){
			kbPressedDOWN = false;
		}
		if(event.getKeyCode() == cs.getUpBind()){
			kbPressedUP = false;
		}
		if(event.getKeyCode() == cs.getUpBind() && kbPressedDOWN == true){
			playerMoveY = -1;	
		}
		if(event.getKeyCode() == cs.getDownBind() && kbPressedUP == true){
			playerMoveY = 1;	
		}
		//Left + Right release handler
		if(event.getKeyCode() == cs.getLeftBind() && kbPressedRIGHT == false){
			playerMoveX = 0;
			kbPressedLEFT = false;
		}
		if(event.getKeyCode() == cs.getRightBind() && kbPressedLEFT == false){
			playerMoveX = 0;
			kbPressedRIGHT = false;
		}
		if(event.getKeyCode() == cs.getRightBind()){
			kbPressedRIGHT = false;
		}
		if(event.getKeyCode() == cs.getLeftBind()){
			kbPressedLEFT = false;
		}
		if(event.getKeyCode() == cs.getLeftBind() && kbPressedRIGHT == true){
			playerMoveX = -1;	
		}
		if(event.getKeyCode() == cs.getRightBind() && kbPressedLEFT == true){
			playerMoveX = 1;	
		}
	}

	public Integer[] getPlayerCoords() {
		return playerCoords;
	}

	public void setPlayerCoords(Integer[] playerCoords) {
		this.playerCoords = playerCoords;
	}
}
