package qub;

/**
 * A {@link UIElement} that displays text.
 */
public interface UIText extends UIElement
{
    @Override
    public UIText setBackgroundColor(Color backgroundColor);

    /**
     * Get the text {@link String} that this {@link UIText} displays.
     */
    public String getText();

    /**
     * Set the text {@link String} that this {@link UIText} displays.
     * @param text The text {@link String} that this {@link UIText} displays.
     * @return This object for method chaining.
     */
    public UIText setText(String text);
}
