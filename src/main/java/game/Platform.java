package game;
 
public class Platform extends AbstractObject {
 
	public Platform(GameState game, int x, int y, int width, int height) {
		this(game, x, y, width, height, "platform.png");
	}
	
	public Platform(GameState game, int x, int y, int width, int height, String imgFile) {
		 super.setGameState(game);
		 game.addSprite(this);
		
		 super.setX(x);
		 super.setY(y);
		 
		 super.setWidth(width);
		 super.setHeight(height);
		 
		 super.setSprite(imgFile); 
	}
	
	//This is used to automatically add platforms in the list of platforms in gameState
	public String getType() {
		return "Platform";
	}

}
