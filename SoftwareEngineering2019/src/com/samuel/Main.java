package com.samuel;

import java.io.File;

import javax.swing.JOptionPane;

import com.osreboot.ridhvl.config.HvlConfig;
import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.painter.HvlAnimatedTextureUV;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;

public class Main extends HvlTemplateInteg2D{
	
	public static void main(String [] args){
		try {
			new Main();
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, e.getClass().getSimpleName() + " - " + e.getMessage(), "Message Melee Exception", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public Main(){
		super(144, 1280, 720, "Message Melee", "Icon32", new HvlDisplayModeDefault());
	}
	
	public static final int NUM_TEXTURES = 53;

	public static final int
	LEVEL_ONE_INDEX = 0,
	CRATE_INDEX = 1,
	FONT_INDEX = 2,
	COG_INDEX = 3,
	COG_TEXT_INDEX = 4,
	BLUE_STILL_INDEX = 5,
	BLUE_RUNNING_INDEX = 6,
	B_INDEX = 7,
	A_INDEX = 8,
	X_INDEX = 9,
	Y_INDEX = 10,
	A_KEY_INDEX = 11,
	D_INDEX = 12,
	S_INDEX = 13,
	W_INDEX = 14,
	YELLOW_RUNNING_INDEX = 15,
	YELLOW_STILL_INDEX = 16,
	RED_RUNNING_INDEX = 17,
	RED_STILL_INDEX = 18,
	GREEN_RUNNING_INDEX = 19,
	GREEN_STILL_INDEX = 20,
	BLUE_JUMP_INDEX = 21,
	YELLOW_JUMP_INDEX = 22,
	RED_JUMP_INDEX = 23,
	GREEN_JUMP_INDEX = 24,
	MENU_BACK_INDEX = 25,
	NAME_INDEX = 26,
	CREDITS_INDEX = 27,
	EXIT_INDEX = 28,
	OPTIONS_INDEX = 29,
	START_INDEX = 30,
	CVILLE_INDEX = 31,
	EFFECTS_INDEX = 32,
	B_MUSIC_INDEX = 33,
	LAUNCHER_INDEX = 34,
	COCONUT_INDEX = 35,
	PAUSE_BACK_INDEX = 36,
	MENU_B_INDEX = 37,
	CONT_INDEX = 38,
	LEVEL_2_INDEX = 39,
	PINE_INDEX = 40,
	SLING_INDEX = 41,
	MENU_SPACE_INDEX = 42,
	SPACE_CRATE_INDEX = 43,
	MIC_INDEX = 44,
	CANNON_INDEX = 45,
	CHEESE_INDEX = 46,
	RIFLE_INDEX = 47,
	CARROT_INDEX = 48,
	BLASTER_INDEX = 49,
	GUMMY_INDEX = 50,
	TRUM_INDEX = 51,
	WAT_INDEX = 52;
	
	public static final int NUM_SOUNDS = 15;
	
	public static final int
	GEAR_RUN_INDEX = 0,
	FORWARD_INDEX = 1,
	BACK_INDEX = 2,
	MENU_SONG_INDEX = 3,
	MENU_SONG_2_INDEX = 4,
	COCONUT_LAUNCH_INDEX = 5,
	MENU_SONG_3_INDEX = 6,
	FUNKY_INDEX = 7,
	JAZZ_INDEX = 8,
	METAL_INDEX = 9,
	CARROT_SHOT_INDEX = 10,
	CHEESE_SHOT_INDEX = 11,
	HIT_SOUND_INDEX = 12,
	GUMMY_SHOT_INDEX = 13,
	PINE_SHOT_INDEX = 14;
	
	public static final String PATH_SETTINGS = "res\\settings.cfg";
	
	public static Options options;
	
	static HvlFontPainter2D font;
	public static HvlAnimatedTextureUV loadingAnimation, blueRunning, blueStanding, blueJumping, yellowRunning, 
	yellowStanding, yellowJumping, redRunning, redStanding, redJumping, greenRunning, greenStanding, greenJumping,
	level1, level2;
	public static AnimatedTextureGroup blue, yellow, red, green;
	
	public static void saveConfig(){
		HvlConfig.saveToFile(options, PATH_SETTINGS);
	}
	
	@Override
	public void initialize() {
		getTextureLoader().loadResource("BackgroundSheet");
		getTextureLoader().loadResource("crate");
		getTextureLoader().loadResource("INOF");
		getTextureLoader().loadResource("headSheet");
		getTextureLoader().loadResource("splashText");
		getTextureLoader().loadResource("Idle4");
		getTextureLoader().loadResource("Left4");
		getTextureLoader().loadResource("b");
		getTextureLoader().loadResource("a");
		getTextureLoader().loadResource("x");
		getTextureLoader().loadResource("y");
		getTextureLoader().loadResource("ak");
		getTextureLoader().loadResource("d");
		getTextureLoader().loadResource("s");
		getTextureLoader().loadResource("w");
		getTextureLoader().loadResource("Left1");
		getTextureLoader().loadResource("Idle1");
		getTextureLoader().loadResource("Left2");
		getTextureLoader().loadResource("Idle2");
		getTextureLoader().loadResource("Left3");
		getTextureLoader().loadResource("Idle3");
		getTextureLoader().loadResource("Jump4");
		getTextureLoader().loadResource("Jump1");
		getTextureLoader().loadResource("Jump2");
		getTextureLoader().loadResource("Jump3");
		getTextureLoader().loadResource("1");
		getTextureLoader().loadResource("Logo2");
		getTextureLoader().loadResource("CreditsButton");
		getTextureLoader().loadResource("ExitButton");
		getTextureLoader().loadResource("OptionsButton");
		getTextureLoader().loadResource("StartButton");
		getTextureLoader().loadResource("clogo");
		getTextureLoader().loadResource("SoundEffects");
		getTextureLoader().loadResource("BackgroundMuzak");
		getTextureLoader().loadResource("cocoLanuch");
		getTextureLoader().loadResource("coconut");
		getTextureLoader().loadResource("PauseBackground");
		getTextureLoader().loadResource("Menu");
		getTextureLoader().loadResource("ContinueButton");
		getTextureLoader().loadResource("SpaceSheet2");
		getTextureLoader().loadResource("pinapple");
		getTextureLoader().loadResource("slingshot");
		getTextureLoader().loadResource("spacebackM");
		getTextureLoader().loadResource("SpaceCrate");
		getTextureLoader().loadResource("MIC");
		getTextureLoader().loadResource("cannon");
		getTextureLoader().loadResource("cheese");
		getTextureLoader().loadResource("rifle");
		getTextureLoader().loadResource("carrot");
		getTextureLoader().loadResource("blaster");
		getTextureLoader().loadResource("gummy");
		getTextureLoader().loadResource("trumpet");
		getTextureLoader().loadResource("cant");
		
		getSoundLoader().loadResource("gears");
		getSoundLoader().loadResource("forward");
		getSoundLoader().loadResource("back");
		getSoundLoader().loadResource("Song2");
		getSoundLoader().loadResource("Song3");
		getSoundLoader().loadResource("coconutLaunch");
		getSoundLoader().loadResource("Song1");
		getSoundLoader().loadResource("funkyBeat");
		getSoundLoader().loadResource("jazzBeat");
		getSoundLoader().loadResource("metalBeat");
		getSoundLoader().loadResource("CarrotSound2");
		getSoundLoader().loadResource("CheeseCannon");
		getSoundLoader().loadResource("hit_sound");
		getSoundLoader().loadResource("Gummy_Bear");
		getSoundLoader().loadResource("Slingshot");
		
		if(getTextureLoader().getResources().size() != NUM_TEXTURES)
			throw new RuntimeException("Textures and/or sounds not loaded, try running the application in the same directory as the 'res' folder.");
		if(getSoundLoader().getResources().size() != NUM_SOUNDS) 
			throw new RuntimeException("Textures and/or sounds not loaded, try running the application in the same directory as the 'res' folder.");
		
		font = new HvlFontPainter2D(getTexture(FONT_INDEX), HvlFontPainter2D.Preset.FP_INOFFICIAL);
		font.setCharSpacing(16f);
		
		loadingAnimation = new HvlAnimatedTextureUV(getTexture(COG_INDEX), 1024, 26, 0.05f);
		loadingAnimation.setAutoStop(false);
		loadingAnimation.setRunning(true);
		
		blueStanding = new HvlAnimatedTextureUV(getTexture(BLUE_STILL_INDEX), 512, 26, 0.1f);
		blueRunning = new HvlAnimatedTextureUV(getTexture(BLUE_RUNNING_INDEX), 512, 26, 0.1f);
		blueJumping = new HvlAnimatedTextureUV(getTexture(BLUE_JUMP_INDEX), 512, 26, 0.1f);
		blue = new AnimatedTextureGroup(blueStanding, blueRunning, blueJumping);
		
		yellowStanding = new HvlAnimatedTextureUV(getTexture(YELLOW_STILL_INDEX), 512, 26, 0.1f);
		yellowRunning = new HvlAnimatedTextureUV(getTexture(YELLOW_RUNNING_INDEX), 512, 26, 0.1f);
		yellowJumping = new HvlAnimatedTextureUV(getTexture(YELLOW_JUMP_INDEX), 512, 26, 0.1f);
		yellow = new AnimatedTextureGroup(yellowStanding, yellowRunning, yellowJumping);
		
		redStanding = new HvlAnimatedTextureUV(getTexture(RED_STILL_INDEX), 512, 26, 0.1f);
		redRunning = new HvlAnimatedTextureUV(getTexture(RED_RUNNING_INDEX), 512, 26, 0.1f);
		redJumping = new HvlAnimatedTextureUV(getTexture(RED_JUMP_INDEX), 512, 26, 0.1f);
		red = new AnimatedTextureGroup(redStanding, redRunning, redJumping);
		
		greenStanding = new HvlAnimatedTextureUV(getTexture(GREEN_STILL_INDEX), 512, 26, 0.1f);
		greenRunning = new HvlAnimatedTextureUV(getTexture(GREEN_RUNNING_INDEX), 512, 26, 0.1f);
		greenJumping = new HvlAnimatedTextureUV(getTexture(GREEN_JUMP_INDEX), 512, 26, 0.1f);
		green = new AnimatedTextureGroup(greenStanding, greenRunning, greenJumping);
		
		level1 = new HvlAnimatedTextureUV(getTexture(LEVEL_ONE_INDEX), 2048, 26, 2f);
		level2 = new HvlAnimatedTextureUV(getTexture(LEVEL_2_INDEX), 2048, 26, 2f);
		
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
