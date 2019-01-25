package com.samuel;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlMath;

/**
 * <p>The word class draws words generated by the word manager class. It chooses the word
 * itself, but not the part of speech. </p>
 * 
 * 
 * @author Samuel Munro
 *
 */
public class Word{
	public String text;
	public int type;
	public float x, y, actX, actY;
	
	/**
	 * <p>Chooses the word randomly based on the type (part of speech) </p>
	 * @param type
	 * @param x
	 * @param y
	 */
	public Word(int type, float x, float y) {
		this.x = x;
		this.y = y;
		this.type = type;
		switch(this.type) {
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
	
	/**
	 * <p>Uses the same relativity formula as platforms to draw words consistently along player perspectives.</p> 
	 * 
	 * @param xPlay
	 * @param yPlay
	 */
	public void draw(float xPlay, float yPlay) {
		actX = this.x + xPlay + Game.FIXED_X;
		actY = this.y + yPlay + Game.FIXED_Y;
		if(MenuManager.currentLevel.background == Main.level1) { //determines color based on level
			Main.font.drawWordc(this.text, actX, actY, Color.black, 0.4f);
		} else if (MenuManager.currentLevel.background == Main.level2) {
			Main.font.drawWordc(this.text, actX, actY, new Color(128, 128, 128, 1f), 0.4f);
		}
		
	}
	
	//removes words from memory. If word is on a platform, this method resets the platform's word-bearing status
	public void remove(WorldElement toRemove, boolean onPlat) {
		MenuManager.currentLevel.words.remove(this);
		if(toRemove instanceof Platform && onPlat) {
			toRemove.wordOn = false;
		}
	}
}
