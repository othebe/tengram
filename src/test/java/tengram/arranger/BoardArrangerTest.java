package tengram.arranger;

import org.junit.Before;
import org.junit.Test;
import tengram.core.Board;
import tengram.core.Orientation;
import tengram.core.Piece;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoardArrangerTest {
    @Before
    public void setup() { }

    @Test
    public void shouldArrange() {
        // Arrange
        Piece[] pieces = {
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
        Piece[] pieces = { new Piece(2, 2) };

        Board board = new Board(1, 1);

        // Act
        boolean isArranged = BoardArranger.arrange(board, pieces);

        // Assert
        assertFalse("Board cannot be arranged", isArranged);
    }

    private void assertAllPiecesUsedOnlyOnce(Board board, Piece[] pieces) {
        Piece[][] grid = board.getGrid();

        Set<Piece> boardPieces = new HashSet<Piece>();
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                if (grid[y][x] != null) {
                    assertFalse("Piece appears on board multiple times", boardPieces.contains(grid[y][x]));
                    boardPieces.add(grid[y][x]);
                }
            }
        }

        for (Piece piece : pieces) {
            assertTrue("Piece missing fron board", boardPieces.contains(piece));
            boardPieces.remove(piece);
        }

        assertTrue("Board contains extra pieces", boardPieces.isEmpty());
    }
}
