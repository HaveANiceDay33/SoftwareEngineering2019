package com.samuel;

import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
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
		
	}
	@Override
	public void update(float delta) {
		
	}
}
