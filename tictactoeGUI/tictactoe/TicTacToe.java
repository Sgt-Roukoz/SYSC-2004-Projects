import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A class modelling a tic-tac-toe (noughts and crosses, Xs and Os) game using
 * a GUI created with Java Swing components
 * 
 * @author Marwan Zeid
 * @version April 2, 2022
 */
public class TicTacToe
{
    public static final String PLAYER_X = "X"; // player using "X"
    public static final String PLAYER_O = "O"; // player using "O"
    public static final String EMPTY = " ";  // empty cell
    public static final String TIE = "T"; // game ended in a tie

    private String player;   // current player (PLAYER_X or PLAYER_O)

    private String winner;   // winner: PLAYER_X, PLAYER_O, TIE, EMPTY = in progress

    private int numFreeSquares; // number of squares still free

    private JButton board[][]; // 3x3 array representing the board

    private JLabel currPlayer; // text area to print game status

    private JFrame f; // base window for Tic Tac Toe game

    private Container p; // container holding 3x3 ttt board

    private JMenuBar menu; // menubar holding Game menu

    private ActionListener button, newGame, quit; // action lister to implement button changing
    
    private final int SHORTCUT_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx(); //to save typing

    /** 
     * Constructs a new Tic-Tac-Toe board.
     */
    public TicTacToe()
    {
        // set up board
        board = new JButton[3][3];
        numFreeSquares = 9;

        // set up labels
        currPlayer = new JLabel();

        // set up action listeners
        setUpActionListeners();

        // set up menubar and items
        menu = new JMenuBar();
        JMenu game = new JMenu("Game");
        menu.add(game);

        JMenuItem newGameItem = new JMenuItem("New");
        newGameItem.addActionListener(newGame);
        newGameItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, SHORTCUT_MASK));
        game.add(newGameItem);
        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(quit);
        quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
        game.add(quitItem);

        // set up container for board
        p = new JPanel();
        p.setLayout(new GridLayout(3,3));

        int count = 1;

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                board[i][j] = new JButton();
                board[i][j].addActionListener(button);
                p.add(board[i][j]);
                count++;
            }
        }

        // set up frame
        f = new JFrame("TIC-TAC-TOE");
        f.setJMenuBar(menu);
        f.add(currPlayer, BorderLayout.SOUTH);
        f.add(p, BorderLayout.CENTER);

        f.pack();

        f.setSize(300,300);
        f.setVisible(true);
        clearBoard();
    }

    /**
     * Sets up action listeners used by buttons and menu items in the game window
     */
    private void setUpActionListeners()
    {
        newGame = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearBoard();
            }
        };
        
        button = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                takeTurn((JButton)e.getSource());
                
                if (!winner.equals(EMPTY))
                {
                    endGame();
                }
            }
        };
        
        quit = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                quit();
            }
        };
    }

    /**
     * Primary function which modifies button and controls the game. Sets the buttons label to the player who clicked it, checks win condition,
     * and changes the label that indicates player turn.
     */
    private void takeTurn(JButton button)
    {
        if (winner == EMPTY)
        {
            button.setEnabled(false);
            button.setText(player);
            numFreeSquares--;

            // see if the game is over
            if (findButton(button)) 
            {
                winner = player; // must be the player who just went
            }
            else if (numFreeSquares==0)
            {
                winner = TIE; // board is full so it's a tie
            }
            else
            {
                if (player == PLAYER_X){
                    player = PLAYER_O;
                } 
                else{
                    player = PLAYER_X;
                }
            }
            currPlayer.setText("Player " + player + "'s Turn!");
        }
    }
    
    /**
     * Modifies game window accordingly to signify game ending, if there are blank squares when game ended, turns them black
     * It also disables all buttons and shows the winner of the game if there was one, otherwise it's a TIE.
     */
    private void endGame()
    {
        System.out.println("Ending Game");
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton square = board[i][j];
                if (square.getText().equals(EMPTY)){
                    square.setEnabled(false);
                    square.setBackground(Color.BLACK);
                }
            }
        }
        
        if (!winner.equals(TIE))
        {
            currPlayer.setText("Player " + player + " is the winner!");
            System.out.println("Player " + player + " is the winner!");
        }
        else
        {
            currPlayer.setText("It's a TIE!");
            System.out.println("It's a TIE!");
        }
    }

    /**
     * Returns true if filling the given square gives us a winner, and false
     * otherwise.
     *
     * @param int row of square just set
     * @param int col of square just set
     * 
     * @return true if we have a winner, false otherwise
     */
    private boolean haveWinner(int row, int col) 
    {
        // unless at least 5 squares have been filled, we don't need to go any further
        // (the earliest we can have a winner is after player X's 3rd move).

        if (numFreeSquares>4) return false;

        // Note: We don't need to check all rows, columns, and diagonals, only those
        // that contain the latest filled square.  We know that we have a winner 
        // if all 3 squares are the same, as they can't all be blank (as the latest
        // filled square is one of them).
        
        System.out.println("Checking");

        // check row "row"
        if ( board[row][0].getText().equals(board[row][1].getText()) &&
        board[row][0].getText().equals(board[row][2].getText()) ) return true;

        // check column "col"
        if ( board[0][col].getText().equals(board[1][col].getText()) &&
        board[0][col].getText().equals(board[2][col].getText()) ) return true;

        // if row=col check one diagonal
        if (row==col)
            if ( board[0][0].getText().equals(board[1][1].getText()) &&
            board[0][0].getText().equals(board[2][2].getText()) ) return true;

        // if row=2-col check other diagonal
        if (row==2-col)
            if ( board[0][2].getText().equals(board[1][1].getText()) &&
            board[0][2].getText().equals(board[2][0].getText()) ) return true;

        // no winner yet
        return false;
    }

    /**
     * Sets everything up for a new game.  Marks all squares in the Tic Tac Toe board as empty,
     * and indicates no winner yet, 9 free squares and the current player is player X.
     */
    private void clearBoard()
    {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].setText(EMPTY);
                board[i][j].setEnabled(true);
                board[i][j].setBackground(Color.WHITE);
            }
        }

        winner = EMPTY;
        numFreeSquares = 9;
        player = PLAYER_X;     // Player X always has the first turn.

        currPlayer.setText("Player X's Turn!");
    }

    /**
     * FInds the index of the button that was just clicked and returns whether or not a winner is found.
     * 
     * @return Returns true if the button that was just clicked result in a win, false otherwise.
     */
    private boolean findButton(JButton button) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == button)
                {
                    return haveWinner(i, j);
                }
            }
        }

        return false;
    }
    
    /**
     * Quits game window
     */
    private void quit() {
        f.dispose(); // exit tic tac toe window
    }
}
