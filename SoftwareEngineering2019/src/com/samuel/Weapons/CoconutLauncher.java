package com.samuel.Weapons;

import com.samuel.Game;
import com.samuel.Main;
import com.samuel.MenuManager;
import com.samuel.Projectile;
import com.samuel.Weapon;
import com.samuel.Projectiles.Coconut;

public class CoconutLauncher extends Weapon {
	public CoconutLauncher(float x, float y) {
		super(Main.getTexture(Main.LAUNCHER_INDEX), x, y, 128, 128);
	}
	
	public void fire() {
		Projectile coco = new Coconut((this.vx <= 0 ? Game.FIXED_X + this.sizeX : Game.FIXED_X - this.sizeX), Game.FIXED_Y, this.vx <= 0 ? 200 : -200);
		MenuManager.currentLevel.projs.add(coco);
	}
}
