/*
#################################################

  Thomas Debenham - id 16107190 - Assessment 2

#################################################
*/

package com.massey.id16107190.pong;

public class Level {
	
	private Grid[][] grid = {};
	private Integer[][] mobs = {};

	public Level(Grid[][] newGrid) {
		this.grid = newGrid;
	}

	public Grid[][] getGrid() {
		return grid;
	}

	public void setGrid(Grid[][] grid) {
		this.grid = grid;
	}
	
	public Integer[][] getMobs() {
		return mobs;
	}

	public void setMobs(Integer[][] mobs) {
		this.mobs = mobs;
	}

}
