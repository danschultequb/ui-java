package qub;

public interface EveryoneUIElement extends UIElement
{
    @Override
    public EveryoneUIElement setBackgroundColor(Color backgroundColor);

    /**
     * Set the pixel width of this {@link EveryoneUIElement}.
     * @param width The pixel width of this {@link EveryoneUIElement}.
     * @return This object for method chaining.
     */
    public EveryoneUIElement setWidth(int width);

    /**
     * Set the {@link Distance} width of this {@link EveryoneUIElement}.
     * @param width The {@link Distance} width of this {@link EveryoneUIElement}.
     * @return This object for method chaining.
     */
    public EveryoneUIElement setWidth(Distance width);

    /**
     * Set the pixel height of this {@link EveryoneUIElement}.
     * @param height The pixel height of this {@link EveryoneUIElement}.
     * @return This object for method chaining.
     */
    public EveryoneUIElement setHeight(int height);

    /**
     * Set the {@link Distance} height of this {@link EveryoneUIElement}.
     * @param height The {@link Distance} height of this {@link EveryoneUIElement}.
     * @return This object for method chaining.
     */
    public EveryoneUIElement setHeight(Distance height);

    /**
     * Set the pixel width and height of this {@link EveryoneSwingUIWindow}.
     * @param width The pixel width of this {@link EveryoneSwingUIWindow}.
     * @param height The pixel height of this {@link EveryoneSwingUIWindow}.
     * @return This object for method chaining.
     */
    public EveryoneUIElement setSize(int width, int height);

    /**
     * Set the {@link Distance} width and height of this {@link EveryoneSwingUIWindow}.
     * @param width The new {@link Distance} width of this {@link EveryoneSwingUIWindow}.
     * @param height The new {@link Distance} height of this {@link EveryoneSwingUIWindow}.
     * @return This object for method chaining.
     */
    public EveryoneUIElement setSize(Distance width, Distance height);

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
    }

    /**
     * An abstract base class implementation of {@link EveryoneUIElement} that contains common
     * properties and functions for all {@link EveryoneUIElement} types.
     * @param <T> The actual type of the {@link EveryoneUIElement}.
     */
    public static class Base<T extends EveryoneUIElement> implements EveryoneUIElement.Typed<T>
    {
        private final EveryoneSwingUI ui;
        private int width;
        private int height;
        private Color backgroundColor;

        protected Base(EveryoneSwingUI ui)
        {
            PreCondition.assertNotNull(ui, "ui");

            this.ui = ui;
            this.backgroundColor = Color.transparent;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T setBackgroundColor(Color backgroundColor)
        {
            PreCondition.assertNotNull(backgroundColor, "backgroundColor");

            this.backgroundColor = backgroundColor;

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
        @SuppressWarnings("unchecked")
        public T setWidth(int width)
        {
            PreCondition.assertGreaterThanOrEqualTo(width, 0, "width");

            this.width = width;

            return (T)this;
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
        public Size2Integer getSize()
        {
            return Size2Integer.create(this.width, this.height);
        }

        @Override
        @SuppressWarnings("unchecked")
        public T setHeight(int height)
        {
            PreCondition.assertGreaterThanOrEqualTo(height, 0, "height");

            this.height = height;

            return (T)this;
        }

        @Override
        public T setHeight(Distance height)
        {
            PreCondition.assertNotNull(height, "height");
            PreCondition.assertGreaterThanOrEqualTo(height, Distance.zero, "height");

            return this.setHeight(this.ui.convertVerticalDistanceToPixels(height));
        }

        @Override
        @SuppressWarnings("unchecked")
        public T setSize(int width, int height)
        {
            PreCondition.assertGreaterThanOrEqualTo(width, 0, "width");
            PreCondition.assertGreaterThanOrEqualTo(height, 0, "height");

            this.width = width;
            this.height = height;

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
    }
}
