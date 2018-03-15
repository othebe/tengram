package tengram.core;

public class Board {
    private final Piece[][] grid;

    public Board(int width, int height) {
        this.grid = new Piece[height][width];
    }

    /**
     * Gets the width of the board.
     * @return Integer representing board width.
     */
    public int getWidth() {
        return grid.length == 0 ? 0 : grid[0].length;
    }

    /**
     * Gets the height of the board.
     * @return Integer representing board height.
     */
    public int getHeight() {
        return grid.length;
    }

    /**
     * Gets the grid of pieces on the board.
     * @return Two-dimensional array containing the pieces currently on the board.
     */
    public Piece[][] getGrid() {
        return grid;
    }

    /**
     * Determines if a piece can be placed on the board.
     * @param piece Piece to place.
     * @param x x position on board.
     * @param y y position on board.
     * @param orientation NORMAL or CLOCKWISE rotated.
     * @return True if the piece can be placed, else false.
     */
    public boolean canPlacePiece(Piece piece, int x, int y, Orientation orientation) {
        return isPieceWithinBoard(piece, x, y, orientation) && isGridUnoccupiedForPiece(piece, x, y, orientation);
    }

    /**
     * Places piece on board. Throws exception if invalid.
     * @param piece Piece to place.
     * @param x x position on board.
     * @param y y position on board.
     * @param orientation NORMAL or CLOCKWISE rotated.
     */
    public void placePiece(Piece piece, int x, int y, Orientation orientation) {
        if (!isPieceWithinBoard(piece, x, y, orientation)) {
            throw new RuntimeException("Positions are off the board");
        }

        if (!isGridUnoccupiedForPiece(piece, x, y, orientation)) {
            throw new RuntimeException("There is already a piece occupying this position");
        }

        removePieceFromBoard(piece);

        addPieceToBoard(piece, x, y, orientation);
    }

    /**
     * Removes a piece from the board.
     * @param piece
     */
    public void removePieceFromBoard(Piece piece) {
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                if (grid[y][x] == piece) {
                    grid[y][x] = null;
                }
            }
        }
    }

    private boolean isPieceWithinBoard(Piece piece, int x, int y, Orientation orientation) {
        int pieceWidth = orientation == Orientation.NORMAL ? piece.getWidth() : piece.getHeight();
        int pieceHeight = orientation == Orientation.NORMAL ? piece.getHeight() : piece.getWidth();

        for (int yNdx = 0; yNdx < pieceHeight; yNdx++) {
            for (int xNdx = 0; xNdx < pieceWidth; xNdx++) {
                int projectedY = y + yNdx;
                int projectedX = x + xNdx;

                // Off limits.
                if (projectedY < 0 || projectedY >= getHeight() || projectedX < 0 || projectedX >= getWidth()) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isGridUnoccupiedForPiece(Piece piece, int x, int y, Orientation orientation) {
        int pieceWidth = orientation == Orientation.NORMAL ? piece.getWidth() : piece.getHeight();
        int pieceHeight = orientation == Orientation.NORMAL ? piece.getHeight() : piece.getWidth();

        for (int yNdx = 0; yNdx < pieceHeight; yNdx++) {
            for (int xNdx = 0; xNdx < pieceWidth; xNdx++) {
                int projectedY = y + yNdx;
                int projectedX = x + xNdx;

                // Grid is occupied.
                if (grid[projectedY][projectedX] != null && grid[projectedY][projectedX] != piece) {
                    return false;
                }
            }
        }

        return true;
    }

    private void addPieceToBoard(Piece piece, int x, int y, Orientation orientation) {
        int pieceWidth = orientation == Orientation.NORMAL ? piece.getWidth() : piece.getHeight();
        int pieceHeight = orientation == Orientation.NORMAL ? piece.getHeight() : piece.getWidth();

        for (int yNdx = 0; yNdx < pieceHeight; yNdx++) {
            for (int xNdx = 0; xNdx < pieceWidth; xNdx++) {
                int projectedY = y + yNdx;
                int projectedX = x + xNdx;

                grid[projectedY][projectedX] = piece;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                stringBuilder.append(String.format("%s\t", grid[y][x] == null ? 0 : grid[y][x].getId()));
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
