package tengram.arranger;

import tengram.core.Board;
import tengram.core.Orientation;
import tengram.core.Piece;

public class BoardArranger {
    public static boolean arrange(Board board, Piece[] pieces) {
        return arrange(board, pieces, 0);
    }







































































    private static boolean arrange(Board board, Piece[] pieces, int pieceNdx) {
        if (pieceNdx >= pieces.length) {
            return true;
        }

        Piece piece = pieces[pieceNdx];
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                if (board.canPlacePiece(piece, x, y, Orientation.NORMAL)) {
                    board.placePiece(piece, x, y, Orientation.NORMAL);
                } else if (board.canPlacePiece(piece, x, y, Orientation.CLOCKWISE)) {
                    board.placePiece(piece, x, y, Orientation.CLOCKWISE);
                } else {
                    continue;
                }

                if (arrange(board, pieces, pieceNdx + 1)) {
                    return true;
                } else {
                    board.removePieceFromBoard(piece);
                }
            }
        }

        return false;
    }
}
