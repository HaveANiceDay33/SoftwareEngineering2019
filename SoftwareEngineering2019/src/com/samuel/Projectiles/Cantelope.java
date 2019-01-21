package com.samuel.Projectiles;

import com.samuel.Main;
import com.samuel.Player;
import com.samuel.Projectile;

public class Cantelope extends Projectile{
	private static final float size = 100;
	public Cantelope(float x, float y, float initVX, float initVY, Player owner, float rotSpeed) {
		super(Main.getTexture(Main.CANT_INDEX), x, y, (initVX >= 0 ? size : -size), size, initVX, initVY, owner, rotSpeed);
	}
}
