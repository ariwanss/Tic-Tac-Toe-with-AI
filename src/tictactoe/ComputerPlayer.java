package tictactoe;

import java.util.Random;

public class ComputerPlayer extends Player {

    private static final Random random = new Random();
    protected static String difficulty = "easy";

    public ComputerPlayer(char sign) {
        super(sign);
    }

    @Override
    public void move(char[][] board) {
        System.out.println("Making move level \"" + difficulty + "\"");
        Coordinate coordinate = getNextMove(board);

        board[coordinate.getRow()][coordinate.getColumn()] = self;
    }

    protected Coordinate getNextMove(char[][] board) {
        while (true) {
            int row = random.nextInt(3);
            int col = random.nextInt(3);
            if (!isCellOccupied(board, new Coordinate(row, col))) {
                return new Coordinate(row, col);
            }
        }
    }
}
