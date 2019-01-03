package com.samuel;

import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.painter.HvlAnimatedTextureUV;

public class WorldElement {
	public float x;
	public float y;
	public float actX, actY, sizeX, sizeY;
	public Texture staticTexture;
	public boolean wordOn;
	
	public WorldElement(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public WorldElement(float x, float y, Texture staticTexture) {
		this.x = x;
		this.y = y;
		this.staticTexture = staticTexture;
	}
	
	public WorldElement(float x, float y, float vx, float vy, HvlAnimatedTextureUV movingTexture) {
		this.x = x;
		this.y = y;
	}
	
	public void draw(float x, float y) {
		System.out.println("ELEMENT NOT DRAWING");
	}
	
	public float get_x() {
		return this.x;
	}
	
	public float get_y() {
		return this.y;
	}
}
