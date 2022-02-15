package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;


public class Player {
	private static int x;
	private static int y;
	private static int v;
	
	public Player() {
		x = 0;
		y = 0;
		v = 3;
	}
	
	public static int getSpeed() {
		return v;
	}
	
	public static int getX() {
		return x;
	}
	
	public static int getY() {
		return y;
	}
	
	public static void move() {
	
		if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) 
			x -= Gdx.graphics.getDeltaTime() * v;
		if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) 
			y += Gdx.graphics.getDeltaTime() * v;
		if(Gdx.input.isKeyPressed(Keys.DPAD_UP)) 
			y += Gdx.graphics.getDeltaTime() * v;
		if(Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) 
			x -= Gdx.graphics.getDeltaTime() * v;
	}
}
