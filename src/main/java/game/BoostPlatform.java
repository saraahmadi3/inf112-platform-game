package game;

public class BoostPlatform extends Platform{
	
	private double boostFactor;
	
	public BoostPlatform(GameState game, int x, int y, int width, int height, double boostFactor) {
		super(game, x, y, width, height, "boost.png");
		super.setGameState(game);
		this.boostFactor = boostFactor;
	}
	
	public void checkForBoost() {
		Player player1 = super.getGameState().getPlayer1();
		
		if ((player1.getCurrentPlatform() == this && player1.getGrounded()) || super.checkForHit(player1)){
			player1.boost(boostFactor);
		}
	}
	
	@Override
	public void update() {
		checkForBoost();
	}
	
}
