package qub;

/**
 * A user interface window.
 */
public interface UIWindow extends UIElement, Disposable
{
    @Override
    public UIWindow setBackgroundColor(Color backgroundColor);

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
     * Get the width and height pixel counts of this {@link UIWindow}.
     */
    public default Size2Integer getSize()
    {
        return MutableSize2Integer.create(this.getWidth(), this.getHeight());
    }

    /**
     * Get the width and height {@link Distance}s of this {@link UIWindow}.
     */
    public default Size2Distance getSizeDistances()
    {
        final Distance width = this.getWidthDistance();
        final Distance height = this.getHeightDistance();
        final Size2Distance result = Size2.create(width, height);

        PostCondition.assertNotNull(result, "result");

        return result;
    }

    /**
     * Get the width pixel count of this {@link UIWindow}.
     */
    public int getWidth();

    /**
     * Get the width {@link Distance} of this {@link UIWindow}.
     */
    public Distance getWidthDistance();

    /**
     * Get the height pixel count of this {@link UIWindow}.
     */
    public int getHeight();

    /**
     * Get the height {@link Distance} of this {@link UIWindow}.
     */
    public Distance getHeightDistance();

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
}
