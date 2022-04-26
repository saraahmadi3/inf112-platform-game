package gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import game.*;

public class GameOverKeyPress extends AbstractObject {
	
	public GameState game;
	
	public GameOverKeyPress(GameState game) {
		this.game = game;
		game.addSprite(this);
	}
	
	@Override
	public void draw() {
		return;
	}
	
	@Override
	public void update() {
		if(Gdx.input.isKeyPressed(Keys.SPACE)) {
			if (game.getServer() != null) {
				game.getServer().levelComplete();
			} else if (game.getClient() != null) {
				game.getClient().levelComplete();
			} else {
				game.level(1);
			}
		}
	}
}
