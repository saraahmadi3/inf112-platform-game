package gameObjects;

import game.*;

public class Door extends Platform {
	
	public Door(GameState game, int x, int y, int width, int height, String imgFile) {
		super(game, x, y, width, height, imgFile);
		super.setGameState(game);	
	}
	
	public Door(GameState game, int x, int y, int width, int height) {
		this(game, x , y, width, height, "door.png");
	}
	
	//should only open the door if the player has a key and touches the door
	public void checkForKey(int playerId) {
		Player player = super.getGameState().getPlayer(playerId);
		if (player.hasKey() && (player.getCurrentPlatform()==this)){
			openDoor(player);
		}
	}
	
	//After door is opened the player should no longer have the key
	private void openDoor(Player player) {
		super.getGameState().playSound("door.ogg");
		player.useKey();
		super.getGameState().levelComplete(player.getIdentity());
		player.killPlayer();
		
	}
	
	@Override
	public void update() {
		if (super.getGameState().getPlayer(1) != null) {
			checkForKey(1);
		}
		if (super.getGameState().getPlayer(2) != null) {
			checkForKey(2);
		}
	}
	
}
