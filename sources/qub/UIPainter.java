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
     * Save the current fill {@link Color} of this {@link UIPainter}. The saved fill {@link Color}
     * will be restored when the returned {@link Disposable} is disposed.
     */
    public default Disposable saveFillColor()
    {
        final Color fillColor = this.getFillColor();
        return Disposable.create(() ->
        {
            this.setFillColor(fillColor);
        });
    }

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
     * Save the current line {@link Color} of this {@link UIPainter}. The saved line {@link Color}
     * will be restored when the returned {@link Disposable} is disposed.
     */
    public default Disposable saveLineColor()
    {
        final Color lineColor = this.getLineColor();
        return Disposable.create(() ->
        {
            this.setLineColor(lineColor);
        });
    }

    /**
     * Save the current line and fill {@link Color}s of this {@link UIPainter}. The saved line and
     * fill {@link Color}s will be restored when the returned {@link Disposable} is disposed.
     */
    public default Disposable saveColors()
    {
        return Disposable.create(
            this.saveLineColor(),
            this.saveFillColor());
    }

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
    public UIPainter drawLine(DrawLineOptions options);

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
    public UIPainter drawOval(DrawOvalOptions options);

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
    public UIPainter drawRectangle(DrawRectangleOptions options);

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
    public UIPainter drawText(DrawTextOptions options);

    /**
     * Get {@link TextMeasurements} for the provided text.
     * @param text The text to measure.
     */
    public TextMeasurements getTextMeasurements(String text);

    /**
     * Translate this {@link UIPainter} so that all draw operations get the provided x-value added
     * to their x-coordinates.
     * @param x The x-value to add to every draw operation's x-coordinates.
     * @return This object for method chaining.
     */
    public default UIPainter translateX(int x)
    {
        return this.translate(x, 0);
    }

    /**
     * Translate this {@link UIPainter} so that all draw operations get the provided y-value added
     * to their y-coordinates.
     * @param y The y-value to add to every draw operation's y-coordinates.
     * @return This object for method chaining.
     */
    public default UIPainter translateY(int y)
    {
        return this.translate(0, y);
    }

    /**
     * Translate this {@link UIPainter} so that all draw operations get the provided x- and y-values
     * added to their x- and y-coordinates.
     * @param x The x-value to add to every draw operation's x-coordinates.
     * @param y The y-value to add to every draw operation's y-coordinates.
     * @return This object for method chaining.
     */
    public UIPainter translate(int x, int y);

    /**
     * Get the {@link Transform2} that this {@link UIPainter} is using to transform coordinates and
     * draw operations.
     */
    public Transform2 getTransform();

    /**
     * Set the {@link Transform2} that this {@link UIPainter} is using to transform coordinates and
     * draw operations.
     * @param transform The {@link Transform2} that this {@link UIPainter} is using to transform
     *                  coordinates and draw operations.
     * @return This object for method chaining.
     */
    public UIPainter setTransform(Transform2 transform);

    /**
     * Save the current {@link Transform2} of this {@link UIPainter}. The saved {@link Transform2}
     * will be restored when the returned {@link Disposable} is disposed.
     */
    public default Disposable saveTransform()
    {
        final Transform2 transformBackup = Transform2.create(this.getTransform());
        return Disposable.create(() ->
        {
            this.setTransform(transformBackup);
        });
    }

    /**
     * Get the clip of this {@link UIPainter}. Any draw operations that are outside the clip (based
     * on the current transform), will not be drawn.
     */
    public Size2Integer getClip();

    /**
     * Set the clip of this {@link UIPainter}. Any draw operations that are outside the clip will
     * not be drawn.
     * @param width The width of the clip size.
     * @param height The height of the clip size.
     * @return This object for method chaining.
     */
    public default UIPainter setClip(int width, int height)
    {
        return this.setClip(options -> options.setSize(width, height));
    }

    /**
     * Set the clip of this {@link UIPainter}. Any draw operations that are outside the clip will
     * not be drawn.
     * @param size The clip size.
     * @return This object for method chaining.
     */
    public default UIPainter setClip(Size2Integer size)
    {
        PreCondition.assertNotNull(size, "size");

        return this.setClip(options -> options.setSize(size));
    }

    /**
     * Set the clip of this {@link UIPainter}. Any draw operations that are outside the clip will
     * not be drawn.
     * @param optionsAction The {@link Action1} that populates the options to use to set the clip of
     *                      this {@link UIPainter}.
     * @return This object for method chaining.
     */
    public default UIPainter setClip(Action1<UIPainterSetClipSizeOptions> optionsAction)
    {
        PreCondition.assertNotNull(optionsAction, "optionsAction");

        final UIPainterSetClipSizeOptions options = UIPainterSetClipSizeOptions.create();
        optionsAction.run(options);
        return this.setClip(options);
    }

    /**
     * Set the clip of this {@link UIPainter}. Any draw operations that are outside the clip will
     * not be drawn.
     * @param options The options to use to set the clip of this {@link UIPainter}.
     * @return This object for method chaining.
     */
    public UIPainter setClip(UIPainterSetClipSizeOptions options);

    /**
     * Save the current clip of this {@link UIPainter}'s. The saved clip will be restored when the
     * returned {@link Disposable} is disposed.
     */
    public default Disposable saveClip()
    {
        final Size2Integer clipSize = this.getClip();
        return Disposable.create(() ->
        {
            this.setClip(options -> options
                .setSize(clipSize)
                .setResetClip(true));
        });
    }

    /**
     * Save the current state of this {@link UIPainter} ({@link Transform2}, clip, etc.). The saved
     * state will be restored when the returned {@link Disposable} is disposed.
     */
    public default Disposable saveState()
    {
        return Disposable.create(
            this.saveTransform(),
            this.saveClip(),
            this.saveColors());
    }

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
        public T drawLine(DrawLineOptions options);

        @Override
        public T drawOval(int leftX, int topY, int width, int height);

        @Override
        public T drawOval(DrawOvalOptions options);

        @Override
        public T drawRectangle(int leftX, int topY, int width, int height);

        @Override
        public T drawRectangle(DrawRectangleOptions options);

        @Override
        public T drawText(int leftX, int baselineY, String text);

        @Override
        public T drawText(DrawTextOptions options);

        @Override
        @SuppressWarnings("unchecked")
        public default T translateX(int x)
        {
            return (T)UIPainter.super.translateX(x);
        }

        @Override
        @SuppressWarnings("unchecked")
        public default T translateY(int y)
        {
            return (T)UIPainter.super.translateY(y);
        }

        @Override
        public T translate(int x, int y);

        @Override
        public T setTransform(Transform2 transform);

        @Override
        @SuppressWarnings("unchecked")
        public default T setClip(int width, int height)
        {
            return (T)UIPainter.super.setClip(width, height);
        }

        @Override
        @SuppressWarnings("unchecked")
        public default T setClip(Size2Integer size)
        {
            return (T)UIPainter.super.setClip(size);
        }

        @Override
        @SuppressWarnings("unchecked")
        public default T setClip(Action1<UIPainterSetClipSizeOptions> optionsAction)
        {
            return (T)UIPainter.super.setClip(optionsAction);
        }

        @Override
        public T setClip(UIPainterSetClipSizeOptions options);
    }
}
