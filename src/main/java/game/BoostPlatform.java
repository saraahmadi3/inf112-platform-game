package game;

public class BoostPlatform extends Platform{
	
	private double boostFactor;
	
	public BoostPlatform(GameState game, int x, int y, int width, int height, double boostFactor) {
		super(game, x, y, width, height, "boost.png");
		super.setGameState(game);
		this.boostFactor = boostFactor;
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
		checkForBoost(1);
		if (super.getGameState().getPlayer(2) != null) {
			checkForBoost(2);
		}
	}
	
}
