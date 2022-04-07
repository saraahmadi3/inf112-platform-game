package screens;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import game.Main;

public abstract class AbstractScreen{
	
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
	 * sets up a frame with a set of buttons.
	 * defult: close: EXIT_ON_CLOSE, window dimensions: 500x350
	 * @param frame
	 * @param buttons
	 */
	public void setUpWindow(JFrame frame, JPanel buttons) {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(buttons);
		frame.setMinimumSize(new Dimension(500, 350));
		frame.setPreferredSize(new Dimension(500, 350));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	//https://github.com/LWJGL/lwjgl3/issues/149
	/*
	 * Run System.gc(), at least two times. Not sure how AWT handles native resources, 
	 * maybe there's a finalizable/PhantomRef-ed resource that is not released immediately, 
	 * but a subsequent GC triggers a (too late) free. 
	 */
	public void dispose(JFrame frame) {
		frame.setVisible(false);
		frame.dispose();
		for (int i=0; i<5; i++) {
			System.gc();
		}
		
	}
}
