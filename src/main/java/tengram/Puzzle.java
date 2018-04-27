package tengram;

import tengram.core.Board;
import tengram.core.Piece;
import tengram.arranger.BoardArranger;

public class Puzzle {
    private static Piece[] PIECES = {
            new Piece("A", 4, 3),
            new Piece("B", 2, 5),
            new Piece("C", 1, 8),
            new Piece("D", 7, 2),
            new Piece("E", 5, 5),
            new Piece("F", 9, 2),
            new Piece("G", 1, 3),
            new Piece("H", 1, 2),
            new Piece("I", 2, 1),
            new Piece("J", 2, 3)
    };

    public static void main(String[] args) {
        Board board = new Board(10, 10);
        BoardArranger.arrange(board, PIECES);
        System.out.print(board);
    }
}
