import javafx.scene.image.Image;

public class Rook extends DirectionPiece
{
    public static final Direction[] DIRECTIONS = {Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};

    public Rook(Colour colour, Coordinate location)
    {
        super(colour, location, DIRECTIONS);
        this.type = PieceType.ROOK;
        this.sprite = new Image("./sprites/" + this.type + this.colour + ".png");
    }
}
