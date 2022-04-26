package game;

import com.badlogic.gdx.graphics.Color;

public class Text extends AbstractObject{

	private String txt;
	private Color color;
	private float scale;
	
	
	public Text(GameState game, double x, double y, String txt) {
		this(game, x, y, txt, 1, new Color(255, 0, 0, 255));
	}
	
	public Text(GameState game, double x, double y, String txt, float scale, Color color) {
		super.setGameState(game);
		game.addSprite(this);
		
		super.setX(x);
		super.setY(y);
		this.txt = txt;
		this.scale = scale;
		this.color = color;

		// TODO Find more accurate width/height numbers if needed
		// Does not actually set size, just for reference by other method who needs to know the estimated size
		super.setWidth(txt.length()*5);
		super.setHeight(10);
	}

	@Override
	public String getSymbol() {
		return txt;
	}
	
	public Color getColor() {
		return color;
	}
	
	public float getFontSize() {
		return scale;
	}
}
