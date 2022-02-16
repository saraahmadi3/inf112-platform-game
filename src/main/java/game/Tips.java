package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tips implements Sprite {
	private static double x; //X-coordinate for text
	private static double y; //Y-coordinate for text		
	private static final double V = 75; //Velocity for text
	private static final int spaces = 200; //Spaces between each tip
	
	//TODO Add more tips as the game gets more mechanics
	private static final String tip = 
			"Tip: You can move to the left or right even while in the air." + " ".repeat(spaces)
			+ "Tip: You can press DOWN while in the air to get down faster." + " ".repeat(spaces)
			+ "TIP: While falling back down after a jump you can press jump again to do a double jump." + " ".repeat(spaces)
			+ "TIP: After doing a double jump you can not jump again bafore landing." + " ".repeat(spaces)
			+ "Tip: Moving while in the air is slower than running on the ground." + " ".repeat(spaces)
			+ "Tip: Using the double jump at the right time is crucial. Use it as soon as possible to gain the most height, or wait to get more distance." + " ".repeat(spaces);
	

	public Tips() {
		x = 1250; 
		y = 700;   
	}
	
	public String getSymbol() {
		return tip;
	}
	
	public void draw(SpriteBatch batch, BitmapFont font) {
		font.draw(batch, getSymbol(), getX(), getY());
	}
	
	public int getX() {
		return (int) x;
	}
	
	public int getY() {
		return (int) y;
	}
	
	public void move() {
		double delta = Gdx.graphics.getDeltaTime(); //The time passed since last frame
		x -= V*delta; //Scrolling
		
		if (x<-tip.length()*5) {
			x = 1250;
		}
	}
}
