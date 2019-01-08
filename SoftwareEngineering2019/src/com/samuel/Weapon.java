package com.samuel;

import org.newdawn.slick.opengl.Texture;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;

public class Weapon {
	public Texture weapon;
	public float x, y, sizeX, sizeY;
	public boolean onPlayer;
	public Weapon(Texture weapon, float x, float y, float sizeX, float sizeY) {
		this.weapon = weapon;
		this.x = x;
		this.y = y;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		onPlayer = false;
	}
	
	public void fire(Projectile shot, float delta) {
		
	}
	
	public void draw(float xPlay, float yPlay) {
		float actX = this.x + xPlay + Game.FIXED_X;
		float actY = this.y + yPlay + Game.FIXED_Y;
		hvlDrawQuadc(actX, actY, this.sizeX, this.sizeY, this.weapon);
	}
	
	public void draw(float vx) {
		this.x = Game.FIXED_X;
		this.y = Game.FIXED_Y;
		hvlDrawQuadc(this.x, (vx > 0 ? this.y : -this.y), this.sizeX, this.sizeY, this.weapon);
	}
	
}
