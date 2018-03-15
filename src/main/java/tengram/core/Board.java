package tengram.core;

public class Board {
    private final Piece[][] grid;

    public Board(int width, int height) {
        this.grid = new Piece[height][width];
    }

    public int getWidth() {
        return grid.length;
    }

    public int getHeight() {
        return grid.length == 0 ? 0 : grid[0].length;
    }

    public Piece[][] getGrid() {
        return grid;
    }

    public boolean canPlacePiece(Piece piece, int x, int y, Orientation orientation) {
        return isPieceWithinBoard(piece, x, y, orientation) && isGridUnoccupiedForPiece(piece, x, y, orientation);
    }

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

    private void removePieceFromBoard(Piece piece) {
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                if (grid[y][x] == piece) {
                    grid[y][x] = null;
                }
            }
        }
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
}
