package qub;

/**
 * An individual element within a {@link UI}.
 */
public interface UIElement
{
    /**
     * Set the background {@link Color} of this {@link UIElement}.
     * @param backgroundColor The background {@link Color} of this {@link UIElement}.
     * @return This object for method chaining.
     */
    public UIElement setBackgroundColor(Color backgroundColor);

    /**
     * Get the background {@link Color} of this {@link UIElement}.
     */
    public Color getBackgroundColor();

    /**
     * Get the pixel width of this {@link UIElement}.
     */
    public int getWidth();

    /**
     * Set the pixel width of this {@link EveryoneUIElement}.
     * @param width The pixel width of this {@link EveryoneUIElement}.
     * @return This object for method chaining.
     */
    public UIElement setWidth(int width);

    /**
     * Set the {@link Distance} width of this {@link EveryoneUIElement}.
     * @param width The pixel width of this {@link EveryoneUIElement}.
     * @return This object for method chaining.
     */
    public UIElement setWidth(Distance width);

    /**
     * Get the pixel height of this {@link UIElement}.
     */
    public int getHeight();

    /**
     * Set the pixel height of this {@link EveryoneUIElement}.
     * @param height The pixel height of this {@link EveryoneUIElement}.
     * @return This object for method chaining.
     */
    public UIElement setHeight(int height);

    /**
     * Set the {@link Distance} height of this {@link EveryoneUIElement}.
     * @param height The {@link Distance} height of this {@link EveryoneUIElement}.
     * @return This object for method chaining.
     */
    public UIElement setHeight(Distance height);

    /**
     * Get the pixel size of this {@link UIElement}.
     */
    public Size2Integer getSize();

    /**
     * Set the pixel width and height of this {@link UIElement}.
     * @param width The pixel width of this {@link UIElement}.
     * @param height The pixel height of this {@link UIElement}.
     * @return This object for method chaining.
     */
    public UIElement setSize(int width, int height);

    /**
     * Set the pixel size of this {@link UIElement}.
     * @param size The new pixel size of this {@link UIElement}.
     * @return This object for method chaining.
     */
    public UIElement setSize(Size2Integer size);

    /**
     * Set the {@link Distance} width and height of this {@link UIElement}.
     * @param width The new {@link Distance} width of this {@link UIElement}.
     * @param height The new {@link Distance} height of this {@link UIElement}.
     * @return This object for method chaining.
     */
    public UIElement setSize(Distance width, Distance height);

    /**
     * Set the {@link Distance} size of this {@link UIElement}.
     * @param size The new {@link Distance} size of this {@link UIElement}.
     * @return This object for method chaining.
     */
    public UIElement setSize(Size2Distance size);

    /**
     * Get the dynamic size of this {@link UIElement} that will always return the current size of
     * this {@link UIElement}.
     */
    public default DynamicSize2Integer getDynamicSize()
    {
        return FunctionDynamicSize2Integer.create()
            .setGetWidthFunction(this::getWidth)
            .setGetHeightFunction(this::getHeight)
            .setOnChangedFunction(this::onSizeChanged);
    }

    /**
     * Set the {@link DynamicSize2Integer} of this {@link UIElement}. This will continue to update
     * this {@link UIElement}'s size whenever the provided {@link DynamicSize2Integer} changes.
     * @param dynamicSize The new {@link DynamicSize2Integer} of this {@link UIElement}.
     * @return This object for method chaining.
     */
    public UIElement setDynamicSize(DynamicSize2Integer dynamicSize);

    /**
     * Run the provided {@link Action0} when this {@link UIElement}'s size changes.
     * @param action The {@link Action0} to run when this {@link UIElement}'s size changes.
     * @return A {@link Disposable} that can be disposed to unregister the provided {@link Action0}.
     */
    public default Disposable onSizeChanged(Action0 action)
    {
        PreCondition.assertNotNull(action, "action");

        return this.onSizeChanged((SizeChange sizeChange) -> { action.run(); });
    }

    /**
     * Run the provided {@link Action1} when this {@link UIElement}'s size changes.
     * @param action The {@link Action1} to run when this {@link UIElement}'s size changes.
     * @return A {@link Disposable} that can be disposed to unregister the provided {@link Action1}.
     */
    public Disposable onSizeChanged(Action1<SizeChange> action);

    /**
     * Get the width of this {@link UIElement}'s content.
     */
    public int getContentWidth();

    /**
     * Get the height of this {@link UIElement}'s content.
     */
    public int getContentHeight();

    /**
     * Get the size of this {@link UIElement}'s content.
     */
    public default Size2Integer getContentSize()
    {
        return Size2.create(this.getContentWidth(), this.getContentHeight());
    }

    /**
     * Run the provided {@link Action0} when this {@link UIElement}'s content size changes.
     * @param action The {@link Action0} to run when this {@link UIElement}'s content size changes.
     * @return A {@link Disposable} that can be disposed to unregister the provided {@link Action0}.
     */
    public default Disposable onContentSizeChanged(Action0 action)
    {
        PreCondition.assertNotNull(action, "action");

        return this.onContentSizeChanged((SizeChange sizeChange) -> { action.run(); });
    }

    /**
     * Run the provided {@link Action1} when this {@link UIElement}'s content size changes.
     * @param action The {@link Action1} to run when this {@link UIElement}'s content size changes.
     * @return A {@link Disposable} that can be disposed to unregister the provided {@link Action1}.
     */
    public Disposable onContentSizeChanged(Action1<SizeChange> action);

    /**
     * Get the dynamic size of this {@link UIElement}'s content that will always return the current
     * size of this {@link UIElement}'s content.
     */
    public default DynamicSize2Integer getContentDynamicSize()
    {
        return FunctionDynamicSize2Integer.create()
            .setGetWidthFunction(this::getContentWidth)
            .setGetHeightFunction(this::getContentHeight)
            .setOnChangedFunction(this::onContentSizeChanged);
    }

    /**
     * Run the provided {@link Action0} when a pointer enters this {@link EveryoneUIElement}.
     * @param action The {@link Action0} to run when a pointer enters this
     *               {@link EveryoneUIElement}.
     * @return A {@link Disposable} that can be disposed to unregister the provided {@link Action0}.
     */
    public default Disposable onPointerEntered(Action0 action)
    {
        PreCondition.assertNotNull(action, "action");

        return this.onPointerEntered((PointerEvent event) -> action.run());
    }

    /**
     * Run the provided {@link Action1} when a pointer enters this {@link EveryoneUIElement}.
     * @param action The {@link Action1} to run when a pointer enters this
     *               {@link EveryoneUIElement}.
     * @return A {@link Disposable} that can be disposed to unregister the provided {@link Action1}.
     */
    public Disposable onPointerEntered(Action1<PointerEvent> action);

    /**
     * Run the provided {@link Action0} when a pointer moves within this {@link EveryoneUIElement}.
     * @param action The {@link Action0} to run when a pointer moves within this
     * {@link EveryoneUIElement}.
     * @return A {@link Disposable} that can be disposed to unregister the provided {@link Action0}.
     */
    public default Disposable onPointerMoved(Action0 action)
    {
        return this.onPointerMoved((PointerEvent event) -> action.run());
    }

    /**
     * Run the provided {@link Action1} when a pointer moves within this {@link EveryoneUIElement}.
     * @param action The {@link Action1} to run when a pointer moves within this
     * {@link EveryoneUIElement}.
     * @return A {@link Disposable} that can be disposed to unregister the provided {@link Action1}.
     */
    public Disposable onPointerMoved(Action1<PointerEvent> action);

    /**
     * Run the provided {@link Action0} when a pointer exits this {@link EveryoneUIElement}.
     * @param action The {@link Action0} to run when a pointer exits this {@link EveryoneUIElement}.
     * @return A {@link Disposable} that can be disposed to unregister the provided {@link Action0}.
     */
    public default Disposable onPointerExited(Action0 action)
    {
        PreCondition.assertNotNull(action, "action");

        return this.onPointerExited((PointerEvent event) -> action.run());
    }

    /**
     * Run the provided {@link Action1} when a pointer exits this {@link EveryoneUIElement}.
     * @param action The {@link Action1} to run when a pointer exits this {@link EveryoneUIElement}.
     * @return A {@link Disposable} that can be disposed to unregister the provided {@link Action1}.
     */
    public Disposable onPointerExited(Action1<PointerEvent> action);

    /**
     * A version of a {@link UIElement} that returns its own type from chainable methods.
     * @param <T> The actual type of the {@link UIElement}.
     */
    public static interface Typed<T extends UIElement> extends UIElement
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
        public T setSize(Distance width, Distance height);

        @Override
        public T setSize(Size2Distance size);

        @Override
        public T setDynamicSize(DynamicSize2Integer dynamicSize);
    }
}
