package gameTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.AbstractObject;
import game.GameState;
import game.Player;

class GameStateTest {

	private static GameState game;
	private static Player playerOne;
	
	@BeforeEach
	void setUpBeforeClass() throws NullPointerException {
		game = new GameState(0);
		playerOne = new Player(50, 15, game, "testMode");
	}
	
	//Level 0 does not exist. Should be an "blank slate" environment
	@Test
	void testLevel0HasNoObjects() {
		assertTrue(game.getAllSprites().isEmpty());
	}
	
	//Player specific
	@Test
	void testAddPlayer() {
		game.addSprite(playerOne);
		assertTrue(game.getAllSprites().isEmpty());
		
		game.addAllNewSprites();
		assertEquals(playerOne, game.getAllSprites().get(0));
	}
	
	@Test
	void allowsRedundantPlayer() {
		game.addSpriteQ(playerOne);
		game.addSpriteQ(playerOne);
		assertFalse(Collections.frequency(game.getAllSprites(), playerOne) > 1);
	}

}
