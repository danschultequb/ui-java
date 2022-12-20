package qub;

/**
 * A {@link UIElement} that displays other {@link UIElement}s in a horizontal stack.
 */
public interface UIHorizontalLayout extends UIElement
{
    @Override
    public UIHorizontalLayout setBackgroundColor(Color backgroundColor);

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
}
