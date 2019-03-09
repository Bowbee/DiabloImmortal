/*
#################################################

  Thomas Debenham - id 16107190 - Assessment 2

#################################################
*/

package com.massey.id16107190.pong;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.massey.id16107190.pong.Entities.Entity;
import com.massey.id16107190.pong.Entities.EntitySpawner;

public final class PaintEngine{
	 
    private static PaintEngine INSTANCE;
    
	private ClientSettings cs = ClientSettings.getInstance();
	private PlayerController pc = PlayerController.getInstance();
	private LevelController lc = LevelController.getInstance();
	private EntitySpawner es = EntitySpawner.getInstance();
	private Level currentLevel;
	private Grid[][] currentGrid;
	
	
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	private Image heartF = toolkit.getImage("Assets/heart_full.png");
	private Image heartE = toolkit.getImage("Assets/heart_empty.png");
	private Image vignette = toolkit.getImage("Assets/vin.png");
	private Image keyframe = toolkit.getImage("Assets/tileset1/key_frame.png");
	private Image fbFrame = toolkit.getImage("Assets/spells/spellFrame_FB.png");
	private Image amFrame = toolkit.getImage("Assets/spells/spellFrame_AM.png");
	private Image menuBackground = toolkit.getImage("Assets/titleScreen.jpg");
	private Image playBtn = toolkit.getImage("Assets/playBtn.png");
	
	private File fileStand = new File("Assets/characters/player.png");
	private BufferedImage playerStand = null;
	private File fileCast = new File("Assets/characters/player_cast.png");
	private BufferedImage playerCast = null;
	
	private int pOffsetX = 0;
	private int pOffsetY = 0;
	
	private int gridSize = lc.getGridSize();
    
    private int middleX = cs.getResX()/2;
    private int middleY = cs.getResY()/2;

	private int ttimer = 100;
	private int rateLimit = 10;
	private int updateTime = 0;
	private int canUpdate = 0;
	
    private PaintEngine() {
    	try {
    		playerStand = ImageIO.read(fileStand);
    		playerCast = ImageIO.read(fileCast);
    		pOffsetX = (playerStand.getWidth()/2)+10;
    		pOffsetY = (playerStand.getHeight()/2)+10;
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
     
    public static PaintEngine getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new PaintEngine();
        }
         
        return INSTANCE;
    }
    
    public void nextLevel(){
    	currentLevel = lc.getCurrentLevel();
    	currentGrid = currentLevel.getGrid();
    }
    

	public void DrawMenu(Game game) {
		game.drawImage(menuBackground, 0, 0, cs.getResX(), cs.getResY());
		game.drawImage(playBtn, cs.getResX()/2 - playBtn.getWidth(null)/2, cs.getResY()/2);
		
	}
    
    public void PaintUI(GameEngine game){
    	Image img = vignette;
    	game.drawImage(img, 0, 0);
    	img = heartF;
    	int posX = cs.getResX() - 100;
    	int posY = 60;
    	for(int i = 0; i < 5; i++){
    		if(pc.getPlayer().getHealth() >= 20*(i+1)){
    			img = heartF;
    		}
    		else{
    			img = heartE;
    		}
    		game.drawImage(img, posX-(i*70), posY, 60, 60);
    	}
    	if(pc.getPlayer().getInventory().contains("key1")){
    		Image key1 = toolkit.getImage("Assets/tileset1/key_UI_1.png");
    		Image keyFrame = keyframe;
    		game.drawImage(key1, 10, 10, 160, 160);
    		game.drawImage(keyFrame, 50, 55, 80, 80);
    	}
    	game.drawImage(fbFrame, cs.getResX()-170, cs.getResY()-170, 100, 100);
    	game.drawImage(amFrame, cs.getResX()-300, cs.getResY()-170, 100, 100);
    	
    	
    	
    }
    
    public void PaintPlayer(GameEngine game){
    	if(playerStand != null){
    		BufferedImage playerImg = null;
    		if(pc.isCasting() == true){
    			playerImg = playerCast;
			}
			else{
				playerImg = playerStand;
			}
    		
    		AffineTransform tx = new AffineTransform();
    	    tx.rotate(pc.getAngle(), playerImg.getWidth() / 2, playerImg.getHeight() / 2);
    	    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
    	    playerImg = op.filter(playerImg, null);
    	    game.drawImage(playerImg, middleX-(pOffsetX), middleY-(pOffsetY), playerImg.getWidth()*1.5, playerImg.getHeight()*1.5);
    	}
    }
    
    
    public void PaintHitBoxes(GameEngine game) {
    	ttimer += 1;

    	for (Entity item : es.entityList){
	
	        double speed = item.getVelocity() * -1;
	        double angle = item.getAngle();
	        
	        double xVelocity = (speed) * Math.cos(angle);
	        double yVelocity = (speed) * Math.sin(angle);
	        
	        //System.out.println(item.getPosX() + " "+ item.getPosY());
	        item.setPosX((item.getPosX()+xVelocity));
	        item.setPosY((item.getPosY()+yVelocity));
	        
	        double xcoord = item.getPosX() + lc.getWorldX();
			double ycoord = item.getPosY() + lc.getWorldY(); 
			

			
			if(item.getImage() != null){
		
				if(item.getType() == "mob" || item.getType() == "mage"){
					if(ttimer > updateTime + rateLimit){
			    		canUpdate = 0;
			    		item.setRotated(false);
			    	}

					BufferedImage img = item.getImage();
					if(canUpdate < 20){
						AffineTransform tx = new AffineTransform();
					    tx.rotate(item.getAngle(), item.getImage().getHeight()/2, item.getImage().getWidth()/2);
					    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
					    item.setRotatedImage(op.filter(img, null));
					    updateTime = ttimer;
					    canUpdate += 1;
					}
					if(item.getRotatedImage() != null){
						game.drawImage(item.getRotatedImage(), xcoord-item.getRotatedImage().getWidth()+4, ycoord-item.getRotatedImage().getHeight(), item.getRotatedImage().getWidth()*2, item.getRotatedImage().getHeight()*2);
					}
					else{
						game.drawImage(item.getImage(), xcoord, ycoord, item.getImage().getWidth()*2, item.getImage().getHeight()*2);
					}
					
				}
				else{
					BufferedImage img = item.getImage();
					if(item.getRotated() == false){
						AffineTransform tx = new AffineTransform();
					    tx.rotate(item.getAngle(), img.getWidth() / 2, img.getHeight() / 2);
					    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
					    img = op.filter(img, null);
					    item.setImage(img);
					    item.setRotated(true);
					}
					game.drawImage(img, xcoord-(item.getRadius()*2), ycoord-(item.getRadius()*2), item.getRadius()*4, item.getRadius()*4);
				}
	
			}
			else{
				game.changeColor(item.getColor());
				game.drawSolidCircle(xcoord, ycoord, item.getRadius());

			}

	        int offset = 30;
	        float width = 80;
	        int height = 10;
	        int border = 4;
	        float hp = item.getHealth();
	        float perc = hp/100;
	        if(item.getType() == "mob" || item.getType() == "mage"){		        
		        if(item.getHealth() > 0 && item.getHealth() < 100){
		        	game.changeColor(Color.white);
		        	game.drawSolidRectangle(xcoord - ((width+border)/2), ycoord - item.getRadius() - offset - border/2, width+border, height+border);
		        	game.changeColor(Color.red);
		        	game.drawSolidRectangle(xcoord - (width/2), ycoord - item.getRadius() - offset, width*(perc), height);
		        	
		        }
	        }

		}
    	
    }
	
    public void PaintLevel(GameEngine game) {
		game.changeColor(game.white);
		
		int xoffset = lc.getWorldX();		
		int xcoord = 0 + xoffset;
		int ycoord = 0 + lc.getWorldY();

		for(int y = 0; y < currentGrid.length; y++) {
			for(int x = 0; x < currentGrid[y].length; x++) {
				if(currentGrid[y][x] != null){
					if(currentGrid[y][x].getImage() == null){
						game.changeColor(currentGrid[y][x].getColor());
						game.drawSolidRectangle(xcoord, ycoord, gridSize, gridSize);
					}
					else{
						Image img = currentGrid[y][x].getImage();
						//System.out.println(img);
						game.drawImage(img, xcoord, ycoord, gridSize, gridSize);
					}
					
					
				}
				
				xcoord += gridSize;
			}
			xcoord = 0 + xoffset;
			ycoord += gridSize;
			
		}

	}

	public void evaluateMouseClick(MouseEvent event, Game g) {
		if(event.getButton() == 1){ // left click
			System.out.println(event.getX() + " " + event.getY());
			
			if(event.getX() > (cs.getResX()/2 - playBtn.getWidth(null)/2) && event.getX() < (cs.getResX()/2 + playBtn.getWidth(null)/2)){ // x coord check
				if(event.getY() > (cs.getResY()/2) && event.getY() < cs.getResY()/2  + playBtn.getHeight(null)){
					g.setPaused(false);
				}
			}
			
		}
		
	}

}


