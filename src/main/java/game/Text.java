package game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text extends AbstractObject{

	private String txt;
	
	public Text(GameState game, double x, double y, String txt) {
		super.setX(x);
		super.setY(y);
		this.txt = txt;
		
		// TODO Find more accurate width/height numbers if needed
		super.setWidth(txt.length()*5);
		super.setHeight(10);
		
		game.addSprite(this);
	}
	
	@Override
	public String getSymbol() {
		return txt;
	}

	@Override
	public void draw(SpriteBatch batch, BitmapFont font) {
		font.draw(batch, getSymbol(), (float) getX(), (float) getY());
	}
}
