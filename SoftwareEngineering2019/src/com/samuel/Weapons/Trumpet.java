package com.samuel.Weapons;

import com.samuel.Main;
import com.samuel.MenuManager;
import com.samuel.Player;
import com.samuel.Projectile;
import com.samuel.Weapon;
import com.samuel.Projectiles.Watermelon;
/**
 * <p>This class has an overriding method for firing its weapon and provides parameters to the Weapon parent class for drawing.
 * @author Samuel Munro
 *
 */
public class Trumpet extends Weapon{
	private static int size = 150;
	private static int launchSpeedX = 280;
	private static int launchSpeedY = -100;
	private static int rotRate = 1;
	public Trumpet(float x, float y) {
		super(Main.getTexture(Main.TRUM_INDEX), x, y, size, size);
	}
	
	public void fire(Player owner) {
		
		if(Main.options.soundEffectsEnabled) {
			Main.getSound(Main.TRUM_SHOT_INDEX).playAsSoundEffect(1, 1, false);
		}
		//spawn a dedicated projectile when fired
		Projectile cant = new Watermelon((owner.vx <= 0 ? -(owner.x - this.sizeX) : -(owner.x + this.sizeX)), this.weapY, owner.vx <= 0 ? launchSpeedX - owner.vx/10 : -launchSpeedX - owner.vx/10, launchSpeedY, owner, rotRate);
		MenuManager.currentLevel.projs.add(cant);
	}
}
