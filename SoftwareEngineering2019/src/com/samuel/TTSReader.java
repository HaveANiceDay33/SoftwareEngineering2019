package com.samuel;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TTSReader {
	public void read(String message) {
		try 
	    { 
	        VoiceManager vm = VoiceManager.getInstance();
	        Voice voice = vm.getVoice("kevin16");
	        voice.allocate();
	        voice.speak(message);
	    }  
	
	    catch (Exception e)  
	    { 
	    	throw new RuntimeException("Error with text to speech engine");
	    }
	}
}
