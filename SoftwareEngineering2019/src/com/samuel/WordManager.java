package com.samuel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.osreboot.ridhvl.HvlMath;

public class WordManager {
	
	private static final float WORD_TIME = 5f;
	
	public static ArrayList<String> nouns = new ArrayList<>();
	public static ArrayList<String> verbs = new ArrayList<>();
	public static ArrayList<String> adjs = new ArrayList<>();
	public static ArrayList<String> adverbs = new ArrayList<>();
	public static ArrayList<String> conjs = new ArrayList<>();
	public static ArrayList<String> preps = new ArrayList<>();
	
	public static float wordTimer;
	
	public static void saveTextData(String filePath, ArrayList<String> partOfSpeech){
		try {
			Scanner wordsIn;
			wordsIn = new Scanner(new FileInputStream(filePath));
			int count = 0;
			while(wordsIn.hasNextLine()) {
				partOfSpeech.add(count, wordsIn.nextLine());
				count++;
			}
			wordsIn.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Vocabulary File not Found! Make sure there are [partOfSpeech].txt files in the 'res' folder.");
		}
	}
	
	public static void initWords() {
		saveTextData("res/nouns.txt", nouns);
		wordTimer = 0;
		MenuManager.currentLevel.words.add(new Word(0, Game.fixedX, Game.fixedY + 10000));
	}
	
	public static void updateWords(float delta) {
		System.out.println(MenuManager.currentLevel.elements.get(1).wordOn);
		wordTimer -= delta;
		if(wordTimer <= 0) {
			float x, y;
			WorldElement spawnOn = MenuManager.currentLevel.elements.get(HvlMath.randomIntBetween(0, MenuManager.currentLevel.elements.size()));
			if(spawnOn instanceof Platform && !spawnOn.wordOn) {
				x = spawnOn.get_x();
				y = spawnOn.get_y() - 100;
				spawnOn.wordOn = true;
			}else {
				x = HvlMath.randomIntBetween(Player.BORDER_RIGHT, Player.BORDER_LEFT);
				y = Game.fixedY;
			}
			Word newWord = new Word(HvlMath.randomIntBetween(0, 0), x, y);
			MenuManager.currentLevel.words.add(newWord);
			wordTimer = WORD_TIME;
		}
	}
	
}
