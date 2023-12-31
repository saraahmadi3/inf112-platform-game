package screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import game.Main;

public class ClientChoiceScreen extends AbstractScreen implements ActionListener {

	private final JButton discoverhost;
	private final JButton enterIP;
	private static JFrame frame;

	/**
	 * creates new ClientShoiceScreen object. Opens a new JFrame with interactive buttons
	 */
	public ClientChoiceScreen() {
		
		//make new main window for the game menu
		frame = new JFrame();
		frame.setTitle("Client Options");

		//make panel for game buttons
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));

		//add one button for each option
		discoverhost = super.addButton(buttons, "Discover host automatically");
		discoverhost.addActionListener(this);
		enterIP = super.addButton(buttons, "Enter IP manually");
		enterIP.addActionListener(this);
		addOptions(buttons);
		super.getback().addActionListener(this);
		super.getstart().addActionListener(this);

		
		//add buttons to the window
		setUpWindow(frame, buttons);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.dispose(frame);
		if(e.getSource()==discoverhost) {
			var.setMode(5);
			Main.startGame();
		}else if(e.getSource()==enterIP) {
			EnterIPScreen ip = new EnterIPScreen();
		}else if (e.getSource()==super.getback()) {
			System.out.println("Received go back");
			new NetworkChoiceScreen();
		}else if (e.getSource() == super.getstart()) {
			BacktoStart();
		}

	}

}
