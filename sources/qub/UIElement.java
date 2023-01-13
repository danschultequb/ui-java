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
     * A version of a {@link UIElement} that returns its own type from chainable methods.
     * @param <T> The actual type of the {@link UIElement}.
     */
    public static interface Typed<T extends UIElement> extends UIElement
    {
        @Override
        public T setBackgroundColor(Color backgroundColor);
    }
}
