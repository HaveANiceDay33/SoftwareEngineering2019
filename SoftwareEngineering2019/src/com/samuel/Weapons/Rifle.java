package com.samuel.Weapons;

import com.samuel.Main;
import com.samuel.MenuManager;
import com.samuel.Player;
import com.samuel.Weapon;
import com.samuel.Projectiles.Carrot;
/**
 * <p>This class has an overriding method for firing its weapon and provides parameters to the Weapon parent class for drawing.
 * @author Samuel Munro
 *
 */
public class Rifle extends Weapon{
	private static int size = 50;
	private static int launchSpeedX = 600;
	private static int launchSpeedY = -20;
	private static int rotRate = 0;
	public Rifle(float x, float y) {
		super(Main.getTexture(Main.RIFLE_INDEX), x, y, size*3, size);
	}
	
	public void fire(Player owner) {
		
		if(Main.options.soundEffectsEnabled) {
			Main.getSound(Main.CARROT_SHOT_INDEX).playAsSoundEffect(1, 1, false);
		}
		//spawn a dedicated projectile when fired
		Carrot carrot = new Carrot((owner.vx <= 0 ? -(owner.x - this.sizeX) : -(owner.x + this.sizeX)), this.weapY, owner.vx <= 0 ? launchSpeedX - owner.vx/10 : -launchSpeedX - owner.vx/10, launchSpeedY, owner, rotRate);
		MenuManager.currentLevel.projs.add(carrot);
	}
}

