package screens;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import game.Main;

public class MenuScreen extends AbstractScreen implements ActionListener {

	private final JButton playSingleplayer;
	private final JButton playMultiplayer;
	private final JSlider volume;
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
		
		
		JLabel space = new JLabel("<html><br></html>");
		JLabel volumeLabel = new JLabel("<html>Volume</html>");
	
		volume = new JSlider(0,100,100);
		volume.setMinorTickSpacing(10);  
		volume.setMajorTickSpacing(20);  
		volume.setPaintTicks(true);  
		volume.setPaintLabels(true);  
		
		
		buttons.add(space);
		buttons.add(volumeLabel);
		buttons.add(volume);
		
		
		buttons.add(volumeLabel);
	
		
		//add buttons to the window
		setUpWindow(frame, buttons);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.dispose(frame);
		if(e.getSource() == playSingleplayer) {
			System.out.println("Received Singleplayer");
			Main.startGame(0, null);

		}
		if(e.getSource() == playMultiplayer) {
			System.out.println("Received Multiplayer");
			
			new MultiplayerChoiceScreen();

		}
		
	}

	
}
