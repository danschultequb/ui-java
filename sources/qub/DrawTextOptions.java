package qub;

/**
 * Options that can be set when drawing text using a {@link UIPainter}.
 */
public class DrawTextOptions
{
    private static final String leftXPropertyName = "leftX";
    private static final String centerXPropertyName = "centerX";
    private static final String rightXPropertyName = "rightX";
    private static final String topYPropertyName = "topY";
    private static final String centerYPropertyName = "centerY";
    private static final String baselineYPropertyName = "baselineY";
    private static final String bottomYPropertyName = "bottomY";
    private static final String textPropertyName = "text";
    private static final String lineColorPropertyName = "lineColor";

    private Integer leftX;
    private Integer centerX;
    private Integer rightX;

    private Integer topY;
    private Integer centerY;
    private Integer baselineY;
    private Integer bottomY;

    private String text;

    private Color lineColor;

    private DrawTextOptions()
    {
    }

    /**
     * Create a new {@link DrawTextOptions} object.
     */
    public static DrawTextOptions create()
    {
        return new DrawTextOptions();
    }

    public static DrawTextOptions create(DrawTextOptions toCopy)
    {
        PreCondition.assertNotNull(toCopy, "toCopy");

        return DrawTextOptions.create()
            .set(toCopy);
    }

    public DrawTextOptions set(DrawTextOptions toCopy)
    {
        PreCondition.assertNotNull(toCopy, "toCopy");

        this.leftX = toCopy.getLeftX();
        this.centerX = toCopy.getCenterX();
        this.rightX = toCopy.getRightX();
        this.topY = toCopy.getTopY();
        this.centerY = toCopy.getCenterY();
        this.baselineY = toCopy.getBaselineY();
        this.bottomY = toCopy.getBottomY();
        this.text = toCopy.getText();
        this.lineColor = toCopy.getLineColor();

        return this;
    }

    /**
     * Get whether this {@link DrawTextOptions} has an x-coordinate value.
     */
    public boolean hasX()
    {
        return this.leftX != null || this.centerX != null || this.rightX != null;
    }

    /**
     * Set the left edge of where the text will be drawn.
     * @param leftX The left edge of where the text will be drawn at.
     * @return This object for method chaining.
     */
    public DrawTextOptions setLeftX(int leftX)
    {
        PreCondition.assertFalse(this.hasX(), "this.hasX()");

        this.leftX = leftX;

        return this;
    }

    /**
     * Get the left edge of where the text will be drawn, or null if this property has not been set.
     */
    public Integer getLeftX()
    {
        return this.leftX;
    }

    public DrawTextOptions setCenterX(int centerX)
    {
        PreCondition.assertFalse(this.hasX(), "this.hasX()");

        this.centerX = centerX;

        return this;
    }

    public Integer getCenterX()
    {
        return this.centerX;
    }

    public DrawTextOptions setRightX(int rightX)
    {
        PreCondition.assertFalse(this.hasX(), "this.hasX()");

        this.rightX = rightX;

        return this;
    }

    public Integer getRightX()
    {
        return this.rightX;
    }

    /**
     * Get whether this {@link DrawTextOptions} has a y-coordinate value.
     */
    public boolean hasY()
    {
        return this.topY != null || this.centerY != null || this.baselineY != null || this.bottomY != null;
    }

    public DrawTextOptions setTopY(int topY)
    {
        PreCondition.assertFalse(this.hasY(), "this.hasY()");

        this.topY = topY;

        return this;
    }

    public Integer getTopY()
    {
        return this.topY;
    }

    public DrawTextOptions setCenterY(int centerY)
    {
        PreCondition.assertFalse(this.hasY(), "this.hasY()");

        this.centerY = centerY;

        return this;
    }

    public Integer getCenterY()
    {
        return this.centerY;
    }

    public DrawTextOptions setBaselineY(int baselineY)
    {
        PreCondition.assertFalse(this.hasY(), "this.hasY()");

        this.baselineY = baselineY;

        return this;
    }

    public Integer getBaselineY()
    {
        return this.baselineY;
    }

    public DrawTextOptions setBottomY(int bottomY)
    {
        PreCondition.assertFalse(this.hasY(), "this.hasY()");

        this.bottomY = bottomY;

        return this;
    }

    public Integer getBottomY()
    {
        return this.bottomY;
    }

    public DrawTextOptions setText(String text)
    {
        PreCondition.assertNotNull(text, "text");

        this.text = text;

        return this;
    }

    public String getText()
    {
        return this.text;
    }

    public DrawTextOptions setLineColor(Color lineColor)
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
     * Get the JSON representation of this {@link DrawTextOptions} object.
     */
    public JSONObject toJson()
    {
        final JSONObject result = JSONObject.create();
        if (this.leftX != null)
        {
            result.setNumber(DrawTextOptions.leftXPropertyName, this.leftX);
        }
        if (this.centerX != null)
        {
            result.setNumber(DrawTextOptions.centerXPropertyName, this.centerX);
        }
        if (this.rightX != null)
        {
            result.setNumber(DrawTextOptions.rightXPropertyName, this.rightX);
        }
        if (this.topY != null)
        {
            result.setNumber(DrawTextOptions.topYPropertyName, this.topY);
        }
        if (this.centerY != null)
        {
            result.setNumber(DrawTextOptions.centerYPropertyName, this.centerY);
        }
        if (this.baselineY != null)
        {
            result.setNumber(DrawTextOptions.baselineYPropertyName, this.baselineY);
        }
        if (this.bottomY != null)
        {
            result.setNumber(DrawTextOptions.bottomYPropertyName, this.bottomY);
        }
        if (this.text != null)
        {
            result.setString(DrawTextOptions.textPropertyName, this.text);
        }
        if (this.lineColor != null)
        {
            result.setObject(DrawTextOptions.lineColorPropertyName, this.lineColor.toJson());
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
        return rhs instanceof DrawTextOptions rhsOptions && this.equals(rhsOptions);
    }

    public boolean equals(DrawTextOptions rhs)
    {
        return rhs != null &&
            Comparer.equal(this.leftX, rhs.leftX) &&
            Comparer.equal(this.centerX, rhs.centerX) &&
            Comparer.equal(this.rightX, rhs.rightX) &&
            Comparer.equal(this.topY, rhs.topY) &&
            Comparer.equal(this.centerY, rhs.centerY) &&
            Comparer.equal(this.baselineY, rhs.baselineY) &&
            Comparer.equal(this.bottomY, rhs.bottomY) &&
            Comparer.equal(this.text, rhs.text) &&
            Comparer.equal(this.lineColor, rhs.lineColor);
    }
}
