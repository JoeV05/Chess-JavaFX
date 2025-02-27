public class Knight extends VectorPiece
{
    public Knight(Colour colour, Coordinate location)
    {
        super(colour, location, null);
        this.type = PieceType.KNIGHT;
    }
}
