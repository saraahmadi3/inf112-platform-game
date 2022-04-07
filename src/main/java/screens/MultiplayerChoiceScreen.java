package screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import game.Main;

public class MultiplayerChoiceScreen extends AbstractScreen implements ActionListener {

	private final JButton SingleScreenMultiplayer;
	private final JButton NetworkMultiplayer;
	private static JFrame frame;
	boolean start;

	public MultiplayerChoiceScreen() {
		
		//make new main window for the game menu
		frame = new JFrame();
		frame.setTitle("Multiplayer Options");

		//make panel for game buttons
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));

		//add one button for each option
		SingleScreenMultiplayer = super.addButton(buttons, "SingleScreenMultiplayer");
		SingleScreenMultiplayer.addActionListener(this);
		NetworkMultiplayer = super.addButton(buttons, "NetworkMultiplayer");
		NetworkMultiplayer.addActionListener(this);
		
		
		//add buttons to the window
		setUpWindow(frame, buttons);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		super.dispose(frame);
		if (e.getSource() == SingleScreenMultiplayer) {
			System.out.println("Received Single screen Multiplayer");
			Main.startGame(2, null);
		}
		if (e.getSource() == NetworkMultiplayer) {
			System.out.println("Received Multiplayer over a Network");
			
			NetworkChoiceScreen network = new NetworkChoiceScreen();
		}

	}

}