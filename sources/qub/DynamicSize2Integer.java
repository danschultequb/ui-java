package qub;

/**
 * An object that contains a {@link Size2Integer} and can notify observers when it changes.
 */
public interface DynamicSize2Integer extends Size2Integer
{
    /**
     * Create a new {@link MutableDynamicSize2Integer}.
     */
    public static MutableDynamicSize2Integer create()
    {
        return MutableDynamicSize2Integer.create();
    }

    /**
     * Create a new {@link MutableDynamicSize2Integer} with the provided width and height.
     * @param width The width of the new {@link MutableDynamicSize2Integer}.
     * @param height The height of the new {@link MutableDynamicSize2Integer}.
     */
    public static MutableDynamicSize2Integer create(int width, int height)
    {
        return MutableDynamicSize2Integer.create(width, height);
    }

    /**
     * Create a new {@link MutableDynamicSize2Integer} with the provided {@link Size2}.
     * @param size The size of the new {@link MutableDynamicSize2Integer}.
     */
    public static MutableDynamicSize2Integer create(Size2<Integer> size)
    {
        return MutableDynamicSize2Integer.create(size);
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

    /**
     * Get a {@link ScaledDynamicSize2Integer} where the width is a scaled version of this
     * {@link DynamicSize2Integer}'s width.
     * @param widthScale The scale factor.
     */
    public default ScaledDynamicSize2Integer scaleWidth(double widthScale)
    {
        return this.scale(widthScale, 1.0);
    }

    /**
     * Get a {@link ScaledDynamicSize2Integer} where the height is a scaled version of this
     * {@link DynamicSize2Integer}'s height.
     * @param heightScale The scale factor.
     */
    public default ScaledDynamicSize2Integer scaleHeight(double heightScale)
    {
        return this.scale(1.0, heightScale);
    }

    /**
     * Get a {@link ScaledDynamicSize2Integer} where the width and height is a scaled version of
     * this {@link DynamicSize2Integer}'s width and height.
     * @param scale The scale factor.
     */
    public default ScaledDynamicSize2Integer scale(double scale)
    {
        PreCondition.assertGreaterThanOrEqualTo(scale, 0, "scale");

        return this.scale(scale, scale);
    }

    /**
     * Get a {@link ScaledDynamicSize2Integer} where the width and height is a scaled version of
     * this {@link DynamicSize2Integer}'s width and height.
     * @param widthScale The width scale factor.
     * @param heightScale The height scale factor.
     */
    public default ScaledDynamicSize2Integer scale(double widthScale, double heightScale)
    {
        return ScaledDynamicSize2Integer.create(this)
            .setWidthScale(widthScale)
            .setHeightScale(heightScale);
    }

    /**
     * An abstract base class implementation of {@link DynamicSize2Integer} that contains common
     * properties and functions for all {@link DynamicSize2Integer} types.
     */
    public static abstract class Base extends Size2.Base<Integer> implements DynamicSize2Integer
    {
    }
}
