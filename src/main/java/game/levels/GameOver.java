package game.levels;

import com.badlogic.gdx.graphics.Color;

import game.Enemy;
import game.GameOverKeyPress;
import game.GameState;
import game.MovingPlatform;
import game.Platform;
import game.Text;
import game.Tips;

public class GameOver implements Levels {
	
	public GameOver (GameState game) {
		new Tips(game, "A game by Kristian, Sara, Adrian and Dani.");
		new Text(game, 350, 100, "Press SPACE to play again!", 2, Color.RED);
		new GameOverKeyPress(game);
		
		if (!game.getMultiPlayer()) {
			new Text(game, 350, 575, "Game over. You got " + game.getPlayer(game.getSinglePlayerID()).getScore() + " points.", 2, Color.BLACK);;
		}
		else {
			int Player1Points = game.getPlayer(1).getScore();
			int Player2Points = game.getPlayer(2).getScore();
			new Text(game, 350, 575, "Game over. Player 1  got " + Player1Points + ".", 2, Color.BLACK);
			new Text(game, 350, 475, "Game over. Player 2  got " + Player2Points + ".", 2, Color.BLACK);
			
			if (Player1Points > Player2Points) {
				new Text(game, 350, 375, "Player 1 won!", 2, Color.BLACK);
			}
			else if (Player1Points < Player2Points) {
				new Text(game, 350, 375, "Player 2 won!", 2, Color.BLACK);
			}
			else {
				new Text(game, 450, 275, "It's a draw.", 2, Color.BLACK);
			}
		}
		
		Platform floor1 = new Platform(game, -500, -10, 1150, 20, "grass.png"); //Floor
		new Enemy(game, floor1);
		Platform floor2 =new Platform(game, 900, -10, 180, 20, "grass.png"); //Floor
		new Enemy(game, floor2);
		new Platform(game, -500, 718, 2000, 20); //Top
		new Platform(game, -10, -500, 12, 2000); //Left wall
		new Platform(game, 1078, -500, 15, 2000); //Right wall
		
		MovingPlatform moving1 = new MovingPlatform(game, 800, 250, 150, 8, 0, 150, 75);
		new Enemy(game, moving1);

		MovingPlatform moving2 = new MovingPlatform(game, 350, 360, 300, 8, 100, 50, 25);
		new Enemy(game, moving2);
		MovingPlatform moving3 = new MovingPlatform(game, 100, 250, 150, 8, 0, 150, 75);
		new Enemy(game, moving3);
		
	}

}
