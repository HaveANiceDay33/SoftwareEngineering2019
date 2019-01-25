package com.samuel.Projectiles;

import com.samuel.Main;
import com.samuel.Player;
import com.samuel.Projectile;
/**
 * <p>This class takes a variety of arguments from the weapon that fires it and passes that up to the parent class Projectile.</p>
 * @author Samuel Munro
 *
 */
public class Pinapple extends Projectile{
	private static final float size = 100;
	/**
	 * 
	 * @param x
	 * @param y
	 * @param initVX
	 * @param initVY
	 * @param owner
	 * @param rotSpeed
	 */
	public Pinapple(float x, float y, float initVX, float initVY, Player owner, float rotSpeed) {
		super(Main.getTexture(Main.PINE_INDEX), x, y, size, size, initVX, initVY, owner, rotSpeed);
	}
}