package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//TODO: Make interface that Player should implement. F.ex. an interface for any object which has coordinates.
public class Player implements GameObjects {
	
	private Sprite playerSprite;
	private int width;
	private int height;
	private GameState game;
	//coordinates must be double, if not Java will round the movement down to -1 for left/down, or 0 for up/right
	private double x; //X-coordinate for player
	private double y; //Y-coordinate for player
	
	private static final double V = 100; //Running velocity/speed for player	//later not static (powerups)
	private static final double J = 150; //Jump strength
	public static final double G = 150; //Gravity acceleration
	
	private static double gV; //Gravity/speed at which the player falls
	private static boolean isGrounded; //True if player is on ground
	private static boolean canDoubleJump; //True if the player can jump again
	private static Platform currentPlatform;
	
	public Player(int x, int y, GameState game) {
		isGrounded = false;
		canDoubleJump = false;
		this.game = game;
		this.x = x;
		this.y = y;
		gV = 0; 
		 
		height = 32;
		width = 16;
		
	    FileHandle playerFileHandle = Gdx.files.internal("game/img/player.png"); 
	    Texture playerTexture = new Texture(playerFileHandle);
	    playerSprite = new Sprite(playerTexture, width, height);
	    
	    game.addSprite(this);
	}
	
	public void Boost() {
		gV+=J;
	}
	
	public void moveByXandY(double xMovment, double yMovment) {
		x += xMovment;
		y += yMovment;
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
	
	//TODO: Use more advanced graphics for the player sprite
	public String getSymbol() {
		return "O";
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}
	
	public void draw(SpriteBatch batch, BitmapFont font) {
		batch.draw(playerSprite, (float) getX(), (float) getY());
	}
	
	public void update() {
		move();
	}
	
	//TODO Collision detection with platforms and other sprites
	public void move() {
		
		double delta = Gdx.graphics.getDeltaTime(); //The time passed since last frame
		
		double oldY = y;
		double oldX = x;
		
		y -= gV*delta; //Gravity
		
		gV += G*delta;
		
		isGrounded = false;
		for (Platform p : game.getAllPlatforms()) {
			if (p.checkForHit(this)) {
				if (oldY<y) {
					gV = G*delta*5; //Increase downwards momentum a little extra after hitting head on platform.
				} else { 
					//This should only happen when the player lands on top of the platform.
					isGrounded = true;
					canDoubleJump = false;
				} 
				currentPlatform = p;
				y=oldY;
				gV = G*delta;
				break;
			}
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
		
		for (Platform p : game.getAllPlatforms()) {
			if (p.checkForHit(this)) {
				x=oldX;
				break;
			}
		}
		
	}
}
