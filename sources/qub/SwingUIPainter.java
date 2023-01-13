package qub;

/**
 * A {@link UIPainter} implementation that wraps around a {@link java.awt.Graphics} object.
 */
public class SwingUIPainter implements UIPainter.Typed<SwingUIPainter>
{
    private final java.awt.Graphics graphics;

    private java.awt.Color fillColor;
    private java.awt.Color lineColor;

    private SwingUIPainter(java.awt.Graphics graphics)
    {
        PreCondition.assertNotNull(graphics, "graphics");

        this.graphics = graphics;

        this.fillColor = JavaAwtColors.convertColorToJavaAwtColor(Color.transparent);
        this.lineColor = this.graphics.getColor();
    }

    public static SwingUIPainter create(java.awt.Graphics graphics)
    {
        return new SwingUIPainter(graphics);
    }

    @Override
    public Color getFillColor()
    {
        return JavaAwtColors.convertJavaAwtColorToColor(this.fillColor);
    }

    @Override
    public Color getLineColor()
    {
        return JavaAwtColors.convertJavaAwtColorToColor(this.lineColor);
    }

    @Override
    public SwingUIPainter setFillColor(Color fillColor)
    {
        PreCondition.assertNotNull(fillColor, "fillColor");

        this.fillColor = JavaAwtColors.convertColorToJavaAwtColor(fillColor);

        return this;
    }

    @Override
    public SwingUIPainter setLineColor(Color lineColor)
    {
        PreCondition.assertNotNull(lineColor, "lineColor");

        this.lineColor = JavaAwtColors.convertColorToJavaAwtColor(lineColor);

        return this;
    }

    @Override
    public SwingUIPainter drawLine(int startX, int startY, int endX, int endY)
    {
        this.graphics.setColor(this.lineColor);
        this.graphics.drawLine(startX, startY, endX, endY);

        return this;
    }

    @Override
    public SwingUIPainter drawOval(int leftX, int topY, int width, int height)
    {
        PreCondition.assertGreaterThanOrEqualTo(width, 0, "width");
        PreCondition.assertGreaterThanOrEqualTo(height, 0, "height");

        if (this.fillColor.getAlpha() != 0)
        {
            this.graphics.setColor(this.fillColor);
            this.graphics.fillOval(leftX, topY, width, height);
        }

        if (this.lineColor.getAlpha() != 0)
        {
            this.graphics.setColor(this.lineColor);
            this.graphics.drawOval(leftX, topY, width, height);
        }

        return this;
    }

    @Override
    public SwingUIPainter drawRectangle(int leftX, int topY, int width, int height)
    {
        PreCondition.assertGreaterThanOrEqualTo(width, 0, "width");
        PreCondition.assertGreaterThanOrEqualTo(height, 0, "height");

        if (this.fillColor.getAlpha() != 0)
        {
            this.graphics.setColor(this.fillColor);
            this.graphics.fillRect(leftX, topY, width, height);
        }

        if (this.lineColor.getAlpha() != 0)
        {
            this.graphics.setColor(this.lineColor);
            this.graphics.drawRect(leftX, topY, width, height);
        }

        return this;
    }

    @Override
    public SwingUIPainter drawText(int leftX, int baselineY, String text)
    {
        PreCondition.assertNotNull(text, "text");

        this.graphics.setColor(this.lineColor);
        this.graphics.drawString(text, leftX, baselineY);

        return this;
    }
}
