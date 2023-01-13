package qub;

/**
 * A user interface window.
 */
public interface UIWindow extends UIElement.Typed<UIWindow>, Disposable
{
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
