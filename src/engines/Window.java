package engines;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends JFrame {	// Container for Game-Canvas
	private static final long serialVersionUID = 1L;

	public Window(int width, int height, String title, Game game){
		
		game.setPreferredSize(new Dimension(width, height));
		add(game);
		pack();
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		game.start();
	}
}
