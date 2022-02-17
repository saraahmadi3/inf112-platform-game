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
	 * @return the sprite's integer position in the x-axis
	 */
	public int getX();
	/**
	 * @return the sprite's integer position in the y-axis
	 */
	public int getY();
	/**
	 * draws the sprite onto the board
	 */
	public void draw(SpriteBatch batch, BitmapFont font);
	/**
	 * updates the sprite's stat's as the game renders
	 */
	public void update();
	/**
	 * moves the sprite's position as the game renders
	 */
	public void move();
}
