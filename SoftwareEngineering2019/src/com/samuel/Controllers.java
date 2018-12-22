package com.samuel;

import com.osreboot.ridhvl.input.HvlController;
import com.osreboot.ridhvl.input.HvlControllerProfile;
import com.osreboot.ridhvl.input.collection.HvlCPG_Gamepad;

public class Controllers {
	static HvlCPG_Gamepad conts;
	public static float
	allA, a1, a2, a3, a4;
	
	public static void initControllers() {
		conts = new HvlCPG_Gamepad();
		HvlController.rescanControllers();
		allA = conts.getValue(HvlCPG_Gamepad.BUTTON_A);
		a1 = conts.getValue(HvlCPG_Gamepad.BUTTON_A, 0);
		a2 = conts.getValue(HvlCPG_Gamepad.BUTTON_A, 1);
		a3 = conts.getValue(HvlCPG_Gamepad.BUTTON_A, 2);
		a4 = conts.getValue(HvlCPG_Gamepad.BUTTON_A, 3);
	}
}
