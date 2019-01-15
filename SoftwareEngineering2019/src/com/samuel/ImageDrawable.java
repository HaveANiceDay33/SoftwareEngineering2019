package com.samuel;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuad;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.menu.component.HvlComponentDrawable;

public class ImageDrawable extends HvlComponentDrawable{
	
	int textureIndex;
	public ImageDrawable(int textureIndex) {
		this.textureIndex = textureIndex;
	}
	@Override
	public void draw(float delta, float x, float y, float width, float height) {
		hvlDrawQuad(x, y, width, height, Main.getTexture(this.textureIndex));
	}

}
