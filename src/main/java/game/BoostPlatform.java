package game;

public class BoostPlatform extends Platform{
	
	private double boostFactor;
	
	public BoostPlatform(GameState game, int x, int y, int width, int height, double boostFactor, String imgFile) {
		super(game, x, y, width, height, imgFile);
		super.setGameState(game);
		this.boostFactor = boostFactor;
	}
	
	public BoostPlatform(GameState game, int x, int y, int width, int height, double boostFactor) {
		this(game, x, y, width, height, boostFactor, "Boost.png");
	}
	
	//Checks if a player is on top of the platform and then boost the player.
	public void checkForBoost(int playerId) {
		Player player = super.getGameState().getPlayer(playerId);
		
		if ((player.getCurrentPlatform() == this && player.getGrounded()) || super.checkForHit(player)){
			player.boost(boostFactor);
		}
	}
	
	@Override
	public void update() {
		if (super.getGameState().getPlayer(1) != null) {
			checkForBoost(1);
		}
		if (super.getGameState().getPlayer(2) != null) {
			checkForBoost(2);
		}
	}
	
}
