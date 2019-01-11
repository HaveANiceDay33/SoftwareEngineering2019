package com.samuel.LevelProfiles;

import java.util.ArrayList;

import com.samuel.Level;
import com.samuel.Main;
import com.samuel.Platform;
import com.samuel.WorldElement;

public class Battlefield extends Level{
	private static final String NAME = "Battlefield";
	public Battlefield() {
		super(Main.level1);
		Platform plat1 = new Platform(0, -400, 16);
		elements.add(plat1); 
		Platform plat2 = new Platform(1000, 200, 12);
		elements.add(plat2);
		Platform plat3 = new Platform(-1000, 200, 12);
		elements.add(plat3);
	}
}
