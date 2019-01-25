package com.samuel;

import com.osreboot.ridhvl.painter.HvlAnimatedTextureUV;

/**
 * 
 * @author Samuel Munro
 * 
 * <p>The AnimatedTextureGroup class </p>
 * <p>This class serves solely as data storage for animations. Each one of the animations
 * is able to be switched to based on player movement, and this class makes those transitions
 * easy</p>
 * 
 * @param standing
 * @param moving
 * @param jumping
 *
 */

public class AnimatedTextureGroup {
	public HvlAnimatedTextureUV standing, moving, jumping;
	public AnimatedTextureGroup(HvlAnimatedTextureUV standing, HvlAnimatedTextureUV moving, HvlAnimatedTextureUV jumping) {
		this.standing = standing;
		this.standing.setAutoStop(false);
		this.standing.setRunning(true);
		
		this.moving = moving;
		this.moving.setAutoStop(false);
		this.moving.setRunning(true);
		
		this.jumping = jumping;
		this.jumping.setAutoStop(false);
		this.jumping.setRunning(false);
	}
}
