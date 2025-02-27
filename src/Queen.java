public class Queen extends DirectionPiece
{
    public static final Direction[] DIRECTIONS = Direction.values();

    public Queen(Colour colour, Coordinate location)
    {
        super(colour, location, DIRECTIONS);
        this.type = PieceType.QUEEN;
    }
}
