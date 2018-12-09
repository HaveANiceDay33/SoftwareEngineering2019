package com.samuel;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuad;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;

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
	static float jumpT1, jumpT2, jumpT3, jumpT4 = 0;
	
	public static void updateControls(float delta) {
		jumpT1 -= delta;
		jumpT2 -= delta;
		jumpT3 -= delta;
		jumpT3 -= delta;
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
	public static void updateBorderCollisions() {
		if(y1 < 0) {y1 = 0;} // bottom world border 
		if(y2 < 0) {y2 = 0;}
		if(y3 < 0) {y3 = 0;}
		if(y4 < 0) {y4 = 0;}
		
		if(y1 > 1080) {y1 = 1080;}  //top world border 
		if(y1 > 1080) {y2 = 1080;}
		if(y1 > 1080) {y3 = 1080;}
		if(y1 > 1080) {y4 = 1080;}
		
		if(x1 < 0) {x1 = 0;} // left world border 
		if(x2 < 0) {x2 = 0;}
		if(x3 < 0) {x3 = 0;}
		if(x4 < 0) {x4 = 0;}
		
		if(x1 > 1920) {x1 = 1920;} // right world border 
		if(x2 > 1920) {x2 = 1920;}
		if(x3 > 1920) {x3 = 1920;}
		if(x4 > 1920) {x4 = 1920;}
		
	}
	public static void initGame() {
		x1 = x2 = x3 = x4 = 960; 
		y1 = y2 = y3 = y4 = 540;
		
		player1 = new Player(0, x1, y1, Color.blue);
		player2 = new Player(1, x2, y2, Color.green);
		player3 = new Player(2, x3, y3, Color.yellow);
		player4 = new Player(3, x4, y4, Color.red);
		try {
			p1R = new HvlRenderFrame(1920, 1080);
			p2R = new HvlRenderFrame(1920, 1080);
			p3R = new HvlRenderFrame(1920, 1080);
			p4R = new HvlRenderFrame(1920, 1080);
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
		updateBorderCollisions();
		p1R.doCapture(new HvlAction0() { //player 1 
			
			@Override
			public void run() {
				hvlDrawQuadc(x1, y1, 3840, 2160, Main.getTexture(0));
				player1.update(delta);
				hvlDrawQuadc(x1 + -x2 + 960, y1 + -y2 + 540, 50, 50, player2.color); //render player 2
				hvlDrawQuadc(x1 + -x3 + 960, y1 + -y3 + 540, 50, 50, player3.color); //render player 3
				hvlDrawQuadc(x1 + -x4 + 960, y1 + -y4 + 540, 50, 50, player4.color);//render player 4
				
			}
		});
		p2R.doCapture(new HvlAction0() { //player 2 
			
			@Override
			public void run() {
				hvlDrawQuadc(x2, y2, 3840, 2160, Main.getTexture(0)); //background
				player2.update(delta);
				hvlDrawQuadc(x2 + -x1 + 960, y2 + -y1 + 540, 50, 50, player1.color); //render player 1
				hvlDrawQuadc(x2 + -x3 + 960, y2 + -y3 + 540, 50, 50, player3.color); //render player 3
				hvlDrawQuadc(x2 + -x4 + 960, y2 + -y4 + 540, 50, 50, player4.color); //render player 4
 			}
		});
		p3R.doCapture(new HvlAction0() { //player 3 
			@Override
			public void run() {
				hvlDrawQuadc(x3 , y3,3840, 2160, Main.getTexture(0)); //background
				player3.update(delta);
				
				hvlDrawQuadc(x3 + -x2 + 960, y3 + -y2 + 540, 50, 50, player2.color); //render player 2
				hvlDrawQuadc(x3 + -x1 + 960, y3 + -y1 + 540, 50, 50, player1.color); //render player 1
				hvlDrawQuadc(x3 + -x4 + 960, y3 + -y4 + 540, 50, 50, player4.color); //render player 4
			}
		});
		p4R.doCapture(new HvlAction0() { //player 4 
			@Override
			public void run() {
				hvlDrawQuadc(x4, y4, 3840, 2160, Main.getTexture(0)); //background
				hvlDrawQuadc(960, 540, 50, 50, Color.magenta);
				
				hvlDrawQuadc(x4 + -x2 + 960, y4 + -y2 + 540, 50, 50, player2.color); //render player 2
				hvlDrawQuadc(x4 + -x1 + 960, y4 + -y1 + 540, 50, 50, player1.color); //render player 1
				hvlDrawQuadc(x4 + -x3 + 960, y4 + -y3 + 540, 50, 50, player3.color); //render player 3
			}
		});
		
		hvlDrawQuad(0, 0, 960, 540, p1R);
		hvlDrawQuad(960, 0, 960, 540, p2R);
		hvlDrawQuad(0, 540, 960, 540, p3R);
		hvlDrawQuad(960, 540, 960, 540, p4R);
		hvlDrawQuad(955, 0, 10, 1080, Color.black);
		hvlDrawQuad(0, 535, 1920, 10, Color.black);
		
		
	}
}
