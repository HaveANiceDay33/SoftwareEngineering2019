package com.samuel.LevelProfiles;

import java.util.ArrayList;

import com.samuel.Level;
import com.samuel.Main;
import com.samuel.Platform;
import com.samuel.WorldElement;

public class Battlefield extends Level{
	private static final String NAME = "Battlefield";
	private static final int PLATFORM = Main.CRATE_INDEX;
	public Battlefield() {
		super(Main.level1);
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
