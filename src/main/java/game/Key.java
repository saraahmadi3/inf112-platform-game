package game;

public class Key extends AbstractObject {
	
	public Key(GameState game, int x, int y) {
		 super.setGameState(game);
		 game.addSprite(this);
		 
		 super.setX(x);
		 super.setY(y);
		 
		 super.setWidth(18);
		 super.setHeight(30);
		 
		 super.setSprite("key.png"); 
	}

	@Override
	public void update() {
		checkForPlayer(1);
		if (super.getGameState().getPlayer(2) != null) {
			checkForPlayer(2);
		}
	}

	public void checkForPlayer(int playerId) {
		Player player = super.getGameState().getPlayer(playerId);
		if (checkForHit(player)){
			if(player.pickUpKey()) {
				super.getGameState().killSprite(this);
			}
		}
		
	}
}
