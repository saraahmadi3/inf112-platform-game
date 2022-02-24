package gameTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.JUnitException;

import exceptions.InvalidPlatformException;
import game.BoostPlatform;
import game.Door;
import game.Enemy;
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
class GameObjectTest {
	
	private static GameState game;
	private static Player playerOne;
	private static Player playerTwo;
	private static BoostPlatform boostP;
	private static BoostPlatform negativeBoostP;
	private static Door door;
	private static Enemy boostPlatEnemy;
	private static Enemy movingPlatEnemy;
	private static Enemy regularPlatEnemy;
	private static Enemy ghostPlatEnemy;
	private static GhostPlatform ghostP;
	private static Key key;
	private static MovingPlatform movingP;
	private static Platform regularP;
	private static Text tooLong;
	private static Text text;
	private static Tips tip;
	

	private static final double J = 150;
	private static double boostFactor = 2;
	private static double negativeBoostFactor = -1;
	private static double ghostDelay = 2;
	private static int xRangeMovingP = 250;
	private static int yRangeMovingP = 0;
	private static int speedMovingP = 50;
	
	@BeforeAll
	static void setUp() {
		game = new GameState(0);
		playerOne = new Player(50, 15, game, null, 1);
		playerTwo = new Player(50, 15, game, null, 2);
		
		negativeBoostP = new BoostPlatform(game, -500, -10, 1150, 20, negativeBoostFactor, null); 
		boostP = new BoostPlatform(game, -500, -10, 1150, 20, boostFactor, null); 
		ghostP = new GhostPlatform(game, -500, -10, 1150, 20, ghostDelay);
		movingP = new MovingPlatform(game, -500, -10, 1150, 20, xRangeMovingP, yRangeMovingP, speedMovingP, null);
		door = new Door(game, 60, 15, 20, 34, null);
		
		regularP = new Platform(game, -500, -10, 1150, 20, null);
		boostPlatEnemy = new Enemy(game, boostP, null);
		movingPlatEnemy = new Enemy(game, movingP, null);
		regularPlatEnemy = new Enemy(game, regularP, null);
		ghostPlatEnemy = new Enemy(game, ghostP, null);
		key = new Key(game, 55, 15, null);
		text = new Text(game, 30, 200, "This is a regular text at x=30 and y=200");
		tooLong = new Text(game, 30, 300, "This text is too l" + " ".repeat(10000) + "ng");
		Tips tip = new Tips(game);
		
	}
	
	
// Approach: SPLIT TESTS INTO CATEGORIES. Create a new issue in git for each category.

	//Example:
	//==========TO-DO=========== (Issue #nr)[OPEN / CLOSED]
	//ToDoClass: toDo(), example()
	
		//All issues link to #13
	
	
//=========PLATFORMS========(Issue #19)[OPEN]
	@Test 
	void boostPlatformTest() {
		assertEquals(0, playerOne.getGv());
		game.addSprite(boostP);
		game.addSprite(playerOne);
		game.addAllNewSprites();
		
		//Adding playerTwo to wait list
		game.addSprite(playerTwo);
		
		playerOne.move();
		assertTrue(boostP.checkForHit(playerOne));
		assertFalse(boostP.checkForHit(playerTwo));
		
		//checkForBoost() in BoostPlatform
		boostP.checkForBoost(1);
		assertEquals(-J*boostFactor, playerOne.getGv());
		
		//update() in BoostPlatform
		playerOne.boost(0);
		boostP.update();
		assertEquals(-J*boostFactor, playerOne.getGv());
		
		game.killSprite(playerOne);
		game.removeAllDeadSprites();
		
		//Adding playerTwo from wait list
		game.addAllNewSprites();
		playerTwo.move();
		
		assertTrue(boostP.checkForHit(playerTwo));
		
		//checkForBoost() playerTwo
		boostP.checkForBoost(2);
		assertEquals(-J*boostFactor, playerTwo.getGv());
		
		//update() boost with playerTwo on it
		playerTwo.boost(0);
		boostP.update();
		assertEquals(-J*boostFactor, playerTwo.getGv());
		
		game.killSprite(boostP);
		game.removeAllDeadSprites();
		
		negativeBoostP = new BoostPlatform(game, -500, -10, 1150, 20, negativeBoostFactor); 
		
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
	
	
//GhostPlatform: update(), checkForPlayer(), checkForHit()
	//Need to test fall when player stands too long on GhostPlatform
	//Need to find conditions for player to pass through. Check for negative delta Y.
	
//MovingPlatform: update(), move(), movePlayer()
//Platform: getType()
	

	
//======DOOR-KEY-PLAYER======(Issue #20)[OPEN]
	//(Say it fast, it becomes dorky player lol)
//Door: update(), checkForKey(), openDoor()
//Key(): update(), checkForPlayer()

	
//=======TIPS-and-TEXT========(Issue #21)[OPEN]
//Tips: move()
//Text: getSymbol()

	
//===========ENEMY===========(Issue #22)[OPEN]
//Enemy: update(), move()
	

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
