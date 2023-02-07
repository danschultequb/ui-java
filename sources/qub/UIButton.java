package qub;

/**
 * A {@link UIElement} that can be clicked.
 */
public interface UIButton extends UIElement
{
    @Override
    public UIButton setBackgroundColor(Color backgroundColor);

    @Override
    public UIButton setWidth(int width);

    @Override
    public UIButton setWidth(Distance width);

    @Override
    public UIButton setHeight(int height);

    @Override
    public UIButton setHeight(Distance height);

    @Override
    public UIButton setSize(int width, int height);

    @Override
    public UIButton setSize(Size2Integer size);

    @Override
    public UIButton setDynamicSize(DynamicSize2Integer dynamicSize);

    /**
     * Get the text of this {@link UIButton}.
     */
    public String getText();

    /**
     * Set the text of this {@link UIButton}.
     * @param text The text of this {@link UIButton}.
     * @return This object for method chaining.
     */
    public UIButton setText(String text);

    /**
     * Register the provided {@link Action0} to be invoked when this {@link UIButton} is clicked.
     * @param callback The {@link Action0} to invoke when this {@link UIButton} is clicked.
     * @return A {@link Disposable} that can be disposed to unregister the provided {@link Action0}.
     */
    public Disposable onClick(Action0 callback);

    /**
     * A version of a {@link UIButton} that returns its own type from chainable methods.
     * @param <T> The actual type of the {@link UIButton}.
     */
    public interface Typed<T extends UIButton> extends UIButton
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
