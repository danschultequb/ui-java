package qub;

public interface EveryoneUIElement extends UIElement
{
    /**
     * Get this {@link EveryoneUIElement}'s parent.
     */
    public EveryoneUIElementParent getParent();

    /**
     * Set this {@link EveryoneUIElement}'s parent.
     * @param parent The new parent for this {@link EveryoneUIElement}.
     * @return This object for method chaining.
     */
    public EveryoneUIElement setParent(EveryoneUIElementParent parent);

    /**
     * Request that this {@link EveryoneUIElement} repaint itself.
     */
    public default void repaint()
    {
        final EveryoneUIElementParent parent = this.getParent();
        if (parent != null)
        {
            parent.repaint();
        }
    }

    @Override
    public EveryoneUIElement setBackgroundColor(Color backgroundColor);

    @Override
    public EveryoneUIElement setWidth(int width);

    /**
     * Set the {@link Distance} width of this {@link EveryoneUIElement}.
     * @param width The {@link Distance} width of this {@link EveryoneUIElement}.
     * @return This object for method chaining.
     */
    public EveryoneUIElement setWidth(Distance width);

    @Override
    public EveryoneUIElement setHeight(int height);

    /**
     * Set the {@link Distance} height of this {@link EveryoneUIElement}.
     * @param height The {@link Distance} height of this {@link EveryoneUIElement}.
     * @return This object for method chaining.
     */
    public EveryoneUIElement setHeight(Distance height);

    @Override
    public EveryoneUIElement setSize(int width, int height);

    /**
     * Set the {@link Distance} width and height of this {@link EveryoneUIElement}.
     * @param width The new {@link Distance} width of this {@link EveryoneUIElement}.
     * @param height The new {@link Distance} height of this {@link EveryoneUIElement}.
     * @return This object for method chaining.
     */
    public EveryoneUIElement setSize(Distance width, Distance height);

    @Override
    public EveryoneUIElement setSize(Size2Integer size);

    /**
     * Set the {@link Distance} size of this {@link EveryoneUIElement}.
     * @param size The new {@link Distance} size of this {@link EveryoneUIElement}.
     * @return This object for method chaining.
     */
    public EveryoneUIElement setSize(Size2Distance size);

    @Override
    public EveryoneUIElement setDynamicSize(DynamicSize2Integer dynamicSize);

    /**
     * Paint this {@link EveryoneUIElement} using the provided {@link UIPainter}.
     * @param painter The {@link UIPainter} to use to paint this {@link EveryoneUIElement}.
     */
    public void paint(UIPainter painter);

    /**
     * A version of a {@link EveryoneUIElement} that returns its own type from chainable methods.
     * @param <T> The actual type of the {@link EveryoneUIElement}.
     */
    public static interface Typed<T extends EveryoneUIElement> extends EveryoneUIElement
    {
        @Override
        public T setParent(EveryoneUIElementParent parent);

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
        public T setSize(Distance width, Distance height);

        @Override
        public T setSize(Size2Integer size);

        @Override
        public T setSize(Size2Distance size);

        @Override
        public T setDynamicSize(DynamicSize2Integer dynamicSize);
    }

    /**
     * An abstract base class implementation of {@link EveryoneUIElement} that contains common
     * properties and functions for all {@link EveryoneUIElement} types.
     * @param <T> The actual type of the {@link EveryoneUIElement}.
     */
    public static class Base<T extends EveryoneUIElement> implements EveryoneUIElement.Typed<T>
    {
        private final EveryoneSwingUI ui;
        private EveryoneUIElementParent parent;
        private int width;
        private int height;
        private Disposable sizeChangedSubscription;
        private RunnableEvent1<SizeChange> sizeChanged;
        private Color backgroundColor;

        protected Base(EveryoneSwingUI ui)
        {
            PreCondition.assertNotNull(ui, "ui");

            this.ui = ui;
            this.backgroundColor = Color.transparent;
        }

        @Override
        public EveryoneUIElementParent getParent()
        {
            return this.parent;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T setParent(EveryoneUIElementParent parent)
        {
            this.parent = parent;

            return (T)this;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T setBackgroundColor(Color backgroundColor)
        {
            PreCondition.assertNotNull(backgroundColor, "backgroundColor");

            if (!this.backgroundColor.equals(backgroundColor))
            {
                this.backgroundColor = backgroundColor;
                this.repaint();
            }

            return (T)this;
        }

        @Override
        public void paint(UIPainter painter)
        {
            PreCondition.assertNotNull(painter, "painter");

            if (this.backgroundColor.getAlpha() != Color.ComponentMin)
            {
                painter.drawRectangle(DrawRectangleOptions.create()
                    .setLeftX(0)
                    .setTopY(0)
                    .setWidth(this.getWidth())
                    .setHeight(this.getHeight())
                    .setLineColor(Color.transparent)
                    .setFillColor(this.backgroundColor));
            }
        }

        @Override
        public Color getBackgroundColor()
        {
            final Color result = this.backgroundColor;

            PostCondition.assertNotNull(result, "result");

            return result;
        }

        @Override
        public int getWidth()
        {
            return this.width;
        }

        @Override
        public T setWidth(int width)
        {
            return this.setSize(width, this.height);
        }

        @Override
        public T setWidth(Distance width)
        {
            PreCondition.assertNotNull(width, "width");
            PreCondition.assertGreaterThanOrEqualTo(width, Distance.zero, "width");

            return this.setWidth(this.ui.convertHorizontalDistanceToPixels(width));
        }

        @Override
        public int getHeight()
        {
            return this.height;
        }

        @Override
        public DynamicSize2Integer getSize()
        {
            return DynamicSize2Integer.create()
                .setGetWidthFunction(this::getWidth)
                .setGetHeightFunction(this::getHeight)
                .setOnChangedFunction(this::onSizeChanged);
        }

        @Override
        public T setHeight(int height)
        {
            return this.setSize(this.width, height);
        }

        @Override
        public T setHeight(Distance height)
        {
            PreCondition.assertNotNull(height, "height");
            PreCondition.assertGreaterThanOrEqualTo(height, Distance.zero, "height");

            return this.setHeight(this.ui.convertVerticalDistanceToPixels(height));
        }

        @Override
        public T setSize(int width, int height)
        {
            return this.setSize(width, height, true);
        }

        @SuppressWarnings("unchecked")
        private T setSize(int width, int height, boolean clearSizeChangedSubscription)
        {
            PreCondition.assertGreaterThanOrEqualTo(width, 0, "width");
            PreCondition.assertGreaterThanOrEqualTo(height, 0, "height");

            if (clearSizeChangedSubscription && this.sizeChangedSubscription != null)
            {
                this.sizeChangedSubscription.dispose().await();
                this.sizeChangedSubscription = null;
            }

            if (this.width != width || this.height != height)
            {
                MutableSizeChange sizeChange = null;
                if (this.sizeChanged != null)
                {
                    sizeChange = SizeChange.create()
                        .setPreviousSize(this.width, this.height)
                        .setNewSize(width, height);
                }

                this.width = width;
                this.height = height;

                if (sizeChange != null)
                {
                    this.sizeChanged.run(sizeChange);
                }

                this.repaint();
            }

            return (T)this;
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
        public T setSize(Distance width, Distance height)
        {
            PreCondition.assertNotNull(width, "width");
            PreCondition.assertGreaterThanOrEqualTo(width, Distance.zero, "width");

            final int pixelWidth = this.ui.convertHorizontalDistanceToPixels(width);
            final int pixelHeight = this.ui.convertVerticalDistanceToPixels(height);
            return this.setSize(pixelWidth, pixelHeight);
        }

        @Override
        public T setSize(Size2Integer size)
        {
            PreCondition.assertNotNull(size, "size");

            return this.setSize(size.getWidthAsInt(), size.getHeightAsInt());
        }

        @Override
        public T setSize(Size2Distance size)
        {
            PreCondition.assertNotNull(size, "size");

            return this.setSize(size.getWidth(), size.getHeight());
        }

        @Override
        public Disposable onSizeChanged(Action1<SizeChange> action)
        {
            PreCondition.assertNotNull(action, "action");

            if (this.sizeChanged == null)
            {
                this.sizeChanged = RunnableEvent1.create();
            }
            return this.sizeChanged.subscribe(action);
        }
    }
}
