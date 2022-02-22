package game;

import com.badlogic.gdx.Gdx;

public class GhostPlatform extends Platform {
	
	private int state;
	private double counter;
	private double prevCounter;
	private double delay;

	public GhostPlatform(GameState game, int x, int y, int width, int height, double delay) {
		super(game, x, y, width, height);
		super.setGameState(game);
		state = 0;
		counter = 0;
		prevCounter = 0;
		this.delay = delay;

		
	}
	
	@Override
	public void update() {
		if (super.getGameState().getPlayer1().getCurrentPlatform()==this || state > 3) {
			double delta = Gdx.graphics.getDeltaTime(); //The time passed since last frame;
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
	
	@Override
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
	public boolean checkForHit(Player player) {
		if (state<4) {
			return super.checkForHit(player);
		}
		else {
			return false;
		}
	}
}
