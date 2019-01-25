package com.samuel.LevelProfiles;

import com.samuel.Level;
import com.samuel.Main;
import com.samuel.Platform;

/**
 * <p>This class and others like it tell the game how to draw each level.
 * Attributes include platform type and name.</p>
 * @author Samuel Munro
 *
 */
public class Overworld extends Level{
	private static final String NAME = "Overworld";
	private static final int PLATFORM = Main.SPACE_CRATE_INDEX;
	public Overworld() {
		super(Main.level2);
		Platform plat1 = new Platform(0, -300, 8, PLATFORM);
		elements.add(plat1); 
		Platform plat2 = new Platform(500, 200, 4, PLATFORM);
		elements.add(plat2);
		Platform plat3 = new Platform(-800, 0, 4, PLATFORM);
		elements.add(plat3);
		Platform plat4 = new Platform(0, -800, 6, PLATFORM);
		elements.add(plat4); 
		Platform plat5 = new Platform(1000, -800, 10, PLATFORM);
		elements.add(plat5);
		Platform plat6 = new Platform(-1000, -800, 10, PLATFORM);
		elements.add(plat6);
	}
}
