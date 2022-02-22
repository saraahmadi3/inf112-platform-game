package game;

public class Text extends AbstractObject{

	private String txt;
	
	public Text(GameState game, double x, double y, String txt) {
		super.setGameState(game);
		game.addSprite(this);
		
		super.setX(x);
		super.setY(y);
		this.txt = txt;
	
		// TODO Find more accurate width/height numbers if needed
		super.setWidth(txt.length()*5);
		super.setHeight(10);
	}
	
	@Override
	public String getSymbol() {
		return txt;
	}
}
