package gameTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.GameState;
import game.Player;

class PlayerTest {

	private static GameState game;
	private static Player playerOne;
	
	@BeforeEach
	void setUpBeforeClass() throws NullPointerException {
		game = new GameState(0);
		playerOne = new Player(50, 15, game, "testMode", 1);
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
	void playerStaysPutAtSpawn() {
		assertEquals(50, (int) playerOne.getX());
		assertEquals(15, (int) playerOne.getY());
	}
	
	
	
	//==========PLATFORM==========
	//Test the boost platform interaction with player
	@Test
	void boostWorks() {
		//TO-DO: test boost() and getGv() together. Possibly isGrounded()
	}
	
	//Player - Platform interaction test
	@Test
	void platformInteraction() {
		//TO-DO: getCurrentPlatform(), isGrounded()
	}
	
	
	
	//========PLAYER-VALUES========
	@Test
	void hpStressTest() {
		//TO-DO: test getLives(), loseLife(), checkForDeath()
	}
	
	
	
	//==========REDUNDENCY==========
	//One cannot allow two instances of the same player into the game. Solved by identity solution in Player class
	@Test
	void allowsRedundantPlayer() {
		game.addSprite(playerOne);
		game.addSprite(playerOne);
		game.addAllNewSprites();
		assertFalse(Collections.frequency(game.getAllSprites(), playerOne) > 1);
		game.killSprite(playerOne);
		game.removeAllDeadSprites();
		assertTrue(game.getAllSprites().isEmpty());
	}
	
	@Test
	void getIdentityWorks() {
		//TO-DO getIdentity()
	}
	
	
	
	//=============KEY=============
	//Player - Key interaction test
	@Test
	void keyInteraction() {
		//TO-DO: haskey(), useKey(), pickUpKey()
	}
	
	

	
}
