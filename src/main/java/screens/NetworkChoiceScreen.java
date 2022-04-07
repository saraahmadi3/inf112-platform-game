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
	boolean start;

	public NetworkChoiceScreen() {
		
		//make new main window for the game menu
		frame = new JFrame();
		frame.setTitle("Game Menu");

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
		
		
		//add buttons to the window
		setUpWindow(frame, buttons);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		super.dispose(frame);
		if (e.getSource() == auto) {
			System.out.println("Received Aotomatic Network solution");
			Main.startGame(3, null);
		}else if(e.getSource()==server) {
			System.out.println("Received Server");
			Main.startGame(4, null);
		}else if (e.getSource()==client) {
			System.out.println("Received Client");
			ClientChoiceScreen client = new ClientChoiceScreen();
		}

	}

}
