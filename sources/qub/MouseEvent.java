package qub;

/**
 * An event that came from a mouse input device.
 */
public class MouseEvent extends PointerEvent.Base implements PointerEvent.Typed<MouseEvent>
{
    private MutablePoint2Integer previousLocation;
    private MutablePoint2Integer newLocation;

    private MouseEvent()
    {
    }

    /**
     * Create a new {@link MouseEvent}.
     */
    public static MouseEvent create()
    {
        return new MouseEvent();
    }

    @Override
    public Point2Integer getPreviousLocation()
    {
        return this.previousLocation;
    }

    @Override
    public Point2Integer getNewLocation()
    {
        return this.newLocation;
    }

    @Override
    public PointerType getPointerType()
    {
        return PointerType.Mouse;
    }

    @Override
    public MouseEvent setPreviousLocation(int previousLocationX, int previousLocationY)
    {
        this.previousLocation = Point2.create(previousLocationX, previousLocationY);

        return this;
    }

    @Override
    public MouseEvent setNewLocation(int newLocationX, int newLocationY)
    {
        this.newLocation = Point2.create(newLocationX, newLocationY);

        return this;
    }

    @Override
    public MouseEvent translate(int x, int y)
    {
        if (previousLocation != null)
        {
            previousLocation.set(previousLocation.getXAsInt() + x, previousLocation.getYAsInt() + y);
        }
        if (newLocation != null)
        {
            newLocation.set(newLocation.getXAsInt() + x, newLocation.getYAsInt() + y);
        }
        return this;
    }
}
