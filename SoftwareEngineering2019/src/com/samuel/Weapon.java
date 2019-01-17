package com.samuel;

import org.newdawn.slick.opengl.Texture;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;

public class Weapon {
	public Texture weapon;
	public float weapX, weapY, sizeX, sizeY, actX, actY;
	public boolean onPlayer;
	public int ammo;
	public Weapon(Texture weapon, float x, float y, float sizeX, float sizeY) {
		this.weapon = weapon;
		this.weapX = x;
		this.weapY = y;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		onPlayer = false;
		this.ammo = 3;
	}
	
	public void draw(float xPlay, float yPlay) { //on ground
		actX = this.weapX + xPlay + Game.FIXED_X;
		actY = this.weapY + yPlay + Game.FIXED_Y;
		hvlDrawQuadc(actX, actY, this.sizeX, this.sizeY, this.weapon);
	}
	
	public void drawOther(float xPlay, float yPlay, float vx) { //on other players 
		this.weapX = xPlay;
		this.weapY = yPlay;
		hvlDrawQuadc((vx <= 0 ? this.weapX+this.sizeX/2 : this.weapX-this.sizeX/2), this.weapY, (vx <= 0 ? this.sizeX : -this.sizeX), this.sizeY, this.weapon);
	}

	public void drawOn(float pvx) {
		hvlDrawQuadc((pvx <= 0 ? Game.FIXED_X+this.sizeX/2 : Game.FIXED_X-this.sizeX/2), Game.FIXED_Y, (pvx <= 0 ? this.sizeX : -this.sizeX), this.sizeY, this.weapon);
	}
	
	public void fire(Player owner) {System.out.println("ADD A FIRE METHOD TO PROJECTILE!");}
	
	public void remove() {
		MenuManager.currentLevel.weapons.remove(this);
	}
}
