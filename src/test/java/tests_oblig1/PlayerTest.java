package tests_oblig1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
