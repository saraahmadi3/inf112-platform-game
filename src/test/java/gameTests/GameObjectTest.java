package gameTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
		boolean hasCollided = false;
		int frameCount = 0;
		while(!hasCollided) {
			playerOne.move();
			hasCollided = boostP.checkForHit(playerOne);
			frameCount++;
			//Simulate a 100 second drop to boostP by playerOne in 60 frames per second
			if (frameCount > 6000) {
				fail("The player never hits the platform");
				break;
			}
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
	

//MovingPlatform: update(), move(), movePlayer()
	/*public void move() {
		double delta = super.getGameState().getDeltaTime(); //The time passed since last frame
		
		double xMove = 0;
		double yMove = 0;
		
		if (goingRight) {
			if (super.getX() < startX+rangeX) {
				xMove+=delta*v;
			} else {
				goingRight=false;
			}
		}
		if (!goingRight) {
			if (super.getX() > startX) {
				xMove-=delta*v;
			} else {
				goingRight=true;
			}
		}
		
		if (goingUp) {
			if (super.getY() < startY+rangeY) {
				yMove+=delta*v;
			} else {
				goingUp=false;
			}
		}
		if (!goingUp) {
			if (super.getY() > startY) {
				yMove-=delta*v;
			} else {
				goingUp=true;
			}
		}
		
		super.moveByXandY(xMove, yMove);		
		
		if (super.getGameState().getPlayer(1) != null) {
			movePlayer(1, xMove, yMove);
		}
		if (super.getGameState().getPlayer(2) != null) {
			movePlayer(2, xMove, yMove);
		}
	
	}*/
	@Test
	void MovingPlatformTest() {
		assertEquals("Platform", movingP.getType());
		game.addSpriteQ(movingP);
		game.addAllNewSprites();
		assertEquals("Platform", game.getAllPlatforms().get(0).getType());
		//test update()
		//test movePlayer()
		
		double xStart = movingP.getX();
		double yStart = movingP.getY();
		double xPrevious = movingP.getX();
		double yPrevious = movingP.getY();
		int frameCount = 0;
		boolean hasComeBack = false;
		
		//Horizontal moving platform test on x axis
		while(!hasComeBack) {
			movingP.update();
			assertTrue(movingP.getY() == yStart);
			//Updating two frames. Simulates 100 seconds in 60 fps
			if (frameCount > 6000) {
				fail("The platform never returns");
				break;
			}
			else if (xPrevious == movingP.getX()) {
				fail("The platform does not move");
				break;
			}
			else if (xStart == movingP.getX()) {
				break;
			}
			frameCount ++;
			xPrevious = movingP.getX();
			yPrevious = movingP.getY();
		}
		
		xStart = movingP.getX();
		yStart = movingP.getY();
		yPrevious = movingP.getY();
		
		//Set platform to move vertically
		xRangeMovingP = 0;
		yRangeMovingP = 250;
		
		//Vertical moving platform test on y axis
		while(!hasComeBack) {
			movingP.update();
			assertTrue(movingP.getX() == xStart);
			//Updating two frames. Simulates 100 seconds in 60 fps
			if (frameCount > 6000) {
				fail("The platform never returns");
				break;
			}
			else if (yPrevious == movingP.getY()) {
				fail("The platform does not move");
				break;
			}
			else if (yStart == movingP.getY()) {
				break;
			}
			frameCount ++;
			xPrevious = movingP.getX();
			yPrevious = movingP.getY();
		}
	}
	
	
//Platform: getType()
	
	
//GhostPlatform: update(), checkForPlayer(), checkForHit()
	//Need to test fall when player stands too long on GhostPlatform
	//Need to find conditions for player to pass through. Check for negative delta Y.
	@Test 
	void GhostPlatformTest() {
		fail("Not yet implemented");
	}

	
//======DOOR-KEY-PLAYER======(Issue #20)[OPEN]
	//(Say it fast, it becomes dorky player lol)
//Door: update(), checkForKey(), openDoor()
//Key(): update(), checkForPlayer()

	
//=======TIPS-and-TEXT========(Issue #21)[OPEN]
//Tips: move()
//Text: getSymbol()

	
//===========ENEMY===========(Issue #22)[OPEN]
//Enemy: update(), move()

}
