package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
 
public class Platform extends AbstractObject {
 
	public Platform(GameState game, int x, int y, int width, int height) {
		this(game, x, y, width, height, "platform.png");
	}
	
	public Platform(GameState game, int x, int y, int width, int height, String imgFile) {
		 super.setX(x);
		 super.setY(y);
		 
		 super.setWidth(width);
		 super.setHeight(height);
		 
		 FileHandle platformFileHandle = Gdx.files.internal("game/img/"+imgFile); 
		 Texture platformTexture = new Texture(platformFileHandle);
		 super.setSprite(new Sprite(platformTexture, width, height));
		 
		 game.addSprite(this);
	}
	
	public String getType() {
		return "Platform";
	}

}
