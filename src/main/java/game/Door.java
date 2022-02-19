package game;

public class Door extends Platform {
	
	private GameState game;
	private static final boolean SOLID = true;
	
	public Door(GameState game, int x, int y, int width, int height) {
		super(game, x, y, width, height, "door.png");
		this.game = game;
		
	}
	
	public void checkForKey() {
		Player player1 = game.getPlayer1();
		if (player1.hasKey() && player1.getCurrentPlatform()==this){
			openDoor(player1);
		}
	}
	
	private void openDoor(Player player) {
		player.useKey();
		game.levelComplete();
	}
	public void update() {
		
		checkForKey();
	}
	
}
