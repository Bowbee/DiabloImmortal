package com.massey.id16107190.pong.Spells;

import java.awt.Color;
import java.awt.Image;

import com.massey.id16107190.pong.Entities.EntitySpawner;

public class Spell_1_Fireball extends Spell {
	
	private EntitySpawner es = EntitySpawner.getInstance();

	@Override
	public void trigger() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPosX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPosY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCollision() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setPosX(int x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPosY(int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cast(int x, int y, double angle, boolean isLeft) {
		System.out.println(angle + "" + isLeft);
		es.spawnEntity(1, x, y, 10, angle, 0);
		
	}
	

}
