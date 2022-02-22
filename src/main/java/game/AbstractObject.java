package game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class AbstractObject implements GameObjects {
	
	private Sprite sprite;
	private double x;
	private double y;
	private int height;
	private int width;
	
	public boolean checkForHit(Player player) {
		boolean checkForXOverlap = player.getX()+player.getWidth() > getX() && player.getX() < getX()+getWidth();
		boolean checkForYOverlap = player.getY()+player.getHeight() > getY() && player.getY()<getY()+getHeight();
		
		if (checkForXOverlap && checkForYOverlap) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	@Override
	public String getSymbol() {
		return "Object"; 
	}
	
	public String getType() {
		return "AbstractObject";
	}

	@Override
	public double getX() {
		return x;
	}

	public void setX(double xCoordinate) {
		x = xCoordinate;
	}
	
	public void moveByX(double xMovment) {
		x += xMovment;
	}
	
	@Override
	public double getY() { 
		return y;
	}
	
	public void setY(double yCoordinate) {
		y = yCoordinate;
	}
	
	public void moveByY(double yMovment) {
		y += yMovment;
	}
	
	public void moveByXandY(double xMovment, double yMovment) {
		x += xMovment;
		y += yMovment;
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
	
	public void setSprite(Sprite s) {
		sprite = s;
	}
	
	@Override
	public void draw(SpriteBatch batch, BitmapFont font) {
		batch.draw(getSprite(), (float) getX(), (float) getY());
	}
	
	public void update() {
		return;
	}
	
	public void move() {
		return;
	}
}
