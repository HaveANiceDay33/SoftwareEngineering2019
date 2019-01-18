package com.samuel.Projectiles;

import com.samuel.Main;
import com.samuel.Player;
import com.samuel.Projectile;

public class Coconut extends Projectile{
	private static final float size = 64;
	public Coconut(float x, float y, float initVX, Player owner) {
		super(Main.getTexture(Main.COCONUT_INDEX), x, y, size, size, initVX, owner);
	}
}