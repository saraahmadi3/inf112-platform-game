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
	
	private static Player playerOne;
	private static GameState state;
	
	@BeforeAll
	static void setUpBeforeClass() throws NullPointerException {
		
		//Probably can't test much without running the GameLoop which is what makes everything happen. 
		//Starting the GameLoop would probably look something like the code below, but it might be better to find a way
		//to start a fresh GameLoop without a window and without the level designs. Perhaps finding a way to call render() from 
		//GameLoop manually would be a good solution, since we wouldn't have to deal with the Lwjgl3 stuff more than necessary.
		/*
		Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
		cfg.setWindowedMode(1080, 720);
		new Lwjgl3Application(new GameLoop(), cfg);
		*/
		
		playerOne = new Player(10, (int) FLOOR, state);
		state = new GameState();
		
		int spritecount = state.getAllSprites().size();
		for (int i=0; i<spritecount; i++) {
			GameObjects current = state.getAllSprites().get(0);
			state.killSprite(current);
			
		state.addSprite(playerOne);
		}
		
	}

	@Test
	void playerSpawStaysFloor() throws InterruptedException {
		Thread.sleep(1000);
		assertEquals((int) FLOOR, state.getAllSprites().get(0).getY());
	}

}
