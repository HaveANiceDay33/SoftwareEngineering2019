package com.samuel.Projectiles;

import com.samuel.Main;
import com.samuel.Player;
import com.samuel.Projectile;
/**
 * <p>This class takes a variety of arguments from the weapon that fires it and passes that up to the parent class Projectile.</p>
 * @author Samuel Munro
 *
 */
public class GummyBear extends Projectile{
	private static final float size = 70;
	/**
	 * 
	 * @param x
	 * @param y
	 * @param initVX
	 * @param initVY
	 * @param owner
	 * @param rotSpeed
	 */
	public GummyBear(float x, float y, float initVX, float initVY, Player owner, float rotSpeed) {
		super(Main.getTexture(Main.GUMMY_INDEX), x, y, size, size, initVX, initVY, owner, rotSpeed);
	}
}
