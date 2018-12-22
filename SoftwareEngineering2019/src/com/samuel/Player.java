package com.samuel;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.input.collection.HvlCPG_Gamepad;

public class Player {
	static final private float JUMP_POWER = 50;
	static final private float MOVE_SPEED = 20;
	static final private float JUMP_TIMER = 1f;
	static final private float GRAVITY = 100;
	static final private float DRAG = 100;
	static final private float PLAYER_SIZE = 256;
	
	public int id;
	public float fixedX, fixedY, x, y, vx, vy, x1Cont, aCont;
	public float jumpTimer = 0;
	public Color color;
	
	public Player(int id, float fixedX, float fixedY, Color color){
		this.id = id;
		this.color = color;
		this.fixedX = fixedX;
		this.fixedY = fixedY;
		this.vx = HvlMath.randomIntBetween(-60, 60);
	}
	
	public void update(float delta) {
		this.jumpTimer -= delta;
		this.vy -= GRAVITY * delta;
		this.vx = HvlMath.stepTowards(this.vx, DRAG * delta, 0);
		this.x += this.vx;
		this.y += this.vy;
		this.x1Cont = Controllers.conts.getValue(HvlCPG_Gamepad.JOY1X, this.id);
		this.aCont = Controllers.conts.getValue(HvlCPG_Gamepad.BUTTON_A, this.id);
		if(this.x1Cont > 0)
			this.vx = -MOVE_SPEED * Math.abs(this.x1Cont);
		if(this.x1Cont < 0)
			this.vx = MOVE_SPEED * Math.abs(this.x1Cont);
		if(this.aCont == 1 && this.jumpTimer <= 0){
			this.vy = JUMP_POWER; 
			this.jumpTimer = JUMP_TIMER;
		}
		updateBorderCollisions(Game.BACK_Y-2160, -720, Game.BACK_X/2-1920, -Game.BACK_X/2+1920);
		hvlDrawQuadc(this.fixedX, this.fixedY, this.vx <= 0 ? -PLAYER_SIZE : PLAYER_SIZE, PLAYER_SIZE, vx == 0 ? Main.blueStanding : Main.blueRunning);
	}
	
	public void updateBorderCollisions(int top, int bottom, int right, int left) {
		if(this.y < bottom) {this.y = bottom;} // bottom world border 
		if(this.y > top) {this.y = top;}  //top world border 
		if(this.x < left) {this.x = left;} // left world border 
		if(this.x > right) {this.x = right;} // right world border
	}
	
	public float get_x() {
		return this.x;
	}
	
	public float get_y() {
		return this.y;
	}
	
	public void set_x(int xArg) {
		this.x = xArg;
	}
	
	public void set_y(int yArg) {
		this.y = yArg;
	}
}
