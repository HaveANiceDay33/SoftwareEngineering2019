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
	}
}
