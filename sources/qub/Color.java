package qub;

/**
 * A read-only {@link Color} interface.
 */
public interface Color
{
    /**
     * The minimum integer value that a {@link Color} component can have.
     */
    public static final int ComponentMin = 0;
    /**
     * The maximum integer value that a {@link Color} component can have.
     */
    public static final int ComponentMax = 255;

    public static final Color white = Color.create(Color.ComponentMax, Color.ComponentMax, Color.ComponentMax);
    public static final Color black = Color.create(Color.ComponentMin, Color.ComponentMin, Color.ComponentMin);
    public static final Color red = Color.create(Color.ComponentMax, Color.ComponentMin, Color.ComponentMin);
    public static final Color green = Color.create(Color.ComponentMin, Color.ComponentMax, Color.ComponentMin);
    public static final Color blue = Color.create(Color.ComponentMin, Color.ComponentMin, Color.ComponentMax);
    public static final Color transparent = Color.create(Color.ComponentMin, Color.ComponentMin, Color.ComponentMin, Color.ComponentMin);

    /**
     * Create a new opaque {@link Color} using the provided red, green, and blue components
     * (between 0 and 255).
     * @param redComponent The red component of the new {@link Color}.
     * @param greenComponent The green component of the new {@link Color}.
     * @param blueComponent The blue component of the new {@link Color}.
     * @return The new {@link Color}.
     */
    public static MutableColor create(int redComponent, int greenComponent, int blueComponent)
    {
        return MutableColor.create(redComponent, greenComponent, blueComponent, Color.ComponentMax);
    }

    /**
     * Create a new {@link Color} using the provided red, green, blue, and alpha components
     * (between 0 and 255).
     * @param redComponent The red component of the new {@link Color}.
     * @param greenComponent The green component of the new {@link Color}.
     * @param blueComponent The blue component of the new {@link Color}.
     * @param alphaComponent The alpha/opacity component of the new {@link Color}.
     * @return The new {@link Color}.
     */
    public static MutableColor create(int redComponent, int greenComponent, int blueComponent, int alphaComponent)
    {
        return MutableColor.create(redComponent, greenComponent, blueComponent, alphaComponent);
    }

    /**
     * Get the red component of this {@link Color} (between 0 and 255).
     */
    public int getRed();

    /**
     * Get the green component of this {@link Color} (between 0 and 255).
     */
    public int getGreen();

    /**
     * Get the blue component of this {@link Color} (between 0 and 255).
     */
    public int getBlue();

    /**
     * Get the alpha (opacity) component of this {@link Color} (between 0 and 255).
     */
    public int getAlpha();

    /**
     * Get whether this {@link Color} is completely transparent (alpha is 0).
     */
    public default boolean isTransparent()
    {
        return this.getAlpha() == 0;
    }

    /**
     * Get the JSON representation of this {@link Color}.
     */
    public default JSONObject toJson()
    {
        return JSONObject.create()
            .setNumber("red", this.getRed())
            .setNumber("green", this.getGreen())
            .setNumber("blue", this.getBlue())
            .setNumber("alpha", this.getAlpha());
    }

    /**
     * Get the {@link String} representation of the provided {@link Color}.
     * @param color The {@link Color} to get a {@link String} representation of.
     */
    public static String toString(Color color)
    {
        PreCondition.assertNotNull(color, "color");

        return color.toJson().toString();
    }

    /**
     * Get whether the provided {@link Color} is equal to the provided {@link Object}.
     * @param lhs The {@link Color} to compare against the {@link Object}.
     * @param rhs The {@link Object} to compare against the {@link Color}.
     */
    public static boolean equal(Color lhs, Object rhs)
    {
        PreCondition.assertNotNull(lhs, "lhs");

        return rhs instanceof Color && lhs.equals((Color)rhs);
    }

    /**
     * Get whether this {@link Color} is equal to the provided {@link Color}.
     * @param rhs The {@link Color} to compare against this {@link Color}.
     */
    public default boolean equals(Color rhs)
    {
        boolean result = false;

        if (rhs != null)
        {
            final int lhsAlpha = this.getAlpha();
            if (lhsAlpha == rhs.getAlpha())
            {
                if (lhsAlpha == Color.ComponentMin)
                {
                    result = true;
                }
                else
                {
                    result = this.getRed() == rhs.getRed() &&
                         this.getGreen() == rhs.getGreen() &&
                         this.getBlue() == rhs.getBlue();
                }

            }
        }

        return result;
    }

    /**
     * Get the hash code of the provided {@link Color}.
     * @param color The {@link Color} to get the hash code of.
     */
    public static int hashCode(Color color)
    {
        PreCondition.assertNotNull(color, "color");

        return Hash.getHashCode(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }
}
