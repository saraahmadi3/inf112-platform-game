package game;

public class BoostPlatform extends Platform{
	
	private GameState game;
	private double boostFactor;
	
	public BoostPlatform(GameState game, int x, int y, int width, int height, double boostFactor) {
		super(game, x, y, width, height, "boost.png");
		this.game = game;
		this.boostFactor = boostFactor;
	}
	
	public void checkForBoost() {
		Player player1 = game.getPlayer1();
		
		if ((player1.getCurrentPlatform() == this && player1.getGrounded()) || super.checkForHit(player1)){
			player1.boost(boostFactor);
		}
	}
	
	
	public void update() {
		checkForBoost();
	}
	
}
