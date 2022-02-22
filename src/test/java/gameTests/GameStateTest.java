package gameTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import game.AbstractObject;
import game.GameState;
import game.Player;

class GameStateTest {

	private static GameState game;
	private static Player playerOne;
	
	@BeforeAll
	static void setUpBeforeClass() throws NullPointerException {
		game = new GameState(0);
		playerOne = new Player(50, 15, game, "testMode");
	}
	
	//Level 0 does not exist. Should be an empty game environment
	@Test
	void testLevel0HasNoObjects() {
		assertTrue(game.getAllSprites().isEmpty());
	}
	
	@Test
	void testAddPlayerToWaitList() {
		game.addSprite(playerOne);
		assertTrue(game.getAllSprites().isEmpty());
	}

}
