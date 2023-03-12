package qub;

public class BasicTextMeasurements implements TextMeasurements
{
    private int ascent;
    private int descent;
    private int height;
    private int width;

    private BasicTextMeasurements()
    {
    }

    public static BasicTextMeasurements create()
    {
        return new BasicTextMeasurements();
    }

    @Override
    public int getAscent()
    {
        return this.ascent;
    }

    public BasicTextMeasurements setAscent(int ascent)
    {
        PreCondition.assertGreaterThanOrEqualTo(ascent, 0, "ascent");

        this.ascent = ascent;

        return this;
    }

    @Override
    public int getDescent()
    {
        return this.descent;
    }

    public BasicTextMeasurements setDescent(int descent)
    {
        this.descent = descent;

        return this;
    }

    @Override
    public int getHeight()
    {
        return this.height;
    }

    public BasicTextMeasurements setHeight(int height)
    {
        PreCondition.assertGreaterThanOrEqualTo(height, 0, "height");

        this.height = height;

        return this;
    }

    @Override
    public int getWidth()
    {
        return this.width;
    }

    public BasicTextMeasurements setWidth(int width)
    {
        PreCondition.assertGreaterThanOrEqualTo(width, 0, "width");

        this.width = width;

        return this;
    }

    @Override
    public String toString()
    {
        return TextMeasurements.toString(this);
    }

    @Override
    public boolean equals(Object rhs)
    {
        return TextMeasurements.equals(this, rhs);
    }
}
