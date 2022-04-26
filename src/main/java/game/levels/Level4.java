package game.levels;

import game.GameState;
import gameObjects.*;

public class Level4 implements Levels {
	public Level4 (GameState game) {
		new Tips(game);
		new Door(game, 978, 310, 20, 34);
		new Key(game, 611, 12);
		
		if (game.getMultiPlayer()) {
			new Player(50, 15, game, "player2.png", 2);
			new Player(50, 15, game);
			new Key(game, 635, 12);
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
		new Coin(game, 1050, 550);
		new Coin(game, 1020, 650);
		new Coin(game, 1050, 350);
		new Coin(game, 1020, 450);
		new Coin(game, 1050, 150);
		new Coin(game, 1020, 250);
		new Coin(game, 3, 175);
		new Coin(game, 3, 475);
		new Coin(game, 975, 620);
		
		new BoostPlatform(game, 1030, 0, 50, 10, 2.9);
		
		new Platform(game, -100, -10, 250, 20, "grass.png"); //Floor
		new Enemy (game, new Platform(game, 610, -10, 420, 20, "grass.png")); //Floor
		new Platform(game, -500, 718, 2000, 20); //Top
		new Platform(game, -10, -500, 12, 2000); //Left wall
		new Platform(game, 1078, -500, 15, 2000); //Right wall
		
		new Platform(game, 1000, 75, 12, 585); //Big wall on the right blocking the key
		new Platform(game, 950, 660, 62, 10); //Small platform at top of big wall
		
		Platform plat1 = new Platform(game, 2, 150, 900, 10);
		Platform plat2 = new Platform(game, 100, 300, 900, 10);
		Platform plat3 = new Platform(game, 2, 450, 900, 10); 
		Platform plat4 = new Platform(game, 100, 600, 900, 10);
		
		//blocks used to jump to next platform 
		new Enemy(game, new Platform(game, 2, 500, 35, 35));
		new Enemy(game, new Platform(game, 2, 200, 35, 35));
		
		for (int i=-400; i<=400; i+=155) {
			new Enemy(game, plat1, i);
			if(i%2==0){new Coin(game, (int) plat1.getXMid()+i, (int) plat1.getY()+75);};
		}
		
		for (int i=-400; i<=400; i+=135) {
			new Enemy(game, plat2, i);
			if(i%2==0){new Coin(game, (int) plat2.getXMid()+i, (int) plat2.getY()+75);};
		}
		
		for (int i=-400; i<=400; i+=105) {
			new Enemy(game, plat3, i);
			if(i%2==0){new Coin(game, (int) plat3.getXMid()+i, (int) plat3.getY()+75);};
		}
		
		for (int i=-400; i<=400; i+=75) {
			new Enemy(game, plat4, i);
			if(i%2==0){new Coin(game, (int) plat4.getXMid()+i, (int) plat4.getY()+50);};
		}
		
		new Enemy(game, new Platform(game, 900, 75, 100, 10), -1);
		new Enemy(game, new Platform(game, 800, 65, 100, 10));
		new Enemy(game, new Platform(game, 700, 55, 100, 10), -1);
		new Enemy(game, new Platform(game, 600, 45, 100, 10));
		
		new Platform(game, 600, -100, 10, 145);
		
		new MovingPlatform(game, 200, 0, 75, 10, 100, 50, 30);
		new MovingPlatform(game, 375, 50, 75, 10, 100, 50, 30);
	}
}
