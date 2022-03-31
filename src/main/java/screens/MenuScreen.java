package screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import game.Main;

public class MenuScreen extends AbstractScreen implements ActionListener {

	private final JButton playSingleplayer;
	private final JButton playMultiplayer;
	private static JFrame frame;
	boolean start;

	public MenuScreen() {
		
		//make new main window for the game menu
		frame = new JFrame();
		frame.setTitle("Game Menu");

		//make panel for game buttons
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));

		//add one button for each option
		playSingleplayer = super.addButton(buttons, "Singleplayer");
		playSingleplayer.addActionListener(this);
		playMultiplayer = super.addButton(buttons, "Multiplayer");
		playMultiplayer.addActionListener(this);
		
		
		//add buttons to the window
		setUpWindow(frame, buttons);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		frame.setVisible(false);
		frame.dispose();
		if(e.getSource() == playSingleplayer) {
			System.out.println("Received Singleplayer");
			Main.startGame(0);

		}
		if(e.getSource() == playMultiplayer) {
			System.out.println("Received Multiplayer");
			
			MultiplayerChoiceScreen multi = new MultiplayerChoiceScreen();

		}
		
	}

	
}
