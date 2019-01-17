package com.samuel;

import org.newdawn.slick.opengl.Texture;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;

public class Weapon {
	public Texture weapon;
	public float x, y, sizeX, sizeY, actX, actY;
	public boolean onPlayer;
	public int ammo;
	public float vx;
	public Weapon(Texture weapon, float x, float y, float sizeX, float sizeY) {
		this.weapon = weapon;
		this.x = x;
		this.y = y;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		onPlayer = false;
		this.ammo = 3;
		this.vx = 0;
	}
	
	public void draw(float xPlay, float yPlay) { //on ground
		actX = this.x + xPlay + Game.FIXED_X;
		actY = this.y + yPlay + Game.FIXED_Y;
		this.vx = 0;
		hvlDrawQuadc(actX, actY, this.sizeX, this.sizeY, this.weapon);
	}
	
	public void draw(float xPlay, float yPlay, float vx) { //on other players 
		this.x = xPlay;
		this.y = yPlay;
		this.vx = vx;
		hvlDrawQuadc((vx <= 0 ? this.x+this.sizeX/2 : this.x-this.sizeX/2), this.y, (vx <= 0 ? this.sizeX : -this.sizeX), this.sizeY, this.weapon);
	}
	
	public void draw(float vx) { // on player
		this.x = Game.FIXED_X;
		this.y = Game.FIXED_Y;
		this.vx = vx;
		hvlDrawQuadc((vx <= 0 ? this.x+this.sizeX/2 : this.x-this.sizeX/2), this.y, (vx <= 0 ? this.sizeX : -this.sizeX), this.sizeY, this.weapon);
	}
	
	public void fire() {System.out.println("ADD A FIRE METHOD TO PROJECTILE!");}
	
	public void remove() {
		MenuManager.currentLevel.weapons.remove(this);
	}
}
