package game;

import java.util.ArrayList;

public class GameState {
	
	private Player player;
	private ArrayList<Sprite> allSprites;
	
	public GameState() {
		allSprites = new ArrayList<Sprite>();
		
		player = new Player();
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
