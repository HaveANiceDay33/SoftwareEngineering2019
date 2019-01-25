package com.samuel;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;

import org.lwjgl.opengl.Display;

/**
 * 
 * @author Samuel Munro
 * 
 * <p>Responsible for drawing the platforms in game, extending a class 
 * that was supposed to be used for further platforms if there was more time <p>
 *
 */
public class Platform extends WorldElement{
	/**
	 * 
	 * <p>Platform constructor. Takes a position, size, and texture argument 
	 * for determining platform location and look inside the game. </p>
	 * 
	 * @param x
	 * @param y
	 * @param sizeX
	 * @param textureIndex
	 */
	public Platform(float x, float y, float sizeX, int textureIndex) {
		super(x, y, Main.getTexture(textureIndex));
		this.sizeY = 1;
		this.sizeX = sizeX;
		wordOn = false;
	}
	
	/**
	 * <p>Draws the platform with the desired attributes. <code>actX</code> 
	 * and <code>actY</code> are determined by the spawn location, the player's 
	 * location, and their fixed position on screen. This accomplishes relativity</p>
	 * 
	 * @param xPlay
	 * @param yPlay
	 */
	public void draw(float xPlay, float yPlay) {
		actX = this.x + xPlay + Game.FIXED_X;
		actY = this.y + yPlay + Game.FIXED_Y;
		hvlDrawQuadc(actX, actY, this.sizeX*64, this.sizeY*64, 0, 0, this.sizeX, this.sizeY, this.staticTexture);
	}
}
