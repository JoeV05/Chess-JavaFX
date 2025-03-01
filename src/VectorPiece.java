import java.util.Arrays;

public abstract class VectorPiece extends Piece
{
    protected MoveVector[] possibleMoves;

    public VectorPiece(Colour colour, Coordinate start, MoveVector[] possibleMoves)
    {
        super(colour, start);
        this.possibleMoves = possibleMoves;
    }

    public boolean validateMove(MoveVector move)
    {
        return Arrays.asList(possibleMoves).contains(move);
    }

    public void move(MoveVector move)
    {
        this.location = move.applyVector(this.location);
    }

    public MoveVector[] getVectors()
    {
        return this.possibleMoves;
    }
}
