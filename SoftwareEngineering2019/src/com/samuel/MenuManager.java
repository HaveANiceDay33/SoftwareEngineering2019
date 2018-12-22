package com.samuel;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuad;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.action.HvlAction1;
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

public class MenuManager {
	public static HvlMenu intro, intro2, menu, game;
	private static float whiteFade = 0f;
	public static final float BUTTON_WIDTH = 256f, BUTTON_HEIGHT = 96f;
	static boolean playedSound = false;
	
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
		game = new HvlMenu();
		
		menu.add(new HvlArrangerBox.Builder().setStyle(ArrangementStyle.VERTICAL).build());
		menu.getFirstArrangerBox().add(new HvlSpacer(0f, 32f));
		menu.getFirstArrangerBox().add(new HvlLabeledButton.Builder().setText("Start").setX(Display.getWidth()/2).setY(Display.getHeight()/2 - BUTTON_HEIGHT/2).setClickedCommand(new HvlAction1<HvlButton>(){
			@Override
			public void run(HvlButton aArg){
				Game.initGame();
				HvlMenu.setCurrent(game);
			}
		}).build());
		
		Controllers.initControllers();

		HvlMenu.setCurrent(intro);
	}
	private static float introProgress = 0f;
	
	public static void update(float delta){
		if(!playedSound) {
			Main.getSound(Main.GEAR_RUN_INDEX).playAsSoundEffect(1f, 1f, false);
			playedSound = true;
		}
		whiteFade = HvlMath.stepTowards(whiteFade, delta, 0f);
		if(HvlMenu.getCurrent() == intro){
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
			if(introProgress >= 1f || (introProgress > 0.25f && (Mouse.isButtonDown(0) || Controllers.allA[4] == 1))) {HvlMenu.setCurrent(menu);}
			float alpha = 1f - (Math.abs(introProgress - 0.5f)*2f);
			
			hvlDrawQuadc(Display.getWidth()/2, Display.getHeight()/2-70, 512, 342, Main.getTexture(Main.CVILLE_INDEX), new Color(1f, 1f, 1f, alpha));
			hvlDrawQuadc(Display.getWidth()/2+15, Display.getHeight()/2+170, 688, 86, Main.getTexture(Main.C_TEXT_INDEX), new Color(1f, 1f, 1f, alpha));
		}
		else if(HvlMenu.getCurrent() == menu) {
			if(Controllers.allA[4] == 1) {
				Game.initGame();
				HvlMenu.setCurrent(game);
			}
		}
		else if(HvlMenu.getCurrent() == game) {
			Game.updateGame(delta);
		}
		
		HvlMenu.updateMenus(delta);
	}
}
