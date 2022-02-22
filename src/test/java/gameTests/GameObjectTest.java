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


// ISSUE #13 overall



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
	
// Approach: SPLIT TESTS INTO CATEGORIES. Create a new issue in git for each category.

	//Example:
	//==========TO-DO=========== (Issue #nr)
	//ToDoClass: toDo(), example()
	
		//All issues link to #13
	
	
//=========PLATFORMS========(Issue #19)
//BoostPlatform: update(), checkForBoost()
//GhostPlatform: update(), checkForPlayer(), checkForHit()
//MovingPlatform: update(), move(), movePlayer()
//Platform: getType()

	
//======DOOR-KEY-PLAYER======(Issue #20)
	//(Say it fast, it becomes dorky player lol)
//Door: update(), checkForKey(), openDoor()
//Key(): update(), checkForPlayer()

	
//=======TIPS-and-TEXT========(Issue #21)
//Tips: move()
//Text: getSymbol()

	
//===========ENEMY===========(Issue #22)
//Enemy: update(), move()
	

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
