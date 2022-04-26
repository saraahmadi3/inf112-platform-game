package game.levels;

import game.GameState;
import gameObjects.*;

public class Level2 implements Levels {

	public Level2 (GameState game) {
		new Tips(game);
		new Door(game, 5, 650, 20, 34);
		new Key(game, 1050, 12);

		if (game.getMultiPlayer()) {
			new Player(50, 15, game, "player2.png", 2);
			new Player(50, 15, game);
			new Key(game, 50, 310);
		} else if (game.getSinglePlayerID() == 2){
			new Player(50, 15, game, "player2.png", 2);
		} else {
			new Player(50, 15, game);
		}
		
		new Coin(game, 20, 100);
		new Coin(game, 1020, 100);
		new Coin(game, 50, 400);
		new Coin(game, 750, 650);
		new Coin(game, 500, 350);
		
		new Platform(game, -500, -10, 650, 20, "grass.png"); //Floor
		new Platform(game, 300, -10, 1500, 20, "grass.png"); //Floor
		new Platform(game, -500, 718, 2000, 20); //Top
		new Platform(game, -10, -500, 12, 2000); //Left wall
		new Platform(game, 1078, -500, 15, 2000); //Right wall
		
		
		new MovingPlatform(game, 550, 130, 50, 8, 0, 150, 30);
		MovingPlatform moving1 = new MovingPlatform(game, 825, 350, 100, 8, 0, 250, 75);
		new MovingPlatform(game, 150, 620, 75, 8, 420, 0, 75);
 
		MovingPlatform moving2 = new MovingPlatform(game, 350, 300, 75, 8, 100, 0, 50); 
		
		MovingPlatform moving3 = new MovingPlatform(game, 420, 450, 75, 8, 0, 125, 50); 
		
		new BoostPlatform(game, 1000, 50, 50, 8, 2);
		new GhostPlatform(game, 700, 600,100,8,2);
	
		Platform keyPlatform = new Platform(game, 50, 300, 100, 8);
		
		new GhostPlatform(game, 250,400,75,8,2);
		Platform platform1 = new Platform(game, 400,75,100, 8);
		
		Platform bigBlock = new Platform(game,750,100,200,150);
		new MovingPlatform(game, 760, 10, 8, 50, 0, 40,10);
		new MovingPlatform(game, 795, 10, 8, 50, 0, 40,20);
		new MovingPlatform(game, 830, 10, 8, 50, 0, 40,10);
		new MovingPlatform(game, 865, 10, 8, 50, 0, 40,20);
		new MovingPlatform(game, 900, 10, 8, 50, 0, 40,10);
		new MovingPlatform(game, 935, 10, 8, 50, 0, 40,20);
		
		Platform doorPlatform = new Platform(game, 0,642,100,8);
	
		new Enemy(game, moving1);
		new Enemy(game, moving2);
		new Enemy(game, moving3);
		new Enemy (game, keyPlatform);
		new Enemy(game, platform1);
		new Enemy(game, bigBlock);
		new Enemy(game, doorPlatform);
	}
}
