import java.io.*;
import java.util.*;

public class TicTacToe2 {

  private char[][] tictactoe; // Two Dimensional Array 
  private int nextturn;
  private String[] users;
  int countmoves;
  final static private char[] moves = {'X','O'};

  // Initialize Values
  public TicTacToe2(String user1, String user2) {

    tictactoe = new char[3][3];
    for (int i=0;i < 3; i++)
      for (int j=0; j < 3; j++)
        tictactoe[i][j] = '_';
        nextturn = 0;
        countmoves = 0;
        users = new String[2];
        users[0] = user1;
        users[1] = user2;
  }

  // Prints out the playing board.
  public void publishTictactoe() {

    System.out.println("\tTic Tac Toe ");
    System.out.println("\t0  1  2");
    for (int i=0; i<3; i++) {
      System.out.print(i+"\t");
      for (int j=0; j<3; j++)
        System.out.print(tictactoe[i][j]+"  ");
      System.out.println();
    }
  }
  
  // Validate the Move made by USer
  public boolean validateMove(int row, int column) {

    if ( (tictactoe[row][column] == '_') && inbounds(row,column) ) {
      tictactoe[row][column] = moves[nextturn];
      countmoves++;
      return true;
    }
    else
      return false;
  }

  // Validate Inbound values
  public boolean inbounds(int row, int column) {

    if ((row < 0) || (column < 0))
      return false;
    if ((row > 2) || (column > 2))
      return false;
    return true;
  }

  // Changes whose turn it is.
  public void shiftTurn() {
    nextturn = (nextturn + 1)%2;
  }

  // Returns the current player's name.
  public String findCurrentUser() {
    return users[nextturn];
  }
 // Checks to see if all the characters in a single character array are
  // the same.
  private static boolean ValidateArray(char[] word) {

    char check = word[0];
    for (int i=1; i<word.length; i++)
      if (check != word[i])
        return false;
    return true;
  }
  
  // Check for 3 consecutive X or 0 in a given row
  public char ChooseWin() {

    for (int i=0; i<3; i++)
      if (ValidateArray(tictactoe[i]) && tictactoe[i][0] != '_')
        return tictactoe[i][0];

	// Check for three X's or O's in a column.
    for (int i=0; i<3; i++)
      if ((tictactoe[0][i] == tictactoe[1][i]) && (tictactoe[1][i] == tictactoe[2][i]) && tictactoe[0][i] != '_')
         return tictactoe[0][i];

	if ((tictactoe[0][0] == tictactoe[1][1]) && (tictactoe[1][1] == tictactoe[2][2]))
         return tictactoe[0][0];

	if ((tictactoe[2][0] == tictactoe[1][1]) && (tictactoe[1][1] == tictactoe[0][2]))
         return tictactoe[2][0];

    if (countmoves == 9)
      return 'W';

    return '_';
  }
  // Returns the player who's playing piece is the character x.
  public String nextTurn(char x) {
    for (int i=0; i<2; i++)
      if (x == moves[i])
        return users[i];
    return "Dummy";
  }

  public static void main(String[] args) throws IOException {

    Scanner stdin = new Scanner(System.in);
    
    // Read in players' names.
    System.out.println("Player 1, enter your name.");
    String name1 = stdin.next();
    System.out.println(name1+" You have been assigned X");

    System.out.println("Player 2, enter your name.");
    String name2 = stdin.next();
   
    System.out.println(name2+" You have been assigned O");

    // Create the TicTacToe object.
    TicTacToe2 mygame = new TicTacToe2(name1, name2);
    
    try{
        // Play as long as there is no winner or tie.
    while (mygame.ChooseWin() == '_') {

      int r,c;
      boolean done = false;

      // Read in a move & check if it's valid.
      do {
	mygame.publishTictactoe();

        System.out.print(mygame.findCurrentUser());            
        System.out.print(" Choose the Row(0-2) for your move.");

      	r = stdin.nextInt();
        System.out.print(mygame.findCurrentUser());
        System.out.print(" Choose the Column(0-2) for your move.");

        c = stdin.nextInt();	
        if (!mygame.inbounds(r,c)) 
          System.out.println("These are invalid entries.");
        else {
          if (!mygame.validateMove(r,c))
            System.out.println("This Spot is taken.");
          else
            done = true;
       }  
      } while (!done);
	
      // Change who's turn it is.
      mygame.shiftTurn();
    }

    // Print out a message with the winner.
    mygame.publishTictactoe();
    char win = mygame.ChooseWin();

    if (win == 'W')
      System.out.println("The Game is Tied");
    else {
      System.out.print("Congratulations, " + mygame.nextTurn(win));
      System.out.println(", you won the game.");
    }

    } catch(Exception E){
        System.out.print("An Exception Occurred, Please start the Game Again ");
    }
    
  }
}
