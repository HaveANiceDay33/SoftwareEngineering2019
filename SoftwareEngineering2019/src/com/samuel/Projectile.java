package com.samuel;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlResetRotation;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlRotate;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;


/**
 * <p>This class handles all physics for the launched projectiles. It draws them, 
 * handles interaction with borders and platforms, and removes them when necessary</p>
 * 
 * @author Samuel Munro
 *
 */
public class Projectile {
	public Texture pro;
	public float x, y, sizeX, sizeY, actX, actY, vx, vy, xMod=0, yMod=0, rot = 0, rotSpeed;
	public Player owner;
	public float drag = Game.DRAG/1000;
	
	/**
	 * <p>Called when a projectile is spawned. The values here onward are modified by the in-game physics. </p>
	 * 
	 * @param pro
	 * @param x
	 * @param y
	 * @param sizeX
	 * @param sizeY
	 * @param initVX
	 * @param initVY
	 * @param owner
	 * @param rotSpeed
	 */
	public Projectile(Texture pro, float x, float y, float sizeX, float sizeY, float initVX, float initVY, Player owner, float rotSpeed) {
		this.pro = pro;
		this.x = x;
		this.y = y;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.vx = initVX;
		this.vy = initVY;
		this.owner = owner;
		this.rotSpeed = rotSpeed;
	}
	
	public void update(float delta) {
		this.vy += Game.GRAVITY/100 * delta;
		this.vx = HvlMath.stepTowards(this.vx, this.drag*delta, 0);
		this.xMod += this.vx * delta; //positions are modified by velocities
		this.yMod += this.vy * delta;
		this.rot += this.rotSpeed;
	}
	
	/**
	 * <p>Using the same relativity formula as platforms and weapons as a base, projectiles add on a movement variable 
	 * to move them through the world. This is handled in the update method, along with border and platform collisions.</p>
	 * @param xPlay
	 * @param yPlay
	 * @param delta
	 */
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
	
	//checks to make sure projectiles do not fall through map
	private void updateBorderCollisions(int bottom) {
		if((this.y+this.yMod) > bottom) {this.yMod = 0; this.y = bottom; this.drag = Game.DRAG/100; this.vy = 0; rotSpeed = 0;} // bottom world border 
	}
	
	//checks for closest platform
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
	
	//uses closest platform and checks collision with it.
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
	
	//removes item from memory
	public void remove() {
		MenuManager.currentLevel.projs.remove(this);
	}
	
}
