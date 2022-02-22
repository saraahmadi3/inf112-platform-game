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
	
	private static double gV; //Gravity/speed at which the player falls
	private static boolean isGrounded; //True if player is on ground
	private static boolean canDoubleJump; //True if the player can jump again
	private static Platform currentPlatform;
	private static boolean hasKey;
	
	public Player(int x, int y, GameState game) {
		super.setGameState(game);
		game.addSprite(this);
		
		super.setX(x);
		super.setY(y);
		 
		super.setHeight(32);
		super.setWidth(16);
		
	    super.setSprite("player.png");
	    
		isGrounded = false;
		canDoubleJump = false;
		hasKey = false;
		gV = 0; 
	}
	
	public void boost(double boostFactor) {
		gV=-J*boostFactor;
		isGrounded = false;
		canDoubleJump = true;
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
		
		double delta = Gdx.graphics.getDeltaTime(); //The time passed since last frame
		
		double oldX = super.getX();
		double oldY = super.getY();
		
		super.moveByY(-gV*delta); //Gravity
		
		gV += G*delta;
		
		isGrounded = false;
		for (Platform p : super.getGameState().getAllPlatforms()) {
			if (p.checkForHit(this)) {
				if (oldY<super.getY()) {
					gV = G*delta*5; //Increase downwards momentum a little extra after hitting head on platform.
				} else { 
					//This should only happen when the player lands on top of the platform.
					isGrounded = true;
					canDoubleJump = false;
				} 
				currentPlatform = p;
				super.setY(oldY);
				gV = G*delta;
				break;
			}
		}
				
		//Moves the player to the left, slower while in the air
		if(Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) {
			if (isGrounded) {
				super.moveByX(-delta*V);
			} else {
				super.moveByX(-delta*(V/2));
			}
		} 
		
		//Moves the player to the right, slower while in the air 
		if(Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)) {
			if (isGrounded) {
				super.moveByX(delta*V);
			} else {
				super.moveByX(delta*(V/2));
			}
		}
		
		//Allows the player to get down faster after a jump (or when falling in general) by canceling any upwards momentum and amplifying gravity
		if(Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)) {
			if (gV<0) {
				gV = 0;
			}
			gV += 5*G*delta;
		}
				
		//Jump, this happens once and therefore deltaTime should not be considered.
		if(Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.SPACE)) {
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
				super.setX(oldX);
				currentPlatform = p;
				break;
			}
		}
		
	}
}
