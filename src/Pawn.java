public class Pawn extends VectorPiece
{
    public static final MoveVector[] MOVE_VECTORS = {};
    private boolean hasMoved = false;

    public Pawn(Colour colour, Coordinate location)
    {
        super(colour, location, null);
        this.type = PieceType.PAWN;
    }
}
