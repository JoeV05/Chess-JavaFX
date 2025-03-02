public class MoveVector
{
    private int deltaX;
    private int deltaY;

    public MoveVector(int deltaX, int deltaY, Colour colour)
    {
        this.deltaX = deltaX;

        if (colour == Colour.BLACK)
        {
            this.deltaY = deltaY;
        } else
        {
            this.deltaY = -deltaY;
        }
    }

    public Coordinate applyVector(Coordinate coordinate)
    {
        int newX = coordinate.getX() + deltaX;
        int newY = coordinate.getY() + deltaY;
        
        return new Coordinate(newX, newY);
    }

    public void printVector()
    {
        System.out.println("delta x: " + deltaX);
        System.out.println("delta y: " + deltaY);
    }

    private int getDeltaX()
    {
        return this.deltaX;
    }

    private int getDeltaY()
    {
        return this.deltaY;
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof MoveVector))
        {
            return false;
        }

        MoveVector move = (MoveVector) o;

        if (move.getDeltaX() == this.deltaX && move.getDeltaY() == this.deltaY)
        {
            return true;
        }

        return false;
    }
}
