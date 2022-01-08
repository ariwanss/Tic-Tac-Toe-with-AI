package tictactoe;

import java.util.Scanner;

public class HumanPlayer extends Player {

    private static final Scanner scanner = new Scanner(System.in);

    public HumanPlayer(char sign) {
        super(sign);
    }

    @Override
    public void move(char[][] board) {
        while (true) {
            try {
                System.out.print("Enter the coordinates: ");
                Coordinate coordinate = new Coordinate(scanner.nextLine());

                if (isCellOccupied(board, coordinate)) {
                    System.out.println("This cell is occupied! Choose another one!");
                    continue;
                }

                board[coordinate.getRow()][coordinate.getColumn()] = self;
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("You should enter numbers!");
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
            } catch (IllegalArgumentException e) {
                System.out.println("Coordinates should be from 1 to 3!");
            }
        }
    }
}
