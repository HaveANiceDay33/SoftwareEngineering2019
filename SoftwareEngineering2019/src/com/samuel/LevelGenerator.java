package com.samuel;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;

import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.samuel.LevelProfiles.Battlefield;

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
	}
}
