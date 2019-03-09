/*
#################################################

  Thomas Debenham - id 16107190 - Assessment 2

#################################################
*/

package com.massey.id16107190.pong;

import com.massey.id16107190.pong.Tiles.TileConverter;

public class main {
	
	private static ClientSettings cs = ClientSettings.getInstance();

	public static void main(String[] args) {
		
		//Menus m = new Menus();
		TileConverter.run();
		
		Game m = new Game();
		
		m.init();
		GameEngine.createGame(m, cs.getFPS());
		
	}

}
