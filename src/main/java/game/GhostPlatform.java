package game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GhostPlatform extends Platform {
	
	private GameState game;
	private int state;
	private Sprite ghost0, ghost1, ghost2, ghost3;
	ArrayList<Sprite> ghostSprites;
	private double counter;
	private double prevCounter;
	private double delay;

	public GhostPlatform(GameState game, int x, int y, int width, int height, double delay) {
		super(game, x, y, width, height);
		this.game = game;
		state = 0;
		counter = 0;
		prevCounter = 0;
		this.delay = delay;
		
		ghostSprites = new ArrayList<Sprite>(); 
		
		FileHandle platformFileHandle = Gdx.files.internal("game/img/platform.png"); 
		Texture platformTexture = new Texture(platformFileHandle);
		ghost0 =  new Sprite(platformTexture, width, height);
		ghostSprites.add(ghost0);
		
		platformFileHandle = Gdx.files.internal("game/img/ghost1.png"); 
		platformTexture = new Texture(platformFileHandle);
		ghost1 =  new Sprite(platformTexture, width, height);
		ghostSprites.add(ghost1);
		
		platformFileHandle = Gdx.files.internal("game/img/ghost2.png"); 
		platformTexture = new Texture(platformFileHandle);
		ghost2 =  new Sprite(platformTexture, width, height);
		ghostSprites.add(ghost2);
		
		platformFileHandle = Gdx.files.internal("game/img/ghost3.png"); 
		platformTexture = new Texture(platformFileHandle);
		ghost3 =  new Sprite(platformTexture, width, height);
		ghostSprites.add(ghost3); 
		
	}
	
	@Override
	public void update() {
		if (game.getPlayer1().getCurrentPlatform()==this || state > 3) {
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
	public void draw(SpriteBatch batch, BitmapFont font) {
		if (state <4) {
			batch.draw(ghostSprites.get(state), (float) getX(), (float) getY());
		}
	}
	
	@Override
	public boolean checkForHit(Player player) {
		if (state<4) {
			boolean checkForLeftXOverlap = player.getX() < getX() && (player.getX()+player.getWidth()>getX());
			boolean checkForRightXOverlap = player.getX() < getX()+getWidth() && (player.getX()+player.getWidth()>getX()+getWidth());
			boolean checkForMidXOverlap = player.getX() >= getX() && player.getX()+player.getWidth()<=getX()+getWidth();
			boolean checkForTopYOverlap = player.getY() < getY()+getHeight() && (player.getY()+player.getHeight()>getY()+getHeight());
			boolean checkForBottomYOverlap = player.getY() < getY() && (player.getY()+player.getHeight()>getY());
			boolean checkForMidYOverlap = player.getY() >= getY() && player.getY()+player.getHeight()<=getY()+getHeight();
			
			if ((checkForLeftXOverlap || checkForRightXOverlap || checkForMidXOverlap) && (checkForTopYOverlap || checkForBottomYOverlap || checkForMidYOverlap)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
