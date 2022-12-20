package qub;

/**
 * An object that can be used to create user interfaces.
 */
public interface UI extends Disposable
{
    /**
     * Convert the provided horizontal {@link Distance} to pixels based on this {@link UI}'s
     * horizontal pixels per inch property.
     * @param horizontalDistance The horizontal {@link Distance} to convert.
     */
    public int convertHorizontalDistanceToPixels(Distance horizontalDistance);

    /**
     * Convert the provided vertical {@link Distance} to pixels based on this {@link UI}'s vertical
     * pixels per inch property.
     * @param verticalDistance The vertical {@link Distance} to convert.
     */
    public int convertVerticalDistanceToPixels(Distance verticalDistance);

    /**
     * Convert the provided horizontal pixels to {@link Distance} based on this {@link UI}'s
     * horizontal pixels per inch property.
     * @param horizontalPixels The horizontal pixels to convert.
     */
    public Distance convertHorizontalPixelsToDistance(int horizontalPixels);

    /**
     * Convert the provided vertical pixels to {@link Distance} based on this {@link UI}'s vertical
     * pixels per inch property.
     * @param verticalPixels The vertical pixels to convert.
     */
    public Distance convertVerticalPixelsToDistance(int verticalPixels);

    /**
     * Set the creator function that will be used when an object of the type {@link U} is
     * requested.
     * @param uiElementType The type that will invoke the provided uiElementCreator when it is
     *                      requested.
     * @param uiElementCreator The creator that will be invoked when a uiElementType is requested.
     * @param <U> The type of {@link UIElement} that will invoke the provided uiElementCreator when
     *           it is requested.
     * @param <T> The {@link UIElement} type that will be created.
     * @return This object for method chaining.
     */
    public <U extends UIElement, T extends U> UI setCreator(Class<? extends U> uiElementType, Function0<T> uiElementCreator);

    /**
     * Set the creator function that will be used when an object of the type {@link U} is
     * requested.
     * @param uiElementTypes The types of {@link UIElement} that will invoke the provided
     *                       uiElementCreator when it is requested.
     * @param uiElementCreator The creator that will be invoked when any of the uiElementTypes are
     *                         requested.
     * @param <U> The type of {@link UIElement} that will invoke the provided uiElementCreator when
     *           it is requested.
     * @param <T> The {@link UIElement} type that will be created.
     * @return This object for method chaining.
     */
    public default <U extends UIElement, T extends U> UI setCreator(Iterable<Class<? extends U>> uiElementTypes, Function0<T> uiElementCreator)
    {
        PreCondition.assertNotNullAndNotEmpty(uiElementTypes, "uiElementTypes");
        PreCondition.assertNotNull(uiElementCreator, "uiElementCreator");

        for (final Class<? extends U> uiType : uiElementTypes)
        {
            this.setCreator(uiType, uiElementCreator);
        }

        return this;
    }

    /**
     * Create a new {@link UIElement} with the provided type.
     * @param uiElementType The type of the {@link UIElement} to create.
     * @param <T> The type of the {@link UIElement} to create.
     * @return The created {@link UIElement}.
     */
    public <T extends UIElement> Result<T> create(Class<T> uiElementType);

    /**
     * Create a new {@link UIButton}.
     */
    public default Result<? extends UIButton> createUIButton()
    {
        return this.create(UIButton.class);
    }

    /**
     * Create a new {@link UIText}.
     */
    public default Result<? extends UIText> createUIText()
    {
        return this.create(UIText.class);
    }

//    /**
//     * Create a new UITextBox.
//     * @return The new UITextBox.
//     */
//    default Result<? extends UITextBox> createUITextBox()
//    {
//        return this.create(UITextBox.class);
//    }

    /**
     * Create a new {@link UIVerticalLayout}.
     */
    public default Result<? extends UIVerticalLayout> createUIVerticalLayout()
    {
        return this.create(UIVerticalLayout.class);
    }

//    /**
//     * Create a new UIHorizontalLayout.
//     * @return The new UIHorizontalLayout.
//     */
//    default Result<? extends UIHorizontalLayout> createUIHorizontalLayout()
//    {
//        return this.create(UIHorizontalLayout.class);
//    }
}
