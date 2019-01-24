package com.samuel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.osreboot.ridhvl.HvlMath;

public class WordManager {
	
	private static final float WORD_TIME = 1.5f;
	
	public static ArrayList<String> nouns = new ArrayList<>();
	public static ArrayList<String> verbs = new ArrayList<>();
	public static ArrayList<String> adjs = new ArrayList<>();
	public static ArrayList<String> adverbs = new ArrayList<>();
	
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
		saveTextData("wordLists/nouns.txt", nouns);
		saveTextData("wordLists/verbs.txt", verbs);
		saveTextData("wordLists/adjectives.txt", adjs);
		saveTextData("wordLists/adverbs.txt", adverbs);
		wordTimer = 0;
		MenuManager.currentLevel.words.add(new Word(0, Game.FIXED_X, Game.FIXED_Y + 10000));
	}
	
	public static void updateWords(float delta) {
		wordTimer -= delta;
		if(wordTimer <= 0 && MenuManager.currentLevel.words.size() < 17) {
			float x, y;
			WorldElement spawnOn = MenuManager.currentLevel.elements.get(HvlMath.randomIntBetween(0, MenuManager.currentLevel.elements.size()));
			if(spawnOn instanceof Platform && !spawnOn.wordOn && !spawnOn.weaponOn) {
				x = spawnOn.get_x(); 
				y = spawnOn.get_y() - 100;
				spawnOn.wordOn = true;
			}else {
				x = HvlMath.randomIntBetween(Game.BORDER_RIGHT, Game.BORDER_LEFT);
				y = Game.FIXED_Y;
			}
			Word newWord = new Word(HvlMath.randomIntBetween(0, 4), x, y);
			MenuManager.currentLevel.words.add(newWord);
			wordTimer = WORD_TIME;
		}
	}
	
}
