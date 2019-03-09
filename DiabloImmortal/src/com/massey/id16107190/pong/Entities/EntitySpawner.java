/*
#################################################

  Thomas Debenham - id 16107190 - Assessment 2

#################################################
*/

package com.massey.id16107190.pong.Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.massey.id16107190.pong.GameEngine;
import com.massey.id16107190.pong.GameEngine.AudioClip;

public final class EntitySpawner{
	 
    private static EntitySpawner INSTANCE;
    
    public List<Entity> entityList = new ArrayList<>();
    private static EntityMap eMap = new EntityMap();
    private static GameEngine ge = null;
    private Random r = new Random();
    private int lowerBound = 0;
    private int upperBound = 4;
    
    
	
     
    private EntitySpawner() {  
    }
     
    public static EntitySpawner getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new EntitySpawner();
        }
         
        return INSTANCE;
    }
    
    public void spawnEntity(int id, int x, int y, float velocity, double angle, int volume){
    	Class<? extends Entity> cls = (eMap.getItem(id).getClass());
    	Entity e = null;
		try {
			e = cls.newInstance();
		} catch (InstantiationException | IllegalAccessException e1) {
			e1.printStackTrace();
		}
    	if(e != null){
    		e.setPosX(x);
    		e.setPosY(y);
    		e.setVelocity(velocity);
    		e.setAngle(angle);
    		
    		e.setVolume(volume);
        	//System.out.println("e "+e.slope);
    		entityList.add(e);
    		if(ge != null){
    			if(e.getSound().length != 0){
    				int randomIndex = r.nextInt(upperBound - lowerBound)+ lowerBound;
        			AudioClip ac = ge.loadAudio(e.getSound()[randomIndex]);
            		ge.playAudio(ac, e.getVolume()-15);
    			}
    		}
    		
    	}

    }
    public void setGameEngine(GameEngine g){
    	EntitySpawner.ge = g;
    }
    public void playSound(String[] clips, int vol){
    	if(ge != null){
			int randomIndex = r.nextInt(clips.length - 0);
			AudioClip ac = ge.loadAudio(clips[randomIndex]);
    		ge.playAudio(ac, vol-15);
		}
    }
}
