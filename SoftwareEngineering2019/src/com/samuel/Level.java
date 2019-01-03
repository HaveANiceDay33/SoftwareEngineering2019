package com.samuel;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

public class Level {
	public ArrayList<WorldElement> elements;
	public ArrayList<Word> words;
	public Texture background;
	public Level(Texture back) {
		background = back;
		elements = new ArrayList<>();
		words = new ArrayList<>();
	}

	public ArrayList<WorldElement> get_elements(){
		return this.elements;
	}
}
