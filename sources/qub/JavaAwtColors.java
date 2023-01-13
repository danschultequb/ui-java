package qub;

public interface JavaAwtColors
{
    /**
     * Convert the provided {@link Color} to {@link java.awt.Color}.
     * @param color The {@link Color} to convert.
     */
    public static java.awt.Color convertColorToJavaAwtColor(Color color)
    {
        PreCondition.assertNotNull(color, "javaAwtColor");

        final int red = color.getRed();
        final int green = color.getGreen();
        final int blue = color.getBlue();
        final int alpha = color.getAlpha();
        final java.awt.Color result = new java.awt.Color(red, green, blue, alpha);

        PostCondition.assertNotNull(result, "result");

        return result;
    }

    /**
     * Convert the provided {@link java.awt.Color} to {@link Color}.
     * @param javaAwtColor The {@link java.awt.Color} to convert.
     */
    public static Color convertJavaAwtColorToColor(java.awt.Color javaAwtColor)
    {
        PreCondition.assertNotNull(javaAwtColor, "javaAwtColor");

        final int red = javaAwtColor.getRed();
        final int green = javaAwtColor.getGreen();
        final int blue = javaAwtColor.getBlue();
        final int alpha = javaAwtColor.getAlpha();
        final Color result = MutableColor.create(red, green, blue, alpha);

        PostCondition.assertNotNull(result, "result");

        return result;
    }
}
