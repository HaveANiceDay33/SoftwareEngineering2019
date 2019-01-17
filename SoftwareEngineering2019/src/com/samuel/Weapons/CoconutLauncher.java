package com.samuel.Weapons;

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
		System.out.println(this.vx);
		Projectile coco = new Coconut((this.vx <= 0? this.x + this.sizeX : this.x - this.sizeX), this.y, this.vx <= 0 ? 200 : -200);
		MenuManager.currentLevel.projs.add(coco);
	}
}
