package game;

public class Door extends Platform {
	
	public Door(GameState game, int x, int y, int width, int height) {
		super(game, x, y, width, height, "door.png");
		super.setGameState(game);	
	}
	
	//should only open the door if the player has a key and touches the door
	public void checkForKey(int playerId) {
		Player player = super.getGameState().getPlayer(playerId);
		if (player.hasKey() && player.getCurrentPlatform()==this){
			openDoor(player);
		}
	}
	
	//After door is opened the player should no longer have the key
	private void openDoor(Player player) {
		player.useKey();
		super.getGameState().levelComplete(player.getIdentity());
		super.getGameState().killSprite(player);
	}
	
	@Override
	public void update() {
		checkForKey(1);
		if (super.getGameState().getPlayer(2) != null) {
			checkForKey(2);
		}
	}
	
}
