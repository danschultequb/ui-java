package qub;

/**
 * An object that contains a {@link Size2Integer} and can notify observers when it changes.
 */
public interface DynamicSize2Integer extends Size2Integer
{
    /**
     * Create a new {@link BasicDynamicSize2Integer}.
     */
    public static BasicDynamicSize2Integer create()
    {
        return BasicDynamicSize2Integer.create();
    }

    /**
     * Run the provided {@link Action0} when this {@link DynamicSize2Integer} changes.
     * @param action The {@link Action0} to run when this {@link DynamicSize2Integer} changes.
     * @return A {@link Disposable} that can be disposed to unregister the provided {@link Action0}.
     */
    public default Disposable onChanged(Action0 action)
    {
        PreCondition.assertNotNull(action, "action");

        return this.onChanged((SizeChange sizeChange) -> { action.run(); });
    }

    /**
     * Run the provided {@link Action0} when this {@link DynamicSize2Integer} changes.
     * @param action The {@link Action0} to run when this {@link DynamicSize2Integer} changes.
     * @return A {@link Disposable} that can be disposed to unregister the provided {@link Action0}.
     */
    public Disposable onChanged(Action1<SizeChange> action);

    public default DynamicSize2Integer scaleWidth(double widthScale)
    {
        return this.scale(widthScale, 1.0);
    }

    public default DynamicSize2Integer scaleHeight(double heightScale)
    {
        return this.scale(1.0, heightScale);
    }

    public default DynamicSize2Integer scale(double scale)
    {
        return this.scale(scale, scale);
    }

    public default DynamicSize2Integer scale(double widthScale, double heightScale)
    {
        return ScaledDynamicSize2Integer.create(this)
            .setWidthScale(widthScale)
            .setHeightScale(heightScale);
    }

    /**
     * An abstract base class implementation of {@link DynamicSize2Integer} that contains common
     * properties and functions for all {@link DynamicSize2Integer} types.
     */
    public static abstract class Base implements DynamicSize2Integer
    {
        @Override
        public boolean equals(Object rhs)
        {
            return Size2.equals(this, rhs);
        }

        @Override
        public String toString()
        {
            return Size2.toString(this);
        }

        @Override
        public int hashCode()
        {
            return Size2.hashCode(this);
        }
    }
}
