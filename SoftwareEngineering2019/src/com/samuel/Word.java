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
			case 1:
				this.text = WordManager.verbs.get(HvlMath.randomIntBetween(0, WordManager.verbs.size()));
				break;
			case 2:
				this.text = WordManager.adjs.get(HvlMath.randomIntBetween(0, WordManager.adjs.size()));
				break;
			case 3:
				this.text = WordManager.adverbs.get(HvlMath.randomIntBetween(0, WordManager.adverbs.size()));
				break;
			default:
				this.text = "NO";
		}
	}
	public void draw(float xPlay, float yPlay) {
		actX = this.x + xPlay + Game.FIXED_X;
		actY = this.y + yPlay + Game.FIXED_Y;
		if(MenuManager.currentLevel.background == Main.level1) {
			Main.font.drawWordc(this.text, actX, actY, Color.black, 0.4f);
		} else if (MenuManager.currentLevel.background == Main.level2) {
			Main.font.drawWordc(this.text, actX, actY, new Color(128, 128, 128, 1f), 0.4f);
		}
		
	}
	public void remove(WorldElement toRemove, boolean onPlat) {
		MenuManager.currentLevel.words.remove(this);
		if(toRemove instanceof Platform && onPlat) {
			toRemove.wordOn = false;
		}
	}
}
