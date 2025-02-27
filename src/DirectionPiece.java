import java.util.Arrays;

public abstract class DirectionPiece extends Piece
{
    protected final Direction[] directions;

    public DirectionPiece(Colour colour, Coordinate location, Direction[] directions)
    {
        super(colour, location);
        this.directions = directions;
    }

    public boolean validateMove(Direction move) {
        return Arrays.asList(directions).contains(move);
    }

    public void move(Direction direction, int length)
    {
    }
}
