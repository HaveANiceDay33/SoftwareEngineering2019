package com.samuel;

import org.newdawn.slick.opengl.Texture;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;

/**
 * <p>The weapon class handles drawing weapons spawned by the WeaponManager class. There
 * are multiple draw methods to account for different weapon states.<p>
 * 
 * @author Samuel Munro
 *
 */
public class Weapon {
	public Texture weapon;
	public float weapX, weapY, sizeX, sizeY, actX, actY;
	public boolean onPlayer;
	public int ammo;
	
	/**
	 * <p>The constructor determines how to draw the weapon based on the parameters it is passed</p>
	 * 
	 * @param weapon
	 * @param x
	 * @param y
	 * @param sizeX
	 * @param sizeY
	 */
	public Weapon(Texture weapon, float x, float y, float sizeX, float sizeY) {
		this.weapon = weapon;
		this.weapX = x;
		this.weapY = y;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		onPlayer = false;
		this.ammo = 3;
	}
	
	//called when on the ground.
	public void draw(float xPlay, float yPlay) { //on ground
		actX = this.weapX + xPlay + Game.FIXED_X;
		actY = this.weapY + yPlay + Game.FIXED_Y;
		hvlDrawQuadc(actX, actY, this.sizeX, this.sizeY, this.weapon);
	}
	
	//called when the weapon is on the other players on screen.
	public void drawOther(float xPlay, float yPlay, float vx) { //on other players 
		this.weapX = xPlay;
		this.weapY = yPlay;
		hvlDrawQuadc((vx <= 0 ? this.weapX+this.sizeX/2 : this.weapX-this.sizeX/2), this.weapY, (vx <= 0 ? this.sizeX : -this.sizeX), this.sizeY, this.weapon);
	}

	//called when the weapon is on the current player.
	public void drawOn(float xPlay, float yPlay, float pvx, Player owner) {
		this.weapX = xPlay;
		this.weapY = yPlay;
		hvlDrawQuadc((pvx <= 0 ? Game.FIXED_X+this.sizeX/2 : Game.FIXED_X-this.sizeX/2), Game.FIXED_Y, (pvx <= 0 ? this.sizeX : -this.sizeX), this.sizeY, this.weapon);
	}
	
	public void fire(Player owner) {System.out.println("ADD A FIRE METHOD TO PROJECTILE!");} //method present in all weapons as an overriding method. Check the "Weapons" folder
	
	public void remove() { //removes weapon from memory
		MenuManager.currentLevel.weapons.remove(this);
	}
}
