package qub;

/**
 * An event that occurred from a pointer input device (such as a mouse, finger, or stylus).
 */
public interface PointerEvent
{
    public static final String previousLocationPropertyName = "previousLocation";
    public static final String newLocationPropertyName = "newLocation";

    /**
     * Create a new {@link MouseEvent}.
     */
    public static MouseEvent createMouseEvent()
    {
        return MouseEvent.create();
    }

    /**
     * Get the previous location of the pointer.
     */
    public Point2Integer getPreviousLocation();

    /**
     * Set the previous location of the pointer.
     * @param previousLocationX The x-coordinate of the previous location of the pointer.
     * @param previousLocationY The y-coordinate of the previous location of the pointer.
     * @return This object for method chaining.
     */
    public PointerEvent setPreviousLocation(int previousLocationX, int previousLocationY);

    /**
     * Set the previous location of the pointer.
     * @param previousLocation The previous location of the pointer.
     * @return This object for method chaining.
     */
    public default PointerEvent setPreviousLocation(Point2Integer previousLocation)
    {
        PreCondition.assertNotNull(previousLocation, "previousLocation");

        return this.setPreviousLocation(previousLocation.getXAsInt(), previousLocation.getYAsInt());
    }

    /**
     * Get the new location of the pointer.
     */
    public Point2Integer getNewLocation();

    /**
     * Set the new location of the pointer.
     * @param newLocationX The x-coordinate of the new location of the pointer.
     * @param newLocationY The y-coordinate of the new location of the pointer.
     * @return This object for method chaining.
     */
    public PointerEvent setNewLocation(int newLocationX, int newLocationY);

    /**
     * Set the new location of the pointer.
     * @param newLocation The new location of the pointer.
     * @return This object for method chaining.
     */
    public default PointerEvent setNewLocation(Point2Integer newLocation)
    {
        PreCondition.assertNotNull(newLocation, "newLocation");

        return this.setNewLocation(newLocation.getXAsInt(), newLocation.getYAsInt());
    }

    /**
     * Get the type of pointer that triggered this event.
     */
    public PointerType getPointerType();

    public default PointerEvent translateX(int x)
    {
        return this.translate(x, 0);
    }

    public default PointerEvent translateY(int y)
    {
        return this.translate(0, y);
    }

    public PointerEvent translate(int x, int y);

    public default PointerEvent inverseTranslateX(int x)
    {
        return this.inverseTranslate(x, 0);
    }

    public default PointerEvent inverseTranslateY(int y)
    {
        return this.inverseTranslate(0, y);
    }

    public default PointerEvent inverseTranslate(int x, int y)
    {
        return this.translate(-x, -y);
    }

    public default Disposable saveState()
    {
        final Point2Integer previousLocation = this.getPreviousLocation();
        final Point2Integer newLocation = this.getNewLocation();
        return Disposable.create(() ->
        {
            if (previousLocation != null)
            {
                this.setPreviousLocation(previousLocation);
            }
            if (newLocation != null)
            {
                this.setNewLocation(newLocation);
            }
        });
    }

    public static String toString(PointerEvent event)
    {
        PreCondition.assertNotNull(event, "event");

        return event.toJson().toString();
    }

    public default JSONObject toJson()
    {
        final JSONObject result = JSONObject.create();

        final Point2Integer previousLocation = this.getPreviousLocation();
        if (previousLocation != null)
        {
            result.setObject(PointerEvent.previousLocationPropertyName, previousLocation.toJson());
        }

        final Point2Integer newLocation = this.getNewLocation();
        if (newLocation != null)
        {
            result.setObject(PointerEvent.newLocationPropertyName, newLocation.toJson());
        }

        PostCondition.assertNotNull(result, "result");

        return result;
    }

    /**
     * A version of a {@link PointerEvent} that returns its own type from chainable methods.
     * @param <T> The actual type of the {@link PointerEvent}.
     */
    public static interface Typed<T extends PointerEvent> extends PointerEvent
    {
        @Override
        public T setPreviousLocation(int previousLocationX, int previousLocationY);

        @Override
        @SuppressWarnings("unchecked")
        public default T setPreviousLocation(Point2Integer previousLocation)
        {
            return (T)PointerEvent.super.setPreviousLocation(previousLocation);
        }

        @Override
        public T setNewLocation(int newLocationX, int newLocationY);

        @Override
        @SuppressWarnings("unchecked")
        public default T setNewLocation(Point2Integer newLocation)
        {
            return (T)PointerEvent.super.setNewLocation(newLocation);
        }

        @Override
        @SuppressWarnings("unchecked")
        public default T translateX(int x)
        {
            return (T)PointerEvent.super.translateX(x);
        }

        @Override
        @SuppressWarnings("unchecked")
        public default T translateY(int y)
        {
            return (T)PointerEvent.super.translateY(y);
        }

        @Override
        public T translate(int x, int y);

        @Override
        @SuppressWarnings("unchecked")
        public default T inverseTranslateX(int x)
        {
            return (T)PointerEvent.super.inverseTranslateX(x);
        }

        @Override
        @SuppressWarnings("unchecked")
        public default T inverseTranslateY(int y)
        {
            return (T)PointerEvent.super.inverseTranslateY(y);
        }

        @Override
        @SuppressWarnings("unchecked")
        public default T inverseTranslate(int x, int y)
        {
            return (T)PointerEvent.super.inverseTranslate(x, y);
        }
    }

    public static abstract class Base implements PointerEvent
    {
        @Override
        public String toString()
        {
            return PointerEvent.toString(this);
        }
    }
}
