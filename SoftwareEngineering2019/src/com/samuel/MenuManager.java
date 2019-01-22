package com.samuel;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuad;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.action.HvlAction1;
import com.osreboot.ridhvl.input.HvlInput;
import com.osreboot.ridhvl.menu.HvlButtonMenuLink;
import com.osreboot.ridhvl.menu.HvlComponentDefault;
import com.osreboot.ridhvl.menu.HvlMenu;
import com.osreboot.ridhvl.menu.component.HvlArrangerBox;
import com.osreboot.ridhvl.menu.component.HvlArrangerBox.ArrangementStyle;
import com.osreboot.ridhvl.menu.component.HvlButton;
import com.osreboot.ridhvl.menu.component.HvlSpacer;
import com.osreboot.ridhvl.menu.component.collection.HvlLabeledButton;
import com.osreboot.ridhvl.painter.HvlRenderFrame;
import com.samuel.LevelProfiles.Battlefield;
import com.samuel.LevelProfiles.HomeBase;
import com.samuel.LevelProfiles.MoonRise;
import com.samuel.LevelProfiles.Omega;
import com.samuel.LevelProfiles.Overworld;
import com.samuel.LevelProfiles.Skyrise;

public class MenuManager {
	public static final float BUTTON_WIDTH = 254f, BUTTON_HEIGHT = 78f;
	private static final float CONTROLLER_TIME = 5f;
	private static final float BUTTON_WAIT_TIME = 0.25f;
	private static final float SONG_TIME = 30f;
	
	public static HvlMenu intro, intro2, menu, controllerInit, charSelect, game, options, credits, pause, genre, singing;
	private static float whiteFade = 0;
	
	static boolean playedIntroSound = false;
	static boolean playedMenuMusic = false;
	public static Level currentLevel;
	static int pickLevel;
	
	static String[] genres = {"Funk", "Jazz", "Metal"};
	static String chosenGenre;
	
	public static HvlRenderFrame pauseFrame;
	
	private static void timerBar(float time) {
		hvlDrawQuad(200, 620, Display.getWidth()-400, 20, Color.gray);
		hvlDrawQuadc(200, 630, 5, 40, Color.green);
		hvlDrawQuadc(Display.getWidth()-200, 630, 5, 40, Color.green);
		hvlDrawQuad(200, 620, (Display.getWidth()-400)*time, 20, Color.green);
	}

	private static void playForward() {
		if(Main.options.soundEffectsEnabled) {
			Main.getSound(Main.FORWARD_INDEX).playAsSoundEffect(1, 10f, false);
		}
	}
	
	private static void playBack() {
		if(Main.options.soundEffectsEnabled) {
			Main.getSound(Main.BACK_INDEX).playAsSoundEffect(1, 10f, false);
		}
	}
	
	public static HvlInput pauseInput, backInput;
	
	public static void initialize() {
		
		try{
			pauseFrame = new HvlRenderFrame(Display.getWidth(), Display.getHeight());
		}catch(Exception e){
			throw new RuntimeException("Render Frames not supported on this computer! Do you live in the 80's?");
		}
		
		pauseInput = new HvlInput(new HvlInput.InputFilter() {
			@Override
			public float getCurrentOutput() {
				if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) || Controllers.allStart[4] == 1) {
					return 1;
				}else {
					return 0;
				}	
			}
		});
		
		pauseInput.setPressedAction(new HvlAction1<HvlInput>() {
			@Override
			public void run(HvlInput a) {
				if(HvlMenu.getCurrent() == pause) {
					HvlMenu.setCurrent(game);
					playForward();
				}else if(HvlMenu.getCurrent() == game) {
					HvlMenu.setCurrent(pause);
					playBack();
				}
			}
		});
		
		backInput = new HvlInput(new HvlInput.InputFilter() {
			@Override
			public float getCurrentOutput() {
				if(Controllers.allB[4] == 1) {
					return 1;
				}else {
					return 0;
				}
			}
		});
		
		backInput.setPressedAction(new HvlAction1<HvlInput>() {
			@Override
			public void run(HvlInput a) {
				if(HvlMenu.getCurrent() == pause) {
					playBack();
					resetGame();
				} else if(HvlMenu.getCurrent() == credits || HvlMenu.getCurrent() == options) {
					playBack();
					HvlMenu.setCurrent(menu);
				} else if(HvlMenu.getCurrent() == menu) {
					playBack();
					System.exit(0);
				}
				
			}
		});
		
		HvlComponentDefault.setDefault(new HvlArrangerBox(0, 0, Display.getWidth(), Display.getHeight(), ArrangementStyle.VERTICAL));
		HvlComponentDefault.setDefault(HvlLabeledButton.class, new HvlLabeledButton.Builder().setWidth(BUTTON_WIDTH).setHeight(BUTTON_HEIGHT).setFont(Main.font).setTextColor(Color.white).setTextScale(0.2f).build());
		
		intro = new HvlMenu();
		intro2 = new HvlMenu();
		menu = new HvlMenu();
		controllerInit = new HvlMenu();
		charSelect = new HvlMenu();
		game = new HvlMenu();
		options = new HvlMenu();
		credits = new HvlMenu();
		pause = new HvlMenu();
		genre = new HvlMenu();
		singing = new HvlMenu();
		
		menu.add(new HvlArrangerBox.Builder().setxAlign(0.1f).build());
		menu.getFirstArrangerBox().add(new HvlLabeledButton.Builder().setOffDrawable(new ImageDrawable(Main.START_INDEX, Color.white)).
				setOnDrawable(new ImageDrawable(Main.START_INDEX, Color.gray)).setHoverDrawable(new ImageDrawable(Main.START_INDEX, Color.gray)).setClickedCommand(new HvlAction1<HvlButton>(){
			@Override
			public void run(HvlButton aArg){
				playForward();
				currentPlayer = 0;
				controllerTimer = CONTROLLER_TIME;
				HvlMenu.setCurrent(controllerInit);
			}
		}).build());
		menu.getFirstArrangerBox().add(new HvlSpacer(0,20));
		menu.getFirstArrangerBox().add(new HvlLabeledButton.Builder().setOffDrawable(new ImageDrawable(Main.OPTIONS_INDEX, Color.white)).
				setOnDrawable(new ImageDrawable(Main.OPTIONS_INDEX, Color.gray)).setHoverDrawable(new ImageDrawable(Main.OPTIONS_INDEX, Color.gray)).setClickedCommand(new HvlAction1<HvlButton>(){
			@Override
			public void run(HvlButton aArg){
				playForward();
				HvlMenu.setCurrent(options);
			}
		}).build());
		menu.getFirstArrangerBox().add(new HvlSpacer(0,20));
		menu.getFirstArrangerBox().add(new HvlLabeledButton.Builder().setOffDrawable(new ImageDrawable(Main.CREDITS_INDEX, Color.white)).
				setOnDrawable(new ImageDrawable(Main.CREDITS_INDEX, Color.gray)).setHoverDrawable(new ImageDrawable(Main.CREDITS_INDEX, Color.gray)).setClickedCommand(new HvlAction1<HvlButton>(){
			@Override
			public void run(HvlButton aArg){
				playForward();
				HvlMenu.setCurrent(credits);
			}
		}).build());
		menu.getFirstArrangerBox().add(new HvlSpacer(0,20));
		menu.getFirstArrangerBox().add(new HvlLabeledButton.Builder().setOffDrawable(new ImageDrawable(Main.EXIT_INDEX, Color.white)).
				setOnDrawable(new ImageDrawable(Main.EXIT_INDEX, Color.gray)).setHoverDrawable(new ImageDrawable(Main.EXIT_INDEX, Color.gray)).setClickedCommand(new HvlAction1<HvlButton>(){
			@Override
			public void run(HvlButton aArg){
				playBack();
				System.exit(0);
			}
		}).build());
		
		options.add(new HvlArrangerBox.Builder().setxAlign(0.1f).build()); //A
		options.getFirstArrangerBox().add(new HvlLabeledButton.Builder().setTextScale(0.16f).
				setOffDrawable(new ImageDrawable(Main.EFFECTS_INDEX, Color.white)).
				setOnDrawable(new ImageDrawable(Main.EFFECTS_INDEX, Color.gray)).setHoverDrawable(new ImageDrawable(Main.EFFECTS_INDEX, Color.gray)).setClickedCommand(new HvlAction1<HvlButton>(){
			@Override
			public void run(HvlButton aArg) {
				playForward();
				Main.options.soundEffectsEnabled = !Main.options.soundEffectsEnabled;
			}
		}).build());
		options.getFirstArrangerBox().add(new HvlSpacer(0, 10)); //X
		options.getFirstArrangerBox().add(new HvlLabeledButton.Builder().setTextScale(0.16f).setOffDrawable(new ImageDrawable(Main.B_MUSIC_INDEX, Color.white)).
				setOnDrawable(new ImageDrawable(Main.B_MUSIC_INDEX, Color.gray)).setHoverDrawable(new ImageDrawable(Main.B_MUSIC_INDEX, Color.gray)).setClickedCommand(new HvlAction1<HvlButton>(){
			@Override
			public void run(HvlButton aArg) {
				playForward();
				Main.options.backgroundMusicEnabled = !Main.options.backgroundMusicEnabled;
			}
		}).build());
		
		options.add(new HvlArrangerBox.Builder().setxAlign(0.1f).build());
		options.getChildOfType(HvlArrangerBox.class, 1).add(new HvlSpacer(0, 500));
		options.getChildOfType(HvlArrangerBox.class, 1).add(new HvlLabeledButton.Builder().setOffDrawable(new ImageDrawable(Main.EXIT_INDEX, Color.white)).
				setOnDrawable(new ImageDrawable(Main.EXIT_INDEX, Color.gray)).setHoverDrawable(new ImageDrawable(Main.EXIT_INDEX, Color.gray)).setClickedCommand(new HvlAction1<HvlButton>(){
			@Override
			public void run(HvlButton aArg) {
				playBack();
				Main.saveConfig();
				HvlMenu.setCurrent(menu);
			}
		}).build());
		
		credits.add(new HvlArrangerBox.Builder().setxAlign(0.1f).build());
		credits.getFirstArrangerBox().add(new HvlSpacer(0, 500));
		credits.getFirstArrangerBox().add(new HvlLabeledButton.Builder().setOffDrawable(new ImageDrawable(Main.EXIT_INDEX, Color.white)).
				setOnDrawable(new ImageDrawable(Main.EXIT_INDEX, Color.gray)).setHoverDrawable(new ImageDrawable(Main.EXIT_INDEX, Color.gray)).setClickedCommand(new HvlAction1<HvlButton>(){
			@Override
			public void run(HvlButton aArg) {
				playBack();
				HvlMenu.setCurrent(menu);
			}
		}).build());
		
		pause.add(new HvlArrangerBox.Builder().setxAlign(0.5f).build());
		pause.getFirstArrangerBox().add(new HvlLabeledButton.Builder().setOffDrawable(new ImageDrawable(Main.CONT_INDEX, Color.white)).
				setOnDrawable(new ImageDrawable(Main.CONT_INDEX, Color.gray)).setHoverDrawable(new ImageDrawable(Main.CONT_INDEX, Color.gray)).setClickedCommand(new HvlButtonMenuLink(game)).build());
		pause.getFirstArrangerBox().add(new HvlSpacer(0, 30));
		pause.getFirstArrangerBox().add(new HvlLabeledButton.Builder().setOffDrawable(new ImageDrawable(Main.MENU_B_INDEX, Color.white)).
				setOnDrawable(new ImageDrawable(Main.MENU_B_INDEX, Color.gray)).setHoverDrawable(new ImageDrawable(Main.MENU_B_INDEX, Color.gray)).setClickedCommand(new HvlAction1<HvlButton>(){
			@Override
			public void run(HvlButton aArg){
				resetGame();
			}
		}).build());
		
		pickLevel = HvlMath.randomIntBetween(0, 4);
		
		switch(pickLevel) {
			case 0:
				currentLevel = new HomeBase();
				break;
			case 1:
				currentLevel = new Overworld();
				break;
			case 2:
				currentLevel = new Battlefield();
				break;
			case 3:
				currentLevel = new Skyrise();
				break;
			case 4:
				currentLevel = new MoonRise();
				break;
			case 5:
				currentLevel = new Omega();
				break;
			default:
				currentLevel = new Overworld();
				break;
		}
		
		WordManager.initWords();
		Controllers.initControllers();
		HvlMenu.setCurrent(intro);
	}
	
	private static float introProgress = 0f;
	private static float controllerTimer = CONTROLLER_TIME;
	private static float buttonWait = BUTTON_WAIT_TIME;
	private static float genreTimer = 3f;
	public static int p1index = 3, p2index = 3, p3index = 3, p4index = 3; //default controller
	public static AnimatedTextureGroup p1A = Main.blue, p2A = Main.blue, p3A = Main.blue, p4A = Main.blue; //default character
	public static int currentPlayer = 0;
	public static int[] songs = {Main.MENU_SONG_INDEX, Main.MENU_SONG_2_INDEX, Main.MENU_SONG_3_INDEX};
	public static String[] lyrics = {"lyricSheets/lyrics1.txt"};
	public static int currentSong = HvlMath.randomIntBetween(0, 2);
	public static boolean beatPlayed = false;
	public static int singingPlayer = 0;
	public static float singTimer = SONG_TIME;
	public static boolean playerSang = false;
	
	public static void update(float delta){
		
		if(HvlMenu.getCurrent() == pause){
			hvlDrawQuad(0, 0, Display.getWidth(), Display.getHeight(), pauseFrame);
			hvlDrawQuad(0, 0, Display.getWidth(), Display.getHeight(), new Color(0f, 0f, 0f, 0.4f));
			hvlDrawQuadc(Display.getWidth()/2, Display.getHeight()/2, 400, 500, Main.getTexture(Main.PAUSE_BACK_INDEX));
			if(Controllers.allA[4] == 1) {
				HvlMenu.setCurrent(game);
			}
		}
		
		whiteFade = HvlMath.stepTowards(whiteFade, delta, 0f);
		if(HvlMenu.getCurrent() == intro){
			if(!playedIntroSound && Main.options.soundEffectsEnabled) {
				Main.getSound(Main.GEAR_RUN_INDEX).playAsSoundEffect(1f, 1f, false);
				playedIntroSound = true;
			}
			//UPDATING THE INTRO MENU//
			introProgress += delta/6f;
			if(introProgress >= 1f || (introProgress > 0.25f && (Mouse.isButtonDown(0) || Controllers.allA[4] == 1))) {
				introProgress = 0f;
				Main.getSound(Main.GEAR_RUN_INDEX).stop();
				HvlMenu.setCurrent(intro2);
			}
			float alpha = 1f - (Math.abs(introProgress - 0.5f)*2f);
			hvlDrawQuad(0, 0, Display.getWidth(), Display.getHeight(), new Color(0.21f,.2f,.2f,.2f));
			hvlDrawQuadc(Display.getWidth()/2, Display.getHeight()/2-100, 512, 512, Main.loadingAnimation, new Color(1f, 1f, 1f, alpha));
			hvlDrawQuadc(Display.getWidth()/2, Display.getHeight()/2+200, 1024, 1024, Main.getTexture(Main.COG_TEXT_INDEX), new Color(1f, 1f, 1f, alpha));
		}
		else if(HvlMenu.getCurrent() == intro2){
			//UPDATING THE INTRO 2 MENU//
			introProgress += delta/4f;
			if(introProgress >= 1f || (introProgress > 0.25f && (Mouse.isButtonDown(0) || Controllers.allA[4] == 1))) {HvlMenu.setCurrent(menu); buttonWait = BUTTON_WAIT_TIME;}
			float alpha = 1f - (Math.abs(introProgress - 0.5f)*2f);
			
			hvlDrawQuadc(Display.getWidth()/2, Display.getHeight()/2, 1280, 720, Main.getTexture(Main.CVILLE_INDEX), new Color(1f, 1f, 1f, alpha));
		}
		else if(HvlMenu.getCurrent() == menu) {
			hvlDrawQuad(0, 0, Display.getWidth(), Display.getHeight(), currentLevel.menuBackground);
			hvlDrawQuadc(800, Display.getHeight()/2, 640, 360, Main.getTexture(Main.NAME_INDEX), new Color(1f, 1f, 1f, 1f));
			buttonWait-=delta;
			if(Controllers.allA[4] == 1 && buttonWait <= 0) {
				currentPlayer = 0;
				controllerTimer = CONTROLLER_TIME;
				buttonWait = BUTTON_WAIT_TIME;
				playForward();
				HvlMenu.setCurrent(controllerInit);
			}
			
			if(Controllers.allY[4] == 1 && buttonWait <= 0) {playForward(); HvlMenu.setCurrent(options); buttonWait = BUTTON_WAIT_TIME;}
			if(Controllers.allX[4] == 1 && buttonWait <= 0) {playForward(); HvlMenu.setCurrent(credits); buttonWait = BUTTON_WAIT_TIME;}
		}
		else if(HvlMenu.getCurrent() == options) {
			hvlDrawQuad(0, 0, Display.getWidth(), Display.getHeight(), currentLevel.menuBackground);
			buttonWait -= delta;
			Main.font.drawWordc("Options", Display.getWidth()/2, 100, currentLevel.textColor, 0.3f);
			Main.font.drawWord((Main.options.soundEffectsEnabled ? "ON" : "OFF"), 380, 300, currentLevel.textColor, 0.18f);
			Main.font.drawWord((Main.options.backgroundMusicEnabled ? "ON" : "OFF"), 380, 390, currentLevel.textColor, 0.18f);
			if(Controllers.allA[4] == 1 && buttonWait <= 0) {
				playForward();
				Main.options.soundEffectsEnabled = !Main.options.soundEffectsEnabled;
				buttonWait = BUTTON_WAIT_TIME;
			}
			if(Controllers.allX[4] == 1 && buttonWait <= 0) {
				playForward();
				Main.options.backgroundMusicEnabled = !Main.options.backgroundMusicEnabled;
				buttonWait = BUTTON_WAIT_TIME;
			}
		}
		else if(HvlMenu.getCurrent() == credits) {
			hvlDrawQuad(0, 0, Display.getWidth(), Display.getHeight(), currentLevel.menuBackground);
			Main.font.drawWordc("Samuel Munro - Programming", Display.getWidth()/2, 150, currentLevel.textColor, 0.2f);
			Main.font.drawWordc("George Bolmida - Art and Sound Effects", Display.getWidth()/2, 250, currentLevel.textColor, 0.2f);
			Main.font.drawWordc("Ben Matos - Music", Display.getWidth()/2, 350, currentLevel.textColor, 0.2f);
			Main.font.drawWordc("Shane Pritchard - Visual Assets", Display.getWidth()/2, 450, currentLevel.textColor, 0.2f);
		}
		else if(HvlMenu.getCurrent() == controllerInit) {
			hvlDrawQuad(0, 0, Display.getWidth(), Display.getHeight(), currentLevel.menuBackground);
			controllerTimer -= delta;
			buttonWait -= delta;
			
			Main.font.drawWordc("Player "+ (currentPlayer != 4 ? (currentPlayer+1) : currentPlayer) +":", Display.getWidth()/2,100,currentLevel.textColor, 0.3f);
			Main.font.drawWordc("Press A on Xbox controller or W on Keyboard", Display.getWidth()/2, 150, currentLevel.textColor, 0.22f);
			Main.font.drawWordc("Press B (Xbox) or D (Keyboard) to skip", Display.getWidth()/2, 200, currentLevel.textColor, 0.15f);

			timerBar(controllerTimer/CONTROLLER_TIME);
			if(currentPlayer == 0 && buttonWait <= 0) {
				if(Controllers.allA[0] == 1) {p1index = 0; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME; playForward();}
				if(Controllers.allA[1] == 1) {p1index = 1; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME; playForward();}
				if(Controllers.allA[2] == 1) {p1index = 2; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME; playForward();}
				if(Controllers.allA[3] == 1) {p1index = 3; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME; playForward();}
				if(Keyboard.isKeyDown(Keyboard.KEY_W)) {p1index = 4; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME; playForward();}
			} else if (currentPlayer == 1 && buttonWait <= 0) {
				if(Controllers.allA[0] == 1) {p2index = 0; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME; playForward();}
				if(Controllers.allA[1] == 1) {p2index = 1; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME; playForward();}
				if(Controllers.allA[2] == 1) {p2index = 2; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME; playForward();}
				if(Controllers.allA[3] == 1) {p2index = 3; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME; playForward();}
				if(Keyboard.isKeyDown(Keyboard.KEY_W)) {p2index = 4; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME; playForward();}
			} else if (currentPlayer == 2 && buttonWait <= 0) {
				if(Controllers.allA[0] == 1) {p3index = 0; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME; playForward();}
				if(Controllers.allA[1] == 1) {p3index = 1; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME; playForward();}
				if(Controllers.allA[2] == 1) {p3index = 2; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME; playForward();}
				if(Controllers.allA[3] == 1) {p3index = 3; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME; playForward();}
				if(Keyboard.isKeyDown(Keyboard.KEY_W)) {p3index = 4; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME; playForward();}
			} else if (currentPlayer == 3 && buttonWait <= 0) {
				if(Controllers.allA[0] == 1) {p4index = 0; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME; playForward();}
				if(Controllers.allA[1] == 1) {p4index = 1; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME; playForward();}
				if(Controllers.allA[2] == 1) {p4index = 2; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME; playForward();}
				if(Controllers.allA[3] == 1) {p4index = 3; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME; playForward();}
				if(Keyboard.isKeyDown(Keyboard.KEY_W)) {p4index = 4; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME; playForward();}
			} else if (currentPlayer > 3) {
				currentPlayer = 0;
				controllerTimer = CONTROLLER_TIME;
				HvlMenu.setCurrent(charSelect);
			}
			if(((Controllers.allB[p1index] == 1 && p1index < 4) || Keyboard.isKeyDown(Keyboard.KEY_D))  && buttonWait <= 0) {
				controllerTimer = CONTROLLER_TIME;
				buttonWait = BUTTON_WAIT_TIME;
				playBack();
				currentPlayer++;
			}
			if(controllerTimer <= 0) {
				playBack();
				currentPlayer++;
				controllerTimer = CONTROLLER_TIME;
			}

		}else if(HvlMenu.getCurrent() == charSelect) {
			hvlDrawQuad(0, 0, Display.getWidth(), Display.getHeight(), currentLevel.menuBackground);
			controllerTimer -= delta;
			buttonWait -= delta;
			hvlDrawQuadc(256, 500, 50, 50, Main.getTexture(Main.Y_INDEX));
			hvlDrawQuadc(256, 560, 50, 50, Main.getTexture(Main.W_INDEX));
			hvlDrawQuadc(256, Display.getHeight()/2+50, -170, 170, Main.black.standing);
			
			hvlDrawQuadc(512, 500, 50, 50, Main.getTexture(Main.X_INDEX));
			hvlDrawQuadc(512, 560, 50, 50, Main.getTexture(Main.A_KEY_INDEX));
			hvlDrawQuadc(512, Display.getHeight()/2+50, -170, 170, Main.blue.standing);
			
			hvlDrawQuadc(768, 500, 50, 50, Main.getTexture(Main.A_INDEX));
			hvlDrawQuadc(768, 560, 50, 50, Main.getTexture(Main.S_INDEX));
			hvlDrawQuadc(768, Display.getHeight()/2+50, -170, 170, Main.green.standing);
			
			hvlDrawQuadc(1024, 500, 50, 50, Main.getTexture(Main.B_INDEX));
			hvlDrawQuadc(1024, 560, 50, 50, Main.getTexture(Main.D_INDEX));
			hvlDrawQuadc(1024, Display.getHeight()/2+50, -170, 170, Main.red.standing);
			
			Main.font.drawWord("Player 1: ", 10, 20, currentLevel.textColor, 0.2f);
			hvlDrawQuad(160, -5, 75, 75, p1A.standing);
			Main.font.drawWord("Player 2: ", 10, 85, currentLevel.textColor, 0.2f);
			hvlDrawQuad(160, 60, 75, 75, p2A.standing);
			Main.font.drawWord("Player 3: ", 10, 150, currentLevel.textColor, 0.2f);
			hvlDrawQuad(160, 125, 75, 75, p3A.standing);
			Main.font.drawWord("Player 4: ", 10, 215, currentLevel.textColor, 0.2f);
			hvlDrawQuad(160, 190, 75, 75, p4A.standing);
			
			timerBar(controllerTimer/CONTROLLER_TIME);
			Main.font.drawWordc("Select your characters!", Display.getWidth()/2, 680, Color.black, 0.24f);
			
			if(Controllers.allX[p1index] == 1 && buttonWait <= 0) {p1A = Main.blue; buttonWait = BUTTON_WAIT_TIME;}
			if(Controllers.allX[p2index] == 1 && buttonWait <= 0) {p2A = Main.blue; buttonWait = BUTTON_WAIT_TIME;}
			if(Controllers.allX[p3index] == 1 && buttonWait <= 0) {p3A = Main.blue; buttonWait = BUTTON_WAIT_TIME;}
			if(Controllers.allX[p4index] == 1 && buttonWait <= 0) {p4A = Main.blue; buttonWait = BUTTON_WAIT_TIME;}
			if(p1index == 4 && Keyboard.isKeyDown(Keyboard.KEY_A)) {p1A = Main.blue; buttonWait = BUTTON_WAIT_TIME;}
			if(p2index == 4 && Keyboard.isKeyDown(Keyboard.KEY_A)) {p2A = Main.blue; buttonWait = BUTTON_WAIT_TIME;}
			if(p3index == 4 && Keyboard.isKeyDown(Keyboard.KEY_A)) {p3A = Main.blue; buttonWait = BUTTON_WAIT_TIME;}
			if(p4index == 4 && Keyboard.isKeyDown(Keyboard.KEY_A)) {p4A = Main.blue; buttonWait = BUTTON_WAIT_TIME;}
			
			if(Controllers.allY[p1index] == 1 && buttonWait <= 0) {p1A = Main.black; buttonWait = BUTTON_WAIT_TIME;}
			if(Controllers.allY[p2index] == 1 && buttonWait <= 0) {p2A = Main.black; buttonWait = BUTTON_WAIT_TIME;}
			if(Controllers.allY[p3index] == 1 && buttonWait <= 0) {p3A = Main.black; buttonWait = BUTTON_WAIT_TIME;}
			if(Controllers.allY[p4index] == 1 && buttonWait <= 0) {p4A = Main.black; buttonWait = BUTTON_WAIT_TIME;}
			if(p1index == 4 && Keyboard.isKeyDown(Keyboard.KEY_W)) {p1A = Main.black; buttonWait = BUTTON_WAIT_TIME;}
			if(p2index == 4 && Keyboard.isKeyDown(Keyboard.KEY_W)) {p2A = Main.black; buttonWait = BUTTON_WAIT_TIME;}
			if(p3index == 4 && Keyboard.isKeyDown(Keyboard.KEY_W)) {p3A = Main.black; buttonWait = BUTTON_WAIT_TIME;}
			if(p4index == 4 && Keyboard.isKeyDown(Keyboard.KEY_W)) {p4A = Main.black; buttonWait = BUTTON_WAIT_TIME;}
			
			if(Controllers.allB[p1index] == 1 && buttonWait <= 0) {p1A = Main.red; buttonWait = BUTTON_WAIT_TIME;}
			if(Controllers.allB[p2index] == 1 && buttonWait <= 0) {p2A = Main.red; buttonWait = BUTTON_WAIT_TIME;}
			if(Controllers.allB[p3index] == 1 && buttonWait <= 0) {p3A = Main.red; buttonWait = BUTTON_WAIT_TIME;}
			if(Controllers.allB[p4index] == 1 && buttonWait <= 0) {p4A = Main.red; buttonWait = BUTTON_WAIT_TIME;}
			if(p1index == 4 && Keyboard.isKeyDown(Keyboard.KEY_D)) {p1A = Main.red; buttonWait = BUTTON_WAIT_TIME;}
			if(p2index == 4 && Keyboard.isKeyDown(Keyboard.KEY_D)) {p2A = Main.red; buttonWait = BUTTON_WAIT_TIME;}
			if(p3index == 4 && Keyboard.isKeyDown(Keyboard.KEY_D)) {p3A = Main.red; buttonWait = BUTTON_WAIT_TIME;}
			if(p4index == 4 && Keyboard.isKeyDown(Keyboard.KEY_D)) {p4A = Main.red; buttonWait = BUTTON_WAIT_TIME;}
			
			if(Controllers.allA[p1index] == 1 && buttonWait <= 0) {p1A = Main.green; buttonWait = BUTTON_WAIT_TIME;}
			if(Controllers.allA[p2index] == 1 && buttonWait <= 0) {p2A = Main.green; buttonWait = BUTTON_WAIT_TIME;}
			if(Controllers.allA[p3index] == 1 && buttonWait <= 0) {p3A = Main.green; buttonWait = BUTTON_WAIT_TIME;}
			if(Controllers.allA[p4index] == 1 && buttonWait <= 0) {p4A = Main.green; buttonWait = BUTTON_WAIT_TIME;}
			if(p1index == 4 && Keyboard.isKeyDown(Keyboard.KEY_S)) {p1A = Main.green; buttonWait = BUTTON_WAIT_TIME;}
			if(p2index == 4 && Keyboard.isKeyDown(Keyboard.KEY_S)) {p2A = Main.green; buttonWait = BUTTON_WAIT_TIME;}
			if(p3index == 4 && Keyboard.isKeyDown(Keyboard.KEY_S)) {p3A = Main.green; buttonWait = BUTTON_WAIT_TIME;}
			if(p4index == 4 && Keyboard.isKeyDown(Keyboard.KEY_S)) {p4A = Main.green; buttonWait = BUTTON_WAIT_TIME;}
			if(controllerTimer <= 0) {
				int gen = HvlMath.randomIntBetween(0, genres.length);
				chosenGenre = genres[gen];
				HvlMenu.setCurrent(genre);
			}
		}
		else if (HvlMenu.getCurrent() == genre){
			genreTimer -= delta;
			hvlDrawQuad(0, 0, Display.getWidth(), Display.getHeight(), currentLevel.menuBackground);
			Main.font.drawWordc("Your Genre is:", Display.getWidth()/2, 100, currentLevel.textColor, 0.8f);
			Main.font.drawWordc(chosenGenre, Display.getWidth()/2, Display.getHeight()/2, currentLevel.textColor);
			
			timerBar(genreTimer/3f);
			if(genreTimer <= 0) {
				Game.initGame(p1index, p2index, p3index, p4index, p1A, p2A, p3A, p4A);
				Main.getSound(songs[currentSong]).stop();
				HvlMenu.setCurrent(game);
			}
		}
		else if(HvlMenu.getCurrent() == game) {
			Game.updateGame(delta);
		}
		else if(HvlMenu.getCurrent() == singing) {
			hvlDrawQuad((currentLevel.background == Main.level2 ? 0 : -64), (currentLevel.background == Main.level2 ? 0 : -350), (currentLevel.background == Main.level2 ? Display.getWidth() : Display.getWidth() + 128),
					(currentLevel.background == Main.level2 ? Display.getHeight() : Display.getWidth()+128), currentLevel.background);
			Main.font.drawWordc("Player " + (singingPlayer+1), Display.getWidth()/2, 100, currentLevel.textColor, 0.8f);
			singTimer -= delta;
			if(singingPlayer == 0) {
				hvlDrawQuadc(Display.getWidth()/2 + 100, Display.getHeight() - 200, 250, 250, Game.player1.animations.standing);
				hvlDrawQuadc(Display.getWidth()/2 + 80, Display.getHeight() - 180, 80, 80, Main.getTexture(Main.MIC_INDEX));
				if(!playerSang) {
					sing(Game.player1.playerWords, lyrics[HvlMath.randomIntBetween(0, lyrics.length)]);
					playerSang = true;
				}
				
				if(singTimer <= 0) {
					singTimer = SONG_TIME;
					playerSang = false;
					singingPlayer++;
				}
			}
			/*
			if(i == 1) {
				hvlDrawQuadc(Display.getWidth()/2 + 100, Display.getHeight() - 200, 250, 250, Game.player2.animations.standing);
				hvlDrawQuadc(Display.getWidth()/2 + 80, Display.getHeight() - 180, 80, 80, Main.getTexture(Main.MIC_INDEX));
				//sing(Game.player2.playerWords);
			}
			if(i == 2) {
				hvlDrawQuadc(Display.getWidth()/2 + 100, Display.getHeight() - 200, 250, 250, Game.player3.animations.standing);
				hvlDrawQuadc(Display.getWidth()/2 + 80, Display.getHeight() - 180, 80, 80, Main.getTexture(Main.MIC_INDEX));
				//sing(Game.player3.playerWords);
			}
			if(i == 3) {
				hvlDrawQuadc(Display.getWidth()/2 + 100, Display.getHeight() - 200, 250, 250, Game.player4.animations.standing);
				hvlDrawQuadc(Display.getWidth()/2 + 80, Display.getHeight() - 180, 80, 80, Main.getTexture(Main.MIC_INDEX));
				//sing(Game.player4.playerWords);
			}
			*/
		}
		
		
		if(HvlMenu.getCurrent() == menu || HvlMenu.getCurrent() == controllerInit || HvlMenu.getCurrent() == charSelect || HvlMenu.getCurrent() == game || HvlMenu.getCurrent() == genre) {
			if(Main.options.backgroundMusicEnabled && playedMenuMusic == false) {
				Main.getSound(songs[currentSong]).playAsSoundEffect(1f, 1f, false);
				playedMenuMusic = true;
			}
			if(Main.getSound(songs[currentSong]).isPlaying() == false) {
				currentSong = HvlMath.randomIntBetween(0, 3);
				playedMenuMusic = false;
			}
		}
		
		HvlMenu.updateMenus(delta);
	}

	public static void sing(ArrayList<Word> words, String lyrics) {
		Main.getSound(Main.JAZZ_INDEX).stop();
		Main.getSound(Main.METAL_INDEX).stop();
		Main.getSound(Main.FUNKY_INDEX).stop();
		if(chosenGenre.equals("Jazz")) {
			Main.getSound(Main.JAZZ_INDEX).playAsSoundEffect(1, 1, false);
		} else if(chosenGenre.equals("Funk")) { 
			Main.getSound(Main.FUNKY_INDEX).playAsSoundEffect(1, 1, false);
		}else if(chosenGenre.equals("Metal")) { 
			Main.getSound(Main.METAL_INDEX).playAsSoundEffect(1, 1, false);
		}
		Scanner lyricReader;
		String song;
		String newSong = "";
		int countDown = 1;
		try {
			lyricReader = new Scanner(new FileInputStream(lyrics));
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Lyric Sheet not found!");
		}
		song = lyricReader.nextLine();
		String [] lyricWords = song.split(" ");
		String [] newWords = new String[lyricWords.length];
		ArrayList<String> nouns = new ArrayList<>();
		ArrayList<String> verbs = new ArrayList<>();
		ArrayList<String> adjs = new ArrayList<>();
		ArrayList<String> advs = new ArrayList<>();
		
		for(int i = 0; i < words.size(); i++) {
			String word = words.get(i).text;
			switch(words.get(i).type) {
				case 0:
					nouns.add(word);
					break;
				case 1:
					verbs.add(word);
					break;
				case 2:
					adjs.add(word);
					break;
				case 3:
					advs.add(word);
					break;
			}
		}
		
		for(int k = 0; k < lyricWords.length; k++) {
			if(lyricWords[k].equals("n") && nouns.size() > 0) {
				newWords[k] = nouns.get(nouns.size()-1);
				nouns.remove(nouns.size()-1);
			} else if(lyricWords[k].equals("v") && verbs.size() > 0) {
				newWords[k] = verbs.get(verbs.size()-1);
				verbs.remove(verbs.size()-1);
			}else if(lyricWords[k].equals("adj") && adjs.size() > 0) {
				newWords[k] = adjs.get(adjs.size()-1);
				adjs.remove(adjs.size()-1);
			}else if(lyricWords[k].equals("adv") && advs.size() > 0) {
				newWords[k] = advs.get(advs.size()-1);
				advs.remove(advs.size()-1);
			}else {
				newWords[k] = lyricWords[k];
			}
		}
		
		for(int j = 0; j < newWords.length; j++) {
			newSong += newWords[j];
		}
		Runnable talk = new TTSReader(newSong);
		new Thread(talk).start();
	}
	
	public static void resetGame() {
		currentLevel.elements.clear();
		currentLevel.projs.clear();
		currentLevel.weapons.clear();
		currentLevel.words.clear();
		WordManager.initWords();
		pickLevel = HvlMath.randomIntBetween(0, 6);
		p1index = 3;
		p2index = 3;
		p3index = 3; 
		p4index = 3;
		switch(pickLevel) {
			case 0:
				currentLevel = new HomeBase();
				break;
			case 1:
				currentLevel = new Overworld();
				break;
			case 2:
				currentLevel = new Battlefield();
				break;
			case 3:
				currentLevel = new Skyrise();
				break;
			case 4:
				currentLevel = new MoonRise();
				break;
			case 5:
				currentLevel = new Omega();
				break;
			default:
				currentLevel = new Overworld();
				break;
		}
		genreTimer = 3f;
		HvlMenu.setCurrent(menu);
	}
}
