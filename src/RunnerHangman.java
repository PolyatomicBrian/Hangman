import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;


public class RunnerHangman {

	public static int w;
	public static int h;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame f = new JFrame();
		f.setTitle("Hangman");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(500,500);
		Container pane = f.getContentPane();
		f.setLayout(new BorderLayout());
		
		w = f.getWidth();
		h = f.getHeight();
		
		HangmanGUI gui = new HangmanGUI(w,h);
		pane.add(gui, BorderLayout.CENTER);
		
		BottomGUI btm = new BottomGUI(w,h, gui);
		btm.setPreferredSize(new Dimension(w/4,h/4));
		pane.add(btm, BorderLayout.SOUTH);

		f.setVisible(true);
	}

}
