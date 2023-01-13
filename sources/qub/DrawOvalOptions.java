package qub;

public class DrawOvalOptions
{
    private static final String leftXPropertyName = "leftX";
    private static final String topYPropertyName = "topY";
    private static final String widthPropertyName = "width";
    private static final String heightPropertyName = "height";

    private int leftX;
    private int topY;
    private int width;
    private int height;

    private DrawOvalOptions()
    {
    }

    public static DrawOvalOptions create()
    {
        return new DrawOvalOptions();
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

    public DrawOvalOptions setTopY(int topY)
    {
        this.topY = topY;

        return this;
    }

    public int getTopY()
    {
        return this.topY;
    }

    public DrawOvalOptions setWidth(int width)
    {
        PreCondition.assertGreaterThanOrEqualTo(width, 0, "width");

        this.width = width;

        return this;
    }

    public int getWidth()
    {
        return this.width;
    }

    public DrawOvalOptions setHeight(int height)
    {
        PreCondition.assertGreaterThanOrEqualTo(height, 0, "height");

        this.height = height;

        return this;
    }

    public int getHeight()
    {
        return this.height;
    }

    public JSONObject toJson()
    {
        return JSONObject.create()
            .setNumber(DrawOvalOptions.leftXPropertyName, this.getLeftX())
            .setNumber(DrawOvalOptions.topYPropertyName, this.getTopY())
            .setNumber(DrawOvalOptions.widthPropertyName, this.getWidth())
            .setNumber(DrawOvalOptions.heightPropertyName, this.getHeight());
    }

    @Override
    public String toString()
    {
        return this.toJson().toString();
    }

    @Override
    public boolean equals(Object rhs)
    {
        return rhs instanceof DrawOvalOptions && this.equals((DrawOvalOptions)rhs);
    }

    public boolean equals(DrawOvalOptions rhs)
    {
        return rhs != null &&
            this.leftX == rhs.leftX &&
            this.topY == rhs.topY &&
            this.width == rhs.width &&
            this.height == rhs.height;
    }
}
