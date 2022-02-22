package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
		this(x, y, game, "player.png", 1);
	}
	
	public Player(int x, int y, GameState game) {
		this(x, y, game, "player.png", 1);
	}
	
	public void boost(double boostFactor) {
		gV=-J*boostFactor;
		isGrounded = false;
		canDoubleJump = true;
	}
	
	public int getLives() {
		return lives;
	}
	
	public int getIdentity() {
		return identity;
	}
	
	public void loseLife() {
		lives--;
	}
	
	public boolean hasKey() {
		return hasKey;
	}
	
	public void useKey() {
		hasKey=false;
	}
	
	public boolean pickUpKey() {
		if (hasKey) {
			return false;
		} else {
			hasKey=true;
			return true;
		}
	}
	
	public Platform getCurrentPlatform() {
		return currentPlatform;
	}
	
	public boolean getGrounded() {
		return isGrounded;
	}
	
	public double getGv() {
		return gV;
	}
	
	public void update() {
		move();
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
		
		if (super.getY()+super.getHeight()<0) {
			loseLife();
			if (getLives()>0) {
				super.setXandY(50, 15);
				canDoubleJump=false;
				gV = G*delta;
			} else {
				super.getGameState().killSprite(this);
				
				//TODO do something other than just display text
				new Text(super.getGameState(), 400, 300, "You Died!");
			}
		}
		
	}
}
