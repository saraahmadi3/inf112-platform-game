package game;

public class MovingPlatform extends Platform {

	private int v;
	private boolean goingUp;
	private boolean goingRight;
	private double startX;
	private double startY;
	private int rangeX;
	private int rangeY;
	
	public MovingPlatform(GameState game, int x, int y, int width, int height, int xRange, int yRange, int speed) {
		super(game, x, y, width, height);
		super.setGameState(game);
		startX=x;
		startY=y;
		v = speed;
		goingUp = true;
		goingRight = true;
		rangeX = xRange;
		rangeY = yRange;
	}
	
	//Player should not move out of range, and should never stop moving from side to side or up and down.
	@Override 
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
		
		movePlayer(1, xMove, yMove);
		if (super.getGameState().getPlayer(2) != null) {
			movePlayer(2, xMove, yMove);
		}
	
	}
	
	//A player that stands on a platform should move with the platform
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

	@Override
	public void update() {
		move();
	}
}
