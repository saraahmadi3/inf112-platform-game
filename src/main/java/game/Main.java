package game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import screens.MenuScreen;

public class Main {
	
    public static void main(String[] args) {
    	new MenuScreen();
    }
    
    public static void startGame (int mode, String inputIP) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("Game");
        cfg.setWindowedMode(1080, 720);
        
        GameLoop loop = new GameLoop(mode, inputIP);
        
        new Lwjgl3Application(loop, cfg);
        
        //When everything in the Lwjgl3Application has finished running the game is over,
        //and the system should exit to close any running servers or other processes.
        System.exit(0);
         
    }

}