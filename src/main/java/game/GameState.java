package game;

import java.util.ArrayList;

public class GameState {
	
	private Player player;
	private ArrayList<GameObjects> allSprites;
	
	public GameState() {
		allSprites = new ArrayList<GameObjects>();
		
		Tips tip = new Tips();
		player = new Player(50,15);
		
		Platform platform = new Platform(300,50,200,8);
		Platform platform2 = new Platform(50,15,4,4);
		addSprite(platform); 
		addSprite(platform2); 
		addSprite(tip);
		addSprite(player); 
	}
	
	
	public void addSprite(GameObjects s) {
		allSprites.add(s);
	}
	
	public void killSprite(GameObjects s) {
		allSprites.remove(s);
	}
	
	public ArrayList<GameObjects> getAllSprites() {
		return allSprites; 
	}
}
