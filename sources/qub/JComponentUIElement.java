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
    public default JComponentUIElement setWidth(int width)
    {
        return this.setSize(width, this.getHeight());
    }

    @Override
    public JComponentUIElement setWidth(Distance width);

    @Override
    public default int getHeight()
    {
        final javax.swing.JComponent jComponent = this.getJComponent();
        final int result = jComponent.getHeight();

        PostCondition.assertGreaterThanOrEqualTo(result, 0, "result");

        return result;
    }

    @Override
    public default JComponentUIElement setHeight(int height)
    {
        return this.setSize(this.getWidth(), height);
    }

    @Override
    public JComponentUIElement setHeight(Distance height);

    @Override
    public default Size2Integer getSize()
    {
        return JavaAwtComponents.getSize(this.getJComponent());
    }

    @Override
    public default JComponentUIElement setSize(int width, int height)
    {
        JavaAwtComponents.setSize(this.getJComponent(), width, height);

        return this;
    }

    @Override
    public default JComponentUIElement setSize(Size2Integer size)
    {
        PreCondition.assertNotNull(size, "size");

        return this.setSize(size.getWidthAsInt(), size.getHeightAsInt());
    }

    @Override
    public JComponentUIElement setSize(Distance width, Distance height);

    @Override
    public default JComponentUIElement setSize(Size2Distance size)
    {
        PreCondition.assertNotNull(size, "size");

        return this.setSize(size.getWidth(), size.getHeight());
    }

    @Override
    public default Disposable onSizeChanged(Action1<SizeChange> action)
    {
        return JavaAwtComponents.onSizeChanged(this.getJComponent(), action);
    }

    @Override
    public JComponentUIElement setDynamicSize(DynamicSize2Integer dynamicSize);

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

        @Override
        @SuppressWarnings("unchecked")
        public default T setWidth(int width)
        {
            return (T)JComponentUIElement.super.setWidth(width);
        }

        @Override
        public T setWidth(Distance width);

        @Override
        @SuppressWarnings("unchecked")
        public default T setHeight(int height)
        {
            return (T)JComponentUIElement.super.setHeight(height);
        }

        @Override
        public T setHeight(Distance height);

        @Override
        @SuppressWarnings("unchecked")
        public default T setSize(Size2Integer size)
        {
            return (T)JComponentUIElement.super.setSize(size);
        }

        @Override
        @SuppressWarnings("unchecked")
        public default T setSize(int width, int height)
        {
            return (T)JComponentUIElement.super.setSize(width, height);
        }

        @Override
        public T setSize(Distance width, Distance height);

        @Override
        @SuppressWarnings("unchecked")
        public default T setSize(Size2Distance size)
        {
            return (T)JComponentUIElement.super.setSize(size);
        }
    }

    /**
     * An abstract base class implementation of {@link JComponentUIElement} that contains common
     * properties and functions for all {@link JComponentUIElement} types.
     * @param <T> The actual type of the {@link JComponentUIElement}.
     */
    public static abstract class Base<T extends JComponentUIElement> implements JComponentUIElement.Typed<T>
    {
        private final SwingUI ui;
        private Disposable sizeChangedSubscription;

        protected Base(SwingUI ui)
        {
            PreCondition.assertNotNull(ui, "ui");

            this.ui = ui;
        }

        @Override
        public T setBackgroundColor(Color backgroundColor)
        {
            return JComponentUIElement.Typed.super.setBackgroundColor(backgroundColor);
        }

        @Override
        public T setWidth(int width)
        {
            return JComponentUIElement.Typed.super.setWidth(width);
        }

        @Override
        public T setWidth(Distance width)
        {
            PreCondition.assertNotNull(width, "width");
            PreCondition.assertGreaterThanOrEqualTo(width, Distance.zero, "width");

            return this.setWidth(this.ui.convertHorizontalDistanceToPixels(width));
        }

        @Override
        public T setHeight(int height)
        {
            return JComponentUIElement.Typed.super.setHeight(height);
        }

        @Override
        public T setHeight(Distance height)
        {
            PreCondition.assertNotNull(height, "height");
            PreCondition.assertGreaterThanOrEqualTo(height, Distance.zero, "height");

            return this.setHeight(this.ui.convertVerticalDistanceToPixels(height));
        }

        @Override
        public T setSize(Size2Integer size)
        {
            return JComponentUIElement.Typed.super.setSize(size);
        }

        @Override
        public T setSize(int width, int height)
        {
            return this.setSize(width, height, true);
        }

        private T setSize(int width, int height, boolean clearSizeChangedSubscription)
        {
            PreCondition.assertGreaterThanOrEqualTo(width, 0, "width");
            PreCondition.assertGreaterThanOrEqualTo(height, 0, "height");

            if (this.sizeChangedSubscription != null && clearSizeChangedSubscription)
            {
                this.sizeChangedSubscription.dispose().await();
                this.sizeChangedSubscription = null;
            }

            return JComponentUIElement.Typed.super.setSize(width, height);
        }

        @Override
        public T setSize(Distance width, Distance height)
        {
            PreCondition.assertGreaterThanOrEqualTo(width, Distance.zero, "width");
            PreCondition.assertGreaterThanOrEqualTo(height, Distance.zero, "height");

            return this.setSize(this.ui.convertHorizontalDistanceToPixels(width), this.ui.convertVerticalDistanceToPixels(height));
        }

        @Override
        @SuppressWarnings("unchecked")
        public T setDynamicSize(DynamicSize2Integer dynamicSize)
        {
            PreCondition.assertNotNull(dynamicSize, "dynamicSize");

            this.setSize(dynamicSize);
            this.sizeChangedSubscription = dynamicSize.onChanged((SizeChange sizeChange) ->
            {
                this.setSize(sizeChange.getNewWidth(), sizeChange.getNewHeight(), false);
            });

            return (T)this;
        }

        @Override
        public int getContentWidth()
        {
            return this.getWidth();
        }

        @Override
        public int getContentHeight()
        {
            return this.getHeight();
        }

        @Override
        public Disposable onContentSizeChanged(Action1<SizeChange> action)
        {
            return this.onSizeChanged(action);
        }

        @Override
        public Disposable onPointerEntered(Action1<PointerEvent> action)
        {
            return null;
        }

        @Override
        public Disposable onPointerMoved(Action1<PointerEvent> action)
        {
            return null;
        }

        @Override
        public Disposable onPointerExited(Action1<PointerEvent> action)
        {
            return null;
        }
    }
}
