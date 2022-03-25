package game;

public class BoostPlatform extends Platform{
	
	private double boostFactor;
	/**
	 * Constructor. Creates a booster platform based on the GameState, x and y coordinate, its width and height, a boost factor 
	 * as well as an image file.
	 * The boost factor decides how much the players gravity/acceleration (player.gV) will change when it lands on this platform.
	 * @param game
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param boostFactor
	 * @param imgFile
	 */
	public BoostPlatform(GameState game, int x, int y, int width, int height, double boostFactor, String imgFile) {
		super(game, x, y, width, height, imgFile);
		super.setGameState(game);
		this.boostFactor = boostFactor;
	}
	/**
	 * Constructor. Creates a platform based on the GameState, x and y coordinate, its width and height as well as a boost factor.
	 * The boost factor decides how much the players gravity/acceleration (player.gV) will change when it lands on this platform.
	 * @param game
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param boostFactor
	 */
	public BoostPlatform(GameState game, int x, int y, int width, int height, double boostFactor) {
		this(game, x, y, width, height, boostFactor, "boost.png");
	}
	
	/**
	 * Checks if a player is on top of the platform and then boost the player.
	 * @param playerId
	 */
	public void checkForBoost(int playerId) {
		Player player = super.getGameState().getPlayer(playerId);
		
		if ((player.getCurrentPlatform() == this && player.getGrounded()) || super.checkForHit(player)){
			player.boost(boostFactor);
		}
	}
	
	
	/**
	 * checks the number of players in the the current gameState then updates the sprite's stats as the game renders. 
	 */
	public void update() {
		if (super.getGameState().getPlayer(1) != null) {
			checkForBoost(1);
		}
		if (super.getGameState().getPlayer(2) != null) {
			checkForBoost(2);
		}
	}
	
}
