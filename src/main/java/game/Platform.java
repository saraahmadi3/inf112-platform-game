package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
 
public class Platform implements GameObjects {
	private Sprite platformSprite;
	public double x; //X-coordinate for platform
	public double y; //Y-coordinate for platform
	private int width;
	private int height; 
 
	public Platform(GameState game, int x, int y, int width, int height) {
		this(game, x, y, width, height, "platform.png");
	}
	
	public Platform(GameState game, int x, int y, int width, int height, String imgFile) {
		 this.x = x;
		 this.y = y;
		 
		 this.width = width;
		 this.height = height;
		 
		 FileHandle platformFileHandle = Gdx.files.internal("game/img/"+imgFile); 
		 Texture platformTexture = new Texture(platformFileHandle);
		 platformSprite = new Sprite(platformTexture, width, height);
		 
		 game.addSprite(this);
	}
	
	@Override
	public String getSymbol() {
		return "##################"; 	//currently all platforms are the same
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
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}
	
	@Override
	public void draw(SpriteBatch batch, BitmapFont font) {
		batch.draw(platformSprite, (float) getX(), (float) getY());
	}

	@Override 
	public void move() {
		return;

	}

	@Override
	public void update() {

	}
	
	public boolean checkForHit(Player player) {
		boolean checkForLeftXOverlap = player.getX() < getX() && (player.getX()+player.getWidth()>getX());
		boolean checkForRightXOverlap = player.getX() < getX()+getWidth() && (player.getX()+player.getWidth()>getX()+getWidth());
		boolean checkForMidXOverlap = player.getX() >= getX() && player.getX()+player.getWidth()<=getX()+getWidth();
		boolean checkForTopYOverlap = player.getY() < getY()+getHeight() && (player.getY()+player.getHeight()>getY()+getHeight());
		boolean checkForBottomYOverlap = player.getY() < getY() && (player.getY()+player.getHeight()>getY());
		boolean checkForMidYOverlap = player.getY() >= getY() && player.getY()+player.getHeight()<=getY()+getHeight();
		
		if ((checkForLeftXOverlap || checkForRightXOverlap || checkForMidXOverlap) && (checkForTopYOverlap || checkForBottomYOverlap || checkForMidYOverlap)) {
			return true;
		} else {
			return false;
		}
	}
}
