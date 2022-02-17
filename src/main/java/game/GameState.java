package game;

import java.util.ArrayList;

public class GameState {
	
	private Player player;
	private ArrayList<GameObjects> allSprites;
	
	public GameState() {
		allSprites = new ArrayList<GameObjects>();
		
		Tips tip = new Tips();
		player = new Player();
		Platform platform = new Platform();
		
		addSprite(platform);
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
