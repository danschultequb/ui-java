package qub;

/**
 * Options that can be used to draw a rectangle.
 */
public class DrawRectangleOptions
{
    private static final String leftXPropertyName = "leftX";
    private static final String topYPropertyName = "topY";
    private static final String widthPropertyName = "width";
    private static final String heightPropertyName = "height";
    private static final String fillColorPropertyName = "fillColor";
    private static final String lineColorPropertyName = "lineColor";

    private int leftX;
    private int topY;
    private int width;
    private int height;
    private Color fillColor;
    private Color lineColor;

    private DrawRectangleOptions()
    {
    }

    public static DrawRectangleOptions create()
    {
        return new DrawRectangleOptions();
    }

    public static DrawRectangleOptions create(DrawRectangleOptions toCopy)
    {
        PreCondition.assertNotNull(toCopy, "toCopy");

        return DrawRectangleOptions.create()
            .set(toCopy);
    }

    public DrawRectangleOptions set(DrawRectangleOptions toCopy)
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
     * Get whether this {@link DrawRectangleOptions} has an x-coordinate value.
     */
    public boolean hasX()
    {
        return true;
    }

    public DrawRectangleOptions setLeftX(int leftX)
    {
        this.leftX = leftX;

        return this;
    }

    public int getLeftX()
    {
        return this.leftX;
    }

    /**
     * Get whether this {@link DrawRectangleOptions} has a y-coordinate value.
     */
    public boolean hasY()
    {
        return true;
    }

    public DrawRectangleOptions setTopY(int topY)
    {
        this.topY = topY;

        return this;
    }

    public int getTopY()
    {
        return this.topY;
    }

    /**
     * Get whether this {@link DrawRectangleOptions} has a width value.
     */
    public boolean hasWidth()
    {
        return true;
    }

    public DrawRectangleOptions setWidth(int width)
    {
        PreCondition.assertGreaterThanOrEqualTo(width, 0, "width");

        this.width = width;

        return this;
    }

    public int getWidth()
    {
        return this.width;
    }

    /**
     * Get whether this {@link DrawRectangleOptions} has a height value.
     */
    public boolean hasHeight()
    {
        return true;
    }

    public DrawRectangleOptions setHeight(int height)
    {
        PreCondition.assertGreaterThanOrEqualTo(height, 0, "height");

        this.height = height;

        return this;
    }

    /**
     * Get the height of the rectangle to draw.
     */
    public int getHeight()
    {
        return this.height;
    }

    /**
     * Set the size of the rectangle to draw.
     * @param width The width of the rectangle to draw.
     * @param height The height of the rectangle to draw.
     * @return This object for method chaining.
     */
    public DrawRectangleOptions setSize(int width, int height)
    {
        this.setWidth(width);
        this.setHeight(height);

        return this;
    }

    public DrawRectangleOptions setFillColor(Color fillColor)
    {
        PreCondition.assertNotNull(fillColor, "fillColor");

        this.fillColor = fillColor;

        return this;
    }

    public Color getFillColor()
    {
        return this.fillColor;
    }

    public DrawRectangleOptions setLineColor(Color lineColor)
    {
        PreCondition.assertNotNull(lineColor, "lineColor");

        this.lineColor = lineColor;

        return this;
    }

    public Color getLineColor()
    {
        return this.lineColor;
    }

    /**
     * Get the JSON representation of this {@link DrawRectangleOptions} object.
     */
    public JSONObject toJson()
    {
        final JSONObject result = JSONObject.create();
        result.setNumber(DrawRectangleOptions.leftXPropertyName, this.getLeftX());
        result.setNumber(DrawRectangleOptions.topYPropertyName, this.getTopY());
        result.setNumber(DrawRectangleOptions.widthPropertyName, this.getWidth());
        result.setNumber(DrawRectangleOptions.heightPropertyName, this.getHeight());

        if (this.lineColor != null)
        {
            result.setObject(DrawRectangleOptions.lineColorPropertyName, this.lineColor.toJson());
        }
        if (this.fillColor != null)
        {
            result.setObject(DrawRectangleOptions.fillColorPropertyName, this.fillColor.toJson());
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
        return rhs instanceof DrawRectangleOptions rhsOptions && this.equals(rhsOptions);
    }

    public boolean equals(DrawRectangleOptions rhs)
    {
        return rhs != null &&
            this.leftX == rhs.leftX &&
            this.topY == rhs.topY &&
            this.width == rhs.width &&
            this.height == rhs.height &&
            Comparer.equal(this.fillColor, rhs.fillColor) &&
            Comparer.equal(this.lineColor, rhs.lineColor);
    }
}
