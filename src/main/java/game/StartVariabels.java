package game;

/**
 * class responsible for all the initial Starting Variables need to start the game.
 * 
 * @author saraa
 *
 */
public class StartVariabels {
	
	private static String IPinput;
	private static float volume;
	private static int mode;
	
	public StartVariabels() {
		
		}
	
	public void setIP(String input) {
		this.IPinput =input;
	}
	
	public void setVolume(float volume) {
		this.volume=volume;
	}
	
	public void setMode(int mode) {
		this.mode=mode;
	}
	
	public int getMode() {
		return mode;
	}
	
	public String getIP() {
		return IPinput;
	}
	
	public float getVolume() {
		return volume;
	}

}
