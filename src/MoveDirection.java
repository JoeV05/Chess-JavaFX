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
}
