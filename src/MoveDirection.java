public class MoveDirection
{
    private int distance;
    private Direction direction;

    public MoveDirection(int distance, Direction direction)
    {
        this.distance = distance;
        this.direction = direction;
    }

    public int getDistance()
    {
        return this.distance;
    }

    public Direction getDirection()
    {
        return this.direction;
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof MoveDirection))
        {
            return false;
        }

        MoveDirection move = (MoveDirection) o;

        if(move.getDistance() == this.distance && move.getDirection() == this.direction)
        {
            return true;
        }

        return false;
    }
}
