package com.samuel;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.painter.HvlAnimatedTextureUV;

public class Player {
	static final private float JUMP_POWER = 3100;//Instantaneous velocity the player gains when jumping
	static final private float MOVE_SPEED = 900;//Movement speed in x-plane
	static final private float JUMP_TIMER = 0.75f;//Time between jumps, in seconds
	static final private float SHOT_TIMER = 1f;//Time between jumps, in seconds
	static final public float PLAYER_SIZE = 200;
	
	public int id, cont;//used to decorate UI elements and assign controller numbers
	public float x, y, vx, vy, x1Cont, aCont; //movement and controller values
	public float jumpTimer = 0, shotTimer = 0;//Timer that gets modified every frame to allow jumps
	public AnimatedTextureGroup animations;
	public ArrayList<Word> playerWords;
	private boolean jumped = false;
	private float aniTime = 0.8f;
	public HvlAnimatedTextureUV currentAnimation;
	private float distanceFrom;
	public boolean onPlat;
	public Weapon playerWeapon;
	
	//Player constructor that runs when a player object is created
	public Player(int id, int cont, AnimatedTextureGroup animations){
		this.id = id;
		this.cont = cont;
		this.animations = animations;
		this.vx = HvlMath.randomIntBetween(-2700, 2700); //sets the initial x-velocity of the player, creates a “fanning” effect for when the players spawn in.
		this.playerWords = new ArrayList<>();
		this.currentAnimation = this.animations.standing; 
		this.playerWeapon = null;
	}
	
	//method that runs every frame, calculates physics, checks border and element collisions, and draws the player.
	public void update(float delta) {
		this.jumpTimer -= delta;//Jumping timer modification
		this.shotTimer -= delta;
		this.vy -= Game.GRAVITY * delta;//”pull” of gravity
		this.vx = HvlMath.stepTowards(this.vx, Game.DRAG*delta, 0);//function to slow player down in the x-plane
		this.x += this.vx * delta; //positions are modified by velocities
		this.y += this.vy * delta;
		updateElementCollisions();//put here to correct for gravity's effect in one frame, vy is set to 0 here, and upon a jump is changed to JUMP_POWER
		updateWords();
		
		updateBorderCollisions(Game.BORDER_TOP, Game.BORDER_BOTTOM, Game.BORDER_LEFT, Game.BORDER_RIGHT);
		if(this.cont != 4) {
			this.x1Cont = Controllers.joy1x[this.cont]; //controller inputs saved to variables for later use
			this.aCont = Controllers.allA[this.cont];
			if(this.x1Cont > 0) //Determines player movement direction and scales to the joystick input
				this.vx = -MOVE_SPEED * Math.abs(this.x1Cont);
			if(this.x1Cont < 0)
				this.vx = MOVE_SPEED * Math.abs(this.x1Cont);
			if(this.aCont == 1 && this.vy == 0 && this.jumpTimer <= 0){//Determines jumping
				this.vy = JUMP_POWER;
				this.jumped = true;
				this.jumpTimer = JUMP_TIMER;//resets jump timer
			} 
		} else { //Clause to add keyboard support
			if(Keyboard.isKeyDown(Keyboard.KEY_D)){this.vx = -MOVE_SPEED;}
			if(Keyboard.isKeyDown(Keyboard.KEY_A)){this.vx = MOVE_SPEED;}
			if(Keyboard.isKeyDown(Keyboard.KEY_SPACE) && this.vy == 0 && this.jumpTimer <= 0){
				this.vy = JUMP_POWER; 
				this.jumpTimer = JUMP_TIMER;
				this.jumped = true;
			}
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_F) && this.shotTimer <= 0) {
			if(this.playerWeapon != null) {
				this.playerWeapon.fire();
				this.shotTimer = SHOT_TIMER;
			}
		}
		
		if(this.vx == 0) {
			currentAnimation = this.animations.standing;
		} else {
			currentAnimation = this.animations.moving;
		}
		if(this.jumped) {
			aniTime -= delta;
			this.currentAnimation = this.animations.jumping;
			this.animations.jumping.setRunning(true);
			if(aniTime <= 0) {
				this.jumped = false;
				aniTime = 0.8f;
			}
		} 
		
		hvlDrawQuadc(Game.FIXED_X, Game.FIXED_Y, this.vx <= 0 ? -PLAYER_SIZE : PLAYER_SIZE, PLAYER_SIZE,
				currentAnimation);
		
		updateWeapons();
	}
	
	//Calculates and prevents players from leaving the play area. 
	private void updateBorderCollisions(int top, int bottom, int left, int right) {
		if(this.y < bottom) {this.y = bottom; this.vy = 0;} // bottom world border 
		if(this.y > top) {this.y = top; this.vy = 0;}  //top world border
		if(this.x > left) {this.x = left;} // left world border 
		if(this.x < right) {this.x = right;} // right world border
	}
	
	private WorldElement closestElement() {
		WorldElement closest = null;
		for(WorldElement e : MenuManager.currentLevel.elements) {
			if(closest == null) {
				closest = e;
			}
			float distance = HvlMath.distance(Game.FIXED_X, Game.FIXED_Y, closest.actX, closest.actY);
			float distanceTest = HvlMath.distance(Game.FIXED_X, Game.FIXED_Y, e.actX, e.actY);
			if(distanceTest < distance) {
				closest = e;
			}
		}
		return closest;
	}
	
	private void updateElementCollisions() {
		WorldElement closest = closestElement();
		onPlat = false;
		//CHECKING PLATFORM COLLISIONS
		if(closest instanceof Platform) {
			if(this.y < -(closest.y + 32) + PLAYER_SIZE/2 && this.y > -(closest.y - 32) - PLAYER_SIZE/2 && 
					((this.x + (PLAYER_SIZE/4) > closest.x - closest.sizeX*32 && this.x - (PLAYER_SIZE/4) < closest.x + closest.sizeX*32) || 
							(this.x - (PLAYER_SIZE/4) < -(closest.x - closest.sizeX*32) && this.x + (PLAYER_SIZE/4) > -(closest.x + closest.sizeX*32)) )){
				if(this.vy < 0) {
					this.vy = 0;
					this.y = -(closest.y + 32) + PLAYER_SIZE/2;
					onPlat = true;
				} else if (this.vy > 0) {
					this.vy = -1;
					this.y = -(closest.y - 32) -PLAYER_SIZE/2;
				}
			}
		}
	}
	
	private void updateWords() {
		Word closeWord = null;
		for(Word w : MenuManager.currentLevel.words) {
			if(closeWord == null) {
				closeWord = w;
			}
			float distance = HvlMath.distance(Game.FIXED_X, Game.FIXED_Y, closeWord.actX, closeWord.actY);
			float distanceTest = HvlMath.distance(Game.FIXED_X, Game.FIXED_Y, w.actX, w.actY);
			if(distanceTest < distance) {
				closeWord = w;
			}
		}
		distanceFrom = HvlMath.distance(Game.FIXED_X, Game.FIXED_Y, closeWord.actX, closeWord.actY);
		if(distanceFrom < PLAYER_SIZE/3) {
			this.playerWords.add(0, closeWord);
			closeWord.remove(closestElement(), onPlat);
		}
	}
 	
	private void updateWeapons() {
		Weapon closeWeapon = null;
		for(Weapon w : MenuManager.currentLevel.weapons) {
			if(closeWeapon == null) {
				closeWeapon = w;
			}
			float distance = HvlMath.distance(Game.FIXED_X, Game.FIXED_Y, closeWeapon.actX, closeWeapon.actY);
			float distanceTest = HvlMath.distance(Game.FIXED_X, Game.FIXED_Y, w.actX, w.actY);
			if(distanceTest < distance) {
				closeWeapon = w;
			}
		}
		if(MenuManager.currentLevel.weapons.size() > 0) {
			distanceFrom = HvlMath.distance(Game.FIXED_X, Game.FIXED_Y, closeWeapon.actX, closeWeapon.actY);
			if(distanceFrom < PLAYER_SIZE/3 && this.playerWeapon == null) {
				this.playerWeapon = closeWeapon;
				closeWeapon.onPlayer = true;
				closeWeapon.remove();
			}
		}
		if(this.playerWeapon != null) {
			this.playerWeapon.draw(this.vx);
		}
	}
	
	public void drawPlayer(Player player) {
		hvlDrawQuadc(player.get_x() + -this.get_x() + Game.FIXED_X, player.get_y() + -this.get_y() + Game.FIXED_Y, this.get_width(), Player.PLAYER_SIZE, this.currentAnimation);
		if(player.playerWeapon != null) {
			player.playerWeapon.draw(player.vx);
		}
	}
	
	public float get_width() {
		if(this.vx <= 0) {return -PLAYER_SIZE;}
		else {return PLAYER_SIZE;}
	}
	
	public float get_x() {//returns a player’s x position
		return this.x;
	}
	
	public float get_y() {//returns a player’s y position
		return this.y;
	}
	
	public void set_x(int xArg) {//sets a player’s x-position
		this.x = xArg;
	}
	
	public void set_y(int yArg) { //sets a player’s y-position
		this.y = yArg;
	}
}
