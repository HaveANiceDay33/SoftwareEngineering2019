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
	        voice.setRate(250);
	        voice.setPitch(130);
	        voice.allocate();
	        for(int i = 0; i < words.length; i++) {
	        	currentWord = words[i] + " ";
	        	//Main.font.drawWordc(currentWord, Display.getWidth()/2, Display.getHeight()/2 , MenuManager.currentLevel.textColor);
	        	voice.speak(words[i]);
			}
	       
	    }  
	
	    catch (Exception e)  
	    { 
	    	throw new RuntimeException("Error with text to speech engine");
	    }
	}
}
