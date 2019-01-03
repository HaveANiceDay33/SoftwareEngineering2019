package com.samuel;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;

import org.lwjgl.opengl.Display;

public class Platform extends WorldElement{
	public Platform(float x, float y, float sizeX) {
		super(x, y, Main.getTexture(Main.CRATE_INDEX));
		this.sizeY = 1;
		this.sizeX = sizeX;
		wordOn = false;
	}
	public void draw(float xPlay, float yPlay) {
		actX = this.x + xPlay + Game.fixedX;
		actY = this.y + yPlay + Game.fixedY;
		hvlDrawQuadc(actX, actY, this.sizeX*64, this.sizeY*64, 0, 0, this.sizeX, this.sizeY, this.staticTexture);
	}
}