package qub;

/**
 * A custom {@link UIElement} that can be used to create different types of {@link UIElement}s.
 */
public interface UICanvas extends UIElement
{
    @Override
    public UICanvas setBackgroundColor(Color backgroundColor);

    @Override
    public UICanvas setWidth(int width);

    @Override
    public UICanvas setWidth(Distance width);

    @Override
    public UICanvas setHeight(int height);

    @Override
    public UICanvas setHeight(Distance height);

    @Override
    public UICanvas setSize(int width, int height);

    @Override
    public UICanvas setSize(Size2Integer size);

    @Override
    public UICanvas setDynamicSize(DynamicSize2Integer dynamicSize);

    /**
     * Set the paint {@link Action1} that will be invoked when this {@link UICanvas} needs to paint
     * itself.
     * @param paintAction The {@link Action1} that will be invoked when this {@link UICanvas}
     *                      needs to paint itself.
     * @return This object for method chaining.
     */
    public UICanvas setPaintAction(Action1<UIPainter> paintAction);

    /**
     * Paint this {@link UIElement} using the provided {@link UIPainter}.
     * @param painter The {@link UIPainter} to use to pain this {@link UIElement}.
     */
    public void paint(UIPainter painter);

    /**
     * A version of a {@link UICanvas} that returns its own types from chainable methods.
     * @param <T> The actual type of the {@link UICanvas}.
     */
    public static interface Typed<T extends UICanvas> extends UICanvas
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
        public T setPaintAction(Action1<UIPainter> paintAction);
    }
}
