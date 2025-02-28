import javafx.scene.image.Image;

public class Knight extends VectorPiece
{
    public Knight(Colour colour, Coordinate location)
    {
        super(colour, location, null);
        this.type = PieceType.KNIGHT;
        this.sprite = new Image("./sprites/" + this.type + this.colour + ".png");
    }
}
