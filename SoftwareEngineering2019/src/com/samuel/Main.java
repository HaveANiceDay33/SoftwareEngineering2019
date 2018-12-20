package com.samuel;

import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.painter.HvlCamera2D;
import com.osreboot.ridhvl.painter.HvlRenderFrame;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;


public class Main extends HvlTemplateInteg2D{
	
	public static void main(String [] args){
		new Main();
	}
	public Main(){
		super(60, 1280, 720, "Insert Game Title Here", new HvlDisplayModeDefault());
	}
	
	public static final int LEVEL_ONE_INDEX = 0;
	public static final int CRATE_INDEX = 1;
	public static final int FONT_INDEX = 2;
	public static final int CVILLE_INDEX = 3;
	
	static HvlFontPainter2D font;
	@Override
	public void initialize() {
		getTextureLoader().loadResource("level1");//0
		getTextureLoader().loadResource("crate");//1
		getTextureLoader().loadResource("INOF");//2
		getTextureLoader().loadResource("clogo");//3
		getTextureLoader().loadResource("cogM");//4
		
		font = new HvlFontPainter2D(getTexture(FONT_INDEX), HvlFontPainter2D.Preset.FP_INOFFICIAL);
		font.setCharSpacing(16f);
		
		MenuManager.initialize();
	}
	@Override
	public void update(float delta) {
		 MenuManager.update(delta);		
	}
}
