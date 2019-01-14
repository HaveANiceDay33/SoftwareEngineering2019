package com.samuel;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuad;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.menu.component.HvlComponentDrawable;

public class ImageDrawable extends HvlComponentDrawable{
	
	int textureIndex;
	Color shade;
	boolean hover;
	boolean played;
	public ImageDrawable(int textureIndex, Color shade, boolean hover) {
		this.textureIndex = textureIndex;
		this.shade = shade;
		this.hover = hover;
		this.played = false;
	}
	@Override
	public void draw(float delta, float x, float y, float width, float height) {
		if(this.hover && !this.played) {
			Main.getSound(Main.COCONUT_LAUNCH_INDEX).playAsSoundEffect(1f, 1f, false);
			this.played = true;
		}
		hvlDrawQuad(x, y, width, height, Main.getTexture(Main.CRATE_INDEX), this.shade);
		hvlDrawQuadc(x+width-45, y+(height/2)-3, 50, 50, Main.getTexture(this.textureIndex));
	}

}
