package com.samuel;

import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.painter.HvlAnimatedTextureUV;

/**
 * <p>This class serves as a framework for platforms and other elements that never were added.</p>
 * 
 * @author Samuel Munro
 *
 */
public class WorldElement {
	public float x;
	public float y;
	public float actX, actY, sizeX, sizeY;
	public Texture staticTexture;
	public boolean wordOn;
	public boolean weaponOn;
	
	public WorldElement(float x, float y) { //for a non-moving element 
		this.x = x;
		this.y = y;
	}
	
	public WorldElement(float x, float y, Texture staticTexture) { //for a non-moving element with a texture.
		this.x = x;
		this.y = y;
		this.staticTexture = staticTexture;
	}
	
	public WorldElement(float x, float y, float vx, float vy, HvlAnimatedTextureUV movingTexture) { //for a moving, animated textured item
		this.x = x;
		this.y = y;
	}
	
	public void draw(float x, float y) { //method that is overridden in the Platform class.
		System.out.println("ELEMENT NOT DRAWING");
	}
	
	public float get_x() {
		return this.x;
	}
	
	public float get_y() {
		return this.y;
	}
}
