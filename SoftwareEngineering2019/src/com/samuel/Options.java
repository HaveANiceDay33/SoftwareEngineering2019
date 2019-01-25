package com.samuel;

import java.io.Serializable;

/**
 * @author Samuel Munro
 * The Options class
 * 
 * <p>The Options class declares and defines the defaults for the in-game
 * options, sound effects and background music. This class is
 * instantiated if a settings.cfg file is not created. The options are
 * serialized as well, making for quick reading and easy integration with
 * RIDHVL</p>
 */

public class Options implements Serializable{
	private static final long serialVersionUID = -2245978679489813019L; //AUTO GENERATED
	
	public boolean soundEffectsEnabled = true;
	public boolean backgroundMusicEnabled = true;
}
