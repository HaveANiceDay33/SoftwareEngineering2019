package com.samuel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.osreboot.ridhvl.HvlMath;

/**
 * 
 * @author Samuel Munro
 * 
 * <p>The Word Manager class loads words from the word lists text files (located in the wordLists file), 
 * saves the words it finds into arrays, and spawns words into the world on a timer. The system randomly chooses
 * a platform, checks the platform for a word, and then takes one of two actions: </p>
 * 
 * <p>If the platform has a word or weapon already on it, spawn word on ground.
 * If not, spawn the word on the platform that was selected. </p>  
 */

public class WordManager {
	
	private static final float WORD_TIME = 1f;
	
	public static ArrayList<String> nouns = new ArrayList<>();
	public static ArrayList<String> verbs = new ArrayList<>();
	public static ArrayList<String> adjs = new ArrayList<>();
	public static ArrayList<String> adverbs = new ArrayList<>();
	
	public static float wordTimer;
	
	/**
	 * <p>Writes words from a passed in file to a passed in Arraylist.</p>
	 * 
	 * @param filePath
	 * @param partOfSpeech
	 */
	public static void saveTextData(String filePath, ArrayList<String> partOfSpeech){
		try {
			Scanner wordsIn;
			wordsIn = new Scanner(new FileInputStream(filePath));
			int count = 0;
			while(wordsIn.hasNextLine()) { //checks for a next line
				partOfSpeech.add(count, wordsIn.nextLine());
				count++;
			}
			wordsIn.close();
		} catch (FileNotFoundException e) { //error throwing. Notifies the user through the window in the Main class.
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
		if(wordTimer <= 0 && MenuManager.currentLevel.words.size() < 11) {
			float x, y;
			WorldElement spawnOn = MenuManager.currentLevel.elements.get(HvlMath.randomIntBetween(0, MenuManager.currentLevel.elements.size()));
			if(spawnOn instanceof Platform && !spawnOn.wordOn && !spawnOn.weaponOn) { //checking platform locations
				x = spawnOn.get_x(); 
				y = spawnOn.get_y() - 100;
				spawnOn.wordOn = true;
			}else {
				x = HvlMath.randomIntBetween(Game.BORDER_RIGHT, Game.BORDER_LEFT); //spawns on ground if platform has a word or weapon
				y = Game.FIXED_Y;
			}
			Word newWord = new Word(HvlMath.randomIntBetween(0, 4), x, y);
			MenuManager.currentLevel.words.add(newWord); //adds word to level ArrayList
			wordTimer = WORD_TIME; //resets the spawn timer
		}
	}
	
}
