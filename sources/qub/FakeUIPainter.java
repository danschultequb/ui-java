package qub;

/**
 * A fake {@link UIPainter} implementation.
 */
public class FakeUIPainter implements UIPainter.Typed<FakeUIPainter>
{
    private final List<FakeDrawOperation> drawOperations;
    private boolean ignoreTransparentDraws;
    private Color fillColor;
    private Color lineColor;
    private MutableTransform2 transform;

    private FakeUIPainter()
    {
        this.drawOperations = List.create();

        this.setFillColor(Color.transparent);
        this.setLineColor(Color.black);
        this.setIgnoreTransparentDraws(true);

        this.transform = Transform2.create();
    }

    public static FakeUIPainter create()
    {
        return new FakeUIPainter();
    }

    /**
     * Set whether this {@link FakeUIPainter} will record draw operations that are performed with a
     * fully transparent {@link Color}.
     * @param ignoreTransparentDraws whether this {@link FakeUIPainter} will record draw operations
     *                               that are performed with a fully transparent {@link Color}.
     * @return This object for method chaining.
     */
    public FakeUIPainter setIgnoreTransparentDraws(boolean ignoreTransparentDraws)
    {
        this.ignoreTransparentDraws = ignoreTransparentDraws;

        return this;
    }

    /**
     * Get the {@link FakeDrawOperation}s that have been performed on this {@link FakeUIPainter}.
     */
    public Iterable<FakeDrawOperation> getDrawOperations()
    {
        return this.drawOperations;
    }

    @Override
    public Color getFillColor()
    {
        return this.fillColor;
    }

    @Override
    public FakeUIPainter setFillColor(Color fillColor)
    {
        PreCondition.assertNotNull(fillColor, "fillColor");

        this.fillColor = fillColor;

        return this;
    }

    @Override
    public Color getLineColor()
    {
        return this.lineColor;
    }

    @Override
    public FakeUIPainter setLineColor(Color lineColor)
    {
        PreCondition.assertNotNull(lineColor, "lineColor");

        this.lineColor = lineColor;

        return this;
    }

    @Override
    public FakeUIPainter drawLine(int startX, int startY, int endX, int endY)
    {
        return this.drawLine(DrawLineOptions.create()
            .setStartX(startX)
            .setStartY(startY)
            .setEndX(endX)
            .setEndY(endY));
    }

    @Override
    public FakeUIPainter drawLine(DrawLineOptions options)
    {
        PreCondition.assertNotNull(options, "options");

        final DrawLineOptions drawOptions = DrawLineOptions.create(options);
        if (drawOptions.getLineColor() == null)
        {
            drawOptions.setLineColor(this.lineColor);
        }

        if (!drawOptions.getLineColor().isTransparent() || !this.ignoreTransparentDraws)
        {
            this.drawOperations.add(FakeDrawLineOperation.create()
                .setOptions(drawOptions)
                .setTransform(Transform2.create(this.transform)));
        }

        return this;
    }

    @Override
    public FakeUIPainter drawOval(int leftX, int topY, int width, int height)
    {
        return this.drawOval(DrawOvalOptions.create()
            .setLeftX(leftX)
            .setTopY(topY)
            .setWidth(width)
            .setHeight(height));
    }

    @Override
    public FakeUIPainter drawOval(DrawOvalOptions options)
    {
        PreCondition.assertNotNull(options, "options");
        PreCondition.assertTrue(options.hasX(), "options.hasX()");
        PreCondition.assertTrue(options.hasY(), "options.hasY()");
        PreCondition.assertTrue(options.hasWidth(), "options.hasWidth()");
        PreCondition.assertTrue(options.hasHeight(), "options.hasHeight()");

        final DrawOvalOptions drawOptions = DrawOvalOptions.create(options);
        if (drawOptions.getLineColor() == null)
        {
            drawOptions.setLineColor(this.lineColor);
        }
        if (drawOptions.getFillColor() == null)
        {
            drawOptions.setFillColor(this.fillColor);
        }

        if (!drawOptions.getLineColor().isTransparent() ||
            !drawOptions.getFillColor().isTransparent() ||
            !this.ignoreTransparentDraws)
        {
            this.drawOperations.add(FakeDrawOvalOperation.create()
                .setOptions(drawOptions)
                .setTransform(Transform2.create(this.transform)));
        }

        return this;
    }

    @Override
    public FakeUIPainter drawRectangle(int leftX, int topY, int width, int height)
    {
        return this.drawRectangle(DrawRectangleOptions.create()
            .setLeftX(leftX)
            .setTopY(topY)
            .setWidth(width)
            .setHeight(height));
    }

    @Override
    public FakeUIPainter drawRectangle(DrawRectangleOptions options)
    {
        PreCondition.assertNotNull(options, "options");
        PreCondition.assertTrue(options.hasX(), "options.hasX()");
        PreCondition.assertTrue(options.hasY(), "options.hasY()");
        PreCondition.assertTrue(options.hasWidth(), "options.hasWidth()");
        PreCondition.assertTrue(options.hasHeight(), "options.hasHeight()");

        final DrawRectangleOptions drawOptions = DrawRectangleOptions.create(options);
        if (drawOptions.getLineColor() == null)
        {
            drawOptions.setLineColor(this.lineColor);
        }
        if (drawOptions.getFillColor() == null)
        {
            drawOptions.setFillColor(this.fillColor);
        }

        if (!drawOptions.getLineColor().isTransparent() ||
            !drawOptions.getFillColor().isTransparent() ||
            !this.ignoreTransparentDraws)
        {
            this.drawOperations.add(FakeDrawRectangleOperation.create()
                .setOptions(drawOptions)
                .setTransform(Transform2.create(this.transform)));
        }

        return this;
    }

    @Override
    public FakeUIPainter drawText(int leftX, int baselineY, String text)
    {
        return this.drawText(DrawTextOptions.create()
            .setLeftX(leftX)
            .setBaselineY(baselineY)
            .setText(text));
    }

    @Override
    public FakeUIPainter drawText(DrawTextOptions options)
    {
        PreCondition.assertNotNull(options, "options");
        PreCondition.assertTrue(options.hasX(), "options.hasX()");
        PreCondition.assertTrue(options.hasY(), "options.hasY()");
        PreCondition.assertNotNull(options.getText(), "options.getText()");

        final DrawTextOptions drawOptions = DrawTextOptions.create(options);
        if (drawOptions.getLineColor() == null)
        {
            drawOptions.setLineColor(this.lineColor);
        }

        if (!drawOptions.getLineColor().isTransparent() ||
            !this.ignoreTransparentDraws)
        {
            this.drawOperations.add(FakeDrawTextOperation.create()
                .setOptions(drawOptions)
                .setTransform(Transform2.create(this.transform)));
        }

        return this;
    }

    @Override
    public FakeUIPainter translate(int x, int y)
    {
        this.transform.translate(x, y);

        return this;
    }

    @Override
    public FakeUIPainter setTransform(Transform2 transform)
    {
        PreCondition.assertNotNull(transform, "transform");

        this.transform.set(transform);

        return this;
    }

    @Override
    public Transform2 getTransform()
    {
        return this.transform;
    }

    @Override
    public TextMeasurements getTextMeasurements(String text)
    {
        return null;
    }
}
