/**
 * The main board of the TicTacToe game
 */


public class Board {
    private String[][] board;
    
    public Board() {
        this.board = new String[3][3];
        resetBoard();
    }

    /**
     * Inner class used to return a tuple value
     */
    private class Pair {
        int val1;
        int val2;

        private Pair(int v1, int v2) {
            val1 = v1;
            val2 = v2;
        }
    }

    /**
     * @param position the cell position 1-9 of the board
     * @param piece the piece, either X or O
     * @return true if the move was valid, otherwise false
     */
    public boolean makeMove(int position, String piece) {
        if (position < 1 || position > 9) return false;
       
        Pair positions = convertPositionTo2dPosition(position);
        int row = positions.val1;
        int col = positions.val2;

        String cellValue = this.board[row][col];
        if (isValid(cellValue)) {
            this.board[row][col] = piece;
            return true;
        }
        return false;
    }

    /**
     * Converts a 1-dimensional position to a 2-dimensional
     * @param position the position in the 1-dimensional array (1-9)
     * @return the corresponding position in 2D space
     */
    private Pair convertPositionTo2dPosition(int position) {
        int row = (position - 1) / 3;
        int col = (position - 1) % 3;
        return new Pair(row, col);
    }
    /**
     * Checks if a cell is unoccupied
     * @param cellValue the current value in the cell
     * @return true if cell is empty otherwise false
     */
    public boolean isValid(String cellValue) {
        return cellValue.equals(" ");
    }

    /**
     * Victory checker
     * @param position the position of the last move
     * @param piece the last piece that was added to the board
     * @return true if victory has been achieved, false otherwise
     */
    public boolean checkVictory(int position, String piece) {
        return checkHorizontal(position, piece) ||
               checkVertical(position, piece)   ||
               checkDiagonals(position, piece);
               

    }

    /**
     * Checks that moves are avaialble
     * @return true if not all cells are occupied, false otherwise
     */
    public boolean movesExist() {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (this.board[i][j].equals(" ")) return true;
            }
        }
        System.out.println("No more moves exist! It's a draw.");
        return false;
    }

    private boolean checkHorizontal(int position, String piece) {
        Pair positions = convertPositionTo2dPosition(position);
        int row = positions.val1;
        int col = positions.val2;

        switch(col) {
            case 0:
                return this.board[row][col + 1].equals(piece) && this.board[row][col + 2].equals(piece);
            case 1:
                return this.board[row][col - 1].equals(piece) && this.board[row][col + 1].equals(piece);
            case 2:
                return this.board[row][col - 1].equals(piece) && this.board[row][col - 2].equals(piece);
            default:
                return false;
        }

    }

    // Help functions for the victory check function
    private boolean checkVertical(int position, String piece) {
        Pair positions = convertPositionTo2dPosition(position);
        int row = positions.val1;
        int col = positions.val2;

        switch(row) {
            case 0:
                return this.board[row + 1][col].equals(piece) && this.board[row + 2][col].equals(piece);
            case 1:
                return this.board[row - 1][col].equals(piece) && this.board[row + 1][col].equals(piece);
            case 2:
                return this.board[row - 1][col].equals(piece) && this.board[row - 2][col].equals(piece);
            default:
                return false;
        }

    }
    
    private boolean checkDiagonals(int position, String piece) {
        if (position == 2 || position == 4 || position == 6 || position == 8) return false;
        Pair positions = convertPositionTo2dPosition(position);
        int row = positions.val1;
        int col = positions.val2;

        switch(position) {
            case 1:
                return this.board[row + 1][col + 1].equals(piece) && this.board[row + 2][col + 2].equals(piece);
            case 3:
                return this.board[row + 1][col - 1].equals(piece) && this.board[row + 2][col - 2].equals(piece);
            case 5:
                return this.board[row - 1][col - 1].equals(piece) && this.board[row + 1][col + 1].equals(piece)
                    || this.board[row - 1][col + 1].equals(piece) && this.board[row + 1][col -1 ].equals(piece);
            case 7:
                return this.board[row - 1][col + 1].equals(piece) && this.board[row - 2][col + 2].equals(piece);
            case 9:
                return this.board[row -1][col - 1].equals(piece) && this.board[row - 2][col - 2].equals(piece);
            default:
                return false; 
        }

    }

    public void resetBoard() {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.board[i][j] = " ";
            }
        }
    }

    public void printBoard() {
        System.out.println("|-----------------|");
        System.out.println("|  " + board[0][0] + "  |  " + board[0][1] + "  |  " + board[0][2] + "  |");
        System.out.println("|-----------------|");
        System.out.println("|  " + board[1][0] + "  |  " + board[1][1] + "  |  " + board[1][2] + "  |");
        System.out.println("|-----------------|");
        System.out.println("|  " + board[2][0] + "  |  " + board[2][1] + "  |  " + board[2][2] + "  |");
        System.out.println("|-----------------|");
    }
}