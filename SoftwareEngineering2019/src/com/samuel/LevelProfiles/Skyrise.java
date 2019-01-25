package com.samuel.LevelProfiles;

import com.samuel.Level;
import com.samuel.Main;
import com.samuel.Platform;
import com.samuel.Weapon;
/**
 * <p>This class and others like it tell the game how to draw each level.
 * Attributes include platform type and name.</p>
 * @author Samuel Munro
 *
 */
public class Skyrise extends Level{
	private static final String NAME = "Skyrise";
	private static final int PLATFORM = Main.CRATE_INDEX;
	public Skyrise() {
		super(Main.level1);
		Platform plat1 = new Platform(0, -300, 8, PLATFORM);
		elements.add(plat1); 
		Platform plat2 = new Platform(1000, 200, 10, PLATFORM);
		elements.add(plat2);
		Platform plat3 = new Platform(-1000, 200, 10, PLATFORM);
		elements.add(plat3);
		Platform plat4 = new Platform(0, -800, 6, PLATFORM);
		elements.add(plat4); 
		Platform plat5 = new Platform(1000, -800, 10, PLATFORM);
		elements.add(plat5);
		Platform plat6 = new Platform(-1000, -800, 10, PLATFORM);
		elements.add(plat6);
	}
}
