package com.massey.id16107190.pong;

public class Level {
	
	private Grid[][] grid = {{new Tile_Floor1(), new Tile_Floor1(), new Tile_Floor1(), new Tile_Floor1()}};

	public Grid[][] getGrid() {
		return grid;
	}

	public void setGrid(Grid[][] grid) {
		this.grid = grid;
	}

}
