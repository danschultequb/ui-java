package qub;

/**
 * A {@link UIElement} that displays text.
 */
public interface UIText extends UIElement
{
    @Override
    public UIText setBackgroundColor(Color backgroundColor);

    @Override
    public UIText setWidth(int width);

    @Override
    public UIText setWidth(Distance width);

    @Override
    public UIText setHeight(int height);

    @Override
    public UIText setHeight(Distance height);

    @Override
    public UIText setSize(int width, int height);

    @Override
    public UIText setSize(Size2Integer size);

    @Override
    public UIText setDynamicSize(DynamicSize2Integer dynamicSize);

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
        public T setWidth(int width);

        @Override
        public T setWidth(Distance width);

        @Override
        public T setHeight(int height);

        @Override
        public T setHeight(Distance height);

        @Override
        public T setSize(int width, int height);

        @Override
        public T setSize(Size2Integer size);

        @Override
        public T setDynamicSize(DynamicSize2Integer dynamicSize);

        @Override
        public T setText(String text);
    }
}
