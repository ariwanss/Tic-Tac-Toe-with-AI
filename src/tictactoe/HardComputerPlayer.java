package tictactoe;

import java.util.ArrayList;
import java.util.List;

public class HardComputerPlayer extends ComputerPlayer {

    public HardComputerPlayer(char sign) {
        super(sign);
        difficulty = "hard";
    }

    @Override
    protected Coordinate getNextMove(char[][] board) {

        List<Coordinate> possibilities = getPossibleMoves(board);
        System.out.println(possibilities);
        int bestScore = Integer.MIN_VALUE;
        Coordinate nextMove = super.getNextMove(board);

        for (Coordinate possibility : possibilities) {
            board[possibility.getRow()][possibility.getColumn()] = self;
            int score = minimax(board, self, opponent);
            board[possibility.getRow()][possibility.getColumn()] = ' ';
            if (score > bestScore) {
                bestScore = score;
                nextMove = possibility;
            }
        }
        
        return nextMove;
    }

    private int minimax(char[][] board, char player, char opponent) {
        Integer result = checkTerminalState(board, self, this.opponent);

        if (result != null) {
            return result;
        }

        List<Coordinate> possibilities = getPossibleMoves(board);
        int bestScore;

        if (player == self) {
            bestScore = Integer.MAX_VALUE;

            for (Coordinate possibility : possibilities) {
                board[possibility.getRow()][possibility.getColumn()] = opponent;
                int score = minimax(board, opponent, player);
                board[possibility.getRow()][possibility.getColumn()] = ' ';
                bestScore = Math.min(bestScore, score);
            }

        } else {
            bestScore = Integer.MIN_VALUE;

            for (Coordinate possibility : possibilities) {
                board[possibility.getRow()][possibility.getColumn()] = opponent;
                int score = minimax(board, opponent, player);
                board[possibility.getRow()][possibility.getColumn()] = ' ';
                bestScore = Math.max(bestScore, score);
            }
        }

        return bestScore;
    }

    private List<Coordinate> getPossibleMoves(char[][] board) {
        List<Coordinate> possibilities = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Coordinate coordinate = new Coordinate(i, j);
                if (!isCellOccupied(board, coordinate)) {
                    possibilities.add(coordinate);
                }
            }
        }

        return possibilities;
    }

    private Integer checkTerminalState(char[][] board, char self, char opponent) {
        if (hasWon(board, self)) {
            return 10;
        } else if (hasWon(board, opponent)) {
            return -10;
        } else if (isDraw(board, self, opponent)){
            return 0;
        }
        return null;
    }

    private boolean hasWon(char[][] board, char player) {
        boolean negativeDiagonalWin = true;
        boolean positiveDiagonalWin = true;

        for (int i = 0; i < 3; i++) {
            boolean horizontalWin = true;
            boolean verticalWin = true;

            negativeDiagonalWin = negativeDiagonalWin && (board[i][i] == player);
            positiveDiagonalWin = positiveDiagonalWin && (board[i][2 - i] == player);

            for (int j = 0; j < 3; j++) {
                horizontalWin = horizontalWin && (board[i][j] == player);
                verticalWin = verticalWin && (board[j][i] == player);
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

    private boolean isBoardFull(char[][] board) {
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

    private boolean isDraw(char[][] board, char player1, char player2) {
        return isBoardFull(board) && !hasWon(board, player1) && !hasWon(board, player2);
    }
}
