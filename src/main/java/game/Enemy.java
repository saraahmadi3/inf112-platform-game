package game;



public class Enemy extends AbstractObject {
	

	private double speed = 20;
	private boolean goingRight;
	private Platform platform;
	private double platformRelation;
	private double step;
	private double relativeDistance;
	
	
	
	public Enemy(GameState game, Platform platform, String imgFile) {
		this.platform = platform;
		super.setGameState(game);
		game.addSprite(this);
		
		super.setX(platform.getXMid());
		super.setY(platform.getY()+platform.getHeight());
		
		relativeDistance = platform.getWidth()/2;
		
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
//		if(checkForHit(null))

	}
	
	public void move() {
		double delta = super.getGameState().getDeltaTime();
		
		super.setY(platform.getY()+platform.getHeight());
		
		if(goingRight) {
			if(super.getX()+super.getWidth() > platform.getX()+platform.getWidth()) {
				goingRight = false;
			} else {
				step = delta*speed;
			}
		}
		
		if(!goingRight) {
			if(super.getX() < platform.getX()) {
				goingRight = true;
			} else {
				step = -delta*speed;
			}
		}
		
		relativeDistance += step;
		super.setX(relativeDistance + platform.getX());
		
		
		if (super.getGameState().getPlayer(1) != null && super.getGameState().playerIsAlive(super.getGameState().getPlayer(1))) {
			playerInteraction(1);
		}
		if (super.getGameState().getPlayer(2) != null && super.getGameState().playerIsAlive(super.getGameState().getPlayer(2))) {
			playerInteraction(2);
		}
	}
	
	public void playerInteraction(int playerID) {
		Player player= super.getGameState().getPlayer(playerID);
		
		if(super.checkForHit(player)) {
			if (super.checkForHit(player) && player.getY()> super.getYMid()) {
				player.boost(0.5);
				player.changeScoreBy(10);
				super.getGameState().killSprite(this);
				super.getGameState().playSound("landing.ogg");
			} else if (((playerID == 1) && (!super.getGameState().isClient())) || ((playerID == 2) && (!super.getGameState().isServer()))){
				player.loseLife();
			}
		}
	}
}
