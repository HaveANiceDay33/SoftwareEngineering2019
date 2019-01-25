package com.samuel;

import com.osreboot.ridhvl.input.HvlController;
import com.osreboot.ridhvl.input.HvlControllerProfile;
import com.osreboot.ridhvl.input.collection.HvlCPG_Gamepad;

/**
 * 
 * @author Samuel Munro
 * 
 * <p>This class provides avenues to access all necessary controls 
 * for the Player class to use. Values are stored in an array and are
 * updated in the Main class </p> 
 *
 */

public class Controllers {
	static HvlCPG_Gamepad conts;
	public static float [] allA = new float[5];
	public static float [] allB = new float[5];
	public static float [] allY = new float[5];
	public static float [] allX = new float[5];
	public static float [] allStart = new float[5];
	public static float [] joy1x = new float[4];
	public static float [] allUp = new float[5];
	public static float [] allDown = new float[5];
	
	public static void initControllers() {
		conts = new HvlCPG_Gamepad();
		HvlController.rescanControllers();
	}
	public static void updateButtons() {
		//a btn
		allA[0] = conts.getValue(HvlCPG_Gamepad.BUTTON_A, 0); //Controller 1
		allA[1] = conts.getValue(HvlCPG_Gamepad.BUTTON_A, 1); //Controller 2
		allA[2] = conts.getValue(HvlCPG_Gamepad.BUTTON_A, 2); //Controller 3
		allA[3] = conts.getValue(HvlCPG_Gamepad.BUTTON_A, 3); //Controller 4
		allA[4] = conts.getValue(HvlCPG_Gamepad.BUTTON_A); //All controllers. Primarily used in menu, so anyone can access the various menus.
		
		//b btn
		allB[0] = conts.getValue(HvlCPG_Gamepad.BUTTON_B, 0);
		allB[1] = conts.getValue(HvlCPG_Gamepad.BUTTON_B, 1);
		allB[2] = conts.getValue(HvlCPG_Gamepad.BUTTON_B, 2);
		allB[3] = conts.getValue(HvlCPG_Gamepad.BUTTON_B, 3);
		allB[4] = conts.getValue(HvlCPG_Gamepad.BUTTON_B);
		
		//y btn
		allY[0] = conts.getValue(HvlCPG_Gamepad.BUTTON_Y, 0);
		allY[1] = conts.getValue(HvlCPG_Gamepad.BUTTON_Y, 1);
		allY[2] = conts.getValue(HvlCPG_Gamepad.BUTTON_Y, 2);
		allY[3] = conts.getValue(HvlCPG_Gamepad.BUTTON_Y, 3);
		allY[4] = conts.getValue(HvlCPG_Gamepad.BUTTON_Y);
		
		//x btn
		allX[0] = conts.getValue(HvlCPG_Gamepad.BUTTON_X, 0);
		allX[1] = conts.getValue(HvlCPG_Gamepad.BUTTON_X, 1);
		allX[2] = conts.getValue(HvlCPG_Gamepad.BUTTON_X, 2);
		allX[3] = conts.getValue(HvlCPG_Gamepad.BUTTON_X, 3);
		allX[4] = conts.getValue(HvlCPG_Gamepad.BUTTON_X);
		
		//start btn
		allStart[0] = conts.getValue(HvlCPG_Gamepad.BUTTON_START, 0);
		allStart[1] = conts.getValue(HvlCPG_Gamepad.BUTTON_START, 1);
		allStart[2] = conts.getValue(HvlCPG_Gamepad.BUTTON_START, 2);
		allStart[3] = conts.getValue(HvlCPG_Gamepad.BUTTON_START, 3);
		allStart[4] = conts.getValue(HvlCPG_Gamepad.BUTTON_START);
		
		//left x-axis
		joy1x[0] = conts.getValue(HvlCPG_Gamepad.JOY1X, 0);
		joy1x[1] = conts.getValue(HvlCPG_Gamepad.JOY1X, 1);
		joy1x[2] = conts.getValue(HvlCPG_Gamepad.JOY1X, 2);
		joy1x[3] = conts.getValue(HvlCPG_Gamepad.JOY1X, 3);
		
		//up on dpad
		allUp[0] = conts.getValue(HvlCPG_Gamepad.DIRECTION_UP, 0);
		allUp[1] = conts.getValue(HvlCPG_Gamepad.DIRECTION_UP, 1);
		allUp[2] = conts.getValue(HvlCPG_Gamepad.DIRECTION_UP, 2);
		allUp[3] = conts.getValue(HvlCPG_Gamepad.DIRECTION_UP, 3);
		allUp[4] = conts.getValue(HvlCPG_Gamepad.DIRECTION_UP);
		
		//down on dpad
		allDown[0] = conts.getValue(HvlCPG_Gamepad.DIRECTION_DOWN, 0);
		allDown[1] = conts.getValue(HvlCPG_Gamepad.DIRECTION_DOWN, 1);
		allDown[2] = conts.getValue(HvlCPG_Gamepad.DIRECTION_DOWN, 2);
		allDown[3] = conts.getValue(HvlCPG_Gamepad.DIRECTION_DOWN, 3);
		allDown[4] = conts.getValue(HvlCPG_Gamepad.DIRECTION_DOWN);
	}
}
