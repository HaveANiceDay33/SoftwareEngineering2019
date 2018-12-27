package com.samuel;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.input.collection.HvlCPG_Gamepad;
import com.osreboot.ridhvl.painter.HvlAnimatedTextureUV;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class Player {
	static final private float JUMP_POWER = 3000;
	static final private float MOVE_SPEED = 900;
	static final private float JUMP_TIMER = 1f;
	static final private float GRAVITY = 100;
	static final private float TERMINAL_VELOCITY = -4000;
	static final private float DRAG = 50;
	static final public float PLAYER_SIZE = 256;
	
	public int id, cont;
	public float fixedX, fixedY, x, y, vx, vy, x1Cont, aCont;
	public float jumpTimer = 0;
	public HvlAnimatedTextureUV standing, moving;
	
	public Player(int id, int cont, float fixedX, float fixedY, 
			HvlAnimatedTextureUV standing, HvlAnimatedTextureUV moving){
		this.id = id;
		this.cont = cont;
		this.fixedX = fixedX;
		this.fixedY = fixedY;
		this.standing = standing;
		this.moving = moving;
		this.vx = HvlMath.randomIntBetween(-2700, 2700);
	}
	
	public void update(float delta) {
		this.jumpTimer -= delta;
		if(this.vy > TERMINAL_VELOCITY) {
			this.vy -= GRAVITY;
		}
		this.vx = HvlMath.stepTowards(this.vx, DRAG, 0);
		this.x += this.vx * delta;
		this.y += this.vy * delta;
		updateElementCollisions();
		this.x1Cont = Controllers.joy1x[this.cont];
		this.aCont = Controllers.allA[this.cont];
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
	
	public void updateBorderCollisions(int top, int bottom, int left, int right) {
		if(this.y < bottom) {this.y = bottom;} // bottom world border 
		if(this.y > top) {this.y = top;}  //top world border 
		if(this.x > left) {this.x = left;} // left world border 
		if(this.x < right) {this.x = right;} // right world border
	}
	public void updateElementCollisions() {
		WorldElement closest = null;
		for(WorldElement e : MenuManager.currentLevel.elements) {
			if(closest == null) {
				closest = e;
			}
			float distance = HvlMath.distance(Display.getWidth()/2, Display.getHeight()/2, closest.actX, closest.actY);
			float distanceTest = HvlMath.distance(Display.getWidth()/2, Display.getHeight()/2, e.actX, e.actY);
			if(distanceTest < distance) {
				closest = e;
			}
		}
		//CHECKING PLATFORM COLLISIONS
		if(closest instanceof Platform) {
			if(this.y < -(closest.y + 32) + PLAYER_SIZE/2 && this.y > -(closest.y - 32) - PLAYER_SIZE/2 && 
					((this.x > closest.x - closest.sizeX*32 && this.x < closest.x + closest.sizeX*32) || (this.x < -(closest.x - closest.sizeX*32) && this.x > -(closest.x + closest.sizeX*32)) )){
				if(this.vy < 0) {
					this.vy = 0;
					this.y = -(closest.y + 32) + PLAYER_SIZE/2;
				}else {
					this.vy = -1;
					this.y = -(closest.y - 32) -PLAYER_SIZE/2;
				}
			}
		}
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
