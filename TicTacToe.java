import java.util.*;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] board = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};
        boolean playerTurn = true;

        System.out.println("Welcome to Tic Tac Toe!");
        printBoard(board);

        while (true) {
            if (playerTurn) {
                System.out.println("Player's turn (X): Enter a number (1-9):");
                int move = sc.nextInt();
                if (makeMove(board, move, 'X')) {
                    printBoard(board);
                    if (checkWin(board, 'X')) {
                        System.out.println("Congratulations! Player wins!");
                        break;
                    }
                    playerTurn = false;
                } else {
                    System.out.println("Invalid move! Try again.");
                }
            } else {
                System.out.println("Computer's turn (O):");
                computerMove(board);
                printBoard(board);
                if (checkWin(board, 'O')) {
                    System.out.println("Computer wins! Better luck next time.");
                    break;
                }
                playerTurn = true;
            }

            if (isBoardFull(board)) {
                System.out.println("It's a tie!");
                break;
            }
        }
        System.out.println("Game over. Thanks for playing!");
    }

    public static void printBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean makeMove(char[][] board, int move, char symbol) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == (char) (move + '0')) {
                    board[i][j] = symbol;
                    return true;
                }
            }
        }
        return false;
    }

    public static void computerMove(char[][] board) {
        // Try to win
        if (findWinningMove(board, 'O')) {
            return;
        }
        // Block the player from winning
        if (findWinningMove(board, 'X')) {
            return;
        }
        // Try a tricky move (taking the center if available)
        if (board[1][1] != 'X' && board[1][1] != 'O') {
            board[1][1] = 'O';
            return;
        }
        // Default move: pick the first available spot
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != 'X' && board[i][j] != 'O') {
                    board[i][j] = 'O';
                    return;
                }
            }
        }
    }

    public static boolean findWinningMove(char[][] board, char symbol) {
        for (int i = 0; i < 3; i++) {
            // Check rows
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] != 'X' && board[i][2] != 'O') {
                board[i][2] = 'O';
                return true;
            }
            if (board[i][0] == symbol && board[i][2] == symbol && board[i][1] != 'X' && board[i][1] != 'O') {
                board[i][1] = 'O';
                return true;
            }
            if (board[i][1] == symbol && board[i][2] == symbol && board[i][0] != 'X' && board[i][0] != 'O') {
                board[i][0] = 'O';
                return true;
            }
            // Check columns
            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] != 'X' && board[2][i] != 'O') {
                board[2][i] = 'O';
                return true;
            }
            if (board[0][i] == symbol && board[2][i] == symbol && board[1][i] != 'X' && board[1][i] != 'O') {
                board[1][i] = 'O';
                return true;
            }
            if (board[1][i] == symbol && board[2][i] == symbol && board[0][i] != 'X' && board[0][i] != 'O') {
                board[0][i] = 'O';
                return true;
            }
        }
        // Check diagonals
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] != 'X' && board[2][2] != 'O') {
            board[2][2] = 'O';
            return true;
        }
        if (board[0][0] == symbol && board[2][2] == symbol && board[1][1] != 'X' && board[1][1] != 'O') {
            board[1][1] = 'O';
            return true;
        }
        if (board[1][1] == symbol && board[2][2] == symbol && board[0][0] != 'X' && board[0][0] != 'O') {
            board[0][0] = 'O';
            return true;
        }
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] != 'X' && board[2][0] != 'O') {
            board[2][0] = 'O';
            return true;
        }
        if (board[0][2] == symbol && board[2][0] == symbol && board[1][1] != 'X' && board[1][1] != 'O') {
            board[1][1] = 'O';
            return true;
        }
        if (board[1][1] == symbol && board[2][0] == symbol && board[0][2] != 'X' && board[0][2] != 'O') {
            board[0][2] = 'O';
            return true;
        }
        return false;
    }    

    public static boolean checkWin(char[][] board, char symbol) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) ||
                (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol)) {
                return true;
            }
        }
        // Check diagonals
        if ((board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
            (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol)) {
            return true;
        }
        return false;
    }

    public static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != 'X' && board[i][j] != 'O') {
                    return false;
                }
            }
        }
        return true;
    }
}