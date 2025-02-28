import javafx.scene.image.Image;

public class Bishop extends DirectionPiece
{
    public static final Direction[] DIRECTIONS = {Direction.NORTH_EAST, Direction.SOUTH_EAST, Direction.SOUTH_WEST, Direction.NORTH_WEST};

    public Bishop(Colour colour, Coordinate location)
    {
        super(colour, location, DIRECTIONS);
        this.type = PieceType.BISHOP;
        this.sprite = new Image("./sprites/" + this.type + this.colour + ".png");
    }
}
