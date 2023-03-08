import java.util.Scanner;

public class Main {

    private static final int BOARD_SIZE = 3;
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    private static char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
    private static char currentPlayer = PLAYER_X;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        initializeBoard();
        printBoard();

        while (!isGameOver()) {
            makeMove();
            printBoard();
            switchPlayer();
        }

        printResult();
    }

    private static void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = '-';
            }
        }
    }

    private static void printBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void makeMove() {
        System.out.println("Player " + currentPlayer + ", it's your turn.");
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter row number (1-3): ");
            int row = scanner.nextInt() - 1;

            System.out.print("Enter column number (1-3): ");
            int col = scanner.nextInt() - 1;

            if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
                System.out.println("Invalid input. Try again.");
            } else if (board[row][col] != '-') {
                System.out.println("That cell is already occupied. Try again.");
            } else {
                board[row][col] = currentPlayer;
                validInput = true;
            }
        }
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
    }

    private static boolean isGameOver() {
        return hasWinner() || isBoardFull();
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean hasWinner() {
        // Check rows
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < BOARD_SIZE; j++) {
            if (board[0][j] != '-' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }

        if (board[2][0] != '-' && board[2][0] == board[1][1] && board[1][1] == board[0][2]) {
            return true;
        }

        return false;
    }

    private static void printResult() {
        if (hasWinner()) {
            switchPlayer();
            System.out.println("Player " + currentPlayer + " has won!");
        } else {
            System.out.println("The game is a tie.");
        }
    }
}
