package game;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;




public class Menu implements ActionListener {

	private final JButton playSingleplayer;
	private final JButton playMultiplayer;
	private static JFrame frame;
	boolean start;

	public Menu() {
		
		//make new main window for the game menu
		frame = new JFrame();
		frame.setTitle("Game Menu");

		//make panel for game buttons
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));

		//add one button for each option
		playSingleplayer = addButton(buttons, "Singleplayer");
		playMultiplayer = addButton(buttons, "Multiplayer");
		
		//add buttons to the window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(buttons);
		frame.setMinimumSize(new Dimension(400, 250));
		frame.setPreferredSize(new Dimension(400, 250));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}


	/**
	 * Adds buttons with a fixed style and add it to the panel
	 * @param buttons - The JPanel containing all the buttons.
	 * @param name - The name to be displayed on the button.
	 * @return
	 */
	JButton addButton(JPanel buttons, String name) {
		JButton button = new JButton();
		button.setText(name);
		button.setFont(new Font("Arial", Font.PLAIN, 40));
		button.addActionListener(this);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		//button.setBorder(new RoundedBorder(20)); //10 is the radius
		buttons.add(Box.createRigidArea(new Dimension(20, 20)));
		buttons.add(button);
		return button;
	}

	//this method is inherited from ActionListener and is the method
	//that gets called when buttons are clicked.
	@Override
	public void actionPerformed(ActionEvent e) {
		frame.setVisible(false);
		if(e.getSource() == playSingleplayer) {
			System.out.println("Received Singleplayer");
			promptKeyPreference();
		}
		if(e.getSource() == playMultiplayer) {
			System.out.println("Received Multiplayer");
			promptMultiplayer();
		}
		
	}

	
	private static void promptKeyPreference() {
		Object[] possibilities = {"W, A, S and D", "Arrows"};
		String s = (String) JOptionPane.showInputDialog(
						null,
						"Which keys do you want to use to control your player?\n",
						"Game menu - Player control",
						JOptionPane.PLAIN_MESSAGE,
						null,
						possibilities,
						null);

		//If a string was returned, say so.
		if((s != null) && (s.length() > 0)) {
			System.out.println("Received " + s);
		}
		if (s.equals(possibilities[0])) {
			Main.startGame(0);
		} else {
			Main.startGame(1);
		}
			
	}


	private static void promptMultiplayer() {
		Object[] possibilities = {"Single Screen Multiplayer", "Network Multiplayer"};
		String s = (String) JOptionPane.showInputDialog(
						null,
						"How would you like to play Multiplayer?\n",
						"Game menu - Multiplayer mode",
						JOptionPane.PLAIN_MESSAGE,
						null,
						possibilities,
						null);

		//If a string was returned, say so.
		if((s != null) && (s.length() > 0)) {
			System.out.println("Received " + s);
		}
		
		if (s == possibilities[0]) {
			
			Main.startGame(2);
		} else {
			promptNetwork();
		}
	}
	
	private static void promptNetwork() {
		Object[] possibilities = {"Auto", "Server", "Client"};
		String s = (String) JOptionPane.showInputDialog(
						null,
						"Would you like to be a server or a client for your network multiplayer game?\n",
						"Game menu - Network options",
						JOptionPane.PLAIN_MESSAGE,
						null,
						possibilities,
						null);

		//If a string was returned, say so.
		if((s != null) && (s.length() > 0)) {
			System.out.println("Received " + s);
		}
		if (s.equals(possibilities[1])) {
			Main.startGame(4);
		} else if (s.equals(possibilities[2])) {
			promptClient();
		} else {
			Main.startGame(3);
		}
			
	}
	
	private static void promptClient() {
		Object[] possibilities = {"Discover host automatically", "Enter IP manually"};
		String s = (String) JOptionPane.showInputDialog(
						null,
						"How would you like to connect to the server?\n",
						"Game menu - Connection options",
						JOptionPane.PLAIN_MESSAGE,
						null,
						possibilities,
						null);

		//If a string was returned, say so.
		if((s != null) && (s.length() > 0)) {
			System.out.println("Received " + s);
		}
		if (s.equals(possibilities[0])) {
			Main.startGame(5);
		} else {
				Main.startGame(6);
		}
			
	}
}



