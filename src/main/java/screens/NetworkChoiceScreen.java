package screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import game.Main;

public class NetworkChoiceScreen extends AbstractScreen implements ActionListener {
	
	private final JButton auto;
	private final JButton server;
	private final JButton client;
	private static JFrame frame;

	/**
	 * creates new NetworkChoiceScreen object. Opens a new JFrame with interactive buttons
	 */
	public NetworkChoiceScreen() {
		
		//make new main window for the game menu
		frame = new JFrame();
		frame.setTitle("Network Menu");

		//make panel for game buttons
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));

		//add one button for each option
		auto = super.addButton(buttons, "Auto");
		auto.addActionListener(this);
		server = super.addButton(buttons, "Server");
		server.addActionListener(this);
		client = super.addButton(buttons, "Client");
		client.addActionListener(this);
		addOptions(buttons);
		super.getback().addActionListener(this);
		super.getstart().addActionListener(this);
		
		//add buttons to the window
		setUpWindow(frame, buttons);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		super.dispose(frame);
		if (e.getSource() == auto) {
			var.setMode(3);
			System.out.println("Received Aotomatic Network solution");
			Main.startGame();
		}else if(e.getSource()==server) {
			var.setMode(4);
			System.out.println("Received Server");
			Main.startGame();
		}else if (e.getSource()==client) {
			System.out.println("Received Client");
			new ClientChoiceScreen();
		}else if (e.getSource()==super.getback()) {
			System.out.println("Received go back");
			new MultiplayerChoiceScreen();
		}else if (e.getSource() == super.getstart()) {
			BacktoStart();
		}

	}

}
