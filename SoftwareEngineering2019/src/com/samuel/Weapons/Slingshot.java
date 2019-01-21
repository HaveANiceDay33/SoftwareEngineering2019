package com.samuel.Weapons;

import org.lwjgl.opengl.Display;

import com.samuel.Main;
import com.samuel.MenuManager;
import com.samuel.Player;
import com.samuel.Projectile;
import com.samuel.Weapon;
import com.samuel.Projectiles.Coconut;
import com.samuel.Projectiles.Pinapple;

public class Slingshot extends Weapon {
	private static int size = 72;
	private static int launchSpeedX = 250;
	private static int launchSpeedY = -120;
	private static int rotRate = 3;
	public Slingshot(float x, float y) {
		super(Main.getTexture(Main.SLING_INDEX), x, y, size, size);
	}
	
	public void fire(Player owner) {
		/*
		if(Main.options.soundEffectsEnabled) {
			Main.getSound(Main.COCONUT_LAUNCH_INDEX).playAsSoundEffect(1, 1, false);
		}
		*/
		Projectile pine = new Pinapple((owner.vx <= 0 ? -(owner.x - this.sizeX) : -(owner.x + this.sizeX)), this.weapY, owner.vx <= 0 ? launchSpeedX - owner.vx/10 : -launchSpeedX - owner.vx/10, launchSpeedY, owner, rotRate);
		MenuManager.currentLevel.projs.add(pine);
	}
}
