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
	boolean start;

	public ClientChoiceScreen() {
		
		//make new main window for the game menu
		frame = new JFrame();
		frame.setTitle("Multiplayer Options");

		//make panel for game buttons
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));

		//add one button for each option
		discoverhost = super.addButton(buttons, "Discover host automatically");
		discoverhost.addActionListener(this);
		enterIP = super.addButton(buttons, "Enter IP manually");
		enterIP.addActionListener(this);
		
		
		//add buttons to the window
		setUpWindow(frame, buttons);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.dispose(frame);
		if(e.getSource()==discoverhost) {
			Main.startGame(5, null);
		}else if(e.getSource()==enterIP) {
			EnterIPScreen ip = new EnterIPScreen();
		}

	}

}
