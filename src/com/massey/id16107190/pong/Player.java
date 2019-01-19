package com.massey.id16107190.pong;

public class Player {
	
	private int health = 100;
	
	private HitBox hitbox = new HitBox(50);

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

}
