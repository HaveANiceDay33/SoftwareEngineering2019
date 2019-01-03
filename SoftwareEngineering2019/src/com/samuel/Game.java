package com.samuel;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuad;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.action.HvlAction0;
import com.osreboot.ridhvl.painter.HvlRenderFrame;
import com.osreboot.ridhvl.painter.HvlRenderFrame.FBOUnsupportedException;

public class Game {
	static final public int BACK_X = 7680;
	static final public int BACK_Y = 4320;
	static final private int BORDER_WIDTH = 6;
	
	public static Player player1, player2, player3, player4;
	static HvlRenderFrame p1R;
	static HvlRenderFrame p2R;
	static HvlRenderFrame p3R;
	static HvlRenderFrame p4R;
	static float fixedX, fixedY;

	public static void drawWorld(float x, float y, float delta) {
		LevelGenerator.update(delta, x, y);
	}
	
	public static void drawUI(Player player) {
		Main.font.drawWord("Player "+(player.id+1), 20, 20, Color.black, 0.3f);
	}

	public static void initGame(int p1, int p2, int p3 ,int p4, 
			AnimatedTextureGroup p1Ani, AnimatedTextureGroup p2Ani, AnimatedTextureGroup p3Ani, AnimatedTextureGroup p4Ani) {
		
		LevelGenerator.init();
		System.out.println("Here");
		fixedX = Display.getWidth()/2; 
		fixedY = Display.getHeight()/2+100;
		
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
	}
	
	public static void updateGame(float delta) {
		WordManager.updateWords(delta);
		p1R.doCapture(new HvlAction0() { //player 1 
			@Override
			public void run() {
				drawWorld(player1.get_x(), player1.get_y(), delta);
				drawUI(player1);
				hvlDrawQuadc(player1.get_x() + -player2.get_x() + fixedX, player1.get_y() + -player2.get_y() + fixedY, player2.get_width(), Player.PLAYER_SIZE, player2.get_animation()); //render player 2
				hvlDrawQuadc(player1.get_x() + -player3.get_x() + fixedX, player1.get_y() + -player3.get_y() + fixedY, player3.get_width(), Player.PLAYER_SIZE, player3.get_animation()); //render player 3
				hvlDrawQuadc(player1.get_x() + -player4.get_x() + fixedX, player1.get_y() + -player4.get_y() + fixedY, player4.get_width(), Player.PLAYER_SIZE, player4.get_animation());//render player 4
				player1.update(delta);
			}
		});
		p2R.doCapture(new HvlAction0() { //player 2 
			@Override
			public void run() {
				drawWorld(player2.get_x(), player2.get_y(), delta);
				drawUI(player2);
				hvlDrawQuadc(player2.get_x() + -player1.get_x() + fixedX, player2.get_y() + -player1.get_y() + fixedY, player1.get_width(), Player.PLAYER_SIZE, player1.get_animation()); //render player 1
				hvlDrawQuadc(player2.get_x() + -player3.get_x() + fixedX, player2.get_y() + -player3.get_y() + fixedY, player3.get_width(), Player.PLAYER_SIZE, player3.get_animation()); //render player 3
				hvlDrawQuadc(player2.get_x() + -player4.get_x() + fixedX, player2.get_y() + -player4.get_y() + fixedY, player4.get_width(), Player.PLAYER_SIZE, player4.get_animation()); //render player 4
				player2.update(delta);
			}
		});
		p3R.doCapture(new HvlAction0() { //player 3 
			@Override
			public void run() {
				drawWorld(player3.get_x(), player3.get_y(), delta);
				drawUI(player3);
				hvlDrawQuadc(player3.get_x() + -player2.get_x() + fixedX, player3.get_y() + -player2.get_y() + fixedY, player2.get_width(), Player.PLAYER_SIZE, player2.get_animation()); //render player 2
				hvlDrawQuadc(player3.get_x() + -player1.get_x() + fixedX, player3.get_y() + -player1.get_y() + fixedY, player1.get_width(), Player.PLAYER_SIZE, player1.get_animation()); //render player 1
				hvlDrawQuadc(player3.get_x() + -player4.get_x() + fixedX, player3.get_y() + -player4.get_y() + fixedY, player4.get_width(), Player.PLAYER_SIZE, player4.get_animation()); //render player 4
				player3.update(delta);
			}
		});
		p4R.doCapture(new HvlAction0() { //player 4 
			@Override
			public void run() {
				drawWorld(player4.get_x(), player4.get_y(), delta);
				drawUI(player4);
				hvlDrawQuadc(player4.get_x() + -player2.get_x() + fixedX, player4.get_y() + -player2.get_y() + fixedY, player2.get_width(), Player.PLAYER_SIZE, player2.get_animation()); //render player 2
				hvlDrawQuadc(player4.get_x() + -player1.get_x() + fixedX, player4.get_y() + -player1.get_y() + fixedY, player1.get_width(), Player.PLAYER_SIZE, player1.get_animation()); //render player 1
				hvlDrawQuadc(player4.get_x() + -player3.get_x() + fixedX, player4.get_y() + -player3.get_y() + fixedY, player3.get_width(), Player.PLAYER_SIZE, player3.get_animation()); //render player 3
				player4.update(delta);
			}
		});
		
		hvlDrawQuad(0, 0, Display.getWidth()/2, Display.getHeight()/2, p1R); //drawing player 1's render frame
		hvlDrawQuad(Display.getWidth()/2, 0, Display.getWidth()/2, Display.getHeight()/2, p2R);//drawing player 2's render frame
		hvlDrawQuad(0, Display.getHeight()/2, Display.getWidth()/2, Display.getHeight()/2, p3R);//drawing player 3's render frame
		hvlDrawQuad(Display.getWidth()/2, Display.getHeight()/2, Display.getWidth()/2, Display.getHeight()/2, p4R);//drawing player 4's render frame
		hvlDrawQuad(Display.getWidth()/2-BORDER_WIDTH/2, 0, BORDER_WIDTH, 1080, Color.black);//drawing division lines
		hvlDrawQuad(0, Display.getHeight()/2-BORDER_WIDTH/2, Display.getWidth(), BORDER_WIDTH, Color.black);//drawing division lines
	}
}
