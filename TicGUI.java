import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicGUI extends JFrame {
    private int size;
    private Container pane;
    private String currentPlayer;
    private JButton[][] board;
    private boolean hasWinner; // mark whether we already have a winner
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem quit;
    private JMenuItem newGame;

    public TicGUI(int size) {
        // calls parent class default constructor
        super();
        this.size = size;
        pane = getContentPane();
        pane.setLayout(new GridLayout(this.size, this.size));
        // pane.setLayout(new GridLayout(this.size, this.size));
        setTitle("Tic Tac Toe");
        setSize(this.size * 100, this.size * 100);
        // setResizable(true);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        currentPlayer = "x";
        board = new JButton[this.size][this.size];
        // board = new JButton[3][3];
        hasWinner = false;
        initializeBoard();
        initializeMenuBar();

    }

    private void initializeMenuBar(){
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        newGame = new JMenuItem("New Game");
        newGame.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				resetBoard();
				
			}});
        quit = new JMenuItem("Quit");
        quit.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });
        menu.add(newGame);
        menu.add(quit);
        menuBar.add(menu);
        // JFrame method
        setJMenuBar(menuBar);
    }
    // reset the play board
    private void resetBoard(){
        currentPlayer = "x";
        hasWinner = false;
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board.length; j++){
                board[i][j].setText("");
            }
        }
    }
    // initialize board
    private void initializeBoard(){
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board.length; j++){
                // if doesn't initialize, just an empty string
                JButton btn = new JButton();
                btn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 100));
                btn.setHorizontalAlignment(SwingConstants.CENTER);
                btn.setVerticalAlignment(SwingConstants.CENTER);
                board[i][j] = btn;
                btn.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
                        // "" means that this place has been occupied, you can't put 
                        // a chess piece there
                        if (((JButton)e.getSource()).getText().equals("") &&
                        hasWinner == false){
                            btn.setText(currentPlayer);
                            // we test whether we have a winner now after placing a new piece
                            hasWinner();
                            togglePlayer();
                        }
                    }
                });
                pane.add(btn);
            }
        }
    }
    // toggle players
    private void togglePlayer(){
        if (currentPlayer.equals("x")){
            currentPlayer = "o";
        }
        else{
            currentPlayer = "x";
        }

    }
    private void hasWinner(){
        // check if any row satisfies winning condition
        boolean win = true;
        for (int i = 0; i < board.length; i++){
            win = true;
            for (int j = 0; j < board.length; j++){
                win = win && board[i][j].getText().equals(currentPlayer);
            }
            if (win == true) {
                hasWinner = true;
                break;
            }
        }
        // check if any column satisfies winning conditions
        if (!hasWinner){
            for (int j = 0; j < board.length; j++){
                win = true;
                for (int i = 0; i < board.length; i++){
                    win = win && board[i][j].getText().equals(currentPlayer);
                }
                if (win == true) {
                    hasWinner = true;
                    break;
                }
            }
        }
        // check if top left to bottom right diagonal satisfies winning conditions
        if (!hasWinner){
            win = true;
            for (int i = 0; i < board.length; i++){
                win = win && board[i][i].getText().equals(currentPlayer);
            }
            if (win == true) {
                hasWinner = true;
            }
        }
        // check if bottom left to top right diagonal satisfies winning conditions
        if (!hasWinner){
            win = true;
            for (int i = 0; i < board.length; i++){
                win = win && board[i][board.length - 1 - i].getText().equals(currentPlayer);
            }
            if (win == true){
                hasWinner = true;
            }
        }
        // if there is a winner, output messsage and reset board to start new game
        if (hasWinner){
            JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won!");
            resetBoard();
        } 
        else {
            // if there's no winner and the game has ended, say -- it's a tie! Start over! and reset board to start new game
            boolean endGame = true;
            for (int i = 0; i < board.length; i++){
                for (int j = 0; j < board.length; j++){
                    endGame = endGame && !board[i][j].getText().equals("");
                }
            }
            if (endGame){
                JOptionPane.showMessageDialog(null, "It's a tie. Start Over!");
                resetBoard();
            }
            
        }
    }
}