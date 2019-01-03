package com.samuel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ConcurrentModificationException;

import javax.management.RuntimeErrorException;
import javax.swing.JOptionPane;

import com.osreboot.ridhvl.config.HvlConfig;
import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.painter.HvlAnimatedTextureUV;
import com.osreboot.ridhvl.painter.HvlCamera2D;
import com.osreboot.ridhvl.painter.HvlRenderFrame;
import com.osreboot.ridhvl.painter.HvlRenderFrame.FBOUnsupportedException;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;

public class Main extends HvlTemplateInteg2D{
	
	public static void main(String [] args){
		try {
			new Main();
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, e.getClass().getSimpleName() + " - " + e.getMessage(), "Message Melee Exception", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
	
	public Main(){
		super(144, 1280, 720, "Message Melee", new HvlDisplayModeDefault());
	}
	
	public static final int NUM_TEXTURES = 19;

	public static final int
	LEVEL_ONE_INDEX = 0,
	CRATE_INDEX = 1,
	FONT_INDEX = 2,
	CVILLE_INDEX = 3,
	COG_INDEX = 4,
	COG_TEXT_INDEX = 5,
	C_TEXT_INDEX = 6,
	BLUE_STILL_INDEX = 7,
	BLUE_RUNNING_INDEX = 8,
	B_INDEX = 9,
	A_INDEX = 10,
	X_INDEX = 11,
	Y_INDEX = 12,
	A_KEY_INDEX = 13,
	D_INDEX = 14,
	S_INDEX = 15,
	W_INDEX = 16,
	PURP_RUNNING_INDEX = 17,
	PURP_STILL_INDEX = 18;
	
	public static final int NUM_SOUNDS = 3;
	
	public static final int
	GEAR_RUN_INDEX = 0,
	FORWARD_INDEX = 1,
	BACK_INDEX = 2;
	
	public static final String PATH_SETTINGS = "res\\settings.cfg";
	
	public static Options options;
	
	static HvlFontPainter2D font;
	public static HvlAnimatedTextureUV loadingAnimation, blueRunning, blueStanding, purpRunning, purpStanding;
	public static AnimatedTextureGroup blue, purp;
	
	public static void saveConfig(){
		HvlConfig.saveToFile(options, PATH_SETTINGS);
	}
	@Override
	public void initialize() {
		getTextureLoader().loadResource("level1");//0
		getTextureLoader().loadResource("crate");//1
		getTextureLoader().loadResource("INOF");//2
		getTextureLoader().loadResource("clogo");//3
		getTextureLoader().loadResource("headSheet");//4
		getTextureLoader().loadResource("splashText");//5
		getTextureLoader().loadResource("textTry");//6
		getTextureLoader().loadResource("blueStill");//7
		getTextureLoader().loadResource("blueManRunning");//8
		getTextureLoader().loadResource("b");//9
		getTextureLoader().loadResource("a");//10
		getTextureLoader().loadResource("x");//11
		getTextureLoader().loadResource("y");//12
		getTextureLoader().loadResource("ak");//13
		getTextureLoader().loadResource("d");//14
		getTextureLoader().loadResource("s");//15
		getTextureLoader().loadResource("w");//16
		getTextureLoader().loadResource("purpCharRun");//17
		getTextureLoader().loadResource("purpmanidle");//18
		
		getSoundLoader().loadResource("gears");//0
		getSoundLoader().loadResource("forward");//1
		getSoundLoader().loadResource("back");//2
		
		if(getTextureLoader().getResources().size() != NUM_TEXTURES)
			throw new RuntimeException("Textures and/or sounds not loaded, try running the application in the same directory as the 'res' folder.");
		if(getSoundLoader().getResources().size() != NUM_SOUNDS) 
			throw new RuntimeException("Textures and/or sounds not loaded, try running the application in the same directory as the 'res' folder.");
		
		font = new HvlFontPainter2D(getTexture(FONT_INDEX), HvlFontPainter2D.Preset.FP_INOFFICIAL);
		font.setCharSpacing(16f);
		
		loadingAnimation = new HvlAnimatedTextureUV(getTexture(COG_INDEX), 1024, 26, 0.05f);
		loadingAnimation.setAutoStop(false);
		loadingAnimation.setRunning(true);
		
		blueStanding = new HvlAnimatedTextureUV(getTexture(BLUE_STILL_INDEX), 256, 26, 0.5f);
		blueRunning = new HvlAnimatedTextureUV(getTexture(BLUE_RUNNING_INDEX), 256, 26, 0.2f);
		blue = new AnimatedTextureGroup(blueStanding, blueRunning);
		
		purpStanding = new HvlAnimatedTextureUV(getTexture(PURP_STILL_INDEX), 256, 26, 0.5f);
		purpRunning = new HvlAnimatedTextureUV(getTexture(PURP_RUNNING_INDEX), 256, 26, 0.2f);
		purp = new AnimatedTextureGroup(purpStanding, purpRunning);
		
		File config = new File(PATH_SETTINGS);
		if(config.exists()){
			options = HvlConfig.loadFromFile(PATH_SETTINGS);
		}else{
			HvlConfig.saveToFile(new Options(), PATH_SETTINGS);
			options = HvlConfig.loadFromFile(PATH_SETTINGS);
		}
		
		MenuManager.initialize();
	}
	@Override
	public void update(float delta) {
		Controllers.updateButtons(); 
		MenuManager.update(delta);	
	}
}
