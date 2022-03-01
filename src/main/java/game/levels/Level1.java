package game.levels;

import game.BoostPlatform;
import game.Door;
import game.Enemy;
import game.GameState;
import game.GhostPlatform;
import game.Key;
import game.MovingPlatform;
import game.Platform;
import game.Player;
import game.Tips;

public class Level1 implements Levels {

	public Level1 (GameState game) {
		new Tips(game);
		new Door(game, 1050, 650, 20, 34);
		new Key(game, 390, 65);
		
		if (game.getMultiPlayer()) {
			new Player(50, 15, game, "player2.png", 2);
			new Player(50, 15, game);
			new Key(game, 950, 12);
		} else if (game.getSinglePlayerID() == 2){
			new Player(50, 15, game, "player2.png", 2);
		} else {
			new Player(50, 15, game);
		}
		
		
		new Platform(game, -500, -10, 1150, 20, "grass.png"); //Floor
		new Platform(game, 900, -10, 180, 20, "grass.png"); //Floor
		new Platform(game, -500, 718, 2000, 20); //Top
		new Platform(game, -10, -500, 12, 2000); //Left wall
		new Platform(game, 1078, -500, 15, 2000); //Right wall
		
		
		new MovingPlatform(game, 650, 140, 100, 8, 100, 0, 50);
		MovingPlatform moving1 = new MovingPlatform(game, 800, 250, 150, 8, 0, 150, 75);
		new MovingPlatform(game, 150, 620, 75, 8, 420, 0, 75);
		
		
		MovingPlatform moving2 = new MovingPlatform(game, 400, 360, 75, 8, 250, 75, 25); //new MovingPlatform(game, 400, 360, 75, 8, 250, 0, 50); 
		
		new BoostPlatform(game, 30, 250, 50, 8, 2);
		new BoostPlatform(game, 1000, 50, 50, 8, 2);
		new GhostPlatform(game, 750,600,100,8,2);
		
		new Platform(game, 250,400,75,8);
		Platform platform1 = new Platform(game, 200,50,100, 8);
		
		Platform bigBlock = new Platform(game, 350,100,200,80);
		new Platform(game, 500, 45, 50, 135);
		new Platform(game, 350, 10, 105, 55);
		new MovingPlatform(game, 360, 55, 8, 60, 0, 60,10);
		new MovingPlatform(game, 535, 10, 8, 60, 0, 50,10);
		
		new Platform(game, 28,600,54,8);
		Platform doorPlatform = new Platform(game, 980,642,100,8);
	
		new Enemy(game, moving1);
		new Enemy(game, moving2);
		new Enemy(game, platform1);
		new Enemy(game, bigBlock);
		new Enemy(game, doorPlatform);
	}
}
