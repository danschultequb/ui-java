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
}
