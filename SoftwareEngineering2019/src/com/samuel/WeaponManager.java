package com.samuel;

import com.osreboot.ridhvl.HvlMath;
import com.samuel.Weapons.CoconutLauncher;

public class WeaponManager {
	
	private static final float WEAP_TIME = 20f;
	
	public static float weaponTimer;
	
	public static void updateWeapons(float delta) {
		weaponTimer -= delta;
		if(weaponTimer <= 0) {
			float x, y;
			WorldElement spawnOn = MenuManager.currentLevel.elements.get(HvlMath.randomIntBetween(0, MenuManager.currentLevel.elements.size()));
			if(spawnOn instanceof Platform && !spawnOn.wordOn && !spawnOn.weaponOn) {
				x = spawnOn.get_x();
				y = spawnOn.get_y() - 100;
				spawnOn.weaponOn = true;
			}else {
				x = HvlMath.randomIntBetween(Game.BORDER_RIGHT, Game.BORDER_LEFT);
				y = Game.FIXED_Y;
			}
			Weapon newWeap = new CoconutLauncher(x, y);
			MenuManager.currentLevel.weapons.add(newWeap);
			weaponTimer = WEAP_TIME;
		}
	}
}
