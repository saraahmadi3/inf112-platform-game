package tests_oblig1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import game.GameLoop;
import game.GameObjects;
import game.GameState;
import game.Player;

class PlayerTest {
	
	private static final double V = 100; //Running velocity/speed for player	//later not static (powerups)
	private static final double J = 150; //Jump strength
	private static final double G = 150; //Gravity acceleration
	private static final double FLOOR = 12.5; //The Y-coordinate at which there is a floor
	
	static GameLoop loop = new GameLoop();
	static GameState state;
	static Player playerOne;
	
	@BeforeAll
	static void setUpBeforeClass() throws NullPointerException {
		
		Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
		cfg.setTitle("PlayerTest");
		cfg.setWindowedMode(1080, 720);
		
		new Lwjgl3Application(loop, cfg);
		
		//Probably can't test much without running the GameLoop which is what makes everything happen. 
		//Starting the GameLoop would probably look something like the code below, but it might be better to find a way
		//to start a fresh GameLoop without a window and without the level designs. Perhaps finding a way to call render() from 
		//GameLoop manually would be a good solution, since we wouldn't have to deal with the Lwjgl3 stuff more than necessary.
		state = loop.getGame();
		ArrayList<GameObjects> sprites = state.getAllSprites();
		
		for (GameObjects sprite : sprites) {
			state.killSprite(sprite);
		}
		
		try {
			playerOne = new Player(10, (int) FLOOR, state);
		} catch (NullPointerException e) {
			// TODO: handle exception
			
		}
		
		state.addSprite(playerOne);
		
		
		
		
	}

	@Test
	void playerSpawStaysFloor() throws InterruptedException {
		assertEquals((int) FLOOR, playerOne.getY());
	}
	
	@Test
	void playerOnlySprite() throws InterruptedException {
		assertEquals(1, state.getAllSprites().size());
	}
	
	@Test 
	void playerAddedIsPlayer1() {
		assertEquals(playerOne, state.getPlayer1());
	}

}
