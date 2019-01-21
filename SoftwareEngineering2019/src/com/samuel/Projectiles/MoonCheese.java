package com.samuel.Projectiles;

import com.samuel.Main;
import com.samuel.Player;
import com.samuel.Projectile;

public class MoonCheese extends Projectile{
	private static final float size = 100;
	public MoonCheese(float x, float y, float initVX, float initVY, Player owner, float rotSpeed) {
		super(Main.getTexture(Main.CHEESE_INDEX), x, y, size, size, initVX, initVY, owner, rotSpeed);
	}
}
