/*
#################################################

  Thomas Debenham - id 16107190 - Assessment 2

#################################################
*/

package com.massey.id16107190.pong.Tiles;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.massey.id16107190.pong.Grid;
import com.massey.id16107190.pong.Level;
import com.massey.id16107190.pong.LevelController;
import com.massey.id16107190.pong.PaintEngine;

public class TileConverter {
	final static File folder = new File("./Levels/");
	
	static List<File> filePaths = new ArrayList<>();
	private static LevelController lc = LevelController.getInstance();
	private static PaintEngine paint = PaintEngine.getInstance();
	private static ColourMap cMap = new ColourMap();

	public static void listFilesForFolder(final File folder) {
	    for (final File fileEntry : folder.listFiles()) {
            System.out.println(fileEntry.getName());
            filePaths.add(fileEntry);
	    }
	}
	
   private static Integer[][] convertTo2D(BufferedImage image) {
      int width = image.getWidth();
      int height = image.getHeight();
      Integer[][] result = new Integer[height][width];

      for (int row = 0; row < height; row++) {
         for (int col = 0; col < width; col++) {
            result[row][col] = image.getRGB(col, row);
         }
      }

      return result;
   }



	public static void run(){
		
		listFilesForFolder(folder);
		List<Integer[][]> grid = new ArrayList<Integer[][]>();
		for(int i = 0; i < filePaths.size(); i++){
			try{
				BufferedImage img = ImageIO.read(filePaths.get(i));
				Integer[][] result = convertTo2D(img);
				grid.add(result);
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		System.out.println(grid.get(1));
		for(Integer[][] level : grid){
			Grid[][] newGrid = new Grid[level.length][level[0].length];
			for(int j = 0; j < level.length; j++){
				for(int k = 0; k < level[j].length; k++){
					Color c = new Color(level[j][k], true);
					//System.out.println(c);
					//System.out.println(c.getAlpha());
					
					Grid tile = cMap.getItem(c);
					if(c.getAlpha() == 0 || tile == null){
						newGrid[j][k] = null;
						
					}
					else{
						newGrid[j][k] = tile;
					}
				}
			}
			Level newLevel = new Level(newGrid);
			lc.levelList.add(newLevel);
		}
		
		MobConverter.run();
		lc.nextLevel(0);
		paint.nextLevel();

	}
	
	

}
