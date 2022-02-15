package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;


public class Player {
	private static int x;
	private static int y;
	private static int v;
	
	public Player() {
		x = 200;
		y = 200;
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
