package qub;

/**
 * A {@link Color} object that can be modified.
 */
public class MutableColor implements Color
{
    private int redComponent;
    private int greenComponent;
    private int blueComponent;
    private int alphaComponent;

    private MutableColor(int red, int green, int blue, int alpha)
    {
        this.setRed(red);
        this.setGreen(green);
        this.setBlue(blue);
        this.setAlpha(alpha);
    }

    /**
     * Create a new opaque {@link MutableColor} using the provided red, green, and blue components
     * (between 0 and 255).
     * @param red The red component of the new {@link MutableColor}.
     * @param green The green component of the new {@link MutableColor}.
     * @param blue The blue component of the new {@link MutableColor}.
     * @return The new {@link MutableColor}.
     */
    public static MutableColor create(int red, int green, int blue)
    {
        return MutableColor.create(red, green, blue, MutableColor.ComponentMax);
    }

    /**
     * Create a new {@link MutableColor} using the provided red, green, blue, and alpha components
     * (between 0 and 255).
     * @param red The red component of the new {@link MutableColor}.
     * @param green The green component of the new {@link MutableColor}.
     * @param blue The blue component of the new {@link MutableColor}.
     * @param alpha The alpha/opacity component of the new {@link MutableColor}.
     * @return The new {@link MutableColor}.
     */
    public static MutableColor create(int red, int green, int blue, int alpha)
    {
        return new MutableColor(red, green, blue, alpha);
    }

    @Override
    public int getRed()
    {
        return this.redComponent;
    }

    /**
     * Set the red component of this {@link MutableColor} (between 0 and 255).
     * @param red The red component of this {@link MutableColor} (between 0 and 255).
     * @return This object for method chaining.
     */
    public MutableColor setRed(int red)
    {
        PreCondition.assertBetween(MutableColor.ComponentMin, red, MutableColor.ComponentMax, "red");

        this.redComponent = red;

        return this;
    }

    @Override
    public int getGreen()
    {
        return this.greenComponent;
    }

    /**
     * Set the green component of this {@link MutableColor} (between 0 and 255).
     * @param green The green component of this {@link MutableColor} (between 0 and 255).
     * @return This object for method chaining.
     */
    public MutableColor setGreen(int green)
    {
        PreCondition.assertBetween(MutableColor.ComponentMin, green, MutableColor.ComponentMax, "green");

        this.greenComponent = green;

        return this;
    }

    @Override
    public int getBlue()
    {
        return this.blueComponent;
    }

    /**
     * Set the blue component of this {@link MutableColor} (between 0 and 255).
     * @param blue The blue component of this {@link MutableColor} (between 0 and 255).
     * @return This object for method chaining.
     */
    public MutableColor setBlue(int blue)
    {
        PreCondition.assertBetween(MutableColor.ComponentMin, blue, MutableColor.ComponentMax, "blue");

        this.blueComponent = blue;

        return this;
    }

    @Override
    public int getAlpha()
    {
        return this.alphaComponent;
    }

    /**
     * Set the alpha component of this {@link MutableColor} (between 0 and 255).
     * @param alpha The alpha/opacity component of this {@link MutableColor} (between 0 and 255).
     * @return This object for method chaining.
     */
    public MutableColor setAlpha(int alpha)
    {
        PreCondition.assertBetween(MutableColor.ComponentMin, alpha, MutableColor.ComponentMax, "alpha");

        this.alphaComponent = alpha;

        return this;
    }

    @Override
    public String toString()
    {
        return Color.toString(this);
    }

    @Override
    public boolean equals(Object rhs)
    {
        return Color.equal(this, rhs);
    }

    @Override
    public int hashCode()
    {
        return Color.hashCode(this);
    }
}
