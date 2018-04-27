package tengram.arranger;

import tengram.core.Board;
import tengram.core.Orientation;
import tengram.core.Piece;

public class BoardArranger {
    /**
     * This function arranges the array of pieces onto the given board.
     * @param board Board to arrange pieces on.
     * @param pieces Array of pieces to arrange onto given board.
     * @return True if board was arranged, else false.
     */
    public static boolean arrange(Board board, Piece[] pieces) {
        if (board.canPlacePiece(pieces[0], 0, 0, Orientation.NORMAL)) {
            board.placePiece(pieces[0], 0, 0, Orientation.NORMAL);
        }
        System.out.println(board);

        if (board.canPlacePiece(pieces[1], 0, 0, Orientation.NORMAL)) {
            board.placePiece(pieces[1], 0, 0, Orientation.NORMAL);
        }
        System.out.println(board);
        
        return true;
    }
}
