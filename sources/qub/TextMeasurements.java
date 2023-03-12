package qub;

/**
 * Measurements for a piece of text.
 */
public interface TextMeasurements
{
    public static BasicTextMeasurements create()
    {
        return BasicTextMeasurements.create();
    }

    public static JavaAwtFontMetricsTextMeasurements create(String text, java.awt.FontMetrics fontMetrics)
    {
        return JavaAwtFontMetricsTextMeasurements.create(text, fontMetrics);
    }

    /**
     * Get the ascent of the text.
     */
    public int getAscent();

    /**
     * Get the descent of the text.
     */
    public int getDescent();

    /**
     * Get the height of the text.
     */
    public int getHeight();

    /**
     * Get the width of the text.
     */
    public int getWidth();

    /**
     * Get the JSON representation of this {@link TextMeasurements} object.
     */
    public default JSONObject toJson()
    {
        return JSONObject.create()
            .setNumber("ascent", this.getAscent())
            .setNumber("descent", this.getDescent())
            .setNumber("height", this.getHeight())
            .setNumber("width", this.getWidth());
    }

    /**
     * Get the {@link String} representation of the provided {@link TextMeasurements} object.
     * @param measurements The {@link TextMeasurements} object to get the {@link String}
     *                     representation of.
     */
    public static String toString(TextMeasurements measurements)
    {
        PreCondition.assertNotNull(measurements, "measurements");

        return measurements.toJson().toString();
    }

    /**
     * Get whether the provided {@link TextMeasurements} object is equal to the provided
     * {@link Object}.
     * @param lhs The {@link TextMeasurements} object to compare.
     * @param rhs The {@link Object} object to compare.
     */
    public static boolean equals(TextMeasurements lhs, Object rhs)
    {
        PreCondition.assertNotNull(lhs, "lhs");

        return rhs instanceof TextMeasurements rhsMeasurements && lhs.equals(rhsMeasurements);
    }

    /**
     * Get whether this {@link TextMeasurements} object is equal to the provided
     * {@link TextMeasurements}.
     * @param rhs The {@link TextMeasurements} object to compare against this
     * {@link TextMeasurements}.
     */
    public default boolean equals(TextMeasurements rhs)
    {
        return rhs != null &&
            this.getAscent() == rhs.getAscent() &&
            this.getDescent() == rhs.getDescent() &&
            this.getHeight() == rhs.getHeight() &&
            this.getWidth() == rhs.getWidth();
    }
}
