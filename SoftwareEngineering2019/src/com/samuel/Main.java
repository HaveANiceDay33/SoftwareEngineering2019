package com.samuel;

import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.painter.HvlCamera2D;
import com.osreboot.ridhvl.painter.HvlRenderFrame;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;


public class Main extends HvlTemplateInteg2D{
	
	public static void main(String [] args){
		new Main();
	}
	public Main(){
		super(60, 1920, 1080, "Insert Game Title Here", new HvlDisplayModeDefault());
	}
	
	@Override
	public void initialize() {
		getTextureLoader().loadResource("level1");
		getTextureLoader().loadResource("crate");
		Game.initGame();
	}
	@Override
	public void update(float delta) {
		 Game.updateGame(delta);
		//1
		/*
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
		
		
		p1R.doCapture(new HvlAction0() { //player 1 
			
			@Override
			public void run() {
				hvlDrawQuadc(x1, y1, 3840, 2160, getTexture(1)); //background
				hvlDrawQuadc(960, 540, 50, 50, Color.blue);
				
				hvlDrawQuadc(x1 + -x2 + 960, y1 + -y2 + 540, 50, 50, Color.pink); //render player 2
				hvlDrawQuadc(x1 + -x3 + 960, y1 + -y3 + 540, 50, 50, Color.green); //render player 3
				hvlDrawQuadc(x1 + -x4 + 960, y1 + -y4 + 540, 50, 50, Color.magenta); //render player 4
				
			}
		});
		p2R.doCapture(new HvlAction0() { //player 2 
			
			@Override
			public void run() {
				hvlDrawQuadc(x2, y2, 3840, 2160, getTexture(1)); //background
				hvlDrawQuadc(960, 540, 50, 50, Color.pink);
				
				hvlDrawQuadc(x2 + -x1 + 960, y2 + -y1 + 540, 50, 50, Color.blue); //render player 1
				hvlDrawQuadc(x2 + -x3 + 960, y2 + -y3 + 540, 50, 50, Color.green); //render player 3
				hvlDrawQuadc(x2 + -x4 + 960, y2 + -y4 + 540, 50, 50, Color.magenta); //render player 4
 			}
		});
		p3R.doCapture(new HvlAction0() { //player 3 
			@Override
			public void run() {
				hvlDrawQuadc(x3 , y3,3840, 2160, getTexture(1)); //background
				hvlDrawQuadc(960, 540, 50, 50, Color.green);
				
				hvlDrawQuadc(x3 + -x2 + 960, y3 + -y2 + 540, 50, 50, Color.pink); //render player 2
				hvlDrawQuadc(x3 + -x1 + 960, y3 + -y1 + 540, 50, 50, Color.blue); //render player 1
				hvlDrawQuadc(x3 + -x4 + 960, y3 + -y4 + 540, 50, 50, Color.magenta); //render player 4
			}
		});
		p4R.doCapture(new HvlAction0() { //player 4 
			@Override
			public void run() {
				hvlDrawQuadc(x4, y4, 3840, 2160, getTexture(1)); //background
				hvlDrawQuadc(960, 540, 50, 50, Color.magenta);
				
				hvlDrawQuadc(x4 + -x2 + 960, y4 + -y2 + 540, 50, 50, Color.pink); //render player 2
				hvlDrawQuadc(x4 + -x1 + 960, y4 + -y1 + 540, 50, 50, Color.blue); //render player 1
				hvlDrawQuadc(x4 + -x3 + 960, y4 + -y3 + 540, 50, 50, Color.green); //render player 3
			}
		});
		hvlDrawQuad(0,0, 1920, 1080, Color.green);
		
		hvlDrawQuad(0, 0, 960, 540, p1R);
		hvlDrawQuad(960, 0, 960, 540, p2R);
		hvlDrawQuad(0, 540, 960, 540, p3R);
		hvlDrawQuad(960, 540, 960, 540, p4R);
		
		hvlDrawQuad(955, 0, 10, 1080, Color.black);
		hvlDrawQuad(0, 535, 1920, 10, Color.black);
		
		//hvlDrawQuadc(x2, y2, 50, 50, Color.pink);
		//hvlDrawQuadc(x3, y3, 50, 50, Color.green);
		//hvlDrawQuadc(x4, y4, 50, 50, Color.yellow);
		 * */

	}
}
