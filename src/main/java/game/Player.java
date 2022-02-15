package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;


public class Player {
	private static double x;
	private static double y;
	private static double v;
	
	public Player() {
		x = 200;
		y = 200;
		v = 60;
	}
	
	public static int getSpeed() {
		return (int) v;
	}
	
	public int getX() {
		return (int) x;
	}
	
	public int getY() {
		return (int) y;
	}
	
	public void move() {
		//coordinates must be double, if not Java will round the movement down to -1 for left/down, or 0 for up/right 
		if(Gdx.input.isKeyPressed(Keys.A)) 
			x -= Gdx.graphics.getDeltaTime() * v;
		if(Gdx.input.isKeyPressed(Keys.D)) 
			x += Gdx.graphics.getDeltaTime() * v;
		if(Gdx.input.isKeyPressed(Keys.W)) 
			y += Gdx.graphics.getDeltaTime() * v;
		if(Gdx.input.isKeyPressed(Keys.S)) 
			y -= Gdx.graphics.getDeltaTime() * v;
	}
}
