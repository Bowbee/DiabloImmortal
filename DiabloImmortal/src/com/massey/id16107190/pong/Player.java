/*
#################################################

  Thomas Debenham - id 16107190 - Assessment 2

#################################################
*/

package com.massey.id16107190.pong;

import java.util.ArrayList;
import java.util.List;

public class Player {
	
	private int health = 100;
	private List<String> inventory = new ArrayList<>();
	
	private HitBox hitbox = new HitBox(25);

	public HitBox getHitbox() {
		return hitbox;
	}

	public void setHitbox(HitBox hitbox) {
		this.hitbox = hitbox;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public List<String> getInventory() {
		return inventory;
	}

	public void setInventory(List<String> inventory) {
		this.inventory = inventory;
	}
	
	public void addToInventory(String str){
		this.inventory.add(str);
	}
	
	public void removeFromInventory(String str){
		this.inventory.remove(str);
	}

}
