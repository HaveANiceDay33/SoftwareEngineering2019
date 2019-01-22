package com.samuel;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuad;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.action.HvlAction0;
import com.osreboot.ridhvl.menu.HvlMenu;
import com.osreboot.ridhvl.painter.HvlRenderFrame;
import com.osreboot.ridhvl.painter.HvlRenderFrame.FBOUnsupportedException;

public class Game {
	static final public int BACK_X = 5500;
	static final public int BACK_Y = 5500; 
	static final public float GRAVITY = 7000; //Gravity value, constantly modifies y-velocity
	static final public float DRAG = 6000;//Similar to gravity, but affects x-velocity
	static final public int BORDER_TOP = 1670;
	static final public int BORDER_BOTTOM = -500;
	static final public int BORDER_RIGHT = -1920;
	static final public int BORDER_LEFT = 1920;
	static final private int BORDER_WIDTH = 6;
	static final private float GAME_TIME = 5f;
	static final public float FIXED_X = Display.getWidth()/2;
	static final public float FIXED_Y = Display.getHeight()/2+100;
	
	public static float gameTimer = GAME_TIME;
	
	public static Player player1, player2, player3, player4;
	static HvlRenderFrame p1R;
	static HvlRenderFrame p2R;
	static HvlRenderFrame p3R;
	static HvlRenderFrame p4R;

	public static void drawWorld(float x, float y, float delta) {
		LevelGenerator.update(delta, x, y);
	}
	
	public static void drawUI(Player player) {
		Main.font.drawWord("Player "+(player.id+1), 20, 20, MenuManager.currentLevel.textColor, 0.3f);
		if(player.playerWords.size() == 1) {
			Main.font.drawWord(player.playerWords.get(0).text, Display.getWidth()-300, 20, MenuManager.currentLevel.textColor, 0.3f);
		}else if(player.playerWords.size() == 2) {
			Main.font.drawWord(player.playerWords.get(0).text, Display.getWidth()-300, 20, MenuManager.currentLevel.textColor, 0.3f);
			Main.font.drawWord(player.playerWords.get(1).text, Display.getWidth()-300, 70, MenuManager.currentLevel.textColor, 0.3f);
		}else if(player.playerWords.size() >= 3) {
			Main.font.drawWord(player.playerWords.get(0).text, Display.getWidth()-300, 20, MenuManager.currentLevel.textColor, 0.3f);
			Main.font.drawWord(player.playerWords.get(1).text, Display.getWidth()-300, 70, MenuManager.currentLevel.textColor, 0.3f);
			Main.font.drawWord(player.playerWords.get(2).text, Display.getWidth()-300, 120, MenuManager.currentLevel.textColor, 0.3f);
		}
		if(player.playerWeapon != null) {
			Main.font.drawWord("Ammo: "+player.playerWeapon.ammo, 20, 100, MenuManager.currentLevel.textColor, 0.3f);
		}
		Main.font.drawWordc("Time: "+ String.format("%.1f" ,gameTimer), Display.getWidth()/2, Display.getHeight()-50, MenuManager.currentLevel.textColor, 0.35f);
	}

	public static void initGame(int p1, int p2, int p3 ,int p4, 
			AnimatedTextureGroup p1Ani, AnimatedTextureGroup p2Ani, AnimatedTextureGroup p3Ani, AnimatedTextureGroup p4Ani) {
		
		LevelGenerator.init();
		
		player1 = new Player(0, p1, p1Ani);
		player2 = new Player(1, p2, p2Ani);
		player3 = new Player(2, p3, p3Ani);
		player4 = new Player(3, p4, p4Ani);
		try {
			p1R = new HvlRenderFrame(Display.getWidth(), Display.getHeight());
			p2R = new HvlRenderFrame(Display.getWidth(), Display.getHeight());
			p3R = new HvlRenderFrame(Display.getWidth(), Display.getHeight());
			p4R = new HvlRenderFrame(Display.getWidth(), Display.getHeight());
		}catch (FBOUnsupportedException e){
			throw new RuntimeException("Your PC does not have Frame Buffer Support, do you live in the 80's?");
		}
		
		gameTimer = GAME_TIME;
	}
	
	public static void updateGame(float delta) {
		WordManager.updateWords(delta);
		WeaponManager.updateWeapons(delta);
		gameTimer -= delta;
		if(gameTimer <= 0) {
			Main.getSound(MenuManager.songs[MenuManager.currentSong]).stop();
			HvlMenu.setCurrent(MenuManager.singing);
		}
		p1R.doCapture(new HvlAction0() { //player 1 
			@Override
			public void run() {
				drawWorld(player1.get_x(), player1.get_y(), delta);
				drawUI(player1);
				player2.drawPlayer(player1);
				player3.drawPlayer(player1);
				player4.drawPlayer(player1);
				player1.update(delta);
			}
		});
		p2R.doCapture(new HvlAction0() { //player 2 
			@Override
			public void run() {
				drawWorld(player2.get_x(), player2.get_y(), delta);
				drawUI(player2);
				player1.drawPlayer(player2);
				player3.drawPlayer(player2);
				player4.drawPlayer(player2);
				player2.update(delta);
			}
		});
		p3R.doCapture(new HvlAction0() { //player 3 
			@Override
			public void run() {
				drawWorld(player3.get_x(), player3.get_y(), delta);
				drawUI(player3);
				player1.drawPlayer(player3);
				player2.drawPlayer(player3);
				player4.drawPlayer(player3);
				player3.update(delta);
			}
		});
		p4R.doCapture(new HvlAction0() { //player 4 
			@Override
			public void run() {
				drawWorld(player4.get_x(), player4.get_y(), delta);
				drawUI(player4);
				player1.drawPlayer(player4);
				player2.drawPlayer(player4);
				player3.drawPlayer(player4);
				player4.update(delta);
			}
		});
		MenuManager.pauseFrame.doCapture(true, new HvlAction0(){
			@Override
			public void run(){
				hvlDrawQuad(0, 0, Display.getWidth()/2, Display.getHeight()/2, p1R); //drawing player 1's render frame
				hvlDrawQuad(Display.getWidth()/2, 0, Display.getWidth()/2, Display.getHeight()/2, p2R);//drawing player 2's render frame
				hvlDrawQuad(0, Display.getHeight()/2, Display.getWidth()/2, Display.getHeight()/2, p3R);//drawing player 3's render frame
				hvlDrawQuad(Display.getWidth()/2, Display.getHeight()/2, Display.getWidth()/2, Display.getHeight()/2, p4R);//drawing player 4's render frame
				hvlDrawQuad(Display.getWidth()/2-BORDER_WIDTH/2, 0, BORDER_WIDTH, 1080, Color.black);//drawing division lines
				hvlDrawQuad(0, Display.getHeight()/2-BORDER_WIDTH/2, Display.getWidth(), BORDER_WIDTH, Color.black);//drawing division lines
			}
		});
		
		hvlDrawQuad(0,0, Display.getWidth(), Display.getHeight(), MenuManager.pauseFrame);
	}
}
