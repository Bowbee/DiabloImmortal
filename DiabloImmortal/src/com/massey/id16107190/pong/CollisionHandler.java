/*
#################################################

  Thomas Debenham - id 16107190 - Assessment 2

#################################################
*/

package com.massey.id16107190.pong;

import java.util.ArrayList;
import java.util.List;

import com.massey.id16107190.pong.Entities.Entity;
import com.massey.id16107190.pong.Entities.EntitySpawner;
import com.massey.id16107190.pong.Entities.Entity_4_MageFireball;

public final class CollisionHandler{
	 
    private static CollisionHandler INSTANCE;
    
	private PlayerController pc = PlayerController.getInstance();
	private LevelController lc = LevelController.getInstance();
	private EntitySpawner es = EntitySpawner.getInstance();
	private Level currentLevel;
	private Grid[][] currentGrid;
	private int gridSize = lc.getGridSize();
	
	private HitBox playerHitBox = pc.getPlayer().getHitbox();
     
    private CollisionHandler() {  
    	nextLevel();
    }
     
    public static CollisionHandler getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new CollisionHandler();
        } 
        return INSTANCE;
	}
    
    public void update(){
    	getPlayerCollisions();
    	getEntityCollisions();
    }
    
    public void nextLevel(){
    	currentLevel = lc.getCurrentLevel();
    	currentGrid = currentLevel.getGrid();
    	getPlayerCollisions();
    	getEntityCollisions();
    }
    
    public double getDistance(double gridPos, double gridPos2, int x2, int y2){
    	double dist = Math.sqrt(Math.pow(gridPos-x2, 2) + Math.pow(gridPos2-y2, 2));
    	
		return dist;
    }
    
    private void getPlayerCollisions() {
    	
    	double[] gridPos = lc.getPlayerGridPosition(playerHitBox.getRadius());
    	System.out.println(gridPos[0] + " : " + gridPos[1]);
    	
    	int gridPosX = (int) (gridPos[0]/gridSize);
    	int gridPosY = (int) (gridPos[1]/gridSize);
    	Grid below = null;
    	Grid above = null;
    	Grid left = null;
    	Grid right = null;
    	
    	Integer Bmax = null;
    	Integer Amax = null;
    	Integer Lmax = null;
    	Integer Rmax = null;
    	
    	try{
    		below = currentGrid[gridPosY+1][gridPosX];
    	}
    	catch (Exception e){
    		Bmax = null;
    	}
    	try{
    		gridPosY = (int) ((gridPos[1]+3)/gridSize);
    		above = currentGrid[gridPosY-1][gridPosX];
    	}
    	catch (Exception e){
    		Amax = null;
    	}
    	try{
    		gridPosX = (int) ((gridPos[0]+3)/gridSize);
    		left = currentGrid[gridPosY][gridPosX-1];
    		
    	}
    	catch (Exception e){
    		Lmax = null;
    	}
    	try{
    		right = currentGrid[gridPosY][gridPosX+1];
    	}
    	catch (Exception e){
    		Rmax = null;
    	}
    	if(below != null){
        	if(below.hasCollision()){
        		Bmax = (gridPosY)*gridSize;
        		//System.out.println(Bmax);
        	}
    	}
    	if(above != null){
        	if(above.hasCollision()){
        		Amax = ((gridPosY)*gridSize);
        		//System.out.println(Amax);
        	}
    	}
    	if(left != null){
        	if(left.hasCollision()){
        		Lmax = (gridPosX)*gridSize;
        		System.out.println(Lmax);
        	}
    	}
    	if(right != null){
        	if(right.hasCollision()){
        		Rmax = ((gridPosX)*gridSize);
        		//System.out.println(Rmax);
        	}
    	}
    	
    	if(Bmax != null){
    		if(gridPos[1] >= Bmax){
    			//System.out.println("COLLIDE BOTTOM?");
    			pc.stopPlayerDown();
    		}
    	}
    	if(Amax != null){
    		if(gridPos[1] <= Amax){
    			//System.out.println(Amax);
    			//System.out.println("COLLIDE ABOVE?");
    			pc.stopPlayerUp();
    		}
    	}
    	if(Lmax != null){
    		if(gridPos[0] <= Lmax+4){
    			//System.out.println("COLLIDE LEFT?");
    			pc.stopPlayerLeft();
    		}
    	}
    	if(Rmax != null){
    		if(gridPos[0] >= Rmax){
    			//System.out.println("COLLIDE RIGHT?");
    			pc.stopPlayerRight();
    		}
    	}
    	
    }
    public double getDistance(double targetPosX, double targetPosY, double gridPos2, double gridPos3){
    	double dist = Math.sqrt(Math.pow(targetPosX-gridPos2, 2) + Math.pow(targetPosY-gridPos3, 2));
		return dist;
    }
    
    private void getEntityCollisions(){
    	double[] gridPos = lc.getPlayerGridPosition(playerHitBox.getRadius());
    	
    	if(es.entityList.isEmpty() == false){
    		List<Entity> rm = new ArrayList<>();
    		for(Entity e : es.entityList){
    			double posX = e.getPosX();
    			double posY = e.getPosY();
    			for(Entity compare : es.entityList){
    				if(e.getType() == "spell"){
    					if(e.equals(compare) == false){
        					if((posX-e.getRadius() < compare.getPosX()+compare.getRadius() && posX+e.getRadius() > compare.getPosX()-compare.getRadius()) &&
        							(posY-e.getRadius() < compare.getPosY()+compare.getRadius() && posY+e.getRadius() > compare.getPosY()-compare.getRadius())){
        						double dist = getDistance(e.getPosX(), e.getPosY(), gridPos[0], gridPos[1]);
        						e.setVolume((int) (dist/60*-1));
        						if(e.hit(compare) == null){
        							
        							rm.add(e);
        						}
        						if(compare.getType() == "harmfulspell"){
        							rm.add(e);
        							rm.add(compare);
        						}
        						continue;
        					}
        				}
    				}
    				if(e.getType() == "harmfulspell"){
    					if((posX-e.getRadius() < gridPos[0]+compare.getRadius() && posX+e.getRadius() > gridPos[0]-compare.getRadius()) &&
    							(posY-e.getRadius() < gridPos[1]+compare.getRadius() && posY+e.getRadius() > gridPos[1]-compare.getRadius())){
    						double dist = getDistance(e.getPosX(), e.getPosY(), gridPos[0], gridPos[1]);
    						e.setVolume((int) (dist/60*-1));
    						Entity_4_MageFireball emf = (Entity_4_MageFireball) e;
    						if(e.hit() == null && emf.active == true){
    							emf.active = false;
    							e = emf;
    							pc.getPlayer().setHealth(pc.getPlayer().getHealth()-e.getDamage());
    							rm.add(e);
    						}
    						continue;
    						
    					}
    				}
    				
    			}
    			
    			try{
    				int gridX = (int) ((posX+e.getRadius())/gridSize);
        			int gridY = (int) ((posY+e.getRadius())/gridSize);
    				Grid currentTile = currentGrid[gridY][gridX];
    				if(currentTile.hasCollision()){
    					double dist = getDistance(e.getPosX(), e.getPosY(), gridPos[0], gridPos[1]);
						e.setVolume((int) (dist/60*-1));
    					if(e.hit() == null){
							rm.add(e);
						}
    					continue;
    				}
    				
    				currentTile = currentGrid[gridY-1][gridX-1];
    				if(currentTile.hasCollision()){
    					double dist = getDistance(e.getPosX(), e.getPosY(), gridPos[0], gridPos[1]);
						e.setVolume((int) (dist/60*-1));
    					if(e.hit() == null){
							rm.add(e);
						}
    					continue;
    				}
    			}
    			catch(Exception IndexOutOfBounds){
    				
    			}	
    		}
    		if(rm.isEmpty() == false){
    			for(Entity e : rm){
    				es.entityList.remove(e);
    			}
    		}
    	}
    	
    }
}



