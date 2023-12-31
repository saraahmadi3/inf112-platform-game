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
	
	/**
	 * creates new EnterIPScreen object. Opens a new JFrame with interactive elements
	 */
	public EnterIPScreen() {
		
		//make new main window for the game menu
		frame = new JFrame();
		frame.setTitle("IP Menu");
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
		
		frame.getRootPane().setDefaultButton(submit);
		addOptions(panel);
		super.getback().addActionListener(this);
		super.getstart().addActionListener(this);

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
			var.setMode(6);
			inputIP =input.getText();
			var.setIP(inputIP);
			Main.startGame();
		}else if (e.getSource()==super.getback()) {
			System.out.println("Received go back");
			new ClientChoiceScreen();
		}else if (e.getSource() == super.getstart()) {
			BacktoStart();
		}

	}

}
