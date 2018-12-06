package com.samuel;

import org.newdawn.slick.Color;

public class Game {
	public static Player player1, player2, player3, player4;
	public static void initGame() {
		player1 = new Player(0, 960, 540, Color.blue);
	}
	public static void updateGame(float delta) {
		player1.update(delta);
	}
}
