package tp1.pack1Revisoes;

import java.util.Scanner;

public class P02FourInaRow {

    /**
     * Shows (prints) the board on the console
     *
     * @param board The board
     */
    private static void showboard(char[][] board) {

        // First Line
        System.out.println("+ ------------------------ +");

        for (int row = 0; row < board.length; row++) {
            // First Column
            System.out.print("|  ");

            for (int col = 0; col < board[row].length; col++) {
                // If the value is a empty char draw "O" else draw its value thats either "A" or
                // "B"
                if (board[row][col] == '\0')
                    System.out.print("O" + "   ");
                else
                    System.out.print(board[row][col] + "   ");

            }
            // Last Column
            System.out.println("|");

        }
        // Last Line
        System.out.println("+ ------------------------ +");

    }

    /**
     * Puts one piece for the received player. First asks the user to choose one
     * column, then validates it and repeat it until a valid column is chosen.
     * Finally, puts the player character on top of selected column.
     *
     * @param player   The player: 'A' or 'B'. Put this character on the board
     * @param board    The board
     * @param keyboard The keyboard Scanner
     * @return The column selected by the user.
     */
    private static int play(char player, char[][] board, Scanner keyboard) {
        System.out.print("Choose a column (Player " + player + "):");
        int col = keyboard.nextInt() - 1;
        // See if input is safe
        while (col > 5 || col < 0 || board[0][col] != '\0') {
            System.out.println("Number chosen is invalid, please try again: ");
            col = keyboard.nextInt() - 1;
        }
        for (int row = board.length - 1; row >= 0; row--) {
            if (board[row][col] == '\0') {
                board[row][col] = player;
                return col;
            }
        }
        return 0;
    }

    /**
     * Checks if the player, with the character on top on the received column, won
     * the game or not. It will get the top move on that column, and check if there
     * are 4 pieces in a row, in relation to that piece and from the same player.
     * Returns true is yes, false is not.
     *
     * @param board The board
     * @param col   The last played column
     * @return True is that player won the game, or false if not.
     */
    private static boolean lastPlayerWon(char[][] board, int col) {

        char player = board[6][col];

        if (board[5][col] != '\0')
            player = board[5][col];

        if (board[4][col] != '\0')
            player = board[4][col];

        if (board[3][col] != '\0')
            player = board[3][col];

        if (board[2][col] != '\0')
            player = board[2][col];

        if (board[1][col] != '\0')
            player = board[1][col];

        if (board[0][col] != '\0')
            player = board[0][col];

        // Vertical Win
        for (int row = 0; row < 4; row++) {
            if (board[row][col] == player && board[row + 1][col] == player && board[row + 2][col] == player
                    && board[row + 3][col] == player) {
                return true;
            }
        }

        // Horizontal Win
        for (int row = 0; row < board.length; row++) {
            for (int rep = 0; rep < 3; rep++) {

                if (board[row][rep] == player && board[row][rep + 1] == player && board[row][rep + 2] == player
                        && board[row][rep + 3] == player) {
                    return true;
                }
            }
        }

        // Diagonal Right Win
        for (int row = board.length - 1; row > 4; row--) {
            for (int rep = 0; rep < 3; rep++) {

                if (board[row][rep] == player && board[row - 1][rep + 1] == player && board[row - 2][rep + 2] == player
                        && board[row - 3][rep + 3] == player) {
                    return true;
                }
            }
        }

        // Diagonal Left Win
        for (int row = board.length - 1; row >= 0; row--) {
            for (int rep = board[row].length - 1; rep > 2; rep--) {
                if (board[row][rep] == player && board[row - 1][rep - 1] == player
                        && board[row - 2][rep - 2] == player
                        && board[row - 3][rep - 3] == player) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check if there are at least one free position on board.
     *
     * @param board The board
     * @return True if there is, at least, one free position on board
     */
    private static boolean existsFreePlaces(char[][] board) {

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                // If the value is a empty char then there are free places
                if (board[row][col] == '\0')
                    return true;
            }
        }
        return false;
    }

    /**
     * Main method - this method should not be changed
     */
    public static void main(String[] args) {
        final int NROWS = 7;
        final int NCOLs = 6;

        // program variables
        Scanner keyboard = new Scanner(System.in);
        char[][] board = new char[NROWS][NCOLs];
        char winner = ' ';

        // show empty board
        showboard(board);

        // game cycle
        do {
            int col = play('A', board, keyboard);
            showboard(board);
            if (lastPlayerWon(board, col)) {
                winner = 'A';
                break;
            }
            if (!existsFreePlaces(board))
                break;

            col = play('B', board, keyboard);
            showboard(board);
            if (lastPlayerWon(board, col)) {
                winner = 'B';
                break;
            }

        } while (existsFreePlaces(board));

        // show final result
        switch (winner) {
            case ' ':
                System.out.println("We have a draw....");
                break;
            case 'A':
                System.out.println("Winner: Player A. Congratulations...");
                break;
            case 'B':
                System.out.println("Winner: Player B. Congratulations...");
                break;
        }

        // close keyboard
        keyboard.close();
    }
}