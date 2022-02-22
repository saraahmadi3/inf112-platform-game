package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class AbstractObject implements GameObjects {
	
	private Sprite sprite;
	private double x;
	private double y;
	private int height;
	private int width;
	private GameState game;
	
	public boolean checkForHit(Player player) {
		boolean checkForXOverlap = player.getX()+player.getWidth() > getX() && player.getX() < getX()+getWidth();
		boolean checkForYOverlap = player.getY()+player.getHeight() > getY() && player.getY()<getY()+getHeight();
		
		if (checkForXOverlap && checkForYOverlap) {
			return true;
		} else {
			return false;
		}
	}
	
	public void setGameState(GameState gameState) {
		game = gameState;
	}
	
	public GameState getGameState() {
		return game;
	}
	
	@Override
	public String getSymbol() {
		return "AbstractObject"; 
	}
	
	public String getType() {
		return "AbstractObject";
	}

	@Override
	public double getX() {
		return x;
	}
	
	public double getXMid() {
		return getX() + (getWidth()/2);
	}

	public void setX(double xCoordinate) {
		x = xCoordinate;
	}
	
	public void moveByX(double xMovment) {
		setX(getX() + xMovment);
	}
	
	@Override
	public double getY() { 
		return y;
	}
	
	public double getYMid() {
		return getY() + (getHeight()/2);
	}
	
	public void setY(double yCoordinate) {
		y = yCoordinate;
	}
	
	public void moveByY(double yMovment) {
		setY(getY() + yMovment);
	}
	
	public void moveByXandY(double xMovment, double yMovment) {
		moveByX(xMovment);
		moveByY(yMovment);
	}
	
	@Override
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int spriteWidth) {
		width = spriteWidth;
	}

	@Override
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int spriteHeight) {
		height = spriteHeight;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public void setSprite(String fileName) {
		if (fileName != "testMode") {
			FileHandle playerFileHandle = Gdx.files.internal("game/img/"+fileName); 
		    Texture playerTexture = new Texture(playerFileHandle);
		    sprite = new Sprite(playerTexture, getWidth(), getHeight());
		}
	}
	
	@Override
	public void draw() {
		draw(game.getBatch(), game.getFont());
	}
	
	public void draw(SpriteBatch batch, BitmapFont font) {
		if (getSprite() != null) {
			batch.draw(getSprite(), (float) getX(), (float) getY());
		} else {
			font.draw(batch, getSymbol(), (float) getX(), (float) getY());
		}
	}
	
	public void update() {
		return;
	}
	
	public void move() {
		return;
	}
}
