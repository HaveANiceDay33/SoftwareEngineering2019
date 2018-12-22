package com.samuel;

import com.osreboot.ridhvl.input.HvlController;
import com.osreboot.ridhvl.input.HvlControllerProfile;
import com.osreboot.ridhvl.input.collection.HvlCPG_Gamepad;

public class Controllers {
	static HvlCPG_Gamepad conts;
	public static void initControllers() {
		conts = new HvlCPG_Gamepad();
		HvlController.rescanControllers();
	}
}
