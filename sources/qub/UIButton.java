package qub;

/**
 * A {@link UIElement} that can be clicked.
 */
public interface UIButton extends UIElement
{
    @Override
    public UIButton setBackgroundColor(Color backgroundColor);

    /**
     * Get the text of this {@link UIButton}.
     */
    public String getText();

    /**
     * Set the text of this {@link UIButton}.
     * @param text The text of this {@link UIButton}.
     * @return This object for method chaining.
     */
    public UIButton setText(String text);

    /**
     * Register the provided {@link Action0} to be invoked when this {@link UIButton} is clicked.
     * @param callback The {@link Action0} to invoke when this {@link UIButton} is clicked.
     * @return A {@link Disposable} that can be disposed to unregister the provided {@link Action0}.
     */
    public Disposable onClick(Action0 callback);
}
