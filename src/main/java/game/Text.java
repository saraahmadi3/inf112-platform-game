package game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text implements GameObjects{

	private double x;
	private double y;
	private String txt;
	
	public Text(GameState game, double x, double y, String txt) {
		this.x = x;
		this.y = y;
		this.txt = txt;
		
		game.addSprite(this);
	}
	
	@Override
	public String getSymbol() {
		return txt;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public int getWidth() {
		// TODO Find accurate number if needed
		return txt.length()*5;
	}

	@Override
	public int getHeight() {
		// TODO Find accurate number if needed
		return 10;
	}

	@Override
	public void draw(SpriteBatch batch, BitmapFont font) {
		font.draw(batch, getSymbol(), (float) getX(), (float) getY());
	}
	

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

}
