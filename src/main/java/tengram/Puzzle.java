package tengram;

import tengram.core.Board;
import tengram.core.Piece;
import tengram.arranger.BoardArranger;

public class Puzzle {
    private static Piece[] PIECES = {
            new Piece(2, 9),
            new Piece(2, 1),
            new Piece(2, 5),
            new Piece(7, 2),
            new Piece(2, 3),
            new Piece(5, 5),
            new Piece(4, 3),
            new Piece(1, 3),
            new Piece(1, 2),
            new Piece(1, 8)
    };

    public static void main(String[] args) {
        boolean isArranged = BoardArranger.arrange(new Board(10, 10), PIECES);
    }
}
