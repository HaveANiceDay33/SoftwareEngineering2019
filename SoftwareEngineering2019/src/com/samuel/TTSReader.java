
package com.samuel;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

/**
 * <p>The TTSReader class implements Runnable, and we use it to 
 * create new threads here. FreeTTS unfortunately sleeps the thread 
 * it is working in, and so we had to move it to a new thread in order to 
 * prevent it from freezing animations and on-screen text.</p>
 * 
 * @author Samuel Munro 
 *
 */
public class TTSReader implements Runnable{

	public String toRead;
	public String[] words;
	public String currentWord = "";
	public String genre;
	
	/**
	 * <p>Takes a string to read aloud and splits it into an array of words.</p>
	 * 
	 * @param toRead
	 */
	public TTSReader(String toRead) {
		this.toRead = toRead;
		words = toRead.split(" ");
	}

	@Override
	public void run() {
		try 
	    {	
			System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory"); 
	        VoiceManager vm = VoiceManager.getInstance(); //instantiates a new voice
	        Voice voice = vm.getVoice("kevin16"); 
	        voice.setRate(200); //sets words per minute
		    voice.setPitch(80);
	        voice.allocate();
	        MenuManager.TTSWord = "Hello There!";
	        for(int i = 0; i < words.length; i++) {
	        	currentWord = words[i] + " ";
	        	MenuManager.TTSWord = words[i]; //sets the text to display on screen in Main thread.
	        	voice.speak(words[i]); //reads the words one at a time.
			}
	        
	    }  
	
	    catch (Exception e)  
	    { 
	    	throw new RuntimeException("Error with text to speech engine");
	    }
	}
}

