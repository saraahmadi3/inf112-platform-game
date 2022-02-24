package game;



public class Enemy extends AbstractObject {
	

	private double speed = 20;
	private boolean goingRight;
	private Platform platform;
	private double platformRelation;
	private double step;
//	private double startX;
	
	
	
	public Enemy(GameState game, Platform platform, String imgFile) {
		this.platform = platform;
		super.setGameState(game);
		game.addSprite(this);
		
		super.setX(platform.getXMid());
		super.setY(platform.getY()+platform.getHeight());
		
		goingRight=true;
//		startX=platform.getXMid();
		super.setHeight(16);
		super.setWidth(16);
		super.setSprite(imgFile);
		
	}

	public Enemy(GameState game, Platform platform) {
		this (game, platform, "ene.png");
	}
	
	public void update() {
		updateStep();
		updatePlatformRelation();
		
		move();
//		if(checkForHit(null))

	}
	public void updatePlatformRelation() {

		platformRelation = (super.getX()-platform.getX())/(platform.getWidth());
		if(platformRelation< 0.00) 
			platformRelation = 0.00;
		if(platformRelation>1.00) 
			platformRelation = 1.00;
		
	}
	
	public void updateStep() {
		step= super.getGameState().getDeltaTime()*speed;
		
	}
	
	public void move() {
//		double delta = super.getGameState().getDeltaTime();
		
		
		
		if(goingRight) {
			if(super.getX()+super.getWidth() > platform.getX()+platform.getWidth()) {
				goingRight = false;
			} else {
//				moveByX(delta*speed);
				super.setX(platform.getX() + (platform.getWidth()* (platformRelation)) + step);
				super.setY(platform.getY()+platform.getHeight());
			}
		}
		
		if(!goingRight) {
			if(super.getX() < platform.getX()) {
				goingRight = true;
			} else {
//				moveByX(-delta*speed);
				super.setX(platform.getX() + (platform.getWidth()* platformRelation) -step);
				super.setY(platform.getY()+platform.getHeight());
			}
		}
			
		
	}
	
}
