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

public class Cannon extends Weapon {
	private static int size = 50;
	private static int launchSpeed = 250;
	public Cannon(float x, float y) {
		super(Main.getTexture(Main.SLING_INDEX), x, y, size*3, size);
	}
	
	public void fire(Player owner) {
		/*
		if(Main.options.soundEffectsEnabled) {
			Main.getSound(Main.COCONUT_LAUNCH_INDEX).playAsSoundEffect(1, 1, false);
		}
		*/
		MoonCheese cheese = new MoonCheese((owner.vx <= 0 ? -(owner.x - this.sizeX) : -(owner.x + this.sizeX)), this.weapY, owner.vx <= 0 ? launchSpeed - owner.vx/10 : -launchSpeed - owner.vx/10, owner);
		MenuManager.currentLevel.projs.add(cheese);
	}
}