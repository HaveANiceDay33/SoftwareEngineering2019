package com.samuel;

import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.painter.HvlAnimatedTextureUV;
import com.osreboot.ridhvl.painter.HvlCamera2D;
import com.osreboot.ridhvl.painter.HvlRenderFrame;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;

public class Main extends HvlTemplateInteg2D{
	
	public static void main(String [] args){
		new Main();
	}
	public Main(){
		super(60, 1280, 720, "Message Melee", new HvlDisplayModeDefault());
	}
	
	public static final int
	LEVEL_ONE_INDEX = 0,
	CRATE_INDEX = 1,
	FONT_INDEX = 2,
	CVILLE_INDEX = 3,
	COG_INDEX = 4,
	COG_TEXT_INDEX = 5,
	C_TEXT_INDEX = 6,
	BLUE_STILL_INDEX = 7,
	BLUE_RUNNING = 8;
	
	public static final int
	GEAR_RUN_INDEX = 0;
	
	static HvlFontPainter2D font;
	public static HvlAnimatedTextureUV loadingAnimation, blueRunning, blueStanding;
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
		
		getSoundLoader().loadResource("gears");//0
		
		font = new HvlFontPainter2D(getTexture(FONT_INDEX), HvlFontPainter2D.Preset.FP_INOFFICIAL);
		font.setCharSpacing(16f);
		
		loadingAnimation = new HvlAnimatedTextureUV(getTexture(COG_INDEX), 1024, 26, 0.05f);
		loadingAnimation.setAutoStop(false);
		loadingAnimation.setRunning(true);
		blueStanding = new HvlAnimatedTextureUV(getTexture(BLUE_STILL_INDEX), 256, 26, 0.5f);
		blueStanding.setAutoStop(true);
		blueStanding.setRunning(true);
		blueRunning = new HvlAnimatedTextureUV(getTexture(BLUE_RUNNING), 256, 26, 0.2f);
		blueRunning.setAutoStop(false);
		blueRunning.setRunning(true);
		
		MenuManager.initialize();
	}
	@Override
	public void update(float delta) {
		Controllers.updateButtons(); 
		MenuManager.update(delta);		
	}
}
