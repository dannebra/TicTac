import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.FileReader;

/**
 * @author      Daniel Degirmen dannebra92@gmail.com
 * @version     1.0
 * @since       1.0 
 */

 /**
  * A simple TicTacToe game using a naive AI 
  */
  
public class TicTac {
    private Board board;
    private int crossWins = 0;
    private int circleWins = 0;
    private Scanner input;
    private Random randomNumber = new Random();
    private boolean gameOver = false;

    public TicTac() {
        this.board = new Board();
        this.input = new Scanner(System.in);
    }

    private void playerTurn() {
        System.out.println("Player turn\n");
            while (true) {
                try {
                    int move = input.nextInt();
                    if (move < 1 || move > 9) {
                        System.out.println("Number has to be between 1 and 9.");
                    }
                    else {
                        if (this.board.makeMove(move, "X")) {
                            if (this.board.checkVictory(move, "X")) {
                                gameOver = true;
                            }
                            break;
                        }
                        System.out.println("Illegal move! Space is occupied.");
                    }
                } catch (InputMismatchException exception) {
                    System.out.println("Only numbers between 1 and 9 are allowed!");
                    input.nextLine();
                }
            }
    }

    private void cpuTurn() {
        System.out.println("Computer turn\n");
        int move = randomNumber.nextInt((9 + 1) - 1) + 1;
        while(true) {
            if (this.board.makeMove(move, "O")) {
                if (this.board.checkVictory(move, "O")) {
                    gameOver = true;
                }
                break; 
            }
            move = randomNumber.nextInt((9 + 1) - 1) + 1;
        }
    }

    public void welcomeMessage() {
        String file = "welcome.txt";
        FileReader fr = null;
        StringBuffer buf = null;
        int character;

        try {
            buf = new StringBuffer();
            fr = new FileReader(file);
            while((character = fr.read()) != -1) {
                buf.append((char) character);
            }

        } catch( IOException ioe ) {
            ioe.printStackTrace();
        }
        finally {
            if(fr != null) {
              try {
                fr.close();
              }
              catch( IOException ioe ) {
              }
            }
          }
          if( buf != null )
            System.out.println(buf);
        
        System.out.println("Welcome to TicTacToe!" + "\n" + 
                            "Type a number between 1 and 9. You are the X pieces.\n");
        this.board.printBoard();
        play();
    }

    public void play() {
        while(true) {
            playerTurn();
            if (gameOver){
                this.crossWins++;
                this.board.printBoard();
                System.out.println("You win!");
                break;
            }
            this.board.printBoard();
            if (!this.board.movesExist()) break;

            cpuTurn();
            if(gameOver) {
                this.circleWins++;
                this.board.printBoard();
                System.out.println("You lose!");
                break;
            }
            if (!this.board.movesExist()) break;
            this.board.printBoard();
        }
        askPlayAgain();
    }

    private void askPlayAgain() {
        System.out.println("Cross wins: " + this.crossWins + "\n" + "Circle wins: " + this.circleWins + "\n");
        System.out.println("Would you like to play again? Y/N.");
        input.nextLine();

        try {
            String answer = input.nextLine();
            if (answer.toLowerCase().equals("y")) {
                board.resetBoard();
                this.board.printBoard();
                gameOver = false;
                play();
            }
            else {
                System.out.println("Thanks for playing! Final score: \n" + "Cross: " + crossWins + "\n"
                                                                         +  "Circle: " + circleWins);
            } 


        } catch (InputMismatchException e) {
            System.out.println("I did not understand your answer.");
            input.next();
        }
    }
}