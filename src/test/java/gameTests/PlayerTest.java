package gameTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import gameObjects.*;
import game.GameState;


class PlayerTest {
	
	//ISSUES #13 and #11 
	private static final double V = 100; //Running velocity/speed for player	//later not static (powerups)
	private static final double J = 150; //Jump strength
	public static final double G = 150; //Gravity acceleration
	
	private static GameState game;
	private static Player playerOne;
	private static Player playerTwo;
	
	@BeforeAll
	static void setUpBeforeClass() throws NullPointerException {
		game = new GameState(0);
		playerOne = new Player(50, 15, game, null, 1);
		playerTwo = new Player(50, 15, game, null, 2);
	}

	@AfterEach
	void tearDown() {
		ArrayList<GameObjects> sprites = game.getAllSprites();
		for (int i=0; i<sprites.size(); i++) {
			game.killSprite(sprites.get(i));
		}
		game.removeAllDeadSprites();

		playerOne.setXandY(50, 15);
		playerTwo.setXandY(50, 15);
		playerOne.boost(0);
		playerTwo.boost(0);
	}
	
	//Level 0 does not exist. Should be an "blank slate" environment
	@Test
	void testLevel0HasNoObjects() {
		assertTrue(game.getAllSprites().isEmpty());
	}
	
	//Player specific
	@Test
	void testAddPlayer() {
		assertEquals("Player", playerOne.getType());
		game.addSprite(playerOne);
		assertTrue(game.getAllSprites().isEmpty());
		
		game.addAllNewSprites();
		assertEquals("Player", game.getAllSprites().get(0).getType());
		
		//Test if players are stored as variables in GameState
		assertEquals(playerOne, game.getPlayer(1));
		playerTwo = new Player(50, 15, game, null, 2);
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
	//==========TO-DO=========== (Issue #nr)[OPEN / CLOSED]
	//ToDoClass: toDo(), example()
	
	//All issues link to #11 and #13
	
//==========PLATFORM========== (Issue #15)[CLOSED]
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
		assertEquals(0, (int) playerOne.getGv());
		
		playerOne.boost(-1);
		assertTrue(playerOne.getGv()>0);
		
		playerOne.boost(1);
		assertTrue(playerOne.getGv()<0);
	}
	
//Player - Platform interaction test
	@Test
	void platformInteraction() {
		game.addSprite(playerOne);
		game.addAllNewSprites();

		assertFalse(playerOne.getGrounded());
		assertNull(playerOne.getCurrentPlatform());
		
		Platform floor1 = new Platform(game, -500, -10, 1150, 20, null); //Floor
		game.addSprite(floor1);
		game.addAllNewSprites();
		
		//Assuming 60 fps, running move() 6000 times is equivalent to letting the player fall for 100 seconds. 
		
		int frameCount = 1;
		while(!(playerOne.getGrounded() && floor1.equals(playerOne.getCurrentPlatform()))) {
			if (frameCount > 6000) {
				fail("The player never hits the platform");
			}
			playerOne.move();
			frameCount ++;
		}
		
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
		Platform floor2 = new Platform(game, -500, -10, 1150, 20, null); //Floor
		game.addSprite(floor2);
		game.addAllNewSprites();
		playerOne.move();
		
	}
	
	
	
//========PLAYER-VALUES========(Issue #16)[CLOSED]
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
		assertTrue(game.getAllSprites().contains(playerTwo));
		assertEquals(2, playerTwo.getLives());
		assertEquals(50, playerTwo.getX());
		assertEquals(15, playerTwo.getY());
		assertEquals(0, (int) playerTwo.getGv());
		
		playerTwo.setXandY(playerTwo.getX() + 123, playerTwo.getY() + 47);
		playerTwo.setY(-playerTwo.getHeight() - 1);
		playerTwo.checkForDeath();
		game.removeAllDeadSprites();
		assertTrue(game.getAllSprites().contains(playerTwo));
		assertEquals(1, playerTwo.getLives());
		assertEquals(50, playerTwo.getX());
		assertEquals(15, playerTwo.getY());
		assertEquals(0, (int) playerTwo.getGv());
		
		playerTwo.setY(-playerTwo.getHeight() - 1);
		playerTwo.checkForDeath();
		game.removeAllDeadSprites();
		assertFalse(game.getAllSprites().contains(playerTwo));
	}
	
	
	
//==========REDUNDENCY==========(Issue #17)[CLOSED]
	//One cannot allow two instances of the same player into the game. Solved by identity solution in Player class
	@Test
	void allowsRedundantPlayer() {
		game.addSprite(playerOne);
		game.addSprite(playerOne);
		game.addSprite(playerTwo);
		game.addAllNewSprites();
		assertFalse(Collections.frequency(game.getAllSprites(), playerOne) > 1);
		game.addSprite(playerTwo);
		game.addAllNewSprites();
		assertFalse(Collections.frequency(game.getAllSprites(), playerTwo) > 1);
		game.killSprite(playerTwo);
		game.killSprite(playerOne);
		game.removeAllDeadSprites();
		assertTrue(game.getAllSprites().isEmpty());
	}
	
	private void addToGame(List<Player> players) {
		for (Player player : players) {
			game.addSprite(player);
		}
		game.addAllNewSprites();
	}
	
	private void killIfPresent(Player player) {
		if(game.getAllSprites().contains(player)) {
			game.killSprite(player);
			game.removeAllDeadSprites();
		}
	}
	
	@Test
	void getIdentityWorks() {
		
		assertEquals(game.getPlayer(1).getIdentity(), playerOne.getIdentity());
		assertEquals(game.getPlayer(2).getIdentity(), playerTwo.getIdentity());
	}
	
	
	
//=============KEY=============(Issue #18)[CLOSED]
	//In hindsight after creating test:
		//One can manipulate player hasKey() without 
		//the player colliding with a key. Same goes for boost.
		//problem? Should be abstracted further? Maybe
	//Player - Key interaction test
	@Test
	void keyInteraction() {
		//Player initially has no key
		assertFalse(playerOne.hasKey());
		
		//Picking up key should be successful
		assertTrue(playerOne.pickUpKey());
		
		//Player should have key after successful
		assertTrue(playerOne.hasKey());
		
		//Player cannot pick up another key. He already has one.
		assertFalse(playerOne.pickUpKey());
		
		//Player uses key
		playerOne.useKey();
		
		//Should have no key
		assertFalse(playerOne.hasKey());
	}
	
	

	
}
