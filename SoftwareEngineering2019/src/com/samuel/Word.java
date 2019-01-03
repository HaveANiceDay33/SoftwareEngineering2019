package com.samuel;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlMath;

public class Word{
	public String text;
	public float x, y, actX, actY;
	public Word(int type, float x, float y) {
		this.x = x;
		this.y = y;
		switch(type) {
			case 0:
				this.text = WordManager.nouns.get(HvlMath.randomIntBetween(0, WordManager.nouns.size()));
				break;
			default:
				this.text = "NO";
		}
	}
	public void draw(float xPlay, float yPlay) {
		actX = this.x + xPlay + Game.fixedX;
		actY = this.y + yPlay + Game.fixedY;
		Main.font.drawWordc(this.text, actX, actY, Color.black, 0.4f);
	}
	public void remove(WorldElement toRemove, boolean onPlat) {
		MenuManager.currentLevel.words.remove(this);
		if(toRemove instanceof Platform && onPlat) {
			toRemove.wordOn = false;
		}
	}
}
