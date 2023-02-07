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

    private Integer leftX;
    private Integer centerX;
    private Integer rightX;

    private Integer topY;
    private Integer centerY;
    private Integer baselineY;
    private Integer bottomY;

    private String text;

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

    /**
     * Set the left edge of where the text will be drawn.
     * @param leftX The left edge of where the text will be drawn at.
     * @return This object for method chaining.
     */
    public DrawTextOptions setLeftX(int leftX)
    {
        PreCondition.assertNull(this.getCenterX(), "this.getCenterX()");
        PreCondition.assertNull(this.getRightX(), "this.getRightX()");

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
        PreCondition.assertNull(this.getLeftX(), "this.getLeftX()");
        PreCondition.assertNull(this.getRightX(), "this.getRightX()");

        this.centerX = centerX;

        return this;
    }

    public Integer getCenterX()
    {
        return this.centerX;
    }

    public DrawTextOptions setRightX(int rightX)
    {
        PreCondition.assertNull(this.getLeftX(), "this.getLeftX()");
        PreCondition.assertNull(this.getCenterX(), "this.getCenterX()");

        this.rightX = rightX;

        return this;
    }

    public Integer getRightX()
    {
        return this.rightX;
    }

    public DrawTextOptions setTopY(int topY)
    {
        PreCondition.assertNull(this.getCenterY(), "this.getCenterY()");
        PreCondition.assertNull(this.getBaselineY(), "this.getBaselineY()");
        PreCondition.assertNull(this.getBottomY(), "this.getBottomY()");

        this.topY = topY;

        return this;
    }

    public Integer getTopY()
    {
        return this.topY;
    }

    public DrawTextOptions setCenterY(int centerY)
    {
        PreCondition.assertNull(this.getTopY(), "this.getTopY()");
        PreCondition.assertNull(this.getBaselineY(), "this.getBaselineY()");
        PreCondition.assertNull(this.getBottomY(), "this.getBottomY()");

        this.centerY = centerY;

        return this;
    }

    public Integer getCenterY()
    {
        return this.centerY;
    }

    public DrawTextOptions setBaselineY(int baselineY)
    {
        PreCondition.assertNull(this.getTopY(), "this.getTopY()");
        PreCondition.assertNull(this.getCenterY(), "this.getCenterY()");
        PreCondition.assertNull(this.getBottomY(), "this.getBottomY()");

        this.baselineY = baselineY;

        return this;
    }

    public Integer getBaselineY()
    {
        return this.baselineY;
    }

    public DrawTextOptions setBottomY(int bottomY)
    {
        PreCondition.assertNull(this.getTopY(), "this.getTopY()");
        PreCondition.assertNull(this.getCenterY(), "this.getCenterY()");
        PreCondition.assertNull(this.getBaselineY(), "this.getBaselineY()");

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
        return rhs instanceof DrawTextOptions && this.equals((DrawTextOptions)rhs);
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
            Comparer.equal(this.text, rhs.text);
    }
}
