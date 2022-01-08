package tictactoe;

public class Coordinate {
    private final int row;
    private final int column;

    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Coordinate(String pair) {
        String[] coordinates = pair.split("\\s");
        this.row = Integer.parseInt(coordinates[0]) - 1;
        this.column = Integer.parseInt(coordinates[1]) - 1;

        if (row < 0 || row > 2 || column < 0 || column > 2) {
            throw new IllegalArgumentException();
        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }
}
