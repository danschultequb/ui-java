package qub;

/**
 * A {@link UIElement} that displays other {@link UIElement}s in a vertical stack.
 */
public interface UIVerticalLayout extends UIElement
{
    @Override
    public UIVerticalLayout setBackgroundColor(Color backgroundColor);

    @Override
    public UIVerticalLayout setWidth(int width);

    @Override
    public UIVerticalLayout setWidth(Distance width);

    @Override
    public UIVerticalLayout setHeight(int height);

    @Override
    public UIVerticalLayout setHeight(Distance height);

    @Override
    public UIVerticalLayout setSize(int width, int height);

    @Override
    public UIVerticalLayout setSize(Size2Integer size);

    @Override
    public UIVerticalLayout setDynamicSize(DynamicSize2Integer dynamicSize);

    /**
     * Add the provided {@link UIElement} to this {@link UIVerticalLayout}.
     * @param uiElement The {@link UIElement} to add to this {@link UIVerticalLayout}.
     * @return This object for method chaining.
     */
    public UIVerticalLayout add(UIElement uiElement);

    /**
     * Add the provided {@link UIElement}s to this {@link UIVerticalLayout}.
     * @param uiElements The {@link UIElement}s to add to this {@link UIVerticalLayout}.
     * @return This object for method chaining.
     */
    public default UIVerticalLayout addAll(UIElement... uiElements)
    {
        PreCondition.assertNotNull(uiElements, "uiElements");

        return this.addAll(Iterable.create(uiElements));
    }

    /**
     * Add the provided {@link UIElement}s to this {@link UIVerticalLayout}.
     * @param uiElements The {@link UIElement}s to add to this {@link UIVerticalLayout}.
     * @return This object for method chaining.
     */
    public default UIVerticalLayout addAll(Iterable<? extends UIElement> uiElements)
    {
        PreCondition.assertNotNull(uiElements, "uiElements");

        for (final UIElement uiElement : uiElements)
        {
            this.add(uiElement);
        }

        return this;
    }

    /**
     * A version of a {@link UIVerticalLayout} that returns its own type from chainable methods.
     * @param <T> The actual type of the {@link UIVerticalLayout}.
     */
    public static interface Typed<T extends UIVerticalLayout> extends UIVerticalLayout
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
            return (T)UIVerticalLayout.super.addAll(uiElements);
        }

        @Override
        @SuppressWarnings("unchecked")
        public default T addAll(Iterable<? extends UIElement> uiElements)
        {
            return (T)UIVerticalLayout.super.addAll(uiElements);
        }
    }
}
