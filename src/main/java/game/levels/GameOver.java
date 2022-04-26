package game.levels;

import gameObjects.GameOverKeyPress;
import game.GameState;
import gameObjects.Text;

public class GameOver implements Levels {
	
	public GameOver (GameState game) {
		new Text(game, 500, 300, "Press SPACE to play again!");
		new GameOverKeyPress(game);
		
		if (!game.getMultiPlayer()) {
			new Text(game, 700, 575, "Game over. You got " + game.getPlayer(game.getSinglePlayerID()).getScore() + " points.");
			return;
		}

		int Player1Points = game.getPlayer(1).getScore();
		int Player2Points = game.getPlayer(2).getScore();
		new Text(game, 700, 575, "Game over. Player 1  got " + Player1Points + ".");
		new Text(game, 700, 475, "Game over. Player 2  got " + Player2Points + ".");
		
		if (Player1Points > Player2Points) {
			new Text(game, 700, 375, "Player 1 won!");
		}
		else if (Player1Points < Player2Points) {
			new Text(game, 700, 375, "Player 2 won!");
		}
		else {
			new Text(game, 700, 275, "It's a draw.");
		}
	}

}

