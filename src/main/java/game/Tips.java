package game;

public class Tips extends Text {
	
	private static final double V = 75; //Velocity for text
	private static final int spaces = 200; //Spaces between each tip
	
	//TODO Add more tips as the game gets more mechanics
	private static final String TIP = 
			"Tip: You can move to the left or right even while in the air." + " ".repeat(spaces)
			+ "Tip: You can press DOWN while in the air to get down faster." + " ".repeat(spaces)
			+ "TIP: While falling back down after a jump you can press jump again to do a double jump." + " ".repeat(spaces)
			+ "TIP: After doing a double jump you can not jump again bafore landing." + " ".repeat(spaces)
			+ "Tip: Moving while in the air is slower than running on the ground." + " ".repeat(spaces)
			+ "Tip: If you run off the edge of a platform you lose your chance to jump and double jump." + " ".repeat(spaces)
			+ "Tip: Timing is key when moving platforms are involved, aim for where the platform is going to be, not where it is." + " ".repeat(spaces)
			+ "Tip: You need to pick up the key before you can open the door and complete the level." + " ".repeat(spaces)
			+ "Tip: A Booster platform launches you into the air and is useful for reaching high places." + " ".repeat(spaces)
			+ "Tip: After being launched into the air by a Booster platform you can use a double jump just like you would after a normal jump." + " ".repeat(spaces)
			+ "Tip: Ghost platforms will look identical to regular platforms until you step on one." + " ".repeat(spaces)
			+ "Tip: Ghost platforms will disappear if you stand on them for too long, but don't worry, after a while they come back." + " ".repeat(spaces)
			+ "Tip: The player with the highest score at the end of the game wins!" + " ".repeat(spaces)
			+ "Tip: You get a lot of points by finishing a level!" + " ".repeat(spaces)
			+ "Tip: Kill enemies and collect coins to earn more points!" + " ".repeat(spaces)
			+ "Tip: Be careful around enemies, touching them can be deadly unless you land on them from above. " + " ".repeat(spaces)
			+ "Tip: Enemies are bouncy, landing on them will not only kill the enemy, but also make the player bounce a small distance into the air." + " ".repeat(spaces)
			+ "Tip: Want to play again after the game is over? Just press SPACE on the Game Over Screen to start a new game." + " ".repeat(spaces)
			+ "Tip: Using the double jump at the right time is crucial. Use it as soon as possible to gain the most height, or wait to get more distance." + " ".repeat(spaces);
	

	public Tips(GameState game) {
		super(game, 1250, 700, TIP);
	}
	
	@Override
	public void update() {
		move();
	}
	
	@Override
	public void move() {
		double delta = super.getGameState().getDeltaTime(); //The time passed since last frame
		super.moveByX(-V*delta); //Scrolling
		
		if (super.getX()<-super.getWidth()) {
			super.setX(1250);
		}
	}
}
