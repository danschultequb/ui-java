package qub;

/**
 * A {@link UIElement} that displays other {@link UIElement}s in a horizontal stack.
 */
public interface UIHorizontalLayout extends UIElement
{
    @Override
    public UIHorizontalLayout setBackgroundColor(Color backgroundColor);

    @Override
    public UIHorizontalLayout setWidth(int width);

    @Override
    public UIHorizontalLayout setWidth(Distance width);

    @Override
    public UIHorizontalLayout setHeight(int height);

    @Override
    public UIHorizontalLayout setHeight(Distance height);

    @Override
    public UIHorizontalLayout setSize(int width, int height);

    @Override
    public UIHorizontalLayout setSize(Size2Integer size);

    @Override
    public UIHorizontalLayout setDynamicSize(DynamicSize2Integer dynamicSize);

    /**
     * Add the provided {@link UIElement} to this {@link UIHorizontalLayout}.
     * @param uiElement The {@link UIElement} to add to this {@link UIHorizontalLayout}.
     * @return This object for method chaining.
     */
    public UIHorizontalLayout add(UIElement uiElement);

    /**
     * Add the provided {@link UIElement}s to this {@link UIHorizontalLayout}.
     * @param uiElements The {@link UIElement}s to add to this {@link UIHorizontalLayout}.
     * @return This object for method chaining.
     */
    public default UIHorizontalLayout addAll(UIElement... uiElements)
    {
        PreCondition.assertNotNull(uiElements, "uiElements");

        return this.addAll(Iterable.create(uiElements));
    }

    /**
     * Add the provided {@link UIElement}s to this {@link UIHorizontalLayout}.
     * @param uiElements The {@link UIElement}s to add to this {@link UIHorizontalLayout}.
     * @return This object for method chaining.
     */
    public default UIHorizontalLayout addAll(Iterable<? extends UIElement> uiElements)
    {
        PreCondition.assertNotNull(uiElements, "uiElements");

        for (final UIElement uiElement : uiElements)
        {
            this.add(uiElement);
        }

        return this;
    }

    /**
     * A version of a {@link UIHorizontalLayout} that returns its own type from chainable methods.
     * @param <T> The actual type of the {@link UIHorizontalLayout}.
     */
    public static interface Typed<T extends UIHorizontalLayout> extends UIHorizontalLayout
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
        public T add(UIElement uiElement);

        @Override
        @SuppressWarnings("unchecked")
        public default T addAll(UIElement... uiElements)
        {
            return (T)UIHorizontalLayout.super.addAll(uiElements);
        }

        @Override
        @SuppressWarnings("unchecked")
        public default T addAll(Iterable<? extends UIElement> uiElements)
        {
            return (T)UIHorizontalLayout.super.addAll(uiElements);
        }
    }
}
