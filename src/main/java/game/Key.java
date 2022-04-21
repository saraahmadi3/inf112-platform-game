package game;

public class Key extends AbstractObject {
	
	public Key(GameState game, int x, int y, String imgFile) {
		 super.setGameState(game);
		 game.addSprite(this);
		 
		 super.setX(x);
		 super.setY(y);
		 
		 super.setWidth(18);
		 super.setHeight(30);
		 
		 super.setSprite(imgFile); 
	}
	
	public Key(GameState game, int x, int y) {
		this(game, x, y, "key.png");

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
	
	//If a player that doesn't already have a key touches the key the player should pick up the key.
	//When a key is picked up it should be removed from the game.
	public boolean checkForPlayer(int playerId) {
		Player player = super.getGameState().getPlayer(playerId);
		if (checkForHit(player)){
			if(player.pickUpKey()) {
				super.getGameState().killSprite(this);
				super.getGameState().playSound("unlink2.ogg");
				return true;
			}
		}
		return false;
		
	}
}
