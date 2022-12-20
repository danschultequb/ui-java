package qub;

/**
 * A {@link UIElement} that contains a {@link javax.swing.JComponent}.
 */
public interface JComponentUIElement extends UIElement
{
    @Override
    public JComponentUIElement setBackgroundColor(Color backgroundColor);

    /**
     * Get the {@link javax.swing.JComponent} that this {@link UIElement} wraps around.
     */
    public javax.swing.JComponent getJComponent();
}
