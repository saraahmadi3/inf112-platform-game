package game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Platform implements Sprite {
	
	private static double x; //X-coordinate for platform
	private static double y; //Y-coordinate for platform
 
	public Platform() {
		 x=300;
		 y=50;
	}
	
	@Override
	public String getSymbol() {
		
		return "##################"; 	//currently all platforms are the same
	}

	@Override
	public int getX() {
		
		return (int)x;
	}

	@Override
	public int getY() {

		return (int) y;
	}

	@Override
	public void draw(SpriteBatch batch, BitmapFont font) {
		font.draw(batch, getSymbol(), getX(), getY());
	}

	@Override 
	public void move() {
		return;

	}

	@Override
	public void update() {
		// TODO add collision detection later on possibly here
		
	}
	
}
