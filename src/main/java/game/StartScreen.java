package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class StartScreen extends AbstractObject {
	
	GameState game;
	int startLvl;
	
	public StartScreen(GameState gamestate, int startLevel) {
		game = gamestate;
		startLvl = startLevel;
		super.setGameState(game);
		this.setX(0);
		this.setY(0);
		this.setWidth(1080);
		this.setHeight(720);
		game.setMultiPlayer(false); //Default value to be changed
		
		this.setSprite("startScreen.png");
		
		game.addSprite(this);
	}
	
	@Override
	public void update() {
		if(Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.D)) {
			game.setSinglePlayerID(1);
			game.level(startLvl);
		} else if(Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.RIGHT)) {
			game.setSinglePlayerID(2);
			game.level(startLvl);
		}
		
		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			game.setSinglePlayerID(0); //This value should not be accessed anyways.
			game.setMultiPlayer(true);
			game.level(startLvl);
		}
	}
}
