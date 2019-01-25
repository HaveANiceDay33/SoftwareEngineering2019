package com.samuel.Weapons;

import org.lwjgl.opengl.Display;

import com.samuel.Main;
import com.samuel.MenuManager;
import com.samuel.Player;
import com.samuel.Projectile;
import com.samuel.Weapon;
import com.samuel.Projectiles.Coconut;
import com.samuel.Projectiles.MoonCheese;
import com.samuel.Projectiles.Pinapple;
/**
 * <p>This class has an overriding method for firing its weapon and provides parameters to the Weapon parent class for drawing.
 * @author Samuel Munro
 *
 */
public class Cannon extends Weapon {
	private static int size = 50;
	private static int launchSpeedX = 250;
	private static int launchSpeedY = -100;
	private static int rotRate = 3;
	public Cannon(float x, float y) {
		super(Main.getTexture(Main.CANNON_INDEX), x, y, size*3, size);
	}
	
	public void fire(Player owner) {
		if(Main.options.soundEffectsEnabled) {
			Main.getSound(Main.CHEESE_SHOT_INDEX).playAsSoundEffect(1, 1, false);
		}
		//spawn a dedicated projectile when fired
		MoonCheese cheese = new MoonCheese((owner.vx <= 0 ? -(owner.x - this.sizeX) : -(owner.x + this.sizeX)), this.weapY, owner.vx <= 0 ? launchSpeedX - owner.vx/10 : -launchSpeedX - owner.vx/10, launchSpeedY, owner, rotRate);
		MenuManager.currentLevel.projs.add(cheese);
	}
}