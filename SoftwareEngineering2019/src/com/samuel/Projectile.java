package com.samuel;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlResetRotation;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlRotate;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class Projectile {
	public Texture pro;
	public float x, y, sizeX, sizeY, actX, actY, vx, vy, xMod=0, yMod=0, rot = 0, rotSpeed = 3;
	public Player owner;
	public float drag = Game.DRAG/1000;
	public Projectile(Texture pro, float x, float y, float sizeX, float sizeY, float initVX, Player owner) {
		this.pro = pro;
		this.x = x;
		this.y = y;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.vx = initVX;
		this.owner = owner;
		this.vy = -100;
	}
	
	public void update(float delta) {
		this.vy += Game.GRAVITY/100 * delta;
		this.vx = HvlMath.stepTowards(this.vx, this.drag*delta, 0);
		this.xMod += this.vx * delta; //positions are modified by velocities
		this.yMod += this.vy * delta;
		this.rot += rotSpeed;
	}
	
	public void draw(float xPlay, float yPlay, float delta) {
		this.update(delta);
		actX = this.x + xPlay + Game.FIXED_X + xMod;
		actY = this.y + yPlay + Game.FIXED_Y + yMod;
		updateElementCollisions();
		updateBorderCollisions(560);
		hvlRotate(actX,  actY, this.rot);
		hvlDrawQuadc(actX, actY, this.sizeX, this.sizeY, this.pro);
		hvlResetRotation();
	}
	
	private void updateBorderCollisions(int bottom) {
		if((this.y+this.yMod) > bottom) {this.yMod = 0; this.y = bottom; this.drag = Game.DRAG/100; this.vy = 0; rotSpeed = 0;} // bottom world border 
	}
	
	private WorldElement closestElement() {
		WorldElement closest = null;
		for(WorldElement e : MenuManager.currentLevel.elements) {
			if(closest == null) {
				closest = e;
			}
			float distance = HvlMath.distance(this.actX, this.actY, closest.actX, closest.actY);
			float distanceTest = HvlMath.distance(this.actX, this.actY, e.actX, e.actY);
			if(distanceTest < distance) {
				closest = e;
			}
			
		}
		return closest;
	}
	
	private void updateElementCollisions() {
		WorldElement closest = closestElement();
		//System.out.println(this.actY + "\t" + closest.actY);
		//CHECKING PLATFORM COLLISIONS
		if(closest instanceof Platform) {
			if((this.actX + (this.sizeX/4) > closest.actX - closest.sizeX*32 && this.actX - (this.sizeX/4) < closest.actX + closest.sizeX*32 || 
					this.actX - (this.sizeX/4) < -(closest.actX - closest.sizeX*32) && this.actX + (this.sizeX/4) > -(closest.actX + closest.sizeX*32))
					&& this.actY > closest.actY - 32 && this.actY < closest.actY + 32) {
				if(this.vy < 0) {
					this.vy = 0;
					this.drag = Game.DRAG/100;
					this.actY = closest.actY + 32 + this.sizeY/2;
					rotSpeed = 0;
				} else if (this.vy > 0) {
					this.vy = 0;
					this.drag = Game.DRAG/10;
					this.actY = closest.actY - 32 - this.sizeY/2;
					rotSpeed = 0;
				}
			}
		}
	}
	
	public void remove() {
		MenuManager.currentLevel.projs.remove(this);
	}
	
}
