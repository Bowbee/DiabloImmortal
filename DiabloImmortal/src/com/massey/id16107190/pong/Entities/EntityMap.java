/*
#################################################

  Thomas Debenham - id 16107190 - Assessment 2

#################################################
*/

package com.massey.id16107190.pong.Entities;

import java.util.HashMap;
import java.util.Map;

public final class EntityMap{
		 
    private static EntityMap INSTANCE;
    Map<Integer, Entity> mMap = new HashMap<Integer, Entity>();
    EntityMap() {
    	putItem(1, new Entity_1_Fireball());
    	putItem(2, new Entity_2_Target());
    	putItem(3, new Entity_3_Mage());
    	putItem(4, new Entity_4_MageFireball());
    	putItem(5, new Entity_5_Missile());
    }
	     
    public static EntityMap getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new EntityMap();
        }
	         
        return INSTANCE;
    }
	
    
    public void putItem(Integer key, Entity something){
    	mMap.put(key, something);
    }
    
    public Entity getItem(Integer key){
    	return mMap.get(key);
    }

}
