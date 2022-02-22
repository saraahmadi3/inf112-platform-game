package game;

public class Door extends Platform {
	
	public Door(GameState game, int x, int y, int width, int height) {
		super(game, x, y, width, height, "door.png");
		super.setGameState(game);	
	}
	
	public void checkForKey() {
		Player player1 = super.getGameState().getPlayer1();
		if (player1.hasKey() && player1.getCurrentPlatform()==this){
			openDoor(player1);
		}
	}
	
	private void openDoor(Player player) {
		player.useKey();
		super.getGameState().levelComplete();
	}
	
	@Override
	public void update() {
		checkForKey();
	}
	
}
