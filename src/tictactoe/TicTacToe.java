package tictactoe;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();
    private char[][] board = new char[3][3];
    private char current = 'X';

    public void run() {
        while (true) {
            System.out.print("Input command: ");
            String[] command = scanner.nextLine().split("\\s");

            if ("exit".equals(command[0])) {
                break;
            }

            if (command.length != 3) {
                System.out.println("Bad parameters!");
                continue;
            }

            Player player1 = PlayerFactory.createPlayer(command[1], 'X');
            Player player2 = PlayerFactory.createPlayer(command[2], 'O');

            initBoard();
            printBoard();

            while (true) {
                player1.move(board);
                printBoard();

                if (hasWon(player1)) {
                    System.out.println(player1.getSelf() + " wins");
                    break;
                }

                if (isDraw(player1, player2)) {
                    System.out.println("Draw");
                    break;
                }

                player2.move(board);
                printBoard();

                if (hasWon(player2)) {
                    System.out.println(player2.getSelf() + " wins");
                    break;
                }
            }
        }
    }

    public void initBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void printBoard() {
        System.out.println("---------");

        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                System.out.print(" ");
            }
            System.out.println("|");
        }

        System.out.println("---------");
    }

    /*private String checkStatus(Player player1, Player player2) {
        if (isBoardFull() && !hasWon(player1) && hasWon(player2)) {
            return
        }
    }*/

    public boolean hasWon(Player player) {
        /*boolean diagonalWin = (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[2][2] == player.getSign()) ||
                (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[2][0] == player.getSign());

        if (diagonalWin) {
            return true;
        }*/

        boolean negativeDiagonalWin = true;
        boolean positiveDiagonalWin = true;

        for (int i = 0; i < 3; i++) {
            boolean horizontalWin = true;
            boolean verticalWin = true;

            negativeDiagonalWin = negativeDiagonalWin && (board[i][i] == player.getSelf());
            positiveDiagonalWin = positiveDiagonalWin && (board[i][2 - i] == player.getSelf());

            for (int j = 0; j < 3; j++) {
                horizontalWin = horizontalWin && (board[i][j] == player.getSelf());
                verticalWin = verticalWin && (board[j][i] == player.getSelf());
            }

            if (horizontalWin) {
                return true;
            }

            if (verticalWin) {
                return true;
            }
        }

        return negativeDiagonalWin || positiveDiagonalWin;
    }

    public boolean isBoardFull() {
        int blankCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    blankCount += 1;
                }
            }
        }
        return blankCount == 0;
    }

    public boolean isDraw(Player player1, Player player2) {
        return isBoardFull() && !hasWon(player1) && !hasWon(player2);
    }

    /*public boolean isNotFinished() {
        char[][] copy = new char[3][];

        for (int i = 0; i < 3; i++) {
            copy[i] = board[i].clone();
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (copy[i][j] == ' ') {
                    copy[i][j] = current;
                }
            }
        }

        return !isBoardFull() && !hasWon(current);
    }*/
}
