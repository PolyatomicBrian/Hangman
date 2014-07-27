import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class BottomGUI extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	public static String word = "carrot";
	private static ArrayList<String> listPossWords = new ArrayList<String>();
	
	public static JLabel lblWord = new JLabel();
	
	private ArrayList<String> listChar = new ArrayList<String>();
	private ArrayList<String> listUScore = new ArrayList<String>();
	
	public static ArrayList<String> listGuesses = new ArrayList<String>();
	
	private String strGuess = "";
	
	static int numMissed = 0;
	
	private int numCorrect = 0;
	private int numTotal = 0;
	
	int w;
	int h;
	JPanel gui;
	
	public BottomGUI(int w, int h, JPanel gui){
		
		selectWord();
		
		this.w = w;
		this.h = h;
		this.gui = gui;
		setBackground(Color.LIGHT_GRAY);
		setFocusable(true);
		
		addCharsToList();
		initiateLabel();
		
		numTotal = word.length();
		
		addKeyListener(new KeyGuess());
	}
	
	public void resetGame(){
		listChar.clear();
		listUScore.clear();
		listGuesses.clear();
		listPossWords.clear();
		
		HangmanGUI.lblGuesses.setText("GUESSES: ");
		
		numMissed = 0;
		numCorrect = 0;
		numTotal = 0;
		
		selectWord();
		addCharsToList();
		initiateLabel();
		numTotal = word.length();
		
		gui.repaint();
	}
	
	public void selectWord(){
		Scanner scan;
		//BufferedReader input = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("listofWords.txt")));
		try {
			scan = new Scanner(new File("listofWords.txt"));
			while (scan.hasNextLine()){
				listPossWords.add(scan.nextLine());
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		word = chooseRnd();
		
	}
	
	public String chooseRnd(){
		int x = (int)(listPossWords.size() * Math.random());
		return listPossWords.get(x);
	}
	
	public void addCharsToList(){
		for (int n = 0; n < word.length(); n++){
			listChar.add(word.substring(n,n+1));
		}
		
		for (int n = 0; n < word.length(); n++){
			listUScore.add("_ ");
		}
	//	DEVPRINT();
	}
	
	public void DEVPRINT(){
		for (int n = 0; n < word.length(); n++){
			System.out.println(listChar.get(n));
		}
	}
	
	public void initiateLabel(){
		String temp = "";
		for (int n = 0; n < word.length(); n++){
			temp += listUScore.get(n);
		}
		lblWord.setText(temp);
		lblWord.setFont(new Font("Serif", Font.PLAIN, 48));
		add(lblWord);
		
	}
	
	public void updatelblGuesses(){
		for (int i = 0; i < listGuesses.size(); i++){ //Checks if current guess has been guessed already.
			if (strGuess.equals(listGuesses.get(i))){
				return;
			}
		}
		
		checkIfCorrect();
		
		HangmanGUI.lblGuesses.setText(HangmanGUI.lblGuesses.getText() + strGuess);
	}
	
	public void checkIfCorrect(){
		boolean correct = false;
		for (int i = 0; i < listChar.size(); i++){
			if (strGuess.equals( listChar.get(i))){
				listUScore.set(i, strGuess); //Correct guess. Replaces underscore.
				System.out.println("check");
				correct = true;
				numCorrect++;
				checkUnderScores();
			}
		}
		if (!correct){
			numMissed++;
		}
		listGuesses.add(strGuess); //Adds guess to list, right or wrong.
		
	}
	
	public void checkUnderScores(){
		String temp = "";
		for (int n = 0; n < word.length(); n++){
			temp += listUScore.get(n);
		}
		lblWord.setText(temp);
	}
	
	public boolean isGameOver(){
		if (numMiss() >= 6){ //Lose
			HangmanGUI.lblGuesses.setText("GAME OVER! Press any key to play again.");
			lblWord.setText(word);
			return true;
		}
		if (numCorrect == numTotal){ //Win
			HangmanGUI.lblGuesses.setText("YOU WON! Press any key to play again.");
			lblWord.setText(word);
			return true;
		}
		return false;
	}
	
	public void refreshGUI(){
		revalidate();
		repaint();
		gui.revalidate();
		gui.repaint();
	}
	
	private class KeyGuess implements KeyListener{
		public void keyPressed(KeyEvent e){
			if (!isGameOver() && numMiss() < 7){
				strGuess = "";
				strGuess += e.getKeyChar();
				strGuess = strGuess.toLowerCase();
				System.out.println(strGuess);
			
				updatelblGuesses();
				refreshGUI();
				
				if (isGameOver()){ //Dumb way to check, but, hey, it works and I'm too lazy to fix it. :)
					System.out.println("Canada is cool.");
				}
			}else if (isGameOver()){
				resetGame();
			}

		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}

	public static int numMiss() {
		return numMissed;
	}
	
}
