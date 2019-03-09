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

import com.massey.id16107190.pong.Level;
import com.massey.id16107190.pong.LevelController;
import com.massey.id16107190.pong.Entities.EntitySpawner;

public class MobConverter {
	final static File folder = new File("./Mobs/");
	
	static List<File> filePaths = new ArrayList<>();
	private static EntitySpawner es = EntitySpawner.getInstance();
	private static MobMap mMap = new MobMap();
	private static LevelController lc = LevelController.getInstance();

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
		es.entityList = new ArrayList<>();
		int counter = 0;
		for(Integer[][] level : grid){
			Integer[][] newGrid = new Integer[level.length][level[0].length];
			for(int j = 0; j < level.length; j++){
				for(int k = 0; k < level[j].length; k++){
					Color c = new Color(level[j][k], true);
					//System.out.println(c);
					//System.out.println(c.getAlpha());
					
					int mobID = mMap.getItem(c);
					if(c.getAlpha() == 0 || mobID == 0){
						newGrid[j][k] = null;
						
					}
					else{
						newGrid[j][k] = mobID;
						
					}
				}
			}
			Level lvl = lc.levelList.get(counter);
			if(lvl != null){
				lvl.setMobs(newGrid);
			}
			counter += 1;
			

		}		

	}

}
