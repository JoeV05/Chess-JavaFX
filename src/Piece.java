import javafx.scene.image.Image;

public abstract class Piece
{
    protected final Colour colour;
    protected Coordinate location;
    protected PieceType type;
    protected Image sprite;

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

    public Image getSprite()
    {
        return this.sprite;
    }

    public int getX()
    {
        return this.location.getX();
    }

    public int getY()
    {
        return this.location.getY();
    }

    public Coordinate getLocation()
    {
        return location;
    }

    public void move(Coordinate newLocation)
    {
        this.location = newLocation;
    }
}
