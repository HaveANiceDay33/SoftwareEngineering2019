package com.samuel;

import com.osreboot.ridhvl.input.HvlController;
import com.osreboot.ridhvl.input.HvlControllerProfile;
import com.osreboot.ridhvl.input.collection.HvlCPG_Gamepad;

public class Controllers {
	static HvlCPG_Gamepad conts;
	public static float [] allA = new float[5];
	public static float [] joy1x = new float[4];
	
	public static void initControllers() {
		conts = new HvlCPG_Gamepad();
		HvlController.rescanControllers();
	}
	public static void updateButtons() {
		allA[0] = conts.getValue(HvlCPG_Gamepad.BUTTON_A, 0);
		allA[1] = conts.getValue(HvlCPG_Gamepad.BUTTON_A, 1);
		allA[2] = conts.getValue(HvlCPG_Gamepad.BUTTON_A, 2);
		allA[3] = conts.getValue(HvlCPG_Gamepad.BUTTON_A, 3);
		allA[4] = conts.getValue(HvlCPG_Gamepad.BUTTON_A);
		
		joy1x[0] = conts.getValue(HvlCPG_Gamepad.JOY1X, 0);
		joy1x[1] = conts.getValue(HvlCPG_Gamepad.JOY1X, 1);
		joy1x[2] = conts.getValue(HvlCPG_Gamepad.JOY1X, 2);
		joy1x[3] = conts.getValue(HvlCPG_Gamepad.JOY1X, 3);
	}
}
