package qub;

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

    public DrawRectangleOptions setLeftX(int leftX)
    {
        this.leftX = leftX;

        return this;
    }

    public int getLeftX()
    {
        return this.leftX;
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

    public DrawRectangleOptions setHeight(int height)
    {
        PreCondition.assertGreaterThanOrEqualTo(height, 0, "height");

        this.height = height;

        return this;
    }

    public int getHeight()
    {
        return this.height;
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

    public JSONObject toJson()
    {
        return JSONObject.create()
            .setNumber(DrawRectangleOptions.leftXPropertyName, this.getLeftX())
            .setNumber(DrawRectangleOptions.topYPropertyName, this.getTopY())
            .setNumber(DrawRectangleOptions.widthPropertyName, this.getWidth())
            .setNumber(DrawRectangleOptions.heightPropertyName, this.getHeight());
    }

    @Override
    public String toString()
    {
        return this.toJson().toString();
    }

    @Override
    public boolean equals(Object rhs)
    {
        return rhs instanceof DrawRectangleOptions && this.equals((DrawRectangleOptions)rhs);
    }

    public boolean equals(DrawRectangleOptions rhs)
    {
        return rhs != null &&
            this.leftX == rhs.leftX &&
            this.topY == rhs.topY &&
            this.width == rhs.width &&
            this.height == rhs.height &&
            Color.equal(this.fillColor, rhs.fillColor) &&
            Color.equal(this.lineColor, rhs.lineColor);
    }
}
