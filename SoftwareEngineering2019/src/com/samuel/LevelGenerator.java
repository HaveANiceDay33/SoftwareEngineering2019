package com.samuel;

import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.painter.HvlAnimatedTextureUV;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;

/**
 * 
 * @author Samuel Munro
 * 
 * <p>The Level Generator class updates all in game elements found in the Level Class, as well
 * as drawing the gameplay background.</p>
 *
 */
public class LevelGenerator {
	static Level currentLevel;
	static HvlAnimatedTextureUV backTexture;
	public static void init() {
		currentLevel = MenuManager.currentLevel;
		backTexture = MenuManager.currentLevel.background;
	}
	
	/**
	 * The update method calls all the update/draw calls of in-game elements relative to the player that calls this class.
	 *   
	 * @param delta
	 * @param playerX
	 * @param playerY
	 */
	public static void update(float delta, float playerX, float playerY) {
		
		hvlDrawQuadc(playerX+620, playerY-220, Game.BACK_X, Game.BACK_Y, backTexture);
		//hvlDrawQuadc(playerX + Display.getWidth()/2, playerY + Display.getHeight()/2, 50, 50, Color.red); CENTER OF WORLD INDICATOR FOR LEVEL DESIGN
		for(WorldElement e : currentLevel.elements) {
			e.draw(playerX, playerY);
		}
		for(Word w : currentLevel.words) {
			w.draw(playerX, playerY);
		}
		for(Weapon w : currentLevel.weapons) {
			if(currentLevel.weapons.size() > 0) {
				w.draw(playerX, playerY);
			}	
		}
		for(Projectile p : currentLevel.projs) {
			if(currentLevel.projs.size() > 0) {
				p.draw(playerX, playerY, delta);
			}
		}
	}
}
