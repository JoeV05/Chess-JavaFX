public class Game
{
    public static final int BOARD_SIZE = 8;

    private static Game game;
    private final Piece[][] board;
    private Colour currentTurn;
    private State state;
    private Piece selected;

    private Game()
    {
        this.board = new Piece[BOARD_SIZE][BOARD_SIZE];
        this.fillBoard();
        this.currentTurn = Colour.WHITE;
        this.state = State.NONE_SELECTED;
    }

    public static Game getGame()
    {
        if (Game.game == null)
        {
            Game.game = new Game();
        }
        return Game.game;
    }

    public Piece getSelected()
    {
        return this.selected;
    }

    public void handle(int x, int y)
    {
        Piece clickedPiece = board[y][x];
        if (clickedPiece != null)
        {
            if (clickedPiece.getColour() == this.currentTurn)
            {
                this.selected = clickedPiece;
                this.state = State.PIECE_SELECTED;
            }
        } else if (this.state == State.PIECE_SELECTED)
        {
            //Move the selected piece
            System.out.println("Test text");
        }
    }

    public MoveVector[] getPossibleVectorMoves(VectorPiece p)
    {
        MoveVector[] moves = p.getVectors();
        Coordinate c = p.getLocation();
        MoveVector[] allowedMoves = new MoveVector[moves.length];

        for (int i = 0; i < moves.length; i ++)
        {
            MoveVector move = moves[i];
            Coordinate newC = move.applyVector(c);

            int x = newC.getX();
            int y = newC.getY();

            if ((x < BOARD_SIZE && y < BOARD_SIZE) && (x >= 0 && y >= 0) && board[y][x] == null)
            {
                allowedMoves[i] = move;
            }
        }
        return allowedMoves;
    }

    public MoveDirection[] getPossibleDirectionalMoves(DirectionPiece p)
    {
        Direction[] dirs = p.getDirections();
        MoveDirection[] possibleMoves = new MoveDirection[dirs.length];
        for (int i = 0; i < dirs.length; i++)
        {
            int distance = this.distanceToBlock(p, dirs[i]);
            if (distance > 0) {
                MoveDirection m = new MoveDirection(distance, dirs[i]);
                possibleMoves[i] = m;
            }
        }
        return possibleMoves;
    }

    private int distanceToBlock(Piece piece, Direction dir)
    {
        int x = piece.getX();
        int y = piece.getY();
        int currentX;
        int currentY;
        switch (dir)
        {

            case Direction.NORTH:
                if (y == 0)
                {
                    return 0;
                } else
                {
                    currentY = y - 1;

                    while (currentY >= 0)
                    {
                        if (board[currentY][x] != null)
                        {
                            return y - (currentY + 1);
                        }

                        currentY--;
                    }
                    return y;
                }

            case Direction.EAST:
                if (x == BOARD_SIZE - 1)
                {
                    return 0;
                } else
                {
                    currentX = x + 1;

                    while (currentX <= (BOARD_SIZE - 1))
                    {
                        if (board[y][currentX] != null)
                        {
                            return currentX - (x + 1);
                        }

                        currentX++;
                    }

                    return BOARD_SIZE - (x + 1);
                }

            case Direction.SOUTH:
                if (y == BOARD_SIZE - 1)
                {
                    return 0;
                } else
                {
                    currentY = y + 1;

                    while (currentY <= (BOARD_SIZE - 1))
                    {
                        if (board[currentY][x] != null)
                        {
                            return currentY - (y + 1);
                        }

                        currentY++;
                    }

                    return BOARD_SIZE - (y + 1);
                }

            case Direction.WEST:
                if (x == 0)
                {
                    return 0;
                } else
                {
                    currentX = x - 1;

                    while (currentX >= 0)
                    {
                        if (board[y][currentX] != null)
                        {
                            return x - (currentX + 1);
                        }

                        currentX--;
                    }
                    return x;
                }

            case Direction.NORTH_EAST:
            case Direction.SOUTH_EAST:
            case Direction.SOUTH_WEST:
            case Direction.NORTH_WEST:
                return 0;

            default:
                throw new IllegalArgumentException("Invalid direction passed in: " + dir);
        }
    }


    public Piece[][] getBoard()
    {
        return this.board;
    }

    public void printBoard()
    {
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            for (int j = 0; j < BOARD_SIZE; j++)
            {
                if (this.board[i][j] != null)
                {
                    System.out.print(this.board[i][j].toString().charAt(0));
                    System.out.print(this.board[i][j].getColour().toString().charAt(0) + " ");
                } else
                {
                    System.out.print("\\/ ");
                }
            }
            System.out.println();
        }
    }

    private void fillBoard()
    {
        this.addPawns();
        this.addRooks();
        this.addKnights();
        this.addBishops();
        this.addKings();
        this.addQueens();

        // TODO - debug pieces, remove at some point
        //board[4][4] = new Rook(Colour.WHITE, new Coordinate(4, 4));
        //board[5][5] = new Rook(Colour.WHITE, new Coordinate(5, 5));
        //board[3][3] = new Rook(Colour.WHITE, new Coordinate(3, 3));
    }

    private void addPawns()
    {
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            this.board[1][i] = new Pawn(Colour.BLACK, new Coordinate(i, 1));
            this.board[6][i] = new Pawn(Colour.WHITE, new Coordinate(i, 6));
        }
    }

    private void addRooks()
    {
        this.board[0][0] = new Rook(Colour.BLACK, new Coordinate(0, 0));
        this.board[0][7] = new Rook(Colour.BLACK, new Coordinate(7, 0));
        this.board[7][0] = new Rook(Colour.WHITE, new Coordinate(0, 7));
        this.board[7][7] = new Rook(Colour.WHITE, new Coordinate(7, 7));
    }

    private void addKnights()
    {
        this.board[0][1] = new Knight(Colour.BLACK, new Coordinate(1, 0));
        this.board[0][6] = new Knight(Colour.BLACK, new Coordinate(6, 0));
        this.board[7][1] = new Knight(Colour.WHITE, new Coordinate(1, 7));
        this.board[7][6] = new Knight(Colour.WHITE, new Coordinate(6, 7));
    }

    private void addBishops()
    {
        this.board[0][2] = new Bishop(Colour.BLACK, new Coordinate(2, 0));
        this.board[0][5] = new Bishop(Colour.BLACK, new Coordinate(5, 0));
        this.board[7][2] = new Bishop(Colour.WHITE, new Coordinate(2, 7));
        this.board[7][5] = new Bishop(Colour.WHITE, new Coordinate(5, 7));
    }

    private void addKings()
    {
        this.board[0][4] = new King(Colour.BLACK, new Coordinate(4, 0));
        this.board[7][3] = new King(Colour.WHITE, new Coordinate(3, 7));
    }

    private void addQueens()
    {
        this.board[0][3] = new Queen(Colour.BLACK, new Coordinate(3, 0));
        this.board[7][4] = new Queen(Colour.WHITE, new Coordinate(4, 7));
    }
}
