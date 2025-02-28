import javafx.scene.image.Image;

public class King extends VectorPiece
{
    public King(Colour colour, Coordinate location)
    {
        super(colour, location, null);
        this.type = PieceType.KING;
        this.sprite = new Image("./sprites/" + this.type + this.colour.toString() + ".png");
    }
}
