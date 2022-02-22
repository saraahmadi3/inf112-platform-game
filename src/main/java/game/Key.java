package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Key extends AbstractObject {

	private GameState game;
	
	public Key(GameState game, int x, int y) {
		 super.setX(x);
		 super.setY(y);
		 this.game = game;
		 
		 super.setWidth(18);
		 super.setHeight(30);
		 
		 FileHandle platformFileHandle = Gdx.files.internal("game/img/key.png"); 
		 Texture platformTexture = new Texture(platformFileHandle);
		 super.setSprite(new Sprite(platformTexture, super.getWidth(), super.getHeight()));
		 
		 game.addSprite(this);
	}

	@Override
	public void update() {
		Player player = game.getPlayer1();
		if (checkForHit(player)){
			if(player.pickUpKey()) {
				game.killSprite(this);
			}
		}
		
	}

}
