package com.samuel;

import com.osreboot.ridhvl.HvlMath;
import com.samuel.Weapons.Blaster;
import com.samuel.Weapons.Cannon;
import com.samuel.Weapons.Launcher;
import com.samuel.Weapons.Rifle;
import com.samuel.Weapons.Slingshot;
import com.samuel.Weapons.Trumpet;

public class WeaponManager {
	
	private static final float WEAP_TIME = 10f;
	
	public static float weaponTimer;
	public static int ranWeapon;
	
	public static void updateWeapons(float delta) {
		weaponTimer -= delta;
		if(weaponTimer <= 0 && MenuManager.currentLevel.weapons.size() < 3) {
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
			Weapon newWeap;
			ranWeapon = HvlMath.randomIntBetween(0, 6);
			switch(ranWeapon) {
			case 0:
				newWeap = new Launcher(x, y);
				break;
			case 1:
				newWeap = new Slingshot(x, y);
				break;
			case 2:
				newWeap = new Cannon(x, y);
				break;
			case 3:
				newWeap = new Rifle(x, y);
				break;
			case 4:
				newWeap = new Blaster(x, y);
				break;
			case 5:
				newWeap = new Trumpet(x, y);
				break;
			default:
				newWeap = new Launcher(x, y);
				break;
			}
			MenuManager.currentLevel.weapons.add(newWeap);
			weaponTimer = WEAP_TIME;
		}
	}
}
