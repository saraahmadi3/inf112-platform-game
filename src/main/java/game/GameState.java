package game;

import java.util.ArrayList;

public class GameState {
	
	private Player player;
	private ArrayList<Sprite> allSprites;
	
	public GameState() {
		allSprites = new ArrayList<Sprite>();
		
		Tips tip = new Tips();
		player = new Player();
		Platform platform = new Platform();
		
		addSprite(platform);
		addSprite(tip);
		addSprite(player);
	}
	
	
	public void addSprite(Sprite s) {
		allSprites.add(s);
	}
	
	public void killSprite(Sprite s) {
		allSprites.remove(s);
	}
	
	public ArrayList<Sprite> getAllSprites() {
		return allSprites; 
	}
}
