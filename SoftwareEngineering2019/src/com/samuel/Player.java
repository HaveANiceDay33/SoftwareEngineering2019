package com.samuel;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.input.collection.HvlCPG_Gamepad;
import com.osreboot.ridhvl.painter.HvlAnimatedTextureUV;

public class Player {
	static final private float JUMP_POWER = 50;
	static final private float MOVE_SPEED = 20;
	static final private float JUMP_TIMER = 1f;
	static final private float GRAVITY = 100;
	static final private float DRAG = 100;
	static final public float PLAYER_SIZE = 256;
	
	public int id;
	public float fixedX, fixedY, x, y, vx, vy, x1Cont, aCont;
	public float jumpTimer = 0;
	public HvlAnimatedTextureUV standing, moving;
	
	public Player(int id, float fixedX, float fixedY, 
			HvlAnimatedTextureUV standing, HvlAnimatedTextureUV moving){
		this.id = id;
		this.fixedX = fixedX;
		this.fixedY = fixedY;
		this.standing = standing;
		this.moving = moving;
		this.vx = HvlMath.randomIntBetween(-60, 60);
	}
	
	public void update(float delta) {
		this.jumpTimer -= delta;
		this.vy -= GRAVITY * delta;
		this.vx = HvlMath.stepTowards(this.vx, DRAG * delta, 0);
		this.x += this.vx;
		this.y += this.vy;
		this.x1Cont = Controllers.joy1x[this.id];
		this.aCont = Controllers.allA[this.id];
		if(this.x1Cont > 0)
			this.vx = -MOVE_SPEED * Math.abs(this.x1Cont);
		if(this.x1Cont < 0)
			this.vx = MOVE_SPEED * Math.abs(this.x1Cont);
		if(this.aCont == 1 && this.jumpTimer <= 0){
			this.vy = JUMP_POWER; 
			this.jumpTimer = JUMP_TIMER;
		}
		updateBorderCollisions(Game.BACK_Y-2160, -720, Game.BACK_X/2-1920, -Game.BACK_X/2+1920);
		hvlDrawQuadc(this.fixedX, this.fixedY, this.vx <= 0 ? -PLAYER_SIZE : PLAYER_SIZE, PLAYER_SIZE,
				vx == 0 ? this.standing : this.moving);
	}
	
	public void updateBorderCollisions(int top, int bottom, int right, int left) {
		if(this.y < bottom) {this.y = bottom;} // bottom world border 
		if(this.y > top) {this.y = top;}  //top world border 
		if(this.x < left) {this.x = left;} // left world border 
		if(this.x > right) {this.x = right;} // right world border
	}
	
	public HvlAnimatedTextureUV get_animation() {
		if(vx == 0) {
			return this.standing;
		}else {
			return this.moving;
		}
	}
	
	public float get_width() {
		if(this.vx <= 0) {return -PLAYER_SIZE;}
		else {return PLAYER_SIZE;}
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
