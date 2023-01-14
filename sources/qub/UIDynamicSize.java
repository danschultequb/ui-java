package qub;

/**
 * The size of a UI object that can be listened to.
 */
public interface UIDynamicSize
{
    /**
     * Get the pixel size of the target.
     */
    public Size2Integer getSize();

    /**
     * Run the provided {@link Action0} when the target changes.
     * @param action The {@link Action0} to run when the target changes.
     * @return A {@link Disposable} that can be disposed to unregister the provided {@link Action0}.
     */
    public default Disposable onSizeChanged(Action0 action)
    {
        PreCondition.assertNotNull(action, "action");

        return this.onSizeChanged((SizeChange sizeChange) -> { action.run(); });
    }

    /**
     * Run the provided {@link Action0} when the target changes.
     * @param action The {@link Action0} to run when the target changes.
     * @return A {@link Disposable} that can be disposed to unregister the provided {@link Action0}.
     */
    public Disposable onSizeChanged(Action1<SizeChange> action);

    public default UIDynamicSize scaleWidth(double widthScale)
    {
        return this.scale(widthScale, 1.0);
    }

    public default UIDynamicSize scaleHeight(double heightScale)
    {
        return this.scale(1.0, heightScale);
    }

    public default UIDynamicSize scale(double scale)
    {
        return this.scale(scale, scale);
    }

    public default UIDynamicSize scale(double widthScale, double heightScale)
    {
        return UIDynamicScaledSize.create(this)
            .setWidthScale(widthScale)
            .setHeightScale(heightScale);
    }
}
