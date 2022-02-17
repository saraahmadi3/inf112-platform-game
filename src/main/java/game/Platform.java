package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Platform implements GameObjects {
	
	private static Sprite platformSprite;
	private static double x; //X-coordinate for platform
	private static double y; //Y-coordinate for platform
	private static int width;
	private static int height; 
 
	public Platform(int x, int y, int width, int height) {
		 this.x = x;
		 this.y = y;
		 
		 this.width = width;
		 this.height = height;
		 
		 FileHandle platformFileHandle = Gdx.files.internal("game/img/platform.png"); 
		 Texture platformTexture = new Texture(platformFileHandle);
		 platformSprite = new Sprite(platformTexture, width, height);
	}
	
	@Override
	public String getSymbol() {
		return "##################"; 	//currently all platforms are the same
	}

	@Override
	public int getX() {
		
		return (int)x;
	}

	@Override
	public int getY() {

		return (int) y;
	}

	@Override
	public void draw(SpriteBatch batch, BitmapFont font) {
		batch.draw(platformSprite, getX(), getY());
	}

	@Override 
	public void move() {
		return;

	}

	@Override
	public void update() {
		// TODO add collision detection later on possibly here
		
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