package qub;

/**
 * Options for drawing a line.
 */
public class DrawLineOptions
{
    private static final String startXPropertyName = "startX";
    private static final String startYPropertyName = "startY";
    private static final String endXPropertyName = "endX";
    private static final String endYPropertyName = "endY";
    private static final String lineColorPropertyName = "lineColor";

    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private Color lineColor;

    private DrawLineOptions()
    {
    }

    public static DrawLineOptions create()
    {
        return new DrawLineOptions();
    }

    public static DrawLineOptions create(DrawLineOptions toCopy)
    {
        PreCondition.assertNotNull(toCopy, "toCopy");

        return DrawLineOptions.create()
            .set(toCopy);
    }

    /**
     * Set the properties of this {@link DrawLineOptions} to match those of the provided
     * {@link DrawLineOptions}.
     * @param toCopy The {@link DrawLineOptions} to copy.
     * @return This object for method chaining.
     */
    public DrawLineOptions set(DrawLineOptions toCopy)
    {
        PreCondition.assertNotNull(toCopy, "toCopy");

        this.startX = toCopy.startX;
        this.startY = toCopy.startY;
        this.endX = toCopy.endX;
        this.endY = toCopy.endY;
        this.lineColor = toCopy.lineColor;

        return this;
    }

    public int getStartX()
    {
        return this.startX;
    }

    public DrawLineOptions setStartX(int startX)
    {
        this.startX = startX;

        return this;
    }

    public int getStartY()
    {
        return this.startY;
    }

    public DrawLineOptions setStartY(int startY)
    {
        this.startY = startY;

        return this;
    }

    public Point2Integer getStart()
    {
        final Point2Integer result = Point2Integer.create(this.startX, this.startY);

        PostCondition.assertNotNull(result, "result");

        return result;
    }

    public DrawLineOptions setStart(Point2Integer start)
    {
        PreCondition.assertNotNull(start, "start");

        return this.setStart(start.getXAsInt(), start.getYAsInt());
    }

    public DrawLineOptions setStart(int startX, int startY)
    {
        this.setStartX(startX);
        this.setStartY(startY);

        return this;
    }

    public int getEndX()
    {
        return this.endX;
    }

    public DrawLineOptions setEndX(int endX)
    {
        this.endX = endX;

        return this;
    }

    public int getEndY()
    {
        return this.endY;
    }

    public DrawLineOptions setEndY(int endY)
    {
        this.endY = endY;

        return this;
    }

    public Point2Integer getEnd()
    {
        final Point2Integer result = Point2Integer.create(this.endX, this.endY);

        PostCondition.assertNotNull(result, "result");

        return result;
    }

    public DrawLineOptions setEnd(Point2Integer end)
    {
        PreCondition.assertNotNull(end, "end");

        return this.setEnd(end.getXAsInt(), end.getYAsInt());
    }

    public DrawLineOptions setEnd(int endX, int endY)
    {
        this.setEndX(endX);
        this.setEndY(endY);

        return this;
    }

    public DrawLineOptions setX(int x)
    {
        this.setStartX(x);
        this.setEndX(x);

        return this;
    }

    public DrawLineOptions setY(int y)
    {
        this.setStartY(y);
        this.setEndY(y);

        return this;
    }

    public Color getLineColor()
    {
        return this.lineColor;
    }

    public DrawLineOptions setLineColor(Color lineColor)
    {
        PreCondition.assertNotNull(lineColor, "lineColor");

        this.lineColor = lineColor;

        return this;
    }

    /**
     * Get the JSON representation of this {@link DrawLineOptions} object.
     */
    public JSONObject toJson()
    {
        final JSONObject result = JSONObject.create()
            .setNumber(DrawLineOptions.startXPropertyName, this.getStartX())
            .setNumber(DrawLineOptions.startYPropertyName, this.getStartY())
            .setNumber(DrawLineOptions.endXPropertyName, this.getEndX())
            .setNumber(DrawLineOptions.endYPropertyName, this.getEndY());

        if (this.lineColor != null)
        {
            result.setObject(DrawLineOptions.lineColorPropertyName, this.lineColor.toJson());
        }

        PostCondition.assertNotNull(result, "result");

        return result;
    }

    @Override
    public String toString()
    {
        return this.toJson().toString();
    }

    @Override
    public boolean equals(Object rhs)
    {
        return rhs instanceof DrawLineOptions rhsOptions && this.equals(rhsOptions);
    }

    public boolean equals(DrawLineOptions rhs)
    {
        return rhs != null &&
            this.startX == rhs.startX &&
            this.startY == rhs.startY &&
            this.endX == rhs.endX &&
            this.endY == rhs.endY &&
            Comparer.equal(this.lineColor, rhs.lineColor);
    }
}
