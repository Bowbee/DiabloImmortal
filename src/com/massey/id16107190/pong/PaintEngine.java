package com.massey.id16107190.pong;

public final class PaintEngine{
	 
    private static PaintEngine INSTANCE;
    
	private ClientSettings cs = ClientSettings.getInstance();
	private PlayerController pc = PlayerController.getInstance();
	private LevelController lc = LevelController.getInstance();
	private Level currentLevel = lc.getCurrentLevel();
	private Grid[][] currentGrid = currentLevel.getGrid();
	
	private HitBox playerHitBox = pc.getPlayer().getHitbox();
	
	private int gridSize = lc.getGridSize();
    
    private int middleX = cs.getResX()/2;
    private int middleY = cs.getResY()/2;
     
    private PaintEngine() {  
    }
     
    public static PaintEngine getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new PaintEngine();
        }
         
        return INSTANCE;
    }
    
    public void PaintHitBoxes(GameEngine game) {
    	game.changeColor(game.red);
    	
    	game.drawSolidCircle(middleX, middleY, playerHitBox.getRadius());
    	
    }
	public void PaintLevel(GameEngine game) {
		game.changeColor(game.white);
		
		int xoffset = lc.getWorldX();		
		int xcoord = 0 + xoffset;
		int ycoord = 0 + lc.getWorldY();
		
		for(int y = 0; y < currentGrid.length; y++) {
			for(int x = 0; x < currentGrid[y].length; x++) {
				game.changeColor(currentGrid[y][x].getColor());

				game.drawSolidRectangle(xcoord, ycoord, gridSize, gridSize);
				xcoord += gridSize;
			}
			xcoord = 0 + xoffset;
			ycoord += gridSize;
			
		}
		
		
	
	}
}


