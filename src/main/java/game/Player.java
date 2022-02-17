package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//TODO: Make interface that Player should implement. F.ex. an interface for any object which has coordinates.
public class Player implements Sprite {
	

	//coordinates must be double, if not Java will round the movement down to -1 for left/down, or 0 for up/right
	private static double x; //X-coordinate for player
	private static double y; //Y-coordinate for player
	
	private static final double V = 100; //Running velocity/speed for player
	private static final double J = 150; //Jump strength
	private static final double G = 150; //Gravity acceleration
	private static final double FLOOR = 12.5; //The Y-coordinate at which there is a floor
	
	private static double gV; //Gravity/speed at which the player falls
	private static boolean isGrounded; //True if player is on ground
	private static boolean canDoubleJump; //True if the player can jump again
	
	public Player() {
		isGrounded = false;
		canDoubleJump = false;
		x = 50; 
		y = 50; 
		gV = 0;  
	}
	
	//TODO: Use more advanced graphics for the player sprite
	public String getSymbol() {
		return "O";
	}
	
	public int getX() {
		return (int) x;
	}
	
	public int getY() {
		return (int) y;
	}
	
	public void draw(SpriteBatch batch, BitmapFont font) {
		font.draw(batch, getSymbol(), getX(), getY());
	}
	
	public void update() {
		move();
	}
	
	//TODO Collision detection with platforms and other sprites
	public void move() {
		
		double delta = Gdx.graphics.getDeltaTime(); //The time passed since last frame
		
		y -= gV*delta; //Gravity
		
		gV += G*delta;
		
		isGrounded = false;
		if (y <= FLOOR) {
			isGrounded = true;
			canDoubleJump = false;   
			y = FLOOR;
			gV = 0;
		}
		
		//Moves the player to the left, slower while in the air
		if(Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) {
			if (isGrounded) {
				x -= delta * V;
			} else {
				x -= delta * (V/3);
			}
		} 
		
		//Moves the player to the right, slower while in the air 
		if(Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)) {
			if (isGrounded) {
				x += delta * V;
			} else {
				x += delta * (V/3);
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
		
		//TODO: Make this number equal to the screen width minus the width of the player, not just a hardcoded number.
		if (x>1068) {
			x = 1068;
		} else if (x<0) {
			x = 0;
		}
		
	}
}
