package game.levels;

import game.BoostPlatform;
import game.Coin;
import game.Door;
import game.Enemy;
import game.GameState;
import game.GhostPlatform;
import game.Key;
import game.MovingPlatform;
import game.Platform;
import game.Player;
import game.Tips;

public class Level3 implements Levels {
	public Level3 (GameState game) {
		new Tips(game);
		new Door(game, 1055, 10, 20, 34);
		new Key(game, 562, 12);
		
		if (game.getMultiPlayer()) {
			new Player(50, 15, game, "player2.png", 2);
			new Player(50, 15, game);
			new Key(game, 330, 12);
		} else if (game.getSinglePlayerID() == 2){
			new Player(50, 15, game, "player2.png", 2);
		} else {
			new Player(50, 15, game);
		}
		
		
		new Coin(game, 1050, 600);
		new Coin(game, 1020, 500);
		new Coin(game, 1050, 400);
		new Coin(game, 1020, 300);
		new Coin(game, 1050, 200);
		new Coin(game, 1020, 100);

		
		
		new Platform(game, -500, -10, 1112, 20, "grass.png"); //Floor
		new Platform(game, 1000, -10, 1500, 20, "grass.png"); //Floor
		new Platform(game, -500, 718, 2000, 20); //Top
		new Platform(game, -10, -500, 12, 2000); //Left wall
		new Platform(game, 1078, -500, 15, 2000); //Right wall
		
		new Platform(game, 1000, 10, 12, 650);//Big wall on the right blocking the door
		new Platform(game, 950, 660, 62, 10); //Small platform at top of big wall
		
		new Platform(game, 800, 75, 12, 1000);
		new Enemy(game, new Platform(game, 900, 100, 100, 10));
		new MovingPlatform(game, 830, 225 , 50, 10, 100, 0, 50);
		new Enemy(game, new Platform(game, 812, 350, 100, 10));
		new MovingPlatform(game, 830, 450, 50, 10, 100, 0, 50);
		new Enemy(game, new MovingPlatform(game, 830, 550, 50, 10, 0, 100, 50));
		
		new MovingPlatform(game, 650, -10, 75, 20, 250, 0, 50);
		
		new Platform(game, 600, 10, 12, 650);
		new Platform(game, 550, 660, 112, 10);
		new Enemy(game, new Platform(game, 612, 100, 100, 10));
		new Enemy(game, new Platform(game, 700, 250, 100, 10));
		new Enemy(game, new Platform(game, 612, 400, 100, 10));
		new Enemy(game, new Platform(game, 700, 550, 100, 10));
		
		new Platform(game, 300, 10, 12, 650);
		new Platform(game, 250, 660, 112, 10);
		new Enemy(game, new Platform(game, 312, 100, 100, 10));
		new Enemy(game, new MovingPlatform(game, 406, 220, 100, 10, 0, 60, 50));
		new Enemy(game, new Platform(game, 312, 400, 100, 10));
		new Enemy(game, new MovingPlatform(game, 406, 500, 100, 10, 0, 75, 50));
		new Enemy(game, new Platform(game, 500, 100, 100, 10));
		new Enemy(game, new Platform(game, 500, 400, 100, 10));
		
		new Enemy(game, new Platform(game, 200, 250, 100, 10));
		new Enemy(game, new Platform(game, 200, 550, 100, 10));
		new Enemy(game, new Platform(game, 175, 350, 50, 10));
		new Enemy(game, new MovingPlatform(game, 10, 100, 100, 10, 0, 75, 50));
		new Enemy(game, new MovingPlatform(game, 10, 400, 100, 10, 0, 75, 50));
		
	}
}
