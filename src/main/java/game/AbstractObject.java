package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class AbstractObject implements GameObjects {
	
	private Sprite sprite;
	private double x;
	private double y;
	private int height;
	private int width;
	private GameState game;
	
	/**
	 * Checks if and object is overlapping with a player
	 * @param player
	 * @return boolean: true, if overlapping, false, if not
	 */
	public boolean checkForHit(Player player) {
		boolean checkForXOverlap = player.getX()+player.getWidth() > getX() && player.getX() < getX()+getWidth();
		boolean checkForYOverlap = player.getY()+player.getHeight() > getY() && player.getY()<getY()+getHeight();
		
		if (checkForXOverlap && checkForYOverlap) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * sets current gameState 
	 * @param gameState
	 */
	public void setGameState(GameState gameState) {
		game = gameState;
	}
	
	/**
	 * 
	 * @return current gameState
	 */
	public GameState getGameState() {
		return game;
	}
	
	@Override
	public String getSymbol() {
		return "AbstractObject"; 
	}
	
	@Override
	public String getType() {
		return "AbstractObject";
	}

	@Override
	public double getX() {
		return x;
	}
	/**
	 * returns the x coordinate thats at the mid point of the object as a double
	 * @return double: mid x-coordinate
	 */
	public double getXMid() {
		return getX() + (getWidth()/2);
	}
	
	/**
	 * sets a objects x-coordinate
	 * @param xCoordinate
	 */
	public void setX(double xCoordinate) {
		x = xCoordinate;
	}
	
	/**
	 * moves a object by a specified amount in the x direction
	 * @param xMovment
	 */
	public void moveByX(double xMovment) {
		setX(getX() + xMovment);
	}
	
	@Override
	public double getY() { 
		return y;
	}
	
	/**
	 * returns the y coordinate thats at the mid point of the object as a double
	 * @return double: mid y-coordinate
	 */
	public double getYMid() {
		return getY() + (getHeight()/2);
	}
	
	/**
	 * sets a objects y-coordinate
	 * @param yCoordinate
	 */
	public void setY(double yCoordinate) {
		y = yCoordinate;
	}
	
	/**
	 * moves a object by a specified amount in the y direction
	 * @param yMovment
	 */
	public void moveByY(double yMovment) {
		setY(getY() + yMovment);
	}
	
	/**
	 * moves a object by a specified amount in the x and y direction
	 * @param xMovment
	 * @param yMovment
	 */
	public void moveByXandY(double xMovment, double yMovment) {
		moveByX(xMovment);
		moveByY(yMovment);
	}
	/**
	 * sets an objects x- and y-coordinate
	 * @param xCoordinate
	 * @param yCoordinate
	 */
	public void setXandY(double xCoordinate, double yCoordinate) {
		setX(xCoordinate);
		setY(yCoordinate);
	}
	
	@Override
	public int getWidth() {
		return width;
	}
	
	/**
	 * sets an objects width
	 * @param spriteWidth
	 */
	public void setWidth(int spriteWidth) {
		width = spriteWidth;
	}

	@Override
	public int getHeight() {
		return height;
	}
	
	/**
	 * sets an objects height
	 * @param spriteHeight
	 */
	public void setHeight(int spriteHeight) {
		height = spriteHeight;
	}
	
	/**
	 * 
	 * @return the sprite of the object
	 */
	public Sprite getSprite() {
		return sprite;
	}
	
	/**
	 * Sets the sprite based on a file name if the file name is valid
	 * @param fileName
	 */
	public void setSprite(String fileName) {
		if (fileName != null && !fileName.equals("testMode")) {
			try {
				FileHandle playerFileHandle = Gdx.files.internal("images/"+fileName); 
				Texture playerTexture = new Texture(playerFileHandle);
			    sprite = new Sprite(playerTexture, getWidth(), getHeight());
			}
			catch (Exception e) {
				System.out.println("An error occurred whilst trying to get the image file for the sprite.");
			}
		    
		}
	}
	
	@Override
	public void draw() {
		draw(game.getBatch(), game.getFont());
	}
	
	public void draw(SpriteBatch batch, BitmapFont font) {
		if (getSprite() != null) {
			batch.draw(getSprite(), (float) getX(), (float) getY());
		} else {
			font.draw(batch, getSymbol(), (float) getX(), (float) getY());
		}
	}
	
	@Override
	public void update() {
		return;
	}
	
	@Override
	public void move() {
		return;
	}
}
