package com.massey.id16107190.pong;

import java.awt.event.KeyEvent;


public final class PlayerController{
	 
    private static PlayerController INSTANCE;
    
    private PlayerController() {  
    	
    }
     
    public static PlayerController getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new PlayerController();
        }
         
        return INSTANCE;
    }
	
	private ClientSettings cs = ClientSettings.getInstance();
	private LevelController lc = LevelController.getInstance();
	private Player player = new Player();
	private int playerMoveY = 0;
	private int playerMoveX = 0;
	private boolean kbPressedUP = false;
	private boolean kbPressedDOWN = false;
	private boolean kbPressedLEFT = false;
	private boolean kbPressedRIGHT = false;
	
	private boolean collideLEFT = false;
	private boolean collideRIGHT = false;
	private boolean collideUP = false;
	private boolean collideDOWN = false;
	
	private Integer[] playerCoords = new Integer[2];
	
	private int speedX = 10;
	private int speedY = 10;
	
	public void Update(double dt) {
		movePlayer();
		collideLEFT = false;
		collideRIGHT = false;
		collideUP = false;
		collideDOWN = false;
		//System.out.println("Coords:"+lc.getWorldCoords()[1]);
	}
	
	private void movePlayer() { //moves the offset for everything in the world, avoids a follow 'camera'
		//System.out.println(playerMoveX);
		if(collideLEFT && playerMoveX > 0) {
			//System.out.println("Collision stopping left");
			playerMoveX = 0;
		}
		if(collideRIGHT && playerMoveX < 0) {
			//System.out.println("Collision stopping right");
			playerMoveX = 0;
		}
		if(collideUP && playerMoveY > 0) {
			playerMoveY = 0;
		}
		if(collideDOWN && playerMoveY < 0) {
			playerMoveY = 0;
		}
		int pcX = lc.getWorldX() + (speedX * playerMoveX);
		int pcY = lc.getWorldY() + (speedY * playerMoveY);	
		lc.setWorldX(pcX);
		lc.setWorldY(pcY);
		lc.updatePositions();
	}
	
	public void stopPlayer() {
		if(kbPressedUP) {
			collideUP = true;
			System.out.println("COLLIDE UP");
		}
		if(kbPressedDOWN) {
			collideDOWN = true;
			System.out.println("COLLIDE DOWN");
		}
		if(kbPressedLEFT) {
			collideLEFT = true;
			System.out.println("COLLIDE LEFT");
		}
		if(kbPressedRIGHT) {
			collideRIGHT = true;
			System.out.println("COLLIDE RIGHT");
		}
		/*
		double gridPosX = gridPos[0] % 1;
		double gridPosY = gridPos[1] % 1;

    	System.out.println(gridPosX + " : " + gridPosY);
    	if(gridPosX <= 1 && gridPosX >= 0.85) {
    		System.out.println("Block is LEFT");
    		collideLEFT = true;
    	}
    	else if(gridPosX >= -0.9 && gridPosX <= -0.85) {
    		System.out.println("Block is RIGHT");
    		collideRIGHT = true;
    	}
    	if(gridPosY <= 1 && gridPosY >= 0.85) {
    		System.out.println("Block is DOWN");
    		collideDOWN = true;
    	}
    	else if(gridPosY >= -0.9 && gridPosY <= -0.85) {
    		System.out.println("Block is UP");
    		collideUP = true;
    	}
    	*/
		
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

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
