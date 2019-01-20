package com.samuel.Weapons;

import org.lwjgl.opengl.Display;

import com.samuel.Main;
import com.samuel.MenuManager;
import com.samuel.Player;
import com.samuel.Projectile;
import com.samuel.Weapon;
import com.samuel.Projectiles.Coconut;

public class Launcher extends Weapon {
	private static int size = 128;
	private static int launchSpeed = 200;
	public Launcher(float x, float y) {
		super(Main.getTexture(Main.LAUNCHER_INDEX), x, y, size, size);
	}
	
	public void fire(Player owner) {
		if(Main.options.soundEffectsEnabled) {
			Main.getSound(Main.COCONUT_LAUNCH_INDEX).playAsSoundEffect(1, 1, false);
		}
		
		Projectile coco = new Coconut((owner.vx <= 0 ? -(owner.x - this.sizeX) : -(owner.x + this.sizeX)), this.weapY, owner.vx <= 0 ? launchSpeed - owner.vx/10 : -launchSpeed - owner.vx/10, owner);
		MenuManager.currentLevel.projs.add(coco);
	}
}
