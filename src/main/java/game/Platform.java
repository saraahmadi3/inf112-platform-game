package game;
 
public class Platform extends AbstractObject {
	/**
	 * Constructor. Creates a platform based on the GameState, x and y coordinate and its width and height
	 * 		This version sets the original black platform
	 * @param game
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Platform(GameState game, int x, int y, int width, int height) {
		this(game, x, y, width, height, "platform.png");
	}
	
	/**
	 * Constructor. Creates a platform based on the GameState, x and y coordinate, its width and height as well as an image file
	 * @param game
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param imgFile
	 */
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
	@Override
	public String getType() {
		return "Platform";
	}

}
