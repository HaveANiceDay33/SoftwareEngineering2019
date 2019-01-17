package com.samuel;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;

import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.HvlMath;

public class Projectile {
	public Texture pro;
	public float x, y, sizeX, sizeY, actX, actY, vx, vy;
	public Player owner;
	public Projectile(Texture pro, float x, float y, float sizeX, float sizeY, float initVX, Player owner) {
		this.pro = pro;
		this.x = x;
		this.y = y;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.vx = initVX;
		this.vy = -100;
		this.owner = owner;
	}
	
	public void update(float delta, float xPlay, float yPlay) {
		actX = this.x + xPlay + Game.FIXED_X;
		actY = this.y + yPlay + Game.FIXED_Y;
		this.vy += Game.GRAVITY/100 * delta;
		this.vx = HvlMath.stepTowards(this.vx, Game.DRAG/1000*delta, 0);
		//this.x += this.vx * delta; //positions are modified by velocities
		//this.y += this.vy * delta;
		//updateElementCollisions();
		//updateBorderCollisions(Game.BORDER_TOP, Game.BORDER_BOTTOM, Game.BORDER_LEFT, Game.BORDER_RIGHT);
		hvlDrawQuadc(actX, actY, this.sizeX, this.sizeY, this.pro);
	}
	/*
	private void updateBorderCollisions(int top, int bottom, int left, int right) {
		if(this.y < bottom) {this.y = bottom; this.vy = 0;} // bottom world border 
		if(this.y > top) {this.y = top; this.vy = 0;}  //top world border
		if(this.x > left) {this.x = left;} // left world border 
		if(this.x < right) {this.x = right;} // right world border
	}
	
	private WorldElement closestElement() {
		WorldElement closest = null;
		for(WorldElement e : MenuManager.currentLevel.elements) {
			if(closest == null) {
				closest = e;
			}
			float distance = HvlMath.distance(Game.FIXED_X, Game.FIXED_Y, closest.actX, closest.actY);
			float distanceTest = HvlMath.distance(Game.FIXED_X, Game.FIXED_Y, e.actX, e.actY);
			if(distanceTest < distance) {
				closest = e;
			}
		}
		return closest;
	}
	
	private void updateElementCollisions() {
		WorldElement closest = closestElement();
		//CHECKING PLATFORM COLLISIONS
		if(closest instanceof Platform) {
			if(this.y < -(closest.y + 32) + this.sizeX/2 && this.y > -(closest.y - 32) - this.sizeX/2 && 
					((this.x + (this.sizeX/4) > closest.x - closest.sizeX*32 && this.x - (this.sizeX/4) < closest.x + closest.sizeX*32) || 
							(this.x - (this.sizeX/4) < -(closest.x - closest.sizeX*32) && this.x + (this.sizeX/4) > -(closest.x + closest.sizeX*32)) )){
				if(this.vy < 0) {
					this.vy = 0;
					this.y = -(closest.y + 32) + this.sizeX/2;
				} else if (this.vy > 0) {
					this.vy = -1;
					this.y = -(closest.y - 32) -this.sizeX/2;
				}
			}
		}
	}
	*/
}
