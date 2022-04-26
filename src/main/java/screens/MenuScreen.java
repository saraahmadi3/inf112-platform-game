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
import game.StartVariabels;

public class MenuScreen extends AbstractScreen implements ActionListener {

	private final JButton playSingleplayer;
	private final JButton playMultiplayer;
	private static JFrame frame;
	private JSlider volume;
	

	/**
	 * creates new MenuScreen object. Opens a new JFrame with interactive elements
	 */
	public MenuScreen(StartVariabels var) {
		super.var=var;
		
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
		
		addVolume(buttons);
					
		//add buttons to the window
		setUpWindow(frame, buttons);

	}
	/**
	 * Adds a volume slider with a label to a JPanel
	 * @param panel
	 */
	public void addVolume(JPanel panel) {
		//panel for volume settings
		JPanel volpanel = new JPanel();
		//JSlider needs to be the sole item in a panel 
		JPanel sliderpanel = new JPanel();
				
		JLabel volumeLabel = new JLabel("Volume");
	
		volume = new JSlider(0,100,100);
		volume.setMinorTickSpacing(10);  
		volume.setMajorTickSpacing(20);  
		volume.setPaintTicks(true);  
		volume.setPaintLabels(true);  
		
		
		
		sliderpanel.add(volume);
		volpanel.add(volumeLabel);
		volpanel.add(sliderpanel);
		
		//add volume panel to the main panel 
		panel.add(volpanel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		float currentVolume = (float)(volume.getValue()/100);
		var.setVolume(currentVolume);
		super.dispose(frame);
		if(e.getSource() == playSingleplayer) {
			var.setMode(0);
			System.out.println("Received Singleplayer");
			Main.startGame();

		}
		if(e.getSource() == playMultiplayer) {
			System.out.println("Received Multiplayer");
			
			new MultiplayerChoiceScreen();

		}
		
	}

	
}
