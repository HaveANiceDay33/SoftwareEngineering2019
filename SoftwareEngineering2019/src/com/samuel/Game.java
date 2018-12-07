package com.samuel;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuad;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;

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
	public static void updateControls(float delta) {
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			x1 -= 400 * delta;	
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			x1 += 400 * delta;	
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			y1 += 400 * delta;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			y1 -= 400 * delta;
		}
		//2
		if(Keyboard.isKeyDown(Keyboard.KEY_L)){
			x2 -= 400 * delta;	
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_J)){
			x2 += 400 * delta;	
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_I)){
			y2 += 400 * delta;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_K)){
			y2 -= 400 * delta;
		}
		//3
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
			x3 -= 400 * delta;	
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
			x3 += 400 * delta;	
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
			y3 += 400 * delta;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
			y3 -= 400 * delta;
		}
		//4
		if(Keyboard.isKeyDown(Keyboard.KEY_H)){
			x4 -= 400 * delta;	
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_F)){
			x4 += 400 * delta;	
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_T)){
			y4 += 400 * delta;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_G)){
			y4 -= 400 * delta;
		}
		
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
		} catch (FBOUnsupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			p2R = new HvlRenderFrame(1920, 1080);
		} catch (FBOUnsupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			p3R = new HvlRenderFrame(1920, 1080);
		} catch (FBOUnsupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			p4R = new HvlRenderFrame(1920, 1080);
		} catch (FBOUnsupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void updateGame(float delta) {
		updateControls(delta);
		p1R.doCapture(new HvlAction0() { //player 1 
			
			@Override
			public void run() {
				hvlDrawQuadc(x1, y1, 3840, 2160, Main.getTexture(0));
				hvlDrawQuadc(x1, y1, 200, 200, Main.getTexture(1));
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
		
		
		
	}
}
