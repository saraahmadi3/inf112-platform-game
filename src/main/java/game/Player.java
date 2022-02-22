package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Player extends AbstractObject {
	
	private static final double V = 100; //Running velocity/speed for player	//later not static (powerups)
	private static final double J = 150; //Jump strength
	public static final double G = 150; //Gravity acceleration
		
	private double gV; //Gravity/speed at which the player falls
	private boolean isGrounded; //True if player is on ground
	private boolean canDoubleJump; //True if the player can jump again
	private Platform currentPlatform;
	private boolean hasKey;
	private int lives;
	private int identity; //Should be 1 or 2
	
	public Player(int x, int y, GameState game, String imgFile, int playerNumber) {
		identity = playerNumber;
		super.setGameState(game);
		game.addSprite(this);
		
		super.setX(x);
		super.setY(y);
		
		super.setHeight(32);
		super.setWidth(16);
		
	    super.setSprite(imgFile);
	    
		isGrounded = false;
		canDoubleJump = false;
		hasKey = false;
		gV = 0; 
		
		lives = 3;
	}
	
	public Player(int x, int y, GameState game, String imgFile) {
		this(x, y, game, imgFile, 1);
	}
	
	public Player(int x, int y, GameState game) {
		this(x, y, game, "player.png", 1);
	}
	
	//Is activated by booster platform
	public void boost(double boostFactor) {
		gV=-J*boostFactor;
		isGrounded = false;
		canDoubleJump = true;
	}
	
	//returns the number of lives the player has
	public int getLives() {
		return lives;
	}
	
	//returns the ID of the player, meaning 1 or 2.
	public int getIdentity() {
		return identity;
	}
	
	//should be called when the player dies, removes one life.
	public void loseLife() {
		lives--;
	}
	
	//only returns true if the player has a key, false otherwise
	public boolean hasKey() {
		return hasKey;
	}
	
	//uses the key, which means the player no longer has the key
	public void useKey() {
		hasKey=false;
	}
	
	//picks up a key, which means the player now has a key
	public boolean pickUpKey() {
		if (hasKey) {
			return false;
		} else {
			hasKey=true;
			return true;
		}
	}
	
	//returns the platform most recently touched by the player 
	public Platform getCurrentPlatform() {
		return currentPlatform;
	}
	
	//returns true if the player is not in the air
	public boolean getGrounded() {
		return isGrounded;
	}
	
	public double getGv() {
		return gV;
	}
	
	public void update() {
		move();
		checkForDeath();
	}
	
	//checks if the player has fallen below the screen and should die.
	public void checkForDeath() {
		if (super.getY()+super.getHeight()<0) {
			loseLife();
			if (getLives()>0) {
				super.setXandY(50, 15);
				canDoubleJump=false;
				gV = 0;
			} else {
				super.getGameState().killSprite(this);
				
				//TODO do something other than just display text when player dies
				new Text(super.getGameState(), 400, 300, "You Died!");
			}
		}
		
	}
	
	public void move() {
		
		double delta = super.getGameState().getDeltaTime(); //The time passed since last frame
		
		super.moveByY(-gV*delta); //Gravity
		
		gV += G*delta;
		
		isGrounded = false;
		for (Platform p : super.getGameState().getAllPlatforms()) {
			if (p.checkForHit(this)) {
				currentPlatform = p;
				
				if (super.getYMid()<p.getYMid()) {
					super.setY(p.getY()-super.getHeight());
				} else { 
					//This should only happen when the player lands on top of the platform.
					super.setY(p.getY()+p.getHeight());
					isGrounded = true;
					canDoubleJump = false;
				} 
				gV = G*delta;
				break;
			}
		}
				
		//Moves the player to the left, slower while in the air
		if((Gdx.input.isKeyPressed(Keys.A) && identity == 1) || (Gdx.input.isKeyPressed(Keys.LEFT) && identity == 2)) {
			if (isGrounded) {
				super.moveByX(-delta*V);
			} else {
				super.moveByX(-delta*(V/2));
			}
		} 
		
		//Moves the player to the right, slower while in the air 
		if(Gdx.input.isKeyPressed(Keys.D)  && identity == 1 || Gdx.input.isKeyPressed(Keys.RIGHT)  && identity == 2) {
			if (isGrounded) {
				super.moveByX(delta*V);
			} else {
				super.moveByX(delta*(V/2));
			}
		}
		
		//Allows the player to get down faster after a jump (or when falling in general) by canceling any upwards momentum and amplifying gravity
		if(Gdx.input.isKeyPressed(Keys.S)  && identity == 1 || Gdx.input.isKeyPressed(Keys.DOWN)  && identity == 2) {
			if (gV<0) {
				gV = 0;
			}
			gV += 5*G*delta;
		}
				
		//Jump, this happens once and therefore deltaTime should not be considered.
		if(Gdx.input.isKeyPressed(Keys.W)  && identity == 1 || Gdx.input.isKeyPressed(Keys.UP)  && identity == 2) {
			if (isGrounded) {
				canDoubleJump = true;
				gV = -J;
			//Allows the player to jump again one time in mid-air after the first jump 
			} else if (canDoubleJump && gV>=0) {
				canDoubleJump = false;
				gV = -J;
			}
		}
		
		for (Platform p : super.getGameState().getAllPlatforms()) {
			if (p.checkForHit(this)) {
				currentPlatform = p;
				if (super.getXMid()<p.getXMid()) {
					super.setX(p.getX()-super.getWidth());
				} else {
					super.setX(p.getX()+p.getWidth());
				}
				break;
			}
		}
	}
}
