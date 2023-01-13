package qub;

/**
 * A {@link UIElement} that contains a {@link javax.swing.JComponent}.
 */
public interface JComponentUIElement extends UIElement
{
    @Override
    public default JComponentUIElement setBackgroundColor(Color backgroundColor)
    {
        PreCondition.assertNotNull(backgroundColor, "backgroundColor");

        final javax.swing.JComponent jComponent = this.getJComponent();
        JavaAwtComponents.setBackgroundColor(jComponent, backgroundColor);

        final boolean isOpaque = (backgroundColor.getAlpha() != 0);
        jComponent.setOpaque(isOpaque);

        return this;
    }

    @Override
    public default Color getBackgroundColor()
    {
        final javax.swing.JComponent jComponent = this.getJComponent();
        return JavaAwtComponents.getBackgroundColor(jComponent);
    }

    @Override
    public default int getWidth()
    {
        final javax.swing.JComponent jComponent = this.getJComponent();
        final int result = jComponent.getWidth();

        PostCondition.assertGreaterThanOrEqualTo(result, 0, "result");

        return result;
    }

    @Override
    public default int getHeight()
    {
        final javax.swing.JComponent jComponent = this.getJComponent();
        final int result = jComponent.getHeight();

        PostCondition.assertGreaterThanOrEqualTo(result, 0, "result");

        return result;
    }

    @Override
    public default Size2Integer getSize()
    {
        final int width = this.getWidth();
        final int height = this.getHeight();
        return Size2.create(width, height);
    }

    /**
     * Get the {@link javax.swing.JComponent} that this {@link UIElement} wraps around.
     */
    public javax.swing.JComponent getJComponent();

    /**
     * A version of a {@link UIText} that returns its own type from chainable methods.
     * @param <T> The actual type of the {@link UIText}.
     */
    public static interface Typed<T extends JComponentUIElement> extends JComponentUIElement
    {
        @Override
        @SuppressWarnings("unchecked")
        public default T setBackgroundColor(Color backgroundColor)
        {
            return (T)JComponentUIElement.super.setBackgroundColor(backgroundColor);
        }
    }
}
