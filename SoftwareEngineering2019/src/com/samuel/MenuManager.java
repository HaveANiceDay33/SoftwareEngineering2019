package com.samuel;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuad;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.action.HvlAction1;
import com.osreboot.ridhvl.menu.HvlComponentDefault;
import com.osreboot.ridhvl.menu.HvlMenu;
import com.osreboot.ridhvl.menu.component.HvlArrangerBox;
import com.osreboot.ridhvl.menu.component.HvlButton;
import com.osreboot.ridhvl.menu.component.HvlComponentDrawable;
import com.osreboot.ridhvl.menu.component.collection.HvlLabeledButton;

public class MenuManager {
	public static HvlMenu intro, intro2, menu, game;
	private static float whiteFade = 0f;
	public static final float BUTTON_WIDTH = 256f, BUTTON_HEIGHT = 96f;
	
	public static void initialize() {
		
		HvlComponentDefault.setDefault(HvlLabeledButton.class, new HvlLabeledButton.Builder().setWidth(100).setHeight(50).setFont(Main.font).setTextColor(Color.cyan).setTextScale(0.2f).setOnDrawable(new HvlComponentDrawable() {
			@Override
			public void draw(float delta, float x, float y, float width, float height) {
				hvlDrawQuad(x,y,width,height,Color.lightGray);	
			}
		}).setOffDrawable(new HvlComponentDrawable() {
			@Override
			public void draw(float delta, float x, float y, float width, float height) {
				hvlDrawQuad(x,y,width,height,Color.darkGray);
			}
		}).setHoverDrawable(new HvlComponentDrawable() {
			@Override
			public void draw(float delta, float x, float y, float width, float height) {
				hvlDrawQuad(x,y,width,height,Color.gray);
			}
		}).build());
		
		intro = new HvlMenu();
		intro2 = new HvlMenu();
		menu = new HvlMenu();
		game = new HvlMenu();
		
		menu.add(new HvlArrangerBox.Builder().setStyle(HvlArrangerBox.ArrangementStyle.VERTICAL).setWidth(1920).setHeight(1080).setX(0).setY(0).build());
		menu.getFirstArrangerBox().add(new HvlLabeledButton.Builder().setText("Start").setX(Display.getWidth()/2).setY(Display.getHeight()/2).setClickedCommand(new HvlAction1<HvlButton>(){
			@Override
			public void run(HvlButton aArg){
				Game.initGame();
				HvlMenu.setCurrent(game);
			}
		}).build());
		
		HvlMenu.setCurrent(intro);
	}
	private static float introProgress = 0f;
	
	public static void update(float delta){
		whiteFade = HvlMath.stepTowards(whiteFade, delta, 0f);
		if(HvlMenu.getCurrent() == intro){
			//UPDATING THE INTRO MENU//
			introProgress += delta/4f;
			if(introProgress >= 1f || (introProgress > 0.25f && Mouse.isButtonDown(0))) {
				introProgress = 0f;
				HvlMenu.setCurrent(intro2);
			}
			float alpha = 1f - (Math.abs(introProgress - 0.5f)*2f);
			hvlDrawQuadc(Display.getWidth()/2, Display.getHeight()/2, 512, 512, Main.getTexture(Main.CRATE_INDEX), new Color(1f, 1f, 1f, alpha));
		}
		else if(HvlMenu.getCurrent() == intro2){
			//UPDATING THE INTRO MENU//
			introProgress += delta/4f;
			if(introProgress >= 1f || (introProgress > 0.25f && Mouse.isButtonDown(0))) {HvlMenu.setCurrent(menu);}
			float alpha = 1f - (Math.abs(introProgress - 0.5f)*2f);
			hvlDrawQuadc(Display.getWidth()/2, Display.getHeight()/2, 512, 512, Main.getTexture(Main.LEVEL_ONE_INDEX), new Color(1f, 1f, 1f, alpha));
		}
		else if(HvlMenu.getCurrent() == menu) {
			
		}
		else if(HvlMenu.getCurrent() == game) {
			Game.updateGame(delta);
		}
	}
}
