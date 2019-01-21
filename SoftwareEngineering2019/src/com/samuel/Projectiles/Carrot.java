package com.samuel.Projectiles;

import com.samuel.Main;
import com.samuel.Player;
import com.samuel.Projectile;

public class Carrot extends Projectile{
	private static final float size = 150;
	public Carrot(float x, float y, float initVX, float initVY, Player owner, float rotSpeed) {
		super(Main.getTexture(Main.CARROT_INDEX), x, y, (initVX >= 0 ? size : -size), size/4, initVX, initVY, owner, rotSpeed);
	}
}
