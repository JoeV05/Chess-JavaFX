public class MoveVector
{
    private int deltaX;
    private int deltaY;

    public MoveVector(int x, int y, Colour colour)
    {
        this.deltaX = x;

        if (colour == Colour.BLACK)
        {
            this.deltaY = y;
        } else
        {
            this.deltaY = -y;
        }
    }

    public Coordinate applyVector(Coordinate coordinate)
    {
        int newX = coordinate.getX() + deltaX;
        int newY = coordinate.getY() + deltaY;
        
        return new Coordinate(newX, newY);
    }
}
