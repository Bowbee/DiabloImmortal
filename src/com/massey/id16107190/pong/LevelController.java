package com.massey.id16107190.pong;

public final class LevelController{
	 
    private static LevelController INSTANCE; 
    
    private ClientSettings cs = ClientSettings.getInstance();
    //private PlayerController pc = PlayerController.getInstance();
    
    private int worldX = 0;
    private int worldY = 0;
    
    private int gridSize = 100;
    
    private Level currentLevel = new Level();
    private Grid[][] currentGrid = currentLevel.getGrid();
     
    private LevelController() { 
    }
     
    public static LevelController getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new LevelController();
        }
         
        return INSTANCE;
    }
    
    public void updatePositions() {
    	int xoffset = getWorldX();		
		int xcoord = 0 + xoffset;
		int ycoord = 0 + getWorldY();
		
		for(int y = 0; y < currentGrid.length; y++) {
			for(int x = 0; x < currentGrid[y].length; x++) {
				if(currentGrid[y][x] != null) {
					currentGrid[y][x].setPosX(xcoord);
					currentGrid[y][x].setPosY(ycoord);
				}
				xcoord += gridSize;
			}
			xcoord = 0 + xoffset;
			ycoord += gridSize;
		}
    }
    
    public Grid[] getColliders() {
    	//(currentGrid[0].length * currentGrid.length)
    	int len = 0;
    	
    	for(int y = 0; y < currentGrid.length; y++) {
			for(int x = 0; x < currentGrid[y].length; x++) {
				if(currentGrid[y][x].hasCollision()) {
					len += 1;
					
				}
				
			}
    	}
    	Grid[] colliders = new Grid[len];
    	int counter = 0;
    	
    	for(int y = 0; y < currentGrid.length; y++) {
			for(int x = 0; x < currentGrid[y].length; x++) {
				if(currentGrid[y][x].hasCollision()) {
					
					colliders[counter] = currentGrid[y][x];
					counter += 1;
					
				}
				
			}
    	}
    	return colliders;
		
    	
    	
    }
    
    public double[] getPlayerGridPosition(int radius) {
    	int yoffset = (cs.getResY()/2);
    	int xoffset = (cs.getResX()/2);
    	
    	double gridX = (double) (worldX - xoffset + radius) /-100;
    	double gridY = (double) (worldY - yoffset + radius) /100;
    	double[] out = {gridX, gridY};
    	return out;
    }

	public int getWorldX() {
		return worldX;
	}

	public void setWorldX(int worldX) {
		this.worldX = worldX;
	}

	public int getWorldY() {
		return worldY;
	}

	public void setWorldY(int worldY) {
		this.worldY = worldY;
	}
	
	public Integer[] getWorldCoords() {
		Integer[] out = new Integer[2];
		out[0] = this.worldX;
		out[1] = this.worldY;
		return out;
	}

	public Level getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(Level currentLevel) {
		this.currentLevel = currentLevel;
	}

	public int getGridSize() {
		return gridSize;
	}

	public void setGridSize(int gridSize) {
		this.gridSize = gridSize;
	}
}

