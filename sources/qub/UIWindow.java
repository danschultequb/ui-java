package qub;

/**
 * A user interface window.
 */
public interface UIWindow extends Disposable
{
    /**
     * Set the background {@link Color} of this {@link UIWindow}.
     * @param backgroundColor The background {@link Color} of this {@link UIWindow}.
     * @return This object for method chaining.
     */
    public UIWindow setBackgroundColor(Color backgroundColor);

    /**
     * Get the background {@link Color} of this {@link UIWindow}.
     */
    public Color getBackgroundColor();

    /**
     * Get the pixel width of this {@link UIWindow}.
     */
    public int getWidth();

    /**
     * Get the pixel height of this {@link UIWindow}.
     */
    public int getHeight();

    /**
     * Get the pixel size of this {@link UIWindow}.
     */
    public Size2Integer getSize();

    /**
     * Get the dynamic pixel size of this {@link UIWindow} that will always return the current size
     * of this {@link UIWindow}.
     */
    public default DynamicSize2Integer getDynamicSize()
    {
        return FunctionDynamicSize2Integer.create()
            .setGetWidthFunction(this::getWidth)
            .setGetHeightFunction(this::getHeight)
            .setOnChangedFunction(this::onSizeChanged);
    }

    /**
     * Run the provided {@link Action0} when this {@link UIWindow}'s size changes.
     * @param action The {@link Action0} to run when this {@link UIWindow}'s size changes.
     * @return A {@link Disposable} that can be disposed to unregister the provided {@link Action0}.
     */
    public default Disposable onSizeChanged(Action0 action)
    {
        PreCondition.assertNotNull(action, "action");

        return this.onSizeChanged((SizeChange sizeChange) -> { action.run(); });
    }

    /**
     * Run the provided {@link Action1} when this {@link UIWindow}'s size changes.
     * @param action The {@link Action1} to run when this {@link UIWindow}'s size changes.
     * @return A {@link Disposable} that can be disposed to unregister the provided {@link Action1}.
     */
    public Disposable onSizeChanged(Action1<SizeChange> action);

    /**
     * Set the title of this {@link UIWindow}.
     * @param title The title of this {@link UIWindow}.
     * @return This object for method chaining.
     */
    public UIWindow setTitle(String title);

    /**
     * Get the title of this {@link UIWindow}.
     */
    public String getTitle();

    /**
     * Get the width of the area that is designated for content.
     */
    public int getContentAreaWidth();

    /**
     * Get the height of the area that is designated for content.
     */
    public int getContentAreaHeight();

    /**
     * Get the size of the area that is designated for content.
     */
    public Size2Integer getContentAreaSize();

    /**
     * Get the dynamic pixel size of this {@link UIWindow} that will always return the current size
     * of the area that is designated for content.
     */
    public default DynamicSize2Integer getContentAreaDynamicSize()
    {
        return FunctionDynamicSize2Integer.create()
            .setGetWidthFunction(this::getContentAreaWidth)
            .setGetHeightFunction(this::getContentAreaHeight)
            .setOnChangedFunction(this::onContentAreaSizeChanged);
    }

    /**
     * Run the provided {@link Action0} when this {@link UIWindow}'s content area size changes.
     * @param action The {@link Action0} to run when this {@link UIWindow}'s content area size
     *               changes.
     * @return A {@link Disposable} that can be disposed to unregister the provided {@link Action0}.
     */
    public default Disposable onContentAreaSizeChanged(Action0 action)
    {
        PreCondition.assertNotNull(action, "action");

        return this.onContentAreaSizeChanged((SizeChange sizeChange) -> { action.run(); });
    }

    /**
     * Run the provided {@link Action1} when this {@link UIWindow}'s content area size changes.
     * @param action The {@link Action1} to run when this {@link UIWindow}'s content area size
     *               changes.
     * @return A {@link Disposable} that can be disposed to unregister the provided {@link Action1}.
     */
    public Disposable onContentAreaSizeChanged(Action1<SizeChange> action);

    /**
     * Get the {@link UIElement} content of this {@link UIWindow}.
     */
    public Result<? extends UIElement> getContent();

    /**
     * Set the {@link UIElement} content of this {@link UIWindow}.
     * @param content The {@link UIElement} content to set.
     * @return This object for method chaining.
     */
    public UIWindow setContent(UIElement content);

    /**
     * A version of a {@link UIWindow} that returns its own type from chainable methods.
     * @param <T> The actual type of the {@link UIWindow}.
     */
    public static interface Typed<T extends UIWindow> extends UIWindow
    {
        @Override
        public T setBackgroundColor(Color backgroundColor);

        @Override
        public T setTitle(String title);

        @Override
        public T setContent(UIElement content);
    }
}
