package qub;

/**
 * An object that can be used to draw/paint/render visuals.
 */
public interface UIPainter
{
    /**
     * Get the {@link Color} that shapes will be filled with.
     */
    public Color getFillColor();

    /**
     * Set the {@link Color} that shapes will be filled with. To not fill shapes with a
     * {@link Color}, set this to a transparent color.
     * @param fillColor The {@link Color} that shapes will be filled with.
     * @return This object for method chaining.
     */
    public UIPainter setFillColor(Color fillColor);

    /**
     * Get the {@link Color} that lines will be drawn with and shapes will be outlined with.
     */
    public Color getLineColor();

    /**
     * Set the {@link Color} that lines will be drawn with and shapes will be outlined with. To draw
     * shapes without outlines, set this to a transparent {@link Color}.
     * @param lineColor The {@link Color} to draw lines with and outline shapes with.
     * @return This object for method chaining.
     */
    public UIPainter setLineColor(Color lineColor);

    /**
     * Draw a line from the provided start point to the provided end point.
     * @param startX The x-coordinate of the start point.
     * @param startY The y-coordinate of the start point.
     * @param endX The x-coordinate of the end point.
     * @param endY The y-coordinate of the end point.
     */
    public UIPainter drawLine(int startX, int startY, int endX, int endY);

    /**
     * Draw a line from the provided start point to the provided end point.
     * @param start The start point.
     * @param end The end point.
     */
    public default UIPainter drawLine(Point2Integer start, Point2Integer end)
    {
        PreCondition.assertNotNull(start, "start");
        PreCondition.assertNotNull(end, "end");

        return this.drawLine(start.getXAsInt(), start.getYAsInt(), end.getXAsInt(), end.getYAsInt());
    }

    /**
     * Draw a line using the provided {@link DrawLineOptions}.
     * @param options The {@link DrawLineOptions} to use when drawing the line.
     */
    public default UIPainter drawLine(DrawLineOptions options)
    {
        PreCondition.assertNotNull(options, "options");

        return this.drawLine(options.getStartX(), options.getStartY(), options.getEndX(), options.getEndY());
    }

    /**
     * Draw an oval at the provided coordinate with the provided width and height.
     * @param leftX The left x-coordinate of the bounding box that contains the oval.
     * @param topY  The top y-coordinate of the bounding box that contains the oval.
     * @param width The width of the bounding box that contains the oval.
     * @param height The height of the bounding box that contains the oval.
     */
    public UIPainter drawOval(int leftX, int topY, int width, int height);

    /**
     * Draw an oval using the provided {@link DrawOvalOptions}.
     * @param options The {@link DrawOvalOptions} to use when drawing the oval.
     */
    public default UIPainter drawOval(DrawOvalOptions options)
    {
        PreCondition.assertNotNull(options, "options");

        return this.drawOval(options.getLeftX(), options.getTopY(), options.getWidth(), options.getHeight());
    }

    /**
     * Draw a rectangle at the provided coordinate with the provided width and height.
     * @param leftX The left x-coordinate of the rectangle.
     * @param topY The top y-coordinate of the rectangle.
     * @param width The width of the rectangle.
     * @param height The height of the rectangle.
     */
    public UIPainter drawRectangle(int leftX, int topY, int width, int height);

    /**
     * Draw a rectangle using the provided options.
     * @param options The options to use when drawing the rectangle.
     */
    public default UIPainter drawRectangle(DrawRectangleOptions options)
    {
        PreCondition.assertNotNull(options, "options");

        final Color backupLineColor = this.getLineColor();
        final Color backupFillColor = this.getFillColor();
        final Color optionsLineColor = options.getLineColor();
        final Color optionsFillColor = options.getFillColor();
        try
        {
            if (optionsLineColor != null)
            {
                this.setLineColor(optionsLineColor);
            }
            if (optionsFillColor != null)
            {
                this.setFillColor(optionsFillColor);
            }

            this.drawRectangle(options.getLeftX(), options.getTopY(), options.getWidth(), options.getHeight());
        }
        finally
        {
            if (optionsLineColor != null)
            {
                this.setLineColor(backupLineColor);
            }
            if (optionsFillColor != null)
            {
                this.setFillColor(backupFillColor);
            }
        }

        return this;
    }

    /**
     * Draw the provided text at the provided baseline coordinate.
     * @param leftX The left x-coordinate of the text baseline.
     * @param baselineY The y-coordinate of the text baseline.
     * @param text The text to draw.
     */
    public UIPainter drawText(int leftX, int baselineY, String text);

    /**
     * Draw text using the provided {@link DrawTextOptions}.
     * @param options The {@link DrawTextOptions} to use when drawing the text.
     * @return This object for method chaining.
     */
    public default UIPainter drawText(DrawTextOptions options)
    {
        PreCondition.assertNotNull(options, "options");
        PreCondition.assertNotNull(options.getText(), "options.getText()");
        PreCondition.assertTrue(options.getLeftX() != null || options.getCenterX() != null || options.getRightX() != null, "options.getLeftX() != null || options.getCenterX() != null || options.getRightX() != null");
        PreCondition.assertTrue(options.getTopY() != null || options.getCenterY() != null || options.getBaselineY() != null || options.getBottomY() != null, "options.getTopY() != null || options.getCenterY() != null || options.getBaselineY() != null || options.getBottomY() != null");

        final String text = options.getText();

        Integer leftX = options.getLeftX();
        Integer baselineY = options.getBaselineY();
        if (leftX == null || baselineY == null)
        {
            final TextMeasurements textMeasurements = this.getTextMeasurements(text);

            if (leftX == null)
            {
                final int textWidth = textMeasurements.getWidth();

                final Integer centerX = options.getCenterX();
                if (centerX != null)
                {
                    leftX = centerX - (textWidth / 2);
                }
                else
                {
                    leftX = options.getRightX() - textWidth;
                }
            }

            if (baselineY == null)
            {
                final Integer topY = options.getTopY();
                if (topY != null)
                {
                    baselineY = topY + textMeasurements.getAscent();
                }
                else
                {
                    final Integer bottomY = options.getBottomY();
                    if (bottomY != null)
                    {
                        baselineY = bottomY - textMeasurements.getDescent();
                    }
                    else
                    {
                        final int centerY = options.getCenterY();
                        final int ascent = textMeasurements.getAscent();
                        final int height = ascent + textMeasurements.getDescent();
                        baselineY = centerY - (height / 2) + ascent;
                    }
                }
            }
        }

        return this.drawText(leftX, baselineY, text);
    }

    /**
     * Get {@link TextMeasurements} for the provided text.
     * @param text The text to measure.
     */
    public TextMeasurements getTextMeasurements(String text);

    /**
     * A version of a {@link UIPainter} that returns its own type from chainable methods.
     * @param <T> The actual type of the {@link UIPainter}.
     */
    public static interface Typed<T extends UIPainter> extends UIPainter
    {
        @Override
        public T setFillColor(Color fillColor);

        @Override
        public T setLineColor(Color lineColor);

        @Override
        public T drawLine(int startX, int startY, int endX, int endY);

        @Override
        @SuppressWarnings("unchecked")
        public default T drawLine(Point2Integer start, Point2Integer end)
        {
            return (T)UIPainter.super.drawLine(start, end);
        }

        @Override
        @SuppressWarnings("unchecked")
        public default T drawLine(DrawLineOptions options)
        {
            return (T)UIPainter.super.drawLine(options);
        }

        @Override
        public T drawOval(int leftX, int topY, int width, int height);

        @Override
        @SuppressWarnings("unchecked")
        public default T drawOval(DrawOvalOptions options)
        {
            return (T)UIPainter.super.drawOval(options);
        }

        @Override
        public T drawRectangle(int leftX, int topY, int width, int height);

        @Override
        @SuppressWarnings("unchecked")
        public default T drawRectangle(DrawRectangleOptions options)
        {
            return (T)UIPainter.super.drawRectangle(options);
        }

        @Override
        public T drawText(int leftX, int baselineY, String text);

        @Override
        @SuppressWarnings("unchecked")
        public default T drawText(DrawTextOptions options)
        {
            return (T)UIPainter.super.drawText(options);
        }
    }
}
