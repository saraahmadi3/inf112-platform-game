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
		
		Player player1 = super.getGameState().getPlayer1();
		
		if ((player1.getCurrentPlatform() == this && player1.getGrounded()) || super.checkForHit(player1)){
			player1.moveByXandY(xMove, yMove);
		} else {
			player1.moveByXandY(xMove, yMove);
			if (!super.checkForHit(player1)) {
				player1.moveByXandY(-xMove, -yMove);
			}
		}
	
		
		
	}

	@Override
	public void update() {
		move();
	}
}
