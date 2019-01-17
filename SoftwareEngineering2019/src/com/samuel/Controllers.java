package com.samuel;

import com.osreboot.ridhvl.input.HvlController;
import com.osreboot.ridhvl.input.HvlControllerProfile;
import com.osreboot.ridhvl.input.collection.HvlCPG_Gamepad;

public class Controllers {
	static HvlCPG_Gamepad conts;
	public static float [] allA = new float[5];
	public static float [] allB = new float[5];
	public static float [] allY = new float[5];
	public static float [] allX = new float[5];
	public static float [] allStart = new float[5];
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
		
		allB[0] = conts.getValue(HvlCPG_Gamepad.BUTTON_B, 0);
		allB[1] = conts.getValue(HvlCPG_Gamepad.BUTTON_B, 1);
		allB[2] = conts.getValue(HvlCPG_Gamepad.BUTTON_B, 2);
		allB[3] = conts.getValue(HvlCPG_Gamepad.BUTTON_B, 3);
		allB[4] = conts.getValue(HvlCPG_Gamepad.BUTTON_B);
		
		allY[0] = conts.getValue(HvlCPG_Gamepad.BUTTON_Y, 0);
		allY[1] = conts.getValue(HvlCPG_Gamepad.BUTTON_Y, 1);
		allY[2] = conts.getValue(HvlCPG_Gamepad.BUTTON_Y, 2);
		allY[3] = conts.getValue(HvlCPG_Gamepad.BUTTON_Y, 3);
		allY[4] = conts.getValue(HvlCPG_Gamepad.BUTTON_Y);
		
		allX[0] = conts.getValue(HvlCPG_Gamepad.BUTTON_X, 0);
		allX[1] = conts.getValue(HvlCPG_Gamepad.BUTTON_X, 1);
		allX[2] = conts.getValue(HvlCPG_Gamepad.BUTTON_X, 2);
		allX[3] = conts.getValue(HvlCPG_Gamepad.BUTTON_X, 3);
		allX[4] = conts.getValue(HvlCPG_Gamepad.BUTTON_X);
		
		allStart[0] = conts.getValue(HvlCPG_Gamepad.BUTTON_START, 0);
		allStart[1] = conts.getValue(HvlCPG_Gamepad.BUTTON_START, 1);
		allStart[2] = conts.getValue(HvlCPG_Gamepad.BUTTON_START, 2);
		allStart[3] = conts.getValue(HvlCPG_Gamepad.BUTTON_START, 3);
		allStart[4] = conts.getValue(HvlCPG_Gamepad.BUTTON_START);
		
		joy1x[0] = conts.getValue(HvlCPG_Gamepad.JOY1X, 0);
		joy1x[1] = conts.getValue(HvlCPG_Gamepad.JOY1X, 1);
		joy1x[2] = conts.getValue(HvlCPG_Gamepad.JOY1X, 2);
		joy1x[3] = conts.getValue(HvlCPG_Gamepad.JOY1X, 3);
	}
}
