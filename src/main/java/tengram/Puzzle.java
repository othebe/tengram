package tengram;

import tengram.core.Board;
import tengram.core.Piece;
import tengram.arranger.BoardArranger;

public class Puzzle {
    private static Piece[] PIECES = {
            new Piece("A", 2, 9),
            new Piece("B", 2, 1),
            new Piece("C", 2, 5),
            new Piece("D", 7, 2),
            new Piece("E", 2, 3),
            new Piece("F", 5, 5),
            new Piece("G", 4, 3),
            new Piece("H", 1, 3),
            new Piece("I", 1, 2),
            new Piece("J", 1, 8)
    };

    public static void main(String[] args) {
        Board board = new Board(10, 10);
        BoardArranger.arrange(board, PIECES);
        System.out.print(board);
    }
}
