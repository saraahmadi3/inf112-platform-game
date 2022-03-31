package screens;

import java.awt.Dimension;
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(buttons);
		frame.setMinimumSize(new Dimension(400, 250));
		frame.setPreferredSize(new Dimension(400, 250));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		frame.setVisible(false);
		frame.dispose();
		if (e.getSource() == SingleScreenMultiplayer) {
			System.out.println("Received Single screen Multiplayer");
			Main.startGame(2);
		}
		if (e.getSource() == NetworkMultiplayer) {
			System.out.println("Received Multiplayer over a Network");
			
			NetworkChoiceScreen network = new NetworkChoiceScreen();
		}

	}

}
