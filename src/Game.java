public class Game
{
    private static Game game;

    private Piece[][] board;

    private Game()
    {
        this.fillBoard();
    }

    public static Game getGame()
    {
        if (Game.game == null)
        {
            Game.game = new Game();
        }
        return Game.game;
    }

    public Piece[][] getBoard()
    {
        return this.board;
    }

    public void printBoard()
    {
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
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
        if (this.board == null)
        {
            this.board = new Piece[8][8];
        }

        for (int i = 0; i < 8; i++)
        {
            this.board[1][i] = new Pawn(Colour.BLACK, new Coordinate(i, 1));
            this.board[6][i] = new Pawn(Colour.WHITE, new Coordinate(i, 6));
        }

        this.board[0][0] = new Rook(Colour.BLACK, new Coordinate(0, 0));
        this.board[0][7] = new Rook(Colour.BLACK, new Coordinate(7, 0));
        this.board[7][0] = new Rook(Colour.WHITE, new Coordinate(0, 7));
        this.board[7][7] = new Rook(Colour.WHITE, new Coordinate(7, 7));

        this.board[0][1] = new Knight(Colour.BLACK, new Coordinate(1, 0));
        this.board[0][6] = new Knight(Colour.BLACK, new Coordinate(6, 0));
        this.board[7][1] = new Knight(Colour.WHITE, new Coordinate(1, 7));
        this.board[7][6] = new Knight(Colour.WHITE, new Coordinate(6, 7));

        this.board[0][2] = new Bishop(Colour.BLACK, new Coordinate(2, 0));
        this.board[0][5] = new Bishop(Colour.BLACK, new Coordinate(5, 0));
        this.board[7][2] = new Bishop(Colour.WHITE, new Coordinate(2, 7));
        this.board[7][5] = new Bishop(Colour.WHITE, new Coordinate(5, 7));

        this.board[0][4] = new King(Colour.BLACK, new Coordinate(4, 0));
        this.board[7][3] = new King(Colour.WHITE, new Coordinate(3, 7));

        this.board[0][3] = new Queen(Colour.BLACK, new Coordinate(3, 0));
        this.board[7][4] = new Queen(Colour.WHITE, new Coordinate(4, 7));
    }
}
