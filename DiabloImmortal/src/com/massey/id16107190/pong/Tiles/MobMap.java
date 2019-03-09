/*
#################################################

  Thomas Debenham - id 16107190 - Assessment 2

#################################################
*/

package com.massey.id16107190.pong.Tiles;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;


public final class MobMap{
	
	
		 
    private static MobMap INSTANCE;
    Map<Color, Integer> mMap = new HashMap<Color, Integer>();
    MobMap() {
    	putItem(new Color(255,0,0), 2);
    	putItem(new Color(0,0,255), 3);
    }
	     
    public static MobMap getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new MobMap();
        }
	         
        return INSTANCE;
    }
	
    
    public void putItem(Color key, Integer something){
    	mMap.put(key, something);
    }
    
    public Integer getItem(Color key){
    	if(mMap.get(key)!= null){
    		return mMap.get(key);
    	}
    	else{
    		return 0;
    	}
    	
    }

}
