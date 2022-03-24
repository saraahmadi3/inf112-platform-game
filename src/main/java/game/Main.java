package game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {
	
    public static void main(String[] args) {
    	new Menu();
    }
    
    public static void startGame (int mode) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("Game");
        cfg.setWindowedMode(1080, 720);
        
        GameLoop loop = new GameLoop(mode);
        
        new Lwjgl3Application(loop, cfg); //No lines below this will run.
         
    }

}