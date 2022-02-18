package game;

import com.badlogic.gdx.Gdx;

public class movingPlatform extends Platform {

	private int v;
	private boolean goingUp;
	private boolean goingRight;
	private double startX;
	private double startY;
	private int rangeX;
	private int rangeY;
	private GameState game;
	
	public movingPlatform(GameState game, int x, int y, int width, int height, int xRange, int yRange, int speed) {
		super(game, x, y, width, height);
		startX=x;
		startY=y;
		v = speed;
		goingUp = true;
		goingRight = true;
		rangeX = xRange;
		rangeY = yRange;
		this.game = game;
		
	}
	
	@Override 
	public void move() {
		double delta = Gdx.graphics.getDeltaTime(); //The time passed since last frame
		
		double xMove = 0;
		double yMove = 0;
		
		if (goingRight) {
			if (super.x < startX+rangeX) {
				xMove+=delta*v;
			} else {
				goingRight=false;
			}
		}
		if (!goingRight) {
			if (super.x > startX) {
				xMove-=delta*v;
			} else {
				goingRight=true;
			}
		}
		
		if (goingUp) {
			if (super.y < startY+rangeY) {
				yMove+=delta*v;
			} else {
				goingUp=false;
			}
		}
		if (!goingUp) {
			if (super.y > startY) {
				yMove-=delta*v;
			} else {
				goingUp=true;
			}
		}
		
		super.x+=xMove;
		super.y+=yMove;
		
		
		Player player1 = game.getPlayer1();
		
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
