package tictactoe;

import java.util.*;

public class MediumComputerPlayer extends ComputerPlayer {

    public MediumComputerPlayer(char sign) {
        super(sign);
        difficulty = "medium";
    }

    @Override
    protected Coordinate getNextMove(char[][] board) {
        Deque<Coordinate> coordinateOptions = new ArrayDeque<>();

        for (int i = 0; i < 3; i++) {
            List<Integer> rowOptions = new ArrayList<>(Arrays.asList(0, 1, 2));
            List<Integer> colOptions = new ArrayList<>(Arrays.asList(0, 1, 2));
            List<Integer> oppRowOptions = new ArrayList<>(Arrays.asList(0, 1, 2));
            List<Integer> oppColOptions = new ArrayList<>(Arrays.asList(0, 1, 2));

            int horizontalCount = 0;
            int verticalCount = 0;
            int oppHorizontalCount = 0;
            int oppVerticalCount = 0;

            for (int j = 0; j < 3; j++) {

                if (board[i][j] == self) {
                    horizontalCount += 1;
                    colOptions.remove(Integer.valueOf(j));
                }

                if (board[i][j] == opponent) {
                    oppHorizontalCount += 1;
                    oppColOptions.remove(Integer.valueOf(j));
                }

                if (board[j][i] == self) {
                    verticalCount += 1;
                    rowOptions.remove(Integer.valueOf(j));
                }

                if (board[j][i] == opponent) {
                    oppVerticalCount += 1;
                    oppRowOptions.remove(Integer.valueOf(j));
                }
            }

            if (colOptions.size() == 1) {
                coordinateOptions.offerLast(new Coordinate(i, colOptions.get(0)));
            }

            if (rowOptions.size() == 1) {
                coordinateOptions.offerLast(new Coordinate(rowOptions.get(0), i));
            }

            if (oppColOptions.size() == 1) {
                coordinateOptions.offerFirst(new Coordinate(i, oppColOptions.get(0)));
            }

            if (oppRowOptions.size() == 1) {
                coordinateOptions.offerFirst(new Coordinate(oppRowOptions.get(0) , i));
            }
        }

        int negDiagonalCount = 0;
        int posDiagonalCount = 0;
        int oppNegDiagonalCount = 0;
        int oppPosDiagonalCount = 0;

        List<Integer> negDiagonalOptions = new ArrayList<>(Arrays.asList(0, 1, 2));
        List<Integer> posDiagonalOptions = new ArrayList<>(Arrays.asList(0, 1, 2));
        List<Integer> oppNegDiagonalOptions = new ArrayList<>(Arrays.asList(0, 1, 2));
        List<Integer> oppPosDiagonalOptions = new ArrayList<>(Arrays.asList(0, 1, 2));

        for (int i = 0; i < 3; i++) {
            if (board[i][i] == self) {
                negDiagonalCount += 1;
                negDiagonalOptions.remove(Integer.valueOf(i));
            }

            if (board[i][i] == opponent) {
                oppNegDiagonalCount += 1;
                oppNegDiagonalOptions.remove(Integer.valueOf(i));
            }

            if (board[i][2 - i] == self) {
                posDiagonalCount += 1;
                posDiagonalOptions.remove(Integer.valueOf(i));
            }

            if (board[i][2 - i] == opponent) {
                oppPosDiagonalCount += 1;
                oppPosDiagonalOptions.remove(Integer.valueOf(i));
            }
        }

        if (negDiagonalOptions.size() == 1) {
            coordinateOptions.offerLast(new Coordinate(negDiagonalOptions.get(0), negDiagonalOptions.get(0)));
        }

        if (posDiagonalOptions.size() == 1) {
            coordinateOptions.offerLast(new Coordinate(posDiagonalOptions.get(0), 2 - posDiagonalOptions.get(0)));
        }

        if (oppNegDiagonalOptions.size() == 1) {
            coordinateOptions.offerFirst(new Coordinate(oppNegDiagonalOptions.get(0), oppNegDiagonalOptions.get(0)));
        }

        if (oppPosDiagonalOptions.size() == 1) {
            coordinateOptions.offerFirst(new Coordinate(oppPosDiagonalOptions.get(0), 2 - oppPosDiagonalOptions.get(0)));
        }

        coordinateOptions.removeIf(x -> isCellOccupied(board, x));
        if (coordinateOptions.isEmpty()) {
            return super.getNextMove(board);
        } else {
            return coordinateOptions.pollFirst();
        }
    }
}
