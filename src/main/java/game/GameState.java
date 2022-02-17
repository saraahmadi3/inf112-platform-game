package game;

import java.util.ArrayList;

public class GameState {
	
	private Player player;
	private ArrayList<GameObjects> allSprites;
	private ArrayList<Platform> allPlatforms;
	
	public GameState() {
		allSprites = new ArrayList<GameObjects>();
		allPlatforms = new ArrayList<Platform>();
		
		Tips tip = new Tips();
		player = new Player(50,15, this);
		
		Platform platform = new Platform(300,50,200,8);
		Platform platform2 = new Platform(500,30,300,300);
		addSprite(platform); 
		addSprite(platform2); 
		addSprite(tip);
		addSprite(player); 
	}
	
	
	public void addSprite(GameObjects s) {
		allSprites.add(s);
		if (s.getSymbol() == "##################") {
			allPlatforms.add((Platform) s);
		}
	}
	
	public void killSprite(GameObjects s) {
		allSprites.remove(s);
		if (s.getSymbol() == "##################") {
			allPlatforms.remove((Platform) s);
		}
	}
	
	public ArrayList<Platform> getAllPlatforms() {
		return allPlatforms;
	}
	
	public ArrayList<GameObjects> getAllSprites() {
		return allSprites; 
	}
}
