package com.samuel;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuad;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;

import java.util.ArrayList;

import org.lwjgl.input.Controller;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.action.HvlAction1;
import com.osreboot.ridhvl.input.HvlController;
import com.osreboot.ridhvl.input.HvlControllerProfile;
import com.osreboot.ridhvl.input.collection.HvlCPG_Gamepad;
import com.osreboot.ridhvl.menu.HvlComponentDefault;
import com.osreboot.ridhvl.menu.HvlMenu;
import com.osreboot.ridhvl.menu.component.HvlArrangerBox;
import com.osreboot.ridhvl.menu.component.HvlArrangerBox.ArrangementStyle;
import com.osreboot.ridhvl.menu.component.HvlButton;
import com.osreboot.ridhvl.menu.component.HvlComponentDrawable;
import com.osreboot.ridhvl.menu.component.HvlSpacer;
import com.osreboot.ridhvl.menu.component.collection.HvlLabeledButton;
import com.osreboot.ridhvl.painter.HvlAnimatedTextureUV;
import com.samuel.LevelProfiles.Battlefield;

public class MenuManager {
	public static final float BUTTON_WIDTH = 256f, BUTTON_HEIGHT = 96f;
	private static final float CONTROLLER_TIME = 5f;
	private static final float BUTTON_WAIT_TIME = 0.5f;
	
	public static HvlMenu intro, intro2, menu, controllerInit, charSelect, game;
	private static float whiteFade = 0;
	static boolean playedSound = false;
	static Level currentLevel;
	private static void timerBar(float time) {
		hvlDrawQuad(200, 620, Display.getWidth()-400, 20, Color.gray);
		hvlDrawQuadc(200, 630, 5, 40, Color.green);
		hvlDrawQuadc(Display.getWidth()-200, 630, 5, 40, Color.green);
		hvlDrawQuad(200, 620, (Display.getWidth()-400)*time, 20, Color.green);
	}
	
	public static void initialize() {
		HvlComponentDefault.setDefault(new HvlArrangerBox(Display.getWidth(), Display.getHeight(), HvlArrangerBox.ArrangementStyle.HORIZONTAL));
		HvlComponentDefault.setDefault(HvlLabeledButton.class, new HvlLabeledButton.Builder().setWidth(BUTTON_WIDTH).setHeight(BUTTON_HEIGHT).setFont(Main.font).setTextColor(Color.cyan).setTextScale(0.2f).setOnDrawable(new HvlComponentDrawable() {
			@Override
			public void draw(float delta, float x, float y, float width, float height) {
				hvlDrawQuad(x,y,width,height, Main.getTexture(Main.CRATE_INDEX), Color.lightGray);	
			}
		}).setOffDrawable(new HvlComponentDrawable() {
			@Override
			public void draw(float delta, float x, float y, float width, float height) {
				hvlDrawQuad(x,y,width,height, Main.getTexture(Main.CRATE_INDEX), Color.darkGray);
			}
		}).setHoverDrawable(new HvlComponentDrawable() {
			@Override
			public void draw(float delta, float x, float y, float width, float height) {
				hvlDrawQuad(x,y,width,height, Main.getTexture(Main.CRATE_INDEX),Color.gray);
			}
		}).build());
		
		intro = new HvlMenu();
		intro2 = new HvlMenu();
		menu = new HvlMenu();
		controllerInit = new HvlMenu();
		charSelect = new HvlMenu();
		game = new HvlMenu();
		
		menu.add(new HvlArrangerBox.Builder().setStyle(ArrangementStyle.VERTICAL).build());
		menu.getFirstArrangerBox().add(new HvlSpacer(0f, 32f));
		menu.getFirstArrangerBox().add(new HvlLabeledButton.Builder().setText("Start").setX(Display.getWidth()/2).setY(Display.getHeight()/2 - BUTTON_HEIGHT/2).setClickedCommand(new HvlAction1<HvlButton>(){
			@Override
			public void run(HvlButton aArg){
				currentPlayer = 0;
				controllerTimer = CONTROLLER_TIME;
				HvlMenu.setCurrent(controllerInit);
			}
		}).build());
		
		currentLevel = new Battlefield();
		
		Controllers.initControllers();
		//Game.initGame(p1index, p2index, p3index, p4index, p1A, p2A, p3A, p4A);
		HvlMenu.setCurrent(intro);
	}
	
	private static float introProgress = 0f;
	private static float controllerTimer = CONTROLLER_TIME;
	private static float buttonWait = BUTTON_WAIT_TIME;
	public static int p1index = 3, p2index = 3, p3index = 3, p4index = 3; //default
	public static AnimatedTextureGroup p1A = Main.blue, p2A = Main.blue, p3A = Main.blue, p4A = Main.blue;
	public static int currentPlayer = 0;
	
	public static void update(float delta){

		whiteFade = HvlMath.stepTowards(whiteFade, delta, 0f);
		if(HvlMenu.getCurrent() == intro){
			if(!playedSound) {
				Main.getSound(Main.GEAR_RUN_INDEX).playAsSoundEffect(1f, 1f, false);
				playedSound = true;
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
			
			hvlDrawQuadc(Display.getWidth()/2, Display.getHeight()/2-70, 512, 342, Main.getTexture(Main.CVILLE_INDEX), new Color(1f, 1f, 1f, alpha));
			hvlDrawQuadc(Display.getWidth()/2+15, Display.getHeight()/2+170, 688, 86, Main.getTexture(Main.C_TEXT_INDEX), new Color(1f, 1f, 1f, alpha));
		}
		else if(HvlMenu.getCurrent() == menu) {
			buttonWait-=delta;
			if(Controllers.allA[4] == 1 && buttonWait <= 0) {
				currentPlayer = 0;
				controllerTimer = CONTROLLER_TIME;
				buttonWait = BUTTON_WAIT_TIME;
				HvlMenu.setCurrent(controllerInit);
			}
		}
		else if(HvlMenu.getCurrent() == controllerInit) {
			controllerTimer -= delta;
			buttonWait -= delta;
			Main.font.drawWordc("Player "+(currentPlayer+1)+":", Display.getWidth()/2,100, Color.white, 0.3f);
			Main.font.drawWordc("Press A on Xbox controller or W on Keyboard", Display.getWidth()/2, 200, Color.white, 0.22f);
			timerBar(controllerTimer/CONTROLLER_TIME);
			if(currentPlayer == 0 && buttonWait <= 0) {
				if(Controllers.allA[0] == 1) {p1index = 0; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME;}
				if(Controllers.allA[1] == 1) {p1index = 1; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME;}
				if(Controllers.allA[2] == 1) {p1index = 2; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME;}
				if(Controllers.allA[3] == 1) {p1index = 3; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME;}
				if(Keyboard.isKeyDown(Keyboard.KEY_W)) {p1index = 4; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME;}
			} else if (currentPlayer == 1 && buttonWait <= 0) {
				if(Controllers.allA[0] == 1) {p2index = 0; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME;}
				if(Controllers.allA[1] == 1) {p2index = 1; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME;}
				if(Controllers.allA[2] == 1) {p2index = 2; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME;}
				if(Controllers.allA[3] == 1) {p2index = 3; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME;}
				if(Keyboard.isKeyDown(Keyboard.KEY_W)) {p2index = 4; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME;}
			} else if (currentPlayer == 2 && buttonWait <= 0) {
				if(Controllers.allA[0] == 1) {p3index = 0; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME;}
				if(Controllers.allA[1] == 1) {p3index = 1; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME;}
				if(Controllers.allA[2] == 1) {p3index = 2; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME;}
				if(Controllers.allA[3] == 1) {p3index = 3; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME;}
				if(Keyboard.isKeyDown(Keyboard.KEY_W)) {p3index = 4; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME;}
			} else if (currentPlayer == 3 && buttonWait <= 0) {
				if(Controllers.allA[0] == 1) {p4index = 0; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME;}
				if(Controllers.allA[1] == 1) {p4index = 1; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME;}
				if(Controllers.allA[2] == 1) {p4index = 2; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME;}
				if(Controllers.allA[3] == 1) {p4index = 3; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME;}
				if(Keyboard.isKeyDown(Keyboard.KEY_W)) {p4index = 4; currentPlayer++; controllerTimer = CONTROLLER_TIME; buttonWait = BUTTON_WAIT_TIME;}
			} else if (currentPlayer > 3) {
				currentPlayer = 0;
				controllerTimer = CONTROLLER_TIME*2;
				HvlMenu.setCurrent(charSelect);
			}
			if(controllerTimer <= 0) {
				currentPlayer++;
				controllerTimer = CONTROLLER_TIME;
			}

		}else if(HvlMenu.getCurrent() == charSelect) {
			controllerTimer -= delta;
			buttonWait -= delta;
			hvlDrawQuadc(256, 500, 50, 50, Main.getTexture(Main.A_INDEX));
			hvlDrawQuadc(512, 500, 50, 50, Main.getTexture(Main.B_INDEX));
			hvlDrawQuadc(768, 500, 50, 50, Main.getTexture(Main.X_INDEX));
			hvlDrawQuadc(768, Display.getHeight()/2+50, -170, 170, Main.blue.moving);
			hvlDrawQuadc(1024, 500, 50, 50, Main.getTexture(Main.Y_INDEX));
			
			Main.font.drawWord("Player 1: ", 10, 20, Color.white, 0.2f);
			hvlDrawQuad(160, -5, 75, 75, p1A.standing);
			Main.font.drawWord("Player 2: ", 10, 85, Color.white, 0.2f);
			hvlDrawQuad(160, 60, 75, 75, p2A.standing);
			Main.font.drawWord("Player 3: ", 10, 150, Color.white, 0.2f);
			hvlDrawQuad(160, 125, 75, 75, p3A.standing);
			Main.font.drawWord("Player 4: ", 10, 215, Color.white, 0.2f);
			hvlDrawQuad(160, 190, 75, 75, p4A.standing);
			
			timerBar(controllerTimer/(CONTROLLER_TIME*2));
			Main.font.drawWordc("Select your characters!", Display.getWidth()/2, 680, Color.white, 0.24f);
			
			if(Controllers.allX[p1index] == 1 && buttonWait <= 0) {p1A = Main.blue; buttonWait = BUTTON_WAIT_TIME;}
			if(Controllers.allX[p2index] == 1 && buttonWait <= 0) {p2A = Main.blue; buttonWait = BUTTON_WAIT_TIME;}
			if(Controllers.allX[p3index] == 1 && buttonWait <= 0) {p3A = Main.blue; buttonWait = BUTTON_WAIT_TIME;}
			if(Controllers.allX[p4index] == 1 && buttonWait <= 0) {p4A = Main.blue; buttonWait = BUTTON_WAIT_TIME;}
			
			if(controllerTimer <= 0) {
				Game.initGame(p1index, p2index, p3index, p4index, p1A, p2A, p3A, p4A);
				HvlMenu.setCurrent(game);
			}
		}
		else if(HvlMenu.getCurrent() == game) {
			Game.updateGame(delta);
		}
		
		HvlMenu.updateMenus(delta);
	}
}
