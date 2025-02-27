public class King extends VectorPiece
{
    public King(Colour colour, Coordinate location)
    {
        super(colour, location, null);
        this.type = PieceType.KING;
    }
}
