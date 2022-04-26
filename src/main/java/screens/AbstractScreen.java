package screens;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import game.Main;
import game.StartVariabels;

public abstract class AbstractScreen{
	
	private JButton back;
	private JButton startmenu;
	public static StartVariabels var;
	
	/**
	 * adds a button (JButton) to a JPanel, with a string to be added onto the button.
	 * default: font: Arial, font size: 40, string in button alignment: centre, button: box with 20x20 dimensions
	 * @param buttons
	 * @param name
	 * @return
	 */
	public JButton addButton(JPanel buttons, String name) {
		JButton button = new JButton();
		button.setText(name);
		button.setFont(new Font("Arial", Font.PLAIN, 40));
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		//button.setBorder(new RoundedBorder(20)); //10 is the radius
		buttons.add(Box.createRigidArea(new Dimension(20, 20)));
		buttons.add(button);
		return button;
	}
	
	/**
	 * adds a JPanel with option buttons to the end of another JPanel panel
	 * @param panel
	 */
	public void addOptions(JPanel panel) {
		JPanel optionspanel = new JPanel();
		
		ImageIcon backicon = new ImageIcon("src/main/resources/images/backButton.png");
		back = new JButton(backicon);
		startmenu = new JButton("Start Menu");
		startmenu.setFont(new Font("Arial", Font.PLAIN, 18));
		
		optionspanel.add(back, BorderLayout.WEST);
		optionspanel.add(startmenu, BorderLayout.CENTER);
		
		panel.add(optionspanel, BorderLayout.SOUTH);
		
	}
	
	
	public JButton getback() {
		return back;
	}
	
	public JButton getstart() {
		return startmenu;
	}
	
	/**
	 * opens a new menuScreen
	 */
	public void BacktoStart() {
		System.out.println("Received start screen");
		
		new MenuScreen(var);
	}
	
	/**
	 * sets up a frame with a a JPanel
	 * defult: close: EXIT_ON_CLOSE, window dimensions: 500x350
	 * @param frame
	 * @param buttons
	 */
	public void setUpWindow(JFrame frame, JPanel panel) {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setMinimumSize(new Dimension(500, 350));
		frame.setPreferredSize(new Dimension(500, 350));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	/**
	 * disposes a frame and runs the garbage collector five times to prevent errors when opening new screens or the game 
	 * @param frame
	 */
	public void dispose(JFrame frame) {
		frame.setVisible(false);
		frame.dispose();
		for (int i=0; i<5; i++) {
			System.gc();
		}
		
	}
}
