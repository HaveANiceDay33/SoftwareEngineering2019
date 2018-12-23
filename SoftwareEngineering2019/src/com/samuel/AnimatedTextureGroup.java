package com.samuel;

import com.osreboot.ridhvl.painter.HvlAnimatedTextureUV;

public class AnimatedTextureGroup {
	public HvlAnimatedTextureUV standing, moving;
	public AnimatedTextureGroup(HvlAnimatedTextureUV standing, HvlAnimatedTextureUV moving) {
		this.standing = standing;
		this.standing.setAutoStop(false);
		this.standing.setRunning(true);
		
		this.moving = moving;
		this.moving.setAutoStop(false);
		this.moving.setRunning(true);
	}
}
