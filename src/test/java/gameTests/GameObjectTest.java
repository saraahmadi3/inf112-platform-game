package gameTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.JUnitException;

import exceptions.InvalidPlatformException;
import game.BoostPlatform;
import game.Door;
import game.Enemy;
import game.GameObjects;
import game.GameState;
import game.GhostPlatform;
import game.Key;
import game.MovingPlatform;
import game.Platform;
import game.Player;
import game.Text;
import game.Tips;

/**
 * @author adria
 * Tests to confirm that AbstractObjects' methods are working as intended
 */


// ISSUE #13 overall



//For this class (AbstractObject) these methods can be tested using any class 
//        that extends AbstractObject (without the methods being overwritten):

//checkForHits(), setGameState(), getGameState(), getSymbol(), getType(), getX(), 
//getY(), getXMid(), getYMid(), setX(), setY(), setXAndY(), moveByX(), moveByY(), 
//moveByXandY(), getWidth(), getHeight(), setWidth(), setHeight()

//Many of the tests in this testClass rely on simulating an environment
//in which we cannot test. Libgdx causes a lot of problems in Junit.
//One might see some problems occur simulating update() for each object 
//in question through a number of seconds in 60 fps.
//The benefit of this technique is that we can simulate the game faster 
//than real time.
class GameObjectTest {
	
	private static GameState game;
	private static Player playerOne;
	
	//Initialise a new game and a player
	@BeforeAll
	static void setUp() {
		game = new GameState(0);
		playerOne = new Player(50, 15, game, null, 1);
		
	} 
	
	//This resets game and playerOne after each test
	@AfterEach
	void tearDown() {
		ArrayList<GameObjects> sprites = game.getAllSprites();
		for (int i=0; i < sprites.size(); i++) {
			game.killSprite(sprites.get(i));
		}
		game.removeAllDeadSprites();
		
		playerOne.setXandY(50, 15);
		playerOne.boost(0);
	}
	
	//Simulate a n-times seconds drop to platform by player in 60 frames per second
	//Checks for hit until the platform registers a player.
	private boolean causeCollision(int seconds, Player player, Platform platform) {
		int totalFrames = seconds * 60;
		int frameCount = 1;
		boolean hasCollided = false;
		while(!hasCollided) {
			if (frameCount > totalFrames) {
				return false;
			}
			playerOne.move();
			hasCollided = platform.checkForHit(player);
			frameCount++;
		}
		return true;
	}
	
// Approach: SPLIT TESTS INTO CATEGORIES. Create a new issue in git for each category.

	//Example:
	//==========TO-DO=========== (Issue #nr)[OPEN / CLOSED]
	//ToDoClass: toDo(), example()
	
		//All issues link to #13
	
	
//=========PLATFORMS========(Issue #19)[CLOSED]
	@Test 
	void boostPlatformTest() {
		System.out.println(playerOne.getX() + "," + playerOne.getY());
		double negativeBoostFactor = -1;
		final double J = 150;
		double boostFactor = 2;
		BoostPlatform boostP = new BoostPlatform(game, -500, -10, 1150, 20, boostFactor, null); 

		System.out.println(playerOne.getGv());
		assertTrue(0 == playerOne.getGv());
		assertEquals("Platform", boostP.getType());
		game.addSprite(boostP);
		game.addAllNewSprites();
		assertEquals(1, game.getAllSprites().size());
		assertEquals("Platform", game.getAllSprites().get(0).getType());
		assertEquals("Platform", game.getAllPlatforms().get(0).getType());
		assertEquals(0, game.getAllPlayers().size());
		
		game.addSprite(playerOne);
		game.addAllNewSprites();
		
		assertEquals("Platform", boostP.getType());
		
		//SIMULATE 6000 frames (100 seconds) until collision occurs
		//Drop player onto platform
		if (!causeCollision(100, playerOne, boostP)) {
			fail("The player never hits the platform");
		}
			
		//PLAYER 1 IS NOW COLLIDING WITH BOOSTP
		
		//checkForBoost() in BoostPlatform
		boostP.checkForBoost(1);
		assertEquals(-J*boostFactor, playerOne.getGv());
		
		//update() in BoostPlatform
		playerOne.boost(0);
		boostP.update();
		assertEquals(-J*boostFactor, playerOne.getGv());
		
		game.killSprite(playerOne);
		game.removeAllDeadSprites();
		
		game.killSprite(boostP);
		game.removeAllDeadSprites();
		

		BoostPlatform negativeBoostP = new BoostPlatform(game, -500, -10, 1150, 20, negativeBoostFactor, null); 
		assertThrows(InvalidPlatformException.class, () -> {
			game.addSprite(negativeBoostP);
		}, "InvalidPlatformException expected. Cannot add BoostPlatform with negative boost.");
		
		game.addAllNewSprites();
		assertFalse(game.getAllPlatforms().contains(negativeBoostP));
		if(game.getAllPlatforms().contains(negativeBoostP)) {
			game.killSprite(negativeBoostP);
			game.removeAllDeadSprites();
		}
		
		assertThrows(InvalidPlatformException.class, () -> {
			game.addSpriteQ(negativeBoostP);
		}, "InvalidPlatformException expected. Cannot add BoostPlatform with negative boost.");
		
		assertFalse(game.getAllPlatforms().contains(negativeBoostP));
	}
	
	@Test
	void MovingPlatformTest() {
		System.out.println(playerOne.getX() + "," + playerOne.getY());
		MovingPlatform horizontalMovingP = new MovingPlatform(game, -500, -10, 1150, 20, 250, 0, 50, null);
		MovingPlatform verticalMovingP = new MovingPlatform(game, -500, -10, 1150, 20, 0, 250, 50, null);
		
		assertEquals("Platform", horizontalMovingP.getType());
		game.addSprite(horizontalMovingP);
		game.addAllNewSprites();
		assertEquals("Platform", game.getAllPlatforms().get(0).getType());
		
		double xStart = horizontalMovingP.getX();
		double yStart = horizontalMovingP.getY();
		double xPrevious = horizontalMovingP.getX();
		double yPrevious = horizontalMovingP.getY();
		int frameCount = 0;
		boolean hasComeBack = false;
		
		//Horizontal moving platform test on x axis
		while(!hasComeBack) {
			horizontalMovingP.update();
			assertTrue(horizontalMovingP.getY() == yStart);
			//Updating two frames. Simulates 100 seconds in 60 fps
			if (frameCount > 6000) {
				fail("The platform never returns");
				break;
			}
			else if (xPrevious == horizontalMovingP.getX()) {
				fail("The platform does not move");
				break;
			}
			hasComeBack = (xStart == horizontalMovingP.getX());
			frameCount ++;
			xPrevious = horizontalMovingP.getX();
			yPrevious = horizontalMovingP.getY();
		}
		horizontalMovingP.setXandY(-500, -10);
		game.killSprite(horizontalMovingP);
		game.removeAllDeadSprites();
		
		game.addSprite(verticalMovingP);
		game.addAllNewSprites();
		frameCount = 0;
		hasComeBack = false;
		xStart = verticalMovingP.getX();
		yStart = verticalMovingP.getY();
		yPrevious = verticalMovingP.getY();
		
		//Vertical moving platform test on y axis
		while(!hasComeBack) {
			verticalMovingP.update();
			assertTrue(verticalMovingP.getX() == xStart);
			//Updating two frames. Simulates 100 seconds in 60 fps
			if (frameCount > 6000) {
				fail("The platform never returns");
				break;
			}
			else if (yPrevious == verticalMovingP.getY()) {
				fail("The platform does not move");
				break;
			}
			hasComeBack = (yStart == verticalMovingP.getY());
			frameCount ++;
			xPrevious = verticalMovingP.getX();
			yPrevious = verticalMovingP.getY();
		}
		verticalMovingP.setXandY(-500, -10);
		game.killSprite(verticalMovingP);
		game.removeAllDeadSprites();
		
		//Test moving player
		game.addSprite(playerOne);
		game.addSprite(horizontalMovingP);
		game.addAllNewSprites();
		
		//causeCollision does not update the platform. It should stay put under the player
		if (!causeCollision(100, playerOne, horizontalMovingP)) {
			fail("The player never hits the platform");
		}
		
		//We have caused collision. Now we can check movePlayer() just by using move()
		xPrevious = horizontalMovingP.getX();
		yPrevious = horizontalMovingP.getY();
		double previousPlayerX = playerOne.getX();
		double previousPlayerY = playerOne.getY();
		
		//Testing player movement on horizontal moving platform
		for (int i=0; i<=6000; i++) {
			horizontalMovingP.update();
			assertEquals(playerOne.getY(), previousPlayerY);
			if (playerOne.getX() == previousPlayerX) {
				fail("The player is not moving");
				break;
			}
			else if (horizontalMovingP.getX() > xPrevious) {
				if (!(playerOne.getX() > previousPlayerX)) {
					fail("The player does not move in the same positive direction as the platform");
				}
			}
			else if (horizontalMovingP.getX() < xPrevious) {
				if (!(playerOne.getX() < previousPlayerX)) {
					fail("The player does not move in the same negative direction as the platform");
				}
			}
			xPrevious = horizontalMovingP.getX();
			yPrevious = horizontalMovingP.getY();
			previousPlayerX = playerOne.getX();
			previousPlayerY = playerOne.getY();
		}
		game.killSprite(horizontalMovingP);
		game.removeAllDeadSprites();
		
		game.addSprite(verticalMovingP);
		game.addAllNewSprites();
		
		playerOne.setXandY(50,  15);
		causeCollision(100, playerOne, verticalMovingP);
		
		for (int i=0; i<=6000; i++) {
			verticalMovingP.update();
			assertEquals(playerOne.getX(), previousPlayerX);
			if (playerOne.getY() == previousPlayerY) {
				fail("The player is not moving");
				break;
			}
			else if (verticalMovingP.getY() > yPrevious) {
				if (!(playerOne.getY() > previousPlayerY)) {
					fail("The player does not move in the same positive direction as platform");
				}
			}
			else if (verticalMovingP.getY() < yPrevious) {
				if (!(playerOne.getY() < previousPlayerY)) {
					fail("The player does not move in the same negative direction as platform");
				}
			}
			xPrevious = verticalMovingP.getX();
			yPrevious = verticalMovingP.getY();
			previousPlayerX = playerOne.getX();
			previousPlayerY = playerOne.getY();
		}
	}
	
//GhostPlatform: update(), checkForPlayer(), checkForHit() through causeCollision()
	//Need to test fall when player stands too long on GhostPlatform
	//Need to find conditions for player to pass through. Check for negative delta Y.
	@Test 
	void GhostPlatformTest() {
		//PlayerOne at x=50, y=15
		double ghostDelay = 2;
		System.out.println(playerOne.getX() + "," + playerOne.getY());
		GhostPlatform ghostP = new GhostPlatform(game, -500, -10, 1150, 20, ghostDelay);
		game.addSprite(ghostP);
		game.addSprite(playerOne);
		game.addAllNewSprites();
		
		if (!causeCollision(100, playerOne, ghostP)) {
			fail("The player does not collide with ghost platform");
		}
		
		boolean hasFallen = false;
		int frameCount = 0;
		double previousY = playerOne.getY();
		while (!hasFallen) {
			if (frameCount > 6000) {
				fail("The player does not fall after 100 seconds of standing on ghost platform.");
				break;
			}
			ghostP.update();
			playerOne.move();
			hasFallen = (previousY > playerOne.getY());
			frameCount ++;
		}
	}
	
	//=======DOOR-and-KEY========(Issue #20)[CLOSED]
	@Test
	void doorKeyTest() {
		//PlayerOne at x = 50, y = 15
		Platform regularP = new Platform(game, -500, -10, 1150, 20, null);
		Door door = new Door(game, 50, 15, 20, 34, null);
		Key key = new Key(game, 50, 15, null);
		assertFalse(playerOne.hasKey());
		game.addSprite(regularP);
		game.addSprite(key);
		game.addSprite(playerOne);
		game.addAllNewSprites();
		
		//Settle playerOne on a platform
		if (!causeCollision(100, playerOne, regularP)) {
			fail("The player does not hit the platform");
		}
		
		//Move key until player collision occurs
		int moveCount = 1;
		boolean hasHitPlayer = false;
		while (!hasHitPlayer) {
			//Move downward towards player settled on platform
			key.moveByY(-(0.1));
			key.update();
			hasHitPlayer = playerOne.hasKey();
			
			if (moveCount == 10000) {
				fail("The key does not interract with player");
			}
		}
		//Player should now have the key
		
		moveCount = 1;
		boolean hasOpenedDoor = false;
		while(!hasOpenedDoor) {
			door.moveByY(-(0.1));
			door.update();
			
			hasOpenedDoor = !playerOne.hasKey();
			
			if(moveCount == 10000) {
				fail("The door does not interract with player");
			}
		}
		//The player should have used the key while interacting with door
	}

	
//=======TIPS-and-TEXT========(Issue #21)[CLOSED]
//Tips: move()
//Text: getSymbol()
	@Test 
	void tipAndTextTest() {
		Text text = new Text(game, 30, 200, "This is a regular text at x=30 and y=200");
		Tips tip = new Tips(game);
		game.addSprite(text);
		game.addSprite(tip);
		
		double startX = tip.getX();
		for (int i=0; i<600; i++) {
			tip.update();
			assertFalse( startX == tip.getX());
			startX = tip.getX();
		}
		assertEquals("This is a regular text at x=30 and y=200", text.getSymbol());
		text.update();
		assertEquals(30, text.getX());
		assertEquals(200, text.getY());
	}
	
//===========ENEMY===========(Issue #22)[OPEN]
//Enemy: update(), move()
	@Test
	void enemyTest() {
		double ghostDelay = 2;
		double boostFactor = 2;
		Platform regularP = new Platform(game, -500, -10, 1150, 20, null);
		GhostPlatform ghostP = new GhostPlatform(game, -500, -10, 1150, 20, ghostDelay);
		MovingPlatform movingP = new MovingPlatform(game, -500, -10, 1150, 20, 250, 0, 50, null);
		BoostPlatform boostP = new BoostPlatform(game, -500, -10, 1150, 20, boostFactor, null); 
		Enemy boostPlatEnemy = new Enemy(game, boostP, null);
		Enemy movingPlatEnemy = new Enemy(game, movingP, null);
		Enemy regularPlatEnemy = new Enemy(game, regularP, null);
		Enemy ghostPlatEnemy = new Enemy(game, ghostP, null);
		
		game.addSprite(regularPlatEnemy);
		game.addSprite(regularP);
		game.addAllNewSprites();
		
		int changesInDirection = 0;
		int frameCount = 0;
		boolean goingRight = true;
		double previousX = regularPlatEnemy.getX();
		
		//Enemy should at least change direction 10 times in
		//about 16 minutes (1000 seconds)
		while(changesInDirection < 11) {
			if (frameCount == 60000) {
				fail("The enemy never changes direction");
				break;
			}
			regularPlatEnemy.update();
			if (goingRight && (previousX > regularPlatEnemy.getX())) {
					changesInDirection ++;
					goingRight = false;
			}
			else if (!goingRight && (previousX < regularPlatEnemy.getX())) {
					changesInDirection ++;
					goingRight = true;
			}
			frameCount ++;
			previousX = regularPlatEnemy.getX();
		}
	}
}
