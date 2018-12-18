package com.samuel;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuad;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.action.HvlAction0;
import com.osreboot.ridhvl.painter.HvlRenderFrame;
import com.osreboot.ridhvl.painter.HvlRenderFrame.FBOUnsupportedException;

public class Game {
	public static Player player1, player2, player3, player4;
	static HvlRenderFrame p1R;
	static HvlRenderFrame p2R;
	static HvlRenderFrame p3R;
	static HvlRenderFrame p4R;
	static float x1, y1, x2, y2, x3, y3, x4, y4;
	static float vx1, vy1, vx2, vy2, vx3, vy3, vx4, vy4;
	
	static final private float GRAVITY = 100;
	static final private float DRAG = 100;
	static final private float JUMP_POWER = 50;
	static final private float MOVE_SPEED = 20;
	static final private float JUMP_TIMER = 1f;
	static final private int BACK_X = 7680;
	static final private int BACK_Y = 4320;
	static final private int BORDER_WIDTH = 6;
	static float jumpT1, jumpT2, jumpT3, jumpT4 = 0;
	
	public static void updateControls(float delta) {
		jumpT1 -= delta;
		jumpT2 -= delta;
		jumpT3 -= delta;
		jumpT4 -= delta;
		//player 1 controls
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){vx1 = -MOVE_SPEED;}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){vx1 = MOVE_SPEED;}
		if(Keyboard.isKeyDown(Keyboard.KEY_W) && jumpT1 <= 0){vy1 = JUMP_POWER; jumpT1 = JUMP_TIMER;}
		//if(Keyboard.isKeyDown(Keyboard.KEY_S)){vy1 = -20;}
		//player 2 controls 
		if(Keyboard.isKeyDown(Keyboard.KEY_L)){vx2 = -MOVE_SPEED;}
		if(Keyboard.isKeyDown(Keyboard.KEY_J)){vx2 = MOVE_SPEED;}
		if(Keyboard.isKeyDown(Keyboard.KEY_I) && jumpT2 <= 0){vy2 = JUMP_POWER; jumpT2 = JUMP_TIMER;}
		//if(Keyboard.isKeyDown(Keyboard.KEY_K)){vy2 = -20;}
		//player 3 controls
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){vx3 = -MOVE_SPEED;}
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){vx3 = MOVE_SPEED;	}
		if(Keyboard.isKeyDown(Keyboard.KEY_UP) && jumpT3 <= 0){vy3 = JUMP_POWER; jumpT3 = JUMP_TIMER;}
		//if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){vy3 = -20;}
		//player 4 controls
 		if(Keyboard.isKeyDown(Keyboard.KEY_H)){vx4 = -MOVE_SPEED;}
		if(Keyboard.isKeyDown(Keyboard.KEY_F)){vx4 = MOVE_SPEED;}
		if(Keyboard.isKeyDown(Keyboard.KEY_T) && jumpT4 <= 0){vy4 = JUMP_POWER; jumpT4 = JUMP_TIMER;}
		//if(Keyboard.isKeyDown(Keyboard.KEY_G)){vy4 = -20;}
	}
	
	public static void drawBack(float x, float y, Texture texture) {hvlDrawQuadc(x, y, BACK_X, BACK_Y, texture);}
	public static void drawUI(Player player) {
		Main.font.drawWord("Player "+(player.id+1), 20, 20, Color.black, 0.3f);
	}
	
	public static void updateBorderCollisions(int top, int bottom, int right, int left) {
		if(y1 < bottom) {y1 = bottom;} // bottom world border 
		if(y2 < bottom) {y2 = bottom;}
		if(y3 < bottom) {y3 = bottom;}
		if(y4 < bottom) {y4 = bottom;}
		
		if(y1 > top) {y1 = top;}  //top world border 
		if(y1 > top) {y2 = top;}
		if(y1 > top) {y3 = top;}
		if(y1 > top) {y4 = top;}
		
		if(x1 < left) {x1 = left;} // left world border 
		if(x2 < left) {x2 = left;}
		if(x3 < left) {x3 = left;}
		if(x4 < left) {x4 = left;}
		
		if(x1 > right) {x1 = right;} // right world border 
		if(x2 > right) {x2 = right;}
		if(x3 > right) {x3 = right;}
		if(x4 > right) {x4 = right;}
		
	}
	public static void initGame() {
		x1 = x2 = x3 = x4 = Display.getWidth()/2; 
		y1 = y2 = y3 = y4 = Display.getHeight()/2;
		
		vx1 = MOVE_SPEED*2;
		vx2 = -MOVE_SPEED*2;
		vx3 = MOVE_SPEED*3;
		vx4 = -MOVE_SPEED*3;
		
		player1 = new Player(0, x1, y1, Color.blue);
		player2 = new Player(1, x2, y2, Color.green);
		player3 = new Player(2, x3, y3, Color.yellow);
		player4 = new Player(3, x4, y4, Color.red);
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
		vy1 -= GRAVITY * delta; // Gravity as an acceleration
		vy2 -= GRAVITY * delta;
		vy3 -= GRAVITY * delta;
		vy4 -= GRAVITY * delta;
		
		vx1 = HvlMath.stepTowards(vx1, DRAG * delta, 0); //x-direction slow downs
		vx2 = HvlMath.stepTowards(vx2, DRAG * delta, 0); 
		vx3 = HvlMath.stepTowards(vx3, DRAG * delta, 0);
		vx4 = HvlMath.stepTowards(vx4, DRAG * delta, 0);
		
		x1 += vx1; //all position modifiers
		x2 += vx2;
		x3 += vx3;
		x4 += vx4;
		
		y1 += vy1;
		y2 += vy2;
		y3 += vy3;
		y4 += vy4;
		
		updateControls(delta);
		updateBorderCollisions(BACK_Y-2160, -720, BACK_X/2-1920, -BACK_X/2+1920);
		
		p1R.doCapture(new HvlAction0() { //player 1 
			
			@Override
			public void run() {
				drawBack(x1, y1, Main.getTexture(Main.LEVEL_ONE_INDEX));
				drawUI(player1);
				player1.update(delta);
				
				hvlDrawQuadc(x1 + -x2 + Display.getWidth()/2, y1 + -y2 + Display.getHeight()/2, 50, 50, player2.color); //render player 2
				hvlDrawQuadc(x1 + -x3 + Display.getWidth()/2, y1 + -y3 + Display.getHeight()/2, 50, 50, player3.color); //render player 3
				hvlDrawQuadc(x1 + -x4 + Display.getWidth()/2, y1 + -y4 + Display.getHeight()/2, 50, 50, player4.color);//render player 4
			}
		});
		p2R.doCapture(new HvlAction0() { //player 2 
			
			@Override
			public void run() {
				drawBack(x2, y2, Main.getTexture(Main.LEVEL_ONE_INDEX));
				drawUI(player2);
				player2.update(delta);
				
				hvlDrawQuadc(x2 + -x1 + Display.getWidth()/2, y2 + -y1 + Display.getHeight()/2, 50, 50, player1.color); //render player 1
				hvlDrawQuadc(x2 + -x3 + Display.getWidth()/2, y2 + -y3 + Display.getHeight()/2, 50, 50, player3.color); //render player 3
				hvlDrawQuadc(x2 + -x4 + Display.getWidth()/2, y2 + -y4 + Display.getHeight()/2, 50, 50, player4.color); //render player 4
 			}
		});
		p3R.doCapture(new HvlAction0() { //player 3 
			@Override
			public void run() {
				drawBack(x3, y3, Main.getTexture(Main.LEVEL_ONE_INDEX));
				drawUI(player3);
				player3.update(delta);
				
				hvlDrawQuadc(x3 + -x2 + Display.getWidth()/2, y3 + -y2 + Display.getHeight()/2, 50, 50, player2.color); //render player 2
				hvlDrawQuadc(x3 + -x1 + Display.getWidth()/2, y3 + -y1 + Display.getHeight()/2, 50, 50, player1.color); //render player 1
				hvlDrawQuadc(x3 + -x4 + Display.getWidth()/2, y3 + -y4 + Display.getHeight()/2, 50, 50, player4.color); //render player 4
			}
		});
		p4R.doCapture(new HvlAction0() { //player 4 
			@Override
			public void run() {
				drawBack(x4, y4, Main.getTexture(Main.LEVEL_ONE_INDEX));
				drawUI(player4);
				player4.update(delta);
				
				hvlDrawQuadc(x4 + -x2 + Display.getWidth()/2, y4 + -y2 + Display.getHeight()/2, 50, 50, player2.color); //render player 2
				hvlDrawQuadc(x4 + -x1 + Display.getWidth()/2, y4 + -y1 + Display.getHeight()/2, 50, 50, player1.color); //render player 1
				hvlDrawQuadc(x4 + -x3 + Display.getWidth()/2, y4 + -y3 + Display.getHeight()/2, 50, 50, player3.color); //render player 3
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
