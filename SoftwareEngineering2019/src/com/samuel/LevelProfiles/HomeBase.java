package com.samuel.LevelProfiles;

import com.samuel.Level;
import com.samuel.Main;
import com.samuel.Platform;

public class HomeBase extends Level{
	private static final String NAME = "Home Base";
	private static final int PLATFORM = Main.SPACE_CRATE_INDEX;
	public HomeBase() {
		super(Main.level2);
		Platform plat1 = new Platform(0, -400, 16, PLATFORM);
		elements.add(plat1); 
		Platform plat2 = new Platform(1000, 200, 12, PLATFORM);
		elements.add(plat2);
		Platform plat3 = new Platform(-1000, -200, 8, PLATFORM);
		elements.add(plat3);
		Platform plat4 = new Platform(-1200, -1000, 6, PLATFORM);
		elements.add(plat4);
		Platform plat5 = new Platform(1200, -1000, 6, PLATFORM);
		elements.add(plat5);
		Platform plat6 = new Platform(0, -1600, 10, PLATFORM);
		elements.add(plat5);
	}
}
