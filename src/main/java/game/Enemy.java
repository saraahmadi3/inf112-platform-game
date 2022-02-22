package game;



public class Enemy extends AbstractObject {
	

	private double speed = 10;
	private boolean goingRight;
	private Platform platform;
	
	
	
	public Enemy(GameState game, Platform platform, String imgFile) {
		this.platform = platform;
		super.setGameState(game);
		game.addSprite(this);
		
		super.setX(platform.getXMid());
		super.setY(platform.getY()+platform.getHeight());
		goingRight=true;
		
		super.setHeight(16);
		super.setWidth(16);
		super.setSprite(imgFile);
	}

	public Enemy(GameState game, Platform platform) {
		this (game, platform, "ene.png");
	}
	
	public void update() {
		move();
//		if() collision med player enten killsprite/gameover
	}
	
	public void move() {
		double delta = super.getGameState().getDeltaTime();
		
		if(goingRight) {
			if(super.getX()+super.getWidth() > platform.getX()+platform.getWidth()) {
				goingRight = false;
			} else {
				moveByX(delta*speed);
				super.setY(platform.getY()+platform.getHeight());
			}
		}
		
		if(!goingRight) {
			if(super.getX() < platform.getX()) {
				goingRight = true;
			} else {
				moveByX(-delta*speed);
				super.setY(platform.getY()+platform.getHeight());
			}
		}
			
		
	}
	
}
