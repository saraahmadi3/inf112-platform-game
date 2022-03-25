package game;

public class MovingPlatform extends Platform {

	private int v;
	private boolean goingUp;
	private boolean goingRight;
	private double startX;
	private double startY;
	private int rangeX;
	private int rangeY;
	/**
	 * Constructor. Creates a moving platform based on the GameState, x and y start coordinate, the platforms width and height,
	 * the x- and y range the platform can move in, the speed the platform moves at, as well as a image file 
	 * @param game
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param xRange
	 * @param yRange
	 * @param speed
	 * @param imgFile
	 */
	public MovingPlatform(GameState game, int x, int y, int width, int height, int xRange, int yRange, int speed, String imgFile) {
		super(game, x, y, width, height, imgFile);
		super.setGameState(game);
		startX=x;
		startY=y;
		v = speed;
		goingUp = true;
		goingRight = true;
		rangeX = xRange;
		rangeY = yRange;
	}
	/**
	 * Constructor. Creates a moving platform based on the GameState, x and y start coordinate, the platforms width and height,
	 * the x- and y range the platform can move in, the speed the platform moves at with the default image file "Platform.png"
	 * @param game
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param xRange
	 * @param yRange
	 * @param speed
	 */
	public MovingPlatform(GameState game, int x, int y, int width, int height, int xRange, int yRange, int speed) {
		this(game, x, y, width, height, xRange, yRange, speed, "Platform.png");
	}
	
	
	@Override 
	/**
	 * moves the platform in accordance with its speed and range. if a player is grounded or collides with the platform, 
	 * it should be moved in accordance to the platforms displacement.
	 */
	public void move() {
		double delta = super.getGameState().getDeltaTime(); //The time passed since last frame
		
		double xMove = 0;
		double yMove = 0;
		
		if (goingRight) {
			if (super.getX() < startX+rangeX) {
				xMove+=delta*v;
			} else {
				goingRight=false;
			}
		}
		if (!goingRight) {
			if (super.getX() > startX) {
				xMove-=delta*v;
			} else {
				goingRight=true;
			}
		}
		
		if (goingUp) {
			if (super.getY() < startY+rangeY) {
				yMove+=delta*v;
			} else {
				goingUp=false;
			}
		}
		if (!goingUp) {
			if (super.getY() > startY) {
				yMove-=delta*v;
			} else {
				goingUp=true;
			}
		}
		
		super.moveByXandY(xMove, yMove);		
		
		if (super.getGameState().getPlayer(1) != null) {
			movePlayer(1, xMove, yMove);
		}
		if (super.getGameState().getPlayer(2) != null) {
			movePlayer(2, xMove, yMove);
		}
	
	}
	
	
	/**
	 * moves a player in accordance to the moving platforms displacement if the player collides with the platform of is 
	 * grounded on the moving platform
	 * @param playerId
	 * @param xMove
	 * @param yMove
	 */
	public void movePlayer(int playerId, double xMove, double yMove) {
		Player player = super.getGameState().getPlayer(playerId);
		
		if ((player.getCurrentPlatform() == this && player.getGrounded()) || super.checkForHit(player)){
			player.moveByXandY(xMove, yMove);
		} else {
			player.moveByXandY(xMove, yMove);
			if (!super.checkForHit(player)) {
				player.moveByXandY(-xMove, -yMove);
			}
		}
	}
	
	/**
	 * 
	 * @return the speed of the platform
	 */
	public int getSpeed() {
		return v;
	}
	
	
	@Override
	public void update() {
		move();
	}
}
