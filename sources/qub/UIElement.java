package qub;

/**
 * An individual element within a {@link UI}.
 */
public interface UIElement
{
    /**
     * Set the background {@link Color} of this {@link UIElement}.
     * @param backgroundColor The background {@link Color} of this {@link UIElement}.
     * @return This object for method chaining.
     */
    public UIElement setBackgroundColor(Color backgroundColor);

    /**
     * Get the background {@link Color} of this {@link UIElement}.
     */
    public Color getBackgroundColor();

    /**
     * Get the pixel width of this {@link UIElement}.
     */
    public int getWidth();

    /**
     * Get the pixel height of this {@link UIElement}.
     */
    public int getHeight();

    /**
     * Get the pixel size of this {@link UIElement}.
     */
    public default Size2Integer getSize()
    {
        final int width = this.getWidth();
        final int height = this.getHeight();
        return Size2.create(width, height);
    }

    /**
     * Get this {@link UIElement}'s dynamic size.
     */
    public default UIDynamicSize getDynamicSize()
    {
        return UIElementDynamicSize.create(this);
    }

    /**
     * Run the provided {@link Action0} when this {@link UIElement}'s size changes.
     * @param action The {@link Action0} to run when this {@link UIElement}'s size changes.
     * @return A {@link Disposable} that can be disposed to unregister the provided {@link Action0}.
     */
    public default Disposable onSizeChanged(Action0 action)
    {
        PreCondition.assertNotNull(action, "action");

        return this.onSizeChanged((SizeChange sizeChange) -> { action.run(); });
    }

    /**
     * Run the provided {@link Action1} when this {@link UIElement}'s size changes.
     * @param action The {@link Action1} to run when this {@link UIElement}'s size changes.
     * @return A {@link Disposable} that can be disposed to unregister the provided {@link Action1}.
     */
    public Disposable onSizeChanged(Action1<SizeChange> action);

    /**
     * A version of a {@link UIElement} that returns its own type from chainable methods.
     * @param <T> The actual type of the {@link UIElement}.
     */
    public static interface Typed<T extends UIElement> extends UIElement
    {
        @Override
        public T setBackgroundColor(Color backgroundColor);
    }
}
