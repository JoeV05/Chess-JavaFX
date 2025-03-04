import javafx.scene.image.Image;

public class Pawn extends VectorPiece
{
    private boolean hasMoved = false;

    public Pawn(Colour colour, Coordinate location)
    {
        super(colour, location, null);
        this.type = PieceType.PAWN;
        this.sprite = new Image("./sprites/" + this.type + this.colour + ".png");
        this.possibleMoves = new MoveVector[]
        {
                new MoveVector(0, 1, this.colour),
                new MoveVector(0, 2, this.colour)
        };
    }

    @Override
    public void move(Coordinate newLocation)
    {

        this.location = newLocation;

        if (!this.hasMoved)
        {
            this.possibleMoves = new MoveVector[] {new MoveVector(0, 1, this.colour)};
            this.hasMoved = true;
        }
    }
}
