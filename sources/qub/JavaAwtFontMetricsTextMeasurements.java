package qub;

public class JavaAwtFontMetricsTextMeasurements implements TextMeasurements
{
    private final String text;
    private final java.awt.FontMetrics fontMetrics;

    private JavaAwtFontMetricsTextMeasurements(String text, java.awt.FontMetrics fontMetrics)
    {
        PreCondition.assertNotNull(text, "text");
        PreCondition.assertNotNull(fontMetrics, "fontMetrics");

        this.text = text;
        this.fontMetrics = fontMetrics;
    }

    public static JavaAwtFontMetricsTextMeasurements create(String text, java.awt.FontMetrics fontMetrics)
    {
        return new JavaAwtFontMetricsTextMeasurements(text, fontMetrics);
    }

    @Override
    public int getAscent()
    {
        return this.fontMetrics.getAscent();
    }

    @Override
    public int getDescent()
    {
        return this.fontMetrics.getDescent();
    }

    @Override
    public int getHeight()
    {
        return this.fontMetrics.getHeight();
    }

    @Override
    public int getWidth()
    {
        return this.fontMetrics.stringWidth(this.text);
    }
}
