package qub;

/**
 * A {@link UIElement} that displays text.
 */
public interface UIText extends UIElement
{
    @Override
    public UIText setBackgroundColor(Color backgroundColor);

    /**
     * Get the text {@link String} that this {@link UIText} displays.
     */
    public String getText();

    /**
     * Set the text {@link String} that this {@link UIText} displays.
     * @param text The text {@link String} that this {@link UIText} displays.
     * @return This object for method chaining.
     */
    public UIText setText(String text);

    /**
     * A version of a {@link UIText} that returns its own type from chainable methods.
     * @param <T> The actual type of the {@link UIText}.
     */
    public static interface Typed<T extends UIText> extends UIText
    {
        @Override
        public T setBackgroundColor(Color backgroundColor);

        @Override
        public T setText(String text);
    }
}
