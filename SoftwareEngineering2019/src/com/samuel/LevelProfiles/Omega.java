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
public class Omega extends Level{
	private static final String NAME = "Omega";
	private static final int PLATFORM = Main.SPACE_CRATE_INDEX;
	public Omega() {
		super(Main.level2);
		Platform plat1 = new Platform(0, -400, 16, PLATFORM);
		elements.add(plat1); 
		Platform plat2 = new Platform(1000, 200, 12, PLATFORM);
		elements.add(plat2);
		Platform plat3 = new Platform(-1000, 200, 12, PLATFORM);
		elements.add(plat3);
		Platform plat4 = new Platform(-1200, -1000, 12, PLATFORM);
		elements.add(plat4);
		Platform plat5 = new Platform(1200, -1000, 12, PLATFORM);
		elements.add(plat5);
	}
}

