package com.samuel;

import org.lwjgl.opengl.Display;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TTSReader implements Runnable{
	
	public String toRead;
	public String[] words;
	public String currentWord = "";
	public TTSReader(String toRead) {
		this.toRead = toRead;
		words = toRead.split(" ");
	}

	@Override
	public void run() {
		try 
	    {	
	        VoiceManager vm = VoiceManager.getInstance();
	        Voice voice = vm.getVoice("kevin16");
	        voice.setRate(80);
	        voice.setPitch(110);
	        voice.allocate();
	        for(int i = 0; i < words.length; i++) {
	        	//voice.speak(words[i] + " ");
	        	currentWord = words[i] + " ";
	        	System.out.println(currentWord);
			}
	       
	    }  
	
	    catch (Exception e)  
	    { 
	    	throw new RuntimeException("Error with text to speech engine");
	    }
	}
	
	public String get_word() {
		return currentWord;
	}
}
