package com.samuel;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.painter.HvlAnimatedTextureUV;
/**
 * 
 * @author Samuel Munro
 * 
 * <p>Level Class</p>
 * <p>The level class stores all world elements in game: including platforms,
 * words, weapons, and projectiles. Once called from a class that inherits this one
 * (found in the LevelProfiles folder) this class changes the level and menu backgrounds,
 * as well as the in-game text in order to contrast nicely with our 2 distinct level backgrounds.</p>
 * 
 * @param back
 *
 */
public class Level {
	public ArrayList<WorldElement> elements;
	public ArrayList<Word> words;
	public ArrayList<Weapon> weapons;
	public ArrayList<Projectile> projs;
	public HvlAnimatedTextureUV background;
	public Texture menuBackground;
	public Color textColor;
	public Level(HvlAnimatedTextureUV back) {
		background = back;
		if(background == Main.level1) {
			textColor = Color.black;
			menuBackground = Main.getTexture(Main.MENU_BACK_INDEX);
		} else {
			textColor = new Color(128, 128, 128, 1f);
			menuBackground = Main.getTexture(Main.MENU_SPACE_INDEX);
		}
		elements = new ArrayList<>();
		words = new ArrayList<>();
		weapons = new ArrayList<>();
		projs = new ArrayList<>();
	}

	public ArrayList<WorldElement> get_elements(){
		return this.elements;
	}
}
