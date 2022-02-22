package game;

public class Door extends Platform {
	
	public Door(GameState game, int x, int y, int width, int height) {
		super(game, x, y, width, height, "door.png");
		super.setGameState(game);	
	}
	
	public void checkForKey(int playerId) {
		Player player = super.getGameState().getPlayer(playerId);
		if (player.hasKey() && player.getCurrentPlatform()==this){
			openDoor(player);
		}
	}
	
	private void openDoor(Player player) {
		player.useKey();
		super.getGameState().levelComplete(player.getIdentity());
	}
	
	@Override
	public void update() {
		checkForKey(1);
		if (super.getGameState().getPlayer(2) != null) {
			checkForKey(2);
		}
	}
	
}
