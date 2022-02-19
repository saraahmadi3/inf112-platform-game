package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tips implements GameObjects {
	private static double x; //X-coordinate for text
	private static double y; //Y-coordinate for text		
	private static final double V = 75; //Velocity for text
	private static final int spaces = 200; //Spaces between each tip
	
	private static int width;
	private static int height;
	
	//TODO Add more tips as the game gets more mechanics
	private static final String tip = 
			"Tip: You can move to the left or right even while in the air." + " ".repeat(spaces)
			+ "Tip: You can press DOWN while in the air to get down faster." + " ".repeat(spaces)
			+ "TIP: While falling back down after a jump you can press jump again to do a double jump." + " ".repeat(spaces)
			+ "TIP: After doing a double jump you can not jump again bafore landing." + " ".repeat(spaces)
			+ "Tip: Moving while in the air is slower than running on the ground." + " ".repeat(spaces)
			+ "Tip: If you run off the edge of a platform you lose your chance to jump and double jump." + " ".repeat(spaces)
			+ "Tip: Timing is key when moving platforms are involved, aim for where the platform is going to be, not where it is." + " ".repeat(spaces)
			+ "Tip: You need to pick up the key before you can open the door and complete the level." + " ".repeat(spaces)
			+ "Tip: A Booster platform launches you into the air and is useful for reaching high places." + " ".repeat(spaces)
			+ "Tip: After being launched into the air by a Booster platform you can use a double jump just like you would after a normal jump." + " ".repeat(spaces)
			+ "Tip: Ghost platforms will look identical to regular platforms until you step on one." + " ".repeat(spaces)
			+ "Tip: Ghost platforms will disappear if you stand on them for too long, but don't worry, after a while they come back." + " ".repeat(spaces)
			+ "Tip: Using the double jump at the right time is crucial. Use it as soon as possible to gain the most height, or wait to get more distance." + " ".repeat(spaces);
	

	public Tips(GameState game) {
		x = 1250; 
		y = 700;  
		
		width = tip.length()*5;
		height = 10;
		
		game.addSprite(this);
	}
	
	public String getSymbol() {
		return tip;
	}
	
	public void draw(SpriteBatch batch, BitmapFont font) {
		font.draw(batch, getSymbol(), (float) getX(), (float) getY());
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return (int) y;
	}
	
	public void update() {
		move();
	}
	
	public void move() {
		double delta = Gdx.graphics.getDeltaTime(); //The time passed since last frame
		x -= V*delta; //Scrolling
		
		if (x<-width) {
			x = 1250;
		}
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}
}
