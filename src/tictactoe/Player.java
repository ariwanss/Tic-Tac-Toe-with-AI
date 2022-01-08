package tictactoe;

abstract class Player {

    protected char self;
    protected char opponent;

    abstract void move(char[][] board);

    public Player(char sign) {
        this.self = sign;
        opponent = sign == 'X' ? 'O' : 'X';
    }

    protected boolean isCellOccupied(char[][] board, Coordinate coord) {
        return board[coord.getRow()][coord.getColumn()] != ' ';
    }

    public char getSelf() {
        return self;
    }
}
