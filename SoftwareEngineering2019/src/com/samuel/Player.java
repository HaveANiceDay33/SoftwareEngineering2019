package com.samuel;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;

public class Player {
	public int id, up, down, left, right;
	public float x;
	public float y;
	public Color color;
	public Player(int id, float x, float y, Color color){
		this.id = id;
		this.x = x;
		this.y = y;
		this.color = color;
		if(id == 0) {
			up = 17;
			down = 31;
			left = 32;
			right = 30;
		}
	}
	
	public void updateControls(int upKey, int downKey, int rightKey, int leftKey, float delta) {
		if(Keyboard.isKeyDown(rightKey)){
			this.x -= 400 * delta;	
		}
		if(Keyboard.isKeyDown(leftKey)){
			this.x += 400 * delta;	
		}
		if(Keyboard.isKeyDown(downKey)){
			this.y += 400 * delta;
		}
		if(Keyboard.isKeyDown(upKey)){
			this.y -= 400 * delta;
		}
	}
	
	public void update(float delta) {
		updateControls(up, down, right, left, delta);
		hvlDrawQuadc(x, y, 50, 50, color);
	}
	
	public double get_x() {
		return this.x;
	}
	public double get_y() {
		return this.y;
	}
}
