/*
#################################################

  Thomas Debenham - id 16107190 - Assessment 2

#################################################
*/

package com.massey.id16107190.pong;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.massey.id16107190.pong.Spells.Spell;
import com.massey.id16107190.pong.Spells.Spell_1_Fireball;
import com.massey.id16107190.pong.Spells.Spell_2_Missile;


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
	
	private Game game;
	
	private boolean kbPressedUP = false;
	private boolean kbPressedDOWN = false;
	private boolean kbPressedLEFT = false;
	private boolean kbPressedRIGHT = false;
	
	private boolean collideLEFT = false;
	private boolean collideRIGHT = false;
	private boolean collideUP = false;
	private boolean collideDOWN = false;
	
	private Spell mainSpell = new Spell_1_Fireball();
	private Spell offSpell = new Spell_2_Missile();
	
	private Integer[] playerCoords = new Integer[2];
	
	private int speedX = 4;
	private int speedY = 4;
	private double angle = 0;
	private boolean casting = false;
	private long castTimer = 0;
	private int castCooldown = 15;
	private int ttracker = 0;
	private int centerX = cs.getResX()/2;
	private int centerY = cs.getResY()/2;
	private boolean offCasting = false;
	private long offCastTimer = 0;
	private int offCastCooldown = 7;
	
	public void Update(double dt) {
		if(player.getHealth() <= 0){
			game.changeLevel(lc.getCurrentLevelNum());
			player.setHealth(100);
		}
		movePlayer();
		collideLEFT = false;
		collideRIGHT = false;
		collideUP = false;
		collideDOWN = false;
		
		ttracker += 1;
		if(casting == true){
			if(castTimer+castCooldown < ttracker){
				casting = false;
			}
		}
		if(offCasting  == true){
			if(offCastTimer+offCastCooldown < ttracker){
				offCasting = false;
			}
		}
		System.out.println(playerMoveX);
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
		
		Grid[][] grid = lc.getCurrentLevel().getGrid();
		try{
			double[] gridPos = lc.getPlayerGridPosition(player.getHitbox().getRadius());
			int gridPosX = (int) (gridPos[0]/lc.getGridSize());
	    	int gridPosY = (int) (gridPos[1]/lc.getGridSize());
			if(grid[gridPosY][gridPosX] != null){
				grid[gridPosY][gridPosX].trigger();
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Player is out of bounds!");
		}
		lc.updatePositions();
	}
	
	public void stopPlayerLeft() {
		collideLEFT = true;
	}
	public void stopPlayerDown() {
		collideDOWN = true;
	}

	public void stopPlayerUp() {
		collideUP = true;
	}

	public void stopPlayerRight() {
		collideRIGHT = true;
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
		speedX = 4;
		speedY = 4;
		angle = 0;
		casting = false;
		castTimer = 0;
		offCasting = false;
		offCastTimer = 0;
		castCooldown = 15;
		ttracker = 0;
	}
	
	public void clickGrid(int x, int y){
		Grid[][] grid = lc.getCurrentLevel().getGrid();
		int gridX = (x) / lc.getGridSize() * -1;
		int gridY = (y) / lc.getGridSize() * -1;
		grid[gridY][gridX].clicked();;
		System.out.println(gridX + ":"+gridY);
	}
	
	public double getLineFromCenter(MouseEvent event){
		int centerX = cs.getResX()/2;
		int centerY = cs.getResY()/2;
		//System.out.println(event.getX() + " " + event.getY());
		
		double angle = Math.atan2((centerY - event.getY()), (centerX - event.getX()));
		return(angle);
		
	}
	
	public void evaluateMouseClick(MouseEvent event) {
		if(event.getButton() == 1){ //if left click
			System.out.println("Left Click");
			double Spellangle = getLineFromCenter(event);
			if(offCasting == false){
				offSpell.cast(centerX-lc.getWorldX(), centerY-lc.getWorldY(), Spellangle, false);
				offCasting = true;
				offCastTimer  = this.ttracker;
			}
		}
		if(event.getButton() == 3){
			System.out.println("Right Click");
			double Spellangle = getLineFromCenter(event);
			if(casting == false){
				mainSpell.cast(centerX-lc.getWorldX(), centerY-lc.getWorldY(), Spellangle, false);
				casting = true;
				castTimer  = this.ttracker;
			}
		}
		
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

	public void setAngle(double angle) {
		this.angle  = angle;
	}

	public double getAngle() {
		return this.angle;
	}
	public boolean isCasting(){
		return casting ;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	

	
}
