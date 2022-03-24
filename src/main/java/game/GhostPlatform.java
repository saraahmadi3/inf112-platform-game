package game;

public class GhostPlatform extends Platform {
	
	private int state;
	private double counter;
	private double prevCounter;
	private double delay;

	/**
	 * Constructor. Creates a ghost platform based on the GameState, x and y coordinate, its width and height and a delay.
	 * the ghost platform disappears after a player has landed on it. the time it takes before the platform disappears is dictated 
	 * by the delay parameter
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param delay
	 */
	public GhostPlatform(GameState game, int x, int y, int width, int height, double delay) {
		super(game, x, y, width, height, null);
		super.setGameState(game);
		state = 0;
		counter = 0;
		prevCounter = 0;
		this.delay = delay;	
	}
	
	/**
	 * checks the number of players in the the current gameState then updates the sprite's stats as the game renders. 
	 */
	public void update() {
		if (super.getGameState().getPlayer(1) != null) {
			checkForPlayer(1);
		}
		if (super.getGameState().getPlayer(2) != null) {
			checkForPlayer(2);
		}
	}
	
	//Should start to disappear if any player touches the platform
	//When it is gone the player can pass through
	//Then it should come back
	/**
	 * checks if a player lands on the platform. if so, then the platform starts disappearing.
	 * this changes the state of the platform. if the state is from 0 to 3 then the platform is still an interactive object in the game
	 * if the state is above 4 and a certain amount of time has passed the platforms field variables will reset and the platform will appear again
	 * 
	 * @param playerId
	 */
	public void checkForPlayer(int playerId) {
		if (super.getGameState().getPlayer(playerId).getCurrentPlatform()==this || state > 3) {
			double delta = super.getGameState().getDeltaTime(); //The time passed since last frame;
			counter += delta;
			if (counter>(prevCounter+(delay/4))){
				state++;
				if (state > 4) {
					if (counter>(prevCounter+delay)) {
						state = 0;
						prevCounter = 0;
						counter = 0;
					}
				} else {
					prevCounter = counter;
				}
			}
		}
	}
	
	/**
	 * draws the platform's sprite onto the board based on it's current state
	 */
	public void draw() {
		if (state <4) {
			if (state == 0) {
				super.setSprite("platform.png");
			} else {
				super.setSprite("ghost"+state+".png");
			}
			super.draw();
		}
	}
	
	@Override
	/**
	 * checks if the platform overlaps with a player if the platform is still interactive (state<4) 
	 */
	public boolean checkForHit(Player player) {
		if (state<4) {
			return super.checkForHit(player);
		}
		else {
			return false;
		}
	}
}
