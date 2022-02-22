package game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * An interface that determines the behaviours and variables of all sprites in the game. 
 * 
 * @author saraa
 *
 */
public interface GameObjects {
	/**
	 * @return the string symbol that represents the sprite
	 */
	public String getSymbol();
	/**
	 * @return the sprite's double position in the x-axis
	 */
	public double getX();
	/**
	 * @return the sprite's double position in the y-axis
	 */
	public double getY();
	
	/**
	 * @return the sprite's integer width
	 */
	public int getWidth();
	
	/**
	 * @return the sprite's integer height
	 */
	public int getHeight();
	/**
	 * draws the sprite onto the board
	 */
	public void draw();
	/**
	 * updates the sprite's stat's as the game renders
	 */
	public void update();
	/**
	 * moves the sprite's position as the game renders
	 */
	public void move();
	
	/**
	 * @return the type of the object
	 */
	public String getType();
	
}
