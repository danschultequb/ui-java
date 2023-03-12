package qub;

/**
 * Options for drawing an oval.
 */
public class DrawOvalOptions
{
    private static final String leftXPropertyName = "leftX";
    private static final String topYPropertyName = "topY";
    private static final String widthPropertyName = "width";
    private static final String heightPropertyName = "height";
    private static final String lineColorPropertyName = "lineColor";
    private static final String fillColorPropertyName = "fillColor";

    private int leftX;
    private int topY;
    private int width;
    private int height;
    private Color lineColor;
    private Color fillColor;

    private DrawOvalOptions()
    {
    }

    public static DrawOvalOptions create()
    {
        return new DrawOvalOptions();
    }

    public static DrawOvalOptions create(DrawOvalOptions toCopy)
    {
        PreCondition.assertNotNull(toCopy, "toCopy");

        return DrawOvalOptions.create()
            .set(toCopy);
    }

    public DrawOvalOptions set(DrawOvalOptions toCopy)
    {
        PreCondition.assertNotNull(toCopy, "toCopy");

        this.leftX = toCopy.getLeftX();
        this.topY = toCopy.getTopY();
        this.width = toCopy.getWidth();
        this.height = toCopy.getHeight();
        this.lineColor = toCopy.getLineColor();
        this.fillColor = toCopy.getFillColor();

        return this;
    }

    /**
     * Get whether this {@link DrawOvalOptions} has an x-coordinate value.
     */
    public boolean hasX()
    {
        return true;
    }

    public DrawOvalOptions setLeftX(int leftX)
    {
        this.leftX = leftX;

        return this;
    }

    public int getLeftX()
    {
        return this.leftX;
    }

    /**
     * Get whether this {@link DrawOvalOptions} has a y-coordinate value.
     */
    public boolean hasY()
    {
        return true;
    }

    public DrawOvalOptions setTopY(int topY)
    {
        this.topY = topY;

        return this;
    }

    public int getTopY()
    {
        return this.topY;
    }

    /**
     * Get whether this {@link DrawOvalOptions} has a width value.
     */
    public boolean hasWidth()
    {
        return true;
    }

    public DrawOvalOptions setWidth(int width)
    {
        return this.setSize(width, this.getHeight());
    }

    public int getWidth()
    {
        return this.width;
    }

    /**
     * Get whether this {@link DrawOvalOptions} has a height value.
     */
    public boolean hasHeight()
    {
        return true;
    }

    public DrawOvalOptions setHeight(int height)
    {
        return this.setSize(this.getWidth(), height);
    }

    public int getHeight()
    {
        return this.height;
    }

    public DrawOvalOptions setSize(int width, int height)
    {
        PreCondition.assertGreaterThanOrEqualTo(width, 0, "width");
        PreCondition.assertGreaterThanOrEqualTo(height, 0, "height");

        this.width = width;
        this.height = height;

        return this;
    }

    public DrawOvalOptions setSize(Size2<Integer> size)
    {
        PreCondition.assertNotNull(size, "size");

        return this.setSize(size.getWidth(), size.getHeight());
    }

    public Color getLineColor()
    {
        return this.lineColor;
    }

    public DrawOvalOptions setLineColor(Color lineColor)
    {
        PreCondition.assertNotNull(lineColor, "lineColor");

        this.lineColor = lineColor;

        return this;
    }

    public Color getFillColor()
    {
        return this.fillColor;
    }

    public DrawOvalOptions setFillColor(Color fillColor)
    {
        PreCondition.assertNotNull(fillColor, "fillColor");

        this.fillColor = fillColor;

        return this;
    }

    /**
     * Get the JSON representation of this {@link DrawOvalOptions} object.
     */
    public JSONObject toJson()
    {
        final JSONObject result = JSONObject.create()
            .setNumber(DrawOvalOptions.leftXPropertyName, this.getLeftX())
            .setNumber(DrawOvalOptions.topYPropertyName, this.getTopY())
            .setNumber(DrawOvalOptions.widthPropertyName, this.getWidth())
            .setNumber(DrawOvalOptions.heightPropertyName, this.getHeight());

        if (this.lineColor != null)
        {
            result.setObject(DrawOvalOptions.lineColorPropertyName, this.lineColor.toJson());
        }
        if (this.fillColor != null)
        {
            result.setObject(DrawOvalOptions.fillColorPropertyName, this.fillColor.toJson());
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
        return rhs instanceof DrawOvalOptions rhsOptions && this.equals(rhsOptions);
    }

    public boolean equals(DrawOvalOptions rhs)
    {
        return rhs != null &&
            this.leftX == rhs.leftX &&
            this.topY == rhs.topY &&
            this.width == rhs.width &&
            this.height == rhs.height &&
            Comparer.equal(this.lineColor, rhs.lineColor) &&
            Comparer.equal(this.fillColor, rhs.fillColor);
    }
}
