package gameTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

//For this class (AbstractObject) these methods can be tested using any class 
//        that extends AbstractObject (without the methods being overwritten):

//checkForHits(), setGameState(), getGameState(), getSymbol(), getType(), getX(), 
//getY(), getXMid(), getYMid(), setX(), setY(), setXAndY(), moveByX(), moveByY(), 
//moveByXandY(), getWidth(), getHeight(), setWidth(), setHeight()
class GameObjectTest {
	
	private static GameState game;
	private static Player player;
	private static BoostPlatform boostP;
	private static Door door;
	private static Enemy enemy;
	private static GhostPlatform ghostP;
	private static Key key;
	private static MovingPlatform movingP;
	private static Platform regularP;
	private static Text text;
	private static Tips tip;
	
	//TO-DO: 
//Each of the following methods listed by class should each have at least one test 
	//confirming they are working as intended.
//BoostPlatform:
//update()
//checkForBoost()

//Door:
//update()
//checkForKey()
//openDoor()

//Enemy:
//update()
//move()

//GhostPlatform:
//update()
//checkForPlayer()
//checkForHit()

//Key:
//update()
//checkForPlayer()

//MovingPlatform:
//update()
//move()
//movePlayer()

//Platform:
//getType()

//Text:
//getSymbol()

//Tips:
//move()
	

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
