package qub;

/**
 * A custom {@link UIElement} that can be used to create different types of {@link UIElement}s.
 */
public interface UICanvas extends UIElement
{
    @Override
    public UICanvas setBackgroundColor(Color backgroundColor);

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
        public T setPaintAction(Action1<UIPainter> paintAction);
    }
}
