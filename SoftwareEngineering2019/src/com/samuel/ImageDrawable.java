package com.samuel;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuad;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.menu.component.HvlComponentDrawable;

/**
 * @author Samuel Munro
 * The ImageDrawable class
 * 
 * <p>This class is a modification of the default ways RIDHVL handles
 * drawing buttons. My class draws images on top of buttons instead of
 * merely text, as the engine is defaulted to. This was necessary in order
 * to have nice looking buttons for the game.</p>\
 * 
 * <p>The textureIndex parameter identifies which texture to draw 
 * on the button, and the shade parameter identifies what to shade the 
 * button (used when hovering, clicking, etc)</p>
 * 
 * @param textureIndex 
 * @param shade
 */

public class ImageDrawable extends HvlComponentDrawable{
	
	int textureIndex;
	Color shade;
	public ImageDrawable(int textureIndex, Color shade) {
		this.textureIndex = textureIndex;
		this.shade = shade;
	}
	@Override
	public void draw(float delta, float x, float y, float width, float height) {
		hvlDrawQuad(x, y, width, height, Main.getTexture(this.textureIndex), this.shade);
	}

}
