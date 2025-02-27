public abstract class Piece
{
    protected final Colour colour;
    protected Coordinate location;
    protected PieceType type;

    public Piece(Colour colour, Coordinate location)
    {
        this.colour = colour;
        this.location = location;
    }

    @Override
    public String toString()
    {
        return this.type.toString();
    }

    public Colour getColour()
    {
        return this.colour;
    }
}
