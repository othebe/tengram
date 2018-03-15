package tengram.core;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoardTest {
    private Board board;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        board = new Board(10, 10);
    }

    @Test
    public void shouldPlacePieces() {
        // Arrange
        Piece piece = new Piece(1, 3);

        // Act
        board.placePiece(piece, 0, 0, Orientation.NORMAL);

        // Assert
        assertEquals("Incorrect piece", piece, board.getGrid()[0][0]);
        assertEquals("Incorrect piece", piece, board.getGrid()[1][0]);
        assertEquals("Incorrect piece", piece, board.getGrid()[2][0]);
    }

    @Test
    public void shouldPlacePiecesWithClockwiseOrientation() {
        // Arrange
        Piece piece = new Piece(1, 3);

        // Act
        board.placePiece(piece, 0, 0, Orientation.CLOCKWISE);

        // Assert
        assertEquals("Incorrect piece", piece, board.getGrid()[0][0]);
        assertEquals("Incorrect piece", piece, board.getGrid()[0][1]);
        assertEquals("Incorrect piece", piece, board.getGrid()[0][2]);
    }

    @Test
    public void shouldNotAllowOverlappingPieces() {
        // Arrange
        Piece piece1 = new Piece(1, 1);
        Piece piece2 = new Piece(1, 1);

        // Act + Assert
        assertTrue("Is valid move", board.canPlacePiece(piece1, 0, 0, Orientation.NORMAL));
        board.placePiece(piece1, 0, 0, Orientation.NORMAL);
        assertFalse("Is invalid move", board.canPlacePiece(piece2, 0, 0, Orientation.NORMAL));
    }

    @Test
    public void shouldThrowExceptionForOverlappingPieces() {
        // Arrange
        Piece piece1 = new Piece(1, 1);
        Piece piece2 = new Piece(1, 1);

        expectedException.expect(Exception.class);

        // Act
        board.placePiece(piece1, 0, 0, Orientation.NORMAL);
        board.placePiece(piece2, 0, 0, Orientation.NORMAL);
    }

    @Test
    public void shouldNotAllowOverlappingPiecesWithClockwiseOrientation() {
        // Arrange
        Piece piece1 = new Piece(1, 3);
        Piece piece2 = new Piece(1, 1);

        // Act + Assert
        assertTrue("Is valid move", board.canPlacePiece(piece1, 0, 0, Orientation.CLOCKWISE));
        board.placePiece(piece1, 0, 0, Orientation.CLOCKWISE);
        assertFalse("Is invalid move", board.canPlacePiece(piece2, 2, 0, Orientation.NORMAL));
    }

    @Test
    public void shouldThrowExceptionForOverlappingPiecesWithClockwiseOrientation() {
        // Arrange
        Piece piece1 = new Piece(1, 3);
        Piece piece2 = new Piece(1, 1);

        expectedException.expect(Exception.class);

        // Act
        board.placePiece(piece1, 0, 0, Orientation.CLOCKWISE);
        board.placePiece(piece2, 2, 0, Orientation.NORMAL);
    }

    @Test
    public void shouldNotAllowPiecesOutsideBoard() {
        // Arrange
        Piece piece1 = new Piece(1, 3);

        // Act + Assert
        assertFalse("Is invalid move", board.canPlacePiece(piece1, 11, 0, Orientation.NORMAL));
    }

    @Test
    public void shouldThrowExceptionForPiecesOutsideBoard() {
        // Arrange
        Piece piece1 = new Piece(1, 3);

        expectedException.expect(Exception.class);

        // Act
        board.placePiece(piece1, 11, 0, Orientation.NORMAL);
    }

    @Test
    public void shouldNotAllowPiecesOutsideBoardWithClockwiseOrientation() {
        // Arrange
        Piece piece1 = new Piece(1, 3);

        // Act + Assert
        assertFalse(board.canPlacePiece(piece1, 9, 0, Orientation.CLOCKWISE));
    }

    @Test
    public void shouldThrowExceptionForPiecesOutsideBoardWithClockwiseOrientation() {
        // Arrange
        Piece piece1 = new Piece(1, 3);

        expectedException.expect(Exception.class);

        // Act
        board.placePiece(piece1, 9, 0, Orientation.CLOCKWISE);
    }
}
