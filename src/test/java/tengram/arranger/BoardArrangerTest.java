package tengram.arranger;

import org.junit.Before;
import org.junit.Test;
import tengram.core.Board;
import tengram.core.Piece;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoardArrangerTest {
    @Before
    public void setup() { }

    @Test
    public void shouldArrange() {
        // Arrange
        Piece[] pieces = {
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

        Board board = new Board(10, 10);

        // Act
        boolean isArranged = BoardArranger.arrange(board, pieces);

        // Assert
        assertTrue("Board can be arranged", isArranged);
        assertAllPiecesUsedOnlyOnce(board, pieces);
    }

    @Test
    public void shouldNotArrange() {
        // Arrange
        Piece[] pieces = { new Piece("A", 2, 2) };

        Board board = new Board(1, 1);

        // Act
        boolean isArranged = BoardArranger.arrange(board, pieces);

        // Assert
        assertFalse("Board cannot be arranged", isArranged);
    }

    private void assertAllPiecesUsedOnlyOnce(Board board, Piece[] pieces) {
        Piece[][] grid = board.getGrid();

        Map<Piece, Integer> boardPieces = new HashMap<Piece, Integer>();
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                if (grid[y][x] != null) {
                    Piece gridPiece = grid[y][x];
                    if (!boardPieces.containsKey(gridPiece)) {
                        boardPieces.put(gridPiece, 0);
                    }
                    boardPieces.put(gridPiece, boardPieces.get(gridPiece) + 1);
                }
            }
        }

        for (Piece piece : pieces) {
            assertTrue("Piece missing fron board", boardPieces.containsKey(piece));
            assertTrue("Piece appears on board multiple times", piece.getWidth() * piece.getHeight() == boardPieces.get(piece));
            boardPieces.remove(piece);
        }

        assertTrue("Board contains extra pieces", boardPieces.isEmpty());
    }
}
