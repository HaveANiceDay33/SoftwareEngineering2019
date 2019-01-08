package com.samuel;

import com.osreboot.ridhvl.painter.HvlAnimatedTextureUV;

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
