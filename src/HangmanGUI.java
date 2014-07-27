import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;



public class HangmanGUI extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	int w;
	int h;
	
	public static JLabel lblGuesses = new JLabel();
	
	
	public HangmanGUI(int w, int h){
		setBackground(Color.GREEN);
		this.w = w;
		this.h = h;
		lblGuesses.setText("GUESSES: ");
		//lblGuesses.setBounds(3*w/4,h/4,100,100);
		add(lblGuesses);
	}
	
	
	public void drawLynch(Graphics g){
		g.setColor(Color.BLACK);
		g.drawLine(w/2, h/8, w/2, h/2); //Main vertical bar
		g.drawLine(w/2, h/8, w/2-w/5, h/8);
		g.drawLine(w/2-w/5, h/8, w/2-w/5, h/6);
		
	}
	
	public void drawHead(Graphics g){
		g.setColor(Color.BLACK);
		g.drawOval(w/2-w/5-w/24/2, h/6, w/24, h/24);
	}
	
	public void drawBody(Graphics g){
		g.setColor(Color.BLACK);
		g.drawLine(w/2-w/5-w/200, h/6 + h/24, w/2-w/5-w/200, h/3);
	}
	
	public void drawLeftLeg(Graphics g){
		g.setColor(Color.BLACK);
		g.drawLine(w/2-w/5-w/200, h/3, w/2-w/5-w/200-w/18, h/3+h/20);
	}
	
	public void drawRightLeg(Graphics g){
		g.setColor(Color.BLACK);
		g.drawLine(w/2-w/5-w/200, h/3, w/2-w/5-w/200+w/18, h/3+h/20);
	}
	
	public void drawLeftArm(Graphics g){
		g.setColor(Color.BLACK);
		g.drawLine(w/2-w/5-w/200, h/3-h/8, w/2-w/5-w/200-w/18, h/4+h/20);
	}
	
	public void drawRightArm(Graphics g){
		g.setColor(Color.BLACK);
		g.drawLine(w/2-w/5-w/200, h/3-h/8, w/2-w/5-w/200+w/18, h/4+h/20);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		drawLynch(g);
		if (BottomGUI.numMiss() >= 1)
			drawHead(g);
		if (BottomGUI.numMiss() >= 2)
			drawBody(g);
		 if (BottomGUI.numMiss() >= 3)
			drawLeftLeg(g);
		 if (BottomGUI.numMiss() >= 4)
			drawRightLeg(g);
		 if (BottomGUI.numMiss() >= 5)
			drawLeftArm(g);
		 if (BottomGUI.numMiss() >= 6)
			drawRightArm(g);
		
	}
}
