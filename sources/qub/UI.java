package qub;

/**
 * An object that can be used to create user interfaces.
 */
public interface UI extends Disposable
{
    /**
     * Get the number of pixels that fit into a horizontal inch.
     */
    public int getHorizontalPixelsPerInch();

    /**
     * Get the number of pixels that fit into a vertical inch.
     */
    public int getVerticalPixelsPerInch();

    /**
     * Convert the provided horizontal {@link Distance} to pixels based on this {@link UI}'s
     * horizontal pixels per inch property.
     * @param horizontalDistance The horizontal {@link Distance} to convert.
     */
    public default int convertHorizontalDistanceToPixels(Distance horizontalDistance)
    {
        PreCondition.assertNotNull(horizontalDistance, "horizontalDistance");

        final double horizontalDistanceInInches = horizontalDistance.toInches().getValue();
        final int result = (int)(horizontalDistanceInInches * this.getHorizontalPixelsPerInch());

        return result;
    }

    /**
     * Convert the provided vertical {@link Distance} to pixels based on this {@link UI}'s vertical
     * pixels per inch property.
     * @param verticalDistance The vertical {@link Distance} to convert.
     */
    public default int convertVerticalDistanceToPixels(Distance verticalDistance)
    {
        PreCondition.assertNotNull(verticalDistance, "verticalDistance");

        final double verticalDistanceInInches = verticalDistance.toInches().getValue();
        final int result = (int)(verticalDistanceInInches * this.getVerticalPixelsPerInch());

        return result;
    }

    /**
     * Convert the provided horizontal pixels to {@link Distance} based on this {@link UI}'s
     * horizontal pixels per inch property.
     * @param horizontalPixels The horizontal pixels to convert.
     */
    public default Distance convertHorizontalPixelsToDistance(int horizontalPixels)
    {
        final double horizontalDistanceInInches = horizontalPixels / (double)this.getHorizontalPixelsPerInch();
        final Distance result = Distance.inches(horizontalDistanceInInches);

        PostCondition.assertNotNull(result, "result");

        return result;
    }

    /**
     * Convert the provided vertical pixels to {@link Distance} based on this {@link UI}'s vertical
     * pixels per inch property.
     * @param verticalPixels The vertical pixels to convert.
     */
    public default Distance convertVerticalPixelsToDistance(int verticalPixels)
    {
        final double verticalDistanceInInches = verticalPixels / (double)this.getVerticalPixelsPerInch();
        final Distance result = Distance.inches(verticalDistanceInInches);

        PostCondition.assertNotNull(result, "result");

        return result;
    }

    /**
     * Convert the provided pixel width and height to a {@link Size2Distance} object based on this
     * {@link UI}'s pixel density.
     * @param width The pixel width to convert.
     * @param height The pixel height to convert.
     */
    public default Size2Distance convertSizePixelsToDistance(int width, int height)
    {
        PreCondition.assertGreaterThanOrEqualTo(width, 0, "width");
        PreCondition.assertGreaterThanOrEqualTo(height, 0, "height");

        final Distance widthDistance = this.convertHorizontalPixelsToDistance(width);
        final Distance heightDistance = this.convertVerticalPixelsToDistance(height);
        final Size2Distance result = Size2.create(widthDistance, heightDistance);

        PostCondition.assertNotNull(result, "result");

        return result;
    }

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

    /**
     * Create a new {@link UITextBox}.
     */
    public default Result<? extends UITextBox> createUITextBox()
    {
        return this.create(UITextBox.class);
    }

    /**
     * Create a new {@link UIVerticalLayout}.
     */
    public default Result<? extends UIVerticalLayout> createUIVerticalLayout()
    {
        return this.create(UIVerticalLayout.class);
    }

    /**
     * Create a new {@link UIHorizontalLayout}.
     */
    public default Result<? extends UIHorizontalLayout> createUIHorizontalLayout()
    {
        return this.create(UIHorizontalLayout.class);
    }

    /**
     * Create a new {@link UICanvas}.
     */
    public default Result<? extends UICanvas> createUICanvas()
    {
        return this.create(UICanvas.class);
    }

    /**
     * A version of a {@link UI} that returns its own type from chainable methods.
     * @param <UIType> The actual type of the {@link UI}.
     */
    public static interface Typed<UIType extends UI> extends UI
    {
        @Override
        public <U extends UIElement, T extends U> UIType setCreator(Class<? extends U> uiElementType, Function0<T> uiElementCreator);

        @Override
        @SuppressWarnings("unchecked")
        public default <U extends UIElement, T extends U> UIType setCreator(Iterable<Class<? extends U>> uiElementTypes, Function0<T> uiElementCreator)
        {
            return (UIType)UI.super.setCreator(uiElementTypes, uiElementCreator);
        }
    }

    /**
     * An abstract base class implementation of {@link UI} that contains common
     * properties and functions for all {@link UI} types.
     * @param <UIType> The actual type of the {@link UI}.
     */
    public static abstract class Base<UIType extends UI> implements UI.Typed<UIType>
    {
        private final MutableMap<Class<? extends UIElement>,Function0<? extends UIElement>> uiCreators;

        private int horizontalPixelsPerInch;
        private int verticalPixelsPerInch;

        protected Base()
        {
            this.uiCreators = Map.create();
        }

        @Override
        @SuppressWarnings("unchecked")
        public <U extends UIElement, T extends U> UIType setCreator(Class<? extends U> uiElementType, Function0<T> uiElementCreator)
        {
            PreCondition.assertNotNull(uiElementType, "uiElementType");
            PreCondition.assertNotNull(uiElementCreator, "uiElementCreator");

            this.uiCreators.set(uiElementType, uiElementCreator);

            return (UIType)this;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T extends UIElement> Result<T> create(Class<T> uiElementType)
        {
            PreCondition.assertNotNull(uiElementType, "uiElementType");

            return Result.create(() ->
            {
                final Function0<? extends UIElement> creatorFunction = this.uiCreators.get(uiElementType)
                    .convertError(NotFoundException.class, () -> new NotFoundException("No " + Types.getTypeName(UIElement.class) + " creator function registered for " + Types.getTypeName(UIElement.class) + " type " + Types.getFullTypeName(uiElementType) + "."))
                    .await();
                final UIElement result = creatorFunction.run();

                PostCondition.assertInstanceOf(result, uiElementType, "result");

                return (T)result;
            });
        }

        @SuppressWarnings("unchecked")
        public UIType setPixelsPerInch(int pixelsPerInch)
        {
            PreCondition.assertGreaterThan(pixelsPerInch, 0, "pixelsPerInch");

            this.setHorizontalPixelsPerInch(pixelsPerInch);
            this.setVerticalPixelsPerInch(pixelsPerInch);

            return (UIType)this;
        }

        @Override
        public int getHorizontalPixelsPerInch()
        {
            return this.horizontalPixelsPerInch;
        }

        @SuppressWarnings("unchecked")
        public UIType setHorizontalPixelsPerInch(int horizontalPixelsPerInch)
        {
            PreCondition.assertGreaterThan(horizontalPixelsPerInch, 0, "horizontalPixelsPerInch");

            this.horizontalPixelsPerInch = horizontalPixelsPerInch;

            return (UIType)this;
        }

        @Override
        public int getVerticalPixelsPerInch()
        {
            return this.verticalPixelsPerInch;
        }

        @SuppressWarnings("unchecked")
        public UIType setVerticalPixelsPerInch(int verticalPixelsPerInch)
        {
            PreCondition.assertGreaterThan(verticalPixelsPerInch, 0, "verticalPixelsPerInch");

            this.verticalPixelsPerInch = verticalPixelsPerInch;

            return (UIType)this;
        }
    }
}
