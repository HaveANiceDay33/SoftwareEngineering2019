
package com.samuel;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TTSReader implements Runnable{

	public String toRead;
	public String[] words;
	public String currentWord = "";
	public String genre;
	public TTSReader(String toRead) {
		this.toRead = toRead;
		words = toRead.split(" ");
		genre = MenuManager.chosenGenre;
	}

	@Override
	public void run() {
		try 
	    {	
			System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
	        VoiceManager vm = VoiceManager.getInstance();
	        Voice voice = vm.getVoice("kevin16");
	        voice.setRate(200);
		    voice.setPitch(80);
	        voice.allocate();
	        MenuManager.TTSWord = "Hello There!";
	        for(int i = 0; i < words.length; i++) {
	        	currentWord = words[i] + " ";
	        	MenuManager.TTSWord = words[i];
	        	voice.speak(words[i]);
			}
	        
	    }  
	
	    catch (Exception e)  
	    { 
	    	throw new RuntimeException("Error with text to speech engine");
	    }
	}
}

