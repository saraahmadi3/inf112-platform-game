package gameTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.ConflictingGameObjectsException;
import game.GameState;
import game.Platform;
import game.Player;

class PlayerTest {
	
	//ISSUES #13 and #11 
	private static final double V = 100; //Running velocity/speed for player	//later not static (powerups)
	private static final double J = 150; //Jump strength
	public static final double G = 150; //Gravity acceleration
	
	private static GameState game;
	private static Player playerOne;
	private static Player playerTwo;
	
	@BeforeEach
	void setUpBeforeClass() throws NullPointerException {
		game = new GameState(0);
		playerOne = new Player(50, 15, game, "testMode", 1);
		playerTwo = new Player(50, 15, game, "testMode", 2);
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
		
		//Test if players are stored as variables in GameState
		assertEquals(playerOne, game.getPlayer(1));
		playerTwo = new Player(50, 15, game, "testMode", 2);
		game.addSprite(playerTwo);
		game.addAllNewSprites();
		assertEquals(playerTwo, game.getPlayer(2));
	}
	
	@Test
	void playerStaysPutAtSpawn() {
		assertEquals(50, (int) playerOne.getX());
		assertEquals(15, (int) playerOne.getY());
	}
	
// Approach: SPLIT TESTS INTO CATEGORIES. Create a new issue in git for each category.

	//Example:
	//==========TO-DO=========== (Issue #nr)
	//ToDoClass: toDo(), example()
	
	//All issues link to #11 and #13
	
//==========PLATFORM========== (Issue #15)
	//Test the boost platform interaction with player
	@Test
	void boostWorks() {
		
		//TO-DO: test boost() and getGv() together. 
		assertEquals(0, (int) playerOne.getGv());
		
		//Repeated boostValue
		playerOne.boost(1);
		playerOne.boost(1);
		assertEquals(-J*1, playerOne.getGv());
		
		//Boosting in increments of 1 from boost of 0 to 1000
		for (int i=0; i<=1000; i++) {
			playerOne.boost(i);
			assertEquals(-J*i, playerOne.getGv());
		}
		
		playerOne.boost(0);
		playerOne.boost(-1);
		assertEquals(0, (int) playerOne.getGv());
	}
	
//Player - Platform interaction test
	@Test
	void platformInteraction() {
		game.addSprite(playerOne);
		game.addAllNewSprites();

		assertFalse(playerOne.getGrounded());
		assertNull(playerOne.getCurrentPlatform());
		
		Platform floor1 = new Platform(game, -500, -10, 1150, 20, "testMode"); //Floor
		game.addSprite(floor1);
		game.addAllNewSprites();
		
		playerOne.move();
		
		assertEquals(floor1, playerOne.getCurrentPlatform());
		assertTrue(playerOne.getGrounded());
		
		//____SOME EXPECTED EXCEPTIONS____
		//Use of new exception class ConflictingGameObjectsException advised.
		
		//Increasing height of platform to cover player
		assertThrows(ConflictingGameObjectsException.class, () -> {
			floor1.setHeight(floor1.getHeight() + 50);
		}, "ConflictingGameObjectsException was expected. Cannot increase height of platform. Covers player");
		
		//Removing platform under player
		double yBeforeFall = playerOne.getY();
		double xBeforeFall = playerOne.getX();
		
		game.killSprite(floor1);
		game.removeAllDeadSprites();
		playerOne.move();
		assertFalse(playerOne.getY() == yBeforeFall);
		
		playerOne.setX(xBeforeFall);
		playerOne.setY(yBeforeFall);
		
		//Adding platform that covers player
		Platform floor2 = new Platform(game, -500, -10, 1150, 20, "testMode"); //Floor
		game.addSprite(floor2);
		game.addAllNewSprites();
		playerOne.move();
		
		assertThrows(ConflictingGameObjectsException.class, () -> {
			game.addSprite(floor1);
		}, "ConflictingGameObjectsException was expected. Cannot add floor that covers player");
		
	}
	
	
	
//========PLAYER-VALUES========(Issue #16)
	@Test
	void hpStressTest() {
		//Adding playerOne to game. Conditions should apply
		game.addSprite(playerOne);
		game.addAllNewSprites();
		assertEquals(3, playerOne.getLives());
		playerOne.checkForDeath();
		game.removeAllDeadSprites();
		assertTrue(game.getAllSprites().contains(playerOne));
		
		//Player loses all lives. Should be killed and removed.
		playerOne.loseLife();
		assertEquals(2, playerOne.getLives());
		playerOne.loseLife();
		assertEquals(1, playerOne.getLives());
		playerOne.loseLife();
		assertEquals(0, playerOne.getLives());
		game.removeAllDeadSprites();
		assertFalse(game.getAllSprites().contains(playerOne));
		
		//Player falls below map. Checking conditions
		game.addSprite(playerTwo);
		game.addAllNewSprites();
		playerTwo.setY(-playerTwo.getHeight() - 1);
		playerTwo.checkForDeath();
		game.removeAllDeadSprites();
		assertTrue(game.getAllSprites().contains(playerOne));
		assertEquals(2, playerTwo.getLives());
		assertEquals(50, playerTwo.getX());
		assertEquals(15, playerTwo.getY());
		assertEquals(0, (int) playerTwo.getGv());
		
		playerTwo.setXandY(playerTwo.getX() + 123, playerTwo.getY() + 47);
		playerTwo.setY(-playerTwo.getHeight() - 1);
		playerTwo.checkForDeath();
		game.removeAllDeadSprites();
		assertTrue(game.getAllSprites().contains(playerOne));
		assertEquals(1, playerTwo.getLives());
		assertEquals(50, playerTwo.getX());
		assertEquals(15, playerTwo.getY());
		assertEquals(0, (int) playerTwo.getGv());
		
		playerTwo.setY(-playerTwo.getHeight() - 1);
		playerTwo.checkForDeath();
		game.removeAllDeadSprites();
		assertFalse(game.getAllSprites().contains(playerTwo));
	}
	
	
	
//==========REDUNDENCY==========(Issue #17)
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
	
	
	
//=============KEY=============(Issue #18)
	//Player - Key interaction test
	@Test
	void keyInteraction() {
		//TO-DO: haskey(), useKey(), pickUpKey()
	}
	
	

	
}
