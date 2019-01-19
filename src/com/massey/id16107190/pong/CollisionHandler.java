package com.massey.id16107190.pong;

public final class CollisionHandler{
	 
    private static CollisionHandler INSTANCE;
    
	private ClientSettings cs = ClientSettings.getInstance();
	private PlayerController pc = PlayerController.getInstance();
	private LevelController lc = LevelController.getInstance();
	private Level currentLevel = lc.getCurrentLevel();
	private Grid[][] currentGrid = currentLevel.getGrid();
	
	private HitBox playerHitBox = pc.getPlayer().getHitbox();
	
	private int gridSize = lc.getGridSize();
    
    private int middleX = cs.getResX()/2;
    private int middleY = cs.getResY()/2;
     
    private CollisionHandler() {  
    }
     
    public static CollisionHandler getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new CollisionHandler();
        }
         
        return INSTANCE;
	}
    
    public Grid[] getPlayerCollisions() {
    	/*
    	double[] gridPos = lc.getPlayerGridPosition(playerHitBox.getRadius()-10);
    	int gridPosX = (int) gridPos[0];
    	int gridPosY = (int) gridPos[1];
    	
    	if(currentGrid.length > gridPosY && currentGrid[0].length > gridPosX) {
    		if(gridPosX >= 0 && gridPosY >= 0) {
    			if(currentGrid[gridPosY][gridPosX].hasCollision()) {
            		pc.stopPlayer(gridPos);
            	}
    		}
        	
    		
    	}
    	*/
    	Grid[] mapColliders = lc.getColliders();
    	System.out.println(mapColliders[0].getPosX() + " : " + mapColliders[0].getPosY());
    	for(int i = 0; i < mapColliders.length; i++) {
    		if(mapColliders[i].getPosX() - playerHitBox.getRadius() <= middleX 
        			&& mapColliders[i].getPosX() + playerHitBox.getRadius() + lc.getGridSize() > middleX) {
        		if(mapColliders[i].getPosY() - playerHitBox.getRadius() <= middleY
        				&& mapColliders[i].getPosY() + playerHitBox.getRadius() +lc.getGridSize() > middleY ) {
        			System.out.println("COLLIDE");
        			pc.stopPlayer();
        			System.out.println(lc.getPlayerGridPosition(playerHitBox.getRadius()/2)[0] + " " + lc.getPlayerGridPosition(playerHitBox.getRadius()/2)[1] );
        		}
        		
        	}
    	}
    	
    	

    	
    	//System.out.println(gridPosX + " : " + gridPosY);
    	
		return null;
    	
    }
}



