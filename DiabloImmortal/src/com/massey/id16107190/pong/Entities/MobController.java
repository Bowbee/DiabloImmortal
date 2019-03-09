/*
#################################################

  Thomas Debenham - id 16107190 - Assessment 2

#################################################
*/

package com.massey.id16107190.pong.Entities;

import java.util.ArrayList;
import java.util.List;

import com.massey.id16107190.pong.Grid;
import com.massey.id16107190.pong.HitBox;
import com.massey.id16107190.pong.Level;
import com.massey.id16107190.pong.LevelController;
import com.massey.id16107190.pong.PlayerController;
import com.massey.id16107190.pong.Entities.EntitySpawner;

public final class MobController{
	 
    private static MobController INSTANCE; 
    
    private EntitySpawner es = EntitySpawner.getInstance();
    private PlayerController pc = PlayerController.getInstance();
	private LevelController lc = LevelController.getInstance();

	private Level currentLevel = lc.getCurrentLevel();
	private Grid[][] currentGrid = currentLevel.getGrid();
	private int gridSize = lc.getGridSize();
    
    private HitBox playerHitBox = pc.getPlayer().getHitbox();
    
    private double lastHit = 0;
    private double counter = 0;

    private MobController() {

    }
     
    public static MobController getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new MobController();
        }
         
        return INSTANCE;
    }
    
    public void update(){
    	mobPathing();
    	//mobCollision();
    	
    }
    
    public double getDistance(double targetPosX, double targetPosY, double gridPos2, double gridPos3){
    	double dist = Math.sqrt(Math.pow(targetPosX-gridPos2, 2) + Math.pow(targetPosY-gridPos3, 2));
		return dist;
    }
    
    public void mobPathing(){
    	counter += 1;
    	double[] gridPos = lc.getPlayerGridPosition(playerHitBox.getRadius());
    	List<Entity> rm = new ArrayList<>();
    	List<Entity> cast = new ArrayList<>();
    	
    	for(Entity e : es.entityList){
    		if(e.getType() == "mob"){
    			if(e.getHealth() <= 0){
    				rm.add(e);
    			}
    			double dist = getDistance(e.getPosX(), e.getPosY(), gridPos[0], gridPos[1]);
    			if(dist <= 600 || e.getHealth() < 100){
    				double angle = Math.atan2((gridPos[1] - e.getPosY()), (gridPos[0] - e.getPosX()));

    				e.setAngle(angle);
    				e.setVelocity(-3);
    				
    			}
    			if(dist <= 1000 && e.getVelocity() != 0){
    				double angle = Math.atan2((gridPos[1] - e.getPosY()), (gridPos[0] - e.getPosX()));
    				e.setAngle(angle);
    				e.setVelocity(-3);	
    			}
    			if(dist >= 800){
    				e.setVelocity(0);
    				
    			}
    			if(dist < e.getRadius()){
    				e.setVelocity(0);
    				if(this.lastHit+20 <= counter){
    					this.lastHit = counter;
    					pc.getPlayer().setHealth(pc.getPlayer().getHealth()-e.getDamage());
    				}
    				
    			}
    			
    		}
    		
    		if(e.getType() == "mage"){
    			if(e.getHealth() <= 0){
    				rm.add(e);
    			}
    			double dist = getDistance(e.getPosX(), e.getPosY(), gridPos[0], gridPos[1]);
    			if(dist <= 600 || e.getHealth() < 100){
    				double angle = Math.atan2((gridPos[1] - e.getPosY()), (gridPos[0] - e.getPosX()));
    				e.setAngle(angle);
    				//System.out.println(angle);
    				int vol = (int) (dist / 100)*-1;
    				e.setVolume(vol);
					cast.add(e);
    				//e.cast(0, true);
    				continue;
    				
    			}
    			if(dist <= 1000 && e.getVelocity() != 0){
    				double angle = Math.atan2((gridPos[1] - e.getPosY()), (gridPos[0] - e.getPosX()));
    				e.setAngle(angle);
    				int vol = (int) (dist / 100)*-1;
    				e.setVolume(vol);
    				//e.cast(0, true);
					cast.add(e);
    				continue;
    			}
    			if(dist >= 800){
    				e.setVelocity(0);
    				
    			}
    			if(dist < e.getRadius()){
    				e.setVelocity(0);
    				if(this.lastHit+20 <= counter){
    					this.lastHit = counter;
    					pc.getPlayer().setHealth(pc.getPlayer().getHealth()-e.getDamage());
    				}
    				
    			}
        	}
    	}
    	if(cast.isEmpty() == false){
    		for(Entity e : cast){
    			e.cast(0, true);
    		}
    	}
    	
    	if(rm.isEmpty() == false){
			for(Entity e : rm){
				es.entityList.remove(e);
			}
		}
    	
    	
    	
    	
    }

    public void mobCollision(){
    	
    	for(Entity ent : es.entityList){
    		//System.out.println(gridPos[0] + " : " + gridPos[1]);
        	
        	int gridPosX = (int) (ent.getPosX()/gridSize);
        	int gridPosY = (int) (ent.getPosY()/gridSize);
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
        		above = currentGrid[gridPosY-1][gridPosX];
        	}
        	catch (Exception e){
        		Amax = null;
        	}
        	try{
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
            		//System.out.println(Lmax);
            	}
        	}
        	if(right != null){
            	if(right.hasCollision()){
            		Rmax = ((gridPosX)*gridSize);
            		//System.out.println(Rmax);
            	}
        	}
        	
        	if(Bmax != null){
        		if(ent.getPosY() >= Bmax){
        			//System.out.println("COLLIDE BOTTOM?");
        			if(ent.velocity > 1){
        				ent.setVelocity(0);
        			}
        		}
        	}
        	if(Amax != null){
        		if(ent.getPosY() <= Amax){
        			//System.out.println("COLLIDE BOTTOM?");
        			if(ent.velocity > 1){
        				ent.setVelocity(0);
        			}
        		}
        	}
        	if(Lmax != null){
        		if(ent.getPosX() <= Lmax){
        			//System.out.println("COLLIDE BOTTOM?");
        			if(ent.velocity > 1){
        				ent.setVelocity(0);
        			}
        		}
        	}
        	if(Rmax != null){
        		if(ent.getPosX() >= Rmax){
        			//System.out.println("COLLIDE BOTTOM?");
        			if(ent.velocity > 1){
        				ent.setVelocity(0);
        			}
        		}
        	}
    		
    	}
    	
    }
}

