import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A class modelling a tic-tac-toe (noughts and crosses, Xs and Os) game in a very
 * simple GUI window.
 * 
 * @author Lynn Marshall
 * @version November 8, 2012
 * 
 * @author Marwan Zeid
 * @version March 29, 2022
 */

public class TicTacToeFrame extends TicTacToe 
{ 
   private JTextArea status; // text area to print game status
   
   /** 
    * Constructs a new Tic-Tac-Toe board and sets up the basic
    * JFrame containing a JTextArea in a JScrollPane GUI.
    */
   public TicTacToeFrame()
   { 
       super();
       status = new JTextArea();
       status.setEditable(false);
       status.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
       
       JScrollPane scroll = new JScrollPane(status);
       JFrame frame = new JFrame("TIC-TAC-TOE");
       
       frame.setLayout(new GridLayout(1,1));
       frame.add(scroll, BorderLayout.CENTER);
       frame.setSize(300,300);   
       frame.setVisible(true); 
   }
   
   /**
    * Prints the board to the GUI using toString().
    */
    public void print() 
    {  
        status.append(super.toString() + "\n");
    }

}