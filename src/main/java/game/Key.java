package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Key implements GameObjects {

	private double x;
	private double y;
	private int width;
	private int height; 
	private Sprite keySprite;
	private GameState game;
	
	public Key(GameState game, int x, int y) {
		 this.x = x;
		 this.y = y;
		 this.game = game;
		 
		 width = 18;
		 height = 30;
		 
		 FileHandle platformFileHandle = Gdx.files.internal("game/img/key.png"); 
		 Texture platformTexture = new Texture(platformFileHandle);
		 keySprite = new Sprite(platformTexture, width, height);
		 
		 game.addSprite(this);
	}
	
	@Override
	public String getSymbol() {
		return "Key";
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
		batch.draw(keySprite, (float) getX(), (float) getY());
		
	}

	@Override
	public void update() {
		Player player = game.getPlayer1();
		if (checkForHit(player)){
			player.pickUpKey();
			game.killSprite(this);
		}
		
	}

	private boolean checkForHit(Player player) {
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

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

}
