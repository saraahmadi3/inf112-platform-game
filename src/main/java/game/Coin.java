package game;

public class Coin extends AbstractObject{
	
	public Coin(GameState game, int x, int y) {
		 super.setGameState(game);
		 game.addSprite(this);
		 
		 super.setX(x);
		 super.setY(y);
		 
		 super.setWidth(15);
		 super.setHeight(15);
		 
		 super.setSprite("coin.png"); 
	}
	
	@Override
	public void update() {
		
		if (super.getGameState().getPlayer(1) != null) {
			if (!checkForPlayer(1)) {
				if (super.getGameState().getPlayer(2) != null) {
					checkForPlayer(2);
				}
			}
		} else {
			if (super.getGameState().getPlayer(2) != null) {
				checkForPlayer(2);
			}
		}
	}
	
	public boolean checkForPlayer(int playerId) {
		Player player = super.getGameState().getPlayer(playerId);
		if (checkForHit(player)){
			player.changeScoreBy(25);
			super.getGameState().killSprite(this);
			super.getGameState().playSound("link4.ogg");
			return true;
		}
		return false;
		
	}
	

	
}
