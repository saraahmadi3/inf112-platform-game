package screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import game.Main;

public class EnterIPScreen extends AbstractScreen implements ActionListener {

	private static JFrame frame;
	private final JTextField input;
	private final JButton submit; 
	private final JLabel textInfo;
	private static String inputIP;
	
	public EnterIPScreen() {
		
		//make new main window for the game menu
		frame = new JFrame();
		frame.setTitle("Game Menu");
		inputIP="";
		//make panel for input and buttons
		JPanel panel = new JPanel();
//		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		textInfo = new JLabel("Enter server-IP:");
		panel.add(textInfo);
		input = new JTextField(16);
		panel.add(input);

		submit  = new JButton("Submit");
		panel.add(submit);
		submit.addActionListener(this);
		
		
		//add buttons to the window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setSize(300,300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
	
	public String getIP() {
		return inputIP;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.dispose(frame);
		if (e.getSource()==submit) {
			inputIP =input.getText();
			Main.startGame(6, inputIP);
		}

	}

}
