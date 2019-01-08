package com.samuel;

import org.newdawn.slick.opengl.Texture;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;

public class LevelGenerator {
	static Level currentLevel;
	static Texture backTexture;
	public static void init() {
		currentLevel = MenuManager.currentLevel;
		backTexture = MenuManager.currentLevel.background;
	}
	public static void update(float delta, float playerX, float playerY) {
		
		hvlDrawQuadc(playerX, playerY, Game.BACK_X, Game.BACK_Y, backTexture);
		//hvlDrawQuadc(playerX + Display.getWidth()/2, playerY + Display.getHeight()/2, 50, 50, Color.red); CENTER OF WORLD INDICATOR FOR LEVEL DESIGN
		for(WorldElement e : currentLevel.elements) {
			e.draw(playerX, playerY);
		}
		for(Word w : currentLevel.words) {
			w.draw(playerX, playerY);
		}
		for(Weapon w : currentLevel.weapons) {
			if(!w.onPlayer) {
				w.draw(playerX, playerY);
			}
		}
	}
}
