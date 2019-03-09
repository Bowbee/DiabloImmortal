/*
#################################################

  Thomas Debenham - id 16107190 - Assessment 2

#################################################
*/

package com.massey.id16107190.pong.Tiles;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import com.massey.id16107190.pong.Grid;

public final class ColourMap{
		 
    private static ColourMap INSTANCE;
    Map<Color, Grid> mMap = new HashMap<Color, Grid>();
    ColourMap() {
    	putItem(Color.BLACK, new Tile_Wall1());
    	putItem(Color.WHITE, new Tile_Floor1());
    	putItem(new Color(24,24,24), new Tile_Wall_C());
    	putItem(new Color(199,199,199), new Tile_Wall1_180());
    	putItem(new Color(0,255,0), new Tile_Trigger1());
    	putItem(new Color(0,0,255), new Tile_Exit1_1());
    	putItem(new Color(0,0,200), new Tile_Exit1_2());
    	putItem(new Color(0,0,150), new Tile_Exit1_3());
    	putItem(new Color(0,0,100), new Tile_Exit1_4());
    	putItem(new Color(255,255,1), new Tile_Entry0());
    	
    	putItem(new Color(0,255,50), new Tile_Exit2_1());
    	putItem(new Color(0,255,100), new Tile_Exit2_2());
    	putItem(new Color(0,255,150), new Tile_Exit2_3());
    	putItem(new Color(0,255,200), new Tile_Exit2_4());
    }
	     
    public static ColourMap getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ColourMap();
        }
	         
        return INSTANCE;
    }
	
    
    public void putItem(Color key, Grid something){
    	mMap.put(key, something);
    }
    
    public Grid getItem(Color key){
    	return mMap.get(key);
    }

}
