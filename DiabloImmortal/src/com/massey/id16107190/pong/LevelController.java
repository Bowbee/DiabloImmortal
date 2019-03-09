/*
#################################################

  Thomas Debenham - id 16107190 - Assessment 2

#################################################
*/

package com.massey.id16107190.pong;

import java.util.ArrayList;
import java.util.List;

import com.massey.id16107190.pong.Entities.EntitySpawner;

public final class LevelController{
	 
    private static LevelController INSTANCE; 
    
    private ClientSettings cs = ClientSettings.getInstance();
    private EntitySpawner es = EntitySpawner.getInstance();
    //private PlayerController pc = PlayerController.getInstance();
    
    private int worldX = 0;
    private int worldY = 0;
    
    private int gridSize = 50;
    
    public List<Level> levelList = new ArrayList<>();;
    
    private int levelIndex = 0;
    
    private Level currentLevel;
    private Grid[][] currentGrid;
     
    private LevelController() {

    }
     
    public static LevelController getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new LevelController();
        }
         
        return INSTANCE;
    }
    
    public void nextLevel(int i) {
    	System.out.println(i);
    	currentLevel = levelList.get(i);
    	currentGrid = currentLevel.getGrid();
    	worldY = 0;
    	worldX = 0;

    	es.entityList = new ArrayList<>();
    	for(int j = 0; j < currentLevel.getMobs().length; j++){
    		for(int k = 0; k < currentLevel.getMobs()[j].length; k++){
    			if(currentLevel.getMobs()[j][k] != null){
    				es.spawnEntity(currentLevel.getMobs()[j][k], k*gridSize, j*gridSize, 0, 0, 0);
    			}
    		}
    	}
    	levelIndex = i;

    	
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
    	
    	double gridX = (double) (worldX - xoffset + radius) /-1;
    	double gridY = (double) (worldY - yoffset + radius)*-1;
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

	public int getCurrentLevelNum() {
		return this.levelIndex;
	}
}

