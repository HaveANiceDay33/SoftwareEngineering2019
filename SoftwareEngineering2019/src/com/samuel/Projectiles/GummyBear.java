package com.samuel.Projectiles;

import com.samuel.Main;
import com.samuel.Player;
import com.samuel.Projectile;

public class GummyBear extends Projectile{
	private static final float size = 70;
	public GummyBear(float x, float y, float initVX, float initVY, Player owner, float rotSpeed) {
		super(Main.getTexture(Main.GUMMY_INDEX), x, y, size, size, initVX, initVY, owner, rotSpeed);
	}
}
