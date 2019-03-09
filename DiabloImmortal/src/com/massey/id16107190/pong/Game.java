/*
#################################################

  Thomas Debenham - id 16107190 - Assessment 2

#################################################
*/

package com.massey.id16107190.pong;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


import com.massey.id16107190.pong.Entities.EntitySpawner;
import com.massey.id16107190.pong.Entities.MobController;

public class Game extends GameEngine {
	// Settings data
	private ClientSettings cs = ClientSettings.getInstance();
	private boolean paused = true;
	private boolean titleSet = false;
	private AudioClip menuAmbient = this.loadAudio("Assets/spells/title_ambient.wav");
	// GameObjects
	// Player controls etc
	private PlayerController pc = PlayerController.getInstance();
	private LevelController lc = LevelController.getInstance();
	private PaintEngine paint = PaintEngine.getInstance();
	private CollisionHandler ch = CollisionHandler.getInstance();
	private MobController mc = MobController.getInstance();
	private EntitySpawner ec = EntitySpawner.getInstance();
	private boolean doOnce = false;
	public void init() {
		// Initialize Window Size
		setWindowSize(cs.getResX(), cs.getResY());	
		lc.setWorldY(0);
	}

	public static void start() {
	}
	
	public static void quit() {
		
	}
	
	public void reset(){
		// Paddle reset
		pc.resetPC();
		// Round reset + timer
		paused = true;
	}
	
	public void changeLevel(int levelNum){
		lc.nextLevel(levelNum);
		paint.nextLevel();
		ch.nextLevel();
		pc.resetPC();
		AudioClip ac = this.loadAudio("Assets/spells/enter.wav");
		this.playAudio(ac, -15);
	}

	@Override
	public void update(double dt) {
		if(!paused){
			if(titleSet == false){
				titleSet = true;
				mFrame.setTitle("Diablo Immortal");	
				pc.resetPC();
				ec.setGameEngine(this);
				pc.setGame(this);
				stopAudioLoop(menuAmbient);
				AudioClip ac = this.loadAudio("Assets/spells/enter.wav");
				this.playAudio(ac, -20);
				ac = this.loadAudio("Assets/spells/ambience.wav");
				this.startAudioLoop(ac, -15);
				ac = this.loadAudio("Assets/spells/music1.wav");
				this.playAudio(ac, -10);
				ac = this.loadAudio("Assets/spells/fireball1.wav");
				this.playAudio(ac, -1000);
				ac = this.loadAudio("Assets/spells/fireball2.wav");
				this.playAudio(ac, -1000);
				ac = this.loadAudio("Assets/spells/fireball3.wav");
				this.playAudio(ac, -1000);
				ac = this.loadAudio("Assets/spells/fireball4.wav");
				this.playAudio(ac, -1000);
				ac = this.loadAudio("Assets/spells/fireball5.wav");
				this.playAudio(ac, -1000);
				ac = this.loadAudio("Assets/spells/explode1.wav");
				this.playAudio(ac, -1000);
				ac = this.loadAudio("Assets/spells/explode2.wav");
				this.playAudio(ac, -1000);
				ac = this.loadAudio("Assets/spells/explode3.wav");
				this.playAudio(ac, -1000);
			}
			/*
			if(counter >= 600){
				counter = 0;
				levelNum = 1;
				changeLevel(levelNum);
			}
			*/
			pc.Update(dt);
			//System.out.println(gridPos[0] + " : " + gridPos[1]);
			ch.update();
			mc.update();
		
		}
		if(doOnce  == false){
			
			this.startAudioLoop(menuAmbient, -20);
			doOnce = true;
			mFrame.setTitle("Diablo Immortal");	
			
		}
		
		
		
	}

	public void paintComponent() {
		// Clear the background to black
		
		if(!paused){
			changeBackgroundColor(black);
			clearBackground(cs.getResX(), cs.getResY());
			paint.PaintLevel(this);
			paint.PaintHitBoxes(this);
			paint.PaintPlayer(this);
			paint.PaintUI(this);			
		}
		else{
			changeBackgroundColor(white);
			clearBackground(cs.getResX(), cs.getResY());
			paint.DrawMenu(this);
		}
		
		
	}
	
	public void keyPressed(KeyEvent event) {
		if(paused == true){
			if(event.getKeyCode() == KeyEvent.VK_ENTER){ // Start the game
				paused = false;
			}
		}
		else{
			pc.evaluateKeyPress(event);
		}
	}
	
	public void keyReleased(KeyEvent event){
		if(paused == true){
			
		}
		else{
			pc.evaluateKeyRelease(event);
		}

	}
	
	//System.out.println(event.getX() + " " + event.getY());
	public void mousePressed(MouseEvent event) {
		if(paused == true){
			paint.evaluateMouseClick(event, this);
		}
		else{
			
			pc.evaluateMouseClick(event);
		}	
	}
	public void mouseMoved(MouseEvent event) {
		if(!paused){
			int y1 = cs.getResY()/2 ;
			int x1 = cs.getResX()/2 ;
			double angle = Math.atan2((event.getY() - y1), (event.getX()- x1));
			pc.setAngle(angle);
		}
		
	}

	public void setPaused(boolean b) {
		this.paused = b;
	}

}
