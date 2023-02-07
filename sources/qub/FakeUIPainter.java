package qub;

/**
 * A fake {@link UIPainter} implementation.
 */
public class FakeUIPainter implements UIPainter.Typed<FakeUIPainter>
{
    private FakeUIPainter()
    {
    }

    public static FakeUIPainter create()
    {
        return new FakeUIPainter();
    }

    @Override
    public Color getFillColor()
    {
        return null;
    }

    @Override
    public FakeUIPainter setFillColor(Color fillColor)
    {
        return this;
    }

    @Override
    public Color getLineColor()
    {
        return null;
    }

    @Override
    public FakeUIPainter setLineColor(Color lineColor)
    {
        return this;
    }

    @Override
    public FakeUIPainter drawLine(int startX, int startY, int endX, int endY)
    {
        return this;
    }

    @Override
    public FakeUIPainter drawOval(int leftX, int topY, int width, int height)
    {
        PreCondition.assertGreaterThanOrEqualTo(width, 0, "width");
        PreCondition.assertGreaterThanOrEqualTo(height, 0, "height");

        return this;
    }

    @Override
    public FakeUIPainter drawRectangle(int leftX, int topY, int width, int height)
    {
        PreCondition.assertGreaterThanOrEqualTo(width, 0, "width");
        PreCondition.assertGreaterThanOrEqualTo(height, 0, "height");

        return this;
    }

    @Override
    public FakeUIPainter drawText(int leftX, int baselineY, String text)
    {
        PreCondition.assertNotNull(text, "text");

        return this;
    }

    @Override
    public TextMeasurements getTextMeasurements(String text)
    {
        return null;
    }
}
