import javafx.scene.image.Image;

public class King extends VectorPiece
{
    public King(Colour colour, Coordinate location)
    {
        super(colour, location, null);
        this.type = PieceType.KING;
        this.sprite = new Image("./sprites/" + this.type + this.colour.toString() + ".png");
        this.possibleMoves = new MoveVector[]
                {
                        new MoveVector(-1, -1, this.colour),
                        new MoveVector(0, -1, this.colour),
                        new MoveVector(1, -1, this.colour),
                        new MoveVector(1, 0, this.colour),
                        new MoveVector(1, 1, this.colour),
                        new MoveVector(0, 1, this.colour),
                        new MoveVector(-1, 1, this.colour),
                        new MoveVector(-1, 0, this.colour)
                };
    }
}
