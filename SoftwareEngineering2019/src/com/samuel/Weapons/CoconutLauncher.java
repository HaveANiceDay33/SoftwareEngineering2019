package com.samuel.Weapons;

import org.lwjgl.opengl.Display;

import com.samuel.Main;
import com.samuel.MenuManager;
import com.samuel.Player;
import com.samuel.Projectile;
import com.samuel.Weapon;
import com.samuel.Projectiles.Coconut;

public class CoconutLauncher extends Weapon {
	public CoconutLauncher(float x, float y) {
		super(Main.getTexture(Main.LAUNCHER_INDEX), x, y, 128, 128);
	}
	
	public void fire(Player owner) {
		Projectile coco = new Coconut((owner.vx <= 0 ? this.weapX + this.sizeX - Display.getWidth()/2 : this.weapX - this.sizeX - Display.getWidth()/2), this.weapY, owner.vx <= 0 ? 200 : -200, owner);
		MenuManager.currentLevel.projs.add(coco);
	}
}
