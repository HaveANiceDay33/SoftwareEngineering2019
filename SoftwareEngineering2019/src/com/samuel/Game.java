package com.samuel;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuad;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.action.HvlAction0;
import com.osreboot.ridhvl.input.HvlControllerProfile;
import com.osreboot.ridhvl.input.collection.HvlCPG_Gamepad;
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
	static float x1, y1, x2, y2, x3, y3, x4, y4;

	public static void drawBack(float x, float y, Texture texture) {
		hvlDrawQuadc(x, y, BACK_X, BACK_Y, texture);
	}
	
	public static void drawUI(Player player) {
		Main.font.drawWord("Player "+(player.id+1), 20, 20, Color.black, 0.3f);
	}

	public static void initGame(int p1, int p2, int p3 ,int p4, 
			AnimatedTextureGroup p1Ani, AnimatedTextureGroup p2Ani, AnimatedTextureGroup p3Ani, AnimatedTextureGroup p4Ani) {
		x1 = x2 = x3 = x4 = Display.getWidth()/2; 
		y1 = y2 = y3 = y4 = Display.getHeight()/2;
		
		player1 = new Player(0, p1, x1, y1, p1Ani.standing, p1Ani.moving);
		player2 = new Player(1, p2, x2, y2, p2Ani.standing, p2Ani.moving);
		player3 = new Player(2, p3, x3, y3, p3Ani.standing, p3Ani.moving);
		player4 = new Player(3, p4, x4, y4, p4Ani.standing, p4Ani.moving);
		
		try {
			p1R = new HvlRenderFrame(Display.getWidth(), Display.getHeight());
			p2R = new HvlRenderFrame(Display.getWidth(), Display.getHeight());
			p3R = new HvlRenderFrame(Display.getWidth(), Display.getHeight());
			p4R = new HvlRenderFrame(Display.getWidth(), Display.getHeight());
		} catch (FBOUnsupportedException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateGame(float delta) {
		p1R.doCapture(new HvlAction0() { //player 1 
			@Override
			public void run() {
				drawBack(player1.get_x(), player1.get_y(), Main.getTexture(Main.LEVEL_ONE_INDEX));
				drawUI(player1);
				hvlDrawQuadc(player1.get_x() + -player2.get_x() + Display.getWidth()/2, player1.get_y() + -player2.get_y() + Display.getHeight()/2, player2.get_width(), Player.PLAYER_SIZE, player2.get_animation()); //render player 2
				hvlDrawQuadc(player1.get_x() + -player3.get_x() + Display.getWidth()/2, player1.get_y() + -player3.get_y() + Display.getHeight()/2, player3.get_width(), Player.PLAYER_SIZE, player3.get_animation()); //render player 3
				hvlDrawQuadc(player1.get_x() + -player4.get_x() + Display.getWidth()/2, player1.get_y() + -player4.get_y() + Display.getHeight()/2, player4.get_width(), Player.PLAYER_SIZE, player4.get_animation());//render player 4
				player1.update(delta);
			}
		});
		p2R.doCapture(new HvlAction0() { //player 2 
			@Override
			public void run() {
				drawBack(player2.get_x(), player2.get_y(), Main.getTexture(Main.LEVEL_ONE_INDEX));
				drawUI(player2);
				hvlDrawQuadc(player2.get_x() + -player1.get_x() + Display.getWidth()/2, player2.get_y() + -player1.get_y() + Display.getHeight()/2, player1.get_width(), Player.PLAYER_SIZE, player1.get_animation()); //render player 1
				hvlDrawQuadc(player2.get_x() + -player3.get_x() + Display.getWidth()/2, player2.get_y() + -player3.get_y() + Display.getHeight()/2, player3.get_width(), Player.PLAYER_SIZE, player3.get_animation()); //render player 3
				hvlDrawQuadc(player2.get_x() + -player4.get_x() + Display.getWidth()/2, player2.get_y() + -player4.get_y() + Display.getHeight()/2, player4.get_width(), Player.PLAYER_SIZE, player4.get_animation()); //render player 4
				player2.update(delta);
			}
		});
		p3R.doCapture(new HvlAction0() { //player 3 
			@Override
			public void run() {
				drawBack(player3.get_x(), player3.get_y(), Main.getTexture(Main.LEVEL_ONE_INDEX));
				drawUI(player3);
				hvlDrawQuadc(player3.get_x() + -player2.get_x() + Display.getWidth()/2, player3.get_y() + -player2.get_y() + Display.getHeight()/2, player2.get_width(), Player.PLAYER_SIZE, player2.get_animation()); //render player 2
				hvlDrawQuadc(player3.get_x() + -player1.get_x() + Display.getWidth()/2, player3.get_y() + -player1.get_y() + Display.getHeight()/2, player1.get_width(), Player.PLAYER_SIZE, player1.get_animation()); //render player 1
				hvlDrawQuadc(player3.get_x() + -player4.get_x() + Display.getWidth()/2, player3.get_y() + -player4.get_y() + Display.getHeight()/2, player4.get_width(), Player.PLAYER_SIZE, player4.get_animation()); //render player 4
				player3.update(delta);
			}
		});
		p4R.doCapture(new HvlAction0() { //player 4 
			@Override
			public void run() {
				drawBack(player4.get_x(), player4.get_y(), Main.getTexture(Main.LEVEL_ONE_INDEX));
				drawUI(player4);
				hvlDrawQuadc(player4.get_x() + -player2.get_x() + Display.getWidth()/2, player4.get_y() + -player2.get_y() + Display.getHeight()/2, player2.get_width(), Player.PLAYER_SIZE, player2.get_animation()); //render player 2
				hvlDrawQuadc(player4.get_x() + -player1.get_x() + Display.getWidth()/2, player4.get_y() + -player1.get_y() + Display.getHeight()/2, player1.get_width(), Player.PLAYER_SIZE, player1.get_animation()); //render player 1
				hvlDrawQuadc(player4.get_x() + -player3.get_x() + Display.getWidth()/2, player4.get_y() + -player3.get_y() + Display.getHeight()/2, player3.get_width(), Player.PLAYER_SIZE, player3.get_animation()); //render player 3
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
