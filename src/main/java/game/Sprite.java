package game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


//TODO Document
public interface Sprite {
	
	public String getSymbol();
	
	public int getX();
	
	public int getY();
	
	public void draw(SpriteBatch batch, BitmapFont font);
	
	public void move();
}
