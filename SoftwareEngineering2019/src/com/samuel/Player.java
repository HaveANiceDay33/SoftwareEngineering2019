package com.samuel;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;

public class Player {
	public int id;
	public float fixedX;
	public float fixedY;
	public Color color;
	public Player(int id, float fixedX, float fixedY, Color color){
		this.id = id;
		this.color = color;
		this.fixedX = fixedX;
		this.fixedY = fixedY;
	}
	public void update(float delta) {
		hvlDrawQuadc(this.fixedX, this.fixedY, 50, 50, this.color);
	}
}
