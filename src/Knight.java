import javafx.scene.image.Image;

public class Knight extends VectorPiece
{
    public Knight(Colour colour, Coordinate location)
    {
        super(colour, location, null);
        this.type = PieceType.KNIGHT;
        this.sprite = new Image("./sprites/" + this.type + this.colour + ".png");
        this.possibleMoves = new MoveVector[]
        {
                new MoveVector(1, 2, this.colour),
                new MoveVector(-1, 2, this.colour),
                new MoveVector(-2, 1, this.colour),
                new MoveVector(-2, -1, this.colour),
                new MoveVector(2, 1, this.colour),
                new MoveVector(2, -1, this.colour),
                new MoveVector(1, -2, this.colour),
                new MoveVector(-1, -2, this.colour)
        };
    }
}
